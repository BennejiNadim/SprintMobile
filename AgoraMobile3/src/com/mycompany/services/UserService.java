/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.mycompany.enitities.Product;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author jha
 */
public class UserService {
        public static int user=2;
        private ConnectionRequest req ;
        
        public UserService() {
        req = new ConnectionRequest();
    }
    public void login(String login, String pass) {
        String url = Statics.BASE_URL_VENTE+"vente/wsLogin?email=" + login+"&password="+pass;
        
        req.setUrl(url);
        req.setPost(false);
        
                    JSONParser j = new JSONParser();
req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                 try {
                req.removeResponseListener(this);
              
                 JSONParser j = new JSONParser();
                    String json = new String(req.getResponseData()) + "";
                    System.out.println(json);
                    if (json.length()<4) {
                        Dialog.show("Echec d'authenfication", "username ou mot de passe éronné", "Ok", null);
                    } else {
                    System.out.println(json);

                        Map<String, Object> user2 = j.parseJSON(new CharArrayReader(json.toCharArray()));
                          
                            user = (int) Float.parseFloat(user2.get("id").toString());
                            Statics.username = user2.get("username").toString();
                    
                }
            
                 } catch (Exception ex) {
                    Dialog.show("Echec d'authenfication", "username ou mot de passe éronné", "Ok", null);
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        
    }

}
