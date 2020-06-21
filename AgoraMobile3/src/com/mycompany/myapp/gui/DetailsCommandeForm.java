/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */

package com.mycompany.myapp.gui;

import com.mycompany.enitities.Product;
import com.mycompany.services.CarteFideliteService;
import com.mycompany.services.DetailCommandeService;
import com.mycompany.services.PanierService;
import com.mycompany.services.ServiceProd;
import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.l10n.ParseException;
import com.codename1.payment.Purchase;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.enitities.Produit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The newsfeed form
 *
 * @author Shai Almog
 */
public class DetailsCommandeForm extends BaseForm {
    public static int idCommande;
    public DetailsCommandeForm(Resources res) throws ParseException {
        super("Détails de la commande", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Détails de la commande");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
        tb.addSearchCommand(e -> {});
        

        
        ButtonGroup bg = new ButtonGroup();
        int size = Display.getInstance().convertToPixels(1);
        Image unselectedWalkthru = Image.createImage(size, size, 0);
        Graphics g = unselectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAlpha(100);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        Image selectedWalkthru = Image.createImage(size, size, 0);
        g = selectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
//        RadioButton[] rbs = new RadioButton[swipe.getTabCount()];
        FlowLayout flow = new FlowLayout(CENTER);
        flow.setValign(BOTTOM);
        Container radioContainer = new Container(flow);
                ButtonGroup barGroup = new ButtonGroup();
        RadioButton myFavorite = RadioButton.createToggle("", barGroup);
        myFavorite.setUIID("SelectBar");
        Label top = new Label(" ");
        top.getStyle().setMargin(200, 0, 0, 0);
        
        add(LayeredLayout.encloseIn(                
                GridLayout.encloseIn(1, myFavorite),
                FlowLayout.encloseBottom(top)
        ));
        
        
        
        
        ////////////////////////////////////////////
        
        
        
           DetailCommandeService pss = new DetailCommandeService();
           Map<Produit,Integer> listProducts = new HashMap<Produit, Integer>();
           listProducts = pss.getDetailsCommandes(idCommande);
            for (Map.Entry<Produit, Integer> entry : listProducts.entrySet()) {
            //Label l = new Label("'"+entry.getKey().getNom()+"'  Qte: '"+entry.getValue().toString()+"' P : '"+entry.getKey().getPrix()+"' Tot : "+(entry.getKey().getPrix()*entry.getValue()));
            //hi2.add(l);
            addButton(entry.getKey(),res,entry.getValue());
        }
            
            
    }
    

    

   private void addButton(Produit p, Resources res,int qte) {
       
       
       EncodedImage enc = EncodedImage.createFromImage(res.getImage("news-item-4.jpg"), false);
                String urlim = "http://127.0.0.1:8080/Agora22/web/images/"+p.getImage();
                URLImage urlimage = URLImage.createToStorage(enc, p.getImage(), urlim);
                ImageViewer iv = new ImageViewer(urlimage);
                Image img = iv.getImage();
       
       
       int height = Display.getInstance().convertToPixels(11.5f);
       int width = Display.getInstance().convertToPixels(14f);
       
       Button image = new Button(img.fill(width, height));
       image.setUIID("Label");
       Container cnt = BorderLayout.west(image);
       cnt.setLeadComponent(image);
       TextArea ta = new TextArea(p.getNomProduit());
       ta.setUIID("NewsTopLine");
       ta.setEditable(false);

       Label likes = new Label(p.getPrixVente() + "   ", "NewsBottomLine");
       likes.setTextPosition(RIGHT);
       
       Label comments = new Label(qte + " Articles", "NewsBottomLine");
       FontImage.setMaterialIcon(likes, FontImage.MATERIAL_ATTACH_MONEY);
       
       
       cnt.add(BorderLayout.CENTER, 
               BoxLayout.encloseY(
                       ta,
                       BoxLayout.encloseX(likes,comments)
               ));
       add(cnt);
       
       
       
       
   }
    
}
