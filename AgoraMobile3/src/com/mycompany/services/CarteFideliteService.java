/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

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
import java.util.List;
import java.util.Map;

/**
 *
 * @author chaima
 */
public class CarteFideliteService {

     
    float pts =0;
    public float getPoints() throws ParseException {  
        
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl(Statics.BASE_URL_VENTE+"vente/wsCartefidPoints?user="+UserService.user);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                
                
                    pts = Float.parseFloat((new String(con.getResponseData())));
                    
                    //Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
               
                
                
                
            }
        });
      
        NetworkManager.getInstance().addToQueueAndWait(con);
        return (pts/10);
    }
    
    
    
    public void creerCarteFid(){
        
        
        ConnectionRequest req = new ConnectionRequest();
        String url = "http://127.0.0.1:81/agora2/web/app_dev.php/vente/wsCartefid";
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) { 
                req.removeResponseListener(this); 

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
    

    
    

}
