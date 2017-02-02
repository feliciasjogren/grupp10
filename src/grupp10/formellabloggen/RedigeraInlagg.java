package grupp10.formellabloggen;

import grupp10.utility.DatabaseHandler;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JOptionPane;

/**
 *
 * @author AramRahimi & Ludvig 
 */
public class RedigeraInlagg extends javax.swing.JFrame {

    
    private ArrayList<HashMap<String,String>> posts;
    private String ID;
    /**
     * Creates new form AndraInlagg
     */
    public RedigeraInlagg(String ID) {
        this.ID = ID;
        initComponents();
        getSpecificPost();
        printSpecificPost();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelRedigeraInlagg = new javax.swing.JLabel();
        titelTextField1 = new javax.swing.JTextField();
        rubrikLabel1 = new javax.swing.JLabel();
        inlaggLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        inlaggTextArea = new javax.swing.JTextArea();
        jbRedigeringsKnapp = new javax.swing.JButton();
        jbTillbakaKnapp = new javax.swing.JButton();
        errorLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        labelRedigeraInlagg.setText("Redigera inlägg");

        titelTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                titelTextField1ActionPerformed(evt);
            }
        });

        rubrikLabel1.setText("Ny rubrik:");

        inlaggLabel1.setText("Nytt inlägg:");

        inlaggTextArea.setColumns(20);
        inlaggTextArea.setRows(5);
        jScrollPane1.setViewportView(inlaggTextArea);

        jbRedigeringsKnapp.setText("Redigera");
        jbRedigeringsKnapp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbRedigeringsKnappActionPerformed(evt);
            }
        });

        jbTillbakaKnapp.setText("Tillbaka");
        jbTillbakaKnapp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbTillbakaKnappActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(labelRedigeraInlagg, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(232, 232, 232))
            .addGroup(layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jbTillbakaKnapp)
                        .addGap(18, 18, 18)
                        .addComponent(jbRedigeringsKnapp)
                        .addGap(109, 109, 109))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(inlaggLabel1)
                            .addComponent(rubrikLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(errorLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(titelTextField1))
                                .addGap(147, 147, 147)))
                        .addContainerGap(117, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelRedigeraInlagg, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(errorLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(titelTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rubrikLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(inlaggLabel1)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbRedigeringsKnapp)
                    .addComponent(jbTillbakaKnapp))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void titelTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_titelTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_titelTextField1ActionPerformed

// Button to publish the changes that have been done after validating them.
    private void jbRedigeringsKnappActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbRedigeringsKnappActionPerformed

        String nyText = inlaggTextArea.getText();
        String nyRubrik = titelTextField1.getText();
        boolean ok = false;
        
        try{
        DatabaseHandler.update("update Formellainlagg set Rubrik = '" + nyRubrik + "', Text = '" + nyText + "' where Id = " + ID);
        ok = true;
        }
        catch(Exception ex)
        {
            ok = false;
            System.out.println("Error");
        }
        if(nyRubrik.length() > 1 && ok)
        {
            if(nyRubrik.length() < 40)
            {
                if(nyText.length() < 1000)
                {
                    if(nyText.length() > 2)
                    {             
                    JOptionPane.showMessageDialog(null, "Du har ändrat ett inlägg");
                    dispose();
                    }
                    else{
                        errorLabel1.setText("Inlägget måste bestå av minst två tecken");
                    }
                }
                else{
                    errorLabel1.setText("Inlägget får högst bestå av 1000 tecken");
                }
            }
            else{
                errorLabel1.setText("Rubriken får max bestå av 40 tecken");
            }
        }
        else{
            errorLabel1.setText("Rubriken måste minst bestå av 1 tecken");
        }  
    }//GEN-LAST:event_jbRedigeringsKnappActionPerformed

// Button to go back and exit the window.    
    private void jbTillbakaKnappActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbTillbakaKnappActionPerformed
        dispose();
    }//GEN-LAST:event_jbTillbakaKnappActionPerformed

    // Gets the specific post from the database.
    private void getSpecificPost()
    {
        posts = DatabaseHandler.fetchRows("select * from FormellaInlagg where Id = " + ID);
    }
    
    // Prints the specific post so that it may be altered.
    private void printSpecificPost()
    {
        for(HashMap<String, String> post : posts)
        {
            String rubrik = post.get("Rubrik");
            String text = post.get("Text");
            
            inlaggTextArea.setText(text);
            titelTextField1.setText(rubrik);
        }
       
    }
    
            
    /**
     * @param args the command line arguments
     */


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel errorLabel1;
    private javax.swing.JLabel inlaggLabel1;
    private javax.swing.JTextArea inlaggTextArea;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbRedigeringsKnapp;
    private javax.swing.JButton jbTillbakaKnapp;
    private javax.swing.JLabel labelRedigeraInlagg;
    private javax.swing.JLabel rubrikLabel1;
    private javax.swing.JTextField titelTextField1;
    // End of variables declaration//GEN-END:variables
}
