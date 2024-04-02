/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aggoun.gestion1.model;

/**
 *
 * @author Dual Computer
 */
public class FournisseurPrix {
    private String produit;
    private String marque;
    private int unite;
    private double prix;
    private String fournisseur;

    public FournisseurPrix(String produit, String marque, int unite, double prix, String fournisseur) {
        this.produit = produit;
        this.marque = marque;
        this.unite = unite;
        this.prix = prix;
        this.fournisseur = fournisseur;
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

    public String getFournisseur() {
        return fournisseur;
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

    public void setFournisseur(String fournisseur) {
        this.fournisseur = fournisseur;
    }
    
    
}    
