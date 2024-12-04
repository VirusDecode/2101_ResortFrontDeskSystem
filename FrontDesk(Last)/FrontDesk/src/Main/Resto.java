/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;
public class Resto extends javax.swing.JFrame {

    /**
     * Creates new form Admin_page
     */
    public Resto() {
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
        Add = new javax.swing.JButton();
        edit = new javax.swing.JButton();
        delete = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 153, 153));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(204, 204, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 153, 51));
        jLabel1.setText("RESTAURANT FIELD");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(298, 298, 298)
                .addComponent(jLabel1)
                .addContainerGap(315, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 950, -1));

        jTable1.setBackground(new java.awt.Color(153, 255, 204));
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

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 510, 370));

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
        jPanel1.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(837, 531, 101, 35));

        Add.setBackground(new java.awt.Color(204, 255, 204));
        Add.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Add.setText("ADD");
        Add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddActionPerformed(evt);
            }
        });
        jPanel1.add(Add, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 440, 133, 48));

        edit.setBackground(new java.awt.Color(204, 204, 255));
        edit.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        edit.setText("UPDATE");
        edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editActionPerformed(evt);
            }
        });
        jPanel1.add(edit, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 440, 133, 48));

        delete.setBackground(new java.awt.Color(255, 204, 204));
        delete.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        delete.setText("DELETE");
        delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteActionPerformed(evt);
            }
        });
        jPanel1.add(delete, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 440, 133, 48));

        jLabel2.setIcon(new javax.swing.ImageIcon("C:\\Users\\garci\\Downloads\\8n89_l7pb_210318 (1).jpg")); // NOI18N
        jLabel2.setText("jLabel2");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 950, 540));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        Menu menu = new Menu();
        menu.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionPerformed
    int selectedRow = jTable1.getSelectedRow();

    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Please select a row to delete.", "No Selection", JOptionPane.WARNING_MESSAGE);
        return;
    }

    int id = (int) jTable1.getValueAt(selectedRow, 0); // Column 0: Id
    String menu = (String) jTable1.getValueAt(selectedRow, 1); // Column 1: Menu

    int confirm = JOptionPane.showConfirmDialog(
        this,
        "Are you sure you want to delete Menu: " + menu + "?",
        "Delete Confirmation",
        JOptionPane.YES_NO_OPTION,
        JOptionPane.WARNING_MESSAGE
    );

    if (confirm == JOptionPane.YES_OPTION) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DatabaseConnection.getConnection();
            String sql = "DELETE FROM Restaurants WHERE Id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            int rowsDeleted = preparedStatement.executeUpdate();
            if (rowsDeleted > 0) {
                ((DefaultTableModel) jTable1.getModel()).removeRow(selectedRow);
                JOptionPane.showMessageDialog(this, "Menu deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error connecting to database:\n" + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (preparedStatement != null) try { preparedStatement.close(); } catch (SQLException e) { e.printStackTrace(); }
            if (connection != null) DatabaseConnection.closeConnection(connection);
        }
    }
    }//GEN-LAST:event_deleteActionPerformed

    private void editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editActionPerformed
 int selectedRow = jTable1.getSelectedRow();

    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Please select a row to edit.", "No Selection", JOptionPane.WARNING_MESSAGE);
        return;
    }

    int id = (int) jTable1.getValueAt(selectedRow, 0); // Column 0: Id
    String currentMenu = (String) jTable1.getValueAt(selectedRow, 1); // Column 1: Menu
    double currentPrice = (double) jTable1.getValueAt(selectedRow, 2); // Column 2: Price

    // Get updated menu name and price
    String updatedMenu = JOptionPane.showInputDialog(this, "Edit Menu Name:", currentMenu);
    if (updatedMenu == null || updatedMenu.trim().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Menu name cannot be empty!", "Validation Error", JOptionPane.WARNING_MESSAGE);
        return;
    }

    String priceInput = JOptionPane.showInputDialog(this, "Edit Price:", currentPrice);
    double updatedPrice;
    try {
        updatedPrice = Double.parseDouble(priceInput);
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Invalid price format. Please enter a valid number.", "Validation Error", JOptionPane.WARNING_MESSAGE);
        return;
    }

    Connection connection = null;
    PreparedStatement preparedStatement = null;

    try {
        connection = DatabaseConnection.getConnection();
        String sql = "UPDATE Restaurants SET Menu = ?, Price = ? WHERE Id = ?";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, updatedMenu);
        preparedStatement.setDouble(2, updatedPrice);
        preparedStatement.setInt(3, id);

        int rowsUpdated = preparedStatement.executeUpdate();
        if (rowsUpdated > 0) {
            jTable1.setValueAt(updatedMenu, selectedRow, 1); // Update Menu column
            jTable1.setValueAt(updatedPrice, selectedRow, 2); // Update Price column
            JOptionPane.showMessageDialog(this, "Menu updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error connecting to database:\n" + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
    } finally {
        if (preparedStatement != null) try { preparedStatement.close(); } catch (SQLException e) { e.printStackTrace(); }
        if (connection != null) DatabaseConnection.closeConnection(connection);
    }
    }//GEN-LAST:event_editActionPerformed

    private void AddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddActionPerformed
      // Prompt user for menu name and price
    String menuName = JOptionPane.showInputDialog(this, "Enter Menu Name:", "Add Menu", JOptionPane.PLAIN_MESSAGE);

    if (menuName == null || menuName.trim().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Menu name cannot be empty!", "Validation Error", JOptionPane.WARNING_MESSAGE);
        return;
    }

    String priceInput = JOptionPane.showInputDialog(this, "Enter Price:", "Add Menu", JOptionPane.PLAIN_MESSAGE);
    if (priceInput == null || priceInput.trim().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Price cannot be empty!", "Validation Error", JOptionPane.WARNING_MESSAGE);
        return;
    }

    double price;
    try {
        price = Double.parseDouble(priceInput);
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Invalid price format. Please enter a valid number.", "Validation Error", JOptionPane.WARNING_MESSAGE);
        return;
    }

    Connection connection = null;
    PreparedStatement preparedStatement = null;

    try {
        connection = DatabaseConnection.getConnection();
        String sql = "INSERT INTO Restaurants (Menu, Price) VALUES (?, ?)";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, menuName);
        preparedStatement.setDouble(2, price);

        int rowsInserted = preparedStatement.executeUpdate();
        if (rowsInserted > 0) {
            JOptionPane.showMessageDialog(this, "Menu added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            loadrestoToTable(); // Refresh table
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error connecting to database:\n" + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
    } finally {
        if (preparedStatement != null) try { preparedStatement.close(); } catch (SQLException e) { e.printStackTrace(); }
        if (connection != null) DatabaseConnection.closeConnection(connection);
    }
    }//GEN-LAST:event_AddActionPerformed

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
            java.util.logging.Logger.getLogger(Resto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Resto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Resto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Resto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Resto().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Add;
    private javax.swing.JButton delete;
    private javax.swing.JButton edit;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
