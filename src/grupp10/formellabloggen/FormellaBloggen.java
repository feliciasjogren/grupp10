/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupp10.formellabloggen;

import grupp10.User;
import grupp10.utility.DatabaseHandler;
import java.beans.PropertyEditorManager;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


/**
 *
 * @author Ludvig
 */
public class FormellaBloggen extends javax.swing.JFrame {

    
    private ArrayList<HashMap<String,String>> posts;
    
    
    public FormellaBloggen() {
        initComponents();
        getAllPosts();
        printPosts();
        findPostForUser();
    }
    
    /**
     * Method to retrieve all the posts from the database.
     */
    private void getAllPosts()
    {
        posts = DatabaseHandler.fetchRows("select * from FormellaInlagg where isDeleted != '1' order by Publiceringsdatum desc ");        
    }  
          
    /**
     * Method to print all the newest posts, up to ten. 
     */  
    private void printPosts()
        {
            
            int i = 0;
            
            // Retrieve the info from the posts and prints it out
            while(i < posts.size() && i < 10)
            {
            String ID = posts.get(i).get("Id");
            String rubrik = posts.get(i).get("Rubrik");
            String text = posts.get(i).get("Text");
            String avsandare = posts.get(i).get("LarareId");
            String date = posts.get(i).get("Publiceringsdatum");
            date = date.substring(0, date.length()-5);
     
            
            // Retrieve the info about a teacher
            ArrayList<HashMap<String, String>> larare = DatabaseHandler.fetchRows("select * from larare where Id = " + avsandare);
            
            String titel = "";
            
            // Creating a String with the info about the retrieved teacher
            for(HashMap<String, String> infoOmLarare : larare)
            {
               titel = ID + ".   " + infoOmLarare.get("Fornamn") + " " + infoOmLarare.get("Efternamn") + ", " + infoOmLarare.get("Titel") + ", " + infoOmLarare.get("Anvandarnamn") + ", " + date; //creating the title          
            }
            
            String message = text;
            
                
                // Adding text to the Areatextfields.
                switch(i) 
                {
                case 0: 
                    ta1.setText(message);
                    postLabel1.setText(titel);
                    break;
                case 1: 
                    ta2.setText(message);
                    postLabel2.setText(titel);
                    break;
                case 2:
                    ta3.setText(message);
                    postLabel3.setText(titel);
                    break;
                case 3:
                    ta4.setText(message);
                    postLabel4.setText(titel);
                    break;
                case 4:
                    ta5.setText(message);
                    postLabel5.setText(titel);
                    break;
                case 5:
                    ta6.setText(message);
                    postLabel6.setText(titel);
                    break;
                case 6:
                    ta7.setText(message);
                    postLabel7.setText(titel);
                    break;
                case 7:
                    ta8.setText(message);
                    postLabel8.setText(titel);
                    break;
                case 8:
                    ta9.setText(message);
                    postLabel9.setText(titel);
                    break;
                case 9:
                    ta10.setText(message);
                    postLabel10.setText(titel);
                    break;                  
                default:
                    JOptionPane.showMessageDialog(null, "Error");
                    break;
                }     
                i++;
            }
            setAllVisible();
            findEmpty(); 
        }    
    
    /**
     * A method to hide all the unused areatextfields.
     * If an areatextfield is empty, it is setVisible(false).
     */
    private void findEmpty()
    {
        if(ta1.getText().isEmpty())
        {
            jScrollPane1.setVisible(false);
            postLabel1.setVisible(false);
            jbRedigera1.setVisible(false);
        }
        if(ta2.getText().isEmpty())
        {
            jScrollPane2.setVisible(false);
            postLabel2.setVisible(false);
            jbRedigera2.setVisible(false);
        }    
        if(ta3.getText().isEmpty())
        {
            jScrollPane3.setVisible(false);
            postLabel3.setVisible(false);
            jbRedigera3.setVisible(false);
        }    
        if(ta4.getText().isEmpty())
        {
            jScrollPane4.setVisible(false);
            postLabel4.setVisible(false);
            jbRedigera4.setVisible(false);
        }
        if(ta5.getText().isEmpty())
        {
            jScrollPane5.setVisible(false);
            postLabel5.setVisible(false);
            jbRedigera5.setVisible(false);
        }
        if(ta6.getText().isEmpty())
        {
            jScrollPane6.setVisible(false);
            postLabel6.setVisible(false);
            jbRedigera6.setVisible(false);
        }
        if(ta7.getText().isEmpty())
        {
            jScrollPane7.setVisible(false);
            postLabel7.setVisible(false);
            jbRedigera7.setVisible(false);
        }
        if(ta8.getText().isEmpty())
        {
            jScrollPane8.setVisible(false);
            postLabel8.setVisible(false);
            jbRedigera8.setVisible(false);
        }
        if(ta9.getText().isEmpty())
        {
            jScrollPane9.setVisible(false);
            postLabel9.setVisible(false);
            jbRedigera9.setVisible(false);
        }
        if(ta10.getText().isEmpty())
        {
            jScrollPane10.setVisible(false);
            postLabel10.setVisible(false);
            jbRedigera10.setVisible(false);
        }
        pack();
    }
    
