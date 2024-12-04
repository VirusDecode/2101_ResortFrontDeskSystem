/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package FrontDesk;

import Main.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.Vector;
import java.sql.DriverManager;
/**
 *
 * @author garci
 */
public class FrontPayment extends javax.swing.JFrame {
     public FrontPayment() {
        initComponents();
        loadPaymentsToTable();  // Load payments into table when the form is initialized
    }
    private void savePaymentDetails() {
        // Retrieve values from UI components
        String nameText = name.getText();
        String contactText = contact.getText();
        String paxText = numberofpax.getText();
        String kidsText = numberofkids.getText();
        String serviceType = (String) jComboBox1.getSelectedItem();
        String rooms = room.getText();
        String poolsText = pools.getText();
        String checkin = checkindate.getText();
        String checkout = checkoutdate.getText();
        String paymentMethod = (String) paymentmethodjComboBox2.getSelectedItem();

        // Validate numeric fields (numberofpax and numberofkids)
        int pax = 0;
        int kids = 0;

        try {
            if (paxText.isEmpty() || kidsText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter valid numbers for Pax and Kids.");
                return;  // Exit if input is empty
            }

            pax = Integer.parseInt(paxText);
            kids = Integer.parseInt(kidsText);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers for Pax and Kids.");
            return; // Exit if the values are not valid integers
        }

        // Validate checkin and checkout dates
        if (checkin.isEmpty() || checkout.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter valid Check-in and Check-out dates.");
            return;
        }

        // Connect to database and save data
        Connection conn = DatabaseConnection.getConnection();   // Using the external DatabaseConnection class
        if (conn != null) {
            try {
                String query = "INSERT INTO payments (name, contact, numberofpax, numberofkids, service_type, rooms, pools, checkin_date, checkout_date, payment_method) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement stmt = conn.prepareStatement(query);

                stmt.setString(1, nameText);
                stmt.setString(2, contactText);
                stmt.setInt(3, pax);
                stmt.setInt(4, kids);
                stmt.setString(5, serviceType);
                stmt.setString(6, rooms);
                stmt.setString(7, poolsText);
                stmt.setString(8, checkin);
                stmt.setString(9, checkout);
                stmt.setString(10, paymentMethod);

                stmt.executeUpdate();
                JOptionPane.showMessageDialog(this, "Payment details saved successfully.");

                loadPaymentsToTable(); // Reload data after saving

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error saving payment details.");
                e.printStackTrace();
            }
        }
    }

    // Load payment data into the table
    private void loadPaymentsToTable() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        Connection conn = DatabaseConnection.getConnection();  // Using the external DatabaseConnection class
        if (conn != null) {
            try {
                String query = "SELECT * FROM payments";
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                // Clear existing rows
                model.setRowCount(0);

                while (rs.next()) {
                    Vector<String> row = new Vector<>();
                    row.add(rs.getString("rooms"));
                    row.add(rs.getString("pools"));
                    row.add(rs.getString("service_type"));
                    model.addRow(row);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
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

        jRadioButtonMenuItem1 = new javax.swing.JRadioButtonMenuItem();
        jPanel2 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        name = new javax.swing.JTextField();
        contact = new javax.swing.JTextField();
        numberofpax = new javax.swing.JTextField();
        numberofkids = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        room = new javax.swing.JTextField();
        pools = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        checkindate = new javax.swing.JTextField();
        checkoutdate = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        paymentmethodjComboBox2 = new javax.swing.JComboBox<>();
        jButton7 = new javax.swing.JButton();
        jButtonSave = new javax.swing.JButton();

        jRadioButtonMenuItem1.setSelected(true);
        jRadioButtonMenuItem1.setText("jRadioButtonMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(204, 204, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel11.setText("Name :");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(47, 46, 43, -1));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel12.setText("Contact No. :");
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(47, 74, 84, -1));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel13.setText("Number of Pax :");
        jPanel2.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(47, 102, 114, -1));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel14.setText("Number Kids :");
        jPanel2.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(47, 130, 114, -1));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel15.setText("Service Type :");
        jPanel2.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(47, 158, 114, -1));
        jPanel2.add(name, new org.netbeans.lib.awtextra.AbsoluteConstraints(179, 43, 149, -1));

        contact.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                contactActionPerformed(evt);
            }
        });
        jPanel2.add(contact, new org.netbeans.lib.awtextra.AbsoluteConstraints(179, 71, 149, -1));
        jPanel2.add(numberofpax, new org.netbeans.lib.awtextra.AbsoluteConstraints(179, 99, 149, -1));
        jPanel2.add(numberofkids, new org.netbeans.lib.awtextra.AbsoluteConstraints(179, 127, 149, -1));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "BOOK", "RESERVE" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        jPanel2.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 160, 149, -1));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("Rooms :");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 190, 63, 20));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Pools :");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 220, 63, 20));
        jPanel2.add(room, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 190, 149, -1));
        jPanel2.add(pools, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 220, 149, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Activities :");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(366, 43, 63, -1));

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "BOOK", "RESERVE" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jList1);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(486, 43, 152, 30));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Check-In Date :");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(366, 114, 105, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("Check-Out Date : ");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(366, 148, 112, -1));
        jPanel2.add(checkindate, new org.netbeans.lib.awtextra.AbsoluteConstraints(489, 111, 149, -1));
        jPanel2.add(checkoutdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(489, 145, 149, -1));

        jTextField6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField6ActionPerformed(evt);
            }
        });
        jPanel2.add(jTextField6, new org.netbeans.lib.awtextra.AbsoluteConstraints(685, 28, 363, 510));

        jTable1.setBackground(new java.awt.Color(255, 255, 204));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Room", "Pools", "Activities"
            }
        ));
        jScrollPane2.setViewportView(jTable1);

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 300, 540, 240));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("Payment Method :");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(366, 182, 112, -1));

        paymentmethodjComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cash", "Gcash", "Card" }));
        jPanel2.add(paymentmethodjComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(489, 179, 149, -1));

        jButton7.setBackground(new java.awt.Color(102, 0, 0));
        jButton7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton7.setForeground(new java.awt.Color(255, 255, 255));
        jButton7.setText("Back");
        jButton7.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 5, true));
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 550, 101, 35));

        jButtonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveActionPerformed(evt);
            }
        });
        jPanel2.add(jButtonSave, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 240, -1, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 1067, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jTextField6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        FrontDesk frontdesk = new FrontDesk();
        frontdesk.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void contactActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_contactActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_contactActionPerformed

    private void jButtonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveActionPerformed
      // TODO add your handling code here:
    System.out.println("Save button clicked"); // Debugging line
    savePaymentDetails();
    }//GEN-LAST:event_jButtonSaveActionPerformed

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
            java.util.logging.Logger.getLogger(FrontPayment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrontPayment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrontPayment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrontPayment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrontPayment().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField checkindate;
    private javax.swing.JTextField checkoutdate;
    private javax.swing.JTextField contact;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButtonSave;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JList<String> jList1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField name;
    private javax.swing.JTextField numberofkids;
    private javax.swing.JTextField numberofpax;
    private javax.swing.JComboBox<String> paymentmethodjComboBox2;
    private javax.swing.JTextField pools;
    private javax.swing.JTextField room;
    // End of variables declaration//GEN-END:variables
}
