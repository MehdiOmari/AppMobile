package com.example.appmobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class mainadmin2 extends AppCompatActivity {

    private CardView students;
    private CardView profile;
    private String User_ID;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_admin2);

        students = findViewById(R.id.contributeCard1);
        profile = findViewById(R.id.Profile);
        User_ID = getIntent().getStringExtra("Current_ID");

        students.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mainadmin2.this, studentadmin.class);
                i.putExtra("Current_ID",User_ID);
                startActivity(i);
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mainadmin2.this, Profile.class);
                i.putExtra("Current_ID",User_ID);
                startActivity(i);
            }
        });
    }
}
