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
            String sq1 = "SELECT * FROM foto " + "WHERE id='" + fotoId + "' AND feest_has_feestganger_feest_id='"+feestId+"'";
            ResultSet result = dbmanager.doQuery(sq1);
            if (result.next()) {
                foto = new Foto(result.getInt("id"),
                        result.getString("fotonaam"),
                        result.getBoolean("allowed"),
                        result.getInt("feest_has_feestganger_feest_id"),
                        result.getString("feest_has_feestganger_feestganger_gebruikersnaam")
                );
            }
        } catch (SQLException e) {
            System.out.println(DbManager.SQL_EXCEPTION + e.getMessage());
        }

        return foto;
    }

}
