/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.aggoun.gestion1;
import com.aggoun.gestion1.DB.DataBase;
import com.aggoun.gestion1.model.Achat;
import com.aggoun.gestion1.model.Client;
import com.aggoun.gestion1.model.Fournisseur;
import com.aggoun.gestion1.model.Livraison;
import com.aggoun.gestion1.model.Produit;
import com.aggoun.gestion1.model.VersementClient;
import com.aggoun.gestion1.model.VersementFournisseur;
import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.Rectangle;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Dual Computer
 */
public class ACCEUIL1 extends javax.swing.JFrame {

    /**
     * Creates new form ACCEUIL1
     */
    DataBase db = new DataBase();
    public ACCEUIL1() throws SQLException {
        initComponents();
        setExtendedState(this.MAXIMIZED_BOTH);
        db.ConnectionToDataBase(); // Handle SQLException appropriately (e.g., show error message)
        parametrage();
        parametre_table_client();
        parametre_table_fournisseur();
        parametre_table_produit();
        parametre_table_livraison();
        parametre_table_achat();
        parametre_table_achat_modifier();
        parametre_table_livraison_modifier();
        parametre_table_client_prix();
        parametre_table_fournisseur_prix();
        parametre_versement_client();
        parametre_versement_fournisseur();
        parametre_table_stock();
    }
    public final void parametrage() throws SQLException{
         CBX_FOURNISSEUR_VERSEMENT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               Object fourni = CBX_FOURNISSEUR_VERSEMENT.getSelectedItem();
            if (fourni != null) {
                int num1 = db.getNUMMAXVERSEMENTF(fourni.toString()) + 1;
                TXT_NUM_VERSEMENT_FOURNISSEUR.setText(String.valueOf(num1));
            }
            }
        });
          CBX_CLIENT_VERSEMENT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               Object fourni = CBX_CLIENT_VERSEMENT.getSelectedItem();
            if (fourni != null) {
                int num1 = db.getNUMMAXVERSEMENT(fourni.toString()) + 1;
                TXT_NUM_VERSEMENT_CLIENT.setText(String.valueOf(num1));
            }
            }
        });
       db.allStockAlerte(TABLE_ALERTE_STOCK);
        CBX_TYPE_MODIFIER_ACHAT.setSelectedItem("num");
        CBX_TYPE_MODIFIER_LIVR.setSelectedItem("num");
        db.allAchat(TABLE_ACHAT_MODIFIER);
        db.allLivraison(TABLE_LIVR_MODIFIER);
        db.allStock(TABLE_STOCK);
        CBX_FOURNISSEUR_RECHERCHE_ACHAT.removeAllItems();
        db.getAllFournisseur(CBX_FOURNISSEUR_RECHERCHE_ACHAT);
        
         CBX_TYPE_MODIFIER_LIVR.setSelectedItem("num");
        db.allLivraison(TABLE_LIVR_MODIFIER);
        CBX_CLIENT_RECHERCHE_LIVR.removeAllItems();
        db.getAllClient(CBX_CLIENT_RECHERCHE_LIVR);
      
        CBX_FOURNISSEUR_VERSEMENT.removeAllItems();
        db.getAllFournisseur(CBX_FOURNISSEUR_VERSEMENT);
        
        db.allFournisseurVersement(TABLE_FOURNISSEUR_VERSEMENT);
        
        
        db.allClientVersement(TABLE_CLIENT_VERSEMENT);
       
                       
        int IDMAX=db.getIDMaxClient()+1;
        TXT_ID_CLIENT.setText(String.valueOf(IDMAX));
        
        int IDMAX2=db.getIDMaxFournisseur()+1;
        TXT_ID_FOURNISSEUR.setText(String.valueOf(IDMAX2));
        
        int IDMAX1=db.getIDMaxProduit()+1;
        TXT_ID_PRODUIT.setText(String.valueOf(IDMAX1));
        
        CBX_CLIENT_LIVR.removeAllItems();
        db.getAllClient(CBX_CLIENT_LIVR);
        
        CBX_FOURNISSEUR_ACHAT.removeAllItems();
        db.getAllFournisseur(CBX_FOURNISSEUR_ACHAT);
        CBX_FOURNISSEUR_RECHERCHE_ACHAT.removeAllItems();
        db.getAllFournisseur(CBX_FOURNISSEUR_RECHERCHE_ACHAT);
        
        
        CBX_CLIENT_VERSEMENT.removeAllItems();
        db.getAllClient(CBX_CLIENT_VERSEMENT);
        
        CBX_CLIENT_PRIX.removeAllItems();
        db.getAllClient(CBX_CLIENT_PRIX);
        
        CBX_FOURNISSEUR_PRIX.removeAllItems();
        db.getAllFournisseur(CBX_FOURNISSEUR_PRIX);
        
        CBX_PRODUIT_PRIX.removeAllItems();
        db.getAllProduit(CBX_PRODUIT_PRIX);
        CBX_PRODUIT_ACHAT.removeAllItems();
        db.getAllProduit(CBX_PRODUIT_ACHAT);
        CBX_PRODUIT_PRIXF.removeAllItems();
        db.getAllProduit(CBX_PRODUIT_PRIXF);
    }
     private void parametre_table_client() throws SQLException{
        
        db.allClient(TABLE_CLIENT);
        DefaultTableCellRenderer centre = new DefaultTableCellRenderer();
        centre.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        for (int i=0;i<8;i++){
            TABLE_CLIENT.getColumnModel().getColumn(i).setCellRenderer(centre);
        } 
        TABLE_CLIENT.setRowHeight(30);
        TableColumnModel colummodel= TABLE_CLIENT.getColumnModel();
        colummodel.getColumn(0).setPreferredWidth(10);
        colummodel.getColumn(1).setPreferredWidth(80);
        colummodel.getColumn(2).setPreferredWidth(80);
        colummodel.getColumn(3).setPreferredWidth(150);
        colummodel.getColumn(4).setPreferredWidth(100);
        colummodel.getColumn(5).setPreferredWidth(80);
        colummodel.getColumn(6).setPreferredWidth(80);
        colummodel.getColumn(7).setPreferredWidth(80);
        DefaultTableCellRenderer head = new DefaultTableCellRenderer();
        head.setHorizontalAlignment(JLabel.CENTER);
        head.setBackground(new Color(252,248,3));
        TABLE_CLIENT.getTableHeader().setDefaultRenderer(head);
        
        TABLE_CLIENT.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                    
                if (!e.getValueIsAdjusting()){
                    int selectedrow =TABLE_CLIENT.getSelectedRow();
                    if(selectedrow!= -1){
                        String id =TABLE_CLIENT.getValueAt(selectedrow,0)+"";
                        TXT_ID_CLIENT.setText(id);
                        TXT_NOM_CLIENT.setText(TABLE_CLIENT.getValueAt(selectedrow,1)+"");
                        TXT_PRENOM_CLIENT.setText(TABLE_CLIENT.getValueAt(selectedrow,2)+"");
                        TXT_ADRESSE_CLIENT.setText(TABLE_CLIENT.getValueAt(selectedrow,3)+"");
                        TXT_EMAIL_CLIENT.setText(TABLE_CLIENT.getValueAt(selectedrow,4)+"");
                        TXT_TELEPHONE_CLIENT.setText(TABLE_CLIENT.getValueAt(selectedrow,5)+"");
                        TXT_CNR_CLIENT.setText(TABLE_CLIENT.getValueAt(selectedrow,6)+"");
                        TXT_NIF_CLIENT.setText(TABLE_CLIENT.getValueAt(selectedrow,7)+"");
                    }
                }
            }      
       
        });
        TXT_RECHERCHE_CLIENT.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                filterData(TXT_RECHERCHE_CLIENT.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filterData(TXT_RECHERCHE_CLIENT.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                // Pas nécessaire pour un champ de texte simple
            }
        });
        
        //***********calcul le montant autoamtiquement lors du livraison*************
        TXT_PRIX_LIVR.getDocument().addDocumentListener(new DocumentListener(){
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateMontantLIVR();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateMontantLIVR();            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateMontantLIVR();
            }
        
            });
            
        TXT_UNITE_LIVR.getDocument().addDocumentListener(new DocumentListener(){
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateMontantLIVR();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateMontantLIVR();            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateMontantLIVR();
            }
        
            });
        TXT_QUANTITE_LIVR.getDocument().addDocumentListener(new DocumentListener(){
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateMontantLIVR();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateMontantLIVR();            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateMontantLIVR();
            }
        
            });
    }
     private void filterData(String searchText) {
        DefaultTableModel model = (DefaultTableModel) TABLE_CLIENT.getModel();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        TABLE_CLIENT.setRowSorter(sorter);
        sorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchText)); // (?i) pour ignorer la casse
}
        private void filterData1(String searchText) {
        DefaultTableModel model = (DefaultTableModel) TABLE_FOURNISSEUR.getModel();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        TABLE_FOURNISSEUR.setRowSorter(sorter);
        sorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchText)); // (?i) pour ignorer la casse
}
  private void parametre_table_stock() throws SQLException{
        
        db.allStock(TABLE_STOCK);
        DefaultTableCellRenderer centre = new DefaultTableCellRenderer();
        centre.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        for (int i=0;i<8;i++){
            TABLE_STOCK.getColumnModel().getColumn(i).setCellRenderer(centre);
        } 
        for (int i=0;i<5;i++){
            TABLE_ALERTE_STOCK.getColumnModel().getColumn(i).setCellRenderer(centre);
        } 
        TABLE_ALERTE_STOCK.setRowHeight(30);
        TABLE_STOCK.setRowHeight(30);
        TableColumnModel colummodel2= TABLE_ALERTE_STOCK.getColumnModel();
        colummodel2.getColumn(0).setPreferredWidth(60);
        colummodel2.getColumn(1).setPreferredWidth(30);
        
        colummodel2.getColumn(2).setPreferredWidth(80);
        colummodel2.getColumn(3).setPreferredWidth(30);
        colummodel2.getColumn(4).setPreferredWidth(100);
      
        
        TableColumnModel colummodel= TABLE_STOCK.getColumnModel();
        colummodel.getColumn(0).setPreferredWidth(60);
        colummodel.getColumn(1).setPreferredWidth(30);
        
        colummodel.getColumn(2).setPreferredWidth(80);
        colummodel.getColumn(3).setPreferredWidth(30);
        colummodel.getColumn(4).setPreferredWidth(100);
        colummodel.getColumn(5).setPreferredWidth(100);
        colummodel.getColumn(6).setPreferredWidth(50);
        colummodel.getColumn(7).setPreferredWidth(60);
        DefaultTableCellRenderer head = new DefaultTableCellRenderer();
        head.setHorizontalAlignment(JLabel.CENTER);
        head.setBackground(new Color(252,248,3));
        TABLE_STOCK.getTableHeader().setDefaultRenderer(head);
        TABLE_ALERTE_STOCK.getTableHeader().setDefaultRenderer(head);
        }
    private void parametre_table_fournisseur() throws SQLException{
        
        db.allFournisseur(TABLE_FOURNISSEUR);
        DefaultTableCellRenderer centre = new DefaultTableCellRenderer();
        centre.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        for (int i=0;i<7;i++){
            TABLE_FOURNISSEUR.getColumnModel().getColumn(i).setCellRenderer(centre);
        } 
        TABLE_FOURNISSEUR.setRowHeight(30);
        TableColumnModel colummodel= TABLE_FOURNISSEUR.getColumnModel();
        colummodel.getColumn(0).setPreferredWidth(10);
        colummodel.getColumn(1).setPreferredWidth(80);
        
        colummodel.getColumn(2).setPreferredWidth(150);
        colummodel.getColumn(3).setPreferredWidth(100);
        colummodel.getColumn(4).setPreferredWidth(80);
        colummodel.getColumn(5).setPreferredWidth(80);
        colummodel.getColumn(6).setPreferredWidth(80);
        DefaultTableCellRenderer head = new DefaultTableCellRenderer();
        head.setHorizontalAlignment(JLabel.CENTER);
        head.setBackground(new Color(252,248,3));
        TABLE_FOURNISSEUR.getTableHeader().setDefaultRenderer(head);
        
        TABLE_FOURNISSEUR.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                    
                if (!e.getValueIsAdjusting()){
                    int selectedrow =TABLE_FOURNISSEUR.getSelectedRow();
                    if(selectedrow!= -1){
                        String id =TABLE_FOURNISSEUR.getValueAt(selectedrow,0)+"";
                        TXT_ID_FOURNISSEUR.setText(id);
                        TXT_NOM_FOURNISSEUR.setText(TABLE_FOURNISSEUR.getValueAt(selectedrow,1)+"");
                        
                        TXT_ADRESSE_FOURNISSEUR.setText(TABLE_FOURNISSEUR.getValueAt(selectedrow,2)+"");
                        TXT_EMAIL_FOURNISSEUR.setText(TABLE_FOURNISSEUR.getValueAt(selectedrow,3)+"");
                        TXT_TELEPHONE_FOURNISSEUR.setText(TABLE_FOURNISSEUR.getValueAt(selectedrow,4)+"");
                        TXT_CNR_FOURNISSEUR.setText(TABLE_FOURNISSEUR.getValueAt(selectedrow,5)+"");
                        TXT_NIF_FOURNISSEUR.setText(TABLE_FOURNISSEUR.getValueAt(selectedrow,6)+"");
                    }
                }
            }      
       
        });
        TXT_RECHERCHE_FOURNISSEUR.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                filterData1(TXT_RECHERCHE_FOURNISSEUR.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filterData1(TXT_RECHERCHE_FOURNISSEUR.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                // Pas nécessaire pour un champ de texte simple
            }
        });
        //***********calcul le montant autoamtiquement lors du livraison*************
        TXT_PRIX_ACHAT.getDocument().addDocumentListener(new DocumentListener(){
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateMontantACHAT();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateMontantACHAT();            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateMontantACHAT();
            }
        
            });
            
        TXT_UNITE_ACHAT.getDocument().addDocumentListener(new DocumentListener(){
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateMontantACHAT();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateMontantACHAT();            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateMontantACHAT();
            }
        
            });
        TXT_QUANTITE_ACHAT.getDocument().addDocumentListener(new DocumentListener(){
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateMontantACHAT();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateMontantACHAT();            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateMontantACHAT();
            }
        
            });
        TXT_QUANTITE_ACHAT_MODIFIER.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateMontantACHATModifier();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateMontantACHATModifier();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateMontantACHATModifier();
            }
        });
        TXT_QUANTITE_LIVR_MODIFIER.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateMontantLIVRModifier();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateMontantLIVRModifier();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateMontantLIVRModifier();
            }
        });
    }
    
    public void updateMontantLIVR(){
        try {
            DecimalFormat decimalFormat = new DecimalFormat("0.00");

            double prix = Double.parseDouble(TXT_PRIX_LIVR.getText());
            double unite = Double.parseDouble(TXT_UNITE_LIVR.getText());
            double qte = Double.parseDouble(TXT_QUANTITE_LIVR.getText());
            double montant = prix * unite * qte;
            String formattedMontant = decimalFormat.format(montant);
            LABEL_MONTANTHT_LIVR.setText(formattedMontant);
                double tva = db.getTVAuser();
                double ecart = montant * tva;
                double montantTTC = montant + ecart;
                String formattedTVA = decimalFormat.format(montantTTC);
                LABEL_MONTANTTTC_LIVR.setText(formattedTVA);
           
        } catch (NumberFormatException e) {
            // Gérer les erreurs de conversion en double
            LABEL_MONTANTHT_LIVR.setText("");
        }
    }
     public void updateMontantACHAT(){
        try {
            DecimalFormat decimalFormat = new DecimalFormat("0.00");

            double prix = Double.parseDouble(TXT_PRIX_ACHAT.getText());
            double unite = Double.parseDouble(TXT_UNITE_ACHAT.getText());
            double qte = Double.parseDouble(TXT_QUANTITE_ACHAT.getText());
            double montant = prix * unite * qte;
            String formattedMontant = decimalFormat.format(montant);
            LABEL_MONTANTHT_ACHAT.setText(formattedMontant);
            
                double tva = db.getTVAuser();
                double ecart = montant * tva;
                double montantTTC = montant + ecart;
                String formattedTVA = decimalFormat.format(montantTTC);
                LABEL_MONTANTTTC_ACHAT.setText(formattedTVA);
           
        } catch (NumberFormatException e) {
            // Gérer les erreurs de conversion en double
            LABEL_MONTANTHT_ACHAT.setText("");
        }
    }
     public void updateMontantACHATModifier(){
        try {
            DecimalFormat decimalFormat = new DecimalFormat("0.00");

            double prix = Double.parseDouble(TXT_PRIX_ACHAT_MODIFIER.getText());
            double unite = Double.parseDouble(TXT_UNITE_ACHAT_MODIFIER.getText());
            double qte = Double.parseDouble(TXT_QUANTITE_ACHAT_MODIFIER.getText());
            double montant = prix * unite * qte;
            String formattedMontant = decimalFormat.format(montant);
            TXT_MONTANT_ACHAT_MODIFIER.setText(formattedMontant);

        } catch (NumberFormatException e) {
            // Gérer les erreurs de conversion en double
            TXT_MONTANT_ACHAT_MODIFIER.setText("");
        }
    }
    public void updateMontantLIVRModifier(){
        try {
            DecimalFormat decimalFormat = new DecimalFormat("0.00");

            double prix = Double.parseDouble(TXT_PRIX_LIVR_MODIFIER.getText());
            double unite = Double.parseDouble(TXT_UNITE_LIVR_MODIFIER.getText());
            double qte = Double.parseDouble(TXT_QUANTITE_LIVR_MODIFIER.getText());
            double montant = prix * unite * qte;
            String formattedMontant = decimalFormat.format(montant);
            TXT_MONTANT_LIVR_MODIFIER.setText(formattedMontant);

        } catch (NumberFormatException e) {
            // Gérer les erreurs de conversion en double
            TXT_MONTANT_LIVR_MODIFIER.setText("");
        }
    }
    private void parametre_table_livraison(){
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer() {
            {
                setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
            }

            @Override
            public Component getTableCellRendererComponent(JTable TABLE_LIVRAISON, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component rendererComponent = super.getTableCellRendererComponent(TABLE_LIVRAISON, value, isSelected, hasFocus, row, column);
                if (column == 5 && value instanceof Number) {
                    setText(new DecimalFormat("0.00").format(value));
                }
                return rendererComponent;
            }
        };
        TABLE_LIVRAISON.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
       for (int i=0;i<9;i++){
            TABLE_LIVRAISON.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
       
       DefaultTableCellRenderer dateRenderer = new DefaultTableCellRenderer() {
            private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            @Override
            public Component getTableCellRendererComponent(JTable TABLE_LIVRAISON, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component rendererComponent = super.getTableCellRendererComponent(TABLE_LIVRAISON, value, isSelected, hasFocus, row, column);
                if (column == 8 && value instanceof Date) {
                    setText(sdf.format(value));
                }
                return rendererComponent;
            }
        };
       TABLE_LIVRAISON.getColumnModel().getColumn(8).setCellRenderer(dateRenderer);

        TABLE_LIVRAISON.setRowHeight(30);
        TableColumnModel colummodel= TABLE_LIVRAISON.getColumnModel();
        colummodel.getColumn(0).setPreferredWidth(10);
        colummodel.getColumn(1).setPreferredWidth(100);
        colummodel.getColumn(2).setPreferredWidth(100);
        colummodel.getColumn(3).setPreferredWidth(80);
        colummodel.getColumn(4).setPreferredWidth(60);
        colummodel.getColumn(5).setPreferredWidth(80);
        colummodel.getColumn(6).setPreferredWidth(60);
        colummodel.getColumn(7).setPreferredWidth(80);
        colummodel.getColumn(8).setPreferredWidth(80);
        DefaultTableCellRenderer head = new DefaultTableCellRenderer();
        
        head.setHorizontalAlignment(JLabel.CENTER);
        head.setBackground(new Color(252,248,3));
        TABLE_LIVRAISON.getTableHeader().setDefaultRenderer(head);
        
        TABLE_LIVRAISON.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                    
                if (!e.getValueIsAdjusting()){
                    int selectedrow =TABLE_LIVRAISON.getSelectedRow();
                    if(selectedrow!= -1){
                        String num =TABLE_LIVRAISON.getValueAt(selectedrow,0)+"";
                        TXT_NUM_LIVR.setText(num);
                        CBX_CLIENT_LIVR.setSelectedItem(TABLE_LIVRAISON.getValueAt(selectedrow,1)+"");
                        TXT_PRODUIT_LIVR.setText(TABLE_LIVRAISON.getValueAt(selectedrow,2)+"");
                        TXT_MARQUE_LIVR.setText(TABLE_LIVRAISON.getValueAt(selectedrow,3)+"");
                        CBX_PRODUIT_LIVR.setSelectedItem(TABLE_LIVRAISON.getValueAt(selectedrow,2)+" "+TABLE_LIVRAISON.getValueAt(selectedrow,3));
                        TXT_UNITE_LIVR.setText(TABLE_LIVRAISON.getValueAt(selectedrow,4)+"");
                        TXT_PRIX_LIVR.setText(TABLE_LIVRAISON.getValueAt(selectedrow,5)+"");
                        TXT_QUANTITE_LIVR.setText(TABLE_LIVRAISON.getValueAt(selectedrow,6)+"");
                        LABEL_MONTANTHT_LIVR.setText(TABLE_LIVRAISON.getValueAt(selectedrow,7)+"");
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                        Date date;
                        date = (Date) TABLE_LIVRAISON.getValueAt(selectedrow,8); // Maintenant, "date" est une instance de java.util.Date
                        CBX_DATE_LIVR.setDate(date);
                        
                    }
                }
            }      
       
        });
    }
    
    private void parametre_table_achat(){
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer() {
            {
                setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
            }

            @Override
            public Component getTableCellRendererComponent(JTable TABLE_ACHAT, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component rendererComponent = super.getTableCellRendererComponent(TABLE_ACHAT, value, isSelected, hasFocus, row, column);
                if (column == 5 && value instanceof Number) {
                    setText(new DecimalFormat("0.00").format(value));
                }
                return rendererComponent;
            }
        };
        TABLE_ACHAT.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
       for (int i=0;i<9;i++){
            TABLE_ACHAT.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
       
       DefaultTableCellRenderer dateRenderer = new DefaultTableCellRenderer() {
            private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            @Override
            public Component getTableCellRendererComponent(JTable TABLE_ACHAT, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component rendererComponent = super.getTableCellRendererComponent(TABLE_ACHAT, value, isSelected, hasFocus, row, column);
                if (column == 8 && value instanceof Date) {
                    setText(sdf.format(value));
                }
                return rendererComponent;
            }
        };
       TABLE_ACHAT.getColumnModel().getColumn(8).setCellRenderer(dateRenderer);

        TABLE_ACHAT.setRowHeight(30);
        TableColumnModel colummodel= TABLE_ACHAT.getColumnModel();
        colummodel.getColumn(0).setPreferredWidth(10);
        colummodel.getColumn(1).setPreferredWidth(100);
        colummodel.getColumn(2).setPreferredWidth(100);
        colummodel.getColumn(3).setPreferredWidth(80);
        colummodel.getColumn(4).setPreferredWidth(60);
        colummodel.getColumn(5).setPreferredWidth(80);
        colummodel.getColumn(6).setPreferredWidth(60);
        colummodel.getColumn(7).setPreferredWidth(80);
        colummodel.getColumn(8).setPreferredWidth(80);
        DefaultTableCellRenderer head = new DefaultTableCellRenderer();
        
        head.setHorizontalAlignment(JLabel.CENTER);
        head.setBackground(new Color(252,248,3));
        TABLE_ACHAT.getTableHeader().setDefaultRenderer(head);
        
        TABLE_ACHAT.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                    
                if (!e.getValueIsAdjusting()){
                    int selectedrow =TABLE_ACHAT.getSelectedRow();
                    if(selectedrow!= -1){
                        String num =TABLE_ACHAT.getValueAt(selectedrow,0)+"";
                        TXT_NUM_ACHAT.setText(num);
                        CBX_FOURNISSEUR_ACHAT.setSelectedItem(TABLE_ACHAT.getValueAt(selectedrow,1)+"");
                        TXT_PRODUIT_ACHAT.setText(TABLE_ACHAT.getValueAt(selectedrow,2)+"");
                        TXT_MARQUE_ACHAT.setText(TABLE_ACHAT.getValueAt(selectedrow,3)+"");
                        CBX_PRODUIT_ACHAT.setSelectedItem(TABLE_ACHAT.getValueAt(selectedrow,2)+" "+TABLE_ACHAT.getValueAt(selectedrow,3));
                        TXT_UNITE_ACHAT.setText(TABLE_ACHAT.getValueAt(selectedrow,4)+"");
                        TXT_PRIX_ACHAT.setText(TABLE_ACHAT.getValueAt(selectedrow,5)+"");
                        TXT_QUANTITE_ACHAT.setText(TABLE_ACHAT.getValueAt(selectedrow,6)+"");
                        LABEL_MONTANTHT_ACHAT.setText(TABLE_ACHAT.getValueAt(selectedrow,7)+"");
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                        Date date;
                        date = (Date) TABLE_ACHAT.getValueAt(selectedrow,8); // Maintenant, "date" est une instance de java.util.Date
                        CBX_DATE_ACHAT.setDate(date);
                        
                    }
                }
            }      
       
        });
    }
    private void parametre_table_achat_modifier(){
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer() {
            {
                setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
            }

            @Override
            public Component getTableCellRendererComponent(JTable TABLE_ACHAT_MODIFIER, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component rendererComponent = super.getTableCellRendererComponent(TABLE_ACHAT_MODIFIER, value, isSelected, hasFocus, row, column);
                if (column == 4  && value instanceof Number) {
                    setText(new DecimalFormat("0.00").format(value));
                }
                if (column == 6  && value instanceof Number) {
                    setText(new DecimalFormat("0.00").format(value));
                }
                return rendererComponent;
            }
        };
        TABLE_ACHAT_MODIFIER.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        TABLE_ACHAT_MODIFIER.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
       for (int i=0;i<10;i++){
            TABLE_ACHAT_MODIFIER.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
       
       DefaultTableCellRenderer dateRenderer = new DefaultTableCellRenderer() {
    private SimpleDateFormat sdfInput = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SS");
    private SimpleDateFormat sdfOutput = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    public Component getTableCellRendererComponent(JTable TABLE_ACHAT_MODIFIER, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component rendererComponent = super.getTableCellRendererComponent(TABLE_ACHAT_MODIFIER, value, isSelected, hasFocus, row, column);
        if (column == 7 && value instanceof String) {
            try {
                Date date = sdfInput.parse((String) value);
                setText(sdfOutput.format(date));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return rendererComponent;
    }
};
TABLE_ACHAT_MODIFIER.getColumnModel().getColumn(7).setCellRenderer(dateRenderer);

        TABLE_ACHAT_MODIFIER.setRowHeight(30);
        TableColumnModel colummodel= TABLE_ACHAT_MODIFIER.getColumnModel();
        colummodel.getColumn(0).setPreferredWidth(20);
        colummodel.getColumn(1).setPreferredWidth(100);
        colummodel.getColumn(2).setPreferredWidth(100);
        colummodel.getColumn(3).setPreferredWidth(60);
        colummodel.getColumn(4).setPreferredWidth(60);
        colummodel.getColumn(5).setPreferredWidth(60);
        colummodel.getColumn(6).setPreferredWidth(80);
        colummodel.getColumn(7).setPreferredWidth(80);
        colummodel.getColumn(8).setPreferredWidth(20);
        colummodel.getColumn(9).setPreferredWidth(100);
        DefaultTableCellRenderer head = new DefaultTableCellRenderer();
        
        head.setHorizontalAlignment(JLabel.CENTER);
        head.setBackground(new Color(252,248,3));
        TABLE_ACHAT_MODIFIER.getTableHeader().setDefaultRenderer(head);
        
        TABLE_ACHAT_MODIFIER.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                    
                if (!e.getValueIsAdjusting()){
                    int selectedrow =TABLE_ACHAT_MODIFIER.getSelectedRow();
                    if(selectedrow!= -1){
                        String num =TABLE_ACHAT_MODIFIER.getValueAt(selectedrow,0)+"";
                        TXT_NUM_ACHAT_MODIFIER.setText(num);
                        TXT_FOURNISSEUR_ACHAT_MODIFIER.setText(TABLE_ACHAT_MODIFIER.getValueAt(selectedrow,9)+"");
                        TXT_PRODUIT_ACHAT_MODIFIER.setText(TABLE_ACHAT_MODIFIER.getValueAt(selectedrow,1)+"");
                        TXT_MARQUE_ACHAT_MODIFIER.setText(TABLE_ACHAT_MODIFIER.getValueAt(selectedrow,2)+"");
                       
                        TXT_UNITE_ACHAT_MODIFIER.setText(TABLE_ACHAT_MODIFIER.getValueAt(selectedrow,3)+"");
                        TXT_PRIX_ACHAT_MODIFIER.setText(TABLE_ACHAT_MODIFIER.getValueAt(selectedrow,4)+"");
                        TXT_QUANTITE_ACHAT_MODIFIER.setText(TABLE_ACHAT_MODIFIER.getValueAt(selectedrow,5)+"");
                        TXT_MONTANT_ACHAT_MODIFIER.setText(TABLE_ACHAT_MODIFIER.getValueAt(selectedrow,6)+"");
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                        ID.setText(num);
                        
                        Object dateObject = TABLE_ACHAT_MODIFIER.getValueAt(selectedrow, 7);
                if (dateObject instanceof Date) {
                    Date date = (Date) dateObject;
                    CBX_DATE_ACHAT_MODIFIER.setDate(date);
                } else if (dateObject instanceof String) {
                    try {
                       
                        Date date = sdf.parse((String) dateObject);
                        CBX_DATE_ACHAT_MODIFIER.setDate(date);
                    } catch (ParseException ex) {
                        ex.printStackTrace(); // Handle parsing exception appropriately
                    }
                }
                    }
                }
            }      
       
        });
    }
    private void parametre_table_livraison_modifier(){
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer() {
            {
                setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
            }

            @Override
            public Component getTableCellRendererComponent(JTable TABLE_LIVR_MODIFIER, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component rendererComponent = super.getTableCellRendererComponent(TABLE_LIVR_MODIFIER, value, isSelected, hasFocus, row, column);
                if (column == 4  && value instanceof Number) {
                    setText(new DecimalFormat("0.00").format(value));
                }
                if (column == 6  && value instanceof Number) {
                    setText(new DecimalFormat("0.00").format(value));
                }
                return rendererComponent;
            }
        };
        TABLE_LIVR_MODIFIER.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        TABLE_LIVR_MODIFIER.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
       for (int i=0;i<10;i++){
            TABLE_LIVR_MODIFIER.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
       
       DefaultTableCellRenderer dateRenderer = new DefaultTableCellRenderer() {
    private SimpleDateFormat sdfInput = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SS");
    private SimpleDateFormat sdfOutput = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    public Component getTableCellRendererComponent(JTable TABLE_LIVR_MODIFIER, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component rendererComponent = super.getTableCellRendererComponent(TABLE_LIVR_MODIFIER, value, isSelected, hasFocus, row, column);
        if (column == 7 && value instanceof String) {
            try {
                Date date = sdfInput.parse((String) value);
                setText(sdfOutput.format(date));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return rendererComponent;
    }
};
TABLE_LIVR_MODIFIER.getColumnModel().getColumn(7).setCellRenderer(dateRenderer);

        TABLE_LIVR_MODIFIER.setRowHeight(30);
        TableColumnModel colummodel= TABLE_LIVR_MODIFIER.getColumnModel();
        colummodel.getColumn(0).setPreferredWidth(20);
        colummodel.getColumn(1).setPreferredWidth(100);
        colummodel.getColumn(2).setPreferredWidth(100);
        colummodel.getColumn(3).setPreferredWidth(60);
        colummodel.getColumn(4).setPreferredWidth(60);
        colummodel.getColumn(5).setPreferredWidth(60);
        colummodel.getColumn(6).setPreferredWidth(80);
        colummodel.getColumn(7).setPreferredWidth(80);
        colummodel.getColumn(8).setPreferredWidth(20);
        colummodel.getColumn(9).setPreferredWidth(100);
        DefaultTableCellRenderer head = new DefaultTableCellRenderer();
        
        head.setHorizontalAlignment(JLabel.CENTER);
        head.setBackground(new Color(252,248,3));
        TABLE_LIVR_MODIFIER.getTableHeader().setDefaultRenderer(head);
        
        TABLE_LIVR_MODIFIER.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                    
                if (!e.getValueIsAdjusting()){
                    int selectedrow =TABLE_LIVR_MODIFIER.getSelectedRow();
                    if(selectedrow!= -1){
                        String num =TABLE_LIVR_MODIFIER.getValueAt(selectedrow,0)+"";
                        TXT_NUM_LIVR_MODIFIER.setText(num);
                        TXT_CLIENT_LIVR_MODIFIER.setText(TABLE_LIVR_MODIFIER.getValueAt(selectedrow,9)+"");
                        TXT_PRODUIT_LIVR_MODIFIER.setText(TABLE_LIVR_MODIFIER.getValueAt(selectedrow,1)+"");
                        TXT_MARQUE_LIVR_MODIFIER.setText(TABLE_LIVR_MODIFIER.getValueAt(selectedrow,2)+"");
                       
                        TXT_UNITE_LIVR_MODIFIER.setText(TABLE_LIVR_MODIFIER.getValueAt(selectedrow,3)+"");
                        TXT_PRIX_LIVR_MODIFIER.setText(TABLE_LIVR_MODIFIER.getValueAt(selectedrow,4)+"");
                        TXT_QUANTITE_LIVR_MODIFIER.setText(TABLE_LIVR_MODIFIER.getValueAt(selectedrow,5)+"");
                        TXT_MONTANT_LIVR_MODIFIER.setText(TABLE_LIVR_MODIFIER.getValueAt(selectedrow,6)+"");
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                        ID1.setText(num);
                        
                        Object dateObject = TABLE_LIVR_MODIFIER.getValueAt(selectedrow, 7);
                if (dateObject instanceof Date) {
                    Date date = (Date) dateObject;
                    CBX_DATE_LIVR_MODIFIER.setDate(date);
                } else if (dateObject instanceof String) {
                    try {
                       
                        Date date = sdf.parse((String) dateObject);
                        CBX_DATE_LIVR_MODIFIER.setDate(date);
                    } catch (ParseException ex) {
                        ex.printStackTrace(); // Handle parsing exception appropriately
                    }
                }
                    }
                }
            }      
       
        });
    }
    private void parametre_table_produit(){
        db.allProduit(TABLE_PRODUIT);
        DefaultTableCellRenderer centre = new DefaultTableCellRenderer();
        centre.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        for (int i=0;i<4;i++){
            TABLE_PRODUIT.getColumnModel().getColumn(i).setCellRenderer(centre);
        } 
        TABLE_PRODUIT.setRowHeight(30);
        TableColumnModel colummodel= TABLE_PRODUIT.getColumnModel();
        colummodel.getColumn(0).setPreferredWidth(10);
        colummodel.getColumn(1).setPreferredWidth(100);
        colummodel.getColumn(2).setPreferredWidth(100);
        colummodel.getColumn(3).setPreferredWidth(60);
        
        DefaultTableCellRenderer head = new DefaultTableCellRenderer();
        head.setHorizontalAlignment(JLabel.CENTER);
        head.setBackground(new Color(252,248,3));
        TABLE_PRODUIT.getTableHeader().setDefaultRenderer(head);
        
        TABLE_PRODUIT.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                    
                if (!e.getValueIsAdjusting()){
                    int selectedrow =TABLE_PRODUIT.getSelectedRow();
                    if(selectedrow!= -1){
                        String id =TABLE_PRODUIT.getValueAt(selectedrow,0)+"";
                        TXT_ID_PRODUIT.setText(id);
                        TXT_PRODUIT.setText(TABLE_PRODUIT.getValueAt(selectedrow,1)+"");
                        TXT_MARQUE.setText(TABLE_PRODUIT.getValueAt(selectedrow,2)+"");
                        TXT_UNITE_PRODUIT.setText(TABLE_PRODUIT.getValueAt(selectedrow,3)+"");
                      
                    }
                }
            }      
       
        });
                          
    }
    
    public final void parametre_table_client_prix() throws SQLException {
        db.allClientProduitTable(TABLE_CLIENT_PRIX);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer() {
            {
                setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
            }

            @Override
            public Component getTableCellRendererComponent(JTable TABLE_CLIENT_PRIX, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component rendererComponent = super.getTableCellRendererComponent(TABLE_CLIENT_PRIX, value, isSelected, hasFocus, row, column);
                if (column == 3 && value instanceof Number) {
                    setText(new DecimalFormat("0.00").format(value));
                }
                return rendererComponent;
            }
        };

        DefaultTableCellRenderer centre = new DefaultTableCellRenderer();
        centre.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);

        JTableHeader header = TABLE_CLIENT_PRIX.getTableHeader();
        header.setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable TABLE_CLIENT_PRIX, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = (JLabel) super.getTableCellRendererComponent(TABLE_CLIENT_PRIX, value, isSelected, hasFocus, row, column);
                label.setHorizontalAlignment(JLabel.CENTER);
                label.setBackground(new Color(252, 248, 3));
                return label;
            }
        });

        TableColumnModel columnModel = TABLE_CLIENT_PRIX.getColumnModel();
        for (int i = 0; i < 5; i++) {
            columnModel.getColumn(i).setCellRenderer(centerRenderer);
            columnModel.getColumn(i).setPreferredWidth(i == 3 ? 80 : 100);
        }

        TABLE_CLIENT_PRIX.setRowHeight(30);

        TABLE_CLIENT_PRIX.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = TABLE_CLIENT_PRIX.getSelectedRow();
                    int selectedColumn = 3; // Assuming the price column is always the fourth column
                    if (selectedRow != -1) {
                        Object price = TABLE_CLIENT_PRIX.getValueAt(selectedRow, selectedColumn);
                        if (price instanceof Number) {
                            double priceValue = ((Number) price).doubleValue();
                            TXT_PRIX_CLIENT.setText(new DecimalFormat("0.00").format(priceValue));
                        }
                    }
                }
            }
        });
    }
    public final void parametre_table_fournisseur_prix() throws SQLException {
        db.allFournisseurProduitTable(TABLE_FOURNISSEUR_PRIX);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer() {
            {
                setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
            }

            @Override
            public Component getTableCellRendererComponent(JTable TABLE_FOURNISSEUR_PRIX, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component rendererComponent = super.getTableCellRendererComponent(TABLE_FOURNISSEUR_PRIX, value, isSelected, hasFocus, row, column);
                if (column == 3 && value instanceof Number) {
                    setText(new DecimalFormat("0.00").format(value));
                }
                return rendererComponent;
            }
        };

        DefaultTableCellRenderer centre = new DefaultTableCellRenderer();
        centre.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);

        JTableHeader header = TABLE_FOURNISSEUR_PRIX.getTableHeader();
        header.setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable TABLE_FOURNISSEUR_PRIX, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = (JLabel) super.getTableCellRendererComponent(TABLE_FOURNISSEUR_PRIX, value, isSelected, hasFocus, row, column);
                label.setHorizontalAlignment(JLabel.CENTER);
                label.setBackground(new Color(252, 248, 3));
                return label;
            }
        });

        TableColumnModel columnModel = TABLE_FOURNISSEUR_PRIX.getColumnModel();
        for (int i = 0; i < 5; i++) {
            columnModel.getColumn(i).setCellRenderer(centerRenderer);
            columnModel.getColumn(i).setPreferredWidth(i == 3 ? 80 : 100);
        }

        TABLE_FOURNISSEUR_PRIX.setRowHeight(30);

        TABLE_FOURNISSEUR_PRIX.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = TABLE_FOURNISSEUR_PRIX.getSelectedRow();
                    int selectedColumn = 3; // Assuming the price column is always the fourth column
                    if (selectedRow != -1) {
                        Object price = TABLE_FOURNISSEUR_PRIX.getValueAt(selectedRow, selectedColumn);
                        if (price instanceof Number) {
                            double priceValue = ((Number) price).doubleValue();
                            TXT_PRIX_FOURNISSEUR.setText(new DecimalFormat("0.00").format(priceValue));
                        }
                    }
                }
            }
        });
    }
    private void parametre_versement_client(){
         
         db.allClientVersement(TABLE_CLIENT_VERSEMENT);
         
         DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer() {
            {
                setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
            }

            @Override
            public Component getTableCellRendererComponent(JTable TABLE_CLIENT_VERSEMENT, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component rendererComponent = super.getTableCellRendererComponent(TABLE_CLIENT_VERSEMENT, value, isSelected, hasFocus, row, column);
                if (column == 4 && value instanceof Number) {
                    setText(new DecimalFormat("0.00").format(value));
                }
                return rendererComponent;
            }
        };

        DefaultTableCellRenderer centre = new DefaultTableCellRenderer();
        centre.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);

        JTableHeader header = TABLE_CLIENT_VERSEMENT.getTableHeader();
        header.setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable TABLE_CLIENT_VERSEMENT, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = (JLabel) super.getTableCellRendererComponent(TABLE_CLIENT_VERSEMENT, value, isSelected, hasFocus, row, column);
                label.setHorizontalAlignment(JLabel.CENTER);
                label.setBackground(new Color(252, 248, 3));
                return label;
            }
        });

        TableColumnModel columnModel = TABLE_CLIENT_VERSEMENT.getColumnModel();
        for (int i = 0; i < 5; i++) {
            columnModel.getColumn(i).setCellRenderer(centerRenderer);
            columnModel.getColumn(i).setPreferredWidth(i == 4 ? 80 : 100);
        }
        columnModel.getColumn(4).setCellRenderer(centerRenderer);

        TABLE_CLIENT_VERSEMENT.setRowHeight(30);

       TABLE_CLIENT_VERSEMENT.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            int selectedRow = TABLE_CLIENT_VERSEMENT.getSelectedRow();
            int selectedColumn = 3; // Assuming the date column is always the fourth column
            if (selectedRow != -1) {
                CBX_CLIENT_VERSEMENT.setSelectedItem(TABLE_CLIENT_VERSEMENT.getValueAt(selectedRow, 2));
                Object dateObject = TABLE_CLIENT_VERSEMENT.getValueAt(selectedRow, selectedColumn);
                if (dateObject instanceof Date) {
                    Date date = (Date) dateObject;
                    CBX_DATE_VERSEMENT_CLIENT.setDate(date);
                } else if (dateObject instanceof String) {
                    try {
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                        Date date = sdf.parse((String) dateObject);
                        CBX_DATE_VERSEMENT_CLIENT.setDate(date);
                    } catch (ParseException ex) {
                        ex.printStackTrace(); // Handle parsing exception appropriately
                    }
                }
                // Assuming TXT_NUM_VERSEMENT_CLIENT is in the first column
                TXT_NUM_VERSEMENT_CLIENT.setText(TABLE_CLIENT_VERSEMENT.getValueAt(selectedRow, 0) + "");
                
                // Assuming the price column is the fifth column
                Object price = TABLE_CLIENT_VERSEMENT.getValueAt(selectedRow, 4);
                if (price instanceof Number) {
                    double priceValue = ((Number) price).doubleValue();
                    TXT_VERSEMENT_CLIENT.setText(new DecimalFormat("0.00").format(priceValue));
                }
            }
        }
    }
});
    }
    
    private void parametre_versement_fournisseur(){
        
         db.allFournisseurVersement(TABLE_FOURNISSEUR_VERSEMENT);
         
         DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer() {
            {
                setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
            }

            @Override
            public Component getTableCellRendererComponent(JTable TABLE_FOURNISSEUR_VERSEMENT, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component rendererComponent = super.getTableCellRendererComponent(TABLE_FOURNISSEUR_VERSEMENT, value, isSelected, hasFocus, row, column);
                if (column == 4 && value instanceof Number) {
                    setText(new DecimalFormat("0.00").format(value));
                }
                return rendererComponent;
            }
        };

        DefaultTableCellRenderer centre = new DefaultTableCellRenderer();
        centre.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);

        JTableHeader header = TABLE_FOURNISSEUR_VERSEMENT.getTableHeader();
        header.setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable TABLE_FOURNISSEUR_VERSEMENT, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = (JLabel) super.getTableCellRendererComponent(TABLE_FOURNISSEUR_VERSEMENT, value, isSelected, hasFocus, row, column);
                label.setHorizontalAlignment(JLabel.CENTER);
                label.setBackground(new Color(252, 248, 3));
                return label;
            }
        });

        TableColumnModel columnModel = TABLE_FOURNISSEUR_VERSEMENT.getColumnModel();
        for (int i = 0; i < 5; i++) {
            columnModel.getColumn(i).setCellRenderer(centerRenderer);
            columnModel.getColumn(i).setPreferredWidth(i == 4 ? 80 : 100);
        }
        columnModel.getColumn(4).setCellRenderer(centerRenderer);

        TABLE_FOURNISSEUR_VERSEMENT.setRowHeight(30);

       TABLE_FOURNISSEUR_VERSEMENT.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            int selectedRow = TABLE_FOURNISSEUR_VERSEMENT.getSelectedRow();
            int selectedColumn = 3; // Assuming the date column is always the fourth column
            if (selectedRow != -1) {
                 CBX_FOURNISSEUR_VERSEMENT.setSelectedItem(TABLE_FOURNISSEUR_VERSEMENT.getValueAt(selectedRow, 2));
                Object dateObject = TABLE_FOURNISSEUR_VERSEMENT.getValueAt(selectedRow, selectedColumn);
                if (dateObject instanceof Date) {
                    Date date = (Date) dateObject;
                    CBX_DATE_VERSEMENT_FOURNISSEUR.setDate(date);
                } else if (dateObject instanceof String) {
                    try {
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                        Date date = sdf.parse((String) dateObject);
                        CBX_DATE_VERSEMENT_FOURNISSEUR.setDate(date);
                    } catch (ParseException ex) {
                        ex.printStackTrace(); // Handle parsing exception appropriately
                    }
                }
                // Assuming TXT_NUM_VERSEMENT_CLIENT is in the first column
                TXT_NUM_VERSEMENT_FOURNISSEUR.setText(TABLE_FOURNISSEUR_VERSEMENT.getValueAt(selectedRow, 0) + "");
                
                // Assuming the price column is the fifth column
                Object price = TABLE_FOURNISSEUR_VERSEMENT.getValueAt(selectedRow, 4);
                if (price instanceof Number) {
                    double priceValue = ((Number) price).doubleValue();
                    TXT_VERSEMENT_FOURNISSEUR.setText(new DecimalFormat("0.00").format(priceValue));
                }
            }
        }
    }
});
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        ACCEUIL = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        TABLE_STOCK = new javax.swing.JTable();
        jLabel68 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane13 = new javax.swing.JScrollPane();
        TABLE_ALERTE_STOCK = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        BTN_OPEN_PARAMETRE = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        BTN_OPEN_PARAMETRE1 = new javax.swing.JButton();
        BTN_OPEN_PARAMETRE2 = new javax.swing.JButton();
        jLabel32 = new javax.swing.JLabel();
        PRODUIT = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        TXT_ID_PRODUIT = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        TXT_PRODUIT = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        TXT_MARQUE = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        TXT_UNITE_PRODUIT = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        TABLE_PRODUIT = new javax.swing.JTable();
        jLabel26 = new javax.swing.JLabel();
        BTN_AJOUT_PRODUIT = new javax.swing.JButton();
        BTN_MODIFIER_PRODUIT = new javax.swing.JButton();
        BTN_SUPPRIMER_PRODUIT = new javax.swing.JButton();
        BTN_ACTUALISER_PRODUIT = new javax.swing.JButton();
        jPanel13 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        CBX_CLIENT_PRIX = new javax.swing.JComboBox<>();
        jLabel29 = new javax.swing.JLabel();
        CBX_PRODUIT_PRIX = new javax.swing.JComboBox<>();
        jLabel30 = new javax.swing.JLabel();
        TXT_PRIX_CLIENT = new javax.swing.JTextField();
        BTN_AJOUT_PRIX = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        TABLE_CLIENT_PRIX = new javax.swing.JTable();
        BTN_MODIFIER_PRIX = new javax.swing.JButton();
        jPanel14 = new javax.swing.JPanel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        CBX_FOURNISSEUR_PRIX = new javax.swing.JComboBox<>();
        jLabel37 = new javax.swing.JLabel();
        CBX_PRODUIT_PRIXF = new javax.swing.JComboBox<>();
        jLabel38 = new javax.swing.JLabel();
        TXT_PRIX_FOURNISSEUR = new javax.swing.JTextField();
        BTN_AJOUT_PRIXF = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        TABLE_FOURNISSEUR_PRIX = new javax.swing.JTable();
        BTN_MODIFIER_PRIXF = new javax.swing.JButton();
        CLIENT = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        TXT_RECHERCHE_CLIENT = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TABLE_CLIENT = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        TXT_ID_CLIENT = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        TXT_NOM_CLIENT = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        TXT_PRENOM_CLIENT = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        TXT_ADRESSE_CLIENT = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        TXT_EMAIL_CLIENT = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        TXT_TELEPHONE_CLIENT = new javax.swing.JTextField();
        BTN_AJOUT_CLIENT = new javax.swing.JButton();
        BTN_SUPPRIMER_CLIENT = new javax.swing.JButton();
        BTN_MODIFIER_CLIENT = new javax.swing.JButton();
        BTN_ACTUALISER_CLIENT = new javax.swing.JButton();
        jLabel62 = new javax.swing.JLabel();
        TXT_CNR_CLIENT = new javax.swing.JTextField();
        jLabel63 = new javax.swing.JLabel();
        TXT_NIF_CLIENT = new javax.swing.JTextField();
        FOURNISSEUR = new javax.swing.JPanel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        TXT_RECHERCHE_FOURNISSEUR = new javax.swing.JTextField();
        jPanel19 = new javax.swing.JPanel();
        jLabel46 = new javax.swing.JLabel();
        TXT_ID_FOURNISSEUR = new javax.swing.JTextField();
        jLabel47 = new javax.swing.JLabel();
        TXT_NOM_FOURNISSEUR = new javax.swing.JTextField();
        jLabel49 = new javax.swing.JLabel();
        TXT_ADRESSE_FOURNISSEUR = new javax.swing.JTextField();
        jLabel50 = new javax.swing.JLabel();
        TXT_EMAIL_FOURNISSEUR = new javax.swing.JTextField();
        jLabel51 = new javax.swing.JLabel();
        TXT_TELEPHONE_FOURNISSEUR = new javax.swing.JTextField();
        BTN_AJOUT_FOURNISSEUR = new javax.swing.JButton();
        BTN_SUPPRIMER_FOURNISSEUR = new javax.swing.JButton();
        BTN_MODIFIER_FOURNISSEUR = new javax.swing.JButton();
        BTN_ACTUALISER_CLIENT1 = new javax.swing.JButton();
        jLabel64 = new javax.swing.JLabel();
        TXT_CNR_FOURNISSEUR = new javax.swing.JTextField();
        jLabel65 = new javax.swing.JLabel();
        TXT_NIF_FOURNISSEUR = new javax.swing.JTextField();
        jScrollPane9 = new javax.swing.JScrollPane();
        TABLE_FOURNISSEUR = new javax.swing.JTable();
        VERSEMENT = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        CBX_CLIENT_VERSEMENT = new javax.swing.JComboBox<>();
        TXT_VERSEMENT_CLIENT = new javax.swing.JTextField();
        jScrollPane6 = new javax.swing.JScrollPane();
        TABLE_CLIENT_VERSEMENT = new javax.swing.JTable();
        BTN_AJOUT_VERS_CLIENT = new javax.swing.JButton();
        BTN_MODIFIER_VERS_CLIENT = new javax.swing.JButton();
        BTN_SUPPRIMER_VERS_CLIENT = new javax.swing.JButton();
        jLabel42 = new javax.swing.JLabel();
        TXT_NUM_VERSEMENT_CLIENT = new javax.swing.JTextField();
        CBX_DATE_VERSEMENT_CLIENT = new com.toedter.calendar.JDateChooser();
        jPanel18 = new javax.swing.JPanel();
        BTN_SUPPRIMER_VERS_FOURNISSEUR = new javax.swing.JButton();
        BTN_MODIFIER_VERS_FOURNISSEUR = new javax.swing.JButton();
        BTN_AJOUT_VERS_FOURNISSEUR = new javax.swing.JButton();
        jLabel39 = new javax.swing.JLabel();
        CBX_FOURNISSEUR_VERSEMENT = new javax.swing.JComboBox<>();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        TXT_VERSEMENT_FOURNISSEUR = new javax.swing.JTextField();
        jScrollPane7 = new javax.swing.JScrollPane();
        TABLE_FOURNISSEUR_VERSEMENT = new javax.swing.JTable();
        jLabel66 = new javax.swing.JLabel();
        TXT_NUM_VERSEMENT_FOURNISSEUR = new javax.swing.JTextField();
        CBX_DATE_VERSEMENT_FOURNISSEUR = new com.toedter.calendar.JDateChooser();
        LIVRAISON = new javax.swing.JTabbedPane();
        LIVRAI = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        TXT_NUM_LIVR = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        CBX_CLIENT_LIVR = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        CBX_PRODUIT_LIVR = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        TXT_MARQUE_LIVR = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        TXT_UNITE_LIVR = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        TXT_PRIX_LIVR = new javax.swing.JTextField();
        BTN_AJOUT_LIVR = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        TXT_QUANTITE_LIVR = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        TXT_PRODUIT_LIVR = new javax.swing.JTextField();
        BTN_SUPPRIMER_LIVRAISON = new javax.swing.JButton();
        CBX_DATE_LIVR = new com.toedter.calendar.JDateChooser();
        jScrollPane2 = new javax.swing.JScrollPane();
        TABLE_LIVRAISON = new javax.swing.JTable();
        jLabel20 = new javax.swing.JLabel();
        LABEL_MONTANTHT_LIVR = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        LABEL_MONTANTTTC_LIVR = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        BTN_VALIDER_LIVR = new javax.swing.JButton();
        BTN_ACTUALISER_LIVR = new javax.swing.JButton();
        BTN_IMPRIMER_LIVR = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane14 = new javax.swing.JScrollPane();
        TABLE_LIVR_MODIFIER = new javax.swing.JTable();
        jPanel31 = new javax.swing.JPanel();
        jLabel99 = new javax.swing.JLabel();
        TXT_RECHERCHE_LIVR = new javax.swing.JTextField();
        jLabel100 = new javax.swing.JLabel();
        jLabel101 = new javax.swing.JLabel();
        CheckBox_NUM_LIVR = new javax.swing.JCheckBox();
        CheckBox_DATE_LIVR = new javax.swing.JCheckBox();
        jLabel102 = new javax.swing.JLabel();
        CBX_CLIENT_RECHERCHE_LIVR = new javax.swing.JComboBox<>();
        BTN_RECHERCHE_LIVR1 = new javax.swing.JButton();
        jLabel103 = new javax.swing.JLabel();
        CBX_DATE_LIVR_RECHERCHE = new com.toedter.calendar.JDateChooser();
        jPanel32 = new javax.swing.JPanel();
        jLabel104 = new javax.swing.JLabel();
        jLabel105 = new javax.swing.JLabel();
        TXT_NUM_LIVR_MODIFIER = new javax.swing.JTextField();
        jLabel106 = new javax.swing.JLabel();
        TXT_QUANTITE_LIVR_MODIFIER = new javax.swing.JTextField();
        jLabel107 = new javax.swing.JLabel();
        TXT_PRODUIT_LIVR_MODIFIER = new javax.swing.JTextField();
        jLabel108 = new javax.swing.JLabel();
        TXT_MARQUE_LIVR_MODIFIER = new javax.swing.JTextField();
        jLabel109 = new javax.swing.JLabel();
        TXT_UNITE_LIVR_MODIFIER = new javax.swing.JTextField();
        jLabel110 = new javax.swing.JLabel();
        TXT_PRIX_LIVR_MODIFIER = new javax.swing.JTextField();
        jLabel111 = new javax.swing.JLabel();
        TXT_MONTANT_LIVR_MODIFIER = new javax.swing.JTextField();
        jLabel112 = new javax.swing.JLabel();
        TXT_CLIENT_LIVR_MODIFIER = new javax.swing.JTextField();
        CBX_TYPE_MODIFIER_LIVR = new javax.swing.JComboBox<>();
        jLabel113 = new javax.swing.JLabel();
        ID1 = new javax.swing.JLabel();
        CBX_DATE_LIVR_MODIFIER = new com.toedter.calendar.JDateChooser();
        BTN_MODIFIER_LIVR1 = new javax.swing.JButton();
        BTN_SUPPRIMER_LIVROFF1 = new javax.swing.JButton();
        CHECKBOX_SUPP_NUM_LIVR = new javax.swing.JCheckBox();
        CHECKBOX_SUPP_PRODUIT_LIVR = new javax.swing.JCheckBox();
        ACHAT = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jPanel20 = new javax.swing.JPanel();
        jLabel48 = new javax.swing.JLabel();
        TXT_NUM_ACHAT = new javax.swing.JTextField();
        jLabel52 = new javax.swing.JLabel();
        CBX_FOURNISSEUR_ACHAT = new javax.swing.JComboBox<>();
        jLabel53 = new javax.swing.JLabel();
        CBX_PRODUIT_ACHAT = new javax.swing.JComboBox<>();
        jLabel54 = new javax.swing.JLabel();
        TXT_MARQUE_ACHAT = new javax.swing.JTextField();
        jLabel55 = new javax.swing.JLabel();
        TXT_UNITE_ACHAT = new javax.swing.JTextField();
        jLabel56 = new javax.swing.JLabel();
        TXT_PRIX_ACHAT = new javax.swing.JTextField();
        BTN_AJOUT_ACHAT = new javax.swing.JButton();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        TXT_QUANTITE_ACHAT = new javax.swing.JTextField();
        jLabel59 = new javax.swing.JLabel();
        TXT_PRODUIT_ACHAT = new javax.swing.JTextField();
        BTN_SUPPRIMER_ACHAT = new javax.swing.JButton();
        CBX_DATE_ACHAT = new com.toedter.calendar.JDateChooser();
        jScrollPane8 = new javax.swing.JScrollPane();
        TABLE_ACHAT = new javax.swing.JTable();
        jLabel60 = new javax.swing.JLabel();
        LABEL_MONTANTHT_ACHAT = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        LABEL_MONTANTTTC_ACHAT = new javax.swing.JLabel();
        jPanel21 = new javax.swing.JPanel();
        BTN_VALIDER_ACHAT = new javax.swing.JButton();
        BTN_ACTUALISER_ACHAT = new javax.swing.JButton();
        BTN_IMPRIMER_ACHAT = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane12 = new javax.swing.JScrollPane();
        TABLE_ACHAT_MODIFIER = new javax.swing.JTable();
        jPanel23 = new javax.swing.JPanel();
        jLabel69 = new javax.swing.JLabel();
        TXT_RECHERCHE_ACHAT = new javax.swing.JTextField();
        jLabel70 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        CheckBox_NUM_ACHAT = new javax.swing.JCheckBox();
        CheckBox_DATE_ACHAT = new javax.swing.JCheckBox();
        jLabel72 = new javax.swing.JLabel();
        CBX_FOURNISSEUR_RECHERCHE_ACHAT = new javax.swing.JComboBox<>();
        BTN_RECHERCHE_ACHAT = new javax.swing.JButton();
        jLabel82 = new javax.swing.JLabel();
        CBX_DATE_ACHAT_RECHERCHE = new com.toedter.calendar.JDateChooser();
        jPanel25 = new javax.swing.JPanel();
        jLabel73 = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        TXT_NUM_ACHAT_MODIFIER = new javax.swing.JTextField();
        jLabel75 = new javax.swing.JLabel();
        TXT_QUANTITE_ACHAT_MODIFIER = new javax.swing.JTextField();
        jLabel76 = new javax.swing.JLabel();
        TXT_PRODUIT_ACHAT_MODIFIER = new javax.swing.JTextField();
        jLabel77 = new javax.swing.JLabel();
        TXT_MARQUE_ACHAT_MODIFIER = new javax.swing.JTextField();
        jLabel78 = new javax.swing.JLabel();
        TXT_UNITE_ACHAT_MODIFIER = new javax.swing.JTextField();
        jLabel79 = new javax.swing.JLabel();
        TXT_PRIX_ACHAT_MODIFIER = new javax.swing.JTextField();
        jLabel80 = new javax.swing.JLabel();
        TXT_MONTANT_ACHAT_MODIFIER = new javax.swing.JTextField();
        jLabel81 = new javax.swing.JLabel();
        TXT_FOURNISSEUR_ACHAT_MODIFIER = new javax.swing.JTextField();
        CBX_TYPE_MODIFIER_ACHAT = new javax.swing.JComboBox<>();
        jLabel83 = new javax.swing.JLabel();
        ID = new javax.swing.JLabel();
        CBX_DATE_ACHAT_MODIFIER = new com.toedter.calendar.JDateChooser();
        BTN_MODIFIER_ACHAT = new javax.swing.JButton();
        BTN_SUPPRIMER_ACHATOFF = new javax.swing.JButton();
        CHECKBOX_SUPP_PRODUIT_ACHAT = new javax.swing.JCheckBox();
        CHECKBOX_SUPP_NUM_ACHAT = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1385, 735));

        jTabbedPane1.setBackground(new java.awt.Color(204, 204, 204));

        ACCEUIL.setBackground(new java.awt.Color(204, 204, 204));

        jPanel16.setBackground(new java.awt.Color(204, 204, 204));
        jPanel16.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        TABLE_STOCK.setBackground(new java.awt.Color(0, 204, 204));
        TABLE_STOCK.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Type Op", "Num", "Type Partenaire", "ID", "Nom Partenaire", "Produit", "Qte", "date"
            }
        ));
        jScrollPane10.setViewportView(TABLE_STOCK);

        jLabel68.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        jLabel68.setForeground(new java.awt.Color(51, 51, 51));
        jLabel68.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel68.setText(" MOUVEMENT DE STOCK ET ALERTE");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane10)
                .addContainerGap())
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(500, 500, 500)
                .addComponent(jLabel68, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(184, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addComponent(jLabel68)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        TABLE_ALERTE_STOCK.setBackground(new java.awt.Color(0, 204, 204));
        TABLE_ALERTE_STOCK.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5"
            }
        ));
        jScrollPane13.setViewportView(TABLE_ALERTE_STOCK);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane13)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane13, javax.swing.GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel5.setBackground(new java.awt.Color(204, 204, 204));
        jPanel5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        BTN_OPEN_PARAMETRE.setBackground(new java.awt.Color(255, 255, 0));
        BTN_OPEN_PARAMETRE.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        BTN_OPEN_PARAMETRE.setForeground(new java.awt.Color(51, 51, 51));
        BTN_OPEN_PARAMETRE.setText("Parametrer");
        BTN_OPEN_PARAMETRE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_OPEN_PARAMETREActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("Parametrage");

        BTN_OPEN_PARAMETRE1.setBackground(new java.awt.Color(255, 255, 0));
        BTN_OPEN_PARAMETRE1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        BTN_OPEN_PARAMETRE1.setForeground(new java.awt.Color(51, 51, 51));
        BTN_OPEN_PARAMETRE1.setText("Situation client");
        BTN_OPEN_PARAMETRE1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_OPEN_PARAMETRE1ActionPerformed(evt);
            }
        });

        BTN_OPEN_PARAMETRE2.setBackground(new java.awt.Color(255, 255, 0));
        BTN_OPEN_PARAMETRE2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        BTN_OPEN_PARAMETRE2.setForeground(new java.awt.Color(51, 51, 51));
        BTN_OPEN_PARAMETRE2.setText("Situation Fournisseur");
        BTN_OPEN_PARAMETRE2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_OPEN_PARAMETRE2ActionPerformed(evt);
            }
        });

        jLabel32.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(0, 0, 0));
        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel32.setText("Situation");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(57, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(72, 72, 72))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(72, 72, 72))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(BTN_OPEN_PARAMETRE1, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BTN_OPEN_PARAMETRE, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BTN_OPEN_PARAMETRE2))
                        .addGap(50, 50, 50))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel16)
                .addGap(18, 18, 18)
                .addComponent(BTN_OPEN_PARAMETRE)
                .addGap(18, 18, 18)
                .addComponent(jLabel32)
                .addGap(18, 18, 18)
                .addComponent(BTN_OPEN_PARAMETRE1)
                .addGap(18, 18, 18)
                .addComponent(BTN_OPEN_PARAMETRE2)
                .addContainerGap(90, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout ACCEUILLayout = new javax.swing.GroupLayout(ACCEUIL);
        ACCEUIL.setLayout(ACCEUILLayout);
        ACCEUILLayout.setHorizontalGroup(
            ACCEUILLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ACCEUILLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ACCEUILLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        ACCEUILLayout.setVerticalGroup(
            ACCEUILLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ACCEUILLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ACCEUILLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(40, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Acceuil", ACCEUIL);

        PRODUIT.setBackground(new java.awt.Color(204, 204, 204));

        jPanel11.setBackground(new java.awt.Color(204, 204, 204));
        jPanel11.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel22.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(0, 0, 204));
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel22.setText("ID :");

        TXT_ID_PRODUIT.setBackground(new java.awt.Color(255, 255, 255));
        TXT_ID_PRODUIT.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        TXT_ID_PRODUIT.setForeground(new java.awt.Color(51, 51, 51));
        TXT_ID_PRODUIT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TXT_ID_PRODUITActionPerformed(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(0, 0, 204));
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel23.setText("PRODUIT :");

        TXT_PRODUIT.setBackground(new java.awt.Color(255, 255, 255));
        TXT_PRODUIT.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        TXT_PRODUIT.setForeground(new java.awt.Color(51, 51, 51));

        jLabel24.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(0, 0, 204));
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel24.setText("MARQUE :");

        TXT_MARQUE.setBackground(new java.awt.Color(255, 255, 255));
        TXT_MARQUE.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        TXT_MARQUE.setForeground(new java.awt.Color(51, 51, 51));

        jLabel25.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(0, 0, 204));
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel25.setText("UNITE :");

        TXT_UNITE_PRODUIT.setBackground(new java.awt.Color(255, 255, 255));
        TXT_UNITE_PRODUIT.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        TXT_UNITE_PRODUIT.setForeground(new java.awt.Color(51, 51, 51));

        TABLE_PRODUIT.setBackground(new java.awt.Color(0, 204, 204));
        TABLE_PRODUIT.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        TABLE_PRODUIT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "PRODUIT", "MARQUE", "UNITE"
            }
        ));
        TABLE_PRODUIT.setShowGrid(true);
        TABLE_PRODUIT.setShowHorizontalLines(false);
        jScrollPane3.setViewportView(TABLE_PRODUIT);

        jLabel26.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(0, 0, 0));
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setText("AJOUTER UN PRODUIT");

        BTN_AJOUT_PRODUIT.setBackground(new java.awt.Color(0, 255, 0));
        BTN_AJOUT_PRODUIT.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        BTN_AJOUT_PRODUIT.setForeground(new java.awt.Color(0, 0, 0));
        BTN_AJOUT_PRODUIT.setText("AJOUTER");
        BTN_AJOUT_PRODUIT.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        BTN_AJOUT_PRODUIT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_AJOUT_PRODUITActionPerformed(evt);
            }
        });

        BTN_MODIFIER_PRODUIT.setBackground(new java.awt.Color(255, 255, 0));
        BTN_MODIFIER_PRODUIT.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        BTN_MODIFIER_PRODUIT.setForeground(new java.awt.Color(0, 0, 0));
        BTN_MODIFIER_PRODUIT.setText("MODIFIER");
        BTN_MODIFIER_PRODUIT.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        BTN_MODIFIER_PRODUIT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_MODIFIER_PRODUITActionPerformed(evt);
            }
        });

        BTN_SUPPRIMER_PRODUIT.setBackground(new java.awt.Color(255, 0, 0));
        BTN_SUPPRIMER_PRODUIT.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        BTN_SUPPRIMER_PRODUIT.setForeground(new java.awt.Color(0, 0, 0));
        BTN_SUPPRIMER_PRODUIT.setText("SUPPRIMER");
        BTN_SUPPRIMER_PRODUIT.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        BTN_SUPPRIMER_PRODUIT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_SUPPRIMER_PRODUITActionPerformed(evt);
            }
        });

        BTN_ACTUALISER_PRODUIT.setBackground(new java.awt.Color(255, 255, 255));
        BTN_ACTUALISER_PRODUIT.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        BTN_ACTUALISER_PRODUIT.setForeground(new java.awt.Color(255, 255, 0));
        BTN_ACTUALISER_PRODUIT.setIcon(new javax.swing.ImageIcon("C:\\Users\\Dual Computer\\Documents\\NetBeansProjects\\gestion1\\src\\main\\resources\\icon\\actualiser.png")); // NOI18N
        BTN_ACTUALISER_PRODUIT.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        BTN_ACTUALISER_PRODUIT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_ACTUALISER_PRODUITActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(jLabel25, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(TXT_ID_PRODUIT, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(TXT_UNITE_PRODUIT, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(TXT_MARQUE)
                                    .addComponent(TXT_PRODUIT, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(35, 35, 35))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                                .addComponent(BTN_SUPPRIMER_PRODUIT, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(BTN_MODIFIER_PRODUIT, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)))
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(BTN_AJOUT_PRODUIT)
                            .addComponent(BTN_ACTUALISER_PRODUIT))))
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TXT_ID_PRODUIT, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(BTN_ACTUALISER_PRODUIT, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(TXT_PRODUIT, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                        .addGap(4, 4, 4)))
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TXT_MARQUE, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TXT_UNITE_PRODUIT, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BTN_MODIFIER_PRODUIT)
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(BTN_AJOUT_PRODUIT)
                        .addComponent(BTN_SUPPRIMER_PRODUIT)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 361, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel13.setBackground(new java.awt.Color(204, 204, 204));
        jPanel13.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel27.setBackground(new java.awt.Color(153, 153, 153));
        jLabel27.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(0, 0, 0));
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel27.setText("AJOUTER PRIX POUR CLIENT");

        jLabel28.setBackground(new java.awt.Color(153, 153, 153));
        jLabel28.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(0, 0, 204));
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel28.setText("CLIENT :");

        CBX_CLIENT_PRIX.setBackground(new java.awt.Color(102, 153, 255));
        CBX_CLIENT_PRIX.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        CBX_CLIENT_PRIX.setForeground(new java.awt.Color(0, 0, 0));
        CBX_CLIENT_PRIX.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel29.setBackground(new java.awt.Color(153, 153, 153));
        jLabel29.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(0, 0, 204));
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel29.setText("PRODUIT :");

        CBX_PRODUIT_PRIX.setBackground(new java.awt.Color(102, 153, 255));
        CBX_PRODUIT_PRIX.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        CBX_PRODUIT_PRIX.setForeground(new java.awt.Color(0, 0, 0));
        CBX_PRODUIT_PRIX.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel30.setBackground(new java.awt.Color(153, 153, 153));
        jLabel30.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(0, 0, 204));
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel30.setText("PRIX/U :");

        TXT_PRIX_CLIENT.setBackground(new java.awt.Color(255, 255, 255));
        TXT_PRIX_CLIENT.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        TXT_PRIX_CLIENT.setForeground(new java.awt.Color(51, 51, 51));

        BTN_AJOUT_PRIX.setBackground(new java.awt.Color(0, 255, 0));
        BTN_AJOUT_PRIX.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        BTN_AJOUT_PRIX.setForeground(new java.awt.Color(0, 0, 0));
        BTN_AJOUT_PRIX.setText("AJOUTER");
        BTN_AJOUT_PRIX.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        BTN_AJOUT_PRIX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_AJOUT_PRIXActionPerformed(evt);
            }
        });

        TABLE_CLIENT_PRIX.setBackground(new java.awt.Color(0, 204, 204));
        TABLE_CLIENT_PRIX.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        TABLE_CLIENT_PRIX.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "PRODUIT", "MARQUE", "UNITE", "PRIX/U", "CLIENT"
            }
        ));
        TABLE_CLIENT_PRIX.setShowGrid(true);
        TABLE_CLIENT_PRIX.setShowHorizontalLines(false);
        jScrollPane4.setViewportView(TABLE_CLIENT_PRIX);

        BTN_MODIFIER_PRIX.setBackground(new java.awt.Color(255, 255, 0));
        BTN_MODIFIER_PRIX.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        BTN_MODIFIER_PRIX.setForeground(new java.awt.Color(0, 0, 0));
        BTN_MODIFIER_PRIX.setText("MODIFIER");
        BTN_MODIFIER_PRIX.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        BTN_MODIFIER_PRIX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_MODIFIER_PRIXActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel13Layout.createSequentialGroup()
                            .addComponent(BTN_MODIFIER_PRIX, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(BTN_AJOUT_PRIX))
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel13Layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(jLabel30)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(TXT_PRIX_CLIENT))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel13Layout.createSequentialGroup()
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(CBX_CLIENT_PRIX, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(CBX_PRODUIT_PRIX, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 602, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jLabel27)
                        .addGap(39, 39, 39)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CBX_CLIENT_PRIX))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(CBX_PRODUIT_PRIX, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(TXT_PRIX_CLIENT, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(BTN_AJOUT_PRIX)
                            .addComponent(BTN_MODIFIER_PRIX))
                        .addGap(83, 83, 83))))
        );

        jPanel14.setBackground(new java.awt.Color(204, 204, 204));
        jPanel14.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel35.setBackground(new java.awt.Color(153, 153, 153));
        jLabel35.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(0, 0, 0));
        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel35.setText("AJOUTER PRIX POUR FOURNI");

        jLabel36.setBackground(new java.awt.Color(153, 153, 153));
        jLabel36.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(0, 0, 204));
        jLabel36.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel36.setText("FOURNI :");

        CBX_FOURNISSEUR_PRIX.setBackground(new java.awt.Color(102, 153, 255));
        CBX_FOURNISSEUR_PRIX.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        CBX_FOURNISSEUR_PRIX.setForeground(new java.awt.Color(0, 0, 0));
        CBX_FOURNISSEUR_PRIX.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel37.setBackground(new java.awt.Color(153, 153, 153));
        jLabel37.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(0, 0, 204));
        jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel37.setText("PRODUIT :");

        CBX_PRODUIT_PRIXF.setBackground(new java.awt.Color(102, 153, 255));
        CBX_PRODUIT_PRIXF.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        CBX_PRODUIT_PRIXF.setForeground(new java.awt.Color(0, 0, 0));
        CBX_PRODUIT_PRIXF.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel38.setBackground(new java.awt.Color(153, 153, 153));
        jLabel38.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(0, 0, 204));
        jLabel38.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel38.setText("PRIX/U :");

        TXT_PRIX_FOURNISSEUR.setBackground(new java.awt.Color(255, 255, 255));
        TXT_PRIX_FOURNISSEUR.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        TXT_PRIX_FOURNISSEUR.setForeground(new java.awt.Color(51, 51, 51));

        BTN_AJOUT_PRIXF.setBackground(new java.awt.Color(0, 255, 0));
        BTN_AJOUT_PRIXF.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        BTN_AJOUT_PRIXF.setForeground(new java.awt.Color(0, 0, 0));
        BTN_AJOUT_PRIXF.setText("AJOUTER");
        BTN_AJOUT_PRIXF.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        BTN_AJOUT_PRIXF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_AJOUT_PRIXFActionPerformed(evt);
            }
        });

        TABLE_FOURNISSEUR_PRIX.setBackground(new java.awt.Color(0, 204, 204));
        TABLE_FOURNISSEUR_PRIX.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        TABLE_FOURNISSEUR_PRIX.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "PRODUIT", "MARQUE", "UNITE", "PRIX/U", "FOURNISSEUR"
            }
        ));
        TABLE_FOURNISSEUR_PRIX.setShowGrid(true);
        TABLE_FOURNISSEUR_PRIX.setShowHorizontalLines(false);
        jScrollPane5.setViewportView(TABLE_FOURNISSEUR_PRIX);

        BTN_MODIFIER_PRIXF.setBackground(new java.awt.Color(255, 255, 0));
        BTN_MODIFIER_PRIXF.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        BTN_MODIFIER_PRIXF.setForeground(new java.awt.Color(0, 0, 0));
        BTN_MODIFIER_PRIXF.setText("MODIFIER");
        BTN_MODIFIER_PRIXF.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        BTN_MODIFIER_PRIXF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_MODIFIER_PRIXFActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel14Layout.createSequentialGroup()
                            .addComponent(BTN_MODIFIER_PRIXF, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(BTN_AJOUT_PRIXF))
                        .addGroup(jPanel14Layout.createSequentialGroup()
                            .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel38)
                                .addComponent(jLabel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(TXT_PRIX_FOURNISSEUR, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(CBX_FOURNISSEUR_PRIX, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(CBX_PRODUIT_PRIXF, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 602, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabel35)
                        .addGap(39, 39, 39)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CBX_FOURNISSEUR_PRIX))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(CBX_PRODUIT_PRIXF, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                            .addComponent(jLabel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TXT_PRIX_FOURNISSEUR, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel38, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(BTN_AJOUT_PRIXF)
                            .addComponent(BTN_MODIFIER_PRIXF)))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout PRODUITLayout = new javax.swing.GroupLayout(PRODUIT);
        PRODUIT.setLayout(PRODUITLayout);
        PRODUITLayout.setHorizontalGroup(
            PRODUITLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PRODUITLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(PRODUITLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        PRODUITLayout.setVerticalGroup(
            PRODUITLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PRODUITLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PRODUITLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(PRODUITLayout.createSequentialGroup()
                        .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Produit", PRODUIT);

        CLIENT.setBackground(new java.awt.Color(204, 204, 204));

        jLabel7.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 51, 51));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("AJOUTER UN CLIENT");

        jLabel8.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(51, 51, 51));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("LISTES DES CLIENTS");

        TXT_RECHERCHE_CLIENT.setBackground(new java.awt.Color(255, 255, 255));
        TXT_RECHERCHE_CLIENT.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        TXT_RECHERCHE_CLIENT.setForeground(new java.awt.Color(51, 51, 51));

        jLabel9.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 204));
        jLabel9.setText("RECHERCHE :");

        TABLE_CLIENT.setBackground(new java.awt.Color(0, 204, 204));
        TABLE_CLIENT.setForeground(new java.awt.Color(51, 51, 51));
        TABLE_CLIENT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "NOM", "PRENOM", "ADRESSE", "EMAIL", "TELEPHONE", "CNR", "NIF"
            }
        ));
        TABLE_CLIENT.setShowGrid(true);
        TABLE_CLIENT.setShowHorizontalLines(false);
        jScrollPane1.setViewportView(TABLE_CLIENT);

        jPanel6.setBackground(new java.awt.Color(204, 204, 204));
        jPanel6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 204));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("ID :");

        TXT_ID_CLIENT.setBackground(new java.awt.Color(255, 255, 255));
        TXT_ID_CLIENT.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        TXT_ID_CLIENT.setForeground(new java.awt.Color(51, 51, 51));

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 204));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("NOM :");

        TXT_NOM_CLIENT.setBackground(new java.awt.Color(255, 255, 255));
        TXT_NOM_CLIENT.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        TXT_NOM_CLIENT.setForeground(new java.awt.Color(51, 51, 51));

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 204));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("PRENOM :");

        TXT_PRENOM_CLIENT.setBackground(new java.awt.Color(255, 255, 255));
        TXT_PRENOM_CLIENT.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        TXT_PRENOM_CLIENT.setForeground(new java.awt.Color(51, 51, 51));

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 204));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("ADRESSE :");

        TXT_ADRESSE_CLIENT.setBackground(new java.awt.Color(255, 255, 255));
        TXT_ADRESSE_CLIENT.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        TXT_ADRESSE_CLIENT.setForeground(new java.awt.Color(51, 51, 51));

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 204));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("EMAIL :");

        TXT_EMAIL_CLIENT.setBackground(new java.awt.Color(255, 255, 255));
        TXT_EMAIL_CLIENT.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        TXT_EMAIL_CLIENT.setForeground(new java.awt.Color(51, 51, 51));

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 204));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("TELEPHONE :");

        TXT_TELEPHONE_CLIENT.setBackground(new java.awt.Color(255, 255, 255));
        TXT_TELEPHONE_CLIENT.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        TXT_TELEPHONE_CLIENT.setForeground(new java.awt.Color(51, 51, 51));

        BTN_AJOUT_CLIENT.setBackground(new java.awt.Color(0, 204, 51));
        BTN_AJOUT_CLIENT.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        BTN_AJOUT_CLIENT.setForeground(new java.awt.Color(51, 51, 51));
        BTN_AJOUT_CLIENT.setText("AJOUTER");
        BTN_AJOUT_CLIENT.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        BTN_AJOUT_CLIENT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_AJOUT_CLIENTActionPerformed(evt);
            }
        });

        BTN_SUPPRIMER_CLIENT.setBackground(new java.awt.Color(255, 0, 51));
        BTN_SUPPRIMER_CLIENT.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        BTN_SUPPRIMER_CLIENT.setForeground(new java.awt.Color(51, 51, 51));
        BTN_SUPPRIMER_CLIENT.setText("SUPPRIMER");
        BTN_SUPPRIMER_CLIENT.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        BTN_SUPPRIMER_CLIENT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_SUPPRIMER_CLIENTActionPerformed(evt);
            }
        });

        BTN_MODIFIER_CLIENT.setBackground(new java.awt.Color(255, 255, 0));
        BTN_MODIFIER_CLIENT.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        BTN_MODIFIER_CLIENT.setForeground(new java.awt.Color(51, 51, 51));
        BTN_MODIFIER_CLIENT.setText("MODIFIER");
        BTN_MODIFIER_CLIENT.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        BTN_MODIFIER_CLIENT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_MODIFIER_CLIENTActionPerformed(evt);
            }
        });

        BTN_ACTUALISER_CLIENT.setBackground(new java.awt.Color(255, 255, 255));
        BTN_ACTUALISER_CLIENT.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        BTN_ACTUALISER_CLIENT.setForeground(new java.awt.Color(255, 255, 0));
        BTN_ACTUALISER_CLIENT.setIcon(new javax.swing.ImageIcon("C:\\Users\\Dual Computer\\Documents\\NetBeansProjects\\gestion1\\src\\main\\resources\\icon\\actualiser.png")); // NOI18N
        BTN_ACTUALISER_CLIENT.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        BTN_ACTUALISER_CLIENT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_ACTUALISER_CLIENTActionPerformed(evt);
            }
        });

        jLabel62.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel62.setForeground(new java.awt.Color(0, 0, 204));
        jLabel62.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel62.setText("CNR :");

        TXT_CNR_CLIENT.setBackground(new java.awt.Color(255, 255, 255));
        TXT_CNR_CLIENT.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        TXT_CNR_CLIENT.setForeground(new java.awt.Color(51, 51, 51));

        jLabel63.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel63.setForeground(new java.awt.Color(0, 0, 204));
        jLabel63.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel63.setText("NIF :");

        TXT_NIF_CLIENT.setBackground(new java.awt.Color(255, 255, 255));
        TXT_NIF_CLIENT.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        TXT_NIF_CLIENT.setForeground(new java.awt.Color(51, 51, 51));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(BTN_SUPPRIMER_CLIENT, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(BTN_MODIFIER_CLIENT, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(BTN_AJOUT_CLIENT, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addGap(19, 19, 19)
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addComponent(jLabel4))
                                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(37, 37, 37)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(TXT_ID_CLIENT, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(BTN_ACTUALISER_CLIENT)
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(TXT_TELEPHONE_CLIENT, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(TXT_NOM_CLIENT, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(TXT_PRENOM_CLIENT, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(TXT_ADRESSE_CLIENT, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(TXT_EMAIL_CLIENT, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel62, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(37, 37, 37)
                                .addComponent(TXT_CNR_CLIENT, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel63, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(37, 37, 37)
                                .addComponent(TXT_NIF_CLIENT, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(14, Short.MAX_VALUE))))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(BTN_ACTUALISER_CLIENT, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TXT_ID_CLIENT, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TXT_NOM_CLIENT, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TXT_PRENOM_CLIENT, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(TXT_ADRESSE_CLIENT, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TXT_EMAIL_CLIENT, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TXT_TELEPHONE_CLIENT, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel62, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TXT_CNR_CLIENT, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel63, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TXT_NIF_CLIENT, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(47, 47, 47)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BTN_AJOUT_CLIENT)
                    .addComponent(BTN_SUPPRIMER_CLIENT)
                    .addComponent(BTN_MODIFIER_CLIENT))
                .addContainerGap(59, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout CLIENTLayout = new javax.swing.GroupLayout(CLIENT);
        CLIENT.setLayout(CLIENTLayout);
        CLIENTLayout.setHorizontalGroup(
            CLIENTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CLIENTLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(CLIENTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(CLIENTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 947, Short.MAX_VALUE)
                    .addGroup(CLIENTLayout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TXT_RECHERCHE_CLIENT, javax.swing.GroupLayout.PREFERRED_SIZE, 391, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        CLIENTLayout.setVerticalGroup(
            CLIENTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CLIENTLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(CLIENTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(CLIENTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(TXT_RECHERCHE_CLIENT, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(CLIENTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Client", CLIENT);

        FOURNISSEUR.setBackground(new java.awt.Color(204, 204, 204));

        jLabel43.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(51, 51, 51));
        jLabel43.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel43.setText("AJOUTER UN FOURNISSEUR");

        jLabel44.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(51, 51, 51));
        jLabel44.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel44.setText("LISTES DES FOURNISSEURS");

        jLabel45.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(0, 0, 204));
        jLabel45.setText("RECHERCHE :");

        TXT_RECHERCHE_FOURNISSEUR.setBackground(new java.awt.Color(255, 255, 255));
        TXT_RECHERCHE_FOURNISSEUR.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        TXT_RECHERCHE_FOURNISSEUR.setForeground(new java.awt.Color(51, 51, 51));

        jPanel19.setBackground(new java.awt.Color(204, 204, 204));
        jPanel19.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel46.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(0, 0, 204));
        jLabel46.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel46.setText("ID :");

        TXT_ID_FOURNISSEUR.setBackground(new java.awt.Color(255, 255, 255));
        TXT_ID_FOURNISSEUR.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        TXT_ID_FOURNISSEUR.setForeground(new java.awt.Color(51, 51, 51));

        jLabel47.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(0, 0, 204));
        jLabel47.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel47.setText("FOURNI:");

        TXT_NOM_FOURNISSEUR.setBackground(new java.awt.Color(255, 255, 255));
        TXT_NOM_FOURNISSEUR.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        TXT_NOM_FOURNISSEUR.setForeground(new java.awt.Color(51, 51, 51));

        jLabel49.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(0, 0, 204));
        jLabel49.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel49.setText("ADRESSE :");

        TXT_ADRESSE_FOURNISSEUR.setBackground(new java.awt.Color(255, 255, 255));
        TXT_ADRESSE_FOURNISSEUR.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        TXT_ADRESSE_FOURNISSEUR.setForeground(new java.awt.Color(51, 51, 51));

        jLabel50.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel50.setForeground(new java.awt.Color(0, 0, 204));
        jLabel50.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel50.setText("EMAIL :");

        TXT_EMAIL_FOURNISSEUR.setBackground(new java.awt.Color(255, 255, 255));
        TXT_EMAIL_FOURNISSEUR.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        TXT_EMAIL_FOURNISSEUR.setForeground(new java.awt.Color(51, 51, 51));

        jLabel51.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel51.setForeground(new java.awt.Color(0, 0, 204));
        jLabel51.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel51.setText("TELEPHONE :");

        TXT_TELEPHONE_FOURNISSEUR.setBackground(new java.awt.Color(255, 255, 255));
        TXT_TELEPHONE_FOURNISSEUR.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        TXT_TELEPHONE_FOURNISSEUR.setForeground(new java.awt.Color(51, 51, 51));

        BTN_AJOUT_FOURNISSEUR.setBackground(new java.awt.Color(0, 204, 51));
        BTN_AJOUT_FOURNISSEUR.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        BTN_AJOUT_FOURNISSEUR.setForeground(new java.awt.Color(51, 51, 51));
        BTN_AJOUT_FOURNISSEUR.setText("AJOUTER");
        BTN_AJOUT_FOURNISSEUR.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        BTN_AJOUT_FOURNISSEUR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_AJOUT_FOURNISSEURActionPerformed(evt);
            }
        });

        BTN_SUPPRIMER_FOURNISSEUR.setBackground(new java.awt.Color(255, 0, 51));
        BTN_SUPPRIMER_FOURNISSEUR.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        BTN_SUPPRIMER_FOURNISSEUR.setForeground(new java.awt.Color(51, 51, 51));
        BTN_SUPPRIMER_FOURNISSEUR.setText("SUPPRIMER");
        BTN_SUPPRIMER_FOURNISSEUR.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        BTN_SUPPRIMER_FOURNISSEUR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_SUPPRIMER_FOURNISSEURActionPerformed(evt);
            }
        });

        BTN_MODIFIER_FOURNISSEUR.setBackground(new java.awt.Color(255, 255, 0));
        BTN_MODIFIER_FOURNISSEUR.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        BTN_MODIFIER_FOURNISSEUR.setForeground(new java.awt.Color(51, 51, 51));
        BTN_MODIFIER_FOURNISSEUR.setText("MODIFIER");
        BTN_MODIFIER_FOURNISSEUR.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        BTN_MODIFIER_FOURNISSEUR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_MODIFIER_FOURNISSEURActionPerformed(evt);
            }
        });

        BTN_ACTUALISER_CLIENT1.setBackground(new java.awt.Color(255, 255, 255));
        BTN_ACTUALISER_CLIENT1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        BTN_ACTUALISER_CLIENT1.setForeground(new java.awt.Color(255, 255, 0));
        BTN_ACTUALISER_CLIENT1.setIcon(new javax.swing.ImageIcon("C:\\Users\\Dual Computer\\Documents\\NetBeansProjects\\gestion1\\src\\main\\resources\\icon\\actualiser.png")); // NOI18N
        BTN_ACTUALISER_CLIENT1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        BTN_ACTUALISER_CLIENT1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_ACTUALISER_CLIENT1ActionPerformed(evt);
            }
        });

        jLabel64.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel64.setForeground(new java.awt.Color(0, 0, 204));
        jLabel64.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel64.setText("CNR :");

        TXT_CNR_FOURNISSEUR.setBackground(new java.awt.Color(255, 255, 255));
        TXT_CNR_FOURNISSEUR.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        TXT_CNR_FOURNISSEUR.setForeground(new java.awt.Color(51, 51, 51));

        jLabel65.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel65.setForeground(new java.awt.Color(0, 0, 204));
        jLabel65.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel65.setText("NIF :");

        TXT_NIF_FOURNISSEUR.setBackground(new java.awt.Color(255, 255, 255));
        TXT_NIF_FOURNISSEUR.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        TXT_NIF_FOURNISSEUR.setForeground(new java.awt.Color(51, 51, 51));

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BTN_ACTUALISER_CLIENT1))
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addGap(86, 86, 86)
                        .addComponent(BTN_SUPPRIMER_FOURNISSEUR, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(BTN_MODIFIER_FOURNISSEUR, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(BTN_AJOUT_FOURNISSEUR, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel19Layout.createSequentialGroup()
                                    .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel19Layout.createSequentialGroup()
                                            .addGap(19, 19, 19)
                                            .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel49)
                                                .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(37, 37, 37)
                                    .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(TXT_TELEPHONE_FOURNISSEUR, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(TXT_ADRESSE_FOURNISSEUR, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(TXT_EMAIL_FOURNISSEUR, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                                    .addGap(19, 19, 19)
                                    .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(37, 37, 37)
                                    .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(TXT_NOM_FOURNISSEUR, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(TXT_ID_FOURNISSEUR, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(69, 69, 69)))
                            .addGroup(jPanel19Layout.createSequentialGroup()
                                .addComponent(jLabel64, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(37, 37, 37)
                                .addComponent(TXT_CNR_FOURNISSEUR, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel19Layout.createSequentialGroup()
                                .addComponent(jLabel65, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(37, 37, 37)
                                .addComponent(TXT_NIF_FOURNISSEUR, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(BTN_ACTUALISER_CLIENT1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TXT_ID_FOURNISSEUR, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(TXT_NOM_FOURNISSEUR, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel49, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(TXT_ADRESSE_FOURNISSEUR, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TXT_EMAIL_FOURNISSEUR, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TXT_TELEPHONE_FOURNISSEUR, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel64, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TXT_CNR_FOURNISSEUR, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel65, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TXT_NIF_FOURNISSEUR, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 75, Short.MAX_VALUE)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BTN_AJOUT_FOURNISSEUR)
                    .addComponent(BTN_SUPPRIMER_FOURNISSEUR)
                    .addComponent(BTN_MODIFIER_FOURNISSEUR))
                .addGap(37, 37, 37))
        );

        TABLE_FOURNISSEUR.setBackground(new java.awt.Color(0, 204, 204));
        TABLE_FOURNISSEUR.setForeground(new java.awt.Color(51, 51, 51));
        TABLE_FOURNISSEUR.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "FOURNISSEUR", "ADRESSE", "EMAIL", "TELEPHONE", "CNR", "NIF"
            }
        ));
        TABLE_FOURNISSEUR.setShowGrid(true);
        TABLE_FOURNISSEUR.setShowHorizontalLines(false);
        jScrollPane9.setViewportView(TABLE_FOURNISSEUR);

        javax.swing.GroupLayout FOURNISSEURLayout = new javax.swing.GroupLayout(FOURNISSEUR);
        FOURNISSEUR.setLayout(FOURNISSEURLayout);
        FOURNISSEURLayout.setHorizontalGroup(
            FOURNISSEURLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FOURNISSEURLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(FOURNISSEURLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(FOURNISSEURLayout.createSequentialGroup()
                        .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(FOURNISSEURLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(FOURNISSEURLayout.createSequentialGroup()
                                .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TXT_RECHERCHE_FOURNISSEUR, javax.swing.GroupLayout.PREFERRED_SIZE, 391, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(479, Short.MAX_VALUE))
                            .addComponent(jLabel44, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(FOURNISSEURLayout.createSequentialGroup()
                        .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 980, Short.MAX_VALUE))))
        );
        FOURNISSEURLayout.setVerticalGroup(
            FOURNISSEURLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FOURNISSEURLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(FOURNISSEURLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(FOURNISSEURLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel45, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(TXT_RECHERCHE_FOURNISSEUR, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(FOURNISSEURLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane9))
                .addContainerGap(87, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Fournisseur", FOURNISSEUR);

        VERSEMENT.setBackground(new java.awt.Color(204, 204, 204));

        jPanel17.setBackground(new java.awt.Color(204, 204, 204));
        jPanel17.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel17.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 0, 204));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel17.setText("CLIENT :");

        jLabel33.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(0, 0, 204));
        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel33.setText("DATE :");

        jLabel34.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(0, 0, 204));
        jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel34.setText("MONTANT :");

        CBX_CLIENT_VERSEMENT.setBackground(new java.awt.Color(102, 153, 255));
        CBX_CLIENT_VERSEMENT.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        CBX_CLIENT_VERSEMENT.setForeground(new java.awt.Color(0, 0, 0));
        CBX_CLIENT_VERSEMENT.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        CBX_CLIENT_VERSEMENT.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                CBX_CLIENT_VERSEMENTItemStateChanged(evt);
            }
        });

        TXT_VERSEMENT_CLIENT.setBackground(new java.awt.Color(255, 255, 255));
        TXT_VERSEMENT_CLIENT.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        TXT_VERSEMENT_CLIENT.setForeground(new java.awt.Color(0, 0, 0));

        TABLE_CLIENT_VERSEMENT.setBackground(new java.awt.Color(0, 204, 204));
        TABLE_CLIENT_VERSEMENT.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        TABLE_CLIENT_VERSEMENT.setForeground(new java.awt.Color(0, 0, 0));
        TABLE_CLIENT_VERSEMENT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "VERSE N°", "ID", "CLIENT", "DATE", "VERSEMENT"
            }
        ));
        TABLE_CLIENT_VERSEMENT.setShowGrid(true);
        TABLE_CLIENT_VERSEMENT.setShowHorizontalLines(false);
        jScrollPane6.setViewportView(TABLE_CLIENT_VERSEMENT);

        BTN_AJOUT_VERS_CLIENT.setBackground(new java.awt.Color(0, 204, 102));
        BTN_AJOUT_VERS_CLIENT.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        BTN_AJOUT_VERS_CLIENT.setForeground(new java.awt.Color(0, 0, 0));
        BTN_AJOUT_VERS_CLIENT.setText("AJOUTER");
        BTN_AJOUT_VERS_CLIENT.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        BTN_AJOUT_VERS_CLIENT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_AJOUT_VERS_CLIENTActionPerformed(evt);
            }
        });

        BTN_MODIFIER_VERS_CLIENT.setBackground(new java.awt.Color(255, 255, 0));
        BTN_MODIFIER_VERS_CLIENT.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        BTN_MODIFIER_VERS_CLIENT.setForeground(new java.awt.Color(0, 0, 0));
        BTN_MODIFIER_VERS_CLIENT.setText("MODIFIER");
        BTN_MODIFIER_VERS_CLIENT.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        BTN_MODIFIER_VERS_CLIENT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_MODIFIER_VERS_CLIENTActionPerformed(evt);
            }
        });

        BTN_SUPPRIMER_VERS_CLIENT.setBackground(new java.awt.Color(255, 51, 51));
        BTN_SUPPRIMER_VERS_CLIENT.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        BTN_SUPPRIMER_VERS_CLIENT.setForeground(new java.awt.Color(0, 0, 0));
        BTN_SUPPRIMER_VERS_CLIENT.setText("SUPPRIMER");
        BTN_SUPPRIMER_VERS_CLIENT.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        BTN_SUPPRIMER_VERS_CLIENT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_SUPPRIMER_VERS_CLIENTActionPerformed(evt);
            }
        });

        jLabel42.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(0, 0, 204));
        jLabel42.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel42.setText("VERSE N° :");

        TXT_NUM_VERSEMENT_CLIENT.setBackground(new java.awt.Color(255, 255, 255));
        TXT_NUM_VERSEMENT_CLIENT.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        TXT_NUM_VERSEMENT_CLIENT.setForeground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                        .addComponent(TXT_VERSEMENT_CLIENT, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BTN_SUPPRIMER_VERS_CLIENT, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(BTN_MODIFIER_VERS_CLIENT, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(BTN_AJOUT_VERS_CLIENT, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(CBX_CLIENT_VERSEMENT, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel33)
                            .addComponent(CBX_DATE_VERSEMENT_CLIENT, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(56, 56, 56)
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TXT_NUM_VERSEMENT_CLIENT, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel42))))
                .addContainerGap())
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                .addContainerGap(26, Short.MAX_VALUE)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                            .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(CBX_CLIENT_VERSEMENT)
                            .addComponent(CBX_DATE_VERSEMENT_CLIENT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                        .addComponent(jLabel42, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TXT_NUM_VERSEMENT_CLIENT, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel34, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TXT_VERSEMENT_CLIENT, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BTN_AJOUT_VERS_CLIENT)
                    .addComponent(BTN_MODIFIER_VERS_CLIENT)
                    .addComponent(BTN_SUPPRIMER_VERS_CLIENT))
                .addGap(23, 23, 23)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 463, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel18.setBackground(new java.awt.Color(204, 204, 204));
        jPanel18.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        BTN_SUPPRIMER_VERS_FOURNISSEUR.setBackground(new java.awt.Color(255, 51, 51));
        BTN_SUPPRIMER_VERS_FOURNISSEUR.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        BTN_SUPPRIMER_VERS_FOURNISSEUR.setForeground(new java.awt.Color(0, 0, 0));
        BTN_SUPPRIMER_VERS_FOURNISSEUR.setText("SUPPRIMER");
        BTN_SUPPRIMER_VERS_FOURNISSEUR.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        BTN_SUPPRIMER_VERS_FOURNISSEUR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_SUPPRIMER_VERS_FOURNISSEURActionPerformed(evt);
            }
        });

        BTN_MODIFIER_VERS_FOURNISSEUR.setBackground(new java.awt.Color(255, 255, 0));
        BTN_MODIFIER_VERS_FOURNISSEUR.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        BTN_MODIFIER_VERS_FOURNISSEUR.setForeground(new java.awt.Color(0, 0, 0));
        BTN_MODIFIER_VERS_FOURNISSEUR.setText("MODIFIER");
        BTN_MODIFIER_VERS_FOURNISSEUR.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        BTN_MODIFIER_VERS_FOURNISSEUR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_MODIFIER_VERS_FOURNISSEURActionPerformed(evt);
            }
        });

        BTN_AJOUT_VERS_FOURNISSEUR.setBackground(new java.awt.Color(0, 204, 102));
        BTN_AJOUT_VERS_FOURNISSEUR.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        BTN_AJOUT_VERS_FOURNISSEUR.setForeground(new java.awt.Color(0, 0, 0));
        BTN_AJOUT_VERS_FOURNISSEUR.setText("AJOUTER");
        BTN_AJOUT_VERS_FOURNISSEUR.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        BTN_AJOUT_VERS_FOURNISSEUR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_AJOUT_VERS_FOURNISSEURActionPerformed(evt);
            }
        });

        jLabel39.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(0, 0, 204));
        jLabel39.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel39.setText("FOURNI :");

        CBX_FOURNISSEUR_VERSEMENT.setBackground(new java.awt.Color(102, 153, 255));
        CBX_FOURNISSEUR_VERSEMENT.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        CBX_FOURNISSEUR_VERSEMENT.setForeground(new java.awt.Color(0, 0, 0));
        CBX_FOURNISSEUR_VERSEMENT.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        CBX_FOURNISSEUR_VERSEMENT.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                CBX_FOURNISSEUR_VERSEMENTItemStateChanged(evt);
            }
        });
        CBX_FOURNISSEUR_VERSEMENT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CBX_FOURNISSEUR_VERSEMENTActionPerformed(evt);
            }
        });

        jLabel40.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(0, 0, 204));
        jLabel40.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel40.setText("DATE :");

        jLabel41.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(0, 0, 204));
        jLabel41.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel41.setText("MONTANT :");

        TXT_VERSEMENT_FOURNISSEUR.setBackground(new java.awt.Color(255, 255, 255));
        TXT_VERSEMENT_FOURNISSEUR.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        TXT_VERSEMENT_FOURNISSEUR.setForeground(new java.awt.Color(0, 0, 0));

        TABLE_FOURNISSEUR_VERSEMENT.setBackground(new java.awt.Color(0, 204, 204));
        TABLE_FOURNISSEUR_VERSEMENT.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        TABLE_FOURNISSEUR_VERSEMENT.setForeground(new java.awt.Color(0, 0, 0));
        TABLE_FOURNISSEUR_VERSEMENT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "VERSE N°", "ID", "FOURNISSEUR", "DATE", "VERSEMENT"
            }
        ));
        TABLE_FOURNISSEUR_VERSEMENT.setShowGrid(true);
        TABLE_FOURNISSEUR_VERSEMENT.setShowHorizontalLines(false);
        jScrollPane7.setViewportView(TABLE_FOURNISSEUR_VERSEMENT);

        jLabel66.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel66.setForeground(new java.awt.Color(0, 0, 204));
        jLabel66.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel66.setText("VERSE N° :");

        TXT_NUM_VERSEMENT_FOURNISSEUR.setBackground(new java.awt.Color(255, 255, 255));
        TXT_NUM_VERSEMENT_FOURNISSEUR.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        TXT_NUM_VERSEMENT_FOURNISSEUR.setForeground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel39)
                            .addComponent(CBX_FOURNISSEUR_VERSEMENT, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 105, Short.MAX_VALUE)
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel40)
                            .addComponent(CBX_DATE_VERSEMENT_FOURNISSEUR, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(51, 51, 51)
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TXT_NUM_VERSEMENT_FOURNISSEUR, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel66)))
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addComponent(TXT_VERSEMENT_FOURNISSEUR, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BTN_SUPPRIMER_VERS_FOURNISSEUR, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(BTN_MODIFIER_VERS_FOURNISSEUR, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(BTN_AJOUT_VERS_FOURNISSEUR, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel18Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 628, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                            .addComponent(jLabel40, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel39, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(CBX_FOURNISSEUR_VERSEMENT, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CBX_DATE_VERSEMENT_FOURNISSEUR, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addComponent(jLabel66, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TXT_NUM_VERSEMENT_FOURNISSEUR, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel41, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(BTN_AJOUT_VERS_FOURNISSEUR)
                        .addComponent(BTN_MODIFIER_VERS_FOURNISSEUR)
                        .addComponent(BTN_SUPPRIMER_VERS_FOURNISSEUR))
                    .addComponent(TXT_VERSEMENT_FOURNISSEUR, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(499, 499, 499))
            .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                    .addContainerGap(165, Short.MAX_VALUE)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 461, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );

        javax.swing.GroupLayout VERSEMENTLayout = new javax.swing.GroupLayout(VERSEMENT);
        VERSEMENT.setLayout(VERSEMENTLayout);
        VERSEMENTLayout.setHorizontalGroup(
            VERSEMENTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(VERSEMENTLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        VERSEMENTLayout.setVerticalGroup(
            VERSEMENTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(VERSEMENTLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(VERSEMENTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(54, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Versement", VERSEMENT);

        LIVRAISON.setBackground(new java.awt.Color(204, 204, 204));
        LIVRAISON.setForeground(new java.awt.Color(0, 0, 0));
        LIVRAISON.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        LIVRAISON.setOpaque(true);

        LIVRAI.setBackground(new java.awt.Color(204, 204, 204));

        jPanel7.setBackground(new java.awt.Color(204, 204, 204));
        jPanel7.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel10.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 204));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("NUM° :");

        TXT_NUM_LIVR.setBackground(new java.awt.Color(255, 255, 255));
        TXT_NUM_LIVR.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        TXT_NUM_LIVR.setForeground(new java.awt.Color(51, 51, 51));

        jLabel11.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 204));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel11.setText("CLIENT :");

        CBX_CLIENT_LIVR.setBackground(new java.awt.Color(102, 153, 255));
        CBX_CLIENT_LIVR.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        CBX_CLIENT_LIVR.setForeground(new java.awt.Color(51, 51, 51));
        CBX_CLIENT_LIVR.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        CBX_CLIENT_LIVR.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                CBX_CLIENT_LIVRItemStateChanged(evt);
            }
        });
        CBX_CLIENT_LIVR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CBX_CLIENT_LIVRActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 0, 204));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel12.setText("PRODUIT :");

        CBX_PRODUIT_LIVR.setBackground(new java.awt.Color(102, 153, 255));
        CBX_PRODUIT_LIVR.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        CBX_PRODUIT_LIVR.setForeground(new java.awt.Color(51, 51, 51));
        CBX_PRODUIT_LIVR.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        CBX_PRODUIT_LIVR.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                CBX_PRODUIT_LIVRItemStateChanged(evt);
            }
        });
        CBX_PRODUIT_LIVR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CBX_PRODUIT_LIVRActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 204));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel13.setText("MARQUE :");

        TXT_MARQUE_LIVR.setBackground(new java.awt.Color(255, 255, 255));
        TXT_MARQUE_LIVR.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        TXT_MARQUE_LIVR.setForeground(new java.awt.Color(51, 51, 51));

        jLabel14.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 0, 204));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel14.setText("UNITE :");

        TXT_UNITE_LIVR.setBackground(new java.awt.Color(255, 255, 255));
        TXT_UNITE_LIVR.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        TXT_UNITE_LIVR.setForeground(new java.awt.Color(51, 51, 51));

        jLabel15.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 0, 204));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel15.setText("PRIX/U :");

        TXT_PRIX_LIVR.setBackground(new java.awt.Color(255, 255, 255));
        TXT_PRIX_LIVR.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        TXT_PRIX_LIVR.setForeground(new java.awt.Color(51, 51, 51));

        BTN_AJOUT_LIVR.setBackground(new java.awt.Color(0, 204, 102));
        BTN_AJOUT_LIVR.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        BTN_AJOUT_LIVR.setForeground(new java.awt.Color(0, 0, 0));
        BTN_AJOUT_LIVR.setText("AJOUTER");
        BTN_AJOUT_LIVR.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        BTN_AJOUT_LIVR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_AJOUT_LIVRActionPerformed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(0, 0, 204));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel18.setText("DATE :");

        jLabel21.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(0, 0, 204));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel21.setText("QTE :");

        TXT_QUANTITE_LIVR.setBackground(new java.awt.Color(255, 255, 255));
        TXT_QUANTITE_LIVR.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        TXT_QUANTITE_LIVR.setForeground(new java.awt.Color(51, 51, 51));

        jLabel19.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(0, 0, 204));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel19.setText("PRODUIT :");

        TXT_PRODUIT_LIVR.setBackground(new java.awt.Color(255, 255, 255));
        TXT_PRODUIT_LIVR.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        TXT_PRODUIT_LIVR.setForeground(new java.awt.Color(51, 51, 51));

        BTN_SUPPRIMER_LIVRAISON.setBackground(new java.awt.Color(255, 51, 51));
        BTN_SUPPRIMER_LIVRAISON.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        BTN_SUPPRIMER_LIVRAISON.setForeground(new java.awt.Color(0, 0, 0));
        BTN_SUPPRIMER_LIVRAISON.setText("SUPPRIMER");
        BTN_SUPPRIMER_LIVRAISON.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        BTN_SUPPRIMER_LIVRAISON.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_SUPPRIMER_LIVRAISONActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(BTN_SUPPRIMER_LIVRAISON, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(BTN_AJOUT_LIVR, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(CBX_PRODUIT_LIVR, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(CBX_CLIENT_LIVR, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(TXT_PRIX_LIVR, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(TXT_NUM_LIVR, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(CBX_DATE_LIVR, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(TXT_PRODUIT_LIVR, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(TXT_QUANTITE_LIVR, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(TXT_UNITE_LIVR, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(TXT_MARQUE_LIVR, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 72, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CBX_CLIENT_LIVR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CBX_PRODUIT_LIVR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                    .addComponent(CBX_DATE_LIVR, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(16, 16, 16)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TXT_NUM_LIVR, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TXT_PRODUIT_LIVR, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TXT_MARQUE_LIVR, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TXT_UNITE_LIVR, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TXT_PRIX_LIVR, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TXT_QUANTITE_LIVR, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BTN_AJOUT_LIVR)
                    .addComponent(BTN_SUPPRIMER_LIVRAISON))
                .addGap(23, 23, 23))
        );

        TABLE_LIVRAISON.setBackground(new java.awt.Color(0, 204, 204));
        TABLE_LIVRAISON.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "N°", "CLIENT", "PRODUIT", "MARQUE", "UNITE", "PRIX/U", "QTE", "MONTANT/HT", "DATE"
            }
        ));
        TABLE_LIVRAISON.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        TABLE_LIVRAISON.setShowGrid(true);
        TABLE_LIVRAISON.setShowHorizontalLines(false);
        jScrollPane2.setViewportView(TABLE_LIVRAISON);

        jLabel20.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(0, 0, 0));
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("MONTANT  HT :");

        LABEL_MONTANTHT_LIVR.setBackground(new java.awt.Color(51, 51, 255));
        LABEL_MONTANTHT_LIVR.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        LABEL_MONTANTHT_LIVR.setForeground(new java.awt.Color(255, 255, 0));
        LABEL_MONTANTHT_LIVR.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LABEL_MONTANTHT_LIVR.setText("0.00");
        LABEL_MONTANTHT_LIVR.setOpaque(true);

        jLabel31.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(0, 0, 0));
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel31.setText("MONTANT TTC :");

        LABEL_MONTANTTTC_LIVR.setBackground(new java.awt.Color(51, 51, 255));
        LABEL_MONTANTTTC_LIVR.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        LABEL_MONTANTTTC_LIVR.setForeground(new java.awt.Color(255, 255, 0));
        LABEL_MONTANTTTC_LIVR.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LABEL_MONTANTTTC_LIVR.setText("0.00");
        LABEL_MONTANTTTC_LIVR.setOpaque(true);

        jPanel12.setBackground(new java.awt.Color(204, 204, 204));
        jPanel12.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        BTN_VALIDER_LIVR.setBackground(new java.awt.Color(0, 204, 102));
        BTN_VALIDER_LIVR.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        BTN_VALIDER_LIVR.setForeground(new java.awt.Color(0, 0, 0));
        BTN_VALIDER_LIVR.setText("VALIDER");
        BTN_VALIDER_LIVR.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        BTN_VALIDER_LIVR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_VALIDER_LIVRActionPerformed(evt);
            }
        });

        BTN_ACTUALISER_LIVR.setBackground(new java.awt.Color(255, 255, 51));
        BTN_ACTUALISER_LIVR.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        BTN_ACTUALISER_LIVR.setForeground(new java.awt.Color(0, 0, 0));
        BTN_ACTUALISER_LIVR.setText("ACTUALISER");
        BTN_ACTUALISER_LIVR.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        BTN_ACTUALISER_LIVR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_ACTUALISER_LIVRActionPerformed(evt);
            }
        });

        BTN_IMPRIMER_LIVR.setBackground(new java.awt.Color(51, 51, 255));
        BTN_IMPRIMER_LIVR.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        BTN_IMPRIMER_LIVR.setForeground(new java.awt.Color(0, 0, 0));
        BTN_IMPRIMER_LIVR.setText("IMPRIMER");
        BTN_IMPRIMER_LIVR.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        BTN_IMPRIMER_LIVR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_IMPRIMER_LIVRActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(BTN_IMPRIMER_LIVR, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addComponent(BTN_ACTUALISER_LIVR, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(BTN_VALIDER_LIVR, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(BTN_VALIDER_LIVR)
                .addComponent(BTN_ACTUALISER_LIVR)
                .addComponent(BTN_IMPRIMER_LIVR))
        );

        javax.swing.GroupLayout LIVRAILayout = new javax.swing.GroupLayout(LIVRAI);
        LIVRAI.setLayout(LIVRAILayout);
        LIVRAILayout.setHorizontalGroup(
            LIVRAILayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, LIVRAILayout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, LIVRAILayout.createSequentialGroup()
                .addContainerGap(513, Short.MAX_VALUE)
                .addGroup(LIVRAILayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, LIVRAILayout.createSequentialGroup()
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(LABEL_MONTANTHT_LIVR, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(68, 68, 68)
                        .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(LABEL_MONTANTTTC_LIVR, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(92, 92, 92))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, LIVRAILayout.createSequentialGroup()
                        .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        LIVRAILayout.setVerticalGroup(
            LIVRAILayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LIVRAILayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(LIVRAILayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(LIVRAILayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(LABEL_MONTANTHT_LIVR, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(LABEL_MONTANTTTC_LIVR, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(57, Short.MAX_VALUE))
        );

        LIVRAISON.addTab("Ajouter livraison", LIVRAI);

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N

        TABLE_LIVR_MODIFIER.setBackground(new java.awt.Color(0, 204, 204));
        TABLE_LIVR_MODIFIER.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "N°", "Produit", "Marque", "Unité", "Prix", "Quantité", "Montant", "Date", "ID", "Client"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Double.class, java.lang.Integer.class, java.lang.Double.class, java.lang.Object.class, java.lang.Integer.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        TABLE_LIVR_MODIFIER.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        TABLE_LIVR_MODIFIER.setShowGrid(true);
        TABLE_LIVR_MODIFIER.setShowHorizontalLines(false);
        jScrollPane14.setViewportView(TABLE_LIVR_MODIFIER);

        jPanel31.setBackground(new java.awt.Color(204, 204, 204));
        jPanel31.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel99.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel99.setForeground(new java.awt.Color(0, 0, 204));
        jLabel99.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel99.setText("Saisi N° :");

        TXT_RECHERCHE_LIVR.setBackground(new java.awt.Color(255, 255, 255));
        TXT_RECHERCHE_LIVR.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        TXT_RECHERCHE_LIVR.setForeground(new java.awt.Color(51, 51, 51));

        jLabel100.setBackground(new java.awt.Color(204, 204, 204));
        jLabel100.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jLabel100.setForeground(new java.awt.Color(51, 51, 51));
        jLabel100.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel100.setText("RECHERCHE ");

        jLabel101.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel101.setForeground(new java.awt.Color(0, 0, 204));
        jLabel101.setText(" Type :");

        CheckBox_NUM_LIVR.setBackground(new java.awt.Color(204, 204, 204));
        CheckBox_NUM_LIVR.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        CheckBox_NUM_LIVR.setForeground(new java.awt.Color(0, 0, 0));
        CheckBox_NUM_LIVR.setText("N° Achat");

        CheckBox_DATE_LIVR.setBackground(new java.awt.Color(204, 204, 204));
        CheckBox_DATE_LIVR.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        CheckBox_DATE_LIVR.setForeground(new java.awt.Color(0, 0, 0));
        CheckBox_DATE_LIVR.setText("Date");

        jLabel102.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel102.setForeground(new java.awt.Color(0, 0, 204));
        jLabel102.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel102.setText("Fourni :");

        CBX_CLIENT_RECHERCHE_LIVR.setBackground(new java.awt.Color(102, 153, 255));
        CBX_CLIENT_RECHERCHE_LIVR.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        CBX_CLIENT_RECHERCHE_LIVR.setForeground(new java.awt.Color(51, 51, 51));
        CBX_CLIENT_RECHERCHE_LIVR.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                CBX_CLIENT_RECHERCHE_LIVRItemStateChanged(evt);
            }
        });
        CBX_CLIENT_RECHERCHE_LIVR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CBX_CLIENT_RECHERCHE_LIVRActionPerformed(evt);
            }
        });

        BTN_RECHERCHE_LIVR1.setBackground(new java.awt.Color(0, 204, 102));
        BTN_RECHERCHE_LIVR1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        BTN_RECHERCHE_LIVR1.setForeground(new java.awt.Color(0, 0, 0));
        BTN_RECHERCHE_LIVR1.setText("RECHERCHE");
        BTN_RECHERCHE_LIVR1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        BTN_RECHERCHE_LIVR1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_RECHERCHE_LIVR1ActionPerformed(evt);
            }
        });

        jLabel103.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel103.setForeground(new java.awt.Color(0, 0, 204));
        jLabel103.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel103.setText("Date :");

        javax.swing.GroupLayout jPanel31Layout = new javax.swing.GroupLayout(jPanel31);
        jPanel31.setLayout(jPanel31Layout);
        jPanel31Layout.setHorizontalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel31Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(BTN_RECHERCHE_LIVR1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel31Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel31Layout.createSequentialGroup()
                                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel102, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel101, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel31Layout.createSequentialGroup()
                                        .addComponent(CheckBox_NUM_LIVR)
                                        .addGap(18, 18, 18)
                                        .addComponent(CheckBox_DATE_LIVR))
                                    .addComponent(CBX_CLIENT_RECHERCHE_LIVR, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(120, 120, 120))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel31Layout.createSequentialGroup()
                                .addComponent(jLabel100, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(81, 81, 81))))
                    .addGroup(jPanel31Layout.createSequentialGroup()
                        .addComponent(jLabel99, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(TXT_RECHERCHE_LIVR, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel103)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(CBX_DATE_LIVR_RECHERCHE, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel31Layout.setVerticalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel31Layout.createSequentialGroup()
                .addComponent(jLabel100, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel101)
                    .addComponent(CheckBox_NUM_LIVR)
                    .addComponent(CheckBox_DATE_LIVR))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel102, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CBX_CLIENT_RECHERCHE_LIVR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel103, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel31Layout.createSequentialGroup()
                        .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TXT_RECHERCHE_LIVR, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel99, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(CBX_DATE_LIVR_RECHERCHE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(BTN_RECHERCHE_LIVR1)
                .addContainerGap())
        );

        jPanel32.setBackground(new java.awt.Color(204, 204, 204));
        jPanel32.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel104.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel104.setForeground(new java.awt.Color(0, 0, 204));
        jLabel104.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel104.setText("DATE :");

        jLabel105.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel105.setForeground(new java.awt.Color(0, 0, 204));
        jLabel105.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel105.setText("NUM° :");

        TXT_NUM_LIVR_MODIFIER.setBackground(new java.awt.Color(255, 255, 255));
        TXT_NUM_LIVR_MODIFIER.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        TXT_NUM_LIVR_MODIFIER.setForeground(new java.awt.Color(51, 51, 51));

        jLabel106.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel106.setForeground(new java.awt.Color(0, 0, 204));
        jLabel106.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel106.setText("QTE :");

        TXT_QUANTITE_LIVR_MODIFIER.setBackground(new java.awt.Color(255, 255, 255));
        TXT_QUANTITE_LIVR_MODIFIER.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        TXT_QUANTITE_LIVR_MODIFIER.setForeground(new java.awt.Color(51, 51, 51));

        jLabel107.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel107.setForeground(new java.awt.Color(0, 0, 204));
        jLabel107.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel107.setText("PRODUIT :");

        TXT_PRODUIT_LIVR_MODIFIER.setEditable(false);
        TXT_PRODUIT_LIVR_MODIFIER.setBackground(new java.awt.Color(255, 255, 255));
        TXT_PRODUIT_LIVR_MODIFIER.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        TXT_PRODUIT_LIVR_MODIFIER.setForeground(new java.awt.Color(51, 51, 51));
        TXT_PRODUIT_LIVR_MODIFIER.setFocusable(false);

        jLabel108.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel108.setForeground(new java.awt.Color(0, 0, 204));
        jLabel108.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel108.setText("MARQUE :");

        TXT_MARQUE_LIVR_MODIFIER.setEditable(false);
        TXT_MARQUE_LIVR_MODIFIER.setBackground(new java.awt.Color(255, 255, 255));
        TXT_MARQUE_LIVR_MODIFIER.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        TXT_MARQUE_LIVR_MODIFIER.setForeground(new java.awt.Color(51, 51, 51));
        TXT_MARQUE_LIVR_MODIFIER.setFocusable(false);

        jLabel109.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel109.setForeground(new java.awt.Color(0, 0, 204));
        jLabel109.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel109.setText("UNITE :");

        TXT_UNITE_LIVR_MODIFIER.setEditable(false);
        TXT_UNITE_LIVR_MODIFIER.setBackground(new java.awt.Color(255, 255, 255));
        TXT_UNITE_LIVR_MODIFIER.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        TXT_UNITE_LIVR_MODIFIER.setForeground(new java.awt.Color(51, 51, 51));
        TXT_UNITE_LIVR_MODIFIER.setFocusable(false);

        jLabel110.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel110.setForeground(new java.awt.Color(0, 0, 204));
        jLabel110.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel110.setText("PRIX/U :");

        TXT_PRIX_LIVR_MODIFIER.setEditable(false);
        TXT_PRIX_LIVR_MODIFIER.setBackground(new java.awt.Color(255, 255, 255));
        TXT_PRIX_LIVR_MODIFIER.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        TXT_PRIX_LIVR_MODIFIER.setForeground(new java.awt.Color(51, 51, 51));
        TXT_PRIX_LIVR_MODIFIER.setFocusable(false);

        jLabel111.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel111.setForeground(new java.awt.Color(0, 0, 204));
        jLabel111.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel111.setText("MONTANT :");

        TXT_MONTANT_LIVR_MODIFIER.setEditable(false);
        TXT_MONTANT_LIVR_MODIFIER.setBackground(new java.awt.Color(255, 255, 255));
        TXT_MONTANT_LIVR_MODIFIER.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        TXT_MONTANT_LIVR_MODIFIER.setForeground(new java.awt.Color(51, 51, 51));
        TXT_MONTANT_LIVR_MODIFIER.setFocusable(false);

        jLabel112.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel112.setForeground(new java.awt.Color(0, 0, 204));
        jLabel112.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel112.setText("Client :");

        TXT_CLIENT_LIVR_MODIFIER.setEditable(false);
        TXT_CLIENT_LIVR_MODIFIER.setBackground(new java.awt.Color(255, 255, 255));
        TXT_CLIENT_LIVR_MODIFIER.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        TXT_CLIENT_LIVR_MODIFIER.setForeground(new java.awt.Color(51, 51, 51));
        TXT_CLIENT_LIVR_MODIFIER.setFocusable(false);

        CBX_TYPE_MODIFIER_LIVR.setBackground(new java.awt.Color(102, 153, 255));
        CBX_TYPE_MODIFIER_LIVR.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        CBX_TYPE_MODIFIER_LIVR.setForeground(new java.awt.Color(51, 51, 51));
        CBX_TYPE_MODIFIER_LIVR.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "num", "date", "quantite" }));
        CBX_TYPE_MODIFIER_LIVR.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                CBX_TYPE_MODIFIER_LIVRItemStateChanged(evt);
            }
        });
        CBX_TYPE_MODIFIER_LIVR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CBX_TYPE_MODIFIER_LIVRActionPerformed(evt);
            }
        });

        jLabel113.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel113.setForeground(new java.awt.Color(0, 0, 204));
        jLabel113.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel113.setText("Type de modif :");

        ID1.setForeground(new java.awt.Color(204, 204, 204));

        BTN_MODIFIER_LIVR1.setBackground(new java.awt.Color(255, 255, 0));
        BTN_MODIFIER_LIVR1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        BTN_MODIFIER_LIVR1.setForeground(new java.awt.Color(0, 0, 0));
        BTN_MODIFIER_LIVR1.setText("MODIFIER");
        BTN_MODIFIER_LIVR1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        BTN_MODIFIER_LIVR1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_MODIFIER_LIVR1ActionPerformed(evt);
            }
        });

        BTN_SUPPRIMER_LIVROFF1.setBackground(new java.awt.Color(255, 102, 102));
        BTN_SUPPRIMER_LIVROFF1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        BTN_SUPPRIMER_LIVROFF1.setForeground(new java.awt.Color(0, 0, 0));
        BTN_SUPPRIMER_LIVROFF1.setText("SUPPRIMER");
        BTN_SUPPRIMER_LIVROFF1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        BTN_SUPPRIMER_LIVROFF1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_SUPPRIMER_LIVROFF1ActionPerformed(evt);
            }
        });

        CHECKBOX_SUPP_NUM_LIVR.setBackground(new java.awt.Color(153, 153, 153));
        CHECKBOX_SUPP_NUM_LIVR.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        CHECKBOX_SUPP_NUM_LIVR.setForeground(new java.awt.Color(0, 0, 0));
        CHECKBOX_SUPP_NUM_LIVR.setText("Par N° achat");

        CHECKBOX_SUPP_PRODUIT_LIVR.setBackground(new java.awt.Color(153, 153, 153));
        CHECKBOX_SUPP_PRODUIT_LIVR.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        CHECKBOX_SUPP_PRODUIT_LIVR.setForeground(new java.awt.Color(0, 0, 0));
        CHECKBOX_SUPP_PRODUIT_LIVR.setText("Par produit");

        javax.swing.GroupLayout jPanel32Layout = new javax.swing.GroupLayout(jPanel32);
        jPanel32.setLayout(jPanel32Layout);
        jPanel32Layout.setHorizontalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel32Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel32Layout.createSequentialGroup()
                                    .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel32Layout.createSequentialGroup()
                                            .addGap(6, 6, 6)
                                            .addComponent(jLabel105, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                                        .addGroup(jPanel32Layout.createSequentialGroup()
                                            .addComponent(jLabel104, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(25, 25, 25)))
                                    .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(CBX_DATE_LIVR_MODIFIER, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel32Layout.createSequentialGroup()
                                            .addComponent(TXT_NUM_LIVR_MODIFIER, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(116, 116, 116))))
                                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel32Layout.createSequentialGroup()
                                        .addComponent(jLabel112, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(TXT_CLIENT_LIVR_MODIFIER, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel32Layout.createSequentialGroup()
                                        .addComponent(jLabel111)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(TXT_MONTANT_LIVR_MODIFIER, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(59, 59, 59)))
                                .addGroup(jPanel32Layout.createSequentialGroup()
                                    .addGap(7, 7, 7)
                                    .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel32Layout.createSequentialGroup()
                                            .addComponent(jLabel108, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(TXT_MARQUE_LIVR_MODIFIER, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel32Layout.createSequentialGroup()
                                            .addComponent(jLabel107, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(TXT_PRODUIT_LIVR_MODIFIER, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel32Layout.createSequentialGroup()
                                            .addComponent(jLabel109, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(TXT_UNITE_LIVR_MODIFIER, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel32Layout.createSequentialGroup()
                                            .addComponent(jLabel110, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(TXT_PRIX_LIVR_MODIFIER, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel32Layout.createSequentialGroup()
                                            .addComponent(jLabel106, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(TXT_QUANTITE_LIVR_MODIFIER, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGap(37, 37, 37)))
                            .addGroup(jPanel32Layout.createSequentialGroup()
                                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel32Layout.createSequentialGroup()
                                        .addComponent(jLabel113)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(CBX_TYPE_MODIFIER_LIVR, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel32Layout.createSequentialGroup()
                                        .addComponent(CHECKBOX_SUPP_NUM_LIVR)
                                        .addGap(18, 18, 18)
                                        .addComponent(CHECKBOX_SUPP_PRODUIT_LIVR)))
                                .addGap(79, 79, 79)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ID1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel32Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BTN_MODIFIER_LIVR1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel32Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(BTN_SUPPRIMER_LIVROFF1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel32Layout.setVerticalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel32Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(ID1, javax.swing.GroupLayout.DEFAULT_SIZE, 12, Short.MAX_VALUE)
                        .addGap(310, 310, 310))
                    .addGroup(jPanel32Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel112, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TXT_CLIENT_LIVR_MODIFIER, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel104, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(CBX_DATE_LIVR_MODIFIER, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel105, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TXT_NUM_LIVR_MODIFIER, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel107, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TXT_PRODUIT_LIVR_MODIFIER, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel108, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TXT_MARQUE_LIVR_MODIFIER, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel109, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TXT_UNITE_LIVR_MODIFIER, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel110, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TXT_PRIX_LIVR_MODIFIER, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel106, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TXT_QUANTITE_LIVR_MODIFIER, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel111, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TXT_MONTANT_LIVR_MODIFIER, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel113, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CBX_TYPE_MODIFIER_LIVR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BTN_MODIFIER_LIVR1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(CHECKBOX_SUPP_PRODUIT_LIVR)
                        .addComponent(CHECKBOX_SUPP_NUM_LIVR))
                    .addComponent(BTN_SUPPRIMER_LIVROFF1))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane14, javax.swing.GroupLayout.DEFAULT_SIZE, 931, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane14)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 60, Short.MAX_VALUE))
        );

        LIVRAISON.addTab("Parametrer livraison", jPanel2);

        jTabbedPane1.addTab("Livraison", LIVRAISON);

        ACHAT.setBackground(new java.awt.Color(204, 204, 204));
        ACHAT.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));
        jPanel3.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N

        jPanel20.setBackground(new java.awt.Color(204, 204, 204));
        jPanel20.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel48.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(0, 0, 204));
        jLabel48.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel48.setText("NUM° :");

        TXT_NUM_ACHAT.setBackground(new java.awt.Color(255, 255, 255));
        TXT_NUM_ACHAT.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        TXT_NUM_ACHAT.setForeground(new java.awt.Color(51, 51, 51));

        jLabel52.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel52.setForeground(new java.awt.Color(0, 0, 204));
        jLabel52.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel52.setText("FOURNI ;");

        CBX_FOURNISSEUR_ACHAT.setBackground(new java.awt.Color(102, 153, 255));
        CBX_FOURNISSEUR_ACHAT.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        CBX_FOURNISSEUR_ACHAT.setForeground(new java.awt.Color(51, 51, 51));
        CBX_FOURNISSEUR_ACHAT.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        CBX_FOURNISSEUR_ACHAT.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                CBX_FOURNISSEUR_ACHATItemStateChanged(evt);
            }
        });
        CBX_FOURNISSEUR_ACHAT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CBX_FOURNISSEUR_ACHATActionPerformed(evt);
            }
        });

        jLabel53.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel53.setForeground(new java.awt.Color(0, 0, 204));
        jLabel53.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel53.setText("PRODUIT :");

        CBX_PRODUIT_ACHAT.setBackground(new java.awt.Color(102, 153, 255));
        CBX_PRODUIT_ACHAT.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        CBX_PRODUIT_ACHAT.setForeground(new java.awt.Color(51, 51, 51));
        CBX_PRODUIT_ACHAT.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        CBX_PRODUIT_ACHAT.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                CBX_PRODUIT_ACHATItemStateChanged(evt);
            }
        });
        CBX_PRODUIT_ACHAT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CBX_PRODUIT_ACHATActionPerformed(evt);
            }
        });

        jLabel54.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel54.setForeground(new java.awt.Color(0, 0, 204));
        jLabel54.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel54.setText("MARQUE :");

        TXT_MARQUE_ACHAT.setBackground(new java.awt.Color(255, 255, 255));
        TXT_MARQUE_ACHAT.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        TXT_MARQUE_ACHAT.setForeground(new java.awt.Color(51, 51, 51));

        jLabel55.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel55.setForeground(new java.awt.Color(0, 0, 204));
        jLabel55.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel55.setText("UNITE :");

        TXT_UNITE_ACHAT.setBackground(new java.awt.Color(255, 255, 255));
        TXT_UNITE_ACHAT.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        TXT_UNITE_ACHAT.setForeground(new java.awt.Color(51, 51, 51));

        jLabel56.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel56.setForeground(new java.awt.Color(0, 0, 204));
        jLabel56.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel56.setText("PRIX/U :");

        TXT_PRIX_ACHAT.setBackground(new java.awt.Color(255, 255, 255));
        TXT_PRIX_ACHAT.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        TXT_PRIX_ACHAT.setForeground(new java.awt.Color(51, 51, 51));

        BTN_AJOUT_ACHAT.setBackground(new java.awt.Color(0, 204, 102));
        BTN_AJOUT_ACHAT.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        BTN_AJOUT_ACHAT.setForeground(new java.awt.Color(0, 0, 0));
        BTN_AJOUT_ACHAT.setText("AJOUTER");
        BTN_AJOUT_ACHAT.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        BTN_AJOUT_ACHAT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_AJOUT_ACHATActionPerformed(evt);
            }
        });

        jLabel57.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel57.setForeground(new java.awt.Color(0, 0, 204));
        jLabel57.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel57.setText("DATE :");

        jLabel58.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel58.setForeground(new java.awt.Color(0, 0, 204));
        jLabel58.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel58.setText("QTE :");

        TXT_QUANTITE_ACHAT.setBackground(new java.awt.Color(255, 255, 255));
        TXT_QUANTITE_ACHAT.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        TXT_QUANTITE_ACHAT.setForeground(new java.awt.Color(51, 51, 51));

        jLabel59.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel59.setForeground(new java.awt.Color(0, 0, 204));
        jLabel59.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel59.setText("PRODUIT :");

        TXT_PRODUIT_ACHAT.setBackground(new java.awt.Color(255, 255, 255));
        TXT_PRODUIT_ACHAT.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        TXT_PRODUIT_ACHAT.setForeground(new java.awt.Color(51, 51, 51));

        BTN_SUPPRIMER_ACHAT.setBackground(new java.awt.Color(255, 51, 51));
        BTN_SUPPRIMER_ACHAT.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        BTN_SUPPRIMER_ACHAT.setForeground(new java.awt.Color(0, 0, 0));
        BTN_SUPPRIMER_ACHAT.setText("SUPPRIMER");
        BTN_SUPPRIMER_ACHAT.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        BTN_SUPPRIMER_ACHAT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_SUPPRIMER_ACHATActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(BTN_SUPPRIMER_ACHAT, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(BTN_AJOUT_ACHAT, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel20Layout.createSequentialGroup()
                                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel53, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel52, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(CBX_PRODUIT_ACHAT, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(CBX_FOURNISSEUR_ACHAT, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel20Layout.createSequentialGroup()
                                .addComponent(jLabel56, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(TXT_PRIX_ACHAT, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel20Layout.createSequentialGroup()
                                .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(TXT_UNITE_ACHAT, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel20Layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(jLabel48)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(TXT_NUM_ACHAT, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel20Layout.createSequentialGroup()
                                .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(TXT_MARQUE_ACHAT, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel20Layout.createSequentialGroup()
                                .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(CBX_DATE_ACHAT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel20Layout.createSequentialGroup()
                                .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(TXT_PRODUIT_ACHAT, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel20Layout.createSequentialGroup()
                                .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(TXT_QUANTITE_ACHAT, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 58, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CBX_FOURNISSEUR_ACHAT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CBX_PRODUIT_ACHAT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel57, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                    .addComponent(CBX_DATE_ACHAT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(16, 16, 16)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TXT_NUM_ACHAT, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TXT_PRODUIT_ACHAT, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TXT_MARQUE_ACHAT, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TXT_UNITE_ACHAT, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel56, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TXT_PRIX_ACHAT, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TXT_QUANTITE_ACHAT, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BTN_AJOUT_ACHAT)
                    .addComponent(BTN_SUPPRIMER_ACHAT))
                .addGap(23, 23, 23))
        );

        TABLE_ACHAT.setBackground(new java.awt.Color(0, 204, 204));
        TABLE_ACHAT.setForeground(new java.awt.Color(51, 51, 51));
        TABLE_ACHAT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "N°", "FOURNISSEUR", "PRODUIT", "MARQUE", "UNITE", "PRIX/U", "QTE", "MONTANT/HT", "DATE"
            }
        ));
        TABLE_ACHAT.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        TABLE_ACHAT.setShowGrid(true);
        TABLE_ACHAT.setShowHorizontalLines(false);
        jScrollPane8.setViewportView(TABLE_ACHAT);

        jLabel60.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        jLabel60.setForeground(new java.awt.Color(0, 0, 0));
        jLabel60.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel60.setText("MONTANT  HT :");

        LABEL_MONTANTHT_ACHAT.setBackground(new java.awt.Color(51, 51, 255));
        LABEL_MONTANTHT_ACHAT.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        LABEL_MONTANTHT_ACHAT.setForeground(new java.awt.Color(255, 255, 0));
        LABEL_MONTANTHT_ACHAT.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LABEL_MONTANTHT_ACHAT.setText("0.00");
        LABEL_MONTANTHT_ACHAT.setOpaque(true);

        jLabel61.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        jLabel61.setForeground(new java.awt.Color(0, 0, 0));
        jLabel61.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel61.setText("MONTANT TTC :");

        LABEL_MONTANTTTC_ACHAT.setBackground(new java.awt.Color(51, 51, 255));
        LABEL_MONTANTTTC_ACHAT.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        LABEL_MONTANTTTC_ACHAT.setForeground(new java.awt.Color(255, 255, 0));
        LABEL_MONTANTTTC_ACHAT.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LABEL_MONTANTTTC_ACHAT.setText("0.00");
        LABEL_MONTANTTTC_ACHAT.setOpaque(true);

        jPanel21.setBackground(new java.awt.Color(204, 204, 204));
        jPanel21.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        BTN_VALIDER_ACHAT.setBackground(new java.awt.Color(0, 204, 102));
        BTN_VALIDER_ACHAT.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        BTN_VALIDER_ACHAT.setForeground(new java.awt.Color(0, 0, 0));
        BTN_VALIDER_ACHAT.setText("VALIDER");
        BTN_VALIDER_ACHAT.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        BTN_VALIDER_ACHAT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_VALIDER_ACHATActionPerformed(evt);
            }
        });

        BTN_ACTUALISER_ACHAT.setBackground(new java.awt.Color(255, 255, 51));
        BTN_ACTUALISER_ACHAT.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        BTN_ACTUALISER_ACHAT.setForeground(new java.awt.Color(0, 0, 0));
        BTN_ACTUALISER_ACHAT.setText("ACTUALISER");
        BTN_ACTUALISER_ACHAT.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        BTN_ACTUALISER_ACHAT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_ACTUALISER_ACHATActionPerformed(evt);
            }
        });

        BTN_IMPRIMER_ACHAT.setBackground(new java.awt.Color(51, 51, 255));
        BTN_IMPRIMER_ACHAT.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        BTN_IMPRIMER_ACHAT.setForeground(new java.awt.Color(0, 0, 0));
        BTN_IMPRIMER_ACHAT.setText("IMPRIMER");
        BTN_IMPRIMER_ACHAT.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        BTN_IMPRIMER_ACHAT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_IMPRIMER_ACHATActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(BTN_IMPRIMER_ACHAT, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(BTN_ACTUALISER_ACHAT, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(BTN_VALIDER_ACHAT, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(BTN_VALIDER_ACHAT)
                .addComponent(BTN_ACTUALISER_ACHAT)
                .addComponent(BTN_IMPRIMER_ACHAT))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 985, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel60, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(LABEL_MONTANTHT_ACHAT, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(74, 74, 74)
                                .addComponent(jLabel61, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(LABEL_MONTANTTTC_ACHAT, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(83, 83, 83))
                            .addComponent(jPanel21, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane8)
                    .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(20, 20, 20)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel61, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(LABEL_MONTANTTTC_ACHAT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(LABEL_MONTANTHT_ACHAT, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel60, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59))
        );

        ACHAT.addTab("Ajouter Achat", jPanel3);

        jPanel4.setBackground(new java.awt.Color(204, 204, 204));

        TABLE_ACHAT_MODIFIER.setBackground(new java.awt.Color(0, 204, 204));
        TABLE_ACHAT_MODIFIER.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "N°", "Produit", "Marque", "Unité", "Prix", "Quantité", "Montant", "Date", "ID", "Fournisseur"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Double.class, java.lang.Integer.class, java.lang.Double.class, java.lang.Object.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                true, true, true, true, true, true, true, false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TABLE_ACHAT_MODIFIER.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        TABLE_ACHAT_MODIFIER.setShowGrid(true);
        TABLE_ACHAT_MODIFIER.setShowHorizontalLines(false);
        jScrollPane12.setViewportView(TABLE_ACHAT_MODIFIER);

        jPanel23.setBackground(new java.awt.Color(204, 204, 204));
        jPanel23.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel69.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel69.setForeground(new java.awt.Color(0, 0, 204));
        jLabel69.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel69.setText("Saisi N° :");

        TXT_RECHERCHE_ACHAT.setBackground(new java.awt.Color(255, 255, 255));
        TXT_RECHERCHE_ACHAT.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        TXT_RECHERCHE_ACHAT.setForeground(new java.awt.Color(51, 51, 51));

        jLabel70.setBackground(new java.awt.Color(204, 204, 204));
        jLabel70.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jLabel70.setForeground(new java.awt.Color(51, 51, 51));
        jLabel70.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel70.setText("RECHERCHE ");

        jLabel71.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel71.setForeground(new java.awt.Color(0, 0, 204));
        jLabel71.setText(" Type :");

        CheckBox_NUM_ACHAT.setBackground(new java.awt.Color(204, 204, 204));
        CheckBox_NUM_ACHAT.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        CheckBox_NUM_ACHAT.setForeground(new java.awt.Color(0, 0, 0));
        CheckBox_NUM_ACHAT.setText("N° Achat");

        CheckBox_DATE_ACHAT.setBackground(new java.awt.Color(204, 204, 204));
        CheckBox_DATE_ACHAT.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        CheckBox_DATE_ACHAT.setForeground(new java.awt.Color(0, 0, 0));
        CheckBox_DATE_ACHAT.setText("Date");

        jLabel72.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel72.setForeground(new java.awt.Color(0, 0, 204));
        jLabel72.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel72.setText("Fourni :");

        CBX_FOURNISSEUR_RECHERCHE_ACHAT.setBackground(new java.awt.Color(102, 153, 255));
        CBX_FOURNISSEUR_RECHERCHE_ACHAT.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        CBX_FOURNISSEUR_RECHERCHE_ACHAT.setForeground(new java.awt.Color(51, 51, 51));
        CBX_FOURNISSEUR_RECHERCHE_ACHAT.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        CBX_FOURNISSEUR_RECHERCHE_ACHAT.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                CBX_FOURNISSEUR_RECHERCHE_ACHATItemStateChanged(evt);
            }
        });
        CBX_FOURNISSEUR_RECHERCHE_ACHAT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CBX_FOURNISSEUR_RECHERCHE_ACHATActionPerformed(evt);
            }
        });

        BTN_RECHERCHE_ACHAT.setBackground(new java.awt.Color(0, 204, 102));
        BTN_RECHERCHE_ACHAT.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        BTN_RECHERCHE_ACHAT.setForeground(new java.awt.Color(0, 0, 0));
        BTN_RECHERCHE_ACHAT.setText("RECHERCHE");
        BTN_RECHERCHE_ACHAT.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        BTN_RECHERCHE_ACHAT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_RECHERCHE_ACHATActionPerformed(evt);
            }
        });

        jLabel82.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel82.setForeground(new java.awt.Color(0, 0, 204));
        jLabel82.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel82.setText("Date :");

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(BTN_RECHERCHE_ACHAT, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createSequentialGroup()
                                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel72, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel71, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel23Layout.createSequentialGroup()
                                        .addComponent(CheckBox_NUM_ACHAT)
                                        .addGap(18, 18, 18)
                                        .addComponent(CheckBox_DATE_ACHAT))
                                    .addComponent(CBX_FOURNISSEUR_RECHERCHE_ACHAT, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(120, 120, 120))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createSequentialGroup()
                                .addComponent(jLabel70, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(81, 81, 81))))
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addComponent(jLabel69, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(TXT_RECHERCHE_ACHAT, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel82)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(CBX_DATE_ACHAT_RECHERCHE, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createSequentialGroup()
                .addComponent(jLabel70, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel71)
                    .addComponent(CheckBox_NUM_ACHAT)
                    .addComponent(CheckBox_DATE_ACHAT))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel72, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CBX_FOURNISSEUR_RECHERCHE_ACHAT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel82, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TXT_RECHERCHE_ACHAT, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel69, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(CBX_DATE_ACHAT_RECHERCHE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(BTN_RECHERCHE_ACHAT)
                .addContainerGap())
        );

        jPanel25.setBackground(new java.awt.Color(204, 204, 204));
        jPanel25.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel73.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel73.setForeground(new java.awt.Color(0, 0, 204));
        jLabel73.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel73.setText("DATE :");

        jLabel74.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel74.setForeground(new java.awt.Color(0, 0, 204));
        jLabel74.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel74.setText("NUM° :");

        TXT_NUM_ACHAT_MODIFIER.setBackground(new java.awt.Color(255, 255, 255));
        TXT_NUM_ACHAT_MODIFIER.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        TXT_NUM_ACHAT_MODIFIER.setForeground(new java.awt.Color(51, 51, 51));

        jLabel75.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel75.setForeground(new java.awt.Color(0, 0, 204));
        jLabel75.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel75.setText("QTE :");

        TXT_QUANTITE_ACHAT_MODIFIER.setBackground(new java.awt.Color(255, 255, 255));
        TXT_QUANTITE_ACHAT_MODIFIER.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        TXT_QUANTITE_ACHAT_MODIFIER.setForeground(new java.awt.Color(51, 51, 51));

        jLabel76.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel76.setForeground(new java.awt.Color(0, 0, 204));
        jLabel76.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel76.setText("PRODUIT :");

        TXT_PRODUIT_ACHAT_MODIFIER.setEditable(false);
        TXT_PRODUIT_ACHAT_MODIFIER.setBackground(new java.awt.Color(255, 255, 255));
        TXT_PRODUIT_ACHAT_MODIFIER.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        TXT_PRODUIT_ACHAT_MODIFIER.setForeground(new java.awt.Color(51, 51, 51));
        TXT_PRODUIT_ACHAT_MODIFIER.setFocusable(false);

        jLabel77.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel77.setForeground(new java.awt.Color(0, 0, 204));
        jLabel77.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel77.setText("MARQUE :");

        TXT_MARQUE_ACHAT_MODIFIER.setEditable(false);
        TXT_MARQUE_ACHAT_MODIFIER.setBackground(new java.awt.Color(255, 255, 255));
        TXT_MARQUE_ACHAT_MODIFIER.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        TXT_MARQUE_ACHAT_MODIFIER.setForeground(new java.awt.Color(51, 51, 51));
        TXT_MARQUE_ACHAT_MODIFIER.setFocusable(false);

        jLabel78.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel78.setForeground(new java.awt.Color(0, 0, 204));
        jLabel78.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel78.setText("UNITE :");

        TXT_UNITE_ACHAT_MODIFIER.setEditable(false);
        TXT_UNITE_ACHAT_MODIFIER.setBackground(new java.awt.Color(255, 255, 255));
        TXT_UNITE_ACHAT_MODIFIER.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        TXT_UNITE_ACHAT_MODIFIER.setForeground(new java.awt.Color(51, 51, 51));
        TXT_UNITE_ACHAT_MODIFIER.setFocusable(false);

        jLabel79.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel79.setForeground(new java.awt.Color(0, 0, 204));
        jLabel79.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel79.setText("PRIX/U :");

        TXT_PRIX_ACHAT_MODIFIER.setEditable(false);
        TXT_PRIX_ACHAT_MODIFIER.setBackground(new java.awt.Color(255, 255, 255));
        TXT_PRIX_ACHAT_MODIFIER.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        TXT_PRIX_ACHAT_MODIFIER.setForeground(new java.awt.Color(51, 51, 51));
        TXT_PRIX_ACHAT_MODIFIER.setFocusable(false);

        jLabel80.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel80.setForeground(new java.awt.Color(0, 0, 204));
        jLabel80.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel80.setText("MONTANT :");

        TXT_MONTANT_ACHAT_MODIFIER.setEditable(false);
        TXT_MONTANT_ACHAT_MODIFIER.setBackground(new java.awt.Color(255, 255, 255));
        TXT_MONTANT_ACHAT_MODIFIER.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        TXT_MONTANT_ACHAT_MODIFIER.setForeground(new java.awt.Color(51, 51, 51));
        TXT_MONTANT_ACHAT_MODIFIER.setFocusable(false);

        jLabel81.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel81.setForeground(new java.awt.Color(0, 0, 204));
        jLabel81.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel81.setText("FOURNI :");

        TXT_FOURNISSEUR_ACHAT_MODIFIER.setEditable(false);
        TXT_FOURNISSEUR_ACHAT_MODIFIER.setBackground(new java.awt.Color(255, 255, 255));
        TXT_FOURNISSEUR_ACHAT_MODIFIER.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        TXT_FOURNISSEUR_ACHAT_MODIFIER.setForeground(new java.awt.Color(51, 51, 51));
        TXT_FOURNISSEUR_ACHAT_MODIFIER.setFocusable(false);

        CBX_TYPE_MODIFIER_ACHAT.setBackground(new java.awt.Color(102, 153, 255));
        CBX_TYPE_MODIFIER_ACHAT.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        CBX_TYPE_MODIFIER_ACHAT.setForeground(new java.awt.Color(51, 51, 51));
        CBX_TYPE_MODIFIER_ACHAT.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "num", "date", "quantite" }));
        CBX_TYPE_MODIFIER_ACHAT.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                CBX_TYPE_MODIFIER_ACHATItemStateChanged(evt);
            }
        });
        CBX_TYPE_MODIFIER_ACHAT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CBX_TYPE_MODIFIER_ACHATActionPerformed(evt);
            }
        });

        jLabel83.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel83.setForeground(new java.awt.Color(0, 0, 204));
        jLabel83.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel83.setText("Type de modif :");

        ID.setForeground(new java.awt.Color(204, 204, 204));

        BTN_MODIFIER_ACHAT.setBackground(new java.awt.Color(255, 255, 0));
        BTN_MODIFIER_ACHAT.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        BTN_MODIFIER_ACHAT.setForeground(new java.awt.Color(0, 0, 0));
        BTN_MODIFIER_ACHAT.setText("MODIFIER");
        BTN_MODIFIER_ACHAT.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        BTN_MODIFIER_ACHAT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_MODIFIER_ACHATActionPerformed(evt);
            }
        });

        BTN_SUPPRIMER_ACHATOFF.setBackground(new java.awt.Color(255, 102, 102));
        BTN_SUPPRIMER_ACHATOFF.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        BTN_SUPPRIMER_ACHATOFF.setForeground(new java.awt.Color(0, 0, 0));
        BTN_SUPPRIMER_ACHATOFF.setText("SUPPRIMER");
        BTN_SUPPRIMER_ACHATOFF.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        BTN_SUPPRIMER_ACHATOFF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_SUPPRIMER_ACHATOFFActionPerformed(evt);
            }
        });

        CHECKBOX_SUPP_PRODUIT_ACHAT.setBackground(new java.awt.Color(153, 153, 153));
        CHECKBOX_SUPP_PRODUIT_ACHAT.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        CHECKBOX_SUPP_PRODUIT_ACHAT.setForeground(new java.awt.Color(0, 0, 0));
        CHECKBOX_SUPP_PRODUIT_ACHAT.setText("Par produit");

        CHECKBOX_SUPP_NUM_ACHAT.setBackground(new java.awt.Color(153, 153, 153));
        CHECKBOX_SUPP_NUM_ACHAT.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        CHECKBOX_SUPP_NUM_ACHAT.setForeground(new java.awt.Color(0, 0, 0));
        CHECKBOX_SUPP_NUM_ACHAT.setText("Par N° achat");

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel25Layout.createSequentialGroup()
                                .addComponent(jLabel80)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(TXT_MONTANT_ACHAT_MODIFIER, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel25Layout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel25Layout.createSequentialGroup()
                                        .addComponent(jLabel78, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(TXT_UNITE_ACHAT_MODIFIER, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel25Layout.createSequentialGroup()
                                        .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel76, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel74, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(TXT_NUM_ACHAT_MODIFIER, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(TXT_PRODUIT_ACHAT_MODIFIER, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel25Layout.createSequentialGroup()
                                        .addComponent(jLabel77, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(TXT_MARQUE_ACHAT_MODIFIER, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel25Layout.createSequentialGroup()
                                        .addComponent(jLabel79, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(TXT_PRIX_ACHAT_MODIFIER, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel25Layout.createSequentialGroup()
                                        .addComponent(jLabel75, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(TXT_QUANTITE_ACHAT_MODIFIER, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel73, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel25Layout.createSequentialGroup()
                                .addComponent(jLabel81, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TXT_FOURNISSEUR_ACHAT_MODIFIER, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ID, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel25Layout.createSequentialGroup()
                            .addComponent(jLabel83)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(CBX_TYPE_MODIFIER_ACHAT, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(BTN_MODIFIER_ACHAT, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel25Layout.createSequentialGroup()
                            .addComponent(CHECKBOX_SUPP_NUM_ACHAT)
                            .addGap(18, 18, 18)
                            .addComponent(CHECKBOX_SUPP_PRODUIT_ACHAT)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(BTN_SUPPRIMER_ACHATOFF, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(10, 10, 10)))
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addGap(81, 81, 81)
                        .addComponent(CBX_DATE_ACHAT_MODIFIER, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel25Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addComponent(ID, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(330, 330, 330))
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel81, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TXT_FOURNISSEUR_ACHAT_MODIFIER, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel73, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(CBX_DATE_ACHAT_MODIFIER, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel74, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TXT_NUM_ACHAT_MODIFIER, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel76, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TXT_PRODUIT_ACHAT_MODIFIER, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel77, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TXT_MARQUE_ACHAT_MODIFIER, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel78, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TXT_UNITE_ACHAT_MODIFIER, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel79, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TXT_PRIX_ACHAT_MODIFIER, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel75, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TXT_QUANTITE_ACHAT_MODIFIER, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel80, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TXT_MONTANT_ACHAT_MODIFIER, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel83, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CBX_TYPE_MODIFIER_ACHAT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BTN_MODIFIER_ACHAT))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(BTN_SUPPRIMER_ACHATOFF)
                            .addComponent(CHECKBOX_SUPP_PRODUIT_ACHAT)
                            .addComponent(CHECKBOX_SUPP_NUM_ACHAT))
                        .addContainerGap(20, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 931, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane12)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(49, Short.MAX_VALUE))
        );

        ACHAT.addTab("Parametrer achat", jPanel4);

        jTabbedPane1.addTab("Achat", ACHAT);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CBX_CLIENT_RECHERCHE_LIVRItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_CBX_CLIENT_RECHERCHE_LIVRItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_CBX_CLIENT_RECHERCHE_LIVRItemStateChanged

    private void CBX_CLIENT_RECHERCHE_LIVRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CBX_CLIENT_RECHERCHE_LIVRActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CBX_CLIENT_RECHERCHE_LIVRActionPerformed

    private void BTN_RECHERCHE_LIVR1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_RECHERCHE_LIVR1ActionPerformed
        Object clientSelectionne = CBX_CLIENT_RECHERCHE_LIVR.getSelectedItem();
        String num = TXT_RECHERCHE_LIVR.getText();
        Date date = CBX_DATE_LIVR_RECHERCHE.getDate();

        if (CheckBox_NUM_LIVR.isSelected() && !CheckBox_DATE_LIVR.isSelected() && clientSelectionne != null && !num.isEmpty()) {
            int numeroLivr = Integer.parseInt(num);
            String client = clientSelectionne.toString();
            String[] splitClient = client.split(" ");
            String nom = splitClient[0];
            String prenom = splitClient[1];
            try {
                int id = db.chercheIdClient(nom, prenom);
                db.chercheLivrByClientNum(id, numeroLivr, TABLE_LIVR_MODIFIER);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        } else if (CheckBox_DATE_LIVR.isSelected() && !CheckBox_NUM_LIVR.isSelected() && clientSelectionne != null && date != null) {
            String client = clientSelectionne.toString();
            try {
                String[] splitClient = client.split(" ");
                String nom = splitClient[0];
                String prenom = splitClient[1];
                int id = db.chercheIdClient(nom, prenom);

                java.sql.Date sqlDate;
                if (date instanceof java.sql.Date) {
                    sqlDate = (java.sql.Date) date;
                } else {
                    sqlDate = new java.sql.Date(date.getTime());
                }

                db.chercheLivrByClientDate(id, sqlDate, TABLE_LIVR_MODIFIER);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } else if (CheckBox_DATE_LIVR.isSelected() && CheckBox_NUM_LIVR.isSelected() && clientSelectionne != null && date != null && !num.isEmpty()) {
            String client = clientSelectionne.toString();
            String[] splitClient = client.split(" ");
            String nom = splitClient[0];
            String prenom = splitClient[1];
            try {
                int id = db.chercheIdClient(nom, prenom);

                java.sql.Date sqlDate;
                if (date instanceof java.sql.Date) {
                    sqlDate = (java.sql.Date) date;
                } else {
                    sqlDate = new java.sql.Date(date.getTime());
                }

                db.chercheLivrByClientDateNum(id, Integer.parseInt(num), sqlDate, TABLE_LIVR_MODIFIER);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } else {
            try {
                db.allLivraison(TABLE_LIVR_MODIFIER);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_BTN_RECHERCHE_LIVR1ActionPerformed

    private void CBX_TYPE_MODIFIER_LIVRItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_CBX_TYPE_MODIFIER_LIVRItemStateChanged
       String type=CBX_TYPE_MODIFIER_LIVR.getSelectedItem().toString();
        if ("num".equals(type)) { // Vérifier si "num" est sélectionné
            // Rendre le champ de numéro d'achat éditable
            TXT_NUM_LIVR_MODIFIER.setEditable(true);
            // Rendre les autres champs non éditables
            TXT_QUANTITE_LIVR_MODIFIER.setEditable(false);
        }else if("date".equals(type)){
            TXT_NUM_LIVR_MODIFIER.setEditable(false);
            // Rendre les autres champs non éditables
            TXT_QUANTITE_LIVR_MODIFIER.setEditable(false);
        }else{
            TXT_NUM_LIVR_MODIFIER.setEditable(false);
            // Rendre les autres champs non éditables
            TXT_QUANTITE_LIVR_MODIFIER.setEditable(true);
        }
    }//GEN-LAST:event_CBX_TYPE_MODIFIER_LIVRItemStateChanged

    private void CBX_TYPE_MODIFIER_LIVRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CBX_TYPE_MODIFIER_LIVRActionPerformed
        String type=CBX_TYPE_MODIFIER_LIVR.getSelectedItem().toString();
        if ("num".equals(type)) { // Vérifier si "num" est sélectionné
            // Rendre le champ de numéro d'achat éditable
            TXT_NUM_LIVR_MODIFIER.setEditable(true);
            // Rendre les autres champs non éditables
            TXT_QUANTITE_LIVR_MODIFIER.setEditable(false);
        }else if("date".equals(type)){
            TXT_NUM_LIVR_MODIFIER.setEditable(false);
            // Rendre les autres champs non éditables
            TXT_QUANTITE_LIVR_MODIFIER.setEditable(false);
        }else{
            TXT_NUM_LIVR_MODIFIER.setEditable(false);
            // Rendre les autres champs non éditables
            TXT_QUANTITE_LIVR_MODIFIER.setEditable(true);
        }
    }//GEN-LAST:event_CBX_TYPE_MODIFIER_LIVRActionPerformed

    private void BTN_MODIFIER_LIVR1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_MODIFIER_LIVR1ActionPerformed
        int num2=0 ;

        try {
            String client =  TXT_CLIENT_LIVR_MODIFIER.getText();
            String produit =  TXT_PRODUIT_LIVR_MODIFIER.getText();
            String marque =  TXT_MARQUE_LIVR_MODIFIER.getText();
            String quantite =TXT_QUANTITE_LIVR_MODIFIER.getText();
            Date date = CBX_DATE_LIVR_MODIFIER.getJCalendar().getDate();
            String[] splitClient = client.split(" ");
            String nom = splitClient[0];
            String prenom = splitClient[1];
            int id = db.chercheIdClient(nom,prenom);

            if (!client.isEmpty() && !produit.isEmpty() && !quantite.isEmpty()) {
                String type=CBX_TYPE_MODIFIER_LIVR.getSelectedItem().toString();
                num2=Integer.parseInt(ID1.getText());
                try {
                    if("num".equals(type)){

                        // Convertir java.util.Date en java.sql.Date
                        java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                        Double prix = Double.parseDouble(TXT_PRIX_LIVR_MODIFIER.getText());
                        int unite = Integer.parseInt(TXT_UNITE_LIVR_MODIFIER.getText());
                        int qte = Integer.parseInt(quantite);
                        int num1 = Integer.parseInt(TXT_NUM_LIVR_MODIFIER.getText());

                        // Correction de la conversion du montant en double
                        Double montant = Double.parseDouble(TXT_MONTANT_LIVR_MODIFIER.getText().replace(',', '.'));
                        System.out.println(num2);
                        Livraison livraison = new Livraison(num1, id,client, produit, marque, unite, prix,qte, montant, date);
                        db.modifierLivrByNum(id,num2,livraison);
                        db.allLivraison(TABLE_LIVR_MODIFIER);
                        
                    }else if("date".equals(type)){
                        TXT_NUM_LIVR_MODIFIER.setEditable(false);
                        TXT_QUANTITE_LIVR_MODIFIER.setEditable(false);
                        // Convertir java.util.Date en java.sql.Date
                        java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                        Double prix = Double.parseDouble(TXT_PRIX_LIVR_MODIFIER.getText());
                        int unite = Integer.parseInt(TXT_UNITE_LIVR_MODIFIER.getText());
                        int qte = Integer.parseInt(quantite);
                        int num1 = Integer.parseInt(TXT_NUM_LIVR_MODIFIER.getText());

                        // Correction de la conversion du montant en double
                        Double montant = Double.parseDouble(TXT_MONTANT_LIVR_MODIFIER.getText().replace(',', '.'));
                        Livraison livraison = new Livraison(num1, id,client, produit, marque, unite, prix,qte, montant, date);
                        db.modifierLivraisonByDate(id,num1,livraison);
                        db.allLivraison(TABLE_LIVR_MODIFIER);
                    }else{
                        TXT_NUM_LIVR_MODIFIER.setEditable(false);
                        TXT_QUANTITE_LIVR_MODIFIER.setEditable(true);
                        // Convertir java.util.Date en java.sql.Date
                        java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                        Double prix = Double.parseDouble(TXT_PRIX_LIVR_MODIFIER.getText());
                        int unite = Integer.parseInt(TXT_UNITE_LIVR_MODIFIER.getText());
                        int qte = Integer.parseInt(quantite);
                        int num1 = Integer.parseInt(TXT_NUM_LIVR_MODIFIER.getText());

                        // Correction de la conversion du montant en double
                        Double montant = Double.parseDouble(TXT_MONTANT_LIVR_MODIFIER.getText().replace(',', '.'));
                        Livraison livraison = new Livraison(num1, id,client, produit, marque, unite, prix,qte, montant, date);
                        db.modifierLivraisonByQTE(id,num1,livraison);
                        db.allLivraison(TABLE_LIVR_MODIFIER);
                        db.allStockAlerte(TABLE_ALERTE_STOCK);
                    }
                    db.allStock(TABLE_STOCK);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(null,"Tu dois remplir tous les champs!");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_BTN_MODIFIER_LIVR1ActionPerformed

    private void CBX_CLIENT_LIVRItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_CBX_CLIENT_LIVRItemStateChanged
         Object cli=CBX_CLIENT_LIVR.getSelectedItem();
         if(cli!=null){
             
               int num1 = db.getNUMMAXLivraison(cli.toString())+1;
               TXT_NUM_LIVR.setText(String.valueOf(num1));
         }
    }//GEN-LAST:event_CBX_CLIENT_LIVRItemStateChanged

    private void CBX_CLIENT_LIVRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CBX_CLIENT_LIVRActionPerformed
        Object clientObject = CBX_CLIENT_LIVR.getSelectedItem();
        if (clientObject != null) {
            String client  = CBX_CLIENT_LIVR.getSelectedItem().toString();
            String[] splitClient = client.toString().split(" ");
            if (splitClient.length >= 2) {
                String nom = splitClient[0];
                String prenom = splitClient[1];
                int id = 0;
                try {
                    id = db.chercheIdClient(nom, prenom);
                    db.getIdProduitPrix(id, CBX_PRODUIT_LIVR);
                } catch (SQLException ex) {
                    Logger.getLogger(ACCEUIL1.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                System.out.println("La sélection du client est invalide.");
            }
        } else {
            System.out.println("Veuillez choisir un client.");
        }
    }//GEN-LAST:event_CBX_CLIENT_LIVRActionPerformed

    private void CBX_PRODUIT_LIVRItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_CBX_PRODUIT_LIVRItemStateChanged

    }//GEN-LAST:event_CBX_PRODUIT_LIVRItemStateChanged

    private void CBX_PRODUIT_LIVRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CBX_PRODUIT_LIVRActionPerformed
        Object selectClient =CBX_CLIENT_LIVR.getSelectedItem();
        Object selectProduit =CBX_PRODUIT_LIVR.getSelectedItem();
        int IDCLIENT=0;
        int IDPRODUIT=0;
        if (selectClient != null && selectProduit !=null){
            String client=selectClient.toString();
            String produit=selectProduit.toString();
            String[] splitClient =client.split(" ");
            String nom =splitClient[0];
            String prenom =splitClient[1];

            String[] splitProduit =produit.split(" ");
            String produit1 =splitProduit[0];
            String marque1 =splitProduit[1];

            try {
                IDCLIENT =db.chercheIdClient(nom,prenom);
            } catch (SQLException ex) {
                Logger.getLogger(ACCEUIL1.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                IDPRODUIT =db.chercheIdProduit(produit1,marque1);
            } catch (SQLException ex) {
                Logger.getLogger(ACCEUIL1.class.getName()).log(Level.SEVERE, null, ex);
            }

            db.allClientProduit(TXT_UNITE_LIVR,TXT_PRIX_LIVR,IDCLIENT,IDPRODUIT);
            TXT_PRODUIT_LIVR.setText(produit1);
            TXT_MARQUE_LIVR.setText(marque1);
        }else{
            TXT_UNITE_LIVR.setText("");
            TXT_PRIX_LIVR.setText("");
        }
    }//GEN-LAST:event_CBX_PRODUIT_LIVRActionPerformed

    private void BTN_AJOUT_LIVRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_AJOUT_LIVRActionPerformed
        boolean produitExist;
        try {
            String client =  CBX_CLIENT_LIVR.getSelectedItem().toString();
            String[] splitProduit = CBX_PRODUIT_LIVR.getSelectedItem().toString().split(" ");
            String produit = splitProduit[0];
            String marque = splitProduit[1];

            Date date = CBX_DATE_LIVR.getJCalendar().getDate();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String dateformat = sdf.format(date);

            if (!TXT_PRODUIT_LIVR.getText().isEmpty() && !TXT_MARQUE_LIVR.getText().isEmpty() && !TXT_UNITE_LIVR.getText().isEmpty() && !TXT_QUANTITE_LIVR.getText().isEmpty() && CBX_DATE_LIVR.getDate() != null ) {
                try {
                    Double prix = Double.parseDouble(TXT_PRIX_LIVR.getText());
                    int unite = Integer.parseInt(TXT_UNITE_LIVR.getText());
                    int qte = Integer.parseInt(TXT_QUANTITE_LIVR.getText());
                    int num = Integer.parseInt(TXT_NUM_LIVR.getText());

                    // Correction de la conversion du montant en double
                    Double montant = Double.parseDouble(LABEL_MONTANTHT_LIVR.getText().replace(',', '.'));

                    DefaultTableModel mo = (DefaultTableModel) TABLE_LIVRAISON.getModel();
                    produitExist=false;
                    for (int i = 0; i < mo.getRowCount(); i++) {
                        Object prodExistObj = mo.getValueAt(i, 2); // Récupérez la valeur de la colonne du produit
                        Object marqExistObj = mo.getValueAt(i, 3); // Récupérez la valeur de la colonne de la marque

                        // Vérifiez si les valeurs sont null avant de les convertir en chaînes de caractères
                        if (prodExistObj != null && marqExistObj != null) {
                            String prodExist = prodExistObj.toString(); // Convertissez la valeur en chaîne de caractères
                            String marqExist = marqExistObj.toString(); // Convertissez la valeur en chaîne de caractères

                            // Maintenant, vous pouvez comparer les valeurs sans risque de NullPointerException
                            if (prodExist.equals(produit) && marqExist.equals(marque)) {
                                produitExist = true;
                                break;
                            }
                        }
                    }
                    if (!produitExist){
                        mo.insertRow(0,new Object[]{num,client,produit,marque,unite,prix,qte,montant,date});
                    }else{
                        JOptionPane.showMessageDialog(null,"le produit "+produit+ " de la marque "+ marque+ " est deja ajouté!");
                    }
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }else{
                JOptionPane.showMessageDialog(null,"tu dois remplir tous les champs!");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_BTN_AJOUT_LIVRActionPerformed

    private void BTN_SUPPRIMER_LIVRAISONActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_SUPPRIMER_LIVRAISONActionPerformed
        int selectedRow = TABLE_LIVRAISON.getSelectedRow();
        if (selectedRow != -1) {
            // Supprimer la ligne sélectionnée du modèle de la JTable
            ((DefaultTableModel) TABLE_LIVRAISON.getModel()).removeRow(selectedRow);
        }
    }//GEN-LAST:event_BTN_SUPPRIMER_LIVRAISONActionPerformed

    private void BTN_VALIDER_LIVRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_VALIDER_LIVRActionPerformed
        try {
            String client = CBX_CLIENT_LIVR.getSelectedItem().toString();
            String[] splitClient = client.split(" ");
            String nom = splitClient[0];
            String prenom = splitClient[1];
            int ID =db.chercheIdClient(nom, prenom);
            String numText = TXT_NUM_LIVR.getText().trim(); // Récupérer le texte et supprimer les espaces inutiles
            if (numText.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Veuillez entrer un numéro de livraison!");
                return; // Arrêter l'exécution de la méthode si le numéro est vide
            }

            int num = Integer.parseInt(numText);
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            int response = JOptionPane.showConfirmDialog(this, "Voulez-vous valider la livraison ?", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (response == JOptionPane.YES_OPTION) {
                if (client.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Veuillez sélectionner un client!");
                } else if (CBX_DATE_LIVR.getDate() == null) {
                    JOptionPane.showMessageDialog(null, "Veuillez sélectionner une date!");
                } else if (TABLE_LIVRAISON.getRowCount() == 0) {
                    JOptionPane.showMessageDialog(null, "Veuillez ajouter au moins un enregistrement!");
                } else {
                    if (!db.livraisonExist(num, client)) {
                        db.ajouterLivraison(TABLE_LIVRAISON,ID);
                        db.allLivraison(TABLE_LIVR_MODIFIER);
                        db.allStock(TABLE_STOCK);
                        
                        int num1 = db.getNUMMAXLivraison(client)+1;
                        TXT_NUM_LIVR.setText(String.valueOf(num1));
                        DefaultTableModel model = (DefaultTableModel) TABLE_LIVRAISON.getModel();
                        model.setRowCount(0);
                        TXT_QUANTITE_LIVR.setText("");
                        CBX_DATE_LIVR.setDate(null);
                        JOptionPane.showMessageDialog(null, "La livraison numéro " + num + " du client " + client + " a été bien ajoutée!");
                    db.allStockAlerte(TABLE_ALERTE_STOCK);
                    } else {
                        JOptionPane.showMessageDialog(null, "La livraison numéro " + num + " du client " + client + " existe déjà!");
                    }
                }
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Erreur de saisie : Assurez-vous d'avoir saisi un nombre valide pour le numéro de livraison!");
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_BTN_VALIDER_LIVRActionPerformed

    private void BTN_ACTUALISER_LIVRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_ACTUALISER_LIVRActionPerformed
        DefaultTableModel model = (DefaultTableModel) TABLE_LIVRAISON.getModel();
        model.setRowCount(0);
    }//GEN-LAST:event_BTN_ACTUALISER_LIVRActionPerformed

    private void CBX_FOURNISSEUR_ACHATItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_CBX_FOURNISSEUR_ACHATItemStateChanged
         Object fourni=CBX_FOURNISSEUR_ACHAT.getSelectedItem();
         if(fourni!=null){
             
               int num1 = db.getNUMMAXAchat(fourni.toString())+1;
               TXT_NUM_ACHAT.setText(String.valueOf(num1));
         }
    }//GEN-LAST:event_CBX_FOURNISSEUR_ACHATItemStateChanged

    private void CBX_FOURNISSEUR_ACHATActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CBX_FOURNISSEUR_ACHATActionPerformed
        Object fourniObject = CBX_FOURNISSEUR_ACHAT.getSelectedItem();
        if (fourniObject != null) {
            String fourni  = CBX_FOURNISSEUR_ACHAT.getSelectedItem().toString();
            int id = 0;
            try {
                id = db.chercheIdFournisseur(fourni);
                db.getIdProduitPrixF(id, CBX_PRODUIT_ACHAT);
            } catch (SQLException ex) {
                Logger.getLogger(ACCEUIL1.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            System.out.println("Veuillez choisir un fournisseur.");
        }
    }//GEN-LAST:event_CBX_FOURNISSEUR_ACHATActionPerformed

    private void CBX_PRODUIT_ACHATItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_CBX_PRODUIT_ACHATItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_CBX_PRODUIT_ACHATItemStateChanged

    private void CBX_PRODUIT_ACHATActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CBX_PRODUIT_ACHATActionPerformed
        Object selectFourni =CBX_FOURNISSEUR_ACHAT.getSelectedItem();
        Object selectProduit =CBX_PRODUIT_ACHAT.getSelectedItem();
        int IDFOURNI=0;
        int IDPRODUIT=0;
        if (selectFourni != null && selectProduit !=null){
            String fourni=selectFourni.toString();
            String produit=selectProduit.toString();

            String[] splitProduit =produit.split(" ");
            String produit1 =splitProduit[0];
            String marque1 =splitProduit[1];

            try {
                IDFOURNI =db.chercheIdFournisseur(fourni);
            } catch (SQLException ex) {
                Logger.getLogger(ACCEUIL1.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                IDPRODUIT =db.chercheIdProduit(produit1,marque1);
            } catch (SQLException ex) {
                Logger.getLogger(ACCEUIL1.class.getName()).log(Level.SEVERE, null, ex);
            }

            db.allFournisseurProduit(TXT_UNITE_ACHAT,TXT_PRIX_ACHAT,IDFOURNI,IDPRODUIT);
            TXT_PRODUIT_ACHAT.setText(produit1);
            TXT_MARQUE_ACHAT.setText(marque1);
        }else{
            TXT_UNITE_ACHAT.setText("");
            TXT_PRIX_ACHAT.setText("");
        }
    }//GEN-LAST:event_CBX_PRODUIT_ACHATActionPerformed

    private void BTN_AJOUT_ACHATActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_AJOUT_ACHATActionPerformed
        boolean produitExist;
        try {
            String fourni =  CBX_FOURNISSEUR_ACHAT.getSelectedItem().toString();
            String[] splitProduit = CBX_PRODUIT_ACHAT.getSelectedItem().toString().split(" ");
            String produit = splitProduit[0];
            String marque = splitProduit[1];

            Date date = CBX_DATE_ACHAT.getJCalendar().getDate();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String dateformat = sdf.format(date);

            if (!TXT_PRODUIT_ACHAT.getText().isEmpty() && !TXT_MARQUE_ACHAT.getText().isEmpty() && !TXT_UNITE_ACHAT.getText().isEmpty() && !TXT_QUANTITE_ACHAT.getText().isEmpty() && CBX_DATE_ACHAT.getDate() != null ) {
                try {
                    Double prix = Double.parseDouble(TXT_PRIX_ACHAT.getText());
                    int unite = Integer.parseInt(TXT_UNITE_ACHAT.getText());
                    int qte = Integer.parseInt(TXT_QUANTITE_ACHAT.getText());
                    int num = Integer.parseInt(TXT_NUM_ACHAT.getText());

                    // Correction de la conversion du montant en double
                    Double montant = Double.parseDouble(LABEL_MONTANTHT_ACHAT.getText().replace(',', '.'));

                    DefaultTableModel mo = (DefaultTableModel) TABLE_ACHAT.getModel();
                    produitExist=false;
                    for (int i = 0; i < mo.getRowCount(); i++) {
                        Object prodExistObj = mo.getValueAt(i, 2); // Récupérer la valeur de la colonne du produit
                        Object marqExistObj = mo.getValueAt(i, 3); // Récupérer la valeur de la colonne de la marque

                        // Vérifier si les valeurs ne sont pas nulles avant de les convertir en chaînes de caractères
                        if (prodExistObj != null && marqExistObj != null) {
                            String prodExist = prodExistObj.toString(); // Convertir la valeur en chaîne de caractères
                            String marqExist = marqExistObj.toString(); // Convertir la valeur en chaîne de caractères

                            // Comparer les valeurs pour vérifier si le produit existe déjà
                            if (prodExist.equals(produit) && marqExist.equals(marque)) {
                                produitExist = true;
                                break; // Sortir de la boucle dès qu'on trouve le produit
                            }
                        }
                    }

                    // Vérifier si le produit existe déjà dans la liste
                    if (!produitExist) {
                        // Si le produit n'existe pas, ajouter une nouvelle ligne à la JTable
                        mo.insertRow(0, new Object[]{num, fourni, produit, marque, unite, prix, qte, montant, date});
                    } else {
                        // Si le produit existe déjà, afficher un message à l'utilisateur
                        JOptionPane.showMessageDialog(null, "Le produit " + produit + " de la marque " + marque + " est déjà ajouté!");
                    }
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }else{
                JOptionPane.showMessageDialog(null,"tu dois remplir tous les champs!");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_BTN_AJOUT_ACHATActionPerformed

    private void BTN_SUPPRIMER_ACHATActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_SUPPRIMER_ACHATActionPerformed
        int selectedRow = TABLE_ACHAT.getSelectedRow();
        if (selectedRow != -1) {
            // Supprimer la ligne sélectionnée du modèle de la JTable
            ((DefaultTableModel) TABLE_ACHAT.getModel()).removeRow(selectedRow);
        }
    }//GEN-LAST:event_BTN_SUPPRIMER_ACHATActionPerformed
   
    private void BTN_VALIDER_ACHATActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_VALIDER_ACHATActionPerformed
        try {
            String fourni = CBX_FOURNISSEUR_ACHAT.getSelectedItem().toString();

            int ID = db.chercheIdFournisseur(fourni);
            String numText = TXT_NUM_ACHAT.getText().trim(); // Récupérer le texte et supprimer les espaces inutiles
            if (numText.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Veuillez entrer un numéro d'achat!");
                return; // Arrêter l'exécution de la méthode si le numéro est vide
            }

            int num = Integer.parseInt(numText);

            int response = JOptionPane.showConfirmDialog(this, "Voulez-vous valider l'achat ?", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (response == JOptionPane.YES_OPTION) {
                if (fourni.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Veuillez sélectionner un fournisseur!");
                } else if (CBX_DATE_ACHAT.getDate() == null) {
                    JOptionPane.showMessageDialog(null, "Veuillez sélectionner une date!");
                } else if (TABLE_ACHAT.getRowCount() == 0) {
                    JOptionPane.showMessageDialog(null, "Veuillez ajouter au moins un enregistrement!");
                } else {
                    if (!db.achatExist(num, fourni)) {
                        db.ajouterAchat(TABLE_ACHAT,ID);
                        db.allAchat(TABLE_ACHAT_MODIFIER);
                        db.allStock(TABLE_STOCK);
                        
                        int num1 = db.getNUMMAXAchat(fourni)+1;
                        TXT_NUM_ACHAT.setText(String.valueOf(num1));
                        DefaultTableModel model = (DefaultTableModel) TABLE_ACHAT.getModel();
                        model.setRowCount(0);
                        TXT_QUANTITE_ACHAT.setText("");
                        CBX_DATE_ACHAT.setDate(null); // Définir la date du JDateChooser

                        JOptionPane.showMessageDialog(this, "L'achat numéro " + num + " du fournisseur " + fourni + " a été bien ajoutée!");
                        db.allStockAlerte(TABLE_ALERTE_STOCK);
                    } else {
                        JOptionPane.showMessageDialog(null, "L'achat numéro " + num + " du fournisseur " + fourni + " existe déjà!");
                    }
                }
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Erreur de saisie : Assurez-vous d'avoir saisi un nombre valide pour le numéro d'achat!");
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_BTN_VALIDER_ACHATActionPerformed

    private void BTN_ACTUALISER_ACHATActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_ACTUALISER_ACHATActionPerformed
        DefaultTableModel model = (DefaultTableModel) TABLE_ACHAT.getModel();
        model.setRowCount(0);
    }//GEN-LAST:event_BTN_ACTUALISER_ACHATActionPerformed

    private void CBX_FOURNISSEUR_RECHERCHE_ACHATItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_CBX_FOURNISSEUR_RECHERCHE_ACHATItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_CBX_FOURNISSEUR_RECHERCHE_ACHATItemStateChanged

    private void CBX_FOURNISSEUR_RECHERCHE_ACHATActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CBX_FOURNISSEUR_RECHERCHE_ACHATActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CBX_FOURNISSEUR_RECHERCHE_ACHATActionPerformed

    private void BTN_RECHERCHE_ACHATActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_RECHERCHE_ACHATActionPerformed
        Object fournisseurSelectionne = CBX_FOURNISSEUR_RECHERCHE_ACHAT.getSelectedItem();
        String num=TXT_RECHERCHE_ACHAT.getText();
        Date date=CBX_DATE_ACHAT_RECHERCHE.getDate();

        if (CheckBox_NUM_ACHAT.isSelected()&& !CheckBox_DATE_ACHAT.isSelected()&& fournisseurSelectionne != null && !num.isEmpty()) {
            int numeroAchat = Integer.parseInt(num);
            String fourni=fournisseurSelectionne.toString();
            try {
                int id = db.chercheIdFournisseur(fourni);
                db.chercheAchatByFournisseurNum(id, numeroAchat, TABLE_ACHAT_MODIFIER);
            } catch (SQLException ex) {
                Logger.getLogger(ACCEUIL1.class.getName()).log(Level.SEVERE, null, ex);
            }

        }else if (CheckBox_DATE_ACHAT.isSelected() && !CheckBox_NUM_ACHAT.isSelected() && fournisseurSelectionne != null && date != null) {
            // Si la case de date est sélectionnée, un fournisseur est sélectionné et une date est définie
            String fournisseur = fournisseurSelectionne.toString();
            try {
                int id = db.chercheIdFournisseur(fournisseur);

                // Conversion de java.util.Date en java.sql.Date si nécessaire
                java.sql.Date sqlDate;
                if (date instanceof java.sql.Date) {
                    sqlDate = (java.sql.Date) date; // Si déjà de type java.sql.Date, pas besoin de conversion
                } else {
                    sqlDate = new java.sql.Date(date.getTime()); // Convertir de java.util.Date à java.sql.Date
                }

                // Utilisation de la méthode chercherAchatByFournisseurDate avec la date appropriée
                db.chercheAchatByFournisseurDate(id, sqlDate, TABLE_ACHAT_MODIFIER);
            } catch (SQLException ex) {
                Logger.getLogger(ACCEUIL1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if (CheckBox_DATE_ACHAT.isSelected() && CheckBox_NUM_ACHAT.isSelected() && fournisseurSelectionne != null && date != null && !num.isEmpty()) {
            // Si la case de date est sélectionnée, un fournisseur est sélectionné et une date est définie
            String fournisseur = fournisseurSelectionne.toString();
            try {
                int id = db.chercheIdFournisseur(fournisseur);

                // Conversion de java.util.Date en java.sql.Date si nécessaire
                java.sql.Date sqlDate;
                if (date instanceof java.sql.Date) {
                    sqlDate = (java.sql.Date) date; // Si déjà de type java.sql.Date, pas besoin de conversion
                } else {
                    sqlDate = new java.sql.Date(date.getTime()); // Convertir de java.util.Date à java.sql.Date
                }

                // Utilisation de la méthode chercherAchatByFournisseurDate avec la date appropriée
                db.chercheAchatByFournisseurDateNum(id, Integer.parseInt(num),sqlDate, TABLE_ACHAT_MODIFIER);
            } catch (SQLException ex) {
                Logger.getLogger(ACCEUIL1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{

            try {
                db.allAchat(TABLE_ACHAT_MODIFIER);
            } catch (SQLException ex) {
                Logger.getLogger(ACCEUIL1.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }//GEN-LAST:event_BTN_RECHERCHE_ACHATActionPerformed

    private void CBX_TYPE_MODIFIER_ACHATItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_CBX_TYPE_MODIFIER_ACHATItemStateChanged
        String type=CBX_TYPE_MODIFIER_ACHAT.getSelectedItem().toString();
        if ("num".equals(type)) { // Vérifier si "num" est sélectionné
            // Rendre le champ de numéro d'achat éditable
            TXT_NUM_ACHAT_MODIFIER.setEditable(true);
            // Rendre les autres champs non éditables
            TXT_QUANTITE_ACHAT_MODIFIER.setEditable(false);
        }else if("date".equals(type)){
            TXT_NUM_ACHAT_MODIFIER.setEditable(false);
            // Rendre les autres champs non éditables
            TXT_QUANTITE_ACHAT_MODIFIER.setEditable(false);
        }else{
            TXT_NUM_ACHAT_MODIFIER.setEditable(false);
            // Rendre les autres champs non éditables
            TXT_QUANTITE_ACHAT_MODIFIER.setEditable(true);
        }
    }//GEN-LAST:event_CBX_TYPE_MODIFIER_ACHATItemStateChanged

    private void CBX_TYPE_MODIFIER_ACHATActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CBX_TYPE_MODIFIER_ACHATActionPerformed
        String type=CBX_TYPE_MODIFIER_ACHAT.getSelectedItem().toString();
        if ("num".equals(type)) { // Vérifier si "num" est sélectionné
            // Rendre le champ de numéro d'achat éditable
            TXT_NUM_ACHAT_MODIFIER.setEditable(true);
            // Rendre les autres champs non éditables
            TXT_QUANTITE_ACHAT_MODIFIER.setEditable(false);
        }else if("date".equals(type)){
            TXT_NUM_ACHAT_MODIFIER.setEditable(false);
            // Rendre les autres champs non éditables
            TXT_QUANTITE_ACHAT_MODIFIER.setEditable(false);
        }else{
            TXT_NUM_ACHAT_MODIFIER.setEditable(false);
            // Rendre les autres champs non éditables
            TXT_QUANTITE_ACHAT_MODIFIER.setEditable(true);
        }
    }//GEN-LAST:event_CBX_TYPE_MODIFIER_ACHATActionPerformed

    private void BTN_MODIFIER_ACHATActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_MODIFIER_ACHATActionPerformed
        int num2=0 ;

        try {
            String fourni =  TXT_FOURNISSEUR_ACHAT_MODIFIER.getText();
            String produit =  TXT_PRODUIT_ACHAT_MODIFIER.getText();
            String marque =  TXT_MARQUE_ACHAT_MODIFIER.getText();
            String quantite =TXT_QUANTITE_ACHAT_MODIFIER.getText();
            Date date = CBX_DATE_ACHAT_MODIFIER.getJCalendar().getDate();

            int id = db.chercheIdFournisseur(fourni);

            if (!fourni.isEmpty() && !produit.isEmpty() && !quantite.isEmpty()) {
                String type=CBX_TYPE_MODIFIER_ACHAT.getSelectedItem().toString();
                num2=Integer.parseInt(ID.getText());
                try {
                    if("num".equals(type)){

                        // Convertir java.util.Date en java.sql.Date
                        java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                        Double prix = Double.parseDouble(TXT_PRIX_ACHAT_MODIFIER.getText());
                        int unite = Integer.parseInt(TXT_UNITE_ACHAT_MODIFIER.getText());
                        int qte = Integer.parseInt(quantite);
                        int num1 = Integer.parseInt(TXT_NUM_ACHAT_MODIFIER.getText());

                        // Correction de la conversion du montant en double
                        Double montant = Double.parseDouble(TXT_MONTANT_ACHAT_MODIFIER.getText().replace(',', '.'));
                        System.out.println(num2);
                        Achat achat = new Achat(num1,produit,marque,unite,prix,qte,montant,sqlDate,id,fourni);
                        db.modifierAchatByNum(id,num2,achat);
                        db.allAchat(TABLE_ACHAT_MODIFIER);
                    }else if("date".equals(type)){
                        TXT_NUM_ACHAT_MODIFIER.setEditable(false);
                        TXT_QUANTITE_ACHAT_MODIFIER.setEditable(false);
                        // Convertir java.util.Date en java.sql.Date
                        java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                        Double prix = Double.parseDouble(TXT_PRIX_ACHAT_MODIFIER.getText());
                        int unite = Integer.parseInt(TXT_UNITE_ACHAT_MODIFIER.getText());
                        int qte = Integer.parseInt(quantite);

                        // Correction de la conversion du montant en double
                        Double montant = Double.parseDouble(TXT_MONTANT_ACHAT_MODIFIER.getText().replace(',', '.'));
                        Achat achat = new Achat(num2,produit,marque,unite,prix,qte,montant,sqlDate,id,fourni);
                        db.modifierAchatByDate(id,num2,achat);
                        db.allAchat(TABLE_ACHAT_MODIFIER);
                    }else{
                        TXT_NUM_ACHAT_MODIFIER.setEditable(false);
                        TXT_QUANTITE_ACHAT_MODIFIER.setEditable(true);
                        // Convertir java.util.Date en java.sql.Date
                        java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                        Double prix = Double.parseDouble(TXT_PRIX_ACHAT_MODIFIER.getText());
                        int unite = Integer.parseInt(TXT_UNITE_ACHAT_MODIFIER.getText());
                        int qte = Integer.parseInt(quantite);

                        // Correction de la conversion du montant en double
                        Double montant = Double.parseDouble(TXT_MONTANT_ACHAT_MODIFIER.getText().replace(',', '.'));
                        Achat achat = new Achat(num2,produit,marque,unite,prix,qte,montant,sqlDate,id,fourni);
                        db.modifierAchatByQTE(id,num2,achat);
                        db.allAchat(TABLE_ACHAT_MODIFIER);
                        db.allStockAlerte(TABLE_ALERTE_STOCK);
                    }
                    db.allStock(TABLE_STOCK);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(null,"Tu dois remplir tous les champs!");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_BTN_MODIFIER_ACHATActionPerformed

    private void BTN_SUPPRIMER_ACHATOFFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_SUPPRIMER_ACHATOFFActionPerformed
       String fourni = TXT_FOURNISSEUR_ACHAT_MODIFIER.getText();
        if (CHECKBOX_SUPP_NUM_ACHAT.isSelected()&& !CHECKBOX_SUPP_PRODUIT_ACHAT.isSelected()&& fourni != null  ) {
        if(!fourni.isEmpty()){
            int id=0;
            try {
                id = db.chercheIdFournisseur(fourni);
            } catch (SQLException ex) {
                Logger.getLogger(ACCEUIL1.class.getName()).log(Level.SEVERE, null, ex);
            }
            db.supprimerAchat(Integer.parseInt(TXT_NUM_ACHAT_MODIFIER.getText()),id);
            int NUMMAX = db.getNUMMAXAchat(fourni)+1;
            try {
                db.allAchat(TABLE_ACHAT_MODIFIER);
                TXT_NUM_ACHAT.setText(NUMMAX+"");
            } catch (SQLException ex) {
                Logger.getLogger(ACCEUIL1.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                db.allStock(TABLE_STOCK);
                db.allStockAlerte(TABLE_ALERTE_STOCK);
            } catch (SQLException ex) {
                Logger.getLogger(ACCEUIL1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            JOptionPane.showMessageDialog(null,"tu dois selectionner une achat !");
        }
        }else if (CHECKBOX_SUPP_NUM_ACHAT.isSelected()&& CHECKBOX_SUPP_PRODUIT_ACHAT.isSelected()&& fourni != null){
            if(!fourni.isEmpty()){
                String prod = TXT_PRODUIT_ACHAT_MODIFIER.getText();
                String marq = TXT_MARQUE_ACHAT_MODIFIER.getText();
                int id=0;
                try {
                    id = db.chercheIdFournisseur(fourni);
                } catch (SQLException ex) {
                    Logger.getLogger(ACCEUIL1.class.getName()).log(Level.SEVERE, null, ex);
                }
                db.supprimerAchatByProduit(Integer.parseInt(TXT_NUM_ACHAT_MODIFIER.getText()),id,prod,marq);
                int NUMMAX = db.getNUMMAXAchat(fourni)+1;
                try {
                    db.allAchat(TABLE_ACHAT_MODIFIER);
                    TXT_NUM_ACHAT.setText(NUMMAX+"");
                } catch (SQLException ex) {
                    Logger.getLogger(ACCEUIL1.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    db.allStock(TABLE_STOCK);
                    db.allStockAlerte(TABLE_ALERTE_STOCK);
                } catch (SQLException ex) {
                    Logger.getLogger(ACCEUIL1.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                JOptionPane.showMessageDialog(null,"tu dois selectionner une achat !");
            }
        }else{
            JOptionPane.showMessageDialog(null,"tu peux^pas supprimer par peroduit seulement !");
        }
    }//GEN-LAST:event_BTN_SUPPRIMER_ACHATOFFActionPerformed

    private void BTN_ACTUALISER_CLIENTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_ACTUALISER_CLIENTActionPerformed
        int IDMAX=db.getIDMaxClient()+1;
        TXT_ID_CLIENT.setText(String.valueOf(IDMAX));
        TXT_NOM_CLIENT.setText("");
        TXT_PRENOM_CLIENT.setText("");
        TXT_ADRESSE_CLIENT.setText("");
        TXT_EMAIL_CLIENT.setText("");
        TXT_TELEPHONE_CLIENT.setText("");
        TXT_CNR_CLIENT.setText("");
        TXT_NIF_CLIENT.setText("");
    }//GEN-LAST:event_BTN_ACTUALISER_CLIENTActionPerformed

    private void BTN_MODIFIER_CLIENTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_MODIFIER_CLIENTActionPerformed
        String id = TXT_ID_CLIENT.getText();
        String nom = TXT_NOM_CLIENT.getText();
        String prenom = TXT_PRENOM_CLIENT.getText();
        String adresse = TXT_ADRESSE_CLIENT.getText();
        String email = TXT_EMAIL_CLIENT.getText();
        String telephone = TXT_TELEPHONE_CLIENT.getText();
        String CNR = TXT_CNR_CLIENT.getText();
        String NIF = TXT_NIF_CLIENT.getText();
        int idClient = 0;
        try {
            idClient = Integer.parseInt(TXT_ID_CLIENT.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Tu dois entrer un nombre.", "Succès", JOptionPane.INFORMATION_MESSAGE);
            return; // Sortir de la méthode si l'ID client n'est pas valide
        }
        try{
            if (!nom.isEmpty() && !prenom.isEmpty() && !id.isEmpty()){
                Client client = new Client(idClient,nom,prenom,adresse,email,telephone,CNR,NIF);
                db.updateClient(idClient,client);
                db.allClient(TABLE_CLIENT);

                CBX_CLIENT_LIVR.removeAll();
                db.getAllClient(CBX_CLIENT_LIVR);

                CBX_CLIENT_VERSEMENT.removeAllItems();
                db.getAllClient(CBX_CLIENT_VERSEMENT);
                CBX_CLIENT_RECHERCHE_LIVR.removeAllItems();
                db.getAllClient(CBX_CLIENT_RECHERCHE_LIVR);
                CBX_CLIENT_PRIX.removeAllItems();
                db.getAllClient(CBX_CLIENT_PRIX);

                int IDMAX=db.getIDMaxClient();
                TXT_ID_CLIENT.setText(String.valueOf(IDMAX));
                TXT_NOM_CLIENT.setText("");
                TXT_PRENOM_CLIENT.setText("");
                TXT_ADRESSE_CLIENT.setText("");
                TXT_EMAIL_CLIENT.setText("");
                TXT_TELEPHONE_CLIENT.setText("");
                TXT_CNR_CLIENT.setText("");
                TXT_NIF_CLIENT.setText("");
            }else{
                JOptionPane.showMessageDialog(null, "veuillez selectionner un client de la table!");
            }
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null, "ID invalide!");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "erreur d'operation!");
        }
    }//GEN-LAST:event_BTN_MODIFIER_CLIENTActionPerformed

    private void BTN_SUPPRIMER_CLIENTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_SUPPRIMER_CLIENTActionPerformed
        try{
            String id =TXT_ID_CLIENT.getText();
            String nom =TXT_NOM_CLIENT.getText();
            String prenom =TXT_PRENOM_CLIENT.getText();
            String adresse =TXT_ADRESSE_CLIENT.getText();
            String email =TXT_EMAIL_CLIENT.getText();
            String telephone =TXT_TELEPHONE_CLIENT.getText();

            int idClient = 0;
            try {
                idClient = Integer.parseInt(TXT_ID_CLIENT.getText());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Tu dois entrer un nombre.", "Succès", JOptionPane.INFORMATION_MESSAGE);
                return; // Sortir de la méthode si l'ID client n'est pas valide
            }

            if (!id.isEmpty() && !nom.isEmpty() && !prenom.isEmpty()){
                if(JOptionPane.showConfirmDialog(null, "confirmer la suppression ?", "suppression",JOptionPane.YES_NO_OPTION)== JOptionPane.YES_OPTION){
                    db.supprimerClient(idClient);
                    db.allClient(TABLE_CLIENT);
                    db.allClientVersement(TABLE_CLIENT_VERSEMENT);
                    db.allClientProduitTable(TABLE_CLIENT_PRIX);
                    CBX_CLIENT_LIVR.removeAllItems();
                    db.getAllClient(CBX_CLIENT_LIVR);

                    CBX_CLIENT_VERSEMENT.removeAllItems();
                    db.getAllClient(CBX_CLIENT_VERSEMENT);
                    CBX_CLIENT_RECHERCHE_LIVR.removeAllItems();
                    db.getAllClient(CBX_CLIENT_RECHERCHE_LIVR);
                    CBX_CLIENT_PRIX.removeAllItems();
                    db.getAllClient(CBX_CLIENT_PRIX);

                    int IDMAX = db.getIDMaxClient();
                    TXT_ID_CLIENT.setText(String.valueOf(IDMAX+1));
                    TXT_NOM_CLIENT.setText("");
                    TXT_PRENOM_CLIENT.setText("");
                    TXT_ADRESSE_CLIENT.setText("");
                    TXT_EMAIL_CLIENT.setText("");
                    TXT_TELEPHONE_CLIENT.setText("");
                    TXT_CNR_CLIENT.setText("");
                    TXT_NIF_CLIENT.setText("");
                }else{
                    int IDMAX = db.getIDMaxClient();
                    TXT_ID_CLIENT.setText(String.valueOf(IDMAX+1));
                    TXT_NOM_CLIENT.setText("");
                    TXT_PRENOM_CLIENT.setText("");
                    TXT_ADRESSE_CLIENT.setText("");
                    TXT_EMAIL_CLIENT.setText("");
                    TXT_TELEPHONE_CLIENT.setText("");
                    TXT_CNR_CLIENT.setText("");
                    TXT_NIF_CLIENT.setText("");
                }
            }else{
                JOptionPane.showMessageDialog(null, "veuiller selectionner un client de la table!");
            }

        }catch(HeadlessException | NumberFormatException e){
            JOptionPane.showMessageDialog(null, "erreur d'operation!");
        } catch (SQLException ex) {
            Logger.getLogger(ACCEUIL1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_BTN_SUPPRIMER_CLIENTActionPerformed

    private void BTN_AJOUT_CLIENTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_AJOUT_CLIENTActionPerformed
        int idClient = 0;
        try {
            idClient = Integer.parseInt(TXT_ID_CLIENT.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Tu dois entrer un nombre.", "Succès", JOptionPane.INFORMATION_MESSAGE);
            return; // Sortir de la méthode si l'ID client n'est pas valide
        }
        String nomClient = TXT_NOM_CLIENT.getText();
        String prenomClient = TXT_PRENOM_CLIENT.getText();
        String adresseClient = TXT_ADRESSE_CLIENT.getText();
        String emailClient = TXT_EMAIL_CLIENT.getText();
        String telephoneClient = TXT_TELEPHONE_CLIENT.getText();
        String NCR = TXT_CNR_CLIENT.getText();
        String NIF = TXT_NIF_CLIENT.getText();
        if(idClient>0 && !nomClient.isEmpty() && !prenomClient.isEmpty() && !telephoneClient.isEmpty()){
            int response =JOptionPane.showConfirmDialog(this, "voulez vous vraiment ajouter ce client?", "Confirmation", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
            if (response == JOptionPane.YES_OPTION){
                if(!db.clientExist(nomClient,prenomClient)){
                    try{
                        Client client = new Client(idClient, nomClient, prenomClient, adresseClient, emailClient, telephoneClient,NCR,NIF);
                        db.ajout_client(client);
                        db.allClient(TABLE_CLIENT);
                        int IDMAX=db.getIDMaxClient()+1;
                        TXT_ID_CLIENT.setText(String.valueOf(IDMAX));
                        TXT_NOM_CLIENT.setText("");
                        TXT_PRENOM_CLIENT.setText("");
                        TXT_ADRESSE_CLIENT.setText("");
                        TXT_EMAIL_CLIENT.setText("");
                        TXT_TELEPHONE_CLIENT.setText("");
                        TXT_CNR_CLIENT.setText("");
                        TXT_NIF_CLIENT.setText("");
                        JOptionPane.showMessageDialog(null, "le client " +nomClient+ " "+ prenomClient+" a été bien ajouter!");

                        CBX_CLIENT_LIVR.removeAllItems();
                        db.getAllClient(CBX_CLIENT_LIVR);
                        CBX_CLIENT_RECHERCHE_LIVR.removeAllItems();
                        db.getAllClient(CBX_CLIENT_RECHERCHE_LIVR);
                        CBX_CLIENT_PRIX.removeAllItems();
                        db.getAllClient(CBX_CLIENT_PRIX);

                        CBX_CLIENT_VERSEMENT.removeAllItems();
                        db.getAllClient(CBX_CLIENT_VERSEMENT);
                    }catch(NumberFormatException e){
                        JOptionPane.showMessageDialog(null, "erreur de saisie!");
                    } catch (SQLException ex) {
                        Logger.getLogger(ACCEUIL1.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "le client " +nomClient+ " "+ prenomClient+" existe deja!");
                }
            }else if(response == JOptionPane.NO_OPTION){
                int IDMAX=db.getIDMaxClient()+1;
                TXT_ID_CLIENT.setText(String.valueOf(IDMAX));
                TXT_NOM_CLIENT.setText("");
                TXT_PRENOM_CLIENT.setText("");
                TXT_ADRESSE_CLIENT.setText("");
                TXT_EMAIL_CLIENT.setText("");
                TXT_TELEPHONE_CLIENT.setText("");
                TXT_CNR_CLIENT.setText("");
                TXT_NIF_CLIENT.setText("");
            }
        }else{
            JOptionPane.showMessageDialog(null, "tu dois remplir les champs *nom* *prenom* *telephone*");
        }
    }//GEN-LAST:event_BTN_AJOUT_CLIENTActionPerformed

    private void BTN_AJOUT_FOURNISSEURActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_AJOUT_FOURNISSEURActionPerformed
        int idFourni = 0;
        try {
            idFourni = Integer.parseInt(TXT_ID_FOURNISSEUR.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Tu dois entrer un nombre.", "Succès", JOptionPane.INFORMATION_MESSAGE);
            return; // Sortir de la méthode si l'ID client n'est pas valide
        }
        String fourni = TXT_NOM_FOURNISSEUR.getText();

        String adresseFourni = TXT_ADRESSE_FOURNISSEUR.getText();
        String emailFourni = TXT_EMAIL_FOURNISSEUR.getText();
        String telephoneFourni = TXT_TELEPHONE_FOURNISSEUR.getText();
        String CNR = TXT_CNR_FOURNISSEUR.getText();
        String NIF = TXT_NIF_FOURNISSEUR.getText();
        if(idFourni>0 && !fourni.isEmpty()  && !telephoneFourni.isEmpty()){
            int response =JOptionPane.showConfirmDialog(this, "voulez vous vraiment ajouter ce fournisseur?", "Confirmation", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
            if (response == JOptionPane.YES_OPTION){
                if(!db.fournisseurExist(fourni)){
                    try{
                        Fournisseur fournisseur = new Fournisseur(idFourni, fourni, adresseFourni, emailFourni, telephoneFourni,CNR,NIF);
                        db.ajout_fournisseur(fournisseur);
                        db.allFournisseur(TABLE_FOURNISSEUR);
                        int IDMAX=db.getIDMaxFournisseur()+1;
                        TXT_ID_FOURNISSEUR.setText(String.valueOf(IDMAX));
                        TXT_NOM_FOURNISSEUR.setText("");

                        TXT_ADRESSE_FOURNISSEUR.setText("");
                        TXT_EMAIL_FOURNISSEUR.setText("");
                        TXT_TELEPHONE_FOURNISSEUR.setText("");
                        TXT_CNR_FOURNISSEUR.setText("");
                        TXT_NIF_FOURNISSEUR.setText("");
                        JOptionPane.showMessageDialog(null, "le fournisseur " +fourni+ " a été bien ajouter!");

                        CBX_FOURNISSEUR_ACHAT.removeAllItems();
                        db.getAllFournisseur(CBX_FOURNISSEUR_ACHAT);

                        CBX_FOURNISSEUR_VERSEMENT.removeAllItems();
                        db.getAllFournisseur(CBX_FOURNISSEUR_VERSEMENT);

                        CBX_FOURNISSEUR_RECHERCHE_ACHAT.removeAllItems();
                        db.getAllFournisseur(CBX_FOURNISSEUR_RECHERCHE_ACHAT);

                        CBX_FOURNISSEUR_PRIX.removeAllItems();
                        db.getAllFournisseur(CBX_FOURNISSEUR_PRIX);

                        CBX_FOURNISSEUR_VERSEMENT.removeAllItems();
                        db.getAllFournisseur(CBX_FOURNISSEUR_VERSEMENT);
                    }catch(NumberFormatException e){
                        JOptionPane.showMessageDialog(null, "erreur de saisie!");
                    } catch (SQLException ex) {
                        Logger.getLogger(ACCEUIL1.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "le fournisseur " +fourni+ " existe deja!");
                }
            }else if(response == JOptionPane.NO_OPTION){
                int IDMAX=db.getIDMaxFournisseur()+1;
                TXT_ID_FOURNISSEUR.setText(String.valueOf(IDMAX));
                TXT_NOM_FOURNISSEUR.setText("");

                TXT_ADRESSE_FOURNISSEUR.setText("");
                TXT_EMAIL_FOURNISSEUR.setText("");
                TXT_TELEPHONE_FOURNISSEUR.setText("");;
            }
        }else{
            JOptionPane.showMessageDialog(null, "tu dois remplir les champs *nom* *prenom* *telephone*");
        }
    }//GEN-LAST:event_BTN_AJOUT_FOURNISSEURActionPerformed

    private void BTN_SUPPRIMER_FOURNISSEURActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_SUPPRIMER_FOURNISSEURActionPerformed
        try{
            String id =TXT_ID_FOURNISSEUR.getText();
            String nom =TXT_NOM_FOURNISSEUR.getText();

            String adresse =TXT_ADRESSE_FOURNISSEUR.getText();
            String email =TXT_EMAIL_FOURNISSEUR.getText();
            String telephone =TXT_TELEPHONE_FOURNISSEUR.getText();

            int idFOURNI = 0;
            try {
                idFOURNI = Integer.parseInt(TXT_ID_FOURNISSEUR.getText());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Tu dois entrer un nombre.", "Succès", JOptionPane.INFORMATION_MESSAGE);
                return; // Sortir de la méthode si l'ID client n'est pas valide
            }

            if (!id.isEmpty() && !nom.isEmpty() ){
                if(JOptionPane.showConfirmDialog(null, "confirmer la suppression ?", "suppression",JOptionPane.YES_NO_OPTION)== JOptionPane.YES_OPTION){
                    db.supprimerFournisseur(idFOURNI);
                    db.allFournisseur(TABLE_FOURNISSEUR);
                    db.allFournisseurVersement(TABLE_FOURNISSEUR_VERSEMENT);
                    db.allFournisseurProduitTable(TABLE_FOURNISSEUR_PRIX);
                    CBX_FOURNISSEUR_ACHAT.removeAllItems();
                    db.getAllFournisseur(CBX_FOURNISSEUR_ACHAT);

                    CBX_FOURNISSEUR_VERSEMENT.removeAllItems();
                    db.getAllFournisseur(CBX_FOURNISSEUR_VERSEMENT);

                    CBX_FOURNISSEUR_PRIX.removeAllItems();
                    db.getAllFournisseur(CBX_FOURNISSEUR_PRIX);

                    CBX_FOURNISSEUR_RECHERCHE_ACHAT.removeAllItems();
                    db.getAllFournisseur(CBX_FOURNISSEUR_RECHERCHE_ACHAT);
                    int IDMAX = db.getIDMaxFournisseur();
                    TXT_ID_FOURNISSEUR.setText(String.valueOf(IDMAX+1));
                    TXT_NOM_FOURNISSEUR.setText("");

                    TXT_ADRESSE_FOURNISSEUR.setText("");
                    TXT_EMAIL_FOURNISSEUR.setText("");
                    TXT_TELEPHONE_FOURNISSEUR.setText("");
                    TXT_CNR_FOURNISSEUR.setText("");
                    TXT_NIF_FOURNISSEUR.setText("");
                }else{
                    int IDMAX = db.getIDMaxFournisseur();
                    TXT_ID_FOURNISSEUR.setText(String.valueOf(IDMAX+1));
                    TXT_NOM_FOURNISSEUR.setText("");

                    TXT_ADRESSE_FOURNISSEUR.setText("");
                    TXT_EMAIL_FOURNISSEUR.setText("");
                    TXT_TELEPHONE_FOURNISSEUR.setText("");
                    TXT_CNR_FOURNISSEUR.setText("");
                    TXT_NIF_FOURNISSEUR.setText("");
                }
            }else{
                JOptionPane.showMessageDialog(null, "veuiller selectionner un fournisseur de la table!");
            }

        }catch(HeadlessException | NumberFormatException e){
            JOptionPane.showMessageDialog(null, "erreur d'operation!");
        } catch (SQLException ex) {
            Logger.getLogger(ACCEUIL1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_BTN_SUPPRIMER_FOURNISSEURActionPerformed

    private void BTN_MODIFIER_FOURNISSEURActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_MODIFIER_FOURNISSEURActionPerformed
        String id = TXT_ID_FOURNISSEUR.getText();
        String nom = TXT_NOM_FOURNISSEUR.getText();

        String adresse = TXT_ADRESSE_FOURNISSEUR.getText();
        String email = TXT_EMAIL_FOURNISSEUR.getText();
        String telephone = TXT_TELEPHONE_FOURNISSEUR.getText();
        String CNR = TXT_CNR_FOURNISSEUR.getText();
        String NIF = TXT_NIF_FOURNISSEUR.getText();
        int idFOURNI = 0;
        try {
            idFOURNI = Integer.parseInt(TXT_ID_FOURNISSEUR.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Tu dois entrer un nombre.", "Succès", JOptionPane.INFORMATION_MESSAGE);
            return; // Sortir de la méthode si l'ID client n'est pas valide
        }
        try{
            if (!nom.isEmpty() && !id.isEmpty()){
                Fournisseur fourni = new Fournisseur(idFOURNI,nom,adresse,email,telephone,CNR,NIF);
                db.updateFournisseur(idFOURNI,fourni);
                db.allFournisseur(TABLE_FOURNISSEUR);

                CBX_FOURNISSEUR_ACHAT.removeAll();
                db.getAllFournisseur(CBX_FOURNISSEUR_ACHAT);

                CBX_FOURNISSEUR_VERSEMENT.removeAllItems();
                db.getAllFournisseur(CBX_FOURNISSEUR_VERSEMENT);

                CBX_FOURNISSEUR_PRIX.removeAllItems();
                db.getAllFournisseur(CBX_FOURNISSEUR_PRIX);

                CBX_FOURNISSEUR_RECHERCHE_ACHAT.removeAllItems();
                db.getAllFournisseur(CBX_FOURNISSEUR_RECHERCHE_ACHAT);
                int IDMAX=db.getIDMaxFournisseur();
                TXT_ID_FOURNISSEUR.setText(String.valueOf(IDMAX));
                TXT_NOM_FOURNISSEUR.setText("");

                TXT_ADRESSE_FOURNISSEUR.setText("");
                TXT_EMAIL_FOURNISSEUR.setText("");
                TXT_TELEPHONE_FOURNISSEUR.setText("");
                TXT_CNR_FOURNISSEUR.setText("");
                TXT_NIF_FOURNISSEUR.setText("");
            }else{
                JOptionPane.showMessageDialog(null, "veuillez selectionner un fournisseur de la table!");
            }
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null, "ID invalide!");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "erreur d'operation!");
        }
    }//GEN-LAST:event_BTN_MODIFIER_FOURNISSEURActionPerformed

    private void BTN_ACTUALISER_CLIENT1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_ACTUALISER_CLIENT1ActionPerformed
        int IDMAX=db.getIDMaxFournisseur()+1;
        TXT_ID_FOURNISSEUR.setText(String.valueOf(IDMAX));
        TXT_NOM_FOURNISSEUR.setText("");
        TXT_ADRESSE_FOURNISSEUR.setText("");
        TXT_EMAIL_FOURNISSEUR.setText("");
        TXT_TELEPHONE_FOURNISSEUR.setText("");
        TXT_CNR_FOURNISSEUR.setText("");
        TXT_NIF_FOURNISSEUR.setText("");
    }//GEN-LAST:event_BTN_ACTUALISER_CLIENT1ActionPerformed

    private void BTN_AJOUT_VERS_CLIENTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_AJOUT_VERS_CLIENTActionPerformed
        double montant=0.00;
        int IDCLIENT=0;
        String client = CBX_CLIENT_VERSEMENT.getSelectedItem().toString();
        String splitClient[]=client.split(" ");
        String nom = splitClient[0];
        String prenom = splitClient[1];
        int num = Integer.parseInt(TXT_NUM_VERSEMENT_CLIENT.getText());
        try {
            IDCLIENT = db.chercheIdClient(nom, prenom);
        } catch (SQLException ex) {
            Logger.getLogger(ACCEUIL1.class.getName()).log(Level.SEVERE, null, ex);
        }
        Date date = CBX_DATE_VERSEMENT_CLIENT.getJCalendar().getDate();
        java.sql.Date dateSql = new java.sql.Date(date.getTime());
        String montantString = TXT_VERSEMENT_CLIENT.getText();
        try{
            montant = Double.parseDouble(montantString);
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null, "le prix dois etre un double valide!");
        }

        if(IDCLIENT>0 && CBX_DATE_VERSEMENT_CLIENT.getDate() != null && !montantString.isEmpty() && num>0){
            int response =JOptionPane.showConfirmDialog(this, "voulez vous vraiment ajouter ce client?", "Confirmation", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
            if (response == JOptionPane.YES_OPTION){

                try{
                    VersementClient versement = new VersementClient(IDCLIENT,client,num, dateSql, montant);
                    db.ajout_versement_client(versement);
                    db.allClientVersement(TABLE_CLIENT_VERSEMENT);
                    int num1 =db.getNumVersementClient()+1;
                    TXT_NUM_VERSEMENT_CLIENT.setText(num1+"");
                    db.allClientVersement(TABLE_CLIENT_VERSEMENT);
                    TXT_VERSEMENT_CLIENT.setText("");

                    JOptionPane.showMessageDialog(null, "le versement pout client " +client+" a été bien ajouter!");

                }catch(NumberFormatException e){
                    JOptionPane.showMessageDialog(null, "erreur de saisie!");
                }

            }else if(response == JOptionPane.NO_OPTION){
                int num1 =db.getNumVersementClient()+1;
                TXT_NUM_VERSEMENT_CLIENT.setText(num1+"");
                db.allClientVersement(TABLE_CLIENT_VERSEMENT);
                TXT_VERSEMENT_CLIENT.setText("");
            }
        }else{
            JOptionPane.showMessageDialog(null, "tu dois remplir les champs *client* *date* *VERS N°*");
        }
    }//GEN-LAST:event_BTN_AJOUT_VERS_CLIENTActionPerformed

    private void BTN_MODIFIER_VERS_CLIENTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_MODIFIER_VERS_CLIENTActionPerformed
       Object client = CBX_CLIENT_VERSEMENT.getSelectedItem();

if (client != null) {
    String cli = client.toString();
    String[] splitClient = cli.split(" ");
    if (splitClient.length >= 2) {
        String nom = splitClient[0];
        String prenom = splitClient[1];
        int idclient = 0;
        double prix = 0.00;
        int num = 0;
        try {
            String versementText = TXT_VERSEMENT_CLIENT.getText().replace(',', '.');
            prix = Double.parseDouble(versementText);

            num = Integer.parseInt(TXT_NUM_VERSEMENT_CLIENT.getText());
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
            // Handle the error, e.g., display an error message to the user
        }

        try {
            idclient = db.chercheIdClient(nom, prenom);
        } catch (SQLException ex) {
            Logger.getLogger(ACCEUIL1.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (idclient != 0 && prix != 0.00 && num != 0) {
            Date date = CBX_DATE_VERSEMENT_CLIENT.getJCalendar().getDate();
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());

            try {
                db.modifierVersementClient(idclient, num, sqlDate, prix);
                db.allClientVersement(TABLE_CLIENT_VERSEMENT);
                TXT_VERSEMENT_CLIENT.setText("");
                int num1 = db.getNumVersementClient() + 1;
                TXT_NUM_VERSEMENT_CLIENT.setText(String.valueOf(num1));
                db.allClientVersement(TABLE_CLIENT_VERSEMENT);
                JOptionPane.showMessageDialog(null, "Le versement pour le client " + client + " a été modifié avec succès!");
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs avant de modifier le versement.");
        }
    } else {
        JOptionPane.showMessageDialog(null, "Format de client incorrect. Veuillez sélectionner un client valide.");
    }
} else {
    JOptionPane.showMessageDialog(null, "Veuillez sélectionner un client avant de modifier le versement.");
}
    }//GEN-LAST:event_BTN_MODIFIER_VERS_CLIENTActionPerformed

    private void BTN_SUPPRIMER_VERS_CLIENTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_SUPPRIMER_VERS_CLIENTActionPerformed
        try {
            String numString = TXT_NUM_VERSEMENT_CLIENT.getText();
            Object clientObject = CBX_CLIENT_VERSEMENT.getSelectedItem();
            String client = CBX_CLIENT_VERSEMENT.getSelectedItem().toString();
            String splitClient[]=client.split(" ");
            String nom = splitClient[0];
            String prenom = splitClient[1];
            int idclient=0;
            int response = JOptionPane.showConfirmDialog(null, "voullez vous supprimer ce versement ?","Suppresion",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
            if (numString != null && !numString.isEmpty() && clientObject != null){
                if (response == JOptionPane.YES_OPTION){
                    idclient= db.chercheIdClient(nom, prenom);
                    db.supprimerVersementClient(Integer.parseInt(numString),idclient);
                    int num =db.getNumVersementClient()+1;
                    TXT_NUM_VERSEMENT_CLIENT.setText(num+"");
                    db.allClientVersement(TABLE_CLIENT_VERSEMENT);
                    TXT_VERSEMENT_CLIENT.setText("");
                }else if (response == JOptionPane.NO_OPTION){
                    int num =db.getNumVersementClient()+1;
                    TXT_NUM_VERSEMENT_CLIENT.setText(num+"");
                    TXT_VERSEMENT_CLIENT.setText("");
                }

            }else {
                JOptionPane.showMessageDialog(null, "veuillez entrer un ID valide !");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ACCEUIL1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_BTN_SUPPRIMER_VERS_CLIENTActionPerformed

    private void BTN_SUPPRIMER_VERS_FOURNISSEURActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_SUPPRIMER_VERS_FOURNISSEURActionPerformed
        try {
            String numString = TXT_NUM_VERSEMENT_FOURNISSEUR.getText();
            Object fourniObject = CBX_FOURNISSEUR_VERSEMENT.getSelectedItem();
            String fourni = CBX_FOURNISSEUR_VERSEMENT.getSelectedItem().toString();

            int idfourni=0;
            int response = JOptionPane.showConfirmDialog(null, "voullez vous supprimer ce versement ?","Suppresion",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
            if (numString != null && !numString.isEmpty() && fourniObject != null){
                if (response == JOptionPane.YES_OPTION){
                    idfourni= db.chercheIdFournisseur(fourni);
                    db.supprimerVersementFournisseur(Integer.parseInt(numString),idfourni);
                    int num =db.getNumVersementFournisseur()+1;
                    TXT_NUM_VERSEMENT_FOURNISSEUR.setText(num+"");
                    db.allFournisseurVersement(TABLE_FOURNISSEUR_VERSEMENT);
                    TXT_VERSEMENT_FOURNISSEUR.setText("");
                }else if (response == JOptionPane.NO_OPTION){
                    int num =db.getNumVersementFournisseur()+1;
                    TXT_NUM_VERSEMENT_FOURNISSEUR.setText(num+"");
                    TXT_VERSEMENT_FOURNISSEUR.setText("");
                }

            }else {
                JOptionPane.showMessageDialog(null, "veuillez entrer un ID valide !");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ACCEUIL1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_BTN_SUPPRIMER_VERS_FOURNISSEURActionPerformed

    private void BTN_MODIFIER_VERS_FOURNISSEURActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_MODIFIER_VERS_FOURNISSEURActionPerformed
        Object forni =CBX_FOURNISSEUR_VERSEMENT.getSelectedItem();

        String fourni = forni.toString();

        int idfourni=0;
        double prix=0.00;
        int num=0;
        try {
            String versementText = TXT_VERSEMENT_FOURNISSEUR.getText().replace(',', '.');
            prix = Double.parseDouble(versementText);

            num = Integer.parseInt(TXT_NUM_VERSEMENT_FOURNISSEUR.getText());

            // Rest of your code here...
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
            // Handle the error, e.g., display an error message to the user
        }

        try {
            idfourni = db.chercheIdFournisseur(fourni);
        } catch (SQLException ex) {
            Logger.getLogger(ACCEUIL1.class.getName()).log(Level.SEVERE, null, ex);
        }
        Date date = CBX_DATE_VERSEMENT_FOURNISSEUR.getJCalendar().getDate();
       java.sql.Date sqlDate = new java.sql.Date(date.getTime());

        try {
            if(forni!=null && date != null && !TXT_VERSEMENT_FOURNISSEUR.getText().isEmpty()){
                db.modifierVersementFournisseur(idfourni,num,sqlDate,prix);
                db.allFournisseurVersement(TABLE_FOURNISSEUR_VERSEMENT);
                TXT_VERSEMENT_FOURNISSEUR.setText("");
                int num1 =db.getNumVersementFournisseur()+1;
                TXT_NUM_VERSEMENT_FOURNISSEUR.setText(num1+"");
                db.allFournisseurVersement(TABLE_FOURNISSEUR_VERSEMENT);
                JOptionPane.showMessageDialog(null, "le versement pout fournisseur " +fourni+" a été bien modifier!");
            }

        }catch(NumberFormatException e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_BTN_MODIFIER_VERS_FOURNISSEURActionPerformed

    private void BTN_AJOUT_VERS_FOURNISSEURActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_AJOUT_VERS_FOURNISSEURActionPerformed
        double montant=0.00;
        int IDFOURNI=0;
        String fourni = CBX_FOURNISSEUR_VERSEMENT.getSelectedItem().toString();

        int num = Integer.parseInt(TXT_NUM_VERSEMENT_FOURNISSEUR.getText());
        try {
            IDFOURNI = db.chercheIdFournisseur(fourni);
        } catch (SQLException ex) {
            Logger.getLogger(ACCEUIL1.class.getName()).log(Level.SEVERE, null, ex);
        }
        Date date = CBX_DATE_VERSEMENT_FOURNISSEUR.getJCalendar().getDate();
        java.sql.Date dateSq = new java.sql.Date(date.getTime());
        String montantString = TXT_VERSEMENT_FOURNISSEUR.getText();
        try{
            montant = Double.parseDouble(montantString);
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null, "le prix dois etre un double valide!");
        }

        if(IDFOURNI>0 && CBX_DATE_VERSEMENT_FOURNISSEUR.getDate() != null && !montantString.isEmpty() && num>0){
            int response =JOptionPane.showConfirmDialog(this, "voulez vous vraiment ajouter ce fournisseur?", "Confirmation", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
            if (response == JOptionPane.YES_OPTION){
                if(!db.versementExist(fourni,num)){
                    try{
                        VersementFournisseur versement = new VersementFournisseur(IDFOURNI,fourni,num, dateSq, montant);
                        db.ajout_versement_fournisseur(versement);
                        db.allFournisseurVersement(TABLE_FOURNISSEUR_VERSEMENT);
                        int num1 =db.getNumVersementFournisseur()+1;
                        TXT_NUM_VERSEMENT_FOURNISSEUR.setText(num1+"");

                        TXT_VERSEMENT_FOURNISSEUR.setText("");

                        JOptionPane.showMessageDialog(null, "le versement pout fournisseur " +fourni+" a été bien ajouter!");

                    }catch(NumberFormatException e){
                        JOptionPane.showMessageDialog(null, "erreur de saisie!");
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "le versement pout fournisseur " +fourni+" numero "+num+" existe deja!");

                }
            }else if(response == JOptionPane.NO_OPTION){
                int num1 =db.getNumVersementFournisseur()+1;
                TXT_NUM_VERSEMENT_FOURNISSEUR.setText(num1+"");
                db.allFournisseurVersement(TABLE_FOURNISSEUR_VERSEMENT);
                TXT_VERSEMENT_FOURNISSEUR.setText("");
            }
        }else{
            JOptionPane.showMessageDialog(null, "tu dois remplir les champs *FOURNI* *date* *vers n°*");
        }
    }//GEN-LAST:event_BTN_AJOUT_VERS_FOURNISSEURActionPerformed

    private void TXT_ID_PRODUITActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TXT_ID_PRODUITActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TXT_ID_PRODUITActionPerformed

    private void BTN_AJOUT_PRODUITActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_AJOUT_PRODUITActionPerformed
        int idProduit = 0;
        try {
            idProduit = Integer.parseInt(TXT_ID_PRODUIT.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Tu dois entrer un nombre.", "Succès", JOptionPane.INFORMATION_MESSAGE);
            return; // Sortir de la méthode si l'ID client n'est pas valide
        }
        String PRODUIT = TXT_PRODUIT.getText();
        String MARQUE = TXT_MARQUE.getText();
        String UNITE = TXT_UNITE_PRODUIT.getText();

        if(idProduit>0 && !PRODUIT.isEmpty() && !MARQUE.isEmpty() && !UNITE.isEmpty()){
            int response =JOptionPane.showConfirmDialog(this, "voulez vous vraiment ajouter ce client?", "Confirmation", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
            if (response == JOptionPane.YES_OPTION){
                if(!db.produitExist(PRODUIT,MARQUE)){
                    try{
                        int unite=0;
                        try {
                            unite = Integer.parseInt(TXT_UNITE_PRODUIT.getText());
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(this, "Tu dois entrer un nombre.", "Succès", JOptionPane.INFORMATION_MESSAGE);
                            return; // Sortir de la méthode si l'ID client n'est pas valide
                        }
                        Produit produit = new Produit(idProduit, PRODUIT, MARQUE, unite);
                        db.ajout_produit(produit);
                        db.allProduit(TABLE_PRODUIT);
                        int IDMAX=db.getIDMaxProduit()+1;
                        TXT_ID_PRODUIT.setText(String.valueOf(IDMAX));
                        TXT_PRODUIT.setText("");
                        TXT_MARQUE.setText("");
                        TXT_UNITE_PRODUIT.setText("");

                        JOptionPane.showMessageDialog(null, "le produit " +PRODUIT+ " "+ MARQUE+" a été bien ajouter!");

                        CBX_PRODUIT_LIVR.removeAllItems();
                        db.getAllProduit(CBX_PRODUIT_LIVR);

                        CBX_PRODUIT_ACHAT.removeAllItems();
                        db.getAllProduit(CBX_PRODUIT_ACHAT);

                        CBX_PRODUIT_PRIX.removeAllItems();
                        db.getAllProduit(CBX_PRODUIT_PRIX);

                        CBX_PRODUIT_PRIXF.removeAllItems();
                        db.getAllProduit(CBX_PRODUIT_PRIXF);
                    }catch(NumberFormatException e){
                        JOptionPane.showMessageDialog(null, "erreur de saisie!");
                    } catch (SQLException ex) {
                        Logger.getLogger(ACCEUIL1.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "le produit " +PRODUIT+ " "+ MARQUE+" existe deja!");
                }
            }else if(response == JOptionPane.NO_OPTION){
                int IDMAX=db.getIDMaxProduit()+1;
                TXT_ID_PRODUIT.setText(String.valueOf(IDMAX));
                TXT_PRODUIT.setText("");
                TXT_MARQUE.setText("");
                TXT_UNITE_PRODUIT.setText("");

            }
        }else{
            JOptionPane.showMessageDialog(null, "tu dois remplir les champs *nom* *prenom* *telephone*");
        }
    }//GEN-LAST:event_BTN_AJOUT_PRODUITActionPerformed

    private void BTN_MODIFIER_PRODUITActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_MODIFIER_PRODUITActionPerformed
        try {
            String produit1=TXT_PRODUIT.getText();
            String marque=TXT_MARQUE.getText();
            String idproduit = TXT_ID_PRODUIT.getText();
            int unite = Integer.parseInt(TXT_UNITE_PRODUIT.getText());
            int response = JOptionPane.showConfirmDialog(null, "voullez vous modifier ce produit ?","Modification",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
            if (idproduit != null && !idproduit.isEmpty()){
                if (response == JOptionPane.YES_OPTION){

                    Produit produit = new Produit(Integer.parseInt(idproduit),produit1,marque,unite);
                    db.modifierProduit(Integer.parseInt(idproduit),produit);
                    db.allProduit(TABLE_PRODUIT);
                    CBX_PRODUIT_PRIX.removeAllItems();
                    db.getAllProduit(CBX_PRODUIT_PRIX);
                    CBX_PRODUIT_PRIXF.removeAllItems();
                    db.getAllProduit(CBX_PRODUIT_PRIXF);
                    CBX_PRODUIT_LIVR.removeAllItems();
                    db.getAllProduit(CBX_PRODUIT_LIVR);
                    CBX_PRODUIT_ACHAT.removeAllItems();
                    db.getAllProduit(CBX_PRODUIT_ACHAT);
                    int IDMAX =db.getIDMaxProduit()+1;
                    TXT_ID_PRODUIT.setText(String.valueOf(IDMAX));
                    TXT_PRODUIT.setText("");
                    TXT_MARQUE.setText("");
                    TXT_UNITE_PRODUIT.setText("");
                }else if (response == JOptionPane.NO_OPTION){
                    int IDMAX =db.getIDMaxProduit()+1;
                    TXT_ID_PRODUIT.setText(String.valueOf(IDMAX));
                    TXT_PRODUIT.setText("");
                    TXT_MARQUE.setText("");
                    TXT_UNITE_PRODUIT.setText("");
                }

            }else {
                JOptionPane.showMessageDialog(null, "veuillez entrer un ID valide !");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ACCEUIL1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_BTN_MODIFIER_PRODUITActionPerformed

    private void BTN_SUPPRIMER_PRODUITActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_SUPPRIMER_PRODUITActionPerformed
        try {
            String idproduit = TXT_ID_PRODUIT.getText();
            int response = JOptionPane.showConfirmDialog(null, "voullez vous supprimer ce produit ?","Suppresion",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
            if (idproduit != null && !idproduit.isEmpty()){
                if (response == JOptionPane.YES_OPTION){
                    db.supprimerProduit(Integer.parseInt(idproduit));
                    db.allProduit(TABLE_PRODUIT);
                    CBX_PRODUIT_PRIX.removeAllItems();
                    CBX_PRODUIT_PRIXF.removeAllItems();
                    db.getAllProduit(CBX_PRODUIT_PRIXF);
                    db.getAllProduit(CBX_PRODUIT_PRIX);
                    CBX_PRODUIT_LIVR.removeAllItems();
                    db.getAllProduit(CBX_PRODUIT_LIVR);
                    CBX_PRODUIT_ACHAT.removeAllItems();
                    db.getAllProduit(CBX_PRODUIT_ACHAT);
                    int IDMAX =db.getIDMaxProduit()+1;
                    TXT_ID_PRODUIT.setText(String.valueOf(IDMAX));
                    TXT_PRODUIT.setText("");
                    TXT_MARQUE.setText("");
                    TXT_UNITE_PRODUIT.setText("");
                }else if (response == JOptionPane.NO_OPTION){
                    int IDMAX =db.getIDMaxProduit()+1;
                    TXT_ID_PRODUIT.setText(String.valueOf(IDMAX));
                    TXT_PRODUIT.setText("");
                    TXT_MARQUE.setText("");
                    TXT_UNITE_PRODUIT.setText("");
                }

            }else {
                JOptionPane.showMessageDialog(null, "veuillez entrer un ID valide !");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ACCEUIL1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_BTN_SUPPRIMER_PRODUITActionPerformed

    private void BTN_ACTUALISER_PRODUITActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_ACTUALISER_PRODUITActionPerformed
        int IDMAX=db.getIDMaxProduit()+1;
        TXT_ID_PRODUIT.setText(String.valueOf(IDMAX));
        TXT_PRODUIT.setText("");
        TXT_MARQUE.setText("");
        TXT_UNITE_PRODUIT.setText("");
    }//GEN-LAST:event_BTN_ACTUALISER_PRODUITActionPerformed

    private void BTN_AJOUT_PRIXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_AJOUT_PRIXActionPerformed
        try{
            String client =CBX_CLIENT_PRIX.getSelectedItem().toString();
            String produit1 =CBX_PRODUIT_PRIX.getSelectedItem().toString();

            String[] splitClient = client.split(" ");
            String nom = splitClient[0];
            String prenom = splitClient[1];
            int IDCLIENT = db.chercheIdClient(nom, prenom);

            String[] splitProduit = produit1.split(" ");
            String produit = splitProduit[0];
            String marque = splitProduit[1];
            int IDPRODUIT = db.chercheIdProduit(produit, marque);

            if(!TXT_PRIX_CLIENT.getText().isEmpty()){
                double prix = Double.parseDouble(TXT_PRIX_CLIENT.getText());
                int response = JOptionPane.showConfirmDialog(this, "voulez vous vraiment ajouter le prix ?","Confirmation",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
                if(response == JOptionPane.YES_OPTION){
                    if(!db.ClientProduitExist(IDCLIENT,IDPRODUIT)){
                        db.ajouterPrix(IDCLIENT ,IDPRODUIT ,prix);
                        db.allClientProduitTable(TABLE_CLIENT_PRIX);
                        JOptionPane.showMessageDialog(null, "le prix " + prix + " du produit "+ produit + marque +" a été ajouté pour le client "+client);
                    }else{
                        JOptionPane.showMessageDialog(null, "le prix " + prix + " du produit "+ produit + marque +" existe deja pour le client "+client);
                    }
                }else if (response==JOptionPane.NO_OPTION){
                    TXT_PRIX_CLIENT.setText("");
                }
            }else{
                JOptionPane.showMessageDialog(null, "saisissez un prix svp!!");
            }

        } catch (SQLException ex) {
            Logger.getLogger(ACCEUIL1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_BTN_AJOUT_PRIXActionPerformed

    private void BTN_MODIFIER_PRIXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_MODIFIER_PRIXActionPerformed
        Object client =CBX_CLIENT_PRIX.getSelectedItem();
        Object produit1 =CBX_PRODUIT_PRIX.getSelectedItem();
        String cli = client.toString();
        String pro = produit1.toString();
        String[] splitClient = cli.split(" ");
        String nom = splitClient[0];
        String prenom = splitClient[1];
        int idclient=0;
        try {
            idclient = db.chercheIdClient(nom, prenom);
        } catch (SQLException ex) {
            Logger.getLogger(ACCEUIL1.class.getName()).log(Level.SEVERE, null, ex);
        }

        String[] splitProduit = pro.split(" ");
        String produit = splitProduit[0];
        String marque = splitProduit[1];
        int idproduit=0;
        try {
            idproduit = db.chercheIdProduit(produit, marque);
        } catch (SQLException ex) {
            Logger.getLogger(ACCEUIL1.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (client != null && produit != null){

            String prixString = TXT_PRIX_CLIENT.getText();
            if (!prixString.trim().isEmpty()){
                double prix=0.00;
                try {
                    prix=Double.parseDouble(prixString);
                    db.modifierPrix(prix,idclient,idproduit);
                    db.allClientProduitTable(TABLE_CLIENT_PRIX);
                    TXT_PRIX_CLIENT.setText("");
                }catch(NumberFormatException e){
                    JOptionPane.showMessageDialog(null, "le prix dois etre un double valide!");
                } catch (SQLException ex) {
                    Logger.getLogger(ACCEUIL1.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                JOptionPane.showMessageDialog(null, "veuillez entrer un prix!");
            }
        }else{
            JOptionPane.showMessageDialog(null, "veuillez selectionner un client et un produit !");
        }
    }//GEN-LAST:event_BTN_MODIFIER_PRIXActionPerformed

    private void BTN_AJOUT_PRIXFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_AJOUT_PRIXFActionPerformed
        try{
            String fourni =CBX_FOURNISSEUR_PRIX.getSelectedItem().toString();
            String produit1 =CBX_PRODUIT_PRIXF.getSelectedItem().toString();

            int IDFOURNI = db.chercheIdFournisseur(fourni);

            String[] splitProduit = produit1.split(" ");
            String produit = splitProduit[0];
            String marque = splitProduit[1];
            int IDPRODUIT = db.chercheIdProduit(produit, marque);

            if(!TXT_PRIX_FOURNISSEUR.getText().isEmpty()){
                double prix = Double.parseDouble(TXT_PRIX_FOURNISSEUR.getText());
                int response = JOptionPane.showConfirmDialog(this, "voulez vous vraiment ajouter le prix ?","Confirmation",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
                if(response == JOptionPane.YES_OPTION){
                    if(!db.FournisseurProduitExist(IDFOURNI,IDPRODUIT)){
                        db.ajouterPrixF(IDFOURNI ,IDPRODUIT ,prix);
                        db.allFournisseurProduitTable(TABLE_FOURNISSEUR_PRIX);
                        JOptionPane.showMessageDialog(null, "le prix " + prix + " du produit "+ produit + marque +" a été ajouté pour le fournisseur "+fourni);
                    }else{
                        JOptionPane.showMessageDialog(null, "le prix " + prix + " du produit "+ produit + marque +" existe deja pour le fournisseur "+fourni);
                    }
                }else if (response==JOptionPane.NO_OPTION){
                    TXT_PRIX_FOURNISSEUR.setText("");
                }
            }else{
                JOptionPane.showMessageDialog(null, "saisissez un prix svp!!");
            }

        } catch (SQLException ex) {
            Logger.getLogger(ACCEUIL1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_BTN_AJOUT_PRIXFActionPerformed

    private void BTN_MODIFIER_PRIXFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_MODIFIER_PRIXFActionPerformed
        Object fournisseur =CBX_FOURNISSEUR_PRIX.getSelectedItem();
        Object produit1 =CBX_PRODUIT_PRIXF.getSelectedItem();
        String fourni = fournisseur.toString();
        String pro = produit1.toString();

        int idfourni=0;
        try {
            idfourni = db.chercheIdFournisseur(fourni);
        } catch (SQLException ex) {
            Logger.getLogger(ACCEUIL1.class.getName()).log(Level.SEVERE, null, ex);
        }

        String[] splitProduit = pro.split(" ");
        String produit = splitProduit[0];
        String marque = splitProduit[1];
        int idproduit=0;
        try {
            idproduit = db.chercheIdProduit(produit, marque);
        } catch (SQLException ex) {
            Logger.getLogger(ACCEUIL1.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (fournisseur != null && produit != null){

            String prixString = TXT_PRIX_FOURNISSEUR.getText();
            if (!prixString.trim().isEmpty()){
                double prix=0.00;
                try {
                    prix=Double.parseDouble(prixString);
                    db.modifierPrixFournisseur(prix,idfourni,idproduit);
                    db.allFournisseurProduitTable(TABLE_FOURNISSEUR_PRIX);
                    TXT_PRIX_FOURNISSEUR.setText("");
                }catch(NumberFormatException e){
                    JOptionPane.showMessageDialog(null, "le prix dois etre un double valide!");
                } catch (SQLException ex) {
                    Logger.getLogger(ACCEUIL1.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                JOptionPane.showMessageDialog(null, "veuillez entrer un prix!");
            }
        }else{
            JOptionPane.showMessageDialog(null, "veuillez selectionner un fournisseur et un produit !");
        }
    }//GEN-LAST:event_BTN_MODIFIER_PRIXFActionPerformed

    private void BTN_SUPPRIMER_LIVROFF1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_SUPPRIMER_LIVROFF1ActionPerformed
        String client = TXT_CLIENT_LIVR_MODIFIER.getText();
        if(CHECKBOX_SUPP_NUM_ACHAT.isSelected()&& !CHECKBOX_SUPP_PRODUIT_ACHAT.isSelected()&& client != null){
          if(!client.isEmpty()){
            int id=0;
            String[] splitClient = client.split(" ");
            String nom = splitClient[0];
            String prenom = splitClient[1];
            try {
                id = db.chercheIdClient(nom,prenom);
            } catch (SQLException ex) {
                Logger.getLogger(ACCEUIL1.class.getName()).log(Level.SEVERE, null, ex);
            }
            db.supprimerLivraison(Integer.parseInt(TXT_NUM_LIVR_MODIFIER.getText()),id);
            int NUMMAX = db.getNUMMAXLivraison(client)+1;
            try {
                db.allLivraison(TABLE_LIVR_MODIFIER);
                TXT_NUM_LIVR.setText(NUMMAX+"");
            } catch (SQLException ex) {
                Logger.getLogger(ACCEUIL1.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                db.allStock(TABLE_STOCK);
                db.allStockAlerte(TABLE_ALERTE_STOCK);
            } catch (SQLException ex) {
                Logger.getLogger(ACCEUIL1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            JOptionPane.showMessageDialog(null,"tu dois selectionner une livraison !");
        }
        }else if(CHECKBOX_SUPP_NUM_ACHAT.isSelected()&& CHECKBOX_SUPP_PRODUIT_ACHAT.isSelected()&& client != null){
           if(!client.isEmpty()){
            String prod = TXT_PRODUIT_LIVR_MODIFIER.getText();
                String marq = TXT_MARQUE_LIVR_MODIFIER.getText();
               int id=0;
            String[] splitClient = client.split(" ");
            String nom = splitClient[0];
            String prenom = splitClient[1];
            try {
                id = db.chercheIdClient(nom,prenom);
            } catch (SQLException ex) {
                Logger.getLogger(ACCEUIL1.class.getName()).log(Level.SEVERE, null, ex);
            }
            db.supprimerLivraisonByProduit(Integer.parseInt(TXT_NUM_LIVR_MODIFIER.getText()),id,prod,marq);
            int NUMMAX = db.getNUMMAXLivraison(client)+1;
            try {
                db.allLivraison(TABLE_LIVR_MODIFIER);
                TXT_NUM_LIVR.setText(NUMMAX+"");
            } catch (SQLException ex) {
                Logger.getLogger(ACCEUIL1.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                db.allStock(TABLE_STOCK);
                db.allStockAlerte(TABLE_ALERTE_STOCK);
            } catch (SQLException ex) {
                Logger.getLogger(ACCEUIL1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            JOptionPane.showMessageDialog(null,"tu dois selectionner une livraison !");
        }  
                  }    
    }//GEN-LAST:event_BTN_SUPPRIMER_LIVROFF1ActionPerformed

    private void BTN_IMPRIMER_LIVRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_IMPRIMER_LIVRActionPerformed
       // String CNR =db.getCNRuser();
        //String NIF =db.getNIFuser;
        try {
            Impression imp = new Impression();
            imp.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(ACCEUIL1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_BTN_IMPRIMER_LIVRActionPerformed

    private void BTN_OPEN_PARAMETREActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_OPEN_PARAMETREActionPerformed
        try {
            Parametre parametre=new Parametre();
            parametre.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(ACCEUIL1.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_BTN_OPEN_PARAMETREActionPerformed

    private void BTN_IMPRIMER_ACHATActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_IMPRIMER_ACHATActionPerformed
        try {
            Impression imp = new Impression();
            imp.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(ACCEUIL1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_BTN_IMPRIMER_ACHATActionPerformed

    private void BTN_OPEN_PARAMETRE1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_OPEN_PARAMETRE1ActionPerformed
        Situationclient client;
        try {
            client = new Situationclient();
            client.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(ACCEUIL1.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_BTN_OPEN_PARAMETRE1ActionPerformed

    private void BTN_OPEN_PARAMETRE2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_OPEN_PARAMETRE2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BTN_OPEN_PARAMETRE2ActionPerformed

    private void CBX_CLIENT_VERSEMENTItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_CBX_CLIENT_VERSEMENTItemStateChanged
          Object cli=CBX_CLIENT_VERSEMENT.getSelectedItem();
         if(cli!=null){
             
               int num1 = db.getNUMMAXVERSEMENT(cli.toString())+1;
               TXT_NUM_VERSEMENT_CLIENT.setText(String.valueOf(num1));
         }
    }//GEN-LAST:event_CBX_CLIENT_VERSEMENTItemStateChanged

    private void CBX_FOURNISSEUR_VERSEMENTItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_CBX_FOURNISSEUR_VERSEMENTItemStateChanged
        Object fourni=CBX_FOURNISSEUR_VERSEMENT.getSelectedItem();
         if(fourni!=null){
             
               int num1 = db.getNUMMAXVERSEMENTF(fourni.toString())+1;
               TXT_NUM_VERSEMENT_FOURNISSEUR.setText(String.valueOf(num1));
         }
    }//GEN-LAST:event_CBX_FOURNISSEUR_VERSEMENTItemStateChanged

    private void CBX_FOURNISSEUR_VERSEMENTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CBX_FOURNISSEUR_VERSEMENTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CBX_FOURNISSEUR_VERSEMENTActionPerformed
    public void Alert(String message){
        JFrame frame = new JFrame("Alerte Stock");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Modification du comportement de fermeture
        frame.setSize(400, 100); // Ajustement de la taille de la fenêtre
         
        ImageIcon icon = new ImageIcon("src\\main\\resources\\icon\\icon-alerte.jfif");
        frame.setIconImage(icon.getImage());
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Rectangle screenRect = ge.getMaximumWindowBounds();
        int screenWidth = screenRect.width;
        int screenHeight = screenRect.height;

        // Positionnement de la fenêtre en bas à droite
        int frameWidth = frame.getWidth();
        int frameHeight = frame.getHeight();
        int x = screenWidth - frameWidth;
        int y = screenHeight - frameHeight;
        frame.setLocation(x, y);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.BLACK); // Couleur de fond du panneau

        JLabel alertLabel = new JLabel(message);
        alertLabel.setHorizontalAlignment(SwingConstants.CENTER);
        alertLabel.setForeground(Color.black);
        alertLabel.setBackground(Color.RED);
        alertLabel.setOpaque(true);
        alertLabel.setFont(new Font("Arial", Font.BOLD, 20));
        alertLabel.setVisible(false);

        panel.add(alertLabel, BorderLayout.CENTER);
        frame.add(panel);

        // Timer pour faire clignoter l'alerte
        Timer timer = new Timer(500, new ActionListener() {
            boolean visible = false;

            @Override
            public void actionPerformed(ActionEvent e) {
                alertLabel.setVisible(visible);
                visible = !visible;
            }
        });
        timer.start();

        frame.setVisible(true);
    }
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
            java.util.logging.Logger.getLogger(ACCEUIL1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ACCEUIL1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ACCEUIL1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ACCEUIL1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new ACCEUIL1().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(ACCEUIL1.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel ACCEUIL;
    private javax.swing.JTabbedPane ACHAT;
    private javax.swing.JButton BTN_ACTUALISER_ACHAT;
    private javax.swing.JButton BTN_ACTUALISER_CLIENT;
    private javax.swing.JButton BTN_ACTUALISER_CLIENT1;
    private javax.swing.JButton BTN_ACTUALISER_LIVR;
    private javax.swing.JButton BTN_ACTUALISER_PRODUIT;
    private javax.swing.JButton BTN_AJOUT_ACHAT;
    private javax.swing.JButton BTN_AJOUT_CLIENT;
    private javax.swing.JButton BTN_AJOUT_FOURNISSEUR;
    private javax.swing.JButton BTN_AJOUT_LIVR;
    private javax.swing.JButton BTN_AJOUT_PRIX;
    private javax.swing.JButton BTN_AJOUT_PRIXF;
    private javax.swing.JButton BTN_AJOUT_PRODUIT;
    private javax.swing.JButton BTN_AJOUT_VERS_CLIENT;
    private javax.swing.JButton BTN_AJOUT_VERS_FOURNISSEUR;
    private javax.swing.JButton BTN_IMPRIMER_ACHAT;
    private javax.swing.JButton BTN_IMPRIMER_LIVR;
    private javax.swing.JButton BTN_MODIFIER_ACHAT;
    private javax.swing.JButton BTN_MODIFIER_CLIENT;
    private javax.swing.JButton BTN_MODIFIER_FOURNISSEUR;
    private javax.swing.JButton BTN_MODIFIER_LIVR1;
    private javax.swing.JButton BTN_MODIFIER_PRIX;
    private javax.swing.JButton BTN_MODIFIER_PRIXF;
    private javax.swing.JButton BTN_MODIFIER_PRODUIT;
    private javax.swing.JButton BTN_MODIFIER_VERS_CLIENT;
    private javax.swing.JButton BTN_MODIFIER_VERS_FOURNISSEUR;
    private javax.swing.JButton BTN_OPEN_PARAMETRE;
    private javax.swing.JButton BTN_OPEN_PARAMETRE1;
    private javax.swing.JButton BTN_OPEN_PARAMETRE2;
    private javax.swing.JButton BTN_RECHERCHE_ACHAT;
    private javax.swing.JButton BTN_RECHERCHE_LIVR1;
    private javax.swing.JButton BTN_SUPPRIMER_ACHAT;
    private javax.swing.JButton BTN_SUPPRIMER_ACHATOFF;
    private javax.swing.JButton BTN_SUPPRIMER_CLIENT;
    private javax.swing.JButton BTN_SUPPRIMER_FOURNISSEUR;
    private javax.swing.JButton BTN_SUPPRIMER_LIVRAISON;
    private javax.swing.JButton BTN_SUPPRIMER_LIVROFF1;
    private javax.swing.JButton BTN_SUPPRIMER_PRODUIT;
    private javax.swing.JButton BTN_SUPPRIMER_VERS_CLIENT;
    private javax.swing.JButton BTN_SUPPRIMER_VERS_FOURNISSEUR;
    private javax.swing.JButton BTN_VALIDER_ACHAT;
    private javax.swing.JButton BTN_VALIDER_LIVR;
    private javax.swing.JComboBox<String> CBX_CLIENT_LIVR;
    private javax.swing.JComboBox<String> CBX_CLIENT_PRIX;
    private javax.swing.JComboBox<String> CBX_CLIENT_RECHERCHE_LIVR;
    private javax.swing.JComboBox<String> CBX_CLIENT_VERSEMENT;
    private com.toedter.calendar.JDateChooser CBX_DATE_ACHAT;
    private com.toedter.calendar.JDateChooser CBX_DATE_ACHAT_MODIFIER;
    private com.toedter.calendar.JDateChooser CBX_DATE_ACHAT_RECHERCHE;
    private com.toedter.calendar.JDateChooser CBX_DATE_LIVR;
    private com.toedter.calendar.JDateChooser CBX_DATE_LIVR_MODIFIER;
    private com.toedter.calendar.JDateChooser CBX_DATE_LIVR_RECHERCHE;
    private com.toedter.calendar.JDateChooser CBX_DATE_VERSEMENT_CLIENT;
    private com.toedter.calendar.JDateChooser CBX_DATE_VERSEMENT_FOURNISSEUR;
    private javax.swing.JComboBox<String> CBX_FOURNISSEUR_ACHAT;
    private javax.swing.JComboBox<String> CBX_FOURNISSEUR_PRIX;
    private javax.swing.JComboBox<String> CBX_FOURNISSEUR_RECHERCHE_ACHAT;
    private javax.swing.JComboBox<String> CBX_FOURNISSEUR_VERSEMENT;
    private javax.swing.JComboBox<String> CBX_PRODUIT_ACHAT;
    private javax.swing.JComboBox<String> CBX_PRODUIT_LIVR;
    private javax.swing.JComboBox<String> CBX_PRODUIT_PRIX;
    private javax.swing.JComboBox<String> CBX_PRODUIT_PRIXF;
    private javax.swing.JComboBox<String> CBX_TYPE_MODIFIER_ACHAT;
    private javax.swing.JComboBox<String> CBX_TYPE_MODIFIER_LIVR;
    private javax.swing.JCheckBox CHECKBOX_SUPP_NUM_ACHAT;
    private javax.swing.JCheckBox CHECKBOX_SUPP_NUM_LIVR;
    private javax.swing.JCheckBox CHECKBOX_SUPP_PRODUIT_ACHAT;
    private javax.swing.JCheckBox CHECKBOX_SUPP_PRODUIT_LIVR;
    private javax.swing.JPanel CLIENT;
    private javax.swing.JCheckBox CheckBox_DATE_ACHAT;
    private javax.swing.JCheckBox CheckBox_DATE_LIVR;
    private javax.swing.JCheckBox CheckBox_NUM_ACHAT;
    private javax.swing.JCheckBox CheckBox_NUM_LIVR;
    private javax.swing.JPanel FOURNISSEUR;
    private javax.swing.JLabel ID;
    private javax.swing.JLabel ID1;
    private javax.swing.JLabel LABEL_MONTANTHT_ACHAT;
    private javax.swing.JLabel LABEL_MONTANTHT_LIVR;
    private javax.swing.JLabel LABEL_MONTANTTTC_ACHAT;
    private javax.swing.JLabel LABEL_MONTANTTTC_LIVR;
    private javax.swing.JPanel LIVRAI;
    private javax.swing.JTabbedPane LIVRAISON;
    private javax.swing.JPanel PRODUIT;
    private javax.swing.JTable TABLE_ACHAT;
    private javax.swing.JTable TABLE_ACHAT_MODIFIER;
    private javax.swing.JTable TABLE_ALERTE_STOCK;
    private javax.swing.JTable TABLE_CLIENT;
    private javax.swing.JTable TABLE_CLIENT_PRIX;
    private javax.swing.JTable TABLE_CLIENT_VERSEMENT;
    private javax.swing.JTable TABLE_FOURNISSEUR;
    private javax.swing.JTable TABLE_FOURNISSEUR_PRIX;
    private javax.swing.JTable TABLE_FOURNISSEUR_VERSEMENT;
    private javax.swing.JTable TABLE_LIVRAISON;
    private javax.swing.JTable TABLE_LIVR_MODIFIER;
    private javax.swing.JTable TABLE_PRODUIT;
    private javax.swing.JTable TABLE_STOCK;
    private javax.swing.JTextField TXT_ADRESSE_CLIENT;
    private javax.swing.JTextField TXT_ADRESSE_FOURNISSEUR;
    private javax.swing.JTextField TXT_CLIENT_LIVR_MODIFIER;
    private javax.swing.JTextField TXT_CNR_CLIENT;
    private javax.swing.JTextField TXT_CNR_FOURNISSEUR;
    private javax.swing.JTextField TXT_EMAIL_CLIENT;
    private javax.swing.JTextField TXT_EMAIL_FOURNISSEUR;
    private javax.swing.JTextField TXT_FOURNISSEUR_ACHAT_MODIFIER;
    private javax.swing.JTextField TXT_ID_CLIENT;
    private javax.swing.JTextField TXT_ID_FOURNISSEUR;
    private javax.swing.JTextField TXT_ID_PRODUIT;
    private javax.swing.JTextField TXT_MARQUE;
    private javax.swing.JTextField TXT_MARQUE_ACHAT;
    private javax.swing.JTextField TXT_MARQUE_ACHAT_MODIFIER;
    private javax.swing.JTextField TXT_MARQUE_LIVR;
    private javax.swing.JTextField TXT_MARQUE_LIVR_MODIFIER;
    private javax.swing.JTextField TXT_MONTANT_ACHAT_MODIFIER;
    private javax.swing.JTextField TXT_MONTANT_LIVR_MODIFIER;
    private javax.swing.JTextField TXT_NIF_CLIENT;
    private javax.swing.JTextField TXT_NIF_FOURNISSEUR;
    private javax.swing.JTextField TXT_NOM_CLIENT;
    private javax.swing.JTextField TXT_NOM_FOURNISSEUR;
    private javax.swing.JTextField TXT_NUM_ACHAT;
    private javax.swing.JTextField TXT_NUM_ACHAT_MODIFIER;
    private javax.swing.JTextField TXT_NUM_LIVR;
    private javax.swing.JTextField TXT_NUM_LIVR_MODIFIER;
    private javax.swing.JTextField TXT_NUM_VERSEMENT_CLIENT;
    private javax.swing.JTextField TXT_NUM_VERSEMENT_FOURNISSEUR;
    private javax.swing.JTextField TXT_PRENOM_CLIENT;
    private javax.swing.JTextField TXT_PRIX_ACHAT;
    private javax.swing.JTextField TXT_PRIX_ACHAT_MODIFIER;
    private javax.swing.JTextField TXT_PRIX_CLIENT;
    private javax.swing.JTextField TXT_PRIX_FOURNISSEUR;
    private javax.swing.JTextField TXT_PRIX_LIVR;
    private javax.swing.JTextField TXT_PRIX_LIVR_MODIFIER;
    private javax.swing.JTextField TXT_PRODUIT;
    private javax.swing.JTextField TXT_PRODUIT_ACHAT;
    private javax.swing.JTextField TXT_PRODUIT_ACHAT_MODIFIER;
    private javax.swing.JTextField TXT_PRODUIT_LIVR;
    private javax.swing.JTextField TXT_PRODUIT_LIVR_MODIFIER;
    private javax.swing.JTextField TXT_QUANTITE_ACHAT;
    private javax.swing.JTextField TXT_QUANTITE_ACHAT_MODIFIER;
    private javax.swing.JTextField TXT_QUANTITE_LIVR;
    private javax.swing.JTextField TXT_QUANTITE_LIVR_MODIFIER;
    private javax.swing.JTextField TXT_RECHERCHE_ACHAT;
    private javax.swing.JTextField TXT_RECHERCHE_CLIENT;
    private javax.swing.JTextField TXT_RECHERCHE_FOURNISSEUR;
    private javax.swing.JTextField TXT_RECHERCHE_LIVR;
    private javax.swing.JTextField TXT_TELEPHONE_CLIENT;
    private javax.swing.JTextField TXT_TELEPHONE_FOURNISSEUR;
    private javax.swing.JTextField TXT_UNITE_ACHAT;
    private javax.swing.JTextField TXT_UNITE_ACHAT_MODIFIER;
    private javax.swing.JTextField TXT_UNITE_LIVR;
    private javax.swing.JTextField TXT_UNITE_LIVR_MODIFIER;
    private javax.swing.JTextField TXT_UNITE_PRODUIT;
    private javax.swing.JTextField TXT_VERSEMENT_CLIENT;
    private javax.swing.JTextField TXT_VERSEMENT_FOURNISSEUR;
    private javax.swing.JPanel VERSEMENT;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel102;
    private javax.swing.JLabel jLabel103;
    private javax.swing.JLabel jLabel104;
    private javax.swing.JLabel jLabel105;
    private javax.swing.JLabel jLabel106;
    private javax.swing.JLabel jLabel107;
    private javax.swing.JLabel jLabel108;
    private javax.swing.JLabel jLabel109;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel110;
    private javax.swing.JLabel jLabel111;
    private javax.swing.JLabel jLabel112;
    private javax.swing.JLabel jLabel113;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel99;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane1;
    // End of variables declaration//GEN-END:variables
}
