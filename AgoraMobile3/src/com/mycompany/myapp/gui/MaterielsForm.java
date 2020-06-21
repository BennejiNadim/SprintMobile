/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.mycompany.enitities.Equipement;
import com.mycompany.enitities.Materiels;
import com.mycompany.services.ServiceEquipement;
import java.util.ArrayList;
import java.util.Date;


/**
 *
 * @author USER
 */
public class MaterielsForm extends Form {
    Form f = new Form("Materiels");
       ImageViewer img1=null;
    ServiceEquipement se=ServiceEquipement.getInstance();
    ArrayList<Materiels> materiels = se.getAllMateriels();
    public MaterielsForm(){
          ImageViewer img1=null;
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
           
        }
        Button btadd = new Button(img.getImage());
         ImageViewer img2=null;
          try {
              img2 = new ImageViewer(Image.createImage("/Liste.png"));
        } catch (Exception e) {
        }
        Button btList = new Button(img2.getImage());
       
        
        
        //liste vehicule
        btList.addActionListener(new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent evt) {
              
             
               f.getToolbar().addCommandToRightBar("Back", null, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
             new VehiculeForm().showBack();
            }
        });
                  
               
                for (Materiels m : materiels ) {
                  addItem(m);            
                }
                f.show();
              }
          });
        
         /**************************/
         
        ImageViewer img3= null;
        try {
              img3 = new ImageViewer(Image.createImage("/stat.jpg"));
        } catch (Exception e) {
        };
        Button btstat = new Button(img3.getImage());
        
        
       //statics
         btstat.addActionListener(new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent evt) {
               
              }
          });
         /**************************/
         
           //add
         btadd.addActionListener(new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent evt) {
             new AddMaterielsForm().show();
              }
          });
         
         
          /**************************/
         
        this.setTitle("Materiels");
        this.add(img1);
        btadd.setText("Add Materiels");
        this.add(btadd);
        btList.setText("List Materiels");
        this.add(btList);
        btstat.setText("Static Materiels");
        this.add(btstat);
        
     
    }
    //List    
        public void addItem ( Materiels m) {
         
             ImageViewer imgm = null;
   
           
                     try {
             imgm = new ImageViewer(Image.createImage("/mat.png"));
             
         } catch (Exception e) {
            
         }
            
             Container C1 = new Container(new BoxLayout(BoxLayout.X_AXIS)); 
              Container c2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
              Label nomMat = new Label("Nom: " + m.getNom() + " ");
            Label couleurMat = new Label ("Quantité: " + m.getQte() + " ");
            Label etatMat= new Label("Etat: " + m.getEtat().toString() + " ");
            Label sep = new Label ("-------------------------------------------------------------");
            c2.add(nomMat);
            c2.add(couleurMat);
            c2.add(etatMat);
            C1.add(imgm);
            C1.add(c2);
              C1.setLeadComponent(nomMat);
              f.add(C1);
              f.add(sep);
              f.refreshTheme();
              
              nomMat.addPointerPressedListener(new ActionListener() {
                 @Override
                 public void actionPerformed(ActionEvent evt) {
                     Form vm = new Form ();
                      vm.getToolbar().addCommandToRightBar("Back", null, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
             new MaterielsForm().showBack();
            }
        });
                     
                      ImageViewer img1=null;
       Label nom = new Label("Nom: ");
     Label Qte = new Label("Quantité: ");
   Label etat = new Label("Etat: ");
    TextField nomm = new TextField(m.getNom());
     TextField Qtem = new TextField(m.getQte());
   ComboBox etatm = new ComboBox("disponnible","en_panne","corbeille");
    Button edit=new Button("Edit Materials"); 
    Button change=new Button("Change State"); 
    Button delete=new Button("Delete Materials"); 
    Container c5 =new Container(new BoxLayout(BoxLayout.X_AXIS));
        Container c6=new Container(new BoxLayout(BoxLayout.X_AXIS));
         Container c9=new Container(new BoxLayout(BoxLayout.X_AXIS));
        Container c7=new Container(new FlowLayout(CENTER,CENTER));
        Container c8=new Container(new FlowLayout(CENTER,CENTER));
        c5.add(nom);
        c5.add(nomm);
        c6.add(Qte);
        c6.add(Qtem);
        c9.add(etat);
        c9.add(etatm);
        c7.add(edit);
        c7.add(change);
        c8.add(delete);
        vm.add(c5);
        vm.add(c6);
        vm.add(c9);
        vm.add(c7);
        vm.add(c8);
        
        
        
          edit.addActionListener(new ActionListener() {
                         @Override
                         public void actionPerformed(ActionEvent evt) {
                             if (Dialog.show("Edite","Are you sure?","Yes","No")) {
                      m.setNom(nomm.getText());
                m.setQte(Integer.parseInt(Qtem.getText()));
                              if(se.editeMateriels(m)){
                                  
                    
                     Dialog.show("", "Materials edited ","Ok","cancel");
                    
                       new MaterielsForm().showBack();
                
                }else{
                Dialog.show("", "there was an error","Ok","cancel");
                }
}
                              
                         }
                     });
         change.addActionListener(new ActionListener() {
                         @Override
                         public void actionPerformed(ActionEvent evt) {
//                            if ((m.getQte()) >= (Integer.parseInt(Qtem.getText()))) {
//                                 ArrayList<Materiels> materielses = se.getAllMateriels();
//          for (Materiels m1 : materielses ) {
//                 if ((m1.getNom().equals(nomm.getText()))&&(m1.getEtat().equals(se.stringToEtat(etatm.getSelectedItem().toString())))) {
//                     m1.setQte(m1.getQte()+Integer.parseInt(Qtem.getText()));
//                     m.setQte(m.getQte()-Integer.parseInt(Qtem.getText()));
//                     se.editeMateriels(m1);
//                     se.editeMateriels(m);
//                     
//                            }
//                 else {
//                     m.setQte(m.getQte()-Integer.parseInt(Qtem.getText()));
//                     se.editeMateriels(m);
//                     Materiels m1= new Materiels(Integer.parseInt(Qtem.getText()),m.getNom() ,se.stringToEtat(etatm.getSelectedItem().toString()));
//                     se.addMateriels(m1);
                     
//                 }
//          }}
                         }
                     });
         delete.addActionListener(new ActionListener() {
                         @Override
                         public void actionPerformed(ActionEvent evt) {
                 if (Dialog.show("Delete","Are you sure?","Yes","No")) {
                        if(se.deleteMateriels(m)){
                    
                     Dialog.show("", "Materials deleted ","Ok","cancel");
                    
                       new MaterielsForm().showBack();
                
                }else{
                Dialog.show("", "there was an error","Ok","cancel");
                } 
}
                              
                         }
                     });
                     
             vm.show();
                 }
             });
     }  
}
