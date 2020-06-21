/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.charts.util.ColorUtil;
import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.services.ServiceEquipement;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.mycompany.enitities.Equipement;
import com.mycompany.enitities.HistoriqueEquipement;
import com.mycompany.enitities.Vehicule;
import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;

/**
 *
 * @author USER
 */
public class AddVehiculeForm extends Form{
     public ServiceEquipement se=ServiceEquipement.getInstance();
     HistoriqueEquipement h = new HistoriqueEquipement();
    ImageViewer img1=null;
    private Label nom = new Label("Nom");
    private Label matricule = new Label("Matricule");
    private Label couleur = new Label("Couleur");
    private Label utilisation = new Label("Utilisation");
    private Label Prix = new Label("Prix");
    private Label km = new Label("km");
    private TextField nomv = new TextField("", "nom");
    private TextField mat = new TextField("", "123TUN123");
    private TextField couleurv = new TextField("", "Couleur");
    private ComboBox utilisationv = new ComboBox("vehicule_personnel,","vehicule_livraison");
    private TextField kmv = new TextField("", "123");
    private TextField prixv = new TextField("", "123.");
    Button add=new Button("add Vehicule");
    private TextField uP=new TextField("");
   
      Date d=java.util.Calendar.getInstance().getTime();
    public AddVehiculeForm()
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
                Vehicule v=new Vehicule();
                HistoriqueEquipement h = new HistoriqueEquipement();
                v.setNom(nomv.getText());
                v.setMatricule(mat.getText());
                v.setCouleur(couleurv.getText());
                v.setUtilisation(se.stringToUtilisation(utilisationv.getSelectedItem().toString()));
               v.setKM(Integer.parseInt(kmv.getText()));
               v.setEtat(Equipement.etat.disponnible);
                v.setPrix(Float.valueOf(prixv.getText()));
             
                if(se.addVehicule(v)){
                    
                     Dialog.show("", "Vehicule added ","Ok","cancel");
                    
                       new VehiculeForm().showBack();
                
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
         Container c7=new Container(new BoxLayout(BoxLayout.X_AXIS));
        Container c6=new Container(new FlowLayout(CENTER,CENTER));
        c1.add(nom);
        c1.add(nomv);
        c2.add(matricule);
        c2.add(mat);
        c3.add(couleur);
        c3.add(couleurv);
        c4.add(utilisation);
        c4.add(utilisationv);
        c5.add(km);
        c5.add(kmv);
        c7.add(Prix);
        c7.add(prixv);
        c6.add(add);
        add(c1);
        add(c2);
        add(c3);
        add(c4);
        add(c5);
         add(c7);
        add(c6);
        
    }
    //        if (se.testTextInput(nom.getText())){
//            if (se.testNumberInput(mat1.getText())&& (!(mat1.getText().length()==0)) && se.testTextInput(mat2.getText()) && se.testNumberInput(mat3.getText())&& (!(mat3.getText().length()==0))) {
//             
//
//        if(se.testTextInput(c) ) {
//            if (se.testTextInput(u)) {
//                if (se.testNumberInput(km.getText()) && (!(km.getText().length()==0))) {
//                    if (se.testTextInput(e)){
//           String mat = matricule.getText() + matricule1.getText()+matricule2.getText();
//           if (!sv.checkMatricule(mat)) {
//               if (se.testFloatInput(prix.getText())) {
//        Vehicule v = new Vehicule(mat,couleurt.getValue().toString(), sv.stringToUtilisation(utilisationc.getValue().toString())  , Integer.parseInt(km.getText()), nom.getText(), se.stringToEtat(etatc.getValue().toString()));
//         v.setPrix(Float.parseFloat(prix.getText()));
    
    
    
    
    
    
    
    
//    public boolean testTextInput(String a) {
//
//        boolean b = true;
//        if (a.length() == 0 || testNumberInput(a)) {
//            b = false;
//        }
//
//        return b;
//
//    }
//
//    public boolean testNumberInput(String c) {
//        boolean b = false;
//        if (c.matches(("^[0-9]*"))) {
//            b = true;
//        }
//        return b;
//    }
//    public boolean testFloatInput (String a) {
//        try
//{
//   Float i = Float.parseFloat(a);
//   return true;
//}
//catch ( Exception e )
//{
//  return false;
//}
//    }
}
