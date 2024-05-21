package com.example.appmobile;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Profile extends AppCompatActivity {

    private TextView emaill;
    private Administration admin = new Administration();
    private String Current_ID;
    private DatabaseReference df;
    private AppCompatButton deconnexion;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.profile);

        Current_ID = getIntent().getStringExtra("Current_ID");
        emaill = findViewById(R.id.emailtexte);
        deconnexion = findViewById(R.id.deconnexion);
        admin.setId_compte(Current_ID);

        df = FirebaseDatabase.getInstance().getReference().child("Administration").child(Current_ID).child("Email");
        df.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                emaill.setText(snapshot.getValue(String.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });

        deconnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                admin.logout(Profile.this);
            }
        });

    }
}
