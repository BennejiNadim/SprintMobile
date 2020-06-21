/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.enitities.Categorie;
import com.mycompany.enitities.Marque;
import com.mycompany.enitities.Product;
import com.mycompany.enitities.Produit;
import com.mycompany.myapp.utils.Statics;
import com.mycompany.services.PanierService;
import com.mycompany.services.ServiceProd;
import java.util.ArrayList;


/**
 *
 * @author ASUS
 */
public class ProductForm_1 extends BaseForm {
    
    ServiceProd sp = ServiceProd.getInstance();
    ArrayList<Produit> prods = sp.getAllProds();
    ArrayList<Categorie> catgs = sp.getAllCatgs();
    ArrayList<Marque> marqs = sp.getAllMarqs();
    ImageViewer img = null;
    Container c;
    ComboBox<String> categories = new ComboBox<String>();
    ComboBox<String> marques = new ComboBox<String>();
    public static String inter = "";
    public static String sh;
    public static String category;
    public static String marque;
    public static String currentUser="visitor";

    public ProductForm_1() {
        getToolbar().addCommandToLeftBar("back",null,new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
               new Welcome().show();
            }
        });
        this.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        try {
            this.setBgImage(Image.createImage("/bg.png"));
        } catch (Exception e) {
        };
        this.getToolbar().addCommandToLeftSideMenu("Home", null, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {

            }
        });
        
        this.getToolbar().addCommandToLeftSideMenu("List products", null, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new ProductForm_1().show();
            }
        });
        
        this.getToolbar().addCommandToLeftSideMenu("Panier", null, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new PanierForm(UIManager.initFirstTheme("/theme_2")).show();
            }
        });
        this.getToolbar().addCommandToLeftSideMenu("logout", null, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new HomeForm().show();
            }
        });
        if (!currentUser.equals("visitor")) {
            this.getToolbar().addCommandToRightBar(currentUser, null, new ActionListener<ActionEvent>() {
                @Override
                public void actionPerformed(ActionEvent evt) {

                }
            });
        }
        
        try {
            img = new ImageViewer(Image.createImage("/agora.png"));
        } catch (Exception e) {
        };
        add(img);
        categories.addItem("catgories");
        for (Categorie cat : catgs) {

            categories.addItem(cat.getLibCatg());
        }
        categories.addItem("all");
        marques.addItem("marques");
        for (Marque marq : marqs) {

            marques.addItem(marq.getNomMarque());
        }
        marques.addItem("all");
        fillContainer();
        add(c);
        this.getToolbar().addSearchCommand(e -> {
            sh = (String) e.getSource();
            inter = "search";
            removeAll();
            c.removeAll();
            prods = sp.findProd(sh);
            fillContainer();
            add(c);
            this.refreshTheme();
            System.out.println(inter + " " + sh);
        });
        categories.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                category = categories.getSelectedItem();
                if (category.equals("all")) {
                    prods = sp.getAllProds();
                } else {
                    prods = sp.getProdByCatg(category);
                };
                inter = "categorie";
                removeAll();
                c.removeAll();

                fillContainer();
                add(c);
                refreshTheme();
                System.out.println(prods.toString());
            }

        });
        marques.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                marque = marques.getSelectedItem();
                inter = "marque";
                removeAll();
                c.removeAll();
                if (marque.equals("all")) {
                    prods = sp.getAllProds();
                } else {
                    prods = sp.getProdByMarq(marque);
                }
                fillContainer();
                add(c);
                refreshTheme();
                System.out.println(prods.toString());
            }
        });

    }

    public void fillContainer() {

        Label filter = new Label("filter By");
        int n = prods.size();
        TableLayout tb = new TableLayout(n + 1, 3);
        c = new Container(BoxLayout.y());
        c.add(filter);
        c.add(categories);
        c.add(marques);
        Resources res = UIManager.initFirstTheme("/theme");
        for (Produit p : prods) {
            Container x = new Container(new TableLayout(1, 3));
            Button b = new Button();
            b.addActionListener(l->{
                Produit pp = new Produit();
                pp.setRefProduit(p.getRefProduit());
                pp.setImage(p.getImage());
                pp.setPrixVente(p.getPrixVente());
                
                PanierService pss = new PanierService();
               pss.ajouterProduit(pp,1);
               
               //notification
               ToastBar.showMessage( p.getNomProduit()+" a été ajouté au panier", FontImage.MATERIAL_ADD_SHOPPING_CART);

            });
            
            Label nomProd = new Label("" + p.getNomProduit() + "");
            Label PrixProd = new Label("" + p.getPrixVente() + "");
            ImageViewer img = null;
            try {
                EncodedImage enc = EncodedImage.createFromImage(res.getImage("add.png"), false);
                String urlim = Statics.images+p.getImage();
                URLImage urlimage = URLImage.createToStorage(enc, p.getImage(), urlim);
                img = new ImageViewer(urlimage);
            } catch (Exception e) {
            };
            x.add(nomProd);
            x.add(PrixProd);
            x.add(img);
            x.setLeadComponent(b);
            c.add(x);
            nomProd.addPointerPressedListener(new ActionListener<ActionEvent>() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    new BuyForm(nomProd.getText(),currentUser).show();
                }
            });
        }
    }

}
