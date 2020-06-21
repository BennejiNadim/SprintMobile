/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.enitities;









/**
 *
 * @author USER
 */
public class HistoriqueEquipement {

    public HistoriqueEquipement(int id, String date, operation operation, equipement equipement, Integer qte, Integer idm, Integer idv, float prix) {
        this.id = id;
        this.date = date;
        this.operation = operation;
        this.equipement = equipement;
        this.qte = qte;
        this.idm = idm;
        this.idv = idv;
        this.prix = prix;
    }

    public HistoriqueEquipement(String date, operation operation, equipement equipement, Integer qte, Integer idm, Integer idv, float prix) {
        this.date = date;
        this.operation = operation;
        this.equipement = equipement;
        this.qte = qte;
        this.idm = idm;
        this.idv = idv;
        this.prix = prix;
    }
    
    
    public HistoriqueEquipement() {
 
    }

    
    public enum operation {
        NULL,
    achat,
    vente,
    corbeille,
    panne,
    maintenance,
    disponnible;
}
    public enum equipement {
        NULL,
        vehicule,
        materiels
    }
 private int id;
 private String date;
 private operation operation;
 private equipement equipement;
 private Integer qte;
 private Integer idm;
 private Integer idv;
 private float prix;
private Integer idf=0 ;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public operation getOperation() {
        return operation;
    }

    public void setOperation(operation operation) {
        this.operation = operation;
    }

    public equipement getEquipement() {
        return equipement;
    }

    public void setEquipement(equipement equipement) {
        this.equipement = equipement;
    }

    public Integer getQte() {
        return qte;
    }

    public void setQte(Integer qte) {
        this.qte = qte;
    }

    public Integer getIdm() {
        return idm;
    }

    public void setIdm(Integer idm) {
        this.idm = idm;
    }

    public Integer getIdv() {
        return idv;
    }

    public void setIdv(Integer idv) {
        this.idv = idv;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public Integer getIdf() {
        return idf;
    }

    public void setIdf(Integer idf) {
        this.idf = idf;
    }

    @Override
    public String toString() {
        return "HistoriqueEquipement{" + "id=" + id + ", date=" + date + ", operation=" + operation + ", equipement=" + equipement + ", qte=" + qte + ", idm=" + idm + ", idv=" + idv + ", prix=" + prix + ", idf=" + idf + '}';
    }



   
    
}
