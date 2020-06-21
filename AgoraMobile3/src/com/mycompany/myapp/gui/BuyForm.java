/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.table.TableLayout;
import com.mycompany.enitities.Produit;
import com.mycompany.enitities.Rating;
import com.mycompany.services.ServiceProd;
import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public class BuyForm extends Form {

    ImageViewer img = null;
    ImageViewer img1 = null;
    int i = 0;
    ServiceProd sp = ServiceProd.getInstance();
    ArrayList<Produit> prods;
    Produit p = new Produit();
    TableLayout tb = new TableLayout(1, 2);
    Container C = new Container(new BoxLayout(BoxLayout.Y_AXIS));
    Container C1 = new Container(tb);
    Container C2 = new Container(new BoxLayout(BoxLayout.X_AXIS));

    public BuyForm(String prod, String currentUser) {
        this.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        prods = sp.findProd(prod);

        for (Produit p1 : prods) {
            p = p1;
        }
        this.setTitle(p.getNomProduit());
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
                new ProductForm().show();
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

        this.getToolbar().addCommandToLeftBar("Back", null, (ActionListener) (ActionEvent evt) -> {
            new ProductForm().show();
        });
        try {
            img = new ImageViewer(Image.createImage("/agora.png"));
        } catch (Exception e) {
        };
        add(img);
        Label l = new Label("Product : " + p.getNomProduit());
        Label l2 = new Label("Price : " + p.getPrixVente());
        Label l3 = new Label("Reference : " + p.getRefProduit());
        Label l4 = new Label("");
        Slider s = createStarRankSlider();
        C.add(l);
        C.add(l2);
        C.add(l3);

        Button plus = new Button("+");
        Button min = new Button("-");

        Label l0 = new Label("quantity : " + i);
        plus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                i++;
                l0.setText("quantity : " + i);
            }
        });
        min.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (i != 0) {
                    i--;
                    l0.setText("quantity : " + i);
                }
            }
        });
        C2.add(l0);
        C2.add(min);
        C2.add(plus);
        C.add(C2);
        C1.add(C);
        Button B = new Button("Ajouter au panier");

        try {
            img1 = new ImageViewer(Image.createImage("/" + p.getImage()));
        } catch (Exception e) {
        };
        C1.add(tb.createConstraint().heightPercentage(100).widthPercentage(50), img1);
        add(C1);
        add(FlowLayout.encloseCenter(B));
        add(FlowLayout.encloseCenter(s));
        TextArea Ta = new TextField("", "give us your feedback", 100, CENTER);
        add(FlowLayout.encloseBottom(Ta));
        Button bt = new Button("send feedback");
        add(FlowLayout.encloseRight(bt));
        bt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Rating r=new Rating(Ta.getText(),s.getProgress());
                sp.addRating(r);
                Label tn = new Label("Thanks for your important feedback");
                Label tn1 = new Label("we'll try to make this plateform better for you!!");
                add(FlowLayout.encloseCenterBottom(tn));
                add(FlowLayout.encloseCenterBottom(tn1));
                refreshTheme();
            }
        });
    }

    private void initStarRankStyle(Style s, Image star) {
        s.setBackgroundType(Style.BACKGROUND_IMAGE_TILE_BOTH);
        s.setBorder(Border.createEmpty());
        s.setBgImage(star);
        s.setBgTransparency(0);
    }

    private Slider createStarRankSlider() {
        Slider starRank = new Slider();
        starRank.setEditable(true);
        starRank.setMinValue(0);
        starRank.setMaxValue(5);

        Style s = new Style(0xffff33, 0, Font.getDefaultFont(), (byte) 0);
        Image fullStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
        s.setOpacity(100);
        s.setFgColor(0);
        Image emptyStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
        initStarRankStyle(starRank.getSliderEmptySelectedStyle(), emptyStar);
        initStarRankStyle(starRank.getSliderEmptyUnselectedStyle(), emptyStar);
        initStarRankStyle(starRank.getSliderFullSelectedStyle(), fullStar);
        initStarRankStyle(starRank.getSliderFullUnselectedStyle(), fullStar);
        starRank.setPreferredSize(new Dimension(fullStar.getWidth() * 5, fullStar.getHeight()));
        return starRank;
    }

}
