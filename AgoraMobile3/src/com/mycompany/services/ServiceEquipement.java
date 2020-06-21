/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.enitities.Categorie;
import com.mycompany.enitities.Equipement;
import com.mycompany.enitities.HistoriqueEquipement;
import com.mycompany.enitities.Marque;
import com.mycompany.enitities.Materiels;
import com.mycompany.enitities.Produit;
import com.mycompany.enitities.Vehicule;
import com.mycompany.myapp.utils.Statics;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ASUS
 */
public class ServiceEquipement {

    public ArrayList<Vehicule> vehicule;
    public ArrayList<Materiels> materiels;
    public ArrayList<HistoriqueEquipement> historiqueequipements;
    public static ServiceEquipement instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceEquipement() {
        req = new ConnectionRequest();
    }

    public static ServiceEquipement getInstance() {
        if (instance == null) {
            instance = new ServiceEquipement();
        }
        return instance;
    }

    /**
     * ***********************Vehicule*********************************
     */
    public ArrayList<Vehicule> parseVehicule(String jsonText) {
        try {
            vehicule = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                Vehicule t = new Vehicule();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int) id);
                t.setNom(obj.get("nom").toString());
                t.setMatricule(obj.get("matricule").toString());
                t.setCouleur(obj.get("couleur").toString());
                t.setUtilisation(stringToUtilisation(obj.get("utilisation").toString()));
                float km = Float.parseFloat(obj.get("km").toString());
                t.setKM((int) km);
                t.setEtat(stringToEtat(obj.get("etat").toString()));
                vehicule.add(t);
            }

        } catch (Exception ex) {

        }
        return vehicule;
    }

    public Equipement.etat stringToEtat(String e) {
        if (e.equals("")) {
            return Equipement.etat.NULL;
        }
        if (e.equals("disponnible")) {
            return Equipement.etat.disponnible;
        }
        if (e.equals("en_panne")) {
            return Equipement.etat.en_panne;
        }
        if (e.equals("vendu")) {
            return Equipement.etat.vendu;
        }
        if (e.equals("corbeille")) {
            return Equipement.etat.corbeille;
        }
        return Equipement.etat.en_maintenance;

    }

    public Vehicule.utilisation stringToUtilisation(String u) {
        if (u.equals("")) {
            return Vehicule.utilisation.NULL;
        }
        if (u.equals("vehicule_personnel")) {
            return Vehicule.utilisation.vehicule_personnel;
        }
        return Vehicule.utilisation.vehicule_livraison;
    }

    public ArrayList<Vehicule> getAllVehicule() {
        String url = Statics.ListVehicule_URL;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                vehicule = parseVehicule(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        System.out.println(vehicule);
        return vehicule;

    }

    public String aujourdhui() {
        final Date date = new Date();
        return new SimpleDateFormat("dd-MM-yyyy").format(date);
    }

    public Boolean addVehicule(Vehicule v) {
        String url = Statics.AddVehicule_URL + "?nom=" + v.getNom() + "&matricule=" + v.getMatricule() + "&couleur=" + v.getCouleur() + "&utilisation="
                + v.getUtilisation() + "&km=" + v.getKM() + "&etat=" + v.getEtat() + "&prix=" + v.getPrix();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);

        return resultOK;
    }

    public Boolean editVehicule(Vehicule v) {
        String url = Statics.EditVehicule_URL + "?id=" + v.getId() + "&nom=" + v.getNom() + "&matricule=" + v.getMatricule() + "&couleur=" + v.getCouleur() + "&utilisation="
                + v.getUtilisation() + "&km=" + v.getKM() + "&etat=" + v.getEtat();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public Boolean deleteVehicule(Vehicule v) {
        String url = Statics.DeleteVehicule_URL + "?id=" + v.getId() + "&nom=" + v.getNom() + "&matricule=" + v.getMatricule() + "&couleur=" + v.getCouleur() + "&utilisation="
                + v.getUtilisation() + "&km=" + v.getKM() + "&etat=" + v.getEtat();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    /**
     * ***********************Materiels*********************************
     */
    public ArrayList<Materiels> parseMateriels(String jsonText) {
        try {
            materiels = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                Materiels t = new Materiels();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int) id);
                t.setNom(obj.get("nom").toString());
                float qte = Float.parseFloat(obj.get("qte").toString());
                t.setQte((int) qte);
                t.setEtat(stringToEtat(obj.get("etat").toString()));
                materiels.add(t);
            }

        } catch (Exception ex) {

        }
        return materiels;
    }

    public ArrayList<Materiels> getAllMateriels() {
        String url = Statics.ListMateriels_URL;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                materiels = parseMateriels(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);

        return materiels;

    }

    public Boolean addMateriels(Materiels m) {
        float prix_total = m.getPrix() * m.getQte();
        String url = Statics.AddMateriels_URL + "?nom=" + m.getNom() + "&qte=" + m.getQte() + "&etat=" + m.getEtat() + "&prix=" + m.getPrix() + "&prix_total=" + prix_total;
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
       
        return resultOK;
    }

    public Boolean editeMateriels(Materiels m) {
        String url = Statics.EditMateriels_URL + "?id=" + m.getId() + "&nom=" + m.getNom() + "&qte=" + m.getQte() + "&etat=" + m.getEtat();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public Boolean deleteMateriels(Materiels m) {
        String url = Statics.DeleteMateriels_URL + "?id=" + m.getId() + "&nom=" + m.getNom() + "&qte=" + m.getQte() + "&etat=" + m.getEtat();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    /**
     * ***********************Historique*********************************
     */
    public ArrayList<HistoriqueEquipement> parseHistoriqueEquipements(String jsonText) {
        try {
            historiqueequipements = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                HistoriqueEquipement t = new HistoriqueEquipement();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int) id);
                t.setDate(obj.get("date").toString());
                t.setOperation(stringToOperation(obj.get("operation").toString()));
                t.setEquipement(stringToEquipement(obj.get("equipement").toString()));
                float qte = Float.parseFloat(obj.get("qte").toString());
                t.setQte((int) qte);
                float prix = Float.parseFloat(obj.get("prix").toString());
                t.setPrix(prix);
                float idm = Float.parseFloat(obj.get("idM").toString());
                t.setIdm((int) idm);
                float idv = Float.parseFloat(obj.get("idV").toString());
                t.setIdv((int) idv);
                float idf = Float.parseFloat(obj.get("idF").toString());
                t.setIdf((int) idf);

                historiqueequipements.add(t);
            }

        } catch (Exception ex) {

        }
        return historiqueequipements;
    }

    public ArrayList<HistoriqueEquipement> getAllHistoriqueEquipements() {
        String url = Statics.ListHistoriqueEquipement_URL;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                historiqueequipements = parseHistoriqueEquipements(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        System.out.println(historiqueequipements);
        return historiqueequipements;

    }

    public HistoriqueEquipement.operation stringToOperation(String o) {
        if (o.equals("")) {
            return HistoriqueEquipement.operation.achat;
        }
        if (o.equals("achat")) {
            return HistoriqueEquipement.operation.achat;
        }
        if (o.equals("vente")) {
            return HistoriqueEquipement.operation.vente;
        }
        if (o.equals("corbeille")) {
            return HistoriqueEquipement.operation.corbeille;
        }
        if (o.equals("maintenance")) {
            return HistoriqueEquipement.operation.maintenance;
        }
        if (o.equals("panne")) {
            return HistoriqueEquipement.operation.panne;
        }
        return HistoriqueEquipement.operation.disponnible;
    }

    public HistoriqueEquipement.equipement stringToEquipement(String e) {
        if (e.equals("vehicule")) {
            return HistoriqueEquipement.equipement.vehicule;
        }
        return HistoriqueEquipement.equipement.materiels;
    }

    public Boolean addHistoriqueEquipement(HistoriqueEquipement h) {
        String url = Statics.AddHistoriqueEquipement__URL + "?date=" + h.getDate() + "&operation=" + h.getOperation() + "&equipement=" + h.getEquipement() + "&qte="
                + h.getQte() + "&prix=" + h.getPrix() + "&idm=" + h.getIdm() + "&idv=" + h.getIdv() + "&idf=" + h.getIdf();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = new String(req.getResponseData()).equals("OK");
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    /**
     * ********************************************
     */

    public HistoriqueEquipement.operation etatToOperation(Equipement.etat e) {
        if (e.equals("")) {
            return HistoriqueEquipement.operation.NULL;
        }
        if (e.equals(Equipement.etat.vendu)) {
            return HistoriqueEquipement.operation.vente;
        }
        if (e.equals(Equipement.etat.corbeille)) {
            return HistoriqueEquipement.operation.corbeille;
        }
        if (e.equals(Equipement.etat.en_panne)) {
            return HistoriqueEquipement.operation.panne;
        }
        if (e.equals(Equipement.etat.en_maintenance)) {
            return HistoriqueEquipement.operation.maintenance;
        }

        return HistoriqueEquipement.operation.disponnible;
    }

}
