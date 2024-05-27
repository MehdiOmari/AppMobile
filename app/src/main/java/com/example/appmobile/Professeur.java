package com.example.appmobile;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;


public class Professeur extends Compte {

    private String Matiere;
    private String Etablissement;
    private String profID;
    private String DatedeNaissance;
    private String LieudeNaissance;
    public int nombre_classe;

    private String[] Classe = new String[20];
    HashMap<String, String> classe_data = new HashMap<>();


    public void setProfID(String profID){
        this.profID = profID;
    }
    public String getProfID(){
        return this.profID;
    }
    public void setEtablissement(String etablissement){
        this.Etablissement = etablissement;
    }

    public String getEtablissement() {
        return this.Etablissement;
    }

    public void setMatiere(String Matiere){
        this.Matiere = Matiere;
    }
    public String getMatiere(){
        return this.Matiere;
    }
    public void setDatedeNaissance(String datedeNaissance){
        this.DatedeNaissance = datedeNaissance;
    }
    public String getDatedeNaissance(){
        return this.DatedeNaissance;
    }

    public void setLieudeNaissance(String lieudeNaissance) {
        LieudeNaissance = lieudeNaissance;
    }
    public String getLieudeNaissance(){
        return this.LieudeNaissance;
    }
    private void setnombre_classe(){
        switch (this.Matiere){
            case "Arabe" : nombre_classe = 1;
                break;
            case "Francais" :
            case "Anglais" : nombre_classe = 3;
                break;
            case "Sport" :
            case "ArtEtMusique" : nombre_classe = 10;
                break;
        }
    }
}
