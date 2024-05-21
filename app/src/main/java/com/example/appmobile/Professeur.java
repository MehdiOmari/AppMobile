package com.example.appmobile;

public class Professeur extends Compte {
    private enum Matiere {Arabe, Math};
    private String Etablissement;
    private enum Niveau {Primaire, Cem, Lycee}
    private int nombre_classes;
    private String[] Classe = new String[nombre_classes];


    public void setEtablissement(String etablissement){
        this.Etablissement = etablissement;
    }

    public String getEtablissement() {
        return this.Etablissement;
    }





}
