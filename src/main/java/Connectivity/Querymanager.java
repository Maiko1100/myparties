/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connectivity;

import Models.Foto;
import Models.Party;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Maiko
 */
public class Querymanager {

    private final DbManager dbmanager;

    public Querymanager(DbManager dbmanager) {
        this.dbmanager = dbmanager;

    }

    public void addParty(String name) {
        String sql = "INSERT INTO feest (naam_feest)"
                + "VALUES('" + name + "')";
        dbmanager.insertQuery(sql);
    }

    public Party getParty(int id) {
        Party party = new Party();
        try {
            String sq1 = "SELECT * FROM feest " + "WHERE id='" + id + "'";
            ResultSet result = dbmanager.doQuery(sq1);
            if (result.next()) {
                party = new Party(result.getInt("id"),
                        result.getString("naam_feest"),
                        result.getString("locatieNaam"),
                        result.getString("straat"),
                        result.getString("huisnummer"),
                        result.getString("postcode"),
                        result.getString("stad"),
                        result.getString("datum")
                );
            }
        } catch (SQLException e) {
            System.out.println(DbManager.SQL_EXCEPTION + e.getMessage());
        }

        return party;
    }

    public boolean checkIfPartyExists(int id) {
        Party party = new Party();
        try {
            String sq1 = "SELECT * FROM feest " + "WHERE id='" + id + "'";
            ResultSet result = dbmanager.doQuery(sq1);
            if (result.next()) {
                party = new Party(result.getInt("id"),
                        result.getString("naam_feest"),
                        result.getString("locatieNaam"),
                        result.getString("straat"),
                        result.getString("huisnummer"),
                        result.getString("postcode"),
                        result.getString("stad"),
                        result.getString("datum")
                );
            }
        } catch (SQLException e) {
            System.out.println(DbManager.SQL_EXCEPTION + e.getMessage());
        }
        if (party.getFeestNaam() == null) {
            return false;
        } else {
            return true;
        }
    }
    
    public Foto getFoto(int feestId,int fotoId) {
        Foto foto = new Foto();
        try {
            String sq1 = "SELECT * FROM foto " + "WHERE id='" + fotoId + "' AND feest_id='"+feestId+"'";
            ResultSet result = dbmanager.doQuery(sq1);
            if (result.next()) {
                foto = new Foto(result.getInt("id"),
                        result.getString("fotonaam"),
                        result.getBoolean("allowed"),
                        result.getInt("feest_id"),
                        result.getString("foto"),
                        result.getString("gebruikersnaam")
                );
            }
        } catch (SQLException e) {
            System.out.println(DbManager.SQL_EXCEPTION + e.getMessage());
        }

        return foto;
    }
    public void addFoto(String fotoNaam,boolean allowed,int feestId,String foto,String gebruikersnaam){
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                Date date = new Date();
        
                String sql1 = "Insert INTO feest_has_feestganger(feest_id,feestganger_gebruikersnaam,datum)"
                        + "VALUES('"+feestId+ "','"+gebruikersnaam+"','"+dateFormat.format(date)+"');";
                
                String sql2 = "INSERT INTO foto (fotonaam,allowed,feest_id,foto,gebruikersnaam)"
                + "VALUES('" + fotoNaam + "','" + allowed + "' ,'" +feestId + "','"+foto+ "' ,'" + gebruikersnaam + "');";
                
                dbmanager.insertQuery(sql1);
                dbmanager.insertQuery(sql2);
        
    }
     public List<Foto> getFotoList() {
        List<Foto> fotos = new ArrayList<Foto>();
        try {
            String sql = "SELECT * FROM foto";
            ResultSet result = dbmanager.doQuery(sql);
            while (result.next()) {
                fotos.add(new Foto(result.getInt("id"),
                        result.getString("fotonaam"),
                        result.getBoolean("allowed"),
                        result.getInt("feest_id"),
                        result.getString("foto"),
                        result.getString("gebruikersnaam")));
            }
        } catch (SQLException e) {
            System.out.println(DbManager.SQL_EXCEPTION + e.getMessage());
        }
        return fotos;
     }
}

