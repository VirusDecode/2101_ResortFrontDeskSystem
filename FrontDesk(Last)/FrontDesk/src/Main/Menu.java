/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Main;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.io.PrintWriter;
import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;
public class Menu extends javax.swing.JFrame {

    /**
     * Creates new form Menu
     */
    public Menu() {
        initComponents();
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
        room = new javax.swing.JButton();
        res = new javax.swing.JButton();
        Bookingssdad = new javax.swing.JButton();
        pools = new javax.swing.JButton();
        list = new javax.swing.JButton();
        acts = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(102, 255, 204));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        room.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        room.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Main/icons/door-lock.png"))); // NOI18N
        room.setText("  ROOMS");
        room.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        room.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roomActionPerformed(evt);
            }
        });
        jPanel1.add(room, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 110, 177, 90));

        res.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        res.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Main/icons/restaurant-building.png"))); // NOI18N
        res.setText("  RESTAURANT");
        res.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        res.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resActionPerformed(evt);
            }
        });
        jPanel1.add(res, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 240, 180, 90));

        Bookingssdad.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        Bookingssdad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Main/icons/admin.png"))); // NOI18N
        Bookingssdad.setText("   ADMIN");
        Bookingssdad.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        Bookingssdad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BookingssdadActionPerformed(evt);
            }
        });
        jPanel1.add(Bookingssdad, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 380, 180, 95));

        pools.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        pools.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Main/icons/swimming-pool.png"))); // NOI18N
        pools.setText("   POOL'S");
        pools.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        pools.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                poolsActionPerformed(evt);
            }
        });
        jPanel1.add(pools, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 110, 180, 90));

        list.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        list.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Main/icons/invoice.png"))); // NOI18N
        list.setText(" LIST OF PRICE");
        list.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        list.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listActionPerformed(evt);
            }
        });
        jPanel1.add(list, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 380, 180, 95));

        acts.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        acts.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Main/icons/team-building.png"))); // NOI18N
        acts.setText("  ACTIVITIES");
        acts.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        acts.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actsActionPerformed(evt);
            }
        });
        jPanel1.add(acts, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 240, 180, 90));

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
        jPanel1.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(703, 473, 110, 50));

        jPanel2.setBackground(new java.awt.Color(0, 153, 153));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 255, 204));
        jLabel1.setText("RESORT FRONT DESK SYSTEM MENU'S");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 659, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(81, 81, 81))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 840, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon("C:\\Users\\garci\\Downloads\\2304.w030.n003.656B.p15.656 (2).jpg")); // NOI18N
        jLabel2.setText("jLabel2");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 840, 490));

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

    private void roomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roomActionPerformed
        Room room = new Room();
        room.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_roomActionPerformed

    private void resActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resActionPerformed
        Resto resto = new Resto();
        resto.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_resActionPerformed
// Receipt Frame

    private void BookingssdadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BookingssdadActionPerformed
         Admin_page admin_page = new Admin_page();
        admin_page.setVisible(true);
        this.dispose();
    
    }//GEN-LAST:event_BookingssdadActionPerformed

    private void poolsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_poolsActionPerformed
        Pool pool = new Pool();
        pool.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_poolsActionPerformed

    private void listActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listActionPerformed
        Prices price = new Prices();
        price.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_listActionPerformed

    private void actsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actsActionPerformed
        Activities act = new Activities();
        act.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_actsActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        Main main = new Main();
        main.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton7ActionPerformed

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
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Bookingssdad;
    private javax.swing.JButton acts;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton list;
    private javax.swing.JButton pools;
    private javax.swing.JButton res;
    private javax.swing.JButton room;
    // End of variables declaration//GEN-END:variables
}