    /**
     * A method to update/refresh the labels and areatextfield to been shown. 
     */
    private void setAllVisible()
    {
            jScrollPane1.setVisible(true);
            postLabel1.setVisible(true);
            jbRedigera1.setVisible(true);
            
            jScrollPane2.setVisible(true);
            postLabel2.setVisible(true);
            jbRedigera2.setVisible(true);
            
            jScrollPane3.setVisible(true);
            postLabel3.setVisible(true);
            jbRedigera3.setVisible(true);
            
            jScrollPane4.setVisible(true);
            postLabel4.setVisible(true);
            jbRedigera4.setVisible(true);

            jScrollPane5.setVisible(true);
            postLabel5.setVisible(true);
            jbRedigera5.setVisible(true);

            jScrollPane6.setVisible(true);
            postLabel6.setVisible(true); 
            jbRedigera6.setVisible(true);

            jScrollPane7.setVisible(true);
            postLabel7.setVisible(true);  
            jbRedigera7.setVisible(true);

            jScrollPane8.setVisible(true);
            postLabel8.setVisible(true);  
            jbRedigera8.setVisible(true);

            jScrollPane9.setVisible(true);
            postLabel9.setVisible(true);  
            jbRedigera9.setVisible(true);

            jScrollPane10.setVisible(true);
            postLabel10.setVisible(true);   
            jbRedigera10.setVisible(true);            
    }
    
    /**
     * Loop to go through every AreaTextfield
     */
    private void findPostForUser()
    {       
        ArrayList<HashMap<String,String>> inlagg = DatabaseHandler.fetchRows("select * from FormellaInlagg where isDeleted != '1' order by Publiceringsdatum desc");
        
        if(inlagg!=null)
        {    
            for(int i = 0; i < inlagg.size() || i < 10; i++)
            {
                findNotEditable(User.username);
            }   
        }
        else{
            System.out.println("inga hittades");
        }
    }
    
    /**
     * Hiding the edit button for users who dont have access to them
     * @param id 
     */
    private void findNotEditable(String id)
    {
   
    try{  
        if(!ta1.getText().isEmpty())          
        {        
            String[] lararID = postLabel1.getText().split(" ");
            if(!lararID[6].substring(0, lararID[6].length() -1).equalsIgnoreCase(id)){
            jbRedigera1.setVisible(false);
            }
        }
        if(!ta2.getText().isEmpty())          
        {        
            String[] lararID = postLabel2.getText().split(" ");
            if(!lararID[6].substring(0, lararID[6].length() -1).equalsIgnoreCase(id)){
            jbRedigera2.setVisible(false);
            }
        }   
        if(!ta3.getText().isEmpty())          
        {        
            String[] lararID = postLabel3.getText().split(" ");
            if(!lararID[6].substring(0, lararID[6].length() -1).equalsIgnoreCase(id)){
            jbRedigera3.setVisible(false);
            }
        }   
        if(!ta4.getText().isEmpty())          
        {        
            String[] lararID = postLabel4.getText().split(" ");
            if(!lararID[6].substring(0, lararID[6].length() -1).equalsIgnoreCase(id)){
            jbRedigera4.setVisible(false);
            }
        }
        if(!ta5.getText().isEmpty())          
        {        
            String[] lararID = postLabel5.getText().split(" ");
            if(!lararID[6].substring(0, lararID[6].length() -1).equalsIgnoreCase(id)){
            jbRedigera5.setVisible(false);
            }
        }
        if(!ta6.getText().isEmpty())          
        {        
            String[] lararID = postLabel6.getText().split(" ");
            if(!lararID[6].substring(0, lararID[6].length() -1).equalsIgnoreCase(id)){
            jbRedigera6.setVisible(false);
            }
        }
        if(!ta7.getText().isEmpty())          
        {        
            String[] lararID = postLabel7.getText().split(" ");
            if(!lararID[6].substring(0, lararID[6].length() -1).equalsIgnoreCase(id)){
            jbRedigera7.setVisible(false);
            }
        }
        if(!ta8.getText().isEmpty())          
        {        
            String[] lararID = postLabel8.getText().split(" ");
            if(!lararID[6].substring(0, lararID[6].length() -1).equalsIgnoreCase(id)){
            jbRedigera8.setVisible(false);
            }
        }
        if(!ta9.getText().isEmpty())          
        {        
            String[] lararID = postLabel9.getText().split(" ");
            if(!lararID[6].substring(0, lararID[6].length() -1).equalsIgnoreCase(id)){
            jbRedigera9.setVisible(false);
            }
        }
        if(!ta10.getText().isEmpty())          
        {        
            String[] lararID = postLabel10.getText().split(" ");
            if(!lararID[6].substring(0, lararID[6].length() -1).equalsIgnoreCase(id)){
            jbRedigera10.setVisible(false);
            }
        }
      }
        catch(NullPointerException ex){
            System.out.println(ex.getMessage());
        }
    }
    
