package com.example.appmobile;

import android.app.Application;

public class myobjects extends Application {

    private Administration admin;
    private Professeur prof = new Professeur();
    private Parent pr = new Parent();

    public void onCreate(){
        super.onCreate();
        admin = new Administration();
    }

    public Administration get_myshared_admin(){
        return admin;
    }
}
