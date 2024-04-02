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
public class Livraison {
    private int num;
    private int idclient;
    private String client;
    private String produit;
    private String marque;
    private int unite;
    private double prix;
    private int quantite;
    private double montant;
    private Date date;

    public Livraison(int num, int idclient, String client, String produit, String marque, int unite, double prix, int quantite, double montant, Date date) {
        this.num = num;
        this.idclient = idclient;
        this.client = client;
        this.produit = produit;
        this.marque = marque;
        this.unite = unite;
        this.prix = prix;
        this.quantite = quantite;
        this.montant = montant;
        this.date = date;
    }

    public void setIdclient(int idclient) {
        this.idclient = idclient;
    }

    public int getIdclient() {
        return idclient;
    }

   

    public int getNum() {
        return num;
    }

    public String getClient() {
        return client;
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

    public void setNum(int num) {
        this.num = num;
    }

    public void setClient(String client) {
        this.client = client;
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
    
    
    
}
