/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aggoun.gestion1.model;

/**
 *
 * @author Dual Computer
 */
public class ClientPrix {
    private String produit;
    private String marque;
    private int unite;
    private double prix;
    private String client;

    public ClientPrix(String produit, String marque, int unite, double prix, String client) {
        this.produit = produit;
        this.marque = marque;
        this.unite = unite;
        this.prix = prix;
        this.client = client;
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

    public String getClient() {
        return client;
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

    public void setClient(String client) {
        this.client = client;
    }
    
    
}
