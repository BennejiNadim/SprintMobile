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
import com.mycompany.enitities.Product;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author jha
 */
public class DetailCommandeService {
     public Map<Produit,Integer> parseListCommandesJson(String json) throws ParseException, IOException  {

        Map<Produit,Integer> listProducts = new HashMap<Produit, Integer>();
        Map<Integer,Integer> listIdProducts = new HashMap<Integer, Integer>();

      try {
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            /*
                On doit convertir notre réponse texte en CharArray à fin de
            permettre au JSONParser de la lire et la manipuler d'ou vient 
            l'utilité de new CharArrayReader(json.toCharArray())
            
            La méthode parse json retourne une MAP<String,Object> ou String est 
            la clé principale de notre résultat.
            Dans notre cas la clé principale n'est pas définie cela ne veux pas
            dire qu'elle est manquante mais plutôt gardée à la valeur par defaut
            qui est root.
            En fait c'est la clé de l'objet qui englobe la totalité des objets 
                    c'est la clé définissant le tableau de tâches.
            */
            Map<String, Object> product = j.parseJSON(new CharArrayReader(json.toCharArray()));
       
                       
            
            /* Ici on récupère l'objet contenant notre liste dans une liste 
            d'objets json List<MAP<String,Object>> ou chaque Map est une tâche                
            */
            List<Map<String, Object>> list = (List<Map<String, Object>>) product.get("root");
            

            //Parcourir la liste des tâches Json
            for (Map<String, Object> obj : list) {
                
                
                listIdProducts.put((int)Float.parseFloat(obj.get("idProduit").toString()), (int)Float.parseFloat(obj.get("quantite").toString()));
                

            }
            
            List<Produit> prods = new ArrayList<Produit>();
            ServiceProd ps = ServiceProd.getInstance();
            prods = ps.getAllProds();
                    
              
        for (Map.Entry<Integer, Integer> entry : listIdProducts.entrySet()) {

            for (Produit x : prods){
                if(x.getRefProduit() == entry.getKey());
            listProducts.put(x, entry.getValue());
                    };
        }      
              } catch (IOException ex) {
            
         } 

     return listProducts;
    }
     
     Map<Produit,Integer> listProducts= new HashMap<Produit,Integer>();
    
    public Map<Produit,Integer> getDetailsCommandes(int commande) throws ParseException {       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl(Statics.BASE_URL_VENTE+"vente/wsDetailsCommandes?idCommande="+commande);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                
                
                try {
                    listProducts = parseListCommandesJson(new String(con.getResponseData()));
                } catch (ParseException ex) {
                    //Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    //Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                
                
            }
        });
      
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listProducts;
    }
    

}
