/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
/**
 *
 * @author Maiko
 */
@XmlRootElement(name = "party")
public class Party {
        private int id;
	private String feestNaam;
        private String locatieNaam;
        private String straat;
        private String huisnummer;
        private String postcode;
        private String stad;
        private String datum;

    public Party() {
    }

    public Party(int id, String feestNaam, String locatienaam, String straat, String huisnummer, String postcode, String stad, String datum) {
        this.id = id;
        this.feestNaam = feestNaam;
        this.locatieNaam = locatienaam;
        this.straat = straat;
        this.huisnummer = huisnummer;
        this.postcode = postcode;
        this.stad = stad;
        this.datum = datum;
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

    public String getLocatienaam() {
        return locatieNaam;
    }

    public void setLocatienaam(String locatienaam) {
        this.locatieNaam = locatienaam;
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
        
        
	
    }