/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.mycompany.enitities.Commande;
import com.mycompany.enitities.Produit;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author jha
 */
public class CommandeService {
     public ArrayList<Commande> parseListCommandesJson(String json) throws ParseException, IOException  {

        ArrayList<Commande> listProducts = new ArrayList<>();

      try {
            JSONParser j = new JSONParser();
            
            Map<String, Object> product = j.parseJSON(new CharArrayReader(json.toCharArray()));
       
                       
            
            /* Ici on récupère l'objet contenant notre liste dans une liste 
            d'objets json List<MAP<String,Object>> ou chaque Map est une tâche                
            */
            List<Map<String, Object>> list = (List<Map<String, Object>>) product.get("root");
            
                    //      [ {"key1" : value1,"key2" : value1},{"key1" : value1,"key2" : value1} ]
            //Parcourir la liste des tâches Json
            for (Map<String, Object> obj : list) {
                //{"key1" : value1,"key2" : value1}
                //Création des tâches et récupération de leurs données
                Commande c = new Commande();

                float id = Float.parseFloat(obj.get("id").toString());
                c.setId((int) id) ;
                c.setTotal(Float.parseFloat(obj.get("id").toString()));
                c.setDate((String) obj.get("dateSTR"));
                
               // System.out.println(c);
                
                listProducts.add(c);

            }
              } catch (IOException ex) {
            
         } 

     
        
        /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
        */
       // System.out.println(listCotisation);
        return listProducts;
    }
     
     ArrayList<Commande> listProducts= new ArrayList<>();
    
    public ArrayList<Commande> getListCommandes() throws ParseException {       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl(Statics.BASE_URL_VENTE+"vente/wsCommandes?user="+UserService.user);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {                
                
                try {
                    listProducts = parseListCommandesJson(new String(con.getResponseData()));
                } catch (ParseException ex) {
                   // Logger.getLogger(CommandeService.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                   // Logger.getLogger(CommandeService.class.getName()).log(Level.SEVERE, null, ex);
                }
                              
            }
        });      
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listProducts;
    }
    
    
    
        public void supprimerCommande(int id) throws ParseException {       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl(Statics.BASE_URL_VENTE+"vente/wsSupprimerCommande?id="+id);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {                
                              
            }
        });      
        NetworkManager.getInstance().addToQueueAndWait(con);
        
    }
    

}