    private void finding(boolean founded, int index)
    {
        switch(index)
        {
            case 0:
                if(!founded){
                    System.out.println("HEJ");
                    jbRedigera1.setVisible(false);
                }
                break;
            case 1:
                if(!founded){
                    jbRedigera2.setVisible(false);
                }
                break;
                
            case 2:
                if(!founded){
                    jbRedigera3.setVisible(false);
                }
                break;
            case 3:
                if(!founded){
                    jbRedigera4.setVisible(false);
                }
                break;
            case 4:
                if(!founded){
                    jbRedigera5.setVisible(false);
                }
                break;
            case 5:
                if(!founded){
                    jbRedigera6.setVisible(false);
                }
                break;
            case 6:
                if(!founded){
                    jbRedigera7.setVisible(false);
                }
                break;
            case 7:
                if(!founded){
                    jbRedigera8.setVisible(false);
                }
                break;
            case 8:
                if(!founded){
                    jbRedigera1.setVisible(false);
                }
                break;
            case 9:
                if(!founded){
                    jbRedigera10.setVisible(false);
                }
                break;          
        } 
    }
    
    /**
     * Fix a properly String that matches the SQL query
     * @param date 
     * @return 
     */
    private String fixStartDate(String date)
    {
        String[] datum = date.split(" ");
        
        String dag = datum[2];
        String manad = "";
        String ar = datum[5];
        
        switch(datum[1])
        {
            case"Jan":
                manad = "01";
                break;
            case"Feb":
                manad = "02";
                break;
            case"Mar":
                manad = "03";
                break;
            case"Apr":
                manad = "04";
                break;
            case"May":
                manad = "05";
                break;
            case"Jun":
                manad = "06";
                break;
            case"Jul":
                manad = "07";
                break;
            case"Aug":
                manad = "08";
                break;
            case"Sep":
                manad = "09";
                break;
            case"Oct":
                manad = "10";
                break;
            case"Nov":
                manad = "11";
                break;
            case"Dec":
                manad = "12";
                break;
            default:
                manad = "00";             
        }
        
        return ar + "." + manad + "." + dag + " 00:00:00";   
    }    
    
    /**
     * Fix a properly String that matches the SQL query
     * @param date
     * @return 
     */
    private String fixEndDate(String date)
    {
        String[] datum = date.split(" ");
        
        String dag = datum[2];
        String manad = "";
        String ar = datum[5];
        
        switch(datum[1])
        {
            case"Jan":
                manad = "01";
                break;
            case"Feb":
                manad = "02";
                break;
            case"Mar":
                manad = "03";
                break;
            case"Apr":
                manad = "04";
                break;
            case"May":
                manad = "05";
                break;
            case"Jun":
                manad = "06";
                break;
            case"Jul":
                manad = "07";
                break;
            case"Aug":
                manad = "08";
                break;
            case"Sep":
                manad = "09";
                break;
            case"Oct":
                manad = "10";
                break;
            case"Nov":
                manad = "11";
                break;
            case"Dec":
                manad = "12";
                break;
            default:
                manad = "00";             
        }
        
        return ar + "." + manad + "." + dag + " 23:59:59";     
    }
    
