package com.example.appmobile;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Liste_Eleve_Prof extends AppCompatActivity {

    private String user_ID;
    private String ID;
    private String classe;
    private String level;
    private String Matiere;
    TableLayout tb;
    private ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_liste_eleve_prof);

        user_ID = getIntent().getStringExtra("user_ID");
        tb = findViewById(R.id.table_etudiants);
        back = findViewById(R.id.back);
        FirebaseDatabase.getInstance().getReference().child("Professeur").child(user_ID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ID = snapshot.child("ID").getValue(String.class);
                Matiere = snapshot.child("Matiere").getValue(String.class);
                if(Matiere.equals("Arabe")) Toast.makeText(Liste_Eleve_Prof.this,"Matiere", Toast.LENGTH_SHORT).show();
                Toast.makeText(Liste_Eleve_Prof.this, "first succes", Toast.LENGTH_SHORT).show();
                get_classeprof_data(Matiere);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        /*FirebaseDatabase.getInstance().getReference().child("Administration").child(level).child(classe)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        //updateFragmentData(snapshot);
                        Toast.makeText(Liste_Eleve_Prof.this, "you are in", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });*/

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void updateFragmentData(DataSnapshot dataSnapshot) {
        tb.removeAllViews();
        addrow("ID", "Etudiant","Date de Naissance", "Lieu de Naissance");
        for (DataSnapshot ds : dataSnapshot.getChildren()) {
            String name = ds.child("Name").getValue(String.class);
            String date = ds.child("Date de Naissance").getValue(String.class);
            String lieu = ds.child("Lieu de Naissance").getValue(String.class);
            String id = ds.getKey();
            addrow(id, name, date, lieu);
        }
    }

    public void addrow(String id, String name, String date, String lieu){


        View v = getLayoutInflater().inflate(R.layout.etudiant_row, null);
        TextView t1 = v.findViewById(R.id.etudianttxt);
        TextView t2 = v.findViewById(R.id.datedenaissancettxt);
        TextView t3 = v.findViewById(R.id.lieudenaissancetxt);
        TextView t4 = v.findViewById(R.id.IDtxt);

        t1.setText(name);
        t2.setText(date);
        t3.setText(lieu);
        t4.setText(id);

        tb.addView(v);


    }
    public void get_classeprof_data(String matiere){
        if(matiere == null || !matiere.equals("Arabe")) return;
        FirebaseDatabase.getInstance().getReference().child("Administration").child("PGEWEgZwSCZLtLnR2g7eCdN6ZZF3").child("Professeur").child(matiere).child(ID)
                .child("Classe Enseigné").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                        classe = snapshot.child("Classe").getValue(String.class);
                        level = snapshot.child("Level").getValue(String.class);
                Toast.makeText(Liste_Eleve_Prof.this, "succes", Toast.LENGTH_SHORT).show();
                get_data_eleve(classe,level);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public void get_data_eleve(String c, String l){
        if(c == null || l == null){
            Toast.makeText(Liste_Eleve_Prof.this,"null",Toast.LENGTH_SHORT).show();
            return;
        }

        FirebaseDatabase.getInstance().getReference().child("Administration").child(Administration.user_ID).child("Levels").child(l).child(c)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        updateFragmentData(snapshot);
                        Toast.makeText(Liste_Eleve_Prof.this, c, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


    }
}