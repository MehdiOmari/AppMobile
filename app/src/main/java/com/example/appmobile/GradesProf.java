package com.example.appmobile;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class GradesProf extends AppCompatActivity {

    private Spinner spinner;
    private String user_ID;
    private String matiere;
    private String Classe;
    private String Level;
    private ArrayList<String> eleves = new ArrayList<>();
    private ArrayAdapter adapter_eleves;
    /*private String*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_grades_prof);

        spinner = findViewById(R.id.spinner_etudiant);
        user_ID = getIntent().getStringExtra("user_ID");





        FirebaseDatabase.getInstance().getReference().child("Professeur").child(user_ID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                    matiere = snapshot.child("Matiere").getValue(String.class);
                    Classe = snapshot.child("Classe").getValue(String.class);
                    Level = snapshot.child("Level").getValue(String.class);
                    addfragment(matiere);
                    get_classe(Classe, Level);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        adapter_eleves = new ArrayAdapter<>(this, R.layout.grade_spinner_item, eleves);
        spinner.setAdapter(adapter_eleves);

    }


    public void addfragment(String matiere){
        if (matiere.equals("Arabe")){
            Classe_prof_arabe c = new Classe_prof_arabe();
            getSupportFragmentManager().beginTransaction().replace(R.id.classecontainer, c).addToBackStack(null).commit();
        }
    }

    public void get_classe(String classe, String level){
        FirebaseDatabase.getInstance().getReference().child("Administration").child(Administration.user_ID).child("Levels").child(level).child(classe)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                            add_liste_spinner(snapshot);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }

    private void add_liste_spinner(DataSnapshot snapshot) {
        eleves.clear();
        eleves.add("Liste eleves");
        for (DataSnapshot ds : snapshot.getChildren()) {
            eleves.add(ds.child("Name").getValue(String.class));
        }
    }
}