    /**
     * Set all labels and AreaTextfields to ""
     */
    private void setAllTextToZero()
    {
            ta1.setText("");
            postLabel1.setText(""); 
            
            ta2.setText("");
            postLabel2.setText(""); 
            
            ta3.setText("");
            postLabel3.setText(""); 
            
            ta4.setText("");
            postLabel4.setText(""); 

            ta5.setText("");
            postLabel5.setText("");   

            ta6.setText("");
            postLabel6.setText(""); 

            ta7.setText("");
            postLabel7.setText("");   

            ta8.setText("");
            postLabel8.setText("");  

            ta9.setText("");
            postLabel9.setText("");  

            ta10.setText("");
            postLabel10.setText(""); 
    
    }    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane11 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ta1 = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        ta2 = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        ta3 = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        ta4 = new javax.swing.JTextArea();
        jScrollPane5 = new javax.swing.JScrollPane();
        ta5 = new javax.swing.JTextArea();
        jScrollPane6 = new javax.swing.JScrollPane();
        ta6 = new javax.swing.JTextArea();
        jScrollPane7 = new javax.swing.JScrollPane();
        ta7 = new javax.swing.JTextArea();
        jScrollPane8 = new javax.swing.JScrollPane();
        ta8 = new javax.swing.JTextArea();
        jScrollPane9 = new javax.swing.JScrollPane();
        ta9 = new javax.swing.JTextArea();
        jScrollPane10 = new javax.swing.JScrollPane();
        ta10 = new javax.swing.JTextArea();
        postLabel1 = new javax.swing.JLabel();
        postLabel2 = new javax.swing.JLabel();
        postLabel3 = new javax.swing.JLabel();
        postLabel4 = new javax.swing.JLabel();
        postLabel5 = new javax.swing.JLabel();
        postLabel6 = new javax.swing.JLabel();
        postLabel7 = new javax.swing.JLabel();
        postLabel8 = new javax.swing.JLabel();
        postLabel9 = new javax.swing.JLabel();
        postLabel10 = new javax.swing.JLabel();
        jbRedigera2 = new javax.swing.JButton();
        jbRedigera3 = new javax.swing.JButton();
        jbRedigera1 = new javax.swing.JButton();
        jbRedigera4 = new javax.swing.JButton();
        jbRedigera5 = new javax.swing.JButton();
        jbRedigera6 = new javax.swing.JButton();
        jbRedigera7 = new javax.swing.JButton();
        jbRedigera8 = new javax.swing.JButton();
        jbRedigera9 = new javax.swing.JButton();
        jbRedigera10 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        startDate = new com.toedter.calendar.JCalendar();
        endDate = new com.toedter.calendar.JCalendar();
        sorteraBtn = new javax.swing.JButton();
        skapaInlaggButton = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        addWindowStateListener(new java.awt.event.WindowStateListener() {
            public void windowStateChanged(java.awt.event.WindowEvent evt) {
                tester(evt);
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jScrollPane11.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane11.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane11.setAutoscrolls(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));

        ta1.setEditable(false);
        ta1.setColumns(20);
        ta1.setRows(5);
        jScrollPane1.setViewportView(ta1);

        ta2.setEditable(false);
        ta2.setColumns(20);
        ta2.setRows(5);
        jScrollPane2.setViewportView(ta2);

        ta3.setEditable(false);
        ta3.setColumns(20);
        ta3.setRows(5);
        jScrollPane3.setViewportView(ta3);

        ta4.setEditable(false);
        ta4.setColumns(20);
        ta4.setRows(5);
        jScrollPane4.setViewportView(ta4);

        ta5.setEditable(false);
        ta5.setColumns(20);
        ta5.setRows(5);
        jScrollPane5.setViewportView(ta5);

        ta6.setEditable(false);
        ta6.setColumns(20);
        ta6.setRows(5);
        jScrollPane6.setViewportView(ta6);

        ta7.setEditable(false);
        ta7.setColumns(20);
        ta7.setRows(5);
        jScrollPane7.setViewportView(ta7);

        ta8.setEditable(false);
        ta8.setColumns(20);
        ta8.setRows(5);
        jScrollPane8.setViewportView(ta8);

        ta9.setEditable(false);
        ta9.setColumns(20);
        ta9.setRows(5);
        jScrollPane9.setViewportView(ta9);

        ta10.setEditable(false);
        ta10.setColumns(20);
        ta10.setRows(5);
        jScrollPane10.setViewportView(ta10);

        postLabel1.setText("postLabel1");

        postLabel2.setText("jLabel2");

        postLabel3.setText("jLabel2");

        postLabel4.setText("jLabel2");

        postLabel5.setText("jLabel2");

        postLabel6.setText("jLabel2");

        postLabel7.setText("jLabel2");

        postLabel8.setText("jLabel2");

        postLabel9.setText("jLabel2");

        postLabel10.setText("jLabel2");

        jbRedigera2.setText("Redigera");
        jbRedigera2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbRedigera2ActionPerformed(evt);
            }
        });

        jbRedigera3.setText("Redigera");
        jbRedigera3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbRedigera3ActionPerformed(evt);
            }
        });

        jbRedigera1.setText("Redigera");
        jbRedigera1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbRedigera1ActionPerformed(evt);
            }
        });

        jbRedigera4.setText("Redigera");
        jbRedigera4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbRedigera4ActionPerformed(evt);
            }
        });

        jbRedigera5.setText("Redigera");
        jbRedigera5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbRedigera5ActionPerformed(evt);
            }
        });

        jbRedigera6.setText("Redigera");
        jbRedigera6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbRedigera6ActionPerformed(evt);
            }
        });

        jbRedigera7.setText("Redigera");
        jbRedigera7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbRedigera7ActionPerformed(evt);
            }
        });

        jbRedigera8.setText("Redigera");
        jbRedigera8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbRedigera8ActionPerformed(evt);
            }
        });

        jbRedigera9.setText("Redigera");
        jbRedigera9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbRedigera9ActionPerformed(evt);
            }
        });

        jbRedigera10.setText("Redigera");
        jbRedigera10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbRedigera10ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(91, 91, 91)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jbRedigera10)
                    .addComponent(jbRedigera9)
                    .addComponent(jbRedigera8)
                    .addComponent(jbRedigera7)
                    .addComponent(jbRedigera3)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(postLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(postLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(postLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(postLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 454, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jbRedigera6))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(postLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jbRedigera5))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(postLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jbRedigera4))
                        .addComponent(postLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(postLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(postLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jbRedigera1))
                        .addComponent(jScrollPane5)
                        .addComponent(jScrollPane10)
                        .addComponent(jScrollPane2)
                        .addComponent(jScrollPane1)
                        .addComponent(jScrollPane3)
                        .addComponent(jScrollPane4)
                        .addComponent(jScrollPane6)
                        .addComponent(jScrollPane7)
                        .addComponent(jScrollPane8)
                        .addComponent(jScrollPane9)
                        .addComponent(postLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jbRedigera2, javax.swing.GroupLayout.Alignment.TRAILING)))
                .addContainerGap(606, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(postLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(postLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbRedigera1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jbRedigera2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(postLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jbRedigera3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(postLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(postLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbRedigera4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(postLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbRedigera5)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(postLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbRedigera6)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jbRedigera7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(postLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(jbRedigera8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(postLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(jbRedigera9)
                .addGap(50, 50, 50)
                .addComponent(postLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jbRedigera10)
                .addContainerGap(156, Short.MAX_VALUE))
        );

        jScrollPane11.setViewportView(jPanel1);

        jLabel1.setFont(new java.awt.Font("Tahoma", 2, 48)); // NOI18N
        jLabel1.setText("Formella Bloggen");

        sorteraBtn.setText("Sortera");
        sorteraBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sorteraBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(sorteraBtn)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(startDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(52, 52, 52)
                        .addComponent(endDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(621, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(startDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(endDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(sorteraBtn)
                .addGap(0, 13, Short.MAX_VALUE))
        );

        skapaInlaggButton.setText("Skapa nytt inlägg");
        skapaInlaggButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                skapaInlaggButtonActionPerformed(evt);
            }
        });

        jButton1.setText("Refresh");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(304, 304, 304)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 368, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(203, 203, 203)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(skapaInlaggButton)
                    .addComponent(jButton1))
                .addGap(81, 81, 81)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 941, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(skapaInlaggButton)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)
                        .addGap(0, 433, Short.MAX_VALUE))
                    .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void skapaInlaggButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_skapaInlaggButtonActionPerformed
        new SkapaInlagg().setVisible(true);
        dispose();
    }//GEN-LAST:event_skapaInlaggButtonActionPerformed

    private void jbRedigera3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbRedigera3ActionPerformed
        String id = postLabel3.getText().substring(0, postLabel3.getText().indexOf("."));
        new RedigeraInlagg(id).setVisible(true);
    }//GEN-LAST:event_jbRedigera3ActionPerformed

    private void jbRedigera1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbRedigera1ActionPerformed
        String id = postLabel1.getText().substring(0, postLabel1.getText().indexOf("."));
        new RedigeraInlagg(id).setVisible(true);
    }//GEN-LAST:event_jbRedigera1ActionPerformed

    private void jbRedigera2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbRedigera2ActionPerformed
        String id = postLabel2.getText().substring(0, postLabel2.getText().indexOf("."));
        new RedigeraInlagg(id).setVisible(true);
    }//GEN-LAST:event_jbRedigera2ActionPerformed

    private void jbRedigera4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbRedigera4ActionPerformed
        String id = postLabel4.getText().substring(0, postLabel4.getText().indexOf("."));
        new RedigeraInlagg(id).setVisible(true);
    }//GEN-LAST:event_jbRedigera4ActionPerformed

    private void jbRedigera5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbRedigera5ActionPerformed
        String id = postLabel5.getText().substring(0, postLabel5.getText().indexOf("."));
        new RedigeraInlagg(id).setVisible(true);
    }//GEN-LAST:event_jbRedigera5ActionPerformed

    private void jbRedigera6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbRedigera6ActionPerformed
        String id = postLabel6.getText().substring(0, postLabel6.getText().indexOf("."));
        new RedigeraInlagg(id).setVisible(true);
    }//GEN-LAST:event_jbRedigera6ActionPerformed

    private void jbRedigera7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbRedigera7ActionPerformed
        String id = postLabel7.getText().substring(0, postLabel7.getText().indexOf("."));
        new RedigeraInlagg(id).setVisible(true);
    }//GEN-LAST:event_jbRedigera7ActionPerformed

    private void jbRedigera8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbRedigera8ActionPerformed
        String id = postLabel8.getText().substring(0, postLabel8.getText().indexOf("."));
        new RedigeraInlagg(id).setVisible(true);
    }//GEN-LAST:event_jbRedigera8ActionPerformed

    private void jbRedigera9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbRedigera9ActionPerformed
        String id = postLabel9.getText().substring(0, postLabel9.getText().indexOf("."));
        new RedigeraInlagg(id).setVisible(true);
    }//GEN-LAST:event_jbRedigera9ActionPerformed

    private void jbRedigera10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbRedigera10ActionPerformed
        String id = postLabel10.getText().substring(0, postLabel10.getText().indexOf("."));
        new RedigeraInlagg(id).setVisible(true);
    }//GEN-LAST:event_jbRedigera10ActionPerformed

    private void sorteraBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sorteraBtnActionPerformed
        
        String datum1 = startDate.getDate().toString();
        String datum2 = endDate.getDate().toString();
        boolean ok = false;
        
        //Control to see if the start date is berofre end date.
        if(startDate.getDate().before(endDate.getDate())) 
        {
            ok = true;
        }
        else{
            JOptionPane.showMessageDialog(null, "Datumen du försökte ange är inte korrekt");
        }
        
        if(datum1 != null && datum2 != null && ok)
        {
            setAllVisible();
            setAllTextToZero();
            
            String startDatum = fixStartDate(datum1);
            String slutDatum = fixEndDate(datum2);
            
            System.out.println(startDatum + " " + slutDatum);
            
            //Retrieve the posts between two chosen dates.
            ArrayList<HashMap<String, String>> sorterade = DatabaseHandler.fetchRows("select * from formellainlagg where Publiceringsdatum between '" + startDatum + "' and '" + slutDatum + "' group by Publiceringsdatum desc");
            
            if(sorterade != null)
            {    
                int i = 0;
            
                while(i < sorterade.size() && i < 10)
                {   
                    String text = sorterade.get(i).get("Text");
                    String ID = sorterade.get(i).get("Id");
                    String avsandare = sorterade.get(i).get("LarareId");
                    String date = sorterade.get(i).get("Publiceringsdatum");
            
                    // Retrieve the info about a teacher
                    ArrayList<HashMap<String, String>> larare = DatabaseHandler.fetchRows("select * from larare where Id = " + avsandare);
            
                    String titel = "";
            
                    // Creating a String with the info about the retrieved teacher
                    for(HashMap<String, String> infoOmLarare : larare)
                    {
                        titel = ID + ".   " + infoOmLarare.get("Fornamn") + " " + infoOmLarare.get("Efternamn") + ", " + infoOmLarare.get("Titel") + ", " + infoOmLarare.get("Anvandarnamn") + ", " + date; //creating the title 
                        titel = titel.substring(0, titel.length() - 2);
                    }
            
                    String message = text;
                    
                        boolean hittad = false;
                        if(User.id.equalsIgnoreCase(avsandare))
                        {
                            hittad = true;
                        }    
                
                    // Adding text to the Areatextfields.
                    switch(i) 
                    {
                    case 0: 
                        ta1.setText(message);
                        postLabel1.setText(titel);
                        break;
                    case 1: 
                        ta2.setText(message);
                        postLabel2.setText(titel);
                        break;
                    case 2:
                        ta3.setText(message);
                        postLabel3.setText(titel);
                        break;
                    case 3:
                        ta4.setText(message);
                        postLabel4.setText(titel);
                        break;
                    case 4:
                        ta5.setText(message);
                        postLabel5.setText(titel);
                        break;
                    case 5:
                        ta6.setText(message);
                        postLabel6.setText(titel);
                        break;
                    case 6:
                        ta7.setText(message);
                        postLabel7.setText(titel);
                        break;
                    case 7:
                        ta8.setText(message);
                        postLabel8.setText(titel);
                        break;
                    case 8:
                        ta9.setText(message);
                        postLabel9.setText(titel);
                        break;
                    case 9:
                        ta10.setText(message);
                        postLabel10.setText(titel);
                        break;                  
                    default:
                        JOptionPane.showMessageDialog(null, "Error");
                        break;
                    }
                    finding(hittad, i);
                    i++;
                }               
            findEmpty();
            }
            else{
                ta1.setText("Inga inlägg hittades");
                postLabel1.setText(".");
                findEmpty();
            }       
        }      
    }//GEN-LAST:event_sorteraBtnActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       
        getAllPosts();
        printPosts();      
    }//GEN-LAST:event_jButton1ActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        setExtendedState(JFrame.MAXIMIZED_BOTH); 
    }//GEN-LAST:event_formWindowOpened

    private void tester(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_tester
        setExtendedState(JFrame.MAXIMIZED_BOTH); 
    }//GEN-LAST:event_tester

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormellaBloggen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormellaBloggen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormellaBloggen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormellaBloggen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormellaBloggen().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JCalendar endDate;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JButton jbRedigera1;
    private javax.swing.JButton jbRedigera10;
    private javax.swing.JButton jbRedigera2;
    private javax.swing.JButton jbRedigera3;
    private javax.swing.JButton jbRedigera4;
    private javax.swing.JButton jbRedigera5;
    private javax.swing.JButton jbRedigera6;
    private javax.swing.JButton jbRedigera7;
    private javax.swing.JButton jbRedigera8;
    private javax.swing.JButton jbRedigera9;
    private javax.swing.JLabel postLabel1;
    private javax.swing.JLabel postLabel10;
    private javax.swing.JLabel postLabel2;
    private javax.swing.JLabel postLabel3;
    private javax.swing.JLabel postLabel4;
    private javax.swing.JLabel postLabel5;
    private javax.swing.JLabel postLabel6;
    private javax.swing.JLabel postLabel7;
    private javax.swing.JLabel postLabel8;
    private javax.swing.JLabel postLabel9;
    private javax.swing.JButton skapaInlaggButton;
    private javax.swing.JButton sorteraBtn;
    private com.toedter.calendar.JCalendar startDate;
    private javax.swing.JTextArea ta1;
    private javax.swing.JTextArea ta10;
    private javax.swing.JTextArea ta2;
    private javax.swing.JTextArea ta3;
    private javax.swing.JTextArea ta4;
    private javax.swing.JTextArea ta5;
    private javax.swing.JTextArea ta6;
    private javax.swing.JTextArea ta7;
    private javax.swing.JTextArea ta8;
    private javax.swing.JTextArea ta9;
    // End of variables declaration//GEN-END:variables
}
