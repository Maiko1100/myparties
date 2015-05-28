package com.team12.wfc.mypartyhub.Models;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Maiko
 */
public class Foto {
    private int id;
    private String fotoNaam;
    private boolean toegestaan;
    private int feestId;
    private String foto;
    private String gebruikersnaam;

    public Foto() {
    }

    public Foto(int id, String fotoNaam, boolean toegestaan, int feestId, String foto, String gebruikersnaam) {
        this.id = id;
        this.fotoNaam = fotoNaam;
        this.toegestaan = toegestaan;
        this.feestId = feestId;
        this.foto = foto;
        this.gebruikersnaam = gebruikersnaam;
    }

    public String getGebruikersnaam() {
        return gebruikersnaam;
    }

    public void setGebruikersnaam(String gebruikersnaam) {
        this.gebruikersnaam = gebruikersnaam;
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

    public int getFeestId() {
        return feestId;
    }

    public void setFeestId(int feestId) {
        this.feestId = feestId;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }


    @Override
    public String toString() {
        return "Foto{" +
                "id=" + id +
                ", fotoNaam='" + fotoNaam + '\'' +
                ", toegestaan=" + toegestaan +
                ", feestId=" + feestId +
                ", foto='" + foto + '\'' +
                ", gebruikersnaam='" + gebruikersnaam + '\'' +
                '}';
    }
}
