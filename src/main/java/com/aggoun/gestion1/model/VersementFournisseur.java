/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aggoun.gestion1.model;

import java.sql.Date;

/**
 *
 * @author Dual Computer
 */
public class VersementFournisseur {
     private int id;
    private String fournisseur;
    private int num;
    private Date date;
    private double montant;

    public VersementFournisseur(int id, String fournisseur, int num, Date date, double montant) {
        this.id = id;
        this.fournisseur = fournisseur;
        this.num = num;
        this.date = date;
        this.montant = montant;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFournisseur(String fournisseur) {
        this.fournisseur = fournisseur;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public int getId() {
        return id;
    }

    public String getFournisseur() {
        return fournisseur;
    }

    public int getNum() {
        return num;
    }

    public Date getDate() {
        return date;
    }

    public double getMontant() {
        return montant;
    }
    
}
