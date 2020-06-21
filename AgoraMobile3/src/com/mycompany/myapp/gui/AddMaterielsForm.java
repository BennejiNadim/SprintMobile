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
import com.codename1.ui.TextField;
import com.mycompany.services.ServiceEquipement;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.mycompany.enitities.Equipement;
import com.mycompany.enitities.HistoriqueEquipement;
import com.mycompany.enitities.Materiels;


/**
 *
 * @author USER
 */
public class AddMaterielsForm extends Form {
      public ServiceEquipement se=ServiceEquipement.getInstance();
    ImageViewer img1=null;
    private Label nom = new Label("Nom");
    private Label Qte = new Label("Quantité");
    private Label Prix = new Label("Prix");
    private TextField nomm = new TextField("", "nom");
    private TextField Qtem = new TextField("", "123");
    private TextField prixm = new TextField("", "123.");
    Button add=new Button("add Materiels");
    private TextField uP=new TextField("");
//      Date d=java.util.Calendar.getInstance().getTime();
    public AddMaterielsForm()
    {
        try {
                img1 = new ImageViewer(Image.createImage("/agora.png"));
            } catch (Exception e) {
            };
        this.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        add(img1);
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
       
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Materiels m=new Materiels();
                HistoriqueEquipement h = new HistoriqueEquipement();
                 
                
                m.setNom(nomm.getText());
                m.setQte(Integer.parseInt(Qtem.getText()));
               m.setEtat(Equipement.etat.disponnible);
                
               
                
                
                
               
                                

                if(se.addMateriels(m)){
                    Dialog.show("", "Materiels added ","Ok","cancel");
                    new MaterielsForm().showBack();
                }else{
                Dialog.show("", "there was an error","Ok","cancel");
                }
             
           }
        });
        Container c1=new Container(new BoxLayout(BoxLayout.X_AXIS));
        Container c2=new Container(new BoxLayout(BoxLayout.X_AXIS));
        Container c3=new Container(new BoxLayout(BoxLayout.X_AXIS));
        Container c4=new Container(new BoxLayout(BoxLayout.X_AXIS));
        Container c5=new Container(new BoxLayout(BoxLayout.X_AXIS));
        Container c6=new Container(new FlowLayout(CENTER,CENTER));
        c1.add(nom);
        c1.add(nomm);
        c2.add(Qte);
        c2.add(Qtem);
        c3.add(Prix);
        c3.add(prixm);
        c6.add(add);
        add(c1);
        add(c2);
        add(c3);
        add(c6);
}
}
