package com.example.appmobile;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.HashMap;

public class TeacherAdmin extends AppCompatActivity {

    AppCompatButton button_arabe;
    AppCompatButton button_francais;
    AppCompatButton button_anglais;
    AppCompatButton button_sport;
    AppCompatButton button_artetmusique;
    HashMap<String, TableauProf> fragment_prof;
    private boolean color_btn_matiere[];
    private Bundle bundle;
    private String user_ID;
    private ImageButton back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_teacher_admin);

        button_arabe = findViewById(R.id.buttonarabe);
        button_francais = findViewById(R.id.buttonfrancais);
        button_anglais = findViewById(R.id.buttonanglais);
        button_sport = findViewById(R.id.buttonsport);
        button_artetmusique = findViewById(R.id.buttonart);
        user_ID = getIntent().getStringExtra("user_ID");
        back = findViewById(R.id.back);

        fragment_prof = new HashMap<>();
        color_btn_matiere = new boolean[5];
        bundle = new Bundle();
        bundle.putString("user_ID",user_ID);

        color_btn_matiere[0] = true;
        for (int i=1; i<5; i++){
            color_btn_matiere[i] = false;
        }

        if (fragment_prof.get("Arabe") == null) {
            TableauProf frg_prof = new TableauProf();
            frg_prof.setMatiere("Arabe");
            frg_prof.setArguments(bundle);
            fragment_prof.put("Arabe", frg_prof);
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.layout_container, fragment_prof.get("Arabe")).addToBackStack(null).commit();

        button_arabe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(color_btn_matiere[0])  Toast.makeText(TeacherAdmin.this, "you are in the liste clicked", Toast.LENGTH_SHORT).show();
                else {
                    button_arabe.setBackground(AppCompatResources.getDrawable(TeacherAdmin.this,R.drawable.btnstyle));
                    for(int i = 1;i < 5; i++){
                        if(color_btn_matiere[i]) {
                            color_btn(i);
                            color_btn_matiere[i] = false;
                        }
                    }
                    color_btn_matiere[0] = true;
                    getSupportFragmentManager().beginTransaction().replace(R.id.layout_container, fragment_prof.get("Arabe")).commit();
                    Toast.makeText(TeacherAdmin.this,fragment_prof.get("Arabe").getMatiere(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        button_francais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(color_btn_matiere[1])  Toast.makeText(TeacherAdmin.this, "you are in the liste clicked", Toast.LENGTH_SHORT).show();
                else {
                    button_francais.setBackground(AppCompatResources.getDrawable(TeacherAdmin.this,R.drawable.btnstyle));
                    for(int i = 0;i < 5; i++){
                        if(color_btn_matiere[i]) {
                            color_btn(i);
                            color_btn_matiere[i] = false;
                        }
                    }
                    color_btn_matiere[1] = true;
                    if(fragment_prof.get("Francais") == null) add_fragment_prof("Francais");
                    getSupportFragmentManager().beginTransaction().replace(R.id.layout_container, fragment_prof.get("Francais")).commit();
                }
            }
        });

        button_anglais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(color_btn_matiere[2])  Toast.makeText(TeacherAdmin.this, "you are in the liste clicked", Toast.LENGTH_SHORT).show();
                else {
                    button_anglais.setBackground(AppCompatResources.getDrawable(TeacherAdmin.this,R.drawable.btnstyle));
                    for(int i = 0;i < 5; i++){
                        if(color_btn_matiere[i]) {
                            color_btn(i);
                            color_btn_matiere[i] = false;
                        }
                    }
                    color_btn_matiere[2] = true;
                    if(fragment_prof.get("Anglais") == null) add_fragment_prof("Anglais");
                    getSupportFragmentManager().beginTransaction().replace(R.id.layout_container, fragment_prof.get("Anglais")).commit();
                    Toast.makeText(TeacherAdmin.this,fragment_prof.get("Anglais").getMatiere(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        button_sport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(color_btn_matiere[3])  Toast.makeText(TeacherAdmin.this, "you are in the liste clicked", Toast.LENGTH_SHORT).show();
                else {
                    button_sport.setBackground(AppCompatResources.getDrawable(TeacherAdmin.this,R.drawable.btnstyle));
                    for(int i = 0;i < 5; i++){
                        if(color_btn_matiere[i]) {
                            color_btn(i);
                            color_btn_matiere[i] = false;
                        }
                    }
                    color_btn_matiere[3] = true;
                    if(fragment_prof.get("Sport") == null) add_fragment_prof("Sport");
                    getSupportFragmentManager().beginTransaction().replace(R.id.layout_container, fragment_prof.get("Sport")).commit();
                    Toast.makeText(TeacherAdmin.this,fragment_prof.get("Sport").getMatiere(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        button_artetmusique.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(color_btn_matiere[4])  Toast.makeText(TeacherAdmin.this, "you are in the liste clicked", Toast.LENGTH_SHORT).show();
                else {
                    button_artetmusique.setBackground(AppCompatResources.getDrawable(TeacherAdmin.this,R.drawable.btnstyle));
                    for(int i = 0;i < 4; i++){
                        if(color_btn_matiere[i]) {
                            color_btn(i);
                            color_btn_matiere[i] = false;
                        }
                    }
                    color_btn_matiere[4] = true;
                    if(fragment_prof.get("ArtEtMusique") == null) add_fragment_prof("ArtEtMusique");
                    getSupportFragmentManager().beginTransaction().replace(R.id.layout_container, fragment_prof.get("ArtEtMusique")).commit();
                    Toast.makeText(TeacherAdmin.this,fragment_prof.get("ArtEtMusique").getMatiere(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        back.setOnClickListener((v -> {finish();}));

    }
    private void color_btn(int i){
        switch (i){
            case 0 : button_arabe.setBackground(AppCompatResources.getDrawable(TeacherAdmin.this,R.drawable.btnstule));
                     break;
            case 1 : button_francais.setBackground(AppCompatResources.getDrawable(TeacherAdmin.this,R.drawable.btnstule));
                     break;
            case 2 : button_anglais.setBackground(AppCompatResources.getDrawable(TeacherAdmin.this,R.drawable.btnstule));
                     break;
            case 3 : button_sport.setBackground(AppCompatResources.getDrawable(TeacherAdmin.this,R.drawable.btnstule));
                     break;
            case 4 : button_artetmusique.setBackground(AppCompatResources.getDrawable(TeacherAdmin.this,R.drawable.btnstule));
                     break;
        }
    }
    public void add_fragment_prof(String matiere){
        TableauProf tb_prof = new TableauProf();
        tb_prof.setMatiere(matiere);
        tb_prof.setArguments(bundle);
        fragment_prof.put(matiere,tb_prof);
    }
}