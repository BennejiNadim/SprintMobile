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
public class Marque {
    private int idMarque;
    private String nomMarque;
    public Marque(){};

    public int getIdMarque() {
        return idMarque;
    }

    public String getNomMarque() {
        return nomMarque;
    }

    public void setIdMarque(int idMarque) {
        this.idMarque = idMarque;
    }

    public void setNomMarque(String nomMarque) {
        this.nomMarque = nomMarque;
    }

    @Override
    public String toString() {
        return "Marque{" + "idMarque=" + idMarque + ", nomMarque=" + nomMarque + '}';
    }
    
}
