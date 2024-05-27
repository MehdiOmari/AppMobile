package com.example.appmobile;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.time.LocalDate;
import java.util.HashMap;


public class TableauProf extends Fragment {

    private Professeur professeur;
    private AppCompatButton ajoute_profeseur;
    private AlertDialog dialog;
    private int counter_prof = 0;
    HashMap<String, Object> prof_data = new HashMap<>();
    private String user_ID;
    private TableLayout tb;
    private DatabaseReference rf;
    private String matiere;

    public void setMatiere(String matiere) {
        this.matiere = matiere;
    }

    public String getMatiere() {
        return this.matiere;
    }

    private void build_dialogue() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View view = getLayoutInflater().inflate(R.layout.taper_nom, null);
        counter_prof ++;

        builder.setView(view);
        EditText e1 = view.findViewById(R.id.nameEdit);
        EditText e2 = view.findViewById(R.id.dateEdit);
        EditText e3 = view.findViewById(R.id.lieuEdit);

        builder.setTitle("Informations")
                .setPositiveButton("ajouter", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String s1 = e1.getText().toString();
                        String s2 = e2.getText().toString();
                        String s3 = e3.getText().toString();
                        professeur = new Professeur();
                        professeur.setProfID(String.valueOf(counter_prof));
                        for (String i : s2.split("-")){
                            professeur.setProfID(professeur.getProfID() + i);
                        }
                        switch (matiere){
                            case "Arabe" : professeur.setProfID(String.valueOf(1) + professeur.getProfID());
                                break;
                            case "Francais" : professeur.setProfID(String.valueOf(2) + professeur.getProfID());
                                break;
                            case "Anglais" : professeur.setProfID(String.valueOf(3) + professeur.getProfID());
                                break;
                            case "Sport" : professeur.setProfID(String.valueOf(4) + professeur.getProfID());
                                break;
                            case "ArtEtMusique" : professeur.setProfID(String.valueOf(5) + professeur.getProfID());
                                break;
                        }
                        professeur.setName(s1);
                        professeur.setDatedeNaissance(s2);
                        professeur.setLieudeNaissance(s3);
                        add_data_prof();
                        //addrow(s1, s2, s3);


                    }
                })
                .setNegativeButton("annuler", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        dialog = builder.create();
    }

    private void add_data_prof(){
        prof_data.put("Name", professeur.getName());
        prof_data.put("Date de Naissance", professeur.getDatedeNaissance());
        prof_data.put("Lieu de Naissance", professeur.getLieudeNaissance());
        FirebaseDatabase.getInstance().getReference().child("Administration").child(user_ID).child("Professeur").child(getMatiere())
                .child(professeur.getProfID()).updateChildren(prof_data).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(getActivity(), "Data added succefully", Toast.LENGTH_SHORT).show();
                    }
                });
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
    private void updateFragmentData(DataSnapshot dataSnapshot) {
        tb.removeAllViews();
        addrow("ID", "Professeur","Date de Naissance", "Lieu de Naissance");
        for (DataSnapshot ds : dataSnapshot.getChildren()) {
            String name = ds.child("Name").getValue(String.class);
            String date = ds.child("Date de Naissance").getValue(String.class);
            String lieu = ds.child("Lieu de Naissance").getValue(String.class);
            String id = ds.getKey();
            addrow(id, name, date, lieu);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Bundle bundel = getArguments();
        user_ID = bundel.getString("user_ID");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView =  inflater.inflate(R.layout.fragment_tableau_prof, container, false);

        tb = rootView.findViewById(R.id.table_professeur);
        ajoute_profeseur = rootView.findViewById(R.id.ajoute_professeur);

        build_dialogue();

        ajoute_profeseur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });

        return rootView;
    }

    public void onStart() {
        super.onStart();
        //Toast.makeText(getActivity(), getName_classe(), Toast.LENGTH_SHORT).show();
        rf = FirebaseDatabase.getInstance().getReference().child("Administration").child(user_ID).child("Professeur").child(getMatiere());
        rf.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                updateFragmentData(snapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        Toast.makeText(getActivity(), "onStart prof", Toast.LENGTH_SHORT).show();
    }
}