package com.example.appmobile;

import java.util.ArrayList;

public class Classe {
    private String id_Classe;
    private ArrayList <String> Liste_Eleves = new ArrayList<>();

    public Classe (){}
    public Classe (String id){
        this.id_Classe = id;
    }

    public void setId(String id) {
        this.id_Classe = id;
    }

    public String getId() {
        return this.id_Classe;
    }
}
