package com.example.appmobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class MainProf extends AppCompatActivity {

    private CardView profile;
    private CardView liste_eleve;
    private String user_ID;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.main_prof);

        liste_eleve = findViewById(R.id.contributeCard);
        user_ID = getIntent().getStringExtra("user_ID");

        /*profile = findViewById(R.id.Profile);

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainProf.this,Profile.class);
                startActivity(i);
            }
        });*/
        liste_eleve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainProf.this, Liste_Eleve_Prof.class);
                i.putExtra("user_ID", user_ID);
                startActivity(i);
            }
        });
    }
}
