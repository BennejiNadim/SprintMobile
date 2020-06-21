/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.table.DefaultTableModel;
import com.codename1.ui.table.Table;
import com.codename1.ui.table.TableModel;
import com.mycompany.enitities.Facture;
import com.mycompany.services.ServicesFacture;

import java.util.*;






/**
 *
 * @author wmedi
 */
public class ClientFactureForm extends Form {
    Form current;
     ImageViewer img1=null;
    public ClientFactureForm(Form previous, String username) {
        current=this;
      
       
        ArrayList<Facture> allFactures = ServicesFacture.getInstance().getClientFactures();
        
     
        
        setTitle("Liste Facture");
       
        //add(sp);
        this.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        try {
             img1 = new ImageViewer(Image.createImage("/agora.png"));
        } catch (Exception e) {
        }
        this.getToolbar().addCommandToLeftBar("Home", null, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
              new Welcome().show();
            }
        });
        
        for (Facture f : allFactures) {
            Label sep = new Label ("-------------------------------------------------------------");
            Container C1 = new Container(new BoxLayout(BoxLayout.X_AXIS)); 
            Container C2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            Container c3 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            Label fp = new Label("prix: " + f.getMontant()+ " ");
            Label dp = new Label("Date: " + f.getDateFacturation()+ "15/06/2020 ");
            Label et = new Label("Etat: " + f.getEtatFacture()+ " ");
            c3.add(fp);
            C2.add(dp);
            C2.add(et);
            C1.add(C2);
            C1.add(c3);
            
            this.add(C1);
            this.add(sep);
        }
    }

    
}
