/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
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
import com.mycompany.myapp.utils.Statics;
import com.mycompany.services.ServicesFacture;

import java.util.ArrayList;

/**
 *
 * @author wmedi
 */
public class AdminFactureForm extends Form {

    Form current;
    ImageViewer img1 = null;

    public AdminFactureForm(Form previous) {
        current = this;

        setTitle("Liste Facture");
        //SpanLabel sp = new SpanLabel();
        ArrayList<Facture> allFactures = ServicesFacture.getInstance().getAllFactures();
        //sp.setText(allFactures.toString());
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
            Label sep = new Label("-------------------------------------------------------------");
            Container C1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
            Container C2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            Container c3 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            Label fp = new Label("prix: " + f.getMontant() + " ");
            Label cl = new Label("Client: " + f.getClientLogin() + " ");
            Label el = new Label("Employe: " + f.getEmployeLogin() + " ");
            Label si = new Label("Fournissuer: " + f.getSupplierId() + " ");
            Label tp = new Label("Type: " + f.getTypeFacture() + " ");
            Label dp = new Label("Date: " + f.getDateFacturation() + "15/06/2020 ");
            Label et = new Label("Etat: " + f.getEtatFacture() + " ");

            Button pdfb= new Button("pdf");
            String url = "http://localhost/agora2/web/app_dev.php/Comptabilite/listfacture/pdf/" + f.getId();
            pdfb.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent evt) {
                    ConnectionRequest req;
                    req = new ConnectionRequest();
                    req.setUrl(url);
                    req.setPost(false);
                    req.addResponseListener(new ActionListener<NetworkEvent>() {
                        @Override
                        public void actionPerformed(NetworkEvent evt) {

                            req.getDestinationFile();
                        }
                    });
                    NetworkManager.getInstance().addToQueueAndWait(req);
                }
            });

            C2.add(tp);
            c3.add(fp);
            if (f.getTypeFacture().equals("vente_produit") || f.getTypeFacture().equals("vente_materiel")) {
                c3.add(cl);
            } else if (f.getTypeFacture().equals("achat_produit") || f.getTypeFacture().equals("achat_materiel")) {
                c3.add(si);
            } else if (f.getTypeFacture().equals("salaire")) {
                c3.add(el);
            }
            C2.add(dp);
            C2.add(et);
            C2.add(pdfb);
            C1.add(C2);
            C1.add(c3);
            this.add(C1);
            this.add(sep);
        }

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

}
