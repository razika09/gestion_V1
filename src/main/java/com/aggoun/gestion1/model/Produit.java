/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aggoun.gestion1.model;

/**
 *
 * @author Dual Computer
 */
public class Produit {
    private int id;
    private String produit;
    private String marque;
    private int unite;

    public Produit(int id, String produit, String marque, int unite) {
        this.id = id;
        this.produit = produit;
        this.marque = marque;
        this.unite = unite;
    }

    public int getId() {
        return id;
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

    public void setId(int id) {
        this.id = id;
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
    
    
}
