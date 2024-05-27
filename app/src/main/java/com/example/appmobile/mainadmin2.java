package com.example.appmobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.appmobile.PFE.studentadmin;

public class mainadmin2 extends AppCompatActivity {

    private CardView students;
    //private CardView profile;
    private CardView teachers;
    private CardView classe_prof;
    private String User_ID;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_admin2);

        students = findViewById(R.id.contributeCard1);
        //profile = findViewById(R.id.Profile);
        teachers = findViewById(R.id.contributeCard);
        classe_prof = findViewById(R.id.practiceCard);
        User_ID = getIntent().getStringExtra("Current_ID");

        students.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mainadmin2.this, studentadmin.class);
                i.putExtra("Current_ID",User_ID);
                startActivity(i);
            }
        });

        /*profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mainadmin2.this, Profile.class);
                i.putExtra("Current_ID",User_ID);
                startActivity(i);
            }
        });*/

        teachers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mainadmin2.this, TeacherAdmin.class);
                i.putExtra("user_ID",User_ID);
                startActivity(i);
            }
        });

        classe_prof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mainadmin2.this, ClasseProf.class);
                i.putExtra("user_ID",User_ID);
                startActivity(i);
            }
        });
    }
}
