/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aggoun.gestion1.model;

import java.util.Date;

/**
 *
 * @author Dual Computer
 */
public class Achat {
    private int num;
    private String produit;
    private String marque;
    private int unite;
    private double prix;
    private int quantite;
    private double montant;
    private Date date;
    private int idfournisseur;
    private String fournisseur;

    public Achat(int num, String produit, String marque, int unite, double prix, int quantite, double montant, Date date, int idfournisseur, String fournisseur) {
        this.num = num;
        this.produit = produit;
        this.marque = marque;
        this.unite = unite;
        this.prix = prix;
        this.quantite = quantite;
        this.montant = montant;
        this.date = date;
        this.idfournisseur = idfournisseur;
        this.fournisseur = fournisseur;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void setProduit(String produit) {
        this.produit = produit;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public void setUnite(int unite) {
        this.unite = unite;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setIdfournisseur(int idfournisseur) {
        this.idfournisseur = idfournisseur;
    }

    public void setFournisseur(String fournisseur) {
        this.fournisseur = fournisseur;
    }
    
    public int getNum() {
        return num;
    }

    public String getProduit() {
        return produit;
    }

    public String getMarque() {
        return marque;
    }

    public int getUnite() {
        return unite;
    }

    public double getPrix() {
        return prix;
    }

    public int getQuantite() {
        return quantite;
    }

    public double getMontant() {
        return montant;
    }

    public Date getDate() {
        return date;
    }

    public int getIdfournisseur() {
        return idfournisseur;
    }

    public String getFournisseur() {
        return fournisseur;
    }
    
}
