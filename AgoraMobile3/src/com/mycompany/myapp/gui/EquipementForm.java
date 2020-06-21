/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
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
public class EquipementForm extends Form {
     ImageViewer img1=null;
     
    
    public EquipementForm(){
     this.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        try {
              img1 = new ImageViewer(Image.createImage("/agora.png"));
        } catch (Exception e) {
        };
        this.getToolbar().addCommandToLeftSideMenu("Home", null, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
          new Welcome().show();
            }
        });
        this.getToolbar().addCommandToRightBar("Back", null, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
           new HomeForm().showBack();
            }
        });
        this.getToolbar().addCommandToLeftSideMenu("vehicle", null, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                 new VehiculeForm().show();
            }
        });
        this.getToolbar().addCommandToLeftSideMenu("Materials", null, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new MaterielsForm().show();
            }
        });
        this.getToolbar().addCommandToLeftSideMenu("Equipment History", null, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
               new HistoriqueEquipementForm().show();
            }
        });
        this.add(img1);
        this.setTitle("Equipment Management");
       
    }
}
