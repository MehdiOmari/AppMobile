package com.example.appmobile;

import java.util.ArrayList;

public class Classes {
    private String id_level_class;
    private String id_Classe;
    private ArrayList <Eleve> Liste_Eleves = new ArrayList<>();

    public Classes(){}
    public Classes(String id){
        this.id_Classe = id;
    }

    public void setId(String id) {
        this.id_Classe = id;
    }

    public String getId() {
        return this.id_Classe;
    }
}
