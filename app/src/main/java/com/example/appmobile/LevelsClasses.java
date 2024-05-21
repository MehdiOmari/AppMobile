package com.example.appmobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class LevelsClasses extends AppCompatActivity {

    private CardView c1,c2,c3,c4;
    public static boolean check_1M = false;
    public static boolean check_2M = false;
    public static boolean check_3M = false;
    public static boolean check_4M = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.levels_classes);

        c1 = findViewById(R.id.class1M);
        c2 = findViewById(R.id.class2M);
        c3 = findViewById(R.id.class3M);
        c4 = findViewById(R.id.class4M);

        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check_1M = true;
                Intent i = new Intent(LevelsClasses.this, ListeClasse.class);
                startActivity(i);
            }
        });

        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check_2M = true;
                Intent i = new Intent(LevelsClasses.this, ListeClasse.class);
                startActivity(i);
            }
        });
        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check_3M = true;
                Intent i = new Intent(LevelsClasses.this, ListeClasse.class);
                startActivity(i);
            }
        });
        c4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check_4M = true;
                Intent i = new Intent(LevelsClasses.this, ListeClasse.class);
                startActivity(i);
            }
        });
    }
}