/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.table.DefaultTableModel;
import com.codename1.ui.table.Table;
import com.codename1.ui.table.TableModel;
import com.mycompany.enitities.Facture;
import com.mycompany.enitities.Transaction;
import com.mycompany.services.ServicesTransaction;

import java.util.ArrayList;


/**
 *
 * @author wmedi
 */
public class AdminTransactionForm extends Form {
    Form current;
    public AdminTransactionForm(Form previous) {
        current=this;
        
        setTitle("Admin");
        ArrayList<Transaction> allTransaction = ServicesTransaction.getInstance().getAllTransaction();
        
        for (Transaction f : allTransaction) {
            Label sep = new Label ("-------------------------------------------------------------");
            Container C1 = new Container(new BoxLayout(BoxLayout.X_AXIS)); 
            Container C2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            Container c3 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            Label et = new Label("Etat: " + f.getEtatTransaction()+ " ");
            Label disc = new Label("Description: " + f.getDescription()+ " ");
            Label da = new Label("Date: " + f.getDate()+ "15/06/2020 ");
            Label mon = new Label("Monton: " + f.getMonton()+ " ");
            C2.add(et);
            c3.add(mon);
            C2.add(da);
            C2.add(disc);
            C1.add(C2);
            C1.add(c3);
            this.add(C1);
            this.add(sep);
        }

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
    }

    
}
