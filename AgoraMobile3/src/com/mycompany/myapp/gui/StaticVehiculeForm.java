/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.enitities.Vehicule;
import com.mycompany.services.ServiceEquipement;
import java.util.ArrayList;

/**
 *
 * @author USER
 */
public class StaticVehiculeForm extends Form {
  Label lb1 = new Label("Disponnible: ");
  Label lb2 = new Label("en_panne: ");
  Label lb3 = new Label("en_maintenance: ");
    Slider s1 = new Slider();
    Slider s2 = new Slider();
    Slider s3 = new Slider();
    int s=0;
    int d=0;
    int p=0;
    int m=0;
    Button btcal=new Button("calculate");
                ServiceEquipement se=ServiceEquipement.getInstance();
    ArrayList<Vehicule> vehicules = se.getAllVehicule();
    public StaticVehiculeForm(){
        this.setTitle("Statics");
          ImageViewer img1=null;
               Container c1=new Container(new BoxLayout(BoxLayout.X_AXIS));
        Container c2=new Container(new BoxLayout(BoxLayout.X_AXIS));
        Container c3=new Container(new BoxLayout(BoxLayout.X_AXIS));


     this.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        try {
             img1 = new ImageViewer(Image.createImage("/agora.png"));
        } catch (Exception e) {
        };
        this.getToolbar().addCommandToLeftBar("Home", null, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
               new HomeForm().showBack();
            }
        });
        this.getToolbar().addCommandToRightBar("Back", null, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
             new VehiculeForm().showBack();
            }
       
            
        });
        btcal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
               for (Vehicule v : vehicules ) {
                  
                   s=s+1;
                   if (v.getEtat().equals(Vehicule.etat.disponnible)) {
                       d=d+1;
                   }
                    if (v.getEtat().equals(Vehicule.etat.en_maintenance)) {
                       m=m+1;
                   }
                     if (v.getEtat().equals(Vehicule.etat.en_panne)) {
                       p=p+1;
                   }
                     
            }
               s1.setProgress((int)(d*100/s));
                s2.setProgress((int)(p*100/s));
                 s1.setProgress((int)(m*100/s));
            lb1.setText("Disponnible :"+s1.getProgress()+"%");
            lb2.setText("Panne :"+s2.getProgress()+"%");
            lb3.setText("Maintenance :"+s3.getProgress()+"%");
            }
        });
        
         s1.setMaxValue(101);
                s1.setMinValue(0);              
                s1.setIncrements(1);
              
                
                s2.setMaxValue(101);
                s2.setMinValue(0);              
                s2.setIncrements(1);
                
                        
                s3.setMaxValue(101);
                s3.setMinValue(0);              
                s3.setIncrements(1);
               
                
            c1.add(lb1);
            c1.add(s1);
          
            
            c2.add(lb2);
            c2.add(s2);
      
            c3.add(lb3);
            c3.add(s3);
          
           
           add(c1);
           add(c2);
           add(c3);
            add(btcal);
}}
