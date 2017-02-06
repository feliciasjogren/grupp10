/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupp10.bookevent;

import com.toedter.calendar.JDateChooser;
import grupp10.User;
import static grupp10.User.id;
import grupp10.utility.DatabaseHandler;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;

/**
 *
 * @author Lukas Lindgren & Andreas Persson
 */
public class BookEvent extends javax.swing.JFrame {

    /**
     * Creates new form BookEvent
     */
    public BookEvent() {
        initComponents();
        fillHourCombobox();
        fillMinuteCombobox();
        fillSourceList();
    }

    /**
     * Fills the hour comboboxes with hours 01-23.
     */
    private void fillHourCombobox() {
        
        JComboBox[] list = { cbHour1, cbHour2, cbHour3, cbHour4, cbHour5 };
        
        for (JComboBox hcb : list) {
            hcb.removeAllItems();
        }
        
        for (int i = 0; i < 24; i++) {
            for (JComboBox hcb : list) {
                
                if(i <= 9) {
                    hcb.addItem("0" + i);
                } else {
                    hcb.addItem(i + "");
                }            
            }
        }
    }
    
    /**
     * Fills the minute comboboxes with minutes with five minutes between.
     */
    private void fillMinuteCombobox() {
        
        JComboBox[] list = { cbMinute1, cbMinute2, cbMinute3, cbMinute4, cbMinute5 };
        
        for (JComboBox mcb : list) {
            mcb.removeAllItems();
        }
        
        for (int i = 0; i < 60; i = i + 5) {
            
            for (JComboBox mcb : list) {
                mcb.addItem(i + "");
            }
        }
        
    }
    
    private void fillSourceList() {
        
        DefaultListModel listModel = new DefaultListModel();
        
        String sqlParticipants = "select Anvandarnamn, Fornamn, Efternamn from Larare";
        ArrayList<HashMap<String, String>> participants = DatabaseHandler.fetchRows(sqlParticipants);
        
        for(int i = 0; i < participants.size(); i++) {
            listModel.addElement(participants.get(i).get("Fornamn") + " " + participants.get(i).get("Efternamn") + " (" + participants.get(i).get("Anvandarnamn") + ")");
        }
        
        listSourceParticipants.setModel(listModel);
        
    }
    /**
     * Gets the dates from selected date choosers.
     * @return An ArrayList of the dates from selected date choosers.
     */
    private ArrayList<String> getDatesFromDateChooser() {
        
        ArrayList<String> listOfDates = new ArrayList<>();
        JDateChooser[] list = { dcDate1, dcDate2, dcDate3, dcDate4, dcDate5 };
        
        // Loops all datechoosers. If date chooser is not null, add the date to listOfDates.
        for (JDateChooser jdc : list) {
            
            if (jdc.getDate() != null) {
                
                String date = fixStartDate(jdc.getDate());
                listOfDates.add(date); 
                
            }
        }
        
        return listOfDates;
    }
    
    /**
     * Method for inserting meeting info to database.
     */
    private void insertToDatabase() {
        
        String toDbTitle = tfTitle.getText();
        String toDbDescription = taDescription.getText();
        
        ArrayList<String> dates = getDatesFromDateChooser();
        JComboBox[] hours = { cbHour1, cbHour2, cbHour3, cbHour4, cbHour5 };
        JComboBox[] minutes = { cbMinute1, cbMinute2, cbMinute3, cbMinute4, cbMinute5 };
        
        // Inserts information regarding the planned event into database.
        String sqlInsert = "insert into inbjudning (id, larareid, rubrik, beskrivning, bestamttillfalle) values (default, " + User.id + ", '" + toDbTitle + "', '" + toDbDescription + "', NULL)";
                
        // Gets ID of SQL insert.
        String newId = DatabaseHandler.insertGetId(sqlInsert);
        
        for(int i = 0; i < dates.size(); i++) {
            
            String toDbDate = dates.get(i);
            String toDbHours = hours[i].getSelectedItem().toString();
            String toDbMinutes = minutes[i].getSelectedItem().toString();
            
            // Inserts dates and times for the planned event into database.
            DatabaseHandler.insert("insert into inbjudning_tillfalleforslag (id, inbjudningid, datum, timslag, minutslag) values (default, " + newId + ",'" + toDbDate + "', '" + toDbHours + "', '" + toDbMinutes + "')");
        }
    }
    
    /**
     * Method for fixing output date from datechoosers to correct date for database.
     * @param date: The date from the datechooser.
     * @return Fixed format date.
     */
    private String fixStartDate(Date date)
    {   
        String date1 = date.toString();
        String[] datum = date1.split(" ");
        
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
        return ar + "." + manad + "." + dag;   
    }
    
