/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.enitities;

/**
 *
 * @author ASUS
 */
public class Categorie {
    private int idCatg;
    private String libCatg;
    public Categorie(){};

    public int getIdCatg() {
        return idCatg;
    }

    public String getLibCatg() {
        return libCatg;
    }

    public void setIdCatg(int idCatg) {
        this.idCatg = idCatg;
    }

    public void setLibCatg(String libCatg) {
        this.libCatg = libCatg;
    }

    @Override
    public String toString() {
        return "Categorie{" + "idCatg=" + idCatg + ", libCatg=" + libCatg + '}';
    }
    
    
}
