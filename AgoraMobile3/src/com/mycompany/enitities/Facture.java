/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.enitities;

import java.util.Date;




/**
 *
 * @author Wael
 */
public class Facture {

    private int id;
    private Date dateFacturation;
    private String etatFacture;
    private float montant;
    private String clientLogin;
    private String EmployeLogin;
    private int supplierId;
    private String typeFacture;

    public void setEtatFacture(String etatFacture) {
        this.etatFacture = etatFacture;
    }

    public Facture() {
    }

    public Facture(String etatFacture, float montant, String clientLogin, String EmployeLogin, int supplierId, String typeFacture) {
        
            this.etatFacture=etatFacture;
        this.montant = montant;
        this.typeFacture = typeFacture;
        if(typeFacture!=null)
            switch (typeFacture) {
            case "salaire":
                this.EmployeLogin = EmployeLogin;
                break;
            case "vente_materiel":
            case "vente_produit":
                this.clientLogin = clientLogin;
                break;
            case "achat_materiel":
            case "achat_produit":
                this.supplierId = supplierId;
                break;
            default:
                break;
        }
    }

    public String getEmployeLogin() {
        return EmployeLogin;
    }

    public String getTypeFacture() {
        return typeFacture;
    }

    public void setEmployeLogin(String EmployeLogin) {
        this.EmployeLogin = EmployeLogin;
    }

    public void setTypeFacture(String typeFacture) {
        this.typeFacture = typeFacture;
    }
    

    public int getId() {
        return id;
    }

    public Date getDateFacturation() {
        return dateFacturation;
    }

    public String getEtatFacture() {
        return etatFacture;
    }

    public float getMontant() {
        return montant;
    }

    public void setDateFacturation(Date dateFacturation) {
        this.dateFacturation = dateFacturation;
    }

    public void setPayed() {
        this.etatFacture = "payed";
    }

    public void setNotPayed() {
        this.etatFacture = "not_payed";
    }

    public void setMontant(float montant) {
        this.montant = montant;
    }

    public String getClientLogin() {
        return clientLogin;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setClientLogin(String clientLogin) {
        this.clientLogin = clientLogin;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Facture{" + "id=" + id + ", dateFacturation=" + dateFacturation + ", etatFacture=" + etatFacture + ", montant=" + montant + ", clientLogin=" + clientLogin + ", EmployeLogin=" + EmployeLogin + ", supplierId=" + supplierId + ", typeFacture=" + typeFacture + '}';
    }
    
    

}
