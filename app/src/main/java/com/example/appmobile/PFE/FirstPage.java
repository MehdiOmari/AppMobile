package com.example.appmobile.PFE;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.appmobile.R;

public class FirstPage extends AppCompatActivity {
    private CardView admin;
    private CardView parent;
    private CardView professeur;
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
                Intent i = new Intent(FirstPage.this, Login.class);
                i.putExtra("Type","Admin");
                startActivity(i);
            }
        });
        parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FirstPage.this, Login.class);
                i.putExtra("Type","Parent");
                startActivity(i);
            }
        });
        professeur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FirstPage.this, Login.class);
                i.putExtra("Type","Prof");
                startActivity(i);
            }
        });
    }
}
