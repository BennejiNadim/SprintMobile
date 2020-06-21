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
public class Transaction {
    
    private static int idc=1;
    private int id;
    private int idFacture;
    private String etatTransaction;
    private Date date;
    private float monton;
    private String description;

    public Transaction() {
       
    }

    public void setId(int id) {
        this.id = id;
    }

    public Transaction(int idFacture,String etatTransaction, String description, Date date, float monton) {

        this.etatTransaction=etatTransaction;
        this.idFacture = idFacture;
        this.description = description;
        this.date = date;
        this.monton = monton;
    }

    public Transaction(int id, int idFacture, String etatTransaction, Date date, float monton, String description) {
        this.id = id;
        this.idFacture = idFacture;
        this.etatTransaction = etatTransaction;
        this.date = date;
        this.monton = monton;
        this.description = description;
    }
    
    

    public String getEtatTransaction() {
        return etatTransaction;
    }
    
    public void setPayed() {
        this.etatTransaction = "payed";
    }

    public void setNotPayed() {
        this.etatTransaction = "not_payed";
    }

    public int getIdFacture() {
        return idFacture;
    }

    public static int getIdc() {
        return idc;
    }

    public void setIdFacture(int idFacture) {
        this.idFacture = idFacture;
    }

    

    public int getId() {
        return id;
    }



    public String getDescription() {
        return description;
    }

    public Date getDate() {
        return date;
    }

    public float getMonton() {
        return monton;
    }


    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setMonton(float monton) {
        this.monton = monton;
    }

}
