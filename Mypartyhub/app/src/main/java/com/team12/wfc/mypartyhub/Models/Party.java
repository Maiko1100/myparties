/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team12.wfc.mypartyhub.Models;/*
/**
 *
 * @author Maiko
 */

public class Party {
        private int id;
	private String feestNaam;
        private String locatieNaam;
        private String straat;
        private String huisnummer;
        private String postcode;
        private String stad;
        private String datum;
        private String latitude;
        private String longtitude;

    public Party() {
    }

    public Party(int id, String feestNaam, String locatieNaam, String straat, String huisnummer, String postcode, String stad, String datum, String latitude, String longtitude) {
        this.id = id;
        this.feestNaam = feestNaam;
        this.locatieNaam = locatieNaam;
        this.straat = straat;
        this.huisnummer = huisnummer;
        this.postcode = postcode;
        this.stad = stad;
        this.datum = datum;
        this.latitude = latitude;
        this.longtitude = longtitude;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFeestNaam() {
        return feestNaam;
    }

    public void setFeestNaam(String feestNaam) {
        this.feestNaam = feestNaam;
    }

    public String getLocatieNaam() {
        return locatieNaam;
    }

    public void setLocatieNaam(String locatieNaam) {
        this.locatieNaam = locatieNaam;
    }

    public String getStraat() {
        return straat;
    }

    public void setStraat(String straat) {
        this.straat = straat;
    }

    public String getHuisnummer() {
        return huisnummer;
    }

    public void setHuisnummer(String huisnummer) {
        this.huisnummer = huisnummer;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getStad() {
        return stad;
    }

    public void setStad(String stad) {
        this.stad = stad;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(String longtitude) {
        this.longtitude = longtitude;
    }
        
        
        
}