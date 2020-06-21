/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.enitities.Categorie;
import com.mycompany.enitities.Marque;
import com.mycompany.enitities.Produit;
import com.mycompany.enitities.Rating;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ASUS
 */
public class ServiceProd {

    public ArrayList<Produit> prods;
    public ArrayList<Rating> rate;
    public ArrayList<Categorie> catgs;
    public ArrayList<Marque> marqs;
    public static ServiceProd instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceProd() {
        req = new ConnectionRequest();
    }

    public static ServiceProd getInstance() {
        if (instance == null) {
            instance = new ServiceProd();
        }
        return instance;
    }

    /**
     * ***********************Prods*********************************
     */
    public ArrayList<Produit> parseProds(String jsonText) {
        try {
            prods = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                Produit t = new Produit();
                float id = Float.parseFloat(obj.get("refProduit").toString());
                t.setRefProduit((int) id);
                t.setNomProduit(obj.get("nomProduit").toString());
                t.setMarque(obj.get("marque").toString());
                t.setCategorie(obj.get("categorie").toString());
                float qS = Float.parseFloat(obj.get("quantiteStock").toString());
                t.setQuantiteStock((int) qS);
                float qM = Float.parseFloat(obj.get("quantiteMagasin").toString());
                t.setQuantiteMagasin((int) qM);
                t.setPrixVente(Float.parseFloat(obj.get("prixVente").toString()));
                t.setPrixVente(Float.parseFloat(obj.get("prixAchat").toString()));
                t.setImage(obj.get("image").toString());
                float u = Float.parseFloat(obj.get("updated").toString());
                t.setUpdated((int) u);
                prods.add(t);
            }

        } catch (IOException ex) {

        }
        return prods;
    }

    public ArrayList<Produit> getAllProds() {
        String url = Statics.ListProd_URL;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                prods = parseProds(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return prods;
    }

    public ArrayList<Produit> getProdByCatg(String catg) {
        String url = Statics.FilterCatg_URL + "?categorie=" + catg;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                prods = parseProds(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return prods;
    }

    public ArrayList<Produit> getProdByMarq(String marq) {
        String url = Statics.FilterMarq_URL + "?marque=" + marq;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                prods = parseProds(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return prods;
    }

    public ArrayList<Produit> findProd(String prod) {
        String url;

        url = Statics.FindProduct_URL + "?nomProduit=" + prod;

        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                prods = parseProds(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return prods;
    }

    public ArrayList<Produit> findProdById(int id) {
        String url;

        url = Statics.FindProductById_URL + "?id=" + id;

        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                prods = parseProds(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return prods;
    }

    public Boolean addProduct(Produit p) {
        String url = Statics.AddProd_url + "?refProduit=" + p.getRefProduit() + "&nomProduit=" + p.getNomProduit() + "&marque=" + p.getMarque() + "&categorie="
                + p.getCategorie() + "&quantiteStock=" + p.getQuantiteStock() + "&quantiteMagasin=" + p.getQuantiteMagasin() + "&prixVente=" + p.getPrixVente()
                + "&prixAchat=" + p.getPrixAchat() + "&image=" + p.getImage() + "&updated=" + p.getUpdated();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    /**
     * ***************************Categories************************************
     */
    public ArrayList<Categorie> parseCatgs(String jsonText) {
        try {
            catgs = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                Categorie t = new Categorie();
                float id = Float.parseFloat(obj.get("idCatg").toString());
                t.setIdCatg((int) id);
                t.setLibCatg(obj.get("libCatg").toString());
                catgs.add(t);
            }

        } catch (IOException ex) {

        }
        return catgs;
    }

    public ArrayList<Categorie> getAllCatgs() {
        String url = Statics.ListCatg_URL;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                catgs = parseCatgs(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return catgs;
    }

    /**
     * ****************************Marque***********************************
     */
    public ArrayList<Marque> parseMarqs(String jsonText) {
        try {
            marqs = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                Marque t = new Marque();
                float id = Float.parseFloat(obj.get("idMarque").toString());
                t.setIdMarque((int) id);
                t.setNomMarque(obj.get("nomMarque").toString());
                marqs.add(t);
            }

        } catch (IOException ex) {

        }
        return marqs;
    }

    public ArrayList<Marque> getAllMarqs() {
        String url = Statics.ListMarq_URL;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                marqs = parseMarqs(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return marqs;
    }
    /*******************Ratings******************/
  
    public void addRating(Rating r)
    {
    String url = Statics.AddRating_URL+"?feedback="+r.getFeedback()+"&ratings="+r.getRatings();
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
}
