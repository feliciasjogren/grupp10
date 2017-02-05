/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupp10.imagehandler;

import grupp10.utility.DatabaseHandler;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;

/**
 * Handles images on informal blog posts
 * @author Robert Östlin
 */
public class ImageHandler {
    // constructor
    public ImageHandler() {
    
    }
    
    /**
     * Uploads an image as a blob to db, attached to blog
     * @author Robert Östlin
     * @param inlaggId Id of post
     * @param bildtext Image text
     * @param path Image path
     */
    public void uploadImageToBlog(String inlaggId, String bildtext, String path) {
        // order feature disabled until possibly needed
        String order = "1";
        
        Connection con = null;
        Statement stmt;
        FileInputStream fis = null;
        
        File file = new File(path);
        
        // FileInputStream with correct path
        try {
            fis = new FileInputStream (path);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        
        // connect to database
        try {
            con = DriverManager.getConnection(DatabaseHandler.connectionJDBC(), DatabaseHandler.getUSERNAME(), DatabaseHandler.getPASSWORD());
            stmt = con.createStatement();

        } catch (Exception e) {
            System.out.println(e);
        }      
        
        // insert image in db as blob
        try {
            String query = "insert into InformellaInlagg_Bild (InformellainlaggId, Bild, Bildtext, Ordning) " +
                           "values (" + inlaggId + ", ?, '" + bildtext + "', " + order + ")";
            System.out.println("uploadFileToBlog(): sql> " + query);
            
            PreparedStatement pst = con.prepareStatement(query);
            pst.setBinaryStream (1, fis, (int) file.length());
            pst.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * Shows the image and image text in two JLabels
     * @author Robert Östlin
     * @param inlaggId Id of post
     * @param showImage JLabel displaying the image
     * @param imageText Image text to display in JLabel
     */
    public void displayImage(String inlaggId, javax.swing.JLabel showImage, javax.swing.JLabel imageText) {
        Connection con = null;
        Statement stmt;
        
        // connect to database
        try {
            con = DriverManager.getConnection(DatabaseHandler.connectionJDBC(), DatabaseHandler.getUSERNAME(), DatabaseHandler.getPASSWORD());
            stmt = con.createStatement();
            
            // execute query
            ResultSet resultSet = stmt.executeQuery("SELECT Bild, Bildtext FROM InformellaInlagg_Bild WHERE InformellainlaggId = " + inlaggId);
            
            // store blob in InputStream
            resultSet.next();
            Blob imageBlob = resultSet.getBlob(1);
            InputStream binaryStream = imageBlob.getBinaryStream(1, imageBlob.length());
            
            imageText.setText(resultSet.getString("Bildtext"));

            // store image from InputStream as BufferedImage in order to scale properly
            BufferedImage img = null;
            try {
                img = ImageIO.read(binaryStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        
            // resize
            Image dimg = img.getScaledInstance(showImage.getWidth(), showImage.getHeight(), Image.SCALE_SMOOTH);
            
            // create an ImageIcon
            ImageIcon imageIcon = new ImageIcon(dimg);
        
            showImage.setIcon(imageIcon);
        } catch (Exception e) {
            System.out.println(e);
        }        
    }

    /**
     * Opens up a file chooser
     * @author Robert Östlin
     * @param jFrame JFrame of current frame
     * @return path to file, if nothing is chosen "" gets returned
     */
    public String fileChooser(javax.swing.JFrame jFrame) {
        // open file chooser
        JFileChooser fc = new JFileChooser();
        fc.showOpenDialog(jFrame);
        String path = "";

        // return if no file is choosen
        if (fc.getSelectedFile() == null) {
            return "";
        }

        // store path
        path = fc.getSelectedFile().toString();
        System.out.println("path: " + path);
        return path;
    }
}