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
import com.mycompany.enitities.Transaction;

import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author wmedi
 */
public class ServicesTransaction {
    public ArrayList<Transaction> transactions;
    
    public static ServicesTransaction instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServicesTransaction() {
         req = new ConnectionRequest();
    }

    public static ServicesTransaction getInstance() {
        if (instance == null) {
            instance = new ServicesTransaction();
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

    public ArrayList<Transaction> parseTransaction(String jsonText){
        try {
            transactions=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> transactionsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)transactionsListJson.get("root");
            for(Map<String,Object> obj : list){
                Transaction t = new Transaction((int)Float.parseFloat(obj.get("id").toString()), (int)Float.parseFloat(obj.get("idfacture").toString()), obj.get("etattransaction").toString(), null, Float.parseFloat(obj.get("monton").toString()), obj.get("description").toString());
                
                
                transactions.add(t);
            }
            
            
        } catch (IOException ex) {
            
        }
        return transactions;
    }
    
    public ArrayList<Transaction> getAllTransaction(){
        String url = Statics.BASE_URL+"transaction/all";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                transactions = parseTransaction(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return transactions;
    }
    
    public ArrayList<Transaction> getClientTransaction(){
        String url = Statics.BASE_URL+"transaction/client";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                transactions = parseTransaction(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return transactions;
    }
}
