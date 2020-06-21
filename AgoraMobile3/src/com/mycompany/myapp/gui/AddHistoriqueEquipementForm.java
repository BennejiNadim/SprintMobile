package com.mycompany.myapp.gui;

///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.mycompany.myapp.GUI;
//
//import com.codename1.components.ImageViewer;
//import com.codename1.ui.Button;
//import com.codename1.ui.Dialog;
//import com.codename1.ui.Form;
//import com.codename1.ui.Image;
//import com.codename1.ui.events.ActionEvent;
//import com.codename1.ui.events.ActionListener;
//import com.codename1.ui.layouts.BoxLayout;
//import com.codename1.ui.layouts.FlowLayout;
//import com.mycompany.enitities.HistoriqueEquipement;
//import com.mycompany.services.ServiceEquipement;
//import java.util.Calendar;
//import java.util.Date;
//
///**
// *
// * @author USER
// */
//public class AddHistoriqueEquipementForm extends Form {
//    Button add= new Button("add");
//    ImageViewer img1 = null;
//      public ServiceEquipement se=ServiceEquipement.getInstance();
//      public AddHistoriqueEquipementForm()
//    {
//        try {
//                img1 = new ImageViewer(Image.createImage("/agora.png"));
//            } catch (Exception e) {
//            };
//        this.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
//        add(img1);
//                this.getToolbar().addCommandToLeftBar("Home", null, new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent evt) {
//     new HomeForm().showBack();
//            }
//        });
//        this.getToolbar().addCommandToRightBar("Back", null, new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent evt) {
//  new VehiculeForm().showBack();
//            }
//        });
//       
//        add.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent evt) {
//                HistoriqueEquipement h = new HistoriqueEquipement();
//               
//       h.setDate("123");
//               
//                h.setEquipement(HistoriqueEquipement.equipement.vehicule);
//                h.setOperation(HistoriqueEquipement.operation.achat);
//                h.setIdm(100);
//                h.setIdf(000);
//                h.setIdv(10);
//                h.setQte(1);
//                h.setPrix(1000);
//                
//                 System.out.println(h.toString());
//               
//                                
//
//                if(se.addHistoriqueEquipement(h)){
//                    Dialog.show("", "Historique added ","Ok","cancel");
//                }else{
//                Dialog.show("", "there was an error","Ok","cancel");
//                }
//             
//           }
//        });
//       
//        add(add);
//}}
