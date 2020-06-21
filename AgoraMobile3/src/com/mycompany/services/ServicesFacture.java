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
import com.mycompany.enitities.Facture;

import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author wmedi
 */
public class ServicesFacture {
    public ArrayList<Facture> factures;
    
    public static ServicesFacture instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServicesFacture() {
         req = new ConnectionRequest();
    }

    public static ServicesFacture getInstance() {
        if (instance == null) {
            instance = new ServicesFacture();
        }
        return instance;
    }

    /*
    public boolean addTask(Facture f) {
        String url = Statics.BASE_URL + "/tasks/" + f.getName() + "/" + f.getStatus();
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
    }*/

    public ArrayList<Facture> parseFactures(String jsonText){
        try {
            factures=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> facturesListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)facturesListJson.get("root");
            for(Map<String,Object> obj : list){
                Facture f = new Facture(obj.get("etatfacture").toString(), Float.parseFloat(obj.get("montant").toString()), obj.get("clientlogin").toString(), obj.get("employelogin").toString(), (int)Float.parseFloat(obj.get("supplierid").toString()), obj.get("typefacture").toString());
                float id = Float.parseFloat(obj.get("id").toString());
                f.setId((int)id);
                f.setDateFacturation(null);
                
                factures.add(f);
            }
            
            
        } catch (IOException ex) {
            
        }
        return factures;
    }
    
    public ArrayList<Facture> getAllFactures(){
        String url = Statics.BASE_URL+"facture/all";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                factures = parseFactures(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return factures;
    }
    
    public ArrayList<Facture> getClientFactures(){
        String url = Statics.BASE_URL+"facture/client";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                factures = parseFactures(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return factures;
    }
}
