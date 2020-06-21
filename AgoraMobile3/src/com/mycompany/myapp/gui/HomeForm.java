/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextComponent;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.table.TableLayout;

import com.mycompany.myapp.utils.Statics;

/**
 *
 * @author bhk
 */
public class HomeForm extends Form {

    Form current;

    public HomeForm() {
        current = this;

        setTitle("Login");
        TableLayout tl = new TableLayout(3, 1);
        setLayout(tl);

        TextComponent login = new TextComponent().labelAndHint("Login");
        TextComponent pass = new TextComponent().labelAndHint("Password").constraint(TextArea.PASSWORD);
        add(login);
        add(pass);
        Button btl = new Button("Login");
        btl.addActionListener(new ActionListener() {
            String tasks;

            @Override
            public void actionPerformed(ActionEvent evt) {

                ConnectionRequest req = new ConnectionRequest();
                String url;
                url = Statics.BASE_URL + "login?name=" + login.getText() + "&password=" + pass.getText();
                req.setUrl(url);
                req.setPost(false);
                req.addResponseListener(new ActionListener<NetworkEvent>() {
                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                        tasks = new String(req.getResponseData());
                        req.removeResponseListener(this);
                    }
                });
                NetworkManager.getInstance().addToQueueAndWait(req);
                System.out.println(tasks);
                //Dialog.show("Fail",tasks,new Command("OK"));
                if (!tasks.contains("not valid")) {
                    Statics.username = login.getText();
                    if (tasks.contains("USER")) {
                        ProductForm.currentUser = login.getText();
                        new Welcome1().show();
                    } else {
                        new Welcome().show();
                    }
                } else {
                    Dialog.show("Fail", "Username or Password not valid.", new Command("OK"));
                }
            }
        });

        add(btl);

    }

}
