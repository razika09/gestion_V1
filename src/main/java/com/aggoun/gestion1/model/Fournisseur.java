/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aggoun.gestion1.model;

/**
 *
 * @author Dual Computer
 */
public class Fournisseur {
    private int id;
    private String nom;
    private String adresse;
    private String email;
    private String telephone;
    private String CNR;
    private String NIF;

    public Fournisseur(int id, String nom, String adresse, String email, String telephone, String CNR, String NIF) {
        this.id = id;
        this.nom = nom;
        this.adresse = adresse;
        this.email = email;
        this.telephone = telephone;
        this.CNR = CNR;
        this.NIF = NIF;
    }

    public void setCNR(String CNR) {
        this.CNR = CNR;
    }

    public void setNIF(String NIF) {
        this.NIF = NIF;
    }

    public String getCNR() {
        return CNR;
    }

    public String getNIF() {
        return NIF;
    }

   

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getEmail() {
        return email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    
}
