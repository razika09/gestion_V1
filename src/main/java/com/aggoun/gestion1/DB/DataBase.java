/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aggoun.gestion1.DB;

import com.aggoun.gestion1.model.Livraison;
import com.aggoun.gestion1.model.Achat;
import com.aggoun.gestion1.model.Client;
import com.aggoun.gestion1.model.ClientPrix;
import com.aggoun.gestion1.model.Fournisseur;
import com.aggoun.gestion1.model.FournisseurPrix;
import com.aggoun.gestion1.model.Produit;
import com.aggoun.gestion1.model.VersementClient;
import com.aggoun.gestion1.model.VersementFournisseur;
import com.ibm.icu.text.RuleBasedNumberFormat;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.DocumentException;

import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
//import com.itextpdf.text.Font;

import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.print.PrinterException;

import java.io.File;

import java.io.FileOutputStream;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.Date;

import java.util.List;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import javax.swing.table.DefaultTableCellRenderer;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;


import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;

import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import java.awt.Font;
//import java.awt.Font;

import java.math.BigDecimal;
import java.util.Vector;
import javax.swing.JSpinner;
import static javax.swing.text.StyleConstants.FontFamily;


/**
 *
 * @author Dual Computer
 */

public class DataBase {
    private final String User ="root";
    private final String password="mynameisnadir";
    String ipLocal ="localhost";
    
    private final String URL = "jdbc:mysql://" +ipLocal +":3306/gestion";
    private Connection cnx;
    private Statement st;
    private ResultSet rst;
    
