/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author Maiko
 */
public class Foto {
    private int id;
    private String fotoNaam;
    private boolean toegestaan;
    private int feestgangerId;
    private String feestgangerGebruikersnaam;

    public Foto() {
    }

    public Foto(int id, String fotoNaam, boolean toegestaan, int feestgangerId, String feestgangerGebruikersnaam) {
        this.id = id;
        this.fotoNaam = fotoNaam;
        this.toegestaan = toegestaan;
        this.feestgangerId = feestgangerId;
        this.feestgangerGebruikersnaam = feestgangerGebruikersnaam;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFotoNaam() {
        return fotoNaam;
    }

    public void setFotoNaam(String fotoNaam) {
        this.fotoNaam = fotoNaam;
    }

    public boolean isToegestaan() {
        return toegestaan;
    }

    public void setToegestaan(boolean toegestaan) {
        this.toegestaan = toegestaan;
    }

    public int getFeestgangerId() {
        return feestgangerId;
    }

    public void setFeestgangerId(int feestgangerId) {
        this.feestgangerId = feestgangerId;
    }

    public String getFeestgangerGebruikersnaam() {
        return feestgangerGebruikersnaam;
    }

    public void setFeestgangerGebruikersnaam(String feestgangerGebruikersnaam) {
        this.feestgangerGebruikersnaam = feestgangerGebruikersnaam;
    }
    
    
    
}
