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
public class VersementClient {
    private int id;
    private String client;
    private int num;
    private Date date;
    private double montant;

    public VersementClient(int id, String client, int num, Date date, double montant) {
        this.id = id;
        this.client = client;
        this.num = num;
        this.date = date;
        this.montant = montant;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getNum() {
        return num;
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

   

    public String getClient() {
        return client;
    }

    public Date getDate() {
        return date;
    }

    public double getMontant() {
        return montant;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }
    
}
