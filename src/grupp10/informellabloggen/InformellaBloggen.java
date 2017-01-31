/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupp10.informellabloggen;

import grupp10.utility.DatabaseHandler;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author Ludvig
 */
public class InformellaBloggen extends javax.swing.JFrame {

    
    private ArrayList<HashMap<String,String>> posts;
    
    
    public InformellaBloggen() {
        initComponents();
        getAllPosts();
        printaMeddelande();
    }
    
    private void getAllPosts()
    {
        posts = DatabaseHandler.fetchRows("select * from FormellaInlagg where isDeleted != '1' group by Publiceringsdatum desc ");        
    }  
    

    private void printaMeddelande()
        {
            
            int i = 0;
            
            while(i < posts.size() && i < 10)
            {
            String ID = posts.get(i).get("Id");
            String rubrik = posts.get(i).get("Rubrik");
            String text = posts.get(i).get("Text");
            String avsandare = posts.get(i).get("LarareId");
            String Date = posts.get(i).get("Publiceringsdatum");
            
            ArrayList<HashMap<String, String>> larare = DatabaseHandler.fetchRows("select * from larare where Id = " + avsandare);
            
            String titel = "";
            
            for(HashMap<String, String> infoOmLarare : larare)
            {
               titel = infoOmLarare.get("Fornamn") + " " + infoOmLarare.get("Efternamn") + ", " + infoOmLarare.get("Titel") + ", " + infoOmLarare.get("Anvandarnamn");           
            }
            
            String message = text + '\n' + '\n' + Date + '\n';
        
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
            hittaTomma();
        }    
    
    private void hittaTomma()
    {
        if(ta1.getText().isEmpty())
        {
            jScrollPane1.setVisible(false);
            postLabel1.setVisible(false);
        }
        if(ta2.getText().isEmpty())
        {
            jScrollPane2.setVisible(false);
            postLabel2.setVisible(false);
        }    
        if(ta3.getText().isEmpty())
        {
            jScrollPane3.setVisible(false);
            postLabel3.setVisible(false);
        }    
        if(ta4.getText().isEmpty())
        {
            jScrollPane4.setVisible(false);
            postLabel4.setVisible(false);
        }
        if(ta5.getText().isEmpty())
        {
            jScrollPane5.setVisible(false);
            postLabel5.setVisible(false);
        }
        if(ta6.getText().isEmpty())
        {
            jScrollPane6.setVisible(false);
            postLabel6.setVisible(false);
        }
        if(ta7.getText().isEmpty())
        {
            jScrollPane7.setVisible(false);
            postLabel7.setVisible(false);
        }
        if(ta8.getText().isEmpty())
        {
            jScrollPane8.setVisible(false);
            postLabel8.setVisible(false);
        }
        if(ta9.getText().isEmpty())
        {
            jScrollPane9.setVisible(false);
            postLabel9.setVisible(false);
        }
        if(ta10.getText().isEmpty())
        {
            jScrollPane10.setVisible(false);
            postLabel10.setVisible(false);
        }
        pack();
    }
    
    private void setAllVisible()
    {
            jScrollPane1.setVisible(true);
            postLabel1.setVisible(true);    
            
            jScrollPane2.setVisible(true);
            postLabel2.setVisible(true);
            
            jScrollPane3.setVisible(true);
            postLabel3.setVisible(true);
            
            jScrollPane4.setVisible(true);
            postLabel4.setVisible(true);   

            jScrollPane5.setVisible(true);
            postLabel5.setVisible(true);   

            jScrollPane6.setVisible(true);
            postLabel6.setVisible(true);   

            jScrollPane7.setVisible(true);
            postLabel7.setVisible(true);   

            jScrollPane8.setVisible(true);
            postLabel8.setVisible(true);   

            jScrollPane9.setVisible(true);
            postLabel9.setVisible(true);   

            jScrollPane10.setVisible(true);
            postLabel10.setVisible(true);               
    }
    
    
    private String createPostMessage(String author, String title, String text, String date, String category)
    {
        String post = title + '\n' + '\n' + text + '\n' + '\n' + date + " " + category;
            
        return post;
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
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jScrollPane11.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(85, 85, 85)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(postLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(postLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(postLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(postLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 454, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(postLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(postLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(postLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(postLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(postLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE)
                        .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE)
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE)
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE)
                        .addComponent(postLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(597, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(postLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(postLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(postLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(postLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(postLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addComponent(postLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(postLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(postLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(postLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(postLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51))
        );

        jScrollPane11.setViewportView(jPanel1);

        jLabel1.setFont(new java.awt.Font("Tahoma", 2, 48)); // NOI18N
        jLabel1.setText("FormellaBloggen");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(437, 437, 437)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 368, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(81, 331, Short.MAX_VALUE)
                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 961, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane11))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(InformellaBloggen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InformellaBloggen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InformellaBloggen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InformellaBloggen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InformellaBloggen().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
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