    //************************connection a la base de donnee**************************
    public void ConnectionToDataBase() throws SQLException{
        try{
            Class.forName("com.mysql.jdbc.Driver");
            cnx=DriverManager.getConnection(URL, User, password);
            
        } catch(ClassNotFoundException ex){
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null,ex);
        } catch(SQLException ex){
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null,ex);
            JOptionPane.showMessageDialog(null,"erreur de connection. activer le server");
        }
    }
    public void UpdateUser(String user, String pass) {
    String sql = "UPDATE user SET user=?, password=? WHERE iduser=1";
    try (PreparedStatement pstmt = cnx.prepareStatement(sql)) {
        // Remplacer les paramètres de la requête par les valeurs fournies
        pstmt.setString(1, user);
        pstmt.setString(2, pass);
        
        // Exécuter la mise à jour
        int rowsAffected = pstmt.executeUpdate();
        
        if (rowsAffected > 0) {
            System.out.println("Utilisateur mis à jour avec succès.");
        } else {
            System.out.println("Aucun utilisateur n'a été mis à jour.");
        }
    } catch (SQLException e) {
        Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, e);
    }
}
   public int FindUser(String user, String pass) {
    int result = 0; // 0 pour utilisateur non trouvé, 1 pour utilisateur trouvé avec succès, -1 pour erreur SQL
    
    String sql = "SELECT user, password FROM user WHERE iduser = 1";
    try (PreparedStatement pstmt = cnx.prepareStatement(sql)) {
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            String dbUser = rs.getString("user");
            String dbPass = rs.getString("password");
            if (dbUser.equals(user) && dbPass.equals(pass)) {
                result = 1; // Utilisateur trouvé
            } else {
                result = 0; // Utilisateur non trouvé
            }
        }
    } catch (SQLException e) {
        Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, e);
        result = -1; // Erreur SQL
    }
    return result;
}
    public class AlertRenderer extends DefaultTableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        // Récupérer le composant par défaut pour le rendu des cellules
        JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        // Vérifier si la valeur de la cellule est "Oui" dans la colonne "Alerte"
        if (value != null && value.toString().equals("ALERTE")) {
            // Appliquer un fond rouge à la cellule
             
            label.setBackground(Color.RED);
        } else {
            // Réinitialiser le fond à la couleur par défaut de la table
            label.setBackground(Color.GREEN);
        }

        return label;
    }
}
    //********************chercher ID du client selon le nom et prenom******************
    public int chercheIdClient(String nom,String prenom) throws SQLException{
        int ID=0;
        String sql="SELECT idclient FROM client WHERE nom=? AND prenom=?";
        try(PreparedStatement st =cnx.prepareStatement(sql)){
            st.setString(1, nom);
            st.setString(2, prenom);
            try (ResultSet rs =st.executeQuery()){
                while (rs.next()){
                    ID=rs.getInt("idclient");
                }
            }
        }
       return ID; 
    }
    //********************verifier si un client existe deja********************************
    public boolean clientExist(String nom,String prenom){
        boolean exists=false;
        try{
            String sql="SELECT COUNT(*) FROM client WHERE nom=? AND prenom=?";
            try(PreparedStatement statement =cnx.prepareStatement(sql)){
                statement.setString(1, nom);
                statement.setString(2,prenom);
                try(ResultSet resultset =statement.executeQuery()){
                    if(resultset.next()){
                        int count=resultset.getInt(1);
                        exists=count>0;
                    }
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return exists;
    }
    //********************verifier si un versement fournisseur existe deja********************************
    public boolean versementExist(String fourni,int num){
        boolean exists=false;
        try{
            String sql="SELECT COUNT(*) FROM versementf WHERE fournisseur=? AND num=?";
            try(PreparedStatement statement =cnx.prepareStatement(sql)){
                statement.setString(1, fourni);
                statement.setInt(2,num);
                try(ResultSet resultset =statement.executeQuery()){
                    if(resultset.next()){
                        int count=resultset.getInt(1);
                        exists=count>0;
                    }
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return exists;
    }
    //********************recuperer le dernier identifiant du client***********************
    public int getIDMaxClient(){
        int id=-1;
        try{
            st=cnx.createStatement();
            String query ="SELECT MAX(idclient) as last_id FROM client";
            rst=(ResultSet) st.executeQuery(query);
            if(rst.next()){
                id=rst.getInt("last_id");
            }
        }catch(SQLException ex){
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE,null,ex);
            JOptionPane.showMessageDialog(null, ex+ "erreur de recuperation du max id");
        }
        return id;
    }
    //****************************ajouter un client***********************************
    public void ajout_client(Client client){
        String query = "INSERT INTO client (idclient,nom, prenom,adresse, email, telephone,CNR,NIF) VALUES (?, ?, ?, ?, ?, ?,?,?)";
        try (PreparedStatement statement = cnx.prepareStatement(query)) {
             statement.setInt(1, client.getId());
             statement.setString(2, client.getNom());
             statement.setString(3, client.getPrenom());
             statement.setString(4, client.getAdresse());
             statement.setString(5, client.getEmail());
             statement.setString(6, client.getTelephone());
             statement.setString(7, client.getCNR());
             statement.setString(8, client.getNIF());
            // Exécuter la requête d'insertion
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Nouveau client inséré avec succès !");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //**************************remplir la table client**************************************
    public void allClient(JTable T){
        try{
            ArrayList<Client> list = new ArrayList<Client>();
            st=cnx.createStatement();
            String sql ="SELECT * from client ORDER BY nom";
            rst=(ResultSet) st.executeQuery(sql);
            
            while(rst.next()){
                Client client = new Client(rst.getInt("idclient"),rst.getString("nom"),rst.getString("prenom"),rst.getString("adresse"),rst.getString("email"),rst.getString("telephone"),rst.getString("CNR"),rst.getString("NIF"));
                list.add(client);
            }
            DefaultTableModel mo =(DefaultTableModel) T.getModel();
            mo.setRowCount(0);
            Object row[] = new Object[8];
            for (int k=0;k<list.size();k++){
                row[0]=list.get(k).getId();
                row[1]=list.get(k).getNom();
                row[2]=list.get(k).getPrenom();
                row[3]=list.get(k).getAdresse();
                row[4]=list.get(k).getEmail();
                row[5]=list.get(k).getTelephone();
                row[6]=list.get(k).getCNR();
                row[7]=list.get(k).getNIF();
                mo.addRow(row);
            }
            list.clear();
        }catch(SQLException ex){
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE,null,ex);
            JOptionPane.showMessageDialog(null, ex+ "erreur de recuperation de la liste des clients");
        }
    }
    //*********************remplir le combobox client********************************
    public void getAllClient(JComboBox combo) throws SQLException{
        try{
            String sql ="SELECT nom,prenom FROM client ORDER BY nom,prenom";
            try (PreparedStatement stmt =cnx.prepareStatement(sql);
                 ResultSet rst =stmt.executeQuery()){
                boolean hasresult = false;
                while (rst.next()){
                    hasresult = true;
                    String nom = rst.getString("nom");
                    String prenom = rst.getString("prenom");
                    combo.addItem(nom + " " + prenom);
                }
                if(!hasresult){
                    System.out.println("pas de client");
                }
                
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    
    //************************modifier un client********************************
    public void updateClient(int id,Client client){
        try{
            String sql ="UPDATE client SET nom=?,prenom=?,adresse=?,email=?,telephone=? WHERE idclient=?";
            PreparedStatement preparedstatement =cnx.prepareStatement(sql);
            preparedstatement.setString(1,client.getNom());
            preparedstatement.setString(2,client.getPrenom());
            preparedstatement.setString(3,client.getAdresse());
            preparedstatement.setString(4,client.getEmail());
            preparedstatement.setString(5,client.getTelephone());
            preparedstatement.setInt(6, id);
            int rowaffected = preparedstatement.executeUpdate();
            if(rowaffected > 0){
                System.out.print("update successful");
            }else{
                System.out.print("no records updated");
            }
            preparedstatement.close();
        }catch(SQLException ex){
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
    //************************modifier un fournisseur********************************
    public void updateFournisseur(int id,Fournisseur fourni){
        try{
            String sql ="UPDATE fournisseur SET nom=?,adresse=?,email=?,telephone=?,CNR=?,NIF=? WHERE idfournisseur=?";
            PreparedStatement preparedstatement =cnx.prepareStatement(sql);
            preparedstatement.setString(1,fourni.getNom());
            
            preparedstatement.setString(2,fourni.getAdresse());
            preparedstatement.setString(3,fourni.getEmail());
            preparedstatement.setString(4,fourni.getTelephone());
            preparedstatement.setString(5,fourni.getCNR());
            preparedstatement.setString(6,fourni.getNIF());
            preparedstatement.setInt(7, id);
            int rowaffected = preparedstatement.executeUpdate();
            if(rowaffected > 0){
                System.out.print("update successful");
            }else{
                System.out.print("no records updated");
            }
            preparedstatement.close();
        }catch(SQLException ex){
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
    
    //*******************************supprimer un client***************************
    public void supprimerClient(int id) {
        String sql = "DELETE FROM client WHERE idclient=?";
        try (PreparedStatement stmt = cnx.prepareStatement(sql)){
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }catch(SQLException ex){
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
    
    //*******************************supprimer un fournisseur***************************
    public void supprimerFournisseur(int id) {
        String sql = "DELETE FROM fournisseur WHERE idfournisseur=?";
        try (PreparedStatement stmt = cnx.prepareStatement(sql)){
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }catch(SQLException ex){
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
    //*******************************supprimer un achat***************************
    public void supprimerAchat(int num,int id) {
        String sql = "DELETE FROM achat WHERE num=? AND id_fournisseur=?";
        try (PreparedStatement stmt = cnx.prepareStatement(sql)){
            stmt.setInt(1, num);
            stmt.setInt(2, id);
            stmt.executeUpdate();
            if (isAchatTableEmpty()) {
            resetAutoIncrement();
        }
        }catch(SQLException ex){
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
     //*******************************supprimer un achat***************************
    public void supprimerAchatByProduit(int num,int id,String produit,String marque) {
        String sql = "DELETE FROM achat WHERE num=? AND id_fournisseur=? AND produit=? AND marque=?";
        try (PreparedStatement stmt = cnx.prepareStatement(sql)){
            stmt.setInt(1, num);
            stmt.setInt(2, id);
            stmt.setString(3, produit);
            stmt.setString(4, marque);
            stmt.executeUpdate();
            if (isAchatTableEmpty()) {
            resetAutoIncrement();
        }
        }catch(SQLException ex){
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
    
    private boolean isAchatTableEmpty() throws SQLException {
    String sql = "SELECT COUNT(*) FROM achat";
    try (Statement stmt = cnx.createStatement();
         ResultSet rs = stmt.executeQuery(sql)) {
        if (rs.next()) {
            int count = rs.getInt(1);
            return count == 0;
        }
    }
    return true; // Par défaut, on suppose que la table est vide
}

private void resetAutoIncrement() throws SQLException {
    String sql = "ALTER TABLE achat AUTO_INCREMENT = 1";
    try (PreparedStatement stmt = cnx.prepareStatement(sql)) {
        stmt.executeUpdate();
    }
}
    //*******************************supprimer un livraison***************************
    public void supprimerLivraison(int num,int id) {
        String sql = "DELETE FROM livraison WHERE num=? AND id_client=?";
        try (PreparedStatement stmt = cnx.prepareStatement(sql)){
            stmt.setInt(1, num);
            stmt.setInt(2, id);
            stmt.executeUpdate();
            if (isLivraisonTableEmpty()) {
            resetAutoIncrement1();
        }
        }catch(SQLException ex){
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
    public void supprimerLivraisonByProduit(int num,int id,String produit,String marque) {
        String sql = "DELETE FROM livraison WHERE num=? AND id_client=? produit=? AND marque=?";
        try (PreparedStatement stmt = cnx.prepareStatement(sql)){
            stmt.setInt(1, num);
            stmt.setInt(2, id);
             stmt.setString(3, produit);
            stmt.setString(4, marque);
            stmt.executeUpdate();
            if (isLivraisonTableEmpty()) {
            resetAutoIncrement1();
        }
        }catch(SQLException ex){
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
    private boolean isLivraisonTableEmpty() throws SQLException {
    String sql = "SELECT COUNT(*) FROM livraison";
    try (Statement stmt = cnx.createStatement();
         ResultSet rs = stmt.executeQuery(sql)) {
        if (rs.next()) {
            int count = rs.getInt(1);
            return count == 0;
        }
    }
    return true; // Par défaut, on suppose que la table est vide
}

private void resetAutoIncrement1() throws SQLException {
    String sql = "ALTER TABLE livraison AUTO_INCREMENT = 1";
    try (PreparedStatement stmt = cnx.prepareStatement(sql)) {
        stmt.executeUpdate();
    }
}

public void getAdresseCnrNifTvaDossier(JTextField adresse,JTextField CNR,JTextField NIF,JSpinner tva,JLabel dossier){
    String sql= "SELECT adresse,CNR,NIF,tva,dossier FROM user WHERE iduser=1";
    try (PreparedStatement st = cnx.prepareStatement(sql)){
           
           
            try (ResultSet rst =(ResultSet) st.executeQuery()){
                if(rst.next()){
                    String adre= rst.getString("adresse");
                    double tv = rst.getDouble("tva");
                    String CN=rst.getString("CNR");
                    String NI=rst.getString("NIF");
                    String doss=rst.getString("dossier");
                    adresse.setText(adre);
                    CNR.setText(CN);
                    NIF.setText(NI);
                    dossier.setText(doss);
                    tva.setValue(tv);
                }else{
                  adresse.setText("");
                    CNR.setText("");
                    NIF.setText("");
                    dossier.setText("");
                    tva.setValue(0.19);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
}
    //************recuperer tout pour faire une livraison*****************
    public void allClientProduit(JTextField T1,JTextField T2,int idclient,int idproduit){
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        String sql = "SELECT produit.unite AS unite_produit, client_produit.prix AS prix_produit " +
             "FROM client_produit " +
              "JOIN client ON client_produit.id_client = client.idclient " +  
             "JOIN produit ON client_produit.id_produit = produit.idproduit " +
             "WHERE client_produit.id_client = ? AND client_produit.id_produit = ?";
        try (PreparedStatement st = cnx.prepareStatement(sql)){
            st.setInt(1, idclient);
            st.setInt(2, idproduit);
            try (ResultSet rst =(ResultSet) st.executeQuery()){
                if(rst.next()){
                    int unite= rst.getInt("unite_produit");
                    double prix = rst.getDouble("prix_produit");
                    String prixFormat=decimalFormat.format(prix).replace(',', '.');
                    T1.setText(unite+"");
                    T2.setText(prixFormat);
                }else{
                    T1.setText("");
                    T2.setText("");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //****************cherche ID produit selon le produit et marque**********
    public int chercheIdProduit(String produit,String marque) throws SQLException{
        int ID=0;
        String sql="SELECT idproduit FROM produit WHERE produit=? AND marque=?";
        try(PreparedStatement st =cnx.prepareStatement(sql)){
            st.setString(1, produit);
            st.setString(2, marque);
            try (ResultSet rs =st.executeQuery()){
                while (rs.next()){
                    ID=rs.getInt("idproduit");
                }
            }
        }
       return ID; 
    }
    //****************cherche ACHAT selon le FOURNISSEUR ET NUM **********
    public void chercheAchatByFournisseurNum(int id, int num, JTable T) throws SQLException {
        String sql = "SELECT * FROM achat WHERE num=? AND id_fournisseur=?";
        DefaultTableModel model = new DefaultTableModel(new Object[]{"N°", "Produit", "Marque", "Unité", "Prix", "Quantité", "Montant", "Date", "ID", "Fournisseur"}, 0);
        try (PreparedStatement st = cnx.prepareStatement(sql)) {
            st.setInt(1, num);
            st.setInt(2, id);
            try (ResultSet rs = st.executeQuery()) {
                model.setRowCount(0);
                while (rs.next()) {
                    Object[] rowData = new Object[]{
                            rs.getInt("num"),
                            rs.getString("produit"),
                            rs.getString("marque"),
                            rs.getInt("unite"),
                            rs.getString("prix"),
                            rs.getInt("quantite"),
                            rs.getString("montant"),
                            rs.getDate("date"),
                            rs.getInt("id_fournisseur"),
                            rs.getString("fournisseur"),
                    };
                    model.addRow(rowData);
                }
            }
        }
        T.setModel(model);
         CenterTableCellRenderer centerRenderer = new CenterTableCellRenderer();
        for (int i = 0; i < T.getColumnCount(); i++) {
            T.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }
    //****************cherche ACHAT selon le CLIENT ET NUM **********
    public void chercheLivrByClientNum(int id, int num, JTable T) throws SQLException {
        String sql = "SELECT * FROM livraison WHERE num=? AND id_client=?";
        DefaultTableModel model = new DefaultTableModel(new Object[]{"N°", "Produit", "Marque", "Unité", "Prix", "Quantité", "Montant", "Date", "ID", "Client"}, 0);
        try (PreparedStatement st = cnx.prepareStatement(sql)) {
            st.setInt(1, num);
            st.setInt(2, id);
            try (ResultSet rs = st.executeQuery()) {
                model.setRowCount(0);
                while (rs.next()) {
                    Object[] rowData = new Object[]{
                            rs.getInt("num"),
                            rs.getString("produit"),
                            rs.getString("marque"),
                            rs.getInt("unite"),
                            rs.getString("prix"),
                            rs.getInt("quantite"),
                            rs.getString("montant"),
                            rs.getDate("date"),
                            rs.getInt("id_client"),
                            rs.getString("client"),
                    };
                    model.addRow(rowData);
                }
            }
        }
        T.setModel(model);
         CenterTableCellRenderer centerRenderer = new CenterTableCellRenderer();
        for (int i = 0; i < T.getColumnCount(); i++) {
            T.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }
     //****************cherche ACHAT selon le FOURNISSEUR ET DATE **********
    public void chercheAchatByFournisseurDate(int id,Date date,JTable T) throws SQLException{
        
        String sql="SELECT * FROM achat WHERE date=? AND id_fournisseur=?";
        DefaultTableModel model = new DefaultTableModel(new Object[]{"N°", "Produit", "Marque", "Unité", "Prix", "Quantité", "Montant", "Date", "ID", "Fournisseur"}, 0);
        try(PreparedStatement st =cnx.prepareStatement(sql)){
            st.setDate(1, (java.sql.Date) date);
            st.setInt(2, id);
            try (ResultSet rs =st.executeQuery()){
                model.setRowCount(0);
                while (rs.next()){
                   Object[] rowData = new Object[]{
                    rs.getInt("num"),
                    rs.getString("produit"),
                    rs.getString("marque"),
                    rs.getInt("unite"),
                    rs.getString("prix"),
                    rs.getInt("quantite"),
                    rs.getString("montant"),
                    rs.getDate("date"),
                    rs.getInt("id_fournisseur"),
                    rs.getString("fournisseur"),
                   
                };
                model.addRow(rowData);
                }
            }
        }
         T.setModel(model);
         CenterTableCellRenderer centerRenderer = new CenterTableCellRenderer();
        for (int i = 0; i < T.getColumnCount(); i++) {
            T.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }
    //****************cherche ACHAT selon le CLIENT ET DATE **********
    public void chercheLivrByClientDate(int id,Date date,JTable T) throws SQLException{
        
        String sql="SELECT * FROM livraison WHERE date=? AND id_client=?";
        DefaultTableModel model = new DefaultTableModel(new Object[]{"N°", "Produit", "Marque", "Unité", "Prix", "Quantité", "Montant", "Date", "ID", "Client"}, 0);
        try(PreparedStatement st =cnx.prepareStatement(sql)){
            st.setDate(1, (java.sql.Date) date);
            st.setInt(2, id);
            try (ResultSet rs =st.executeQuery()){
                model.setRowCount(0);
                while (rs.next()){
                   Object[] rowData = new Object[]{
                    rs.getInt("num"),
                    rs.getString("produit"),
                    rs.getString("marque"),
                    rs.getInt("unite"),
                    rs.getString("prix"),
                    rs.getInt("quantite"),
                    rs.getString("montant"),
                    rs.getDate("date"),
                    rs.getInt("id_client"),
                    rs.getString("client"),
                   
                };
                model.addRow(rowData);
                }
            }
        }
         T.setModel(model);
         CenterTableCellRenderer centerRenderer = new CenterTableCellRenderer();
        for (int i = 0; i < T.getColumnCount(); i++) {
            T.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }
     //****************cherche ACHAT selon le FOURNISSEUR ET DATE et num**********
    public void chercheAchatByFournisseurDateNum(int id,int num,Date date,JTable T) throws SQLException{
        
        String sql="SELECT * FROM achat WHERE num=? AND date=? AND id_fournisseur=?";
        DefaultTableModel model = new DefaultTableModel(new Object[]{"N°", "Produit", "Marque", "Unité", "Prix", "Quantité", "Montant", "Date", "ID", "Fournisseur"}, 0);
        try(PreparedStatement st =cnx.prepareStatement(sql)){
            st.setInt(1, num);
            st.setDate(2, (java.sql.Date) date);
            st.setInt(3, id);
            try (ResultSet rs =st.executeQuery()){
                model.setRowCount(0);
                while (rs.next()){
                   Object[] rowData = new Object[]{
                    rs.getInt("num"),
                    rs.getString("produit"),
                    rs.getString("marque"),
                    rs.getInt("unite"),
                    rs.getString("prix"),
                    rs.getInt("quantite"),
                    rs.getString("montant"),
                    rs.getDate("date"),
                    rs.getInt("id_fournisseur"),
                    rs.getString("fournisseur"),
                   
                };
                model.addRow(rowData);
                }
            }
        }
         T.setModel(model);
        CenterTableCellRenderer centerRenderer = new CenterTableCellRenderer();
        for (int i = 0; i < T.getColumnCount(); i++) {
            T.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }
  
    //****************cherche ACHAT selon le FOURNISSEUR ET DATE et num**********
    public void chercheLivrByClientDateNum(int id,int num,Date date,JTable T) throws SQLException{
        
        String sql="SELECT * FROM livraison WHERE num=? AND date=? AND id_client=?";
        DefaultTableModel model = new DefaultTableModel(new Object[]{"N°", "Produit", "Marque", "Unité", "Prix", "Quantité", "Montant", "Date", "ID", "Client"}, 0);
        try(PreparedStatement st =cnx.prepareStatement(sql)){
            st.setInt(1, num);
            st.setDate(2, (java.sql.Date) date);
            st.setInt(3, id);
            try (ResultSet rs =st.executeQuery()){
                model.setRowCount(0);
                while (rs.next()){
                   Object[] rowData = new Object[]{
                    rs.getInt("num"),
                    rs.getString("produit"),
                    rs.getString("marque"),
                    rs.getInt("unite"),
                    rs.getString("prix"),
                    rs.getInt("quantite"),
                    rs.getString("montant"),
                    rs.getDate("date"),
                    rs.getInt("id_client"),
                    rs.getString("client"),
                   
                };
                model.addRow(rowData);
                }
            }
        }
         T.setModel(model);
        CenterTableCellRenderer centerRenderer = new CenterTableCellRenderer();
        for (int i = 0; i < T.getColumnCount(); i++) {
            T.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }
    private static class CenterTableCellRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component rendererComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            ((JLabel) rendererComponent).setHorizontalAlignment(SwingConstants.CENTER); // Centrer le contenu de la cellule
            return rendererComponent;
        }
    }
    
    //****************cherche ACHAT selon le FOURNISSEUR ET NUM **********
    public void allAchat(JTable T) throws SQLException {
    String sql = "SELECT * FROM achat";
    DefaultTableModel model = new DefaultTableModel(
            new Object[]{"N°", "Produit", "Marque", "Unité", "Prix", "Quantité", "Montant", "Date", "ID", "Fournisseur"}, 0);
    try (PreparedStatement st = cnx.prepareStatement(sql)) {
        try (ResultSet rs = st.executeQuery()) {
            model.setRowCount(0);
            while (rs.next()) {
                Object[] rowData = new Object[]{
                        rs.getInt("num"),
                        rs.getString("produit"),
                        rs.getString("marque"),
                        rs.getInt("unite"),
                        rs.getString("prix"),
                        rs.getInt("quantite"),
                        rs.getString("montant"), // Utilisez directement la valeur de montant comme chaîne
                        rs.getDate("date"),
                        rs.getInt("id_fournisseur"),
                        rs.getString("fournisseur"),
                };
                model.addRow(rowData);
                // Ajoutez des messages de journalisation pour déboguer
                System.out.println("Montant récupéré: " + rs.getString("montant"));
            }
        }
    }
    T.setModel(model);
     CenterTableCellRenderer centerRenderer = new CenterTableCellRenderer();
        for (int i = 0; i < T.getColumnCount(); i++) {
            T.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
}
    //****************cherche ACHAT selon le FOURNISSEUR ET NUM **********
    public void allStock(JTable T) throws SQLException {
        
        String sql = "SELECT * FROM stock";
        DefaultTableModel model = new DefaultTableModel();

        try (PreparedStatement st = cnx.prepareStatement(sql)) {
            try (ResultSet rs = st.executeQuery()) {
                model.setColumnIdentifiers(new Object[]{"Type","Num", "Type de partenaire", "ID", "Nom du partenaire", "Produit", "Quantité", "Date"});
                while (rs.next()) {
                    String typePartenaire = rs.getString("typepartenaire");
                    int id;
                    int num=rs.getInt("num");
                    if (typePartenaire.equals("Client")) {
                        String[] splitClient = rs.getString("nom_partenaire").split(" ");
                        String nom = splitClient[0];
                        String prenom = splitClient[1];
                        
                        id = getClientIdByName(nom,prenom);
                    } else if (typePartenaire.equals("Fournisseur")) {
                        id = getFournisseurIdByName(rs.getString("nom_partenaire"));
                    } else {
                        id = -1; // Valeur par défaut si le type de partenaire est inconnu
                    }

                    Object[] rowData = new Object[]{
                            rs.getString("type"),
                            num,
                            typePartenaire,
                            id,
                            rs.getString("nom_partenaire"),
                            rs.getString("produit"),
                            rs.getInt("quantite"),
                            rs.getDate("date")
                    };
                    model.addRow(rowData);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace(); // Gestion des erreurs SQL
        }
        T.setModel(model); // Mettre à jour le modèle de table avec les données récupérées
}

// Méthode pour récupérer l'ID du client en fonction de son nom
private int getClientIdByName(String nom,String prenom) throws SQLException {
    String sql = "SELECT idclient FROM client WHERE nom = ? AND prenom=?";
    try (PreparedStatement st = cnx.prepareStatement(sql)) {
        st.setString(1, nom);
        st.setString(2, prenom);
        try (ResultSet rs = st.executeQuery()) {
            if (rs.next()) {
                return rs.getInt("idclient");
            } else {
                return -1; // Valeur par défaut si le client n'est pas trouvé
            }
        }
    }
}

// Méthode pour récupérer l'ID du fournisseur en fonction de son nom
private int getFournisseurIdByName(String nom) throws SQLException {
    String sql = "SELECT idfournisseur FROM fournisseur WHERE nom = ?";
    try (PreparedStatement st = cnx.prepareStatement(sql)) {
        st.setString(1, nom);
        try (ResultSet rs = st.executeQuery()) {
            if (rs.next()) {
                return rs.getInt("idfournisseur");
            } else {
                return -1; // Valeur par défaut si le fournisseur n'est pas trouvé
            }
        }
    }
}


    //****************cherche ACHAT selon le FOURNISSEUR ET NUM **********
    public void allLivraison(JTable T) throws SQLException {
    String sql = "SELECT * FROM livraison";
    DefaultTableModel model = new DefaultTableModel(
            new Object[]{"N°", "Produit", "Marque", "Unité", "Prix", "Quantité", "Montant", "Date", "ID", "Client"}, 0);
    try (PreparedStatement st = cnx.prepareStatement(sql)) {
        try (ResultSet rs = st.executeQuery()) {
            model.setRowCount(0);
            while (rs.next()) {
                Object[] rowData = new Object[]{
                        rs.getInt("num"),
                        rs.getString("produit"),
                        rs.getString("marque"),
                        rs.getInt("unite"),
                        rs.getString("prix"),
                        rs.getInt("quantite"),
                        rs.getString("montant"), // Utilisez directement la valeur de montant comme chaîne
                        rs.getDate("date"),
                        rs.getInt("id_client"),
                        rs.getString("client"),
                };
                model.addRow(rowData);
                // Ajoutez des messages de journalisation pour déboguer
                System.out.println("Montant récupéré: " + rs.getString("montant"));
            }
        }
    }
    T.setModel(model);
     CenterTableCellRenderer centerRenderer = new CenterTableCellRenderer();
        for (int i = 0; i < T.getColumnCount(); i++) {
            T.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
}
    //****************cherche ACHAT POUR IMPRESSION selon le FOURNISSEUR ET NUM **********
    public void allLivraisonImpression(JTable T, JLabel T1, String client, int num) throws SQLException {
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    String sql = "SELECT * FROM livraison WHERE client=? AND num=?";
    DefaultTableModel model = new DefaultTableModel(new Object[]{"DESIGNATION", "UNITE", "PRIX/U", "QTE", "MONTANT"}, 0);
    
    try (PreparedStatement st = cnx.prepareStatement(sql)) {
        st.setString(1, client);
        st.setInt(2, num);
        
        try (ResultSet rs = st.executeQuery()) {
            model.setRowCount(0);
            
            
            while (rs.next()) {
                // Convertir la date en format spécifié
                String dateStr = sdf.format(rs.getDate("date"));
                T1.setText(dateStr);

                Object[] rowData = new Object[]{
                    rs.getString("produit") + rs.getString("marque"),
                    rs.getInt("unite"),
                    rs.getString("prix"),
                    rs.getInt("quantite"),
                    rs.getString("montant") // Utilisez directement la valeur de montant comme chaîne
                };

                model.addRow(rowData);
              
            }
           
        }
    }

    T.setModel(model);
    
    // Appliquer le renderer personnalisé pour la couleur de fond
    for (int i = 0; i < T.getColumnCount(); i++) {
        T.getColumnModel().getColumn(i).setCellRenderer(new CustomTableCellRenderer(Color.WHITE));
    }

    // Appliquer le renderer pour centrer le contenu des cellules
    DefaultTableCellRenderer centre = new DefaultTableCellRenderer();
    centre.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
    
    for (int i = 0; i < 5; i++) {
        T.getColumnModel().getColumn(i).setCellRenderer(centre);
    } 
    
    // Appliquer le renderer personnalisé pour l'en-tête de la colonne
    for (int i = 0; i < T.getColumnCount(); i++) {
        TableColumn column = T.getColumnModel().getColumn(i);
        column.setHeaderRenderer(new CustomTableHeaderRenderer(T));
    }
}
     public void allAchatImpression(JTable T, JLabel T1, String fourni, int num) throws SQLException {
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    String sql = "SELECT * FROM achat WHERE fournisseur=? AND num=?";
    DefaultTableModel model = new DefaultTableModel(new Object[]{"DESIGNATION", "UNITE", "PRIX/U", "QTE", "MONTANT"}, 0);
    
    try (PreparedStatement st = cnx.prepareStatement(sql)) {
        st.setString(1, fourni);
        st.setInt(2, num);
        
        try (ResultSet rs = st.executeQuery()) {
            model.setRowCount(0);
            
            
            while (rs.next()) {
                // Convertir la date en format spécifié
                String dateStr = sdf.format(rs.getDate("date"));
                T1.setText(dateStr);

                Object[] rowData = new Object[]{
                    rs.getString("produit") + rs.getString("marque"),
                    rs.getInt("unite"),
                    rs.getString("prix"),
                    rs.getInt("quantite"),
                    rs.getString("montant") // Utilisez directement la valeur de montant comme chaîne
                };

                model.addRow(rowData);
              
            }
           
        }
    }

    T.setModel(model);
    
    // Appliquer le renderer personnalisé pour la couleur de fond
    for (int i = 0; i < T.getColumnCount(); i++) {
        T.getColumnModel().getColumn(i).setCellRenderer(new CustomTableCellRenderer(Color.WHITE));
    }

    // Appliquer le renderer pour centrer le contenu des cellules
    DefaultTableCellRenderer centre = new DefaultTableCellRenderer();
    centre.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
    
    for (int i = 0; i < 5; i++) {
        T.getColumnModel().getColumn(i).setCellRenderer(centre);
    } 
    
    // Appliquer le renderer personnalisé pour l'en-tête de la colonne
    for (int i = 0; i < T.getColumnCount(); i++) {
        TableColumn column = T.getColumnModel().getColumn(i);
        column.setHeaderRenderer(new CustomTableHeaderRenderer(T));
    }
}
public String getAdresseUser(){
          String ADRESSE="";   
         try{
            String sql ="SELECT adresse FROM user ";
            try (PreparedStatement stmt =cnx.prepareStatement(sql);
                 ResultSet rst =stmt.executeQuery()){
                boolean hasresult = false;
                while (rst.next()){
                    hasresult = true;
                    ADRESSE= rst.getString("adresse");
                    
                    
                   
                }
                if(!hasresult){
                    System.out.println("pas de user");
                }
                
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
         
             return ADRESSE;
}
public String getAdresseClient(){
          String ADRESSE="";   
         try{
            String sql ="SELECT adresse FROM client ";
            try (PreparedStatement stmt =cnx.prepareStatement(sql);
                 ResultSet rst =stmt.executeQuery()){
                boolean hasresult = false;
                while (rst.next()){
                    hasresult = true;
                    ADRESSE= rst.getString("adresse");
                    
                    
                   
                }
                if(!hasresult){
                    System.out.println("pas de user");
                }
                
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
         
             return ADRESSE;
}
    public double getTVAuser(){
          double TVA=0.00;   
         try{
            String sql ="SELECT tva FROM user WHERE iduser=1";
            try (PreparedStatement stmt =cnx.prepareStatement(sql);
                 ResultSet rst =stmt.executeQuery()){
                boolean hasresult = false;
                while (rst.next()){
                    hasresult = true;
                    TVA = rst.getDouble("tva");
                   
                
                }
                if(!hasresult){
                    System.out.println("pas de tva");
                }
                
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
         
             return TVA;
}

// Méthode pour créer et afficher un PDF
     public String getCNRandNIFuser(){
          String CNR_NIF="";   
         try{
            String sql ="SELECT CNR,NIF FROM user ";
            try (PreparedStatement stmt =cnx.prepareStatement(sql);
                 ResultSet rst =stmt.executeQuery()){
                boolean hasresult = false;
                while (rst.next()){
                    hasresult = true;
                    String CNR = rst.getString("CNR");
                    String NIF = rst.getString("NIF");
                    
                   CNR_NIF=CNR+" "+NIF;
                }
                if(!hasresult){
                    System.out.println("pas de user");
                }
                
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
         
             return CNR_NIF;
}
      public String getCNRandNIFclient(int id){
          String CNR_NIF="";   
         try{
            String sql ="SELECT CNR,NIF FROM client WHERE idclient=? ";
            try (PreparedStatement st =cnx.prepareStatement(sql)){
                   st.setInt(1, id); 
                 ResultSet rst =st.executeQuery();
                
                
                if (rst.next()){
                  
                    String CNR = rst.getString("CNR");
                    String NIF = rst.getString("NIF");
                    
                   CNR_NIF=CNR+" "+NIF;
                }else {
                System.out.println("Pas de CNR et NIF trouvé pour l'identifiant " + id);
            }
                
                
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
         
             return CNR_NIF;
}
       public String getCNRandNIFfourni(int id){
          String CNR_NIF="";   
         try{
            String sql ="SELECT CNR,NIF FROM fournisseur WHERE idfournisseur=? ";
            try (PreparedStatement st =cnx.prepareStatement(sql)){
                   st.setInt(1, id); 
                 ResultSet rst =st.executeQuery();
                
                
                if (rst.next()){
                  
                    String CNR = rst.getString("CNR");
                    String NIF = rst.getString("NIF");
                    
                   CNR_NIF=CNR+" "+NIF;
                }else {
                System.out.println("Pas de CNR et NIF trouvé pour l'identifiant " + id);
            }
                
                
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
         
             return CNR_NIF;
}
       
       public void createAndShowPDFFourni(String fourni, int num,String adresseuser,String CNRuser,String NIFuser,String CNRfourni,String NIFfourni,double tva) throws SQLException, DocumentException, IOException, PrinterException {
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    String dateStr = null;
    double montant = 0.00;
    String sql = "SELECT * FROM achat WHERE fournisseur=? AND num=?";
   
    // Exécuter la requête SQL pour récupérer les données de la livraison
    try (PreparedStatement st = cnx.prepareStatement(sql)) {
        st.setString(1, fourni);
        st.setInt(2, num);
        
        try (ResultSet rs = st.executeQuery()) {
            Document document = new Document();
            document.setMargins(20, 20, 10, 20); 
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("achat_de_" + fourni + "_N" + num + ".pdf"));
            
            document.open();
            PdfPTable frameTable = new PdfPTable(1);
            frameTable.setWidthPercentage(100);

            PdfPCell frameCell = new PdfPCell();
            frameCell.setBorder(Rectangle.NO_BORDER);        frameCell.setPadding(10); // Ajustez le rembourrage selon vos besoins
            frameCell.setBorderWidth(1f); // Épaisseur de la bordure
            frameCell.setBorderColor(BaseColor.BLACK); // Couleur de la bordure

            // Création du contenu pour la cellule
            Paragraph content = new Paragraph();
            Paragraph content1 = new Paragraph();
            content.add(new Phrase("GHILASS GLACE"));
            content.add(Chunk.NEWLINE);
            content.add(new Phrase("Adresse : " + adresseuser));
            content.add(Chunk.NEWLINE);
             content.add(new Phrase("CNR : " + CNRuser));
             content.add(Chunk.NEWLINE);
             content.add(new Phrase("NIF : " + NIFuser));
             
            content1.add(new Phrase(new Phrase("Fournisseur: " + fourni)));
            content1.add(Chunk.NEWLINE);
            content1.add(new Phrase("CNR : " + CNRfourni));
             content1.add(Chunk.NEWLINE);
             content1.add(new Phrase("NIF : " + NIFfourni));
            content1.setIndentationLeft(320);
           

            frameCell.addElement(content);
            frameCell.addElement(content1);
           
            frameTable.addCell(frameCell);

            document.add(frameTable);
           
            
            // Tableau des produits
            PdfPTable mainTable = new PdfPTable(5); // 5 colonnes pour produit, marque, unité, prix, quantité
            mainTable.setHorizontalAlignment(Element.ALIGN_CENTER);
            mainTable.setWidthPercentage(100);
            addTableHeader(mainTable);
            DecimalFormat decimalFormat = new DecimalFormat("0.00");
            // Ajouter les données de la table
            while (rs.next()) {
                // Récupérer les données de la livraison depuis le ResultSet
                String produit = rs.getString("produit");
                String marque= rs.getString("marque");
                int unite = rs.getInt("unite");
                double prix = rs.getDouble("prix");
                int quantite = rs.getInt("quantite");
                double montantAchat = rs.getDouble("montant");
                
                montant += montantAchat;
                dateStr = sdf.format(rs.getDate("date"));
                String prixFormate = decimalFormat.format(prix);
                 String montantFormate = decimalFormat.format(montantAchat);
                // Ajouter les données de la livraison au tableau principal
               addContentRow(mainTable, produit+" "+marque, unite, prixFormate, quantite, montantFormate);
            }
            
            addLastRow(mainTable, "", "", "", "", "");
            Paragraph paragraph = new Paragraph();

            // Première phrase (centrée)
            Phrase phrase1 = new Phrase("BON : " + num);
            paragraph.add(phrase1);
            paragraph.setAlignment(Element.ALIGN_CENTER); // Centrer le paragraphe

            // Deuxième phrase (alignée à droite)
            Phrase phrase2 = new Phrase("Date : " + dateStr);
            Paragraph rightAlignedParagraph = new Paragraph(phrase2);
            rightAlignedParagraph.setAlignment(Element.ALIGN_RIGHT); // Aligner à droite

            document.add(paragraph);
            document.add(rightAlignedParagraph);


            document.add(new Paragraph("\n"));
            document.add(mainTable);
            
            // Saut de ligne avant le petit tableau
            document.add(new Paragraph("\n"));
        
            // Création du petit tableau
            PdfPTable smallTable = new PdfPTable(2);
            smallTable.setWidthPercentage(30);
            float[] smallTableWidths = {20f, 30f}; // Deux colonnes de largeur égale
            smallTable.setWidths(smallTableWidths);
            smallTable.setHorizontalAlignment(Element.ALIGN_RIGHT);
            // Ajout des cellules dans le petit tableau
            double montantEcart = montant * tva;
    double montantTTC = montant + montantEcart;
            DecimalFormat df = new DecimalFormat("#0.00");
    String montantEcartFormatted = df.format(montantEcart);
    String montantTTCFormatted = df.format(montantTTC);

    // Ajouter les montants au petit tableau
    smallTable.addCell(new PdfPCell(new Paragraph("Total HT:")));
    smallTable.addCell(new PdfPCell(new Paragraph(df.format(montant))));
    smallTable.addCell(new PdfPCell(new Paragraph("Total TTC:")));
    smallTable.addCell(new PdfPCell(new Paragraph(montantTTCFormatted)));
    smallTable.addCell(new PdfPCell(new Paragraph("TVA:")));
    smallTable.addCell(new PdfPCell(new Paragraph(df.format(tva*100))));
    document.setMargins(0, 0, 50, 0); // Marge droite de 50 points

        Paragraph smallTableParagraph = new Paragraph();
        smallTableParagraph.setAlignment(Element.ALIGN_RIGHT);
        smallTableParagraph.add(smallTable);
        document.add(smallTableParagraph);
        
        document.add(new Chunk("\n"));
        document.add(new Chunk("\n"));
        BigDecimal nu = new BigDecimal(montantTTC); 
        RuleBasedNumberFormat formatter = new RuleBasedNumberFormat(RuleBasedNumberFormat.SPELLOUT);
        String integerPart = formatter.format(nu.intValue());
        int decimalPart = (int) ((nu.remainder(BigDecimal.ONE)).doubleValue() * 100); 
        String decimalPartInWords = formatter.format(decimalPart);
        com.itextpdf.text.Font normalFont = FontFactory.getFont(FontFactory.HELVETICA, 12);
        com.itextpdf.text.Font ItalicFont = FontFactory.getFont(FontFactory.HELVETICA, 11, Font.BOLD);

        Paragraph paragraph1 = new Paragraph();
        paragraph1.add(new Chunk("Arrêtée la présente facture ala somme de : ", normalFont));
        paragraph1.add(new Chunk(integerPart + " dinars algerien et  "+decimalPartInWords+" centimes.", ItalicFont));
 
        document.add(paragraph1);  
        document.close();
        printPDF("Achat_de_" + fourni + "_N" + num + ".pdf");
        }
    }
}
public void createAndShowPDF(String client, int num,String adresseuser,String CNRuser,String NIFuser,String CNRclient,String NIFclient,double tva) throws SQLException, DocumentException, IOException, PrinterException {
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    String dateStr = null;
    double montant = 0.00;
    String sql = "SELECT * FROM livraison WHERE client=? AND num=?";
   
    // Exécuter la requête SQL pour récupérer les données de la livraison
    try (PreparedStatement st = cnx.prepareStatement(sql)) {
        st.setString(1, client);
        st.setInt(2, num);
        
        try (ResultSet rs = st.executeQuery()) {
            Document document = new Document();
            document.setMargins(20, 20, 10, 20); 
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("Livraison_de_" + client + "_N" + num + ".pdf"));
            
            document.open();
            PdfPTable frameTable = new PdfPTable(1);
            frameTable.setWidthPercentage(100);

            PdfPCell frameCell = new PdfPCell();
            frameCell.setBorder(Rectangle.NO_BORDER);        frameCell.setPadding(10); // Ajustez le rembourrage selon vos besoins
            frameCell.setBorderWidth(1f); // Épaisseur de la bordure
            frameCell.setBorderColor(BaseColor.BLACK); // Couleur de la bordure

            // Création du contenu pour la cellule
            Paragraph content = new Paragraph();
            Paragraph content1 = new Paragraph();
            content.add(new Phrase("GHILASS GLACE"));
            content.add(Chunk.NEWLINE);
            content.add(new Phrase("Adresse : " + adresseuser));
            content.add(Chunk.NEWLINE);
             content.add(new Phrase("CNR : " + CNRuser));
             content.add(Chunk.NEWLINE);
             content.add(new Phrase("NIF : " + NIFuser));
             
            content1.add(new Phrase(new Phrase("CLlent: " + client)));
            content1.add(Chunk.NEWLINE);
            content1.add(new Phrase("CNR : " + CNRclient));
             content1.add(Chunk.NEWLINE);
             content1.add(new Phrase("NIF : " + NIFclient));
            content1.setIndentationLeft(320);
           

            frameCell.addElement(content);
            frameCell.addElement(content1);
           
            frameTable.addCell(frameCell);

            document.add(frameTable);
           
            
            // Tableau des produits
            PdfPTable mainTable = new PdfPTable(5); // 5 colonnes pour produit, marque, unité, prix, quantité
            mainTable.setHorizontalAlignment(Element.ALIGN_CENTER);
            mainTable.setWidthPercentage(100);
            addTableHeader(mainTable);
            DecimalFormat decimalFormat = new DecimalFormat("0.00");
            // Ajouter les données de la table
            while (rs.next()) {
                // Récupérer les données de la livraison depuis le ResultSet
                String produit = rs.getString("produit");
                String marque= rs.getString("marque");
                int unite = rs.getInt("unite");
                double prix = rs.getDouble("prix");
                int quantite = rs.getInt("quantite");
                double montantLivraison = rs.getDouble("montant");
                
                montant += montantLivraison;
                dateStr = sdf.format(rs.getDate("date"));
                String prixFormate = decimalFormat.format(prix);
                 String montantFormate = decimalFormat.format(montantLivraison);
                // Ajouter les données de la livraison au tableau principal
               addContentRow(mainTable, produit+" "+marque, unite, prixFormate, quantite, montantFormate);
            }
            
            addLastRow(mainTable, "", "", "", "", "");
            Paragraph paragraph = new Paragraph();

            // Première phrase (centrée)
            Phrase phrase1 = new Phrase("BON : " + num);
            paragraph.add(phrase1);
            paragraph.setAlignment(Element.ALIGN_CENTER); // Centrer le paragraphe

            // Deuxième phrase (alignée à droite)
            Phrase phrase2 = new Phrase("Date : " + dateStr);
            Paragraph rightAlignedParagraph = new Paragraph(phrase2);
            rightAlignedParagraph.setAlignment(Element.ALIGN_RIGHT); // Aligner à droite

            document.add(paragraph);
            document.add(rightAlignedParagraph);


            document.add(new Paragraph("\n"));
            document.add(mainTable);
            
            // Saut de ligne avant le petit tableau
            document.add(new Paragraph("\n"));
        
            // Création du petit tableau
            PdfPTable smallTable = new PdfPTable(2);
            smallTable.setWidthPercentage(30);
            float[] smallTableWidths = {20f, 30f}; // Deux colonnes de largeur égale
            smallTable.setWidths(smallTableWidths);
            smallTable.setHorizontalAlignment(Element.ALIGN_RIGHT);
            // Ajout des cellules dans le petit tableau
            double montantEcart = montant * tva;
    double montantTTC = montant + montantEcart;
            DecimalFormat df = new DecimalFormat("#0.00");
    String montantEcartFormatted = df.format(montantEcart);
    String montantTTCFormatted = df.format(montantTTC);

    // Ajouter les montants au petit tableau
    smallTable.addCell(new PdfPCell(new Paragraph("Total HT:")));
    smallTable.addCell(new PdfPCell(new Paragraph(df.format(montant))));
    smallTable.addCell(new PdfPCell(new Paragraph("Total TTC:")));
    smallTable.addCell(new PdfPCell(new Paragraph(montantTTCFormatted)));
    smallTable.addCell(new PdfPCell(new Paragraph("TVA:")));
    smallTable.addCell(new PdfPCell(new Paragraph(df.format(tva*100))));
    document.setMargins(0, 0, 50, 0); // Marge droite de 50 points

        Paragraph smallTableParagraph = new Paragraph();
        smallTableParagraph.setAlignment(Element.ALIGN_RIGHT);
        smallTableParagraph.add(smallTable);
        document.add(smallTableParagraph);
        
        document.add(new Chunk("\n"));
        document.add(new Chunk("\n"));
        BigDecimal nu = new BigDecimal(montantTTC); 
        RuleBasedNumberFormat formatter = new RuleBasedNumberFormat(RuleBasedNumberFormat.SPELLOUT);
        String integerPart = formatter.format(nu.intValue());
        int decimalPart = (int) ((nu.remainder(BigDecimal.ONE)).doubleValue() * 100); 
        String decimalPartInWords = formatter.format(decimalPart);
        com.itextpdf.text.Font normalFont = FontFactory.getFont(FontFactory.HELVETICA, 12);
        com.itextpdf.text.Font ItalicFont = FontFactory.getFont(FontFactory.HELVETICA, 11, Font.BOLD);

        Paragraph paragraph1 = new Paragraph();
        paragraph1.add(new Chunk("Arrêtée la présente facture ala somme de : ", normalFont));
        paragraph1.add(new Chunk(integerPart + " dinars algerien et  "+decimalPartInWords+" centimes.", ItalicFont));
 
        document.add(paragraph1);  
        document.close();
        printPDF("Livraison_de_" + client + "_N" + num + ".pdf");
        }
    }
}
public static String createSpace(int length) {
    StringBuilder spaceBuilder = new StringBuilder();
    for (int i = 0; i < length; i++) {
        spaceBuilder.append(" ");
    }
    return spaceBuilder.toString();
}
 private static void addTableHeader(PdfPTable table) {
        table.addCell("Produit");
        table.addCell("unité");
        table.addCell("prix/U");
        table.addCell("Quantite");
        table.addCell("montant");
      for (int i = 0; i < table.getNumberOfColumns(); i++) {
        PdfPCell cell = table.getRow(0).getCells()[i];
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    }
    }


    private void printPDF(String filename) throws IOException, PrinterException {
    File file = new File(filename);
    if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.OPEN)) {
        Desktop.getDesktop().open(file); // Ouvrir le fichier PDF avec l'application par défaut
    } else {
        System.out.println("L'ouverture du fichier PDF n'est pas prise en charge sur ce système.");
    }
}

  private void addContentRow(PdfPTable table, String produit, int unite, String prix, int quantite, String montantLivraison) {

      PdfPCell cell1 = new PdfPCell(new Phrase(produit));
      cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
    cell1.setBorder(Rectangle.LEFT | Rectangle.RIGHT); // Bordures gauche et droite seulement
    table.addCell(cell1);
    
    PdfPCell cell2 = new PdfPCell(new Phrase(String.valueOf(unite)));
    cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
    cell2.setBorder(Rectangle.LEFT | Rectangle.RIGHT); // Bordures gauche et droite seulement
    table.addCell(cell2);
    
    PdfPCell cell3 = new PdfPCell(new Phrase(String.valueOf(prix)));
    cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
    cell3.setBorder(Rectangle.LEFT | Rectangle.RIGHT); // Bordures gauche et droite seulement
    table.addCell(cell3);
    
    PdfPCell cell4 = new PdfPCell(new Phrase(String.valueOf(quantite)));
    cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
    cell4.setBorder(Rectangle.LEFT | Rectangle.RIGHT); // Bordures gauche et droite seulement
    table.addCell(cell4);
    
    PdfPCell cell5 = new PdfPCell(new Phrase(String.valueOf(montantLivraison)));
    cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
    cell5.setBorder(Rectangle.LEFT | Rectangle.RIGHT); // Bordures gauche et droite seulement
    table.addCell(cell5);
}

// Ajouter la dernière ligne avec des bordures verticales et une bordure inférieure horizontale
private void addLastRow(PdfPTable table, String produit, String unite, String prix, String quantite, String montantLivraison) {
    PdfPCell cell1 = new PdfPCell(new Phrase(produit));
    cell1.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.BOTTOM); // Bordures gauche, droite et bas
    table.addCell(cell1);
    
    PdfPCell cell2 = new PdfPCell(new Phrase(String.valueOf(unite)));
    cell2.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.BOTTOM); // Bordures gauche, droite et bas
    table.addCell(cell2);
    
    PdfPCell cell3 = new PdfPCell(new Phrase(String.valueOf(prix)));
    cell3.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.BOTTOM); // Bordures gauche, droite et bas
    table.addCell(cell3);
    
    PdfPCell cell4 = new PdfPCell(new Phrase(String.valueOf(quantite)));
    cell4.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.BOTTOM); // Bordures gauche, droite et bas
    table.addCell(cell4);
    
    PdfPCell cell5 = new PdfPCell(new Phrase(String.valueOf(montantLivraison)));
    cell5.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.BOTTOM); // Bordures gauche, droite et bas
    table.addCell(cell5);
}  
class CustomTableCellRenderer extends DefaultTableCellRenderer {
    private Color backgroundColor;

    public CustomTableCellRenderer(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        // Définir la couleur de fond des cellules non utilisées sur la même couleur que le fond de la table
        if (row >= table.getRowCount()) {
            c.setBackground(backgroundColor);
        }

        return c;
    }
}
public void createPdfSituation(){
    
}
class CustomTableHeaderRenderer implements TableCellRenderer {
    DefaultTableCellRenderer renderer;

    public CustomTableHeaderRenderer(JTable table) {
        renderer = (DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer();
        renderer.setHorizontalAlignment(JLabel.CENTER);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        JLabel label = (JLabel) renderer.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        
        // Ajoutez des bordures internes à l'en-tête de la colonne
        label.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.BLACK), // Bordure extérieure
            BorderFactory.createEmptyBorder(5, 5, 5, 5) // Marge interne
        ));

        return label;
    }
}
 public void allStockAlerte(JTable T) throws SQLException {
        String sql = "SELECT produit,SUM(CASE WHEN type = 'Entrée' THEN quantite ELSE 0 END) AS quantite_entree,SUM(CASE WHEN type = 'Sortie' THEN quantite ELSE 0 END) AS quantite_sortie FROM stock GROUP BY produit;";
       DefaultTableModel model1 = new DefaultTableModel(new Object[]{"Produit", "Tot/Entree", "Tot/Sortie", "Val/Min", "Alerte"}, 0);
  
        for (int i = 0; i < alertFrames.size(); i++) {
        JFrame alertFrame = alertFrames.get(i);
        // Masquer la frame d'alerte
        alertFrame.setVisible(false);
        // Supprimer la frame d'alerte de l'application
        alertFrames.remove(alertFrame);
        // Décrémenter l'indice pour rester cohérent avec la nouvelle taille de la liste
        i--;
    }
        try (PreparedStatement st = cnx.prepareStatement(sql)) {
            try (ResultSet rs = st.executeQuery()) {
               // model1.setRowCount(0);
                while (rs.next()) {
                    int quantite_entree = rs.getInt("quantite_entree");
                int quantite_sortie = -rs.getInt("quantite_sortie");

                // Calculer le total actuel du stock pour chaque produit
                int total_stock = quantite_entree - quantite_sortie;

                // Valeur minimale prédéfinie pour chaque produit (exemple)
                int valeur_min = 50; // Exemple de valeur minimale
                System.out.println(rs.getString("produit"));
                // Ajouter les valeurs aux colonnes "Val/Min" et "Alerte"
                String alerte = total_stock < valeur_min ? "ALERTE" : "NORMAL";
                Object[] rowData = new Object[]{
                    rs.getString("produit"),
                    quantite_entree,
                    quantite_sortie,
                    valeur_min,
                    alerte
                };
                model1.addRow(rowData);
                 if ("ALERTE".equals(alerte)) {
                    Alert(rs.getString("produit") + " est en alerte!!");
                }
                }
            }
        }
        
        T.setModel(model1);
        T.getColumnModel().getColumn(4).setCellRenderer(new AlertRenderer());

    // Mettre à jour l'affichage de la JTable
    ((DefaultTableModel) T.getModel()).fireTableDataChanged();
  }
    private List<JFrame> alertFrames = new ArrayList<>();

public void Alert(String message) {
    SwingUtilities.invokeLater(() -> {
        JFrame frame = new JFrame("Alerte Stock");
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE); // Modification du comportement de fermeture
        frame.setSize(350, 100); // Ajustement de la taille de la fenêtre

        ImageIcon icon = new ImageIcon("src\\main\\resources\\icon\\icon-alerte.jfif");
        frame.setIconImage(icon.getImage());

        // Positionnement de la fenêtre en bas à droite
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        java.awt.Rectangle screenRect = ge.getMaximumWindowBounds();
        int screenWidth = screenRect.width;
        int screenHeight = screenRect.height;
        int frameWidth = frame.getWidth();
        int frameHeight = frame.getHeight();
        int x = screenWidth - frameWidth;
        int y = screenHeight - frameHeight;
        frame.setLocation(x, y);

        JPanel panel = new JPanel(new GridLayout(2, 1));
        panel.setBackground(Color.BLACK); // Couleur de fond du panneau

        JLabel alertLabel = new JLabel("ALERTE");
        alertLabel.setHorizontalAlignment(SwingConstants.CENTER);
        alertLabel.setForeground(Color.RED);
        alertLabel.setFont(new Font("Arial", Font.BOLD, 20));
        panel.add(alertLabel);

        // Partie inférieure du message (nom du produit + message)
        JLabel productLabel = new JLabel(message );
        productLabel.setHorizontalAlignment(SwingConstants.CENTER);
        productLabel.setForeground(Color.YELLOW);
        productLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        panel.add(productLabel);
        frame.add(panel);

        // Timer pour faire clignoter l'alerte
        Timer timer = new Timer(1000, new ActionListener() {
            boolean visible = false;

            @Override
            public void actionPerformed(ActionEvent e) {
                alertLabel.setVisible(visible);
                visible = !visible;
            }
        });
        timer.start();

        frame.setVisible(true);
        // Amener la fenêtre d'alerte au premier plan
        alertFrames.add(frame);

        // Affichez la nouvelle alerte au-dessus des autres alertes
        positionnerAlertes();
        frame.toFront();
    });
}

private void positionnerAlertes() {
    // Obtenez les dimensions de l'écran
    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        java.awt.Rectangle screenRect = ge.getMaximumWindowBounds();
    int screenWidth = screenRect.width;
    int screenHeight = screenRect.height;

    // Commencez à positionner les alertes à partir du coin bas droit de l'écran
    int y = screenHeight;
    for (int i = alertFrames.size() - 1; i >= 0; i--) {
        JFrame alertFrame = alertFrames.get(i);
        // Positionnez chaque alerte l'une au-dessus de l'autre
        alertFrame.setLocation(screenWidth - alertFrame.getWidth(), y - alertFrame.getHeight());
        y -= alertFrame.getHeight(); // Espacement entre les alertes
        // Rendez chaque alerte visible
        alertFrame.setVisible(true);
    }
}
    //******************ajouter un produit*******************************************
    public void ajout_produit(Produit produit){
        String query = "INSERT INTO produit (idproduit,produit, marque,unite) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = cnx.prepareStatement(query)) {
             statement.setInt(1, produit.getId());
             statement.setString(2, produit.getProduit());
             statement.setString(3, produit.getMarque());
             statement.setInt(4, produit.getUnite());
             
            // Exécuter la requête d'insertion
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Nouveau client inséré avec succès !");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    //******************************remplir la table produit**********************************
    public void allProduit(JTable T){
        try{
            ArrayList<Produit> list = new ArrayList<Produit>();
            st=cnx.createStatement();
            String sql ="SELECT * from produit ORDER BY produit";
            rst=(ResultSet) st.executeQuery(sql);
            
            while(rst.next()){
                Produit produit = new Produit(rst.getInt("idproduit"),rst.getString("produit"),rst.getString("marque"),rst.getInt("unite"));
                list.add(produit);
            }
            DefaultTableModel mo =(DefaultTableModel) T.getModel();
            mo.setRowCount(0);
            Object row[] = new Object[4];
            for (int k=0;k<list.size();k++){
                row[0]=list.get(k).getId();
                row[1]=list.get(k).getProduit();
                row[2]=list.get(k).getMarque();
                row[3]=list.get(k).getUnite();
                
                mo.addRow(row);
            }
            list.clear();
        }catch(SQLException ex){
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE,null,ex);
            JOptionPane.showMessageDialog(null, ex+ "erreur de recuperation de la liste des clients");
        }
    }
    //**************************supprimer un produit**********************************
    public void supprimerProduit(int id) throws SQLException{
        String sql = "DELETE FROM produit WHERE idproduit =?";
        try (PreparedStatement st =cnx.prepareStatement(sql)){
            st.setInt(1, id);
            st.executeUpdate();
        }
    }
    //***************************recuperer le max id du produit************************
     public int getIDMaxProduit(){
        int id=-1;
        try{
            st=cnx.createStatement();
            String query ="SELECT MAX(idproduit) as last_id FROM produit";
            rst=(ResultSet) st.executeQuery(query);
            if(rst.next()){
                id=rst.getInt("last_id");
            }
        }catch(SQLException ex){
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE,null,ex);
            JOptionPane.showMessageDialog(null, ex+ "erreur de recuperation du max id");
        }
        return id;
    }
     
     //*****************modifier un produit****************************************
     public void modifierProduit(int id,Produit produit) throws SQLException{
         try {
             String sql = "UPDATE produit SET idproduit=?,produit=?,marque=?,unite=? WHERE idproduit=?";
             PreparedStatement st =cnx.prepareStatement(sql);
             st.setInt(1, produit.getId());
             st.setString(2, produit.getProduit());
             st.setString(3, produit.getMarque());
             st.setInt(4, produit.getUnite());
             st.setInt(5, id);
              int row =st.executeUpdate();
              if (row>0){
                  System.out.println("update success");
              }else{
                  System.out.println(" NO update success");
              }
         }catch(SQLException ex){
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE,null,ex);
            
        }
     }
     //*****************modifier une achat par num****************************************
     public void modifierAchatByNum(int id,int num,Achat achat) throws SQLException{
         try {
             String sql = "UPDATE achat SET num=? WHERE id_fournisseur=? AND num=? ";
             PreparedStatement st =cnx.prepareStatement(sql);
             st.setInt(1, achat.getNum());
            
             st.setInt(2, id);
             st.setInt(3, num);
             
              int row =st.executeUpdate();
              if (row>0){
                  System.out.println("update success");
              }else{
                  System.out.println(" NO update success");
              }
         }catch(SQLException ex){
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE,null,ex);
            
        }
     }
     public void modifierLivrByNum(int id,int num,Livraison livraison) throws SQLException{
         try {
             String sql = "UPDATE livraison SET num=? WHERE id_client=? AND num=? ";
             PreparedStatement st =cnx.prepareStatement(sql);
             st.setInt(1, livraison.getNum());
            
             st.setInt(2, id);
             st.setInt(3, num);
             
              int row =st.executeUpdate();
              if (row>0){
                  System.out.println("update success");
              }else{
                  System.out.println(" NO update success");
              }
         }catch(SQLException ex){
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE,null,ex);
            
        }
     }
     //*****************modifier une achat par num****************************************
     public void updateUserAdresseDossier(String adresse,String CNR,String NIF,double tva,String dossier) throws SQLException{
         try {
             String sql = "UPDATE user SET adresse=?,CNR=?,NIF=?,tva=?,dossier=? WHERE iduser=1";
             PreparedStatement st =cnx.prepareStatement(sql);
            
            
             st.setString(1, adresse);
             st.setString(2, CNR);
             st.setString(3, NIF);
             st.setDouble(4, tva);
             st.setString(5, dossier);
              int row =st.executeUpdate();
              if (row>0){
                  System.out.println("update success");
              }else{
                  System.out.println(" NO update success");
              }
         }catch(SQLException ex){
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE,null,ex);
            
        }
     }
     //*****************modifier une achat par date****************************************
     public void modifierAchatByDate(int id,int num,Achat achat) throws SQLException{
         try {
             String sql = "UPDATE achat SET date=? WHERE id_fournisseur=? AND num=? ";
             PreparedStatement st =cnx.prepareStatement(sql);
             
             st.setDate(1, (java.sql.Date) achat.getDate());
             
             st.setInt(2, id);
             st.setInt(3, num);
             
              int row =st.executeUpdate();
              if (row>0){
                  System.out.println("update success");
              }else{
                  System.out.println(" NO update success");
              }
         }catch(SQLException ex){
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE,null,ex);
            
        }
     }
     //*****************modifier une achat par date****************************************
     public void modifierLivraisonByDate(int id,int num,Livraison livraison) throws SQLException{
         try {
            // SQL UPDATE query to update the 'date' column in 'livraison' table
            String sql = "UPDATE livraison SET date=? WHERE id_client=? AND num=?";
            PreparedStatement st = cnx.prepareStatement(sql); // Creating a prepared statement

            // Creating a java.sql.Date object from the java.util.Date object
            java.util.Date utilDate = livraison.getDate();
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

            // Setting the parameters for the PreparedStatement
            st.setDate(1, sqlDate); // Setting the date value
            st.setInt(2, id); // Setting the id_client parameter
            st.setInt(3, num); // Setting the num parameter

            // Executing the update query
            int row = st.executeUpdate();

            // Checking if any rows were updated
            if (row > 0) {
                System.out.println("Update success");
            } else {
                System.out.println("No update success");
            }
        } catch (SQLException ex) {
            // Handling any SQL exceptions that might occur
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
     //*****************modifier une achat par quantite****************************************
     public void modifierAchatByQTE(int id,int num,Achat achat) throws SQLException{
         try {
             String sql = "UPDATE achat SET quantite=?,montant=? WHERE id_fournisseur=? AND num=? AND produit=? AND marque=?";
             PreparedStatement st =cnx.prepareStatement(sql);
             
             st.setInt(1, achat.getQuantite());
             st.setDouble(2, achat.getMontant());
             st.setInt(3, id);
             st.setInt(4, num);
             st.setString(5, achat.getProduit());
             st.setString(6, achat.getMarque());
              int row =st.executeUpdate();
              if (row>0){
                  System.out.println("update success");
              }else{
                  System.out.println(" NO update success");
              }
         }catch(SQLException ex){
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE,null,ex);
            
        }
     }
     //*****************modifier une achat par quantite****************************************
     public void modifierLivraisonByQTE(int id,int num,Livraison livraison) throws SQLException{
         try {
             String sql = "UPDATE livraison SET quantite=?,montant=? WHERE id_client=? AND num=? AND produit=? AND marque=?";
             PreparedStatement st =cnx.prepareStatement(sql);
             
             st.setInt(1, livraison.getQuantite());
             st.setDouble(2, livraison.getMontant());
             st.setInt(3, id);
             st.setInt(4, num);
             st.setString(5, livraison.getProduit());
             st.setString(6, livraison.getMarque());
              int row =st.executeUpdate();
              if (row>0){
                  System.out.println("update success");
              }else{
                  System.out.println(" NO update success");
              }
         }catch(SQLException ex){
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE,null,ex);
            
        }
     }
      //*********************remplir le combobox produit********************************
    public void getAllProduit(JComboBox combo) throws SQLException{
        try{
            String sql ="SELECT produit,marque FROM produit ORDER BY produit,marque";
            try (PreparedStatement stmt =cnx.prepareStatement(sql);
                 ResultSet rst =stmt.executeQuery()){
                boolean hasresult = false;
                while (rst.next()){
                    hasresult = true;
                    String produit = rst.getString("produit");
                    String marque = rst.getString("marque");
                    combo.addItem(produit + " " + marque);
                }
                if(!hasresult){
                    System.out.println("pas de produit");
                }
                
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    
    //********************verifier si un produit existe deja********************************
    public boolean produitExist(String produit,String marque){
        boolean exists=false;
        try{
            String sql="SELECT COUNT(*) FROM produit WHERE produit=? AND marque=?";
            try(PreparedStatement statement =cnx.prepareStatement(sql)){
                statement.setString(1, produit);
                statement.setString(2,marque);
                try(ResultSet resultset =statement.executeQuery()){
                    if(resultset.next()){
                        int count=resultset.getInt(1);
                        exists=count>0;
                    }
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return exists;
    }
    //***************************modifier prix d'un produit**************************
    public void modifierPrix(double prix,int idclient,int idproduit){
        try {
             String sql = "UPDATE client_produit SET prix=? WHERE id_client=? AND id_produit=?";
             PreparedStatement st =cnx.prepareStatement(sql);
             st.setDouble(1, prix);
             st.setInt(2, idclient);
             st.setInt(3, idproduit);
           
              int row =st.executeUpdate();
              if (row>0){
                  System.out.println("update success");
              }else{
                  System.out.println(" NO update success");
              }
         }catch(SQLException ex){
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE,null,ex);
            
        }
    }
    
    //***************************modifier prix d'un produit**************************
    public void modifierPrixFournisseur(double prix,int idfourni,int idproduit){
        try {
             String sql = "UPDATE fournisseur_produit SET prix=? WHERE id_fournisseur=? AND id_produit=?";
             PreparedStatement st =cnx.prepareStatement(sql);
             st.setDouble(1, prix);
             st.setInt(2, idfourni);
             st.setInt(3, idproduit);
           
              int row =st.executeUpdate();
              if (row>0){
                  System.out.println("update success");
              }else{
                  System.out.println(" NO update success");
              }
         }catch(SQLException ex){
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE,null,ex);
            
        }
    }
    //************************verifier si un produit pour un client a un prix******************
    public boolean ClientProduitExist(int idclient,int idproduit) throws SQLException{
        boolean exist =false;
        try{
            String sql="SELECT COUNT(*) FROM client_produit WHERE id_client =? AND id_produit=?";
            try (PreparedStatement st =cnx.prepareStatement(sql)){
                st.setInt(1, idclient);
                st.setInt(2, idproduit);
                try(ResultSet rst = st.executeQuery()){
                    if(rst.next()){
                        int count =rst.getInt(1);
                        exist=count>0;
                    }
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return exist;
    }
   
    //*****************ajout prix du produit pour un client*****************
    public void ajouterPrix(int idclient, int idproduit, double prix) {
    try {
        String sql = "INSERT INTO client_produit (id_client, id_produit, prix) VALUES (?, ?, ?)";
        try (PreparedStatement ps = cnx.prepareStatement(sql)) {
            ps.setInt(1, idclient);
            ps.setInt(2, idproduit);
            ps.setDouble(3, prix);
            ps.executeUpdate();
        }
    } catch (SQLException e) {
        Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, "Erreur lors de l'ajout de prix", e);
        JOptionPane.showMessageDialog(null, "Erreur lors de l'ajout de prix : " + e.getMessage());
    }
}
    
    //******remplir table prix client********************
    public void allClientProduitTable(JTable T) throws SQLException{
        try{
            ArrayList<ClientPrix> list = new ArrayList<ClientPrix>();
            Statement st =cnx.createStatement();
            String sql = "SELECT produit.produit AS produit, produit.unite AS unite_produit, produit.marque AS marque_produit, " +
                        "client_produit.prix AS prix_produit, client.nom AS nom_client, client.prenom AS prenom_client " +
                        "FROM client_produit " +
                        "JOIN client ON client_produit.id_client = client.idclient " +
                        "JOIN produit ON client_produit.id_produit = produit.idproduit";
            ResultSet rst= (ResultSet) st.executeQuery(sql);
            while(rst.next()){
                ClientPrix clientprix = new ClientPrix(rst.getString("produit"),rst.getString("marque_produit"),rst.getInt("unite_produit"),rst.getDouble("prix_produit"),rst.getString("nom_client")+" "+ rst.getString("prenom_client"));
                list.add(clientprix);
            }
            DefaultTableModel mo = (DefaultTableModel) T.getModel();
            mo.setRowCount(0);
            Object row[] = new Object[5];
            for (int k=0;k<list.size();k++){
                row[0] = list.get(k).getProduit();
                row[1] = list.get(k).getMarque();
                row[2] = list.get(k).getUnite();
                row[3] = list.get(k).getPrix();
                row[4] = list.get(k).getClient();
                mo.addRow(row);
            }
            list.clear();
            
            
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    //********************verifier si une livraison existe deja*****************************
    public boolean livraisonExist(int num,String client) throws SQLException{
        boolean exist = false;
        try{
            String sql = "SELECT COUNT(*) FROM livraison WHERE num=? AND client = ?";
            try (PreparedStatement st =cnx.prepareStatement(sql)){
                st.setInt(1, num);
                st.setString(2, client);
                try (ResultSet rst =st.executeQuery()){
                    if (rst.next()){
                        int count =rst.getInt(1);
                        exist=count > 0;
                    }
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return exist;
    }
    
    //******************ajouter un elivraison****************
    public void ajouterLivraison(JTable T,int id) throws SQLException{
        DefaultTableModel mo = (DefaultTableModel) T.getModel();
        int rowcount = mo.getRowCount();
        String sql = "INSERT INTO livraison (num,produit,marque,unite,prix,quantite,montant,date,id_client,client) VALUES (?,?,?,?,?,?,?,?,?,?)";
        try (PreparedStatement st =cnx.prepareStatement(sql)){
            for (int i=0;i<rowcount;i++){
                Object num =mo.getValueAt(i, 0);
                Object client =mo.getValueAt(i, 1);
                Object produit =mo.getValueAt(i, 2);
                Object marque =mo.getValueAt(i, 3);
                Object unite =mo.getValueAt(i, 4);
                Object prix =mo.getValueAt(i, 5);
                Object qte =mo.getValueAt(i, 6);
                Object montant =mo.getValueAt(i, 7);
                Object date =mo.getValueAt(i, 8);
                
                st.setObject(1, num);
                st.setObject(2, produit);
                st.setObject(3, marque);
                st.setObject(4, unite);
                st.setObject(5, prix);
                st.setObject(6, qte);
                
                st.setObject(7, montant);
                st.setObject(8, date);
                st.setObject(9, id);
                st.setObject(10, client);
                
                st.executeUpdate();
                
            }
            mo.setRowCount(0);
        }
    }
    public int getNUMMAXLivraison(String client) {
    int num = -1;
    try {
        String query = "SELECT MAX(num) as last_num FROM livraison WHERE client=?";
        try (PreparedStatement st = cnx.prepareStatement(query)) {
            st.setString(1, client);
            try (ResultSet rst = st.executeQuery()) {
                if (rst.next()) {
                    num = rst.getInt("last_num");
                }
            }
        }
    } catch (SQLException ex) {
        Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        JOptionPane.showMessageDialog(null, ex + "erreur de recuperation du max id");
    }
    return num;
}
    public int getNUMMAXVERSEMENTF(String fourni) {
    int num = -1;
    try {
        String query = "SELECT MAX(num) as last_num FROM versementf WHERE fournisseur=?";
        try (PreparedStatement st = cnx.prepareStatement(query)) {
            st.setString(1, fourni);
            try (ResultSet rst = st.executeQuery()) {
                if (rst.next()) {
                    num = rst.getInt("last_num");
                }
            }
        }
    } catch (SQLException ex) {
        Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        JOptionPane.showMessageDialog(null, ex + "erreur de recuperation du max id");
    }
    return num;
}
    public void getSituation(JTable T){
        
        String query = "SELECT \n" +
"    client,"+                
"    num AS num_livraison, \n" +
"    montant AS montant_livraison, \n" +
"    datesortie AS date_livraison,\n" +
"    NULL AS num_versement,\n" +
"    NULL AS montant_versement,\n" +
"    NULL AS date_versement\n" +
"FROM \n" +
"    sortieclient\n" +
"\n" +
"UNION ALL\n" +
"\n" +
"SELECT \n" +
"    client,"+                
"    NULL AS num_livraison, \n" +
"    NULL AS montant_livraison, \n" +
"    NULL AS date_livraison,\n" +
"    num AS num_versement,\n" +
"    versement AS montant_versement,\n" +
"    date AS date_versement\n" +
"FROM \n" +
"    versement\n" +
"\n" +
"ORDER BY \n" +
"    CASE \n" +
"        WHEN date_livraison IS NOT NULL THEN date_livraison\n" +
"        ELSE date_versement \n" +
"    END";
         try{
            Statement statement = cnx.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            // Création du modèle de table
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Client");
            model.addColumn("N° Livraison");
            model.addColumn("Montant");
            model.addColumn("Date");
            model.addColumn("N° Versement");
            model.addColumn("Versement");
            model.addColumn("Date");
            // Remplissage du modèle de table avec les données de la base de données
            while (resultSet.next()) {
                Vector row = new Vector();
                row.add(resultSet.getString("client"));
                row.add(resultSet.getString("num_livraison"));
                row.add(resultSet.getString("montant_livraison"));
                row.add(resultSet.getString("date_livraison"));
                row.add(resultSet.getString("num_versement"));
                row.add(resultSet.getString("montant_versement"));
                row.add(resultSet.getString("date_versement"));
                model.addRow(row);
            }
            T.setModel(model);
         }catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void getSituationByClient(JTable T, String client1, String client2) {
    String query = "SELECT " +
                   "    client," +
                   "    num AS num_livraison, " +
                   "    montant AS montant_livraison, " +
                   "    datesortie AS date_livraison, " +
                   "    NULL AS num_versement, " +
                   "    NULL AS montant_versement, " +
                   "    NULL AS date_versement " +
                   "FROM " +
                   "    sortieclient " +
                   "WHERE " +
                   "    client = ? " +
                   "UNION ALL " +
                   "SELECT " +
                   "    client, " +
                   "    NULL AS num_livraison, " +
                   "    NULL AS montant_livraison, " +
                   "    NULL AS date_livraison, " +
                   "    num AS num_versement, " +
                   "    versement AS montant_versement, " +
                   "    date AS date_versement "+
                   "FROM "+
                   "    versement " +
                   "WHERE "+
                   "    client = ? "  +
                   "ORDER BY " +
                   "    CASE "+
                   "        WHEN date_livraison IS NOT NULL THEN date_livraison " +
                   "        ELSE date_versement "+
                   "    END";

    try {
        PreparedStatement statement = cnx.prepareStatement(query);
        statement.setString(1, client1); // Définir la valeur pour le premier paramètre
        statement.setString(2, client2); // Définir la valeur pour le deuxième paramètre
        ResultSet resultSet = statement.executeQuery();

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Client");
        model.addColumn("N° Livraison");
        model.addColumn("Montant");
        model.addColumn("Date");
        model.addColumn("N° Versement");
        model.addColumn("Versement");
        model.addColumn("Date");

        while (resultSet.next()) {
            Vector row = new Vector();
            row.add(resultSet.getString("client"));
            row.add(resultSet.getString("num_livraison"));
            row.add(resultSet.getString("montant_livraison"));
            row.add(resultSet.getString("date_livraison"));
            row.add(resultSet.getString("num_versement"));
            row.add(resultSet.getString("montant_versement"));
            row.add(resultSet.getString("date_versement"));
            model.addRow(row);
        }
        T.setModel(model);
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
    //*********************recuperer le dernier numero de la livraison
 public int getNUMMAXVERSEMENT(String client) {
    int num = -1;
    try {
        String query = "SELECT MAX(num) as last_num FROM versement WHERE client=?";
        try (PreparedStatement st = cnx.prepareStatement(query)) {
            st.setString(1, client);
            try (ResultSet rst = st.executeQuery()) {
                if (rst.next()) {
                    num = rst.getInt("last_num");
                }
            }
        }
    } catch (SQLException ex) {
        Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        JOptionPane.showMessageDialog(null, ex + "erreur de recuperation du max id");
    }
    return num;
}
    
    //******************ajouter un versment pour un client*******************
    public void ajout_versement_client(VersementClient versement){
       String query = "INSERT INTO versement (id_client,client ,num,date,versement) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = cnx.prepareStatement(query)) {
             statement.setInt(1, versement.getId());
             statement.setString(2, versement.getClient());
             statement.setInt(3, versement.getNum());
             statement.setDate(4, versement.getDate());
             statement.setDouble(5, versement.getMontant());
             
            // Exécuter la requête d'insertion
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Nouveau versement pour client inséré avec succès !");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //******************afficher les versement d un client*******************
     public void allClientVersement(JTable T) {
    try {
        ArrayList<VersementClient> list = new ArrayList<VersementClient>();
        st = cnx.createStatement();
        String sql = "SELECT * from versement ORDER BY client";
        rst = (ResultSet) st.executeQuery(sql);
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        while (rst.next()) {
            VersementClient versement = new VersementClient(
                rst.getInt("id_client"),
                rst.getString("client"),
                rst.getInt("num"),
                rst.getDate("date"),
                Double.parseDouble(rst.getString("versement").replace(',', '.'))
            );
            list.add(versement);
        }

        DefaultTableModel mo = (DefaultTableModel) T.getModel();
        mo.setRowCount(0);
        
        for (VersementClient vc : list) {
            Object[] row = new Object[5]; // Create a new instance of row for each VersementClient
            row[0] = vc.getNum();
            row[1] = vc.getId();
            row[2] = vc.getClient();
            row[3] = vc.getDate();
            row[4] = vc.getMontant();
            mo.addRow(row);
        }
        list.clear();
    } catch (SQLException ex) {
        Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        JOptionPane.showMessageDialog(null, ex + "erreur de recuperation de la liste des clients");
    }
}
    //********************recuperer id produit de where idclient*********************
    private void remplirComboBoxProduits(List<String> produits,JComboBox combo) {
        // Effacer tous les éléments existants dans la JComboBox
        combo.removeAllItems();
        // Ajouter les nouveaux éléments à partir de la liste produits
        for (String produit : produits) {
            combo.addItem(produit);
        }
    }
  public void getIdProduitPrix(int id, JComboBox combo) {
    try {
        String sql = "SELECT id_produit FROM client_produit WHERE id_client=?";
        try (PreparedStatement st = cnx.prepareStatement(sql)) {
            st.setInt(1, id);
            try (ResultSet rst = st.executeQuery()) {
                List<String> produits = new ArrayList<>();
                while (rst.next()) {
                    int produitId = rst.getInt("id_produit");
                    String designationEtMarque = recupererDesignationEtMarque(produitId);
                    if (designationEtMarque != null) {
                        produits.add(designationEtMarque);
                    }
                }
                remplirComboBoxProduits(produits, combo);
            }
        }
    } catch (SQLException ex) {
        Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        JOptionPane.showMessageDialog(null, ex + "erreur de récupération de la liste des produits");
    }
}
    private String recupererDesignationEtMarque(int produitId) throws SQLException {
        String sql = "SELECT produit, marque FROM produit WHERE idproduit=?";
        try (PreparedStatement st = cnx.prepareStatement(sql)) {
            st.setInt(1, produitId);
            try (ResultSet rst = st.executeQuery()) {
                if (rst.next()) {
                    String designation = rst.getString("produit");
                    String marque = rst.getString("marque");
                    return designation + " " + marque;
                }
            }
        }
        return null;
    }

//***************modifier un versement pour un client***************
public void modifierVersementClient(int idclient,int num,Date date,double prix){
    try {
             String sql = "UPDATE versement SET date=? ,versement=? WHERE id_client=? AND num=?";
             PreparedStatement st =cnx.prepareStatement(sql);
             java.sql.Date sqlDate = new java.sql.Date(date.getTime());
             st.setDate(1, sqlDate);
             st.setDouble(2, prix);
             st.setInt(3, idclient);
             st.setInt(4, num);
           
              int row =st.executeUpdate();
              if (row>0){
                  System.out.println("update success");
              }else{
                  System.out.println(" NO update success");
              }
         }catch(SQLException ex){
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE,null,ex);
            
        }
}    

//***************modifier un versement pour un fournisseur***************
public void modifierVersementFournisseur(int idfourni,int num,Date date,double prix){
    try {
             String sql = "UPDATE versementf SET date=? ,versement=? WHERE id_fournisseur=? AND num=?";
             PreparedStatement st =cnx.prepareStatement(sql);
             java.sql.Date sqlDate = new java.sql.Date(date.getTime());
             st.setDate(1, sqlDate);
             st.setDouble(2, prix);
             st.setInt(3, idfourni);
             st.setInt(4, num);
           
              int row =st.executeUpdate();
              if (row>0){
                  System.out.println("update success");
              }else{
                  System.out.println(" NO update success");
              }
         }catch(SQLException ex){
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE,null,ex);
            
        }
}    

//***************recuperer le dernier numero du versement du client***************
public int getNumVersementClient(){
  int num=-1;
  try{
     st=cnx.createStatement();
     String query ="SELECT MAX(num) as last_num FROM versement";
     rst=(ResultSet) st.executeQuery(query);
     if(rst.next()){
        num=rst.getInt("last_num");
     }
  }catch(SQLException ex){
     Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE,null,ex);
     JOptionPane.showMessageDialog(null, ex+ "erreur de recuperation du max id");
  }
  return num;  
}

//***************recuperer le dernier numero du versement du fournisseur***************
public int getNumVersementFournisseur(){
  int num=-1;
  try{
     st=cnx.createStatement();
     String query ="SELECT MAX(num) as last_num FROM versementf";
     rst=(ResultSet) st.executeQuery(query);
     if(rst.next()){
        num=rst.getInt("last_num");
     }
  }catch(SQLException ex){
     Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE,null,ex);
     JOptionPane.showMessageDialog(null, ex+ "erreur de recuperation du max id");
  }
  return num;  
}
   
//***********supprimer versement client*****************
public void supprimerVersementClient(int num,int idclient) throws SQLException{
    String sql = "DELETE FROM versement WHERE num =? AND id_client=?";
    try (PreparedStatement st =cnx.prepareStatement(sql)){
        st.setInt(1, num);
        st.setInt(2, idclient);
        st.executeUpdate();
    }
}

//***********supprimer versement fournisseur*****************
public void supprimerVersementFournisseur(int num,int idfourni) throws SQLException{
    String sql = "DELETE FROM versementf WHERE num =? AND id_fournisseur=?";
    try (PreparedStatement st =cnx.prepareStatement(sql)){
        st.setInt(1, num);
        st.setInt(2, idfourni);
        st.executeUpdate();
    }
}
//********************verifier si un fournisseur existe deja********************************
    public boolean fournisseurExist(String fourni){
        boolean exists=false;
        try{
            String sql="SELECT COUNT(*) FROM fournisseur WHERE nom=? ";
            try(PreparedStatement statement =cnx.prepareStatement(sql)){
                statement.setString(1, fourni);
               
                try(ResultSet resultset =statement.executeQuery()){
                    if(resultset.next()){
                        int count=resultset.getInt(1);
                        exists=count>0;
                    }
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return exists;
    }
    //****************************ajouter un fournisseur***********************************
    public void ajout_fournisseur(Fournisseur fourni){
        String query = "INSERT INTO fournisseur (idfournisseur,nom,adresse, email, telephone,CNR,NIF) VALUES (?, ?, ?, ?, ?,?,?)";
        try (PreparedStatement statement = cnx.prepareStatement(query)) {
             statement.setInt(1, fourni.getId());
             statement.setString(2, fourni.getNom());
             
             statement.setString(3, fourni.getAdresse());
             statement.setString(4, fourni.getEmail());
             statement.setString(5, fourni.getTelephone());
             statement.setString(6, fourni.getCNR());
             statement.setString(7, fourni.getNIF());
            // Exécuter la requête d'insertion
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Nouveau fournisseur inséré avec succès !");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //*********************remplir le combobox client********************************
    public void getAllFournisseur(JComboBox combo) throws SQLException{
        try{
            String sql ="SELECT nom FROM fournisseur ORDER BY nom";
            try (PreparedStatement stmt =cnx.prepareStatement(sql);
                 ResultSet rst =stmt.executeQuery()){
                boolean hasresult = false;
                while (rst.next()){
                    hasresult = true;
                    String nom = rst.getString("nom");
                    
                    combo.addItem(nom );
                }
                if(!hasresult){
                    System.out.println("pas de fournisseur");
                }
                
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    //**************************remplir la table fournisseur**************************************
    public void allFournisseur(JTable T){
        try{
            ArrayList<Fournisseur> list = new ArrayList<Fournisseur>();
            st=cnx.createStatement();
            String sql ="SELECT * from fournisseur ORDER BY nom";
            rst=(ResultSet) st.executeQuery(sql);
            
            while(rst.next()){
                Fournisseur fourni = new Fournisseur(rst.getInt("idfournisseur"),rst.getString("nom"),rst.getString("adresse"),rst.getString("email"),rst.getString("telephone"),rst.getString("CNR"),rst.getString("NIF"));
                list.add(fourni);
            }
            DefaultTableModel mo =(DefaultTableModel) T.getModel();
            mo.setRowCount(0);
            Object row[] = new Object[7];
            for (int k=0;k<list.size();k++){
                row[0]=list.get(k).getId();
                row[1]=list.get(k).getNom();
               
                row[2]=list.get(k).getAdresse();
                row[3]=list.get(k).getEmail();
                row[4]=list.get(k).getTelephone();
                row[5]=list.get(k).getCNR();
                row[6]=list.get(k).getNIF();
                mo.addRow(row);
            }
            list.clear();
        }catch(SQLException ex){
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE,null,ex);
            JOptionPane.showMessageDialog(null, ex+ "erreur de recuperation de la liste des fournisseur");
        }
    }
    //********************recuperer le dernier identifiant du fournisseur***********************
    public int getIDMaxFournisseur(){
        int id=-1;
        try{
            st=cnx.createStatement();
            String query ="SELECT MAX(idfournisseur) as last_id FROM fournisseur";
            rst=(ResultSet) st.executeQuery(query);
            if(rst.next()){
                id=rst.getInt("last_id");
            }
        }catch(SQLException ex){
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE,null,ex);
            JOptionPane.showMessageDialog(null, ex+ "erreur de recuperation du max id");
        }
        return id;
    }
    //********************chercher ID du fournisseur selon le nom ******************
    public int chercheIdFournisseur(String nom) throws SQLException{
        int ID=0;
        String sql="SELECT idfournisseur FROM fournisseur WHERE nom=? ";
        try(PreparedStatement st =cnx.prepareStatement(sql)){
            st.setString(1, nom);
           
            try (ResultSet rs =st.executeQuery()){
                while (rs.next()){
                    ID=rs.getInt("idfournisseur");
                }
            }
        }
       return ID; 
    }
    //********************verifier si une achat existe deja*****************************
    public boolean achatExist(int num,String fourni) throws SQLException{
        boolean exist = false;
        try{
            String sql = "SELECT COUNT(*) FROM achat WHERE num=? AND fournisseur = ?";
            try (PreparedStatement st =cnx.prepareStatement(sql)){
                st.setInt(1, num);
                st.setString(2, fourni);
                try (ResultSet rst =st.executeQuery()){
                    if (rst.next()){
                        int count =rst.getInt(1);
                        exist=count > 0;
                    }
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return exist;
    }
    //******************ajouter une achat****************
    public void ajouterAchat(JTable T,int id) throws SQLException{
        DefaultTableModel mo = (DefaultTableModel) T.getModel();
        int rowcount = mo.getRowCount();
        String sql = "INSERT INTO achat (num, produit, marque, unite, prix, quantite, montant, date, id_fournisseur, fournisseur) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement st =cnx.prepareStatement(sql)){
            for (int i=0;i<rowcount;i++){
                Object num =mo.getValueAt(i, 0);
                Object fourni =mo.getValueAt(i, 1);
                Object produit =mo.getValueAt(i, 2);
                Object marque =mo.getValueAt(i, 3);
                Object unite =mo.getValueAt(i, 4);
                Object prix =mo.getValueAt(i, 5);
                Object qte =mo.getValueAt(i, 6);
                Object montant =mo.getValueAt(i, 7);
                Object date =mo.getValueAt(i, 8);
                
                st.setObject(1, num);
                st.setObject(2, produit);
                st.setObject(3, marque);
                st.setObject(4, unite);
                st.setObject(5, prix);
                st.setObject(6, qte);
                
                st.setObject(7, montant);
                st.setObject(8, date);
                st.setObject(9, id);
                st.setObject(10, fourni);
                
                st.executeUpdate();
                
            }
            mo.setRowCount(0);
        }
    }
    //*********************recuperer le dernier numero de la'achat
  public int getNUMMAXAchat(String fournisseur) {
    int num = -1;
    try {
        String query = "SELECT MAX(num) as last_num FROM achat WHERE fournisseur=?";
        try (PreparedStatement st = cnx.prepareStatement(query)) {
            st.setString(1, fournisseur);
            try (ResultSet rst = st.executeQuery()) {
                if (rst.next()) {
                    num = rst.getInt("last_num");
                }
            }
        }
    } catch (SQLException ex) {
        Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        JOptionPane.showMessageDialog(null, ex + "erreur de recuperation du max id");
    }
    return num;
}
    //************************verifier si un produit pour un fournisseur a un prix******************
    public boolean FournisseurProduitExist(int idfourni,int idproduit) throws SQLException{
        boolean exist =false;
        try{
            String sql="SELECT COUNT(*) FROM fournisseur_produit WHERE id_fournisseur =? AND id_produit=?";
            try (PreparedStatement st =cnx.prepareStatement(sql)){
                st.setInt(1, idfourni);
                st.setInt(2, idproduit);
                try(ResultSet rst = st.executeQuery()){
                    if(rst.next()){
                        int count =rst.getInt(1);
                        exist=count>0;
                    }
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return exist;
    }
     //*****************ajout prix du produit pour un fournisseur*****************
    public void ajouterPrixF(int idfourni, int idproduit, double prix) {
    try {
        String sql = "INSERT INTO fournisseur_produit (id_fournisseur, id_produit, prix) VALUES (?, ?, ?)";
        try (PreparedStatement ps = cnx.prepareStatement(sql)) {
            ps.setInt(1, idfourni);
            ps.setInt(2, idproduit);
            ps.setDouble(3, prix);
            ps.executeUpdate();
        }
    } catch (SQLException e) {
        Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, "Erreur lors de l'ajout de prix", e);
        JOptionPane.showMessageDialog(null, "Erreur lors de l'ajout de prix : " + e.getMessage());
    }
}
    //******remplir table prix fournisseur********************
    public void allFournisseurProduitTable(JTable T) throws SQLException{
        try{
            ArrayList<FournisseurPrix> list = new ArrayList<FournisseurPrix>();
            Statement st =cnx.createStatement();
            String sql = "SELECT produit.produit AS produit, produit.unite AS unite_produit, produit.marque AS marque_produit, " +
                        "fournisseur_produit.prix AS prix_produit, fournisseur.nom AS nom_fournisseur " +
                        "FROM fournisseur_produit " +
                        "JOIN fournisseur ON fournisseur_produit.id_fournisseur = fournisseur.idfournisseur " +
                        "JOIN produit ON fournisseur_produit.id_produit = produit.idproduit";
            ResultSet rst= (ResultSet) st.executeQuery(sql);
            while(rst.next()){
                FournisseurPrix fournisseurprix = new FournisseurPrix(rst.getString("produit"),rst.getString("marque_produit"),rst.getInt("unite_produit"),rst.getDouble("prix_produit"),rst.getString("nom_fournisseur"));
                list.add(fournisseurprix);
            }
            DefaultTableModel mo = (DefaultTableModel) T.getModel();
            mo.setRowCount(0);
            Object row[] = new Object[5];
            for (int k=0;k<list.size();k++){
                row[0] = list.get(k).getProduit();
                row[1] = list.get(k).getMarque();
                row[2] = list.get(k).getUnite();
                row[3] = list.get(k).getPrix();
                row[4] = list.get(k).getFournisseur();
                mo.addRow(row);
            }
            list.clear();
            
            
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    //********************recuperer id produit de where idclient*********************
    
  public void getIdProduitPrixF(int id, JComboBox combo) {
    try {
        String sql = "SELECT id_produit FROM fournisseur_produit WHERE id_fournisseur=?";
        try (PreparedStatement st = cnx.prepareStatement(sql)) {
            st.setInt(1, id);
            try (ResultSet rst = st.executeQuery()) {
                List<String> produits = new ArrayList<>();
                while (rst.next()) {
                    int produitId = rst.getInt("id_produit");
                    String designationEtMarque = recupererDesignationEtMarque(produitId);
                    if (designationEtMarque != null) {
                        produits.add(designationEtMarque);
                    }
                }
                remplirComboBoxProduits(produits, combo);
            }
        }
    } catch (SQLException ex) {
        Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        JOptionPane.showMessageDialog(null, ex + "erreur de récupération de la liste des produits");
    }
}
  
  //************recuperer tout pour faire une achat*****************
    public void allFournisseurProduit(JTextField T1,JTextField T2,int idfourni,int idproduit){
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        String sql = "SELECT produit.unite AS unite_produit, fournisseur_produit.prix AS prix_produit " +
             "FROM fournisseur_produit " +
              "JOIN fournisseur ON fournisseur_produit.id_fournisseur = fournisseur.idfournisseur " +  
             "JOIN produit ON fournisseur_produit.id_produit = produit.idproduit " +
             "WHERE fournisseur_produit.id_fournisseur = ? AND fournisseur_produit.id_produit = ?";
        try (PreparedStatement st = cnx.prepareStatement(sql)){
            st.setInt(1, idfourni);
            st.setInt(2, idproduit);
            try (ResultSet rst =(ResultSet) st.executeQuery()){
                if(rst.next()){
                    int unite= rst.getInt("unite_produit");
                    double prix = rst.getDouble("prix_produit");
                    String prixFormat=decimalFormat.format(prix).replace(',', '.');
                    T1.setText(unite+"");
                    T2.setText(prixFormat);
                }else{
                    T1.setText("");
                    T2.setText("");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        //******************ajouter un versment pour un fournisseur*******************
    public void ajout_versement_fournisseur(VersementFournisseur versement){
       String query = "INSERT INTO versementf (id_fournisseur,fournisseur ,num,date,versement) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = cnx.prepareStatement(query)) {
             statement.setInt(1, versement.getId());
             statement.setString(2, versement.getFournisseur());
             statement.setInt(3, versement.getNum());
             statement.setDate(4, versement.getDate());
             statement.setDouble(5, versement.getMontant());
             
            // Exécuter la requête d'insertion
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Nouveau versement pour fournisseur inséré avec succès !");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    //******************afficher les versement d un client*******************
     public void allFournisseurVersement(JTable T) {
    try {
        ArrayList<VersementFournisseur> list = new ArrayList<VersementFournisseur>();
        st = cnx.createStatement();
        String sql = "SELECT * from versementf ORDER BY fournisseur";
        rst = (ResultSet) st.executeQuery(sql);
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        while (rst.next()) {
            VersementFournisseur versement = new VersementFournisseur(
                rst.getInt("id_fournisseur"),
                rst.getString("fournisseur"),
                rst.getInt("num"),
                rst.getDate("date"),
                Double.parseDouble(rst.getString("versement").replace(',', '.'))
            );
            list.add(versement);
        }

        DefaultTableModel mo = (DefaultTableModel) T.getModel();
        mo.setRowCount(0);
        
        for (VersementFournisseur vc : list) {
            Object[] row = new Object[5]; // Create a new instance of row for each VersementClient
            row[0] = vc.getNum();
            row[1] = vc.getId();
            row[2] = vc.getFournisseur();
            row[3] = vc.getDate();
            row[4] = vc.getMontant();
            mo.addRow(row);
        }
        list.clear();
    } catch (SQLException ex) {
        Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        JOptionPane.showMessageDialog(null, ex + "erreur de recuperation de la liste des clients");
    }
}
    }
