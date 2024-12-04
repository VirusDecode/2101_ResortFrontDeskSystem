/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package FrontDesk;

import Main.DatabaseConnection;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author garci
 */
public class FrontRestaurants extends javax.swing.JFrame {

    /**
     * Creates new form FrontRestaurants
     */
    public FrontRestaurants() {
        initComponents();
        loadrestoToTable();
    }
private void loadrestoToTable() {
    DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
    model.setRowCount(0); // Clear existing rows

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    try {
        // Establish connection to the database
        connection = DatabaseConnection.getConnection();

        // SQL query to fetch restaurant data
        String sql = "SELECT Id, Menu, Price FROM Restaurants";
        preparedStatement = connection.prepareStatement(sql);

        // Execute the query
        resultSet = preparedStatement.executeQuery();

        // Loop through resultSet and populate the table model
        while (resultSet.next()) {
            int id = resultSet.getInt("Id");
            String menu = resultSet.getString("Menu");
            double price = resultSet.getDouble("Price");

            model.addRow(new Object[]{id, menu, price});
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        // Close resources
        if (resultSet != null) try { resultSet.close(); } catch (SQLException e) { e.printStackTrace(); }
        if (preparedStatement != null) try { preparedStatement.close(); } catch (SQLException e) { e.printStackTrace(); }
        if (connection != null) DatabaseConnection.closeConnection(connection);
    }
    }
        private void loadPricesToDropdown(String category, JComboBox<String> dropdown) {
    dropdown.removeAllItems();
    dropdown.addItem("Please choose");
    dropdown.addItem("NONE");
    try {
        Connection connection = DatabaseConnection.getConnection();
        String sql = "SELECT Name FROM Prices WHERE Category = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, category);

        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            dropdown.addItem(resultSet.getString("Name"));
        }

        resultSet.close();
        preparedStatement.close();
        connection.close();
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error loading " + category + " data:\n" + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
    }
}

private double getPriceFromDatabase(String name, String category) {
    if ("NONE".equalsIgnoreCase(name) || "Please choose".equalsIgnoreCase(name)) {
        return 0.0; // No cost for NONE or unselected options
    }

    double price = 0.0;
    try {
        Connection connection = DatabaseConnection.getConnection();
        String sql = "SELECT Price FROM Prices WHERE Name = ? AND Category = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, category);

        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            price = resultSet.getDouble("Price");
        }

        resultSet.close();
        preparedStatement.close();
        connection.close();
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error fetching price:\n" + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
    }
    return price;
}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton7 = new javax.swing.JButton();
        Bookings = new javax.swing.JButton();
        txtsearch = new javax.swing.JTextField();
        btnsearch = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 153, 153));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(204, 204, 255));

        jLabel1.setFont(new java.awt.Font("Segoe Script", 1, 36)); // NOI18N
        jLabel1.setText("RESORT FRONT DESK SYSTEM");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(255, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(236, 236, 236))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jTable1.setBackground(new java.awt.Color(255, 204, 204));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "Menu", "Prices"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 210, 570, 250));

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
        jPanel1.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 480, 101, 35));

        Bookings.setIcon(new javax.swing.ImageIcon("C:\\Users\\garci\\Downloads\\bill (2).png")); // NOI18N
        Bookings.setText("BILLING");
        Bookings.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 5, true));
        Bookings.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BookingsActionPerformed(evt);
            }
        });
        jPanel1.add(Bookings, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 465, 160, 60));

        txtsearch.setBackground(new java.awt.Color(255, 204, 204));
        txtsearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtsearchActionPerformed(evt);
            }
        });
        txtsearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtsearchKeyReleased(evt);
            }
        });
        jPanel1.add(txtsearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 160, 270, 28));

        btnsearch.setBackground(new java.awt.Color(255, 204, 204));
        btnsearch.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnsearch.setText("Search");
        btnsearch.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 3, true));
        btnsearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsearchActionPerformed(evt);
            }
        });
        jPanel1.add(btnsearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 160, 70, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon("C:\\Users\\garci\\Downloads\\resto (3).jpg")); // NOI18N
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 1000, 490));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1001, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
         FrontDesk frontdesk = new FrontDesk();
         frontdesk.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void showReceiptFrame(String fullName, double total) {
    JFrame receiptFrame = new JFrame("Receipt");
    receiptFrame.setLayout(new GridLayout(5, 2));

    JTextField nameField = new JTextField(fullName);
    JTextField contactField = new JTextField();
    JTextField birthdateField = new JTextField();
    JComboBox<String> paymentDropdown = new JComboBox<>(new String[]{"Cash", "Credit Card", "GCash"});

    receiptFrame.add(new JLabel("Name:"));
    receiptFrame.add(nameField);
    receiptFrame.add(new JLabel("Contact:"));
    receiptFrame.add(contactField);
    receiptFrame.add(new JLabel("Birthdate:"));
    receiptFrame.add(birthdateField);
    receiptFrame.add(new JLabel("Payment Method:"));
    receiptFrame.add(paymentDropdown);

    JButton saveButton = new JButton("Save Receipt");
    receiptFrame.add(saveButton);

    saveButton.addActionListener(e -> {
        try {
            String contact = contactField.getText();
            String birthdate = birthdateField.getText();
            String paymentMethod = (String) paymentDropdown.getSelectedItem();

            if (contact.isEmpty() || birthdate.isEmpty()) {
                JOptionPane.showMessageDialog(receiptFrame, "Please fill all fields!", "Validation Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String receiptContent = "Name: " + fullName + "\nContact: " + contact + "\nBirthdate: " + birthdate +
                    "\nPayment Method: " + paymentMethod + "\nTotal: ₱" + String.format("%.2f", total);

            try (PrintWriter writer = new PrintWriter("receipt.txt")) {
                writer.println(receiptContent);
            }

            JOptionPane.showMessageDialog(receiptFrame, "Receipt saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            receiptFrame.dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(receiptFrame, "Error saving receipt:\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    });

    receiptFrame.setSize(400, 300);
    receiptFrame.setVisible(true);
}
    private void BookingsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BookingsActionPerformed
        // TODO add your handling code here:
    JTextField fullNameField = new JTextField();
    JTextField numPeopleField = new JTextField();
    JComboBox<String> roomDropdown = new JComboBox<>();
    JComboBox<String> poolDropdown = new JComboBox<>();
    JComboBox<String> activitiesDropdown = new JComboBox<>();
    JCheckBox parkingCheckbox = new JCheckBox("Parking (₱100)");
    JCheckBox cottageCheckbox = new JCheckBox("Cottage (₱350)");
    JLabel totalLabel = new JLabel("Total: ₱0.00");

    // Load data from Prices table
    loadPricesToDropdown("Room", roomDropdown);
    loadPricesToDropdown("Pool", poolDropdown);
    loadPricesToDropdown("Activities", activitiesDropdown);

    // Panel for user input
    JPanel panel = new JPanel(new GridLayout(8, 2));
    panel.add(new JLabel("Full Name:"));
    panel.add(fullNameField);
    panel.add(new JLabel("Number of People:"));
    panel.add(numPeopleField);
    panel.add(new JLabel("Select Room:"));
    panel.add(roomDropdown);
    panel.add(new JLabel("Select Pool:"));
    panel.add(poolDropdown);
    panel.add(new JLabel("Select Activity:"));
    panel.add(activitiesDropdown);
    panel.add(parkingCheckbox);
    panel.add(cottageCheckbox);
    panel.add(totalLabel);

    // Listener to calculate total dynamically
    ActionListener updateTotalListener = e -> {
        double total = 0.0;

        String selectedRoom = (String) roomDropdown.getSelectedItem();
        String selectedPool = (String) poolDropdown.getSelectedItem();
        String selectedActivity = (String) activitiesDropdown.getSelectedItem();

        if (selectedRoom != null) total += getPriceFromDatabase(selectedRoom, "Room");
        if (selectedPool != null) total += getPriceFromDatabase(selectedPool, "Pool");
        if (selectedActivity != null) total += getPriceFromDatabase(selectedActivity, "Activities");

        if (parkingCheckbox.isSelected()) total += 100;
        if (cottageCheckbox.isSelected()) total += 350;

        totalLabel.setText("Total: ₱" + String.format("%.2f", total));
    };

    roomDropdown.addActionListener(updateTotalListener);
    poolDropdown.addActionListener(updateTotalListener);
    activitiesDropdown.addActionListener(updateTotalListener);
    parkingCheckbox.addActionListener(updateTotalListener);
    cottageCheckbox.addActionListener(updateTotalListener);

    // Show dialog
    int result = JOptionPane.showConfirmDialog(this, panel, "Book a Service", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

    if (result == JOptionPane.OK_OPTION) {
        String fullName = fullNameField.getText();
        String numPeopleText = numPeopleField.getText();

        if (fullName.isEmpty() || numPeopleText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all required fields!", "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            int numPeople = Integer.parseInt(numPeopleText);
            String room = (String) roomDropdown.getSelectedItem();
            String pool = (String) poolDropdown.getSelectedItem();
            String activity = (String) activitiesDropdown.getSelectedItem();
            boolean parking = parkingCheckbox.isSelected();
            boolean cottage = cottageCheckbox.isSelected();
            double total = Double.parseDouble(totalLabel.getText().replace("Total: ₱", ""));

            // Save to database
            Connection connection = DatabaseConnection.getConnection();
            String sql = "INSERT INTO Billing (FullName, NumberOfPeople, Room, Pool, Activity, Parking, Cottage, Total) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, fullName);
            preparedStatement.setInt(2, numPeople);
            preparedStatement.setString(3, room);
            preparedStatement.setString(4, pool);
            preparedStatement.setString(5, activity);
            preparedStatement.setBoolean(6, parking);
            preparedStatement.setBoolean(7, cottage);
            preparedStatement.setDouble(8, total);

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(this, "Booking successful!", "Success", JOptionPane.INFORMATION_MESSAGE);

                // Show receipt frame
                showReceiptFrame(fullName, total);
            }

            preparedStatement.close();
            connection.close();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid number of people or price value!", "Validation Error", JOptionPane.WARNING_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error saving booking:\n" + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }  
    }
    }//GEN-LAST:event_BookingsActionPerformed

    private void txtsearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtsearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtsearchActionPerformed

    private void txtsearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtsearchKeyReleased
        // TODO add your handling code here:
        DefaultTableModel ob = (DefaultTableModel) jTable1.getModel();
        TableRowSorter<DefaultTableModel> obj = new TableRowSorter<>(ob);
        jTable1.setRowSorter(obj);
        obj.setRowFilter(RowFilter.regexFilter(txtsearch.getText()));
    }//GEN-LAST:event_txtsearchKeyReleased

    private void btnsearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsearchActionPerformed
        DefaultTableModel ob = (DefaultTableModel) jTable1.getModel();
        TableRowSorter<DefaultTableModel> obj = new TableRowSorter<>(ob);
        jTable1.setRowSorter(obj);
        obj.setRowFilter(RowFilter.regexFilter(btnsearch.getText()));
    }//GEN-LAST:event_btnsearchActionPerformed

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
            java.util.logging.Logger.getLogger(FrontRestaurants.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrontRestaurants.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrontRestaurants.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrontRestaurants.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrontRestaurants().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Bookings;
    private javax.swing.JButton btnsearch;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtsearch;
    // End of variables declaration//GEN-END:variables
}
