/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package FrontDesk;

import Main.DatabaseConnection;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
/**
 *
 * @author garci
 */
public class FrontPayment extends javax.swing.JFrame {
     private JComboBox<String> roomComboBox; 
     private JTable paymentTable;
    private JScrollPane scrollPane;
    
    
     public FrontPayment() {
        initComponents();
        loadAvailableRooms();
        loadAvailablePool();
        loadAvailableActivities();
         
    }
     private void loadAvailableRooms() {
    DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(); // Create a new DefaultComboBoxModel
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    try {
        // Establish database connection
        connection = DatabaseConnection.getConnection();

        // SQL query to fetch available rooms from the database
        String sql = "SELECT Room FROM Rooms WHERE Status = 'Available'"; // Adjust this query as needed
        preparedStatement = connection.prepareStatement(sql);

        // Execute the query and retrieve the results
        resultSet = preparedStatement.executeQuery();

        // Add the available room names to the combo box model
        while (resultSet.next()) {
            String roomName = resultSet.getString("Room");
            model.addElement(roomName);  // Add room name to the combo box model
        }

        // If no rooms are found, add a default message
        if (model.getSize() == 0) {
            model.addElement("No available rooms");
        }

        // Set the model of jComboBox2 to the updated model
        jComboBox2.setModel(model);

    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error loading rooms: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
    } finally {
        // Close resources
        try {
            if (resultSet != null) resultSet.close();
            if (preparedStatement != null) preparedStatement.close();
            if (connection != null) DatabaseConnection.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


     
    private void savePaymentDetails() {
        // Retrieve values from UI components
        String nameText = name.getText().trim();
        String contactText = contact.getText().trim();
        String paxText = numberofpax.getText().trim();
        String kidsText = numberofkids.getText().trim();
        String serviceType =servicetype.getText().trim();
        String rooms = jComboBox2.getSelectedItem().toString();
        String pools = jComboBox3.getSelectedItem().toString();
        String activities = jComboBox1.getSelectedItem().toString();
        String checkin = checkinDate.getText().trim();
        String checkout = checkoutDate.getText().trim();
        String paymentMethod = (String) paymentmethodjComboBox2.getSelectedItem();
        // Validate numeric fields (numberofpax and numberofkids)
        
        // Replace the previous check-in and check-out date retrieval with JCalendarComboBox

// Check if dates are selected (null check)

// Format the dates to yyyy-MM-dd

        
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
   
  SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    Date checkinDateObj = null;
    Date checkoutDateObj = null;

    try {
        checkinDateObj = dateFormat.parse(checkin);
        checkoutDateObj = dateFormat.parse(checkout);
    } catch (ParseException e) {
        JOptionPane.showMessageDialog(this, "Error parsing dates.");
        return;
    }

    long diffInMillies = checkoutDateObj.getTime() - checkinDateObj.getTime();
    long diffInDays = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
    
    if (diffInDays <= 0) {
        JOptionPane.showMessageDialog(this, "Check-out date must be after Check-in date.");
        return;
    }

    // Fetch Room Price per Night
    double roomCostPerNight = getRoomPrice(rooms);

    // Fetch Pool Price if selected
    double poolCost = (pools.equals("None")) ? 0 : getPoolPrice(pools);

    // Fetch Activity Price if selected
    double activityCost = (activities.equals("None")) ? 0 : getActivityPrice(activities);

    // Calculate the total room cost (price per night * number of nights)
    double totalRoomCost = roomCostPerNight * diffInDays;

    // Calculate total cost by adding room cost, pool cost, and activity cost
    double totalCost = totalRoomCost + poolCost + activityCost;
       String receipt = "=================================\n"
               + "          Payment Receipt\n"
               + "=====================================\n"
               + String.format("%-20s: %s\n", "Name", nameText)
               + String.format("%-20s: %s\n", "Contact", contactText)
               + String.format("%-20s: %d\n", "Number of Pax", pax)
               + String.format("%-20s: %d\n", "Number of Kids", kids)
               + String.format("%-20s: %s\n", "Service Type", serviceType)
               + String.format("%-20s: %s\n", "Rooms", rooms)
               + String.format("%-20s: %s\n", "Pools", pools)
               + String.format("%-20s: %s\n", "Activities", activities)
               + String.format("%-20s: %s\n", "Check-In Date", checkin)
               + String.format("%-20s: %s\n", "Check-Out Date", checkout)
               + String.format("%-20s: %s\n", "Payment Method", paymentMethod)
               + String.format("%-20s: %s\n", "Total Cost", totalCost) 
               + "---------------------------------\n"
               + "Thank you for your payment!\n"
               + "==================================";
        
         jTextArea1.setText(receipt);
         
         Font customFont = new Font("Monospaced", Font.PLAIN, 14); // Use "Monospaced" for better alignment
         jTextArea1.setFont(customFont);
         
        // Connect to database and save data
        Connection conn = DatabaseConnection.getConnection();   // Using the external DatabaseConnection class
        if (conn != null) {
            try {
                String query = "INSERT INTO payments (name, contact, numberofpax, numberofkids, service_type, rooms, pools, activities, checkin_date, checkout_date, payment_method,total_cost) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setString(1, nameText);
            stmt.setString(2, contactText);
            stmt.setInt(3, pax);
            stmt.setInt(4, kids);
            stmt.setString(5, serviceType);
            stmt.setString(6, rooms);
            stmt.setString(7, pools);
            stmt.setString(8, activities);
            stmt.setString(9, checkin);
            stmt.setString(10, checkout);
            stmt.setString(11, paymentMethod);
            stmt.setDouble(12, totalCost);
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(this, "Payment details saved successfully.");

                 // Reload data after savingd

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error saving payment details.");
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        checkinDate = new javax.swing.JTextField();
        checkoutDate = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        paymentmethodjComboBox2 = new javax.swing.JComboBox<>();
        jButton7 = new javax.swing.JButton();
        jButtonSave = new javax.swing.JButton();
        jComboBox2 = new javax.swing.JComboBox<>();
        jComboBox3 = new javax.swing.JComboBox<>();
        servicetype = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel7 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        jCheckBox3 = new javax.swing.JCheckBox();
        jCheckBox4 = new javax.swing.JCheckBox();
        jCheckBox5 = new javax.swing.JCheckBox();
        jCheckBox6 = new javax.swing.JCheckBox();

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

        name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameActionPerformed(evt);
            }
        });
        jPanel2.add(name, new org.netbeans.lib.awtextra.AbsoluteConstraints(179, 43, 149, -1));

        contact.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                contactActionPerformed(evt);
            }
        });
        jPanel2.add(contact, new org.netbeans.lib.awtextra.AbsoluteConstraints(179, 71, 149, -1));
        jPanel2.add(numberofpax, new org.netbeans.lib.awtextra.AbsoluteConstraints(179, 99, 149, -1));
        jPanel2.add(numberofkids, new org.netbeans.lib.awtextra.AbsoluteConstraints(179, 127, 149, -1));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("Rooms :");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 190, 63, 20));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Pools :");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 220, 63, 20));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Activities :");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 50, 63, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Check-In Date :");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 230, 105, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("Check-Out Date : ");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 260, 112, -1));

        checkinDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkinDateActionPerformed(evt);
            }
        });
        jPanel2.add(checkinDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 230, 149, -1));
        jPanel2.add(checkoutDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 260, 149, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("Payment Method :");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 290, 112, -1));

        paymentmethodjComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cash", "Gcash", "Card" }));
        jPanel2.add(paymentmethodjComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 290, 149, -1));

        jButton7.setBackground(new java.awt.Color(102, 0, 0));
        jButton7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton7.setForeground(new java.awt.Color(255, 255, 255));
        jButton7.setText("Back");
        jButton7.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 5, true));
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 530, 101, 35));

        jButtonSave.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButtonSave.setText("SAVE!");
        jButtonSave.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));
        jButtonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveActionPerformed(evt);
            }
        });
        jPanel2.add(jButtonSave, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 390, 70, 30));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });
        jPanel2.add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 190, 150, -1));

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox3ActionPerformed(evt);
            }
        });
        jPanel2.add(jComboBox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 220, 150, -1));

        servicetype.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                servicetypeActionPerformed(evt);
            }
        });
        jPanel2.add(servicetype, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 160, 150, -1));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        jPanel2.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 50, 150, -1));

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));
        jScrollPane1.setViewportView(jTextArea1);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 40, 330, 380));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("SpecialRequest:");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 90, -1, -1));

        jCheckBox1.setText("Extra Towel (200)");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });
        jPanel2.add(jCheckBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 110, -1, -1));

        jCheckBox2.setText("Extra Blanket (300)");
        jCheckBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox2ActionPerformed(evt);
            }
        });
        jPanel2.add(jCheckBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 110, -1, -1));

        jCheckBox3.setText("Pillow (300)");
        jCheckBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox3ActionPerformed(evt);
            }
        });
        jPanel2.add(jCheckBox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 140, -1, -1));

        jCheckBox4.setText("Toiletries (250)");
        jPanel2.add(jCheckBox4, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 140, -1, -1));

        jCheckBox5.setText("Slipper (200)");
        jCheckBox5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox5ActionPerformed(evt);
            }
        });
        jPanel2.add(jCheckBox5, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 170, -1, -1));

        jCheckBox6.setText("Baby Crib (400)");
        jPanel2.add(jCheckBox6, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 170, -1, -1));

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
private double getRoomPrice(String room) {
     double roomPrice = 0.0;
    Connection connection = DatabaseConnection.getConnection();
    PreparedStatement stmt = null;
    ResultSet rs = null;

    try {
        String sql = "SELECT PricePerNight FROM Rooms WHERE Room = ?";
        stmt = connection.prepareStatement(sql);
        stmt.setString(1, room);
        rs = stmt.executeQuery();

        if (rs.next()) {
            roomPrice = rs.getDouble("PricePerNight");  // Fetch the price per night for the selected room
        }

    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (connection != null) DatabaseConnection.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    return roomPrice;
}

private double getPoolPrice(String pool) {
     double poolPrice = 0.0;
    Connection connection = DatabaseConnection.getConnection();
    PreparedStatement stmt = null;
    ResultSet rs = null;

    try {
        String sql = "SELECT PricePerNight FROM Pool WHERE pool = ?";
        stmt = connection.prepareStatement(sql);
        stmt.setString(1, pool);
        rs = stmt.executeQuery();

        if (rs.next()) {
            poolPrice = rs.getDouble("PricePerNight");  // Fetch the price per night for the selected pool
        }

    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (connection != null) DatabaseConnection.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    return poolPrice;
}

private double getActivityPrice(String activity) {
   double activityPrice = 0.0;
    Connection connection = DatabaseConnection.getConnection();
    PreparedStatement stmt = null;
    ResultSet rs = null;

    try {
        String sql = "SELECT PricePerActivity FROM Activities WHERE Activities = ?";
        stmt = connection.prepareStatement(sql);
        stmt.setString(1, activity);
        rs = stmt.executeQuery();

        if (rs.next()) {
            activityPrice = rs.getDouble("PricePerActivity");  // Fetch the price for the selected activity
        }

    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (connection != null) DatabaseConnection.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    return activityPrice;
}
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

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        // TODO add your handling code here:
        String selectedRoom = (String) jComboBox2.getSelectedItem();
        System.out.println("Selected Room: " + selectedRoom);
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void servicetypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_servicetypeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_servicetypeActionPerformed

    private void jComboBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox3ActionPerformed
 // Get the selected activity name
                // Get the selected pool name
       // TODO add your handling code here:
        String selectedPool = (String) jComboBox3.getSelectedItem();
        System.out.println("Selected Pool: " + selectedPool);
    }//GEN-LAST:event_jComboBox3ActionPerformed
    
    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed

     // Get the selected activity name
         String selectedActivities = (String) jComboBox1.getSelectedItem();
        System.out.println("Selected Activities: " + selectedActivities);
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nameActionPerformed

    private void checkinDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkinDateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkinDateActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void jCheckBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox3ActionPerformed

    private void jCheckBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox2ActionPerformed

    private void jCheckBox5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox5ActionPerformed

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
    private javax.swing.JTextField checkinDate;
    private javax.swing.JTextField checkoutDate;
    private javax.swing.JTextField contact;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButtonSave;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JCheckBox jCheckBox4;
    private javax.swing.JCheckBox jCheckBox5;
    private javax.swing.JCheckBox jCheckBox6;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
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
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField name;
    private javax.swing.JTextField numberofkids;
    private javax.swing.JTextField numberofpax;
    private javax.swing.JComboBox<String> paymentmethodjComboBox2;
    private javax.swing.JTextField servicetype;
    // End of variables declaration//GEN-END:variables

   
    private void loadAvailablePool() {
       DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(); // Create a new DefaultComboBoxModel
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    try {
        // Establish database connection
        connection = DatabaseConnection.getConnection();

        // SQL query to fetch available pools from the database
        String sql = "SELECT pool FROM pool WHERE status = 'Available'"; // Adjust this query as needed
        preparedStatement = connection.prepareStatement(sql);

        // Execute the query and retrieve the results
        resultSet = preparedStatement.executeQuery();

        // Add the available pool names to the combo box model
        while (resultSet.next()) {
            String poolName = resultSet.getString("pool");
            model.addElement(poolName);  // Add pool name to the combo box model
        }

        // If no pools are found, add a default message
        if (model.getSize() == 0) {
            model.addElement("No available pools");
        }

        // Set the model of jComboBox3 to the updated model
        jComboBox3.setModel(model);

    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error loading pools: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
    } finally {
        // Close resources
        try {
            if (resultSet != null) resultSet.close();
            if (preparedStatement != null) preparedStatement.close();
            if (connection != null) DatabaseConnection.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    }
    private void loadAvailableActivities() {
   DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(); // Create a new DefaultComboBoxModel
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    try {
        // Establish database connection
        connection = DatabaseConnection.getConnection();

        // SQL query to fetch available activities from the database
        // Change 'Available' to 'Active' to match your table's actual status value
        String sql = "SELECT Activities FROM Activities WHERE Status = 'Active'"; // Adjust this query to match your table structure
        preparedStatement = connection.prepareStatement(sql);

        // Execute the query and retrieve the results
        resultSet = preparedStatement.executeQuery();

        // Check if any results are found
        boolean activitiesFound = false;
        while (resultSet.next()) {
            String activitiesName = resultSet.getString("Activities");
            model.addElement(activitiesName);  // Add activity name to the combo box model
            activitiesFound = true;
        }

        // If no activities are found, add a default message
        if (!activitiesFound) {
            model.addElement("No active activities");
        }

        // Set the model of jComboBox1 to the updated model
        jComboBox1.setModel(model);

    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error loading activities: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
    } finally {
        // Close resources
        try {
            if (resultSet != null) resultSet.close();
            if (preparedStatement != null) preparedStatement.close();
            if (connection != null) DatabaseConnection.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    }

   
}
