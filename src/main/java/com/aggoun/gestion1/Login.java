/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.aggoun.gestion1;

import com.aggoun.gestion1.DB.DataBase;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;



/**
 *
 * @author Dual Computer
 */

public class Login extends javax.swing.JFrame {

    /**
     * Creates new form Login
     */
    DataBase db = new DataBase();
    public Login() throws SQLException {
        initComponents();
        db.ConnectionToDataBase();
       setLocationRelativeTo(null);
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
        jLabel3 = new javax.swing.JLabel();
        TXT_PASSWORD = new javax.swing.JPasswordField();
        TXT_USER = new javax.swing.JTextField();
        BTN_MODIFIER_PARAMETRE = new javax.swing.JButton();
        BTN_LOGIN = new javax.swing.JButton();
        QUITTER = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setLayout(null);

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(204, 0, 51));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Mot de passe :");

        TXT_PASSWORD.setBackground(new java.awt.Color(255, 255, 255));
        TXT_PASSWORD.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        TXT_PASSWORD.setForeground(new java.awt.Color(0, 0, 0));
        TXT_PASSWORD.setText("jPasswordField1");

        TXT_USER.setBackground(new java.awt.Color(255, 255, 255));
        TXT_USER.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        TXT_USER.setForeground(new java.awt.Color(0, 0, 0));

        BTN_MODIFIER_PARAMETRE.setBackground(new java.awt.Color(255, 255, 0));
        BTN_MODIFIER_PARAMETRE.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        BTN_MODIFIER_PARAMETRE.setForeground(new java.awt.Color(0, 0, 0));
        BTN_MODIFIER_PARAMETRE.setText("Modifier");
        BTN_MODIFIER_PARAMETRE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_MODIFIER_PARAMETREActionPerformed(evt);
            }
        });

        BTN_LOGIN.setBackground(new java.awt.Color(102, 255, 0));
        BTN_LOGIN.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        BTN_LOGIN.setForeground(new java.awt.Color(0, 0, 0));
        BTN_LOGIN.setText("Entrer");
        BTN_LOGIN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_LOGINActionPerformed(evt);
            }
        });

        QUITTER.setBackground(new java.awt.Color(255, 0, 0));
        QUITTER.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        QUITTER.setForeground(new java.awt.Color(0, 0, 0));
        QUITTER.setText("Quitter");
        QUITTER.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                QUITTERActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(204, 0, 51));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Utilisateur :");

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/arriereplanlogin.jpg"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TXT_USER, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TXT_PASSWORD, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(BTN_MODIFIER_PARAMETRE, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(BTN_LOGIN))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(190, 190, 190)
                        .addComponent(QUITTER, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addComponent(jLabel1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jLabel3))
            .addGroup(layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(TXT_USER, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(TXT_PASSWORD, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BTN_MODIFIER_PARAMETRE)
                    .addComponent(BTN_LOGIN))
                .addGap(31, 31, 31)
                .addComponent(QUITTER))
            .addComponent(jLabel1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void QUITTERActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_QUITTERActionPerformed
        System.exit(0);
    }//GEN-LAST:event_QUITTERActionPerformed

    private void BTN_LOGINActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_LOGINActionPerformed
       try{
           char[] password = TXT_PASSWORD.getPassword();
           String user =TXT_USER.getText();
           String passwordToString = new String(password);
           if(db.FindUser(user,passwordToString)==1){
               ACCEUIL1 acceuil= new ACCEUIL1();
               acceuil.setVisible(true);
               this.setVisible(false);
           }else{
               JOptionPane.showMessageDialog(null,"le nom d'utilisateur ou mot de pass incorrect !");
           }
       } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_BTN_LOGINActionPerformed

    private void BTN_MODIFIER_PARAMETREActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_MODIFIER_PARAMETREActionPerformed
      try{
          char[] password = TXT_PASSWORD.getPassword();
           String user =TXT_USER.getText();
           String passwordToString = new String(password);
           if(db.FindUser(user,passwordToString)==1){
                Parametre para=new Parametre();
                para.setVisible(true);
           }else{
               JOptionPane.showMessageDialog(null,"le nom d'utilisateur ou mot de pass incorrect !");
           }
      }catch(Exception e){
          e.printStackTrace();
      }
    }//GEN-LAST:event_BTN_MODIFIER_PARAMETREActionPerformed

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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Login().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTN_LOGIN;
    private javax.swing.JButton BTN_MODIFIER_PARAMETRE;
    private javax.swing.JButton QUITTER;
    private javax.swing.JPasswordField TXT_PASSWORD;
    private javax.swing.JTextField TXT_USER;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
