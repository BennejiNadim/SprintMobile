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
public class Produit {

    private int refProduit;
    private String nomProduit;
    private String marque;
    private String categorie;
    private int quantiteStock;
    private int quantiteMagasin;
    private float prixVente;
    private float prixAchat;
    private String image;
    private int updated;

    public Produit() {
    }
;

    public int getRefProduit() {
        return refProduit;
    }

    public String getNomProduit() {
        return nomProduit;
    }

    public String getMarque() {
        return marque;
    }

    public String getCategorie() {
        return categorie;
    }

    public int getQuantiteStock() {
        return quantiteStock;
    }

    public int getQuantiteMagasin() {
        return quantiteMagasin;
    }

    public float getPrixVente() {
        return prixVente;
    }

    public float getPrixAchat() {
        return prixAchat;
    }

    public String getImage() {
        return image;
    }

    public int getUpdated() {
        return updated;
    }

    public void setRefProduit(int refProduit) {
        this.refProduit = refProduit;
    }

    public void setNomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public void setQuantiteStock(int quantiteStock) {
        this.quantiteStock = quantiteStock;
    }

    public void setQuantiteMagasin(int quantiteMagasin) {
        this.quantiteMagasin = quantiteMagasin;
    }

    public void setPrixVente(float prixVente) {
        this.prixVente = prixVente;
    }

    public void setPrixAchat(float prixAchat) {
        this.prixAchat = prixAchat;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setUpdated(int updated) {
        this.updated = updated;
    }

    @Override
    public String toString() {
        return "Produit{" + "refProduit=" + refProduit + ", nomProduit=" + nomProduit + ", marque=" + marque + ", categorie=" + categorie + ", quantiteStock=" + quantiteStock + ", quantiteMagasin=" + quantiteMagasin + ", prixVente=" + prixVente + ", prixAchat=" + prixAchat + ", image=" + image + ", updated=" + updated + '}';
    }


}
