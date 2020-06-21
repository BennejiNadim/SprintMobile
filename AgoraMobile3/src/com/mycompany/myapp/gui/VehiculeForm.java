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
import com.mycompany.enitities.HistoriqueEquipement;
import com.mycompany.enitities.Materiels;
import com.mycompany.enitities.Produit;
import com.mycompany.enitities.Vehicule;
import com.mycompany.services.ServiceEquipement;
import java.util.ArrayList;
import javafx.collections.ObservableList;

/**
 *
 * @author USER
 */
public class VehiculeForm extends Form {

    Form f = new Form("Vehicule");
    ImageViewer imgv = null;
    ServiceEquipement se = ServiceEquipement.getInstance();
    ArrayList<Vehicule> vehicules = se.getAllVehicule();
    
    public VehiculeForm() {
        ImageViewer img1 = null;
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
        ImageViewer img = null;
        try {
            img = new ImageViewer(Image.createImage("/add.png"));
        } catch (Exception e) {
        };
        Button btadd = new Button(img.getImage());
        ImageViewer img2 = null;
        try {
            img2 = new ImageViewer(Image.createImage("/Liste.png"));
        } catch (Exception e) {
        };
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

                for (Vehicule v : vehicules) {
                    addItem(v);
                }
                f.show();
            }
        });

        /**
         * ***********************
         */
        ImageViewer img3 = null;
        try {
            img3 = new ImageViewer(Image.createImage("/stat.jpg"));
        } catch (Exception e) {
        };
        Button btstat = new Button(img3.getImage());

        //statics
        btstat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new StaticVehiculeForm().show();
            }
        });
        /**
         * ***********************
         */

        //add
        btadd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new AddVehiculeForm().show();
            }
        });

        /**
         * ***********************
         */
        this.setTitle("Vehicule");
        this.add(img1);
        btadd.setText("Add vehicle");
        this.add(btadd);
        btList.setText("List vehicle");
        this.add(btList);
        btstat.setText("Static vehicle");
        this.add(btstat);

    }

    //List    
    public void addItem(Vehicule v) {

        ImageViewer imgv = null;
        if (v.getUtilisation().toString().equals("vehicule_personnel")) {

            try {
                imgv = new ImageViewer(Image.createImage("/personnel.png"));

            } catch (Exception e) {

            }
        }
        if (v.getUtilisation().toString().equals("vehicule_livraison")) {

            try {
                imgv = new ImageViewer(Image.createImage("/livraison.png"));

            } catch (Exception e) {

            }
        }
        Container C1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Container c2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Label nomVeh = new Label("Nom: " + v.getNom() + " ");
        Label matriculeVeh = new Label("Couleur: " + v.getMatricule() + " ");
        Label utilisationVeh = new Label("Utilisation: " + v.getUtilisation().toString() + " ");
        Label etatVeh = new Label("Etat: " + v.getEtat().toString() + " ");
        Label sep = new Label("-------------------------------------------------------------");
        c2.add(nomVeh);
        c2.add(matriculeVeh);
        c2.add(utilisationVeh);
        c2.add(etatVeh);
        C1.add(imgv);
        C1.add(c2);
        C1.setLeadComponent(nomVeh);
        f.add(C1);
        f.add(sep);

        f.refreshTheme();

        nomVeh.addPointerPressedListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Form vm = new Form();

                Label nom = new Label("Nom: ");
                Label matricule = new Label("Matricule: ");
                Label couleur = new Label("Couleur: ");
                Label utilisation = new Label("Utilisation: ");
                Label km = new Label("km: ");
                Label etat = new Label("Etat: ");
                TextField nomv = new TextField(v.getNom());
                TextField mat = new TextField(v.getMatricule());
                TextField couleurv = new TextField(v.getCouleur());
                ComboBox utilisationv = new ComboBox();
                ComboBox etatv = new ComboBox("disponnible", "en_panne", "en_maintenance", "vendu");
                if (v.getUtilisation().equals(Vehicule.utilisation.vehicule_personnel)) {
                    utilisationv.addItem("vehicule_personnel");
                    utilisationv.addItem("vehicule_livraison");

                } else {
                    utilisationv.addItem("vehicule_livraison");
                    utilisationv.addItem("vehicule_personnel");
                }
                TextField kmv = new TextField(v.getKM().toString());

                Button edit = new Button("Edit Vehicle");
                Button change = new Button("Change State");
                Button delete = new Button("Delete Vehicle");
                vm.getToolbar().addCommandToRightBar("Back", null, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        new VehiculeForm().showBack();
                    }
                });

                Container c1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
                Container c2 = new Container(new BoxLayout(BoxLayout.X_AXIS));
                Container c3 = new Container(new BoxLayout(BoxLayout.X_AXIS));
                Container c4 = new Container(new BoxLayout(BoxLayout.X_AXIS));
                Container c5 = new Container(new BoxLayout(BoxLayout.X_AXIS));
                Container c7 = new Container(new BoxLayout(BoxLayout.X_AXIS));

                Container c6 = new Container(new FlowLayout(CENTER, CENTER));
                Container c8 = new Container(new FlowLayout(CENTER, CENTER));
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
                c7.add(etat);
                c7.add(etatv);
                c6.add(edit);
                c6.add(change);
                c8.add(delete);
                vm.add(c1);
                vm.add(c2);
                vm.add(c3);
                vm.add(c4);
                vm.add(c5);
                vm.add(c7);
                vm.add(c6);
                vm.add(c8);

                edit.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (Dialog.show("Edite", "Are you sure?", "Yes", "No")) {
                            v.setNom(nomv.getText());
                            v.setMatricule(mat.getText());
                            v.setCouleur(couleurv.getText());
                            v.setUtilisation(se.stringToUtilisation(utilisationv.getSelectedItem().toString()));
                            v.setKM(Integer.parseInt(kmv.getText()));
                            v.setEtat(se.stringToEtat(etatv.getSelectedItem().toString()));
                            if (se.editVehicule(v)) {

                                Dialog.show("", "Vehicule edited ", "Ok", "cancel");

                                new VehiculeForm().showBack();

                            } else {
                                Dialog.show("", "there was an error", "Ok", "cancel");
                            }
                        }

                    }
                });
                change.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {

                    }
                });
                delete.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (Dialog.show("Delete", "Are you sure?", "Yes", "No")) {
                            if (se.deleteVehicule(v)) {

                                Dialog.show("", "Vehicule deleted ", "Ok", "cancel");

                                new VehiculeForm().showBack();

                            } else {
                                Dialog.show("", "there was an error", "Ok", "cancel");
                            }
                        }

                    }
                });

                vm.show();

            }
        });
    }
}
