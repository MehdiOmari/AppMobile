package com.example.appmobile;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
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

import java.util.HashMap;


public class TableauEleve extends Fragment {

    private TableLayout tb;

    private AppCompatButton bt;
    private AlertDialog dialog;
    private Context context;
    private String name_classe;
    private String level;
    private Eleve eleve;
    private Classes cl = new Classes(getName_classe());
    private int counter_eleve = 0;
    private DatabaseReference rf;

    private String user_ID;
    HashMap<String, Object> eleve_data = new HashMap<>();

    public void setName_classe(String name_classe) {
        this.name_classe = name_classe;
    }

    public String getName_classe() {
        return name_classe;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getLevel() {
        return this.level;

    }
    private int integer_level(){
        int lv = 1;
        switch (this.level){
            case "A-1" : lv = 1;
                         break;
            case "A-2" : lv = 2;
                         break;
            case "A-3" : lv = 3;
                         break;
            case "A-4" : lv = 4;
                         break;
            case "A-5" : lv = 5;
                         break;
        }
        return lv;
    }
    private int integer_classe(){
        int clss = 1;
        switch (this.name_classe){
            case "Classe 1" : clss = 1;
                              break;
            case "Classe 2" : clss = 2;
                break;
            case "Classe 3" : clss = 3;
                break;
            case "Classe 4" : clss = 4;
                break;
            case "Classe 5" : clss = 5;
                break;
            case "Classe 6" : clss = 6;
                break;
            case "Classe 7" : clss = 7;
                break;
        }
        return clss;
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

    private void build_dialogue() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View view = getLayoutInflater().inflate(R.layout.taper_nom, null);

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
                        eleve = new Eleve();
                        eleve.setId_eleve(String.valueOf(integer_level()) + String.valueOf(integer_classe()) + String.valueOf(counter_eleve + 1));
                        for (String i : s2.split("-")){
                            eleve.setId_eleve(eleve.getId_eleve() + i);
                        }
                        eleve.setName(s1);
                        eleve.setDate(s2);
                        eleve.setLieu(s3);
                        counter_eleve++;
                        add_data_eleve();
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

    private void add_data_eleve(){
        eleve_data.put("Name", eleve.getName());
        eleve_data.put("Date de Naissance", eleve.getDate());
        eleve_data.put("Lieu de Naissance", eleve.getLieu());
        FirebaseDatabase.getInstance().getReference().child("Administration").child(user_ID).child("Levels").child(getLevel())
                .child(getName_classe()).child(eleve.getId_eleve()).updateChildren(eleve_data).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(getActivity(), "Data added succefully", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void updateFragmentData(DataSnapshot dataSnapshot) {
        tb.removeAllViews();
        addrow("ID","Etudiant","Date de Naissance", "Lieu de Naissance");
        for (DataSnapshot ds : dataSnapshot.getChildren()) {
            String id = ds.getKey();
            String name = ds.child("Name").getValue(String.class);
            String date = ds.child("Date de Naissance").getValue(String.class);
            String lieu = ds.child("Lieu de Naissance").getValue(String.class);
            addrow(id, name, date, lieu);
        }
    }
    @Override
    public void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        //Toast.makeText(getActivity(), "onCreat eleve", Toast.LENGTH_SHORT).show();
        Bundle bundle = getArguments();
        user_ID = bundle.getString("user_ID");
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_tableau_eleve, container, false);
        //Toast.makeText(getActivity(), "Eleve", Toast.LENGTH_SHORT).show();


        tb = rootView.findViewById(R.id.table_etudiants);
        bt = rootView.findViewById(R.id.ajoute_etudiant);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });


        build_dialogue();

        return rootView;
    }


    @Override
    public void onStart() {
        super.onStart();
        //Toast.makeText(getActivity(), getName_classe(), Toast.LENGTH_SHORT).show();
        rf = FirebaseDatabase.getInstance().getReference().child("Administration").child(user_ID).child("Levels").child(getLevel())
                .child(getName_classe());
        rf.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                updateFragmentData(snapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }


    public void setContext(Context context) {
        this.context = context;
    }
}