    private void addParticipants() {
        
        
        List<String> addedParticipants = new ArrayList();
        addedParticipants = listSourceParticipants.getSelectedValuesList();
        
        // Loops the list with the added participants and adds each one with the same "inbjudningsid".
        for (String oneParticipant : addedParticipants) {
            DatabaseHandler.insert("insert into inbjudning_deltagare (inbjudningid, deltagare) values (bajs, '" + oneParticipant + "'");
        }
    }
    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dcDate1 = new com.toedter.calendar.JDateChooser();
        tfInviteToMeeting = new javax.swing.JLabel();
        tfTitle = new javax.swing.JTextField();
        lblTitle = new javax.swing.JLabel();
        lblDescription = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        taDescription = new javax.swing.JTextArea();
        cbMinute1 = new javax.swing.JComboBox<>();
        dcDate2 = new com.toedter.calendar.JDateChooser();
        dcDate3 = new com.toedter.calendar.JDateChooser();
        dcDate4 = new com.toedter.calendar.JDateChooser();
        dcDate5 = new com.toedter.calendar.JDateChooser();
        cbHour2 = new javax.swing.JComboBox<>();
        cbMinute2 = new javax.swing.JComboBox<>();
        cbHour3 = new javax.swing.JComboBox<>();
        cbMinute3 = new javax.swing.JComboBox<>();
        cbHour4 = new javax.swing.JComboBox<>();
        cbMinute4 = new javax.swing.JComboBox<>();
        cbHour5 = new javax.swing.JComboBox<>();
        cbMinute5 = new javax.swing.JComboBox<>();
        lblParticipants = new javax.swing.JLabel();
        cbHour1 = new javax.swing.JComboBox<>();
        lblTidKolon = new javax.swing.JLabel();
        lblTidKolon1 = new javax.swing.JLabel();
        lblTidKolon2 = new javax.swing.JLabel();
        lblTidKolon3 = new javax.swing.JLabel();
        lblTidKolon4 = new javax.swing.JLabel();
        lblValjDagOchTid = new javax.swing.JLabel();
        btnInviteParticipants = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        listSourceParticipants = new javax.swing.JList<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        dcDate1.setMaxSelectableDate(new java.util.Date(253370764888000L));
        dcDate1.setMinSelectableDate(new java.util.Date(-62135769512000L));

        tfInviteToMeeting.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        tfInviteToMeeting.setText("Bjud in till möte");

        lblTitle.setText("Rubrik:");

        lblDescription.setText("Beskrivning:");

        taDescription.setColumns(20);
        taDescription.setRows(5);
        jScrollPane1.setViewportView(taDescription);

        cbMinute1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbHour2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbMinute2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbHour3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbMinute3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbHour4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbMinute4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbHour5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbMinute5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        lblParticipants.setText("Deltagare:");

        cbHour1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        lblTidKolon.setText(":");

        lblTidKolon1.setText(":");

        lblTidKolon2.setText(":");

        lblTidKolon3.setText(":");

        lblTidKolon4.setText(":");

        lblValjDagOchTid.setText("Välj dag och tid:");

        btnInviteParticipants.setText("Bjud in deltagare");
        btnInviteParticipants.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInviteParticipantsActionPerformed(evt);
            }
        });

        jScrollPane2.setViewportView(listSourceParticipants);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 431, Short.MAX_VALUE)
                    .addComponent(lblDescription)
                    .addComponent(lblTitle)
                    .addComponent(tfInviteToMeeting)
                    .addComponent(tfTitle))
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblParticipants)
                        .addGap(178, 178, 178))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGap(41, 41, 41))))
            .addGroup(layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblValjDagOchTid)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(dcDate5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                                .addComponent(cbHour5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(dcDate4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cbHour4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(dcDate3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cbHour3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(dcDate2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cbHour2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(dcDate1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cbHour1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(lblTidKolon1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(cbMinute2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(lblTidKolon2, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(cbMinute3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(lblTidKolon3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(lblTidKolon, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(cbMinute1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(lblTidKolon4, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(cbMinute5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(cbMinute4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 182, Short.MAX_VALUE)
                        .addComponent(btnInviteParticipants)))
                .addGap(111, 111, 111))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(tfInviteToMeeting)
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTitle)
                    .addComponent(lblParticipants))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblDescription)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(dcDate1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(cbMinute1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cbHour1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblTidKolon)))
                                .addGap(18, 18, 18)
                                .addComponent(dcDate2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cbHour2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cbMinute2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblTidKolon1)))
                        .addGap(18, 18, 18)
                        .addComponent(dcDate3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbHour3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbMinute3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblTidKolon2))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(lblValjDagOchTid)
                        .addGap(18, 18, 18)
                        .addComponent(lblTidKolon3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 113, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbMinute4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbHour4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(dcDate4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnInviteParticipants)
                            .addComponent(cbMinute5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(11, 11, 11))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbHour5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTidKolon4))
                        .addComponent(dcDate5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(48, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnInviteParticipantsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInviteParticipantsActionPerformed
        addParticipants();
        insertToDatabase();
        
        
    }//GEN-LAST:event_btnInviteParticipantsActionPerformed

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
            java.util.logging.Logger.getLogger(BookEvent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BookEvent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BookEvent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BookEvent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BookEvent().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnInviteParticipants;
    private javax.swing.JComboBox<String> cbHour1;
    private javax.swing.JComboBox<String> cbHour2;
    private javax.swing.JComboBox<String> cbHour3;
    private javax.swing.JComboBox<String> cbHour4;
    private javax.swing.JComboBox<String> cbHour5;
    private javax.swing.JComboBox<String> cbMinute1;
    private javax.swing.JComboBox<String> cbMinute2;
    private javax.swing.JComboBox<String> cbMinute3;
    private javax.swing.JComboBox<String> cbMinute4;
    private javax.swing.JComboBox<String> cbMinute5;
    private com.toedter.calendar.JDateChooser dcDate1;
    private com.toedter.calendar.JDateChooser dcDate2;
    private com.toedter.calendar.JDateChooser dcDate3;
    private com.toedter.calendar.JDateChooser dcDate4;
    private com.toedter.calendar.JDateChooser dcDate5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblDescription;
    private javax.swing.JLabel lblParticipants;
    private javax.swing.JLabel lblTidKolon;
    private javax.swing.JLabel lblTidKolon1;
    private javax.swing.JLabel lblTidKolon2;
    private javax.swing.JLabel lblTidKolon3;
    private javax.swing.JLabel lblTidKolon4;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblValjDagOchTid;
    private javax.swing.JList<String> listSourceParticipants;
    private javax.swing.JTextArea taDescription;
    private javax.swing.JLabel tfInviteToMeeting;
    private javax.swing.JTextField tfTitle;
    // End of variables declaration//GEN-END:variables
}
