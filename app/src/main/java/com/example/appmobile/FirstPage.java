package com.example.appmobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class FirstPage extends AppCompatActivity {
    private CardView admin;
    private CardView parent;
    private CardView professeur;
    public static boolean checkadmin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.first_page);

        admin = (CardView) findViewById(R.id.contributeCard);
        parent = (CardView) findViewById(R.id.contributeCard2);
        professeur = (CardView) findViewById(R.id.contributeCard3);
        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkadmin = true;
                Intent i = new Intent(FirstPage.this, Login.class);
                startActivity(i);
            }
        });
        parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FirstPage.this, SignUpParent.class);
                startActivity(i);
            }
        });
        professeur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FirstPage.this, Login.class);
                startActivity(i);
            }
        });
    }
}
