/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.aggoun.gestion1;

import com.aggoun.gestion1.DB.DataBase;
import java.awt.HeadlessException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

/**
 *
 * @author Dual Computer
 */
public class Parametre extends javax.swing.JFrame {

    /**
     * Creates new form Parametre
     */
    DataBase db = new DataBase();
    public Parametre() throws SQLException {
        initComponents();
        setLocationRelativeTo(null);
        db.ConnectionToDataBase();
        db.getAdresseCnrNifTvaDossier(TXT_ADRESSE,TXT_CNR,TXT_NIF,SPIN_TVA_LIVR,LABEL_TRAVAIL);
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
        jLabel86 = new javax.swing.JLabel();
        TXT_ADRESSE = new javax.swing.JTextField();
        jLabel84 = new javax.swing.JLabel();
        TXT_CNR = new javax.swing.JTextField();
        jLabel85 = new javax.swing.JLabel();
        TXT_NIF = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        SPIN_TVA_LIVR = new javax.swing.JSpinner();
        jButton1 = new javax.swing.JButton();
        LABEL_TRAVAIL = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        BTN_UPDATE_PARAMETRE = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        BTN_UPDATE_USER = new javax.swing.JButton();
        jLabel87 = new javax.swing.JLabel();
        jLabel88 = new javax.swing.JLabel();
        TXT_LAST_USER = new javax.swing.JTextField();
        TXT_LAST_PASSWORD = new javax.swing.JTextField();
        jLabel89 = new javax.swing.JLabel();
        jLabel90 = new javax.swing.JLabel();
        TXT_NEW_USER = new javax.swing.JTextField();
        jLabel91 = new javax.swing.JLabel();
        TXT_NEW_PASSWORD = new javax.swing.JPasswordField();
        TXT_CONFIRM_PASSWORD = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel86.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel86.setForeground(new java.awt.Color(0, 0, 0));
        jLabel86.setText("Adresse :");

        TXT_ADRESSE.setBackground(new java.awt.Color(255, 255, 255));
        TXT_ADRESSE.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        TXT_ADRESSE.setForeground(new java.awt.Color(0, 0, 0));

        jLabel84.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel84.setForeground(new java.awt.Color(0, 0, 0));
        jLabel84.setText("CNR :");

        TXT_CNR.setBackground(new java.awt.Color(255, 255, 255));
        TXT_CNR.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        TXT_CNR.setForeground(new java.awt.Color(0, 0, 0));

        jLabel85.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel85.setForeground(new java.awt.Color(0, 0, 0));
        jLabel85.setText("NIF :");

        TXT_NIF.setBackground(new java.awt.Color(255, 255, 255));
        TXT_NIF.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        TXT_NIF.setForeground(new java.awt.Color(0, 0, 0));

        jLabel32.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(0, 0, 0));
        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel32.setText("TVA :");

        SPIN_TVA_LIVR.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        SpinnerNumberModel spinnerModel = new SpinnerNumberModel(0.19, 0.0, 1.0, 0.01); // (initial, min, max, step)
        SPIN_TVA_LIVR.setModel(spinnerModel);
        JSpinner.NumberEditor editor = new JSpinner.NumberEditor(SPIN_TVA_LIVR, "0.##"); // Format d'affichage
        SPIN_TVA_LIVR.setEditor(editor);

