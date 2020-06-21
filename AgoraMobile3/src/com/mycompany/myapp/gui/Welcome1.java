/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.utils.Statics;


/**
 *
 * @author wmedi
 */
public class Welcome1 extends Form {
    Form current;
    public Welcome1() {
        
        current=this;
        ImageViewer img = null;
        setTitle("Welcome to Agora");
        getToolbar().addCommandToRightBar("logout", null, e->{
            new HomeForm().show();
            
        });
        try {
            img = new ImageViewer(Image.createImage("/agora.png"));
        } catch (Exception e) {
        };
        getToolbar().addCommandToLeftSideMenu("Products", null, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new ProductForm().show();
            }
        });
        getToolbar().addCommandToLeftSideMenu("Equipements", null, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new EquipementForm().show();
            }
        });
        getToolbar().addCommandToLeftSideMenu("Facture", null, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new ClientFactureForm(current,Statics.username).show();
            }
        });
        getToolbar().addCommandToLeftSideMenu("Transaction", null, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new ClientTransactionForm(current,Statics.username).show();
            }
        });
    }

    
}
