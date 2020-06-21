/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.enitities.HistoriqueEquipement;
import com.mycompany.services.ServiceEquipement;
import java.util.ArrayList;

/**
 *
 * @author USER
 */
public class HistoriqueEquipementForm extends Form {
     Form f = new Form("List Historique");
       ImageViewer imgv=null;
    ServiceEquipement se=ServiceEquipement.getInstance();
    ArrayList<HistoriqueEquipement> historiques = se.getAllHistoriqueEquipements();
    public HistoriqueEquipementForm(){
          ImageViewer img1=null;
     this.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        try {
             img1 = new ImageViewer(Image.createImage("/agora.png"));
        } catch (Exception e) {
        };
        this.getToolbar().addCommandToLeftBar("Home", null, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new Welcome().show();
            }
        });
        this.getToolbar().addCommandToRightBar("Back", null, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
             new EquipementForm().showBack();
            }
        });
        ImageViewer img=null;
        try {
              img = new ImageViewer(Image.createImage("/add.png"));
        } catch (Exception e) {
        };
        Button btadd = new Button(img.getImage());
         ImageViewer img2=null;
          try {
              img2 = new ImageViewer(Image.createImage("/Liste.png"));
        } catch (Exception e) {
        };
        Button btList = new Button(img2.getImage());
       
        
        
        //liste Historique
        btList.addActionListener(new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent evt) {
              
             
               f.getToolbar().addCommandToRightBar("Back", null, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
             new HistoriqueEquipementForm().showBack();
            }
        });
                  
               
                for (HistoriqueEquipement h : historiques ) {
                    System.out.println(h.toString());
                  addItem(h);            
                }
                f.show();
              }
          });
        
         /**************************/
         
    
         /**************************/
        
         
         
          /**************************/
         
        this.setTitle("Historique Equipement");
        this.add(img1);
        btList.setText("List Historique");
        this.add(btList);
        
     
    }
    //List    
        public void addItem ( HistoriqueEquipement h) {
         
             ImageViewer imgh = null;
           
                   if (h.getEquipement().equals(HistoriqueEquipement.equipement.materiels) ){
               
                     try {
             imgh = new ImageViewer(Image.createImage("/mat.png"));
 
         } catch (Exception e) {
            
         } 
            }
            if (h.getEquipement().equals(HistoriqueEquipement.equipement.vehicule) ){
                
                     try {
             imgh = new ImageViewer(Image.createImage("/livraison.png"));
             
         } catch (Exception e) {
            
         }
            }
           
             Container C1 = new Container(new BoxLayout(BoxLayout.X_AXIS)); 
              Container c2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
              Label dateH = new Label("Date: " + h.getDate()+ " ");
            Label opH = new Label("Operation: " + h.getOperation().toString()+ " ");
             Label eqH = new Label("Equipement: " + h.getEquipement().toString() + " ");
             Label qteH = new Label("Quantité: " +Integer.toString(h.getQte()) + " ");
   
            Label sep = new Label ("-------------------------------------------------------------");
            c2.add(dateH);
            c2.add(opH);
            c2.add(eqH);
            c2.add(qteH);
            C1.add(imgh);
            C1.add(c2);
              C1.setLeadComponent(dateH);
              f.add(C1);
              f.add(sep);
              
              f.refreshTheme();
              dateH.addPointerPressedListener(new ActionListener() {
                 @Override
                 public void actionPerformed(ActionEvent evt) {
                       Dialog d = new Dialog();
                    Dialog.show("Equipment History", " Date : "+h.getDate()+"\n"+" operation : "+h.getOperation()+"\n"+" Equipement : "+h.getEquipement()+"\n"+" Prix : "+h.getPrix()+"\n"+" Quantité : "+h.getQte(), "OK", null);
                    f.showBack();
                     }
             });
     }  
    
}