        jButton1.setBackground(new java.awt.Color(204, 204, 204));
        jButton1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 0, 0));
        jButton1.setText("...");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        LABEL_TRAVAIL.setBackground(new java.awt.Color(255, 255, 255));
        LABEL_TRAVAIL.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        LABEL_TRAVAIL.setForeground(new java.awt.Color(0, 0, 0));
        LABEL_TRAVAIL.setOpaque(true);

        jLabel67.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel67.setForeground(new java.awt.Color(0, 0, 0));
        jLabel67.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel67.setText("Dossier de travail :");

        BTN_UPDATE_PARAMETRE.setBackground(new java.awt.Color(51, 153, 0));
        BTN_UPDATE_PARAMETRE.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        BTN_UPDATE_PARAMETRE.setForeground(new java.awt.Color(0, 0, 0));
        BTN_UPDATE_PARAMETRE.setText("Modifier");
        BTN_UPDATE_PARAMETRE.setToolTipText("");
        BTN_UPDATE_PARAMETRE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_UPDATE_PARAMETREActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(LABEL_TRAVAIL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel67)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel86)
                                    .addComponent(jLabel85)
                                    .addComponent(jLabel84))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(TXT_ADRESSE)
                                    .addComponent(TXT_CNR)
                                    .addComponent(TXT_NIF)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(SPIN_TVA_LIVR, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 221, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(BTN_UPDATE_PARAMETRE)))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel86)
                    .addComponent(TXT_ADRESSE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel84)
                    .addComponent(TXT_CNR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel85)
                    .addComponent(TXT_NIF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SPIN_TVA_LIVR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel67, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LABEL_TRAVAIL, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(18, 18, 18)
                .addComponent(BTN_UPDATE_PARAMETRE)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));
        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        BTN_UPDATE_USER.setBackground(new java.awt.Color(51, 153, 0));
        BTN_UPDATE_USER.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        BTN_UPDATE_USER.setForeground(new java.awt.Color(0, 0, 0));
        BTN_UPDATE_USER.setText("Modifier");
        BTN_UPDATE_USER.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_UPDATE_USERActionPerformed(evt);
            }
        });

        jLabel87.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel87.setForeground(new java.awt.Color(0, 0, 0));
        jLabel87.setText("Ancien user :");

        jLabel88.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel88.setForeground(new java.awt.Color(0, 0, 0));
        jLabel88.setText("Ancien password :");

        TXT_LAST_USER.setBackground(new java.awt.Color(255, 255, 255));
        TXT_LAST_USER.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        TXT_LAST_USER.setForeground(new java.awt.Color(0, 0, 0));

        TXT_LAST_PASSWORD.setBackground(new java.awt.Color(255, 255, 255));
        TXT_LAST_PASSWORD.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        TXT_LAST_PASSWORD.setForeground(new java.awt.Color(0, 0, 0));

        jLabel89.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel89.setForeground(new java.awt.Color(0, 0, 0));
        jLabel89.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel89.setText("New user :");

        jLabel90.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel90.setForeground(new java.awt.Color(0, 0, 0));
        jLabel90.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel90.setText("New password :");

        TXT_NEW_USER.setBackground(new java.awt.Color(255, 255, 255));
        TXT_NEW_USER.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        TXT_NEW_USER.setForeground(new java.awt.Color(0, 0, 0));

        jLabel91.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel91.setForeground(new java.awt.Color(0, 0, 0));
        jLabel91.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel91.setText("Confirm.password :");

        TXT_NEW_PASSWORD.setText("jPasswordField1");

        TXT_CONFIRM_PASSWORD.setText("jPasswordField2");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel87)
                                    .addComponent(jLabel88))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(TXT_LAST_USER)
                                    .addComponent(TXT_LAST_PASSWORD, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(31, 31, 31)
                                        .addComponent(jLabel89, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel90, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(TXT_NEW_USER)
                                    .addComponent(TXT_NEW_PASSWORD)))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel91, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TXT_CONFIRM_PASSWORD))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(BTN_UPDATE_USER)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel87, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TXT_LAST_USER, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel88, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(TXT_LAST_PASSWORD, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)))
                .addGap(6, 6, 6)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(TXT_NEW_USER)
                    .addComponent(jLabel89, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(TXT_NEW_PASSWORD, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                    .addComponent(jLabel90, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel91, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TXT_CONFIRM_PASSWORD, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(BTN_UPDATE_USER)
                .addGap(29, 29, 29))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

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
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        JFileChooser folderChooser = new JFileChooser();
        folderChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnValue = folderChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            // L'utilisateur a sélectionné un dossier
            String selectedPath = folderChooser.getSelectedFile().getPath();
            LABEL_TRAVAIL.setText(selectedPath);
            JOptionPane.showMessageDialog(null, "Dossier sélectionné : " + selectedPath);
        } else {
            JOptionPane.showMessageDialog(null, "Aucun dossier sélectionné");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void BTN_UPDATE_USERActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_UPDATE_USERActionPerformed
      try{
          char[] passwordNew = TXT_NEW_PASSWORD.getPassword();
          String passwordToStringNew = new String(passwordNew);
          char[] passwordConfirm = TXT_CONFIRM_PASSWORD.getPassword();
          String passwordToStringConfirm = new String(passwordConfirm);
           String user =TXT_NEW_USER.getText();
           String userLast =TXT_LAST_USER.getText();
           String passwordLast =TXT_LAST_PASSWORD.getText();
           if(db.FindUser(userLast,passwordLast)==1 && passwordToStringConfirm.equals(passwordToStringNew)){
               db.UpdateUser(user,passwordToStringNew);
               JOptionPane.showMessageDialog(null, "l'utilisateur a ete bien modifier !");
           }else{
               JOptionPane.showMessageDialog(null, "ancien utilisateur ou mot de pass incorrect!!");
           }
      }catch(HeadlessException e){
          JOptionPane.showMessageDialog(null, "verifier tes donnees!!");
      }
    }//GEN-LAST:event_BTN_UPDATE_USERActionPerformed

    private void BTN_UPDATE_PARAMETREActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_UPDATE_PARAMETREActionPerformed
      String adresse =TXT_ADRESSE.getText();
      String CNR =TXT_CNR.getText();
      String NIF =TXT_NIF.getText();
      Object selectedValue = SPIN_TVA_LIVR.getValue();
      double tva = (double) selectedValue;
      String dossier =LABEL_TRAVAIL.getText();
      if (!adresse.isEmpty() && !CNR.isEmpty() && !NIF.isEmpty() && selectedValue!= null && !dossier.isEmpty()){
          try {
              db.updateUserAdresseDossier(adresse,CNR,NIF,tva,dossier);
               JOptionPane.showMessageDialog(null, "la modification a été bien reussi!");
          } catch (SQLException ex) {
              Logger.getLogger(Parametre.class.getName()).log(Level.SEVERE, null, ex);
          }
      }else{
          JOptionPane.showMessageDialog(null, "remplir tous les champs!!");
      }
    }//GEN-LAST:event_BTN_UPDATE_PARAMETREActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTN_UPDATE_PARAMETRE;
    private javax.swing.JButton BTN_UPDATE_USER;
    private javax.swing.JLabel LABEL_TRAVAIL;
    private javax.swing.JSpinner SPIN_TVA_LIVR;
    private javax.swing.JTextField TXT_ADRESSE;
    private javax.swing.JTextField TXT_CNR;
    private javax.swing.JPasswordField TXT_CONFIRM_PASSWORD;
    private javax.swing.JTextField TXT_LAST_PASSWORD;
    private javax.swing.JTextField TXT_LAST_USER;
    private javax.swing.JPasswordField TXT_NEW_PASSWORD;
    private javax.swing.JTextField TXT_NEW_USER;
    private javax.swing.JTextField TXT_NIF;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    // End of variables declaration//GEN-END:variables
}
