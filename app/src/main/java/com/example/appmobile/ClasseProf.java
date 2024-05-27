package com.example.appmobile;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;

import com.example.appmobile.PFE.Login;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class ClasseProf extends AppCompatActivity {

    private ArrayList<String> levels = new ArrayList<>();
    private ArrayList<String> classes = new ArrayList<>();
    private ArrayList<String> profs_arabe = new ArrayList<>();
    private ArrayList<String> profs_francais = new ArrayList<>();
    private ArrayList<String> profs_anglais = new ArrayList<>();
    private ArrayList<String> profs_sport = new ArrayList<>();
    private ArrayList<String> profs_art = new ArrayList<>();
    private ArrayList<String> profs_arabe_key = new ArrayList<>();
    private ArrayList<String> profs_francais_key = new ArrayList<>();
    private ArrayList<String> profs_anglais_key = new ArrayList<>();
    private ArrayList<String> profs_sport_key = new ArrayList<>();
    private ArrayList<String> profs_art_key = new ArrayList<>();

    private ArrayAdapter<String> adapter_level;
    private ArrayAdapter<String> adapter_classe;
    private ArrayAdapter<String> adapter_arabe;
    private ArrayAdapter<String> adapter_francais;
    private ArrayAdapter<String> adapter_anglais;
    private ArrayAdapter<String> adapter_sport;
    private ArrayAdapter<String> adapter_art;
    private TableLayout tb;
    private AppCompatButton button;
    private String user_ID;
    private ImageButton back;

    AlertDialog dialog;
    private String s1,s2,s3,s4,s5,s6,s7;
    private HashMap<String, String> classe_prof_data = new HashMap<>();
    private int count = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_classe_prof);

        tb = findViewById(R.id.table_classe_prof);
        button = findViewById(R.id.ajoute__);
        user_ID = getIntent().getStringExtra("user_ID");
        back = findViewById(R.id.back);

        levels.add("Level");
        levels.add("A-1");
        levels.add("A-2");
        levels.add("A-3");
        levels.add("A-4");
        levels.add("A-5");

        classes.add("Classe");
        classes.add("Classe 1");
        classes.add("Classe 2");



        add_prof();

        adapter_level = new ArrayAdapter<>(this, R.layout.spinner_item, levels);
        adapter_classe = new ArrayAdapter<>(this, R.layout.spinner_item, classes);
        adapter_arabe = new ArrayAdapter<>(this, R.layout.spinner_item, profs_arabe);
        adapter_francais = new ArrayAdapter<>(this, R.layout.spinner_item, profs_francais);
        adapter_anglais = new ArrayAdapter<>(this, R.layout.spinner_item, profs_anglais);
        adapter_sport = new ArrayAdapter<>(this, R.layout.spinner_item, profs_sport);
        adapter_art = new ArrayAdapter<>(this, R.layout.spinner_item, profs_art);

        adapter_level.setDropDownViewResource(R.layout.spinner_item);
        adapter_classe.setDropDownViewResource(R.layout.spinner_item);
        adapter_arabe.setDropDownViewResource(R.layout.spinner_item);
        adapter_francais.setDropDownViewResource(R.layout.spinner_item);
        adapter_anglais.setDropDownViewResource(R.layout.spinner_item);
        adapter_sport.setDropDownViewResource(R.layout.spinner_item);
        adapter_art.setDropDownViewResource(R.layout.spinner_item);


        build_dialogue();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });

        back.setOnClickListener((v) -> {finish();});

    }

    protected void onStart(){
        super.onStart();

        FirebaseDatabase.getInstance().getReference().child("Administration").child(user_ID).child("Classe-Prof")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        updatefragmentdata(snapshot);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }

    private void updatefragmentdata(DataSnapshot snapshot) {
        tb.removeAllViews();
        addrow("LEVEL","CLASSE","ENSEIGNANT D'ARABE","ENSEIGNANT DE FRANCAIS","ENSEIGNANT DE ANGLAIS","PROF DE SPORT","PROF D'ART");
        for (DataSnapshot ds:snapshot.getChildren()){
            String l = ds.child("Level").getValue(String.class);
            String c = ds.child("Classe").getValue(String.class);
            String a = ds.child("Prof d'Arabe").getValue(String.class);
            String f = ds.child("Prof de Francais").getValue(String.class);
            String ag = ds.child("Prof d'Anglais").getValue(String.class);
            String s = ds.child("Prof de Sport").getValue(String.class);
            String arm = ds.child("Prof d'Art Et Musique").getValue(String.class);
            addrow(l, c, a, f, ag, s, arm);
        }
    }

    private void build_dialogue() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.classe_prof_dialog, null);

        builder.setView(view);
        Spinner e1 = view.findViewById(R.id.level);
        Spinner e2 = view.findViewById(R.id.classe);
        Spinner e3 = view.findViewById(R.id.arabe);
        Spinner e4 = view.findViewById(R.id.francais);
        Spinner e5 = view.findViewById(R.id.anglais);
        Spinner e6 = view.findViewById(R.id.sport);
        Spinner e7 = view.findViewById(R.id.Art);

        e1.setAdapter(adapter_level);
        e2.setAdapter(adapter_classe);
        e3.setAdapter(adapter_arabe);
        e3.setSelection(0);
        e4.setAdapter(adapter_francais);
        e4.setSelection(0);
        e5.setAdapter(adapter_anglais);
        e5.setSelection(0);
        e6.setAdapter(adapter_sport);
        e6.setSelection(0);
        e7.setAdapter(adapter_art);
        e7.setSelection(0);

        e1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) s1 = null;
                else {
                    s1 = parent.getItemAtPosition(position).toString();
                    Toast.makeText(ClasseProf.this, "level added", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        e2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) s2 = null;
                else {
                    s2 = parent.getItemAtPosition(position).toString();
                    Toast.makeText(ClasseProf.this, "classe added", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        e3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) s3 = null;
                else {
                    s3 = parent.getItemAtPosition(position).toString();
                    Toast.makeText(ClasseProf.this, "prof arabe added", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        e4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) s4 = null;
                else {
                    s4 = parent.getItemAtPosition(position).toString();

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        e5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) s5 = null;
                else {
                    s5 = parent.getItemAtPosition(position).toString();

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        e6.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) s6 = null;
                else {
                    s6 = parent.getItemAtPosition(position).toString();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        e7.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) s7 = null;
                else {
                    s7 = parent.getItemAtPosition(position).toString();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        builder.setTitle("Informations")
                .setPositiveButton("ajouter", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        add_Data(s1, s2, s3, s4, s5, s6, s7);
                        addrow(s1, s2, s3, s4, s5, s6, s7);
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

    public void addrow(String level, String classe, String arabe, String francais, String anglais, String sport, String art){
        View v = getLayoutInflater().inflate(R.layout.classe_prof_row, null);

        TextView t1 = v.findViewById(R.id.leveltxt);
        TextView t2 = v.findViewById(R.id.classetxt);
        TextView t3 = v.findViewById(R.id.arabetxt);
        TextView t4 = v.findViewById(R.id.francaistxt);
        TextView t5 = v.findViewById(R.id.anglaistxt);
        TextView t6 = v.findViewById(R.id.sporttxt);
        TextView t7 = v.findViewById(R.id.arttxt);

        t1.setText(level);
        t2.setText(classe);
        t3.setText(arabe);
        t4.setText(francais);
        t5.setText(anglais);
        t6.setText(sport);
        t7.setText(art);

        tb.addView(v);


    }

    private void add_prof(){
        FirebaseDatabase.getInstance().getReference().child("Administration").child(user_ID).child("Professeur").child("Arabe")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        profs_arabe.clear();
                        profs_arabe.add("Prof d'Arabe");
                        profs_arabe_key.clear();
                        profs_arabe_key.add("0000000");
                        for (DataSnapshot ds : snapshot.getChildren()) {
                            profs_arabe.add(ds.child("Name").getValue().toString());
                            profs_arabe_key.add(ds.getKey().toString());
                        }
                        adapter_arabe.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
        FirebaseDatabase.getInstance().getReference().child("Administration").child(user_ID).child("Professeur").child("Francais")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        profs_francais.clear();
                        profs_francais.add("Prof de Français");
                        profs_francais.add("N'etudie pas");
                        profs_francais_key.clear();
                        profs_francais_key.add("0000000");
                        for (DataSnapshot ds : snapshot.getChildren()) {
                            profs_francais.add(ds.child("Name").getValue().toString());
                            profs_francais_key.add(ds.getKey().toString());
                        }
                        adapter_francais.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
        FirebaseDatabase.getInstance().getReference().child("Administration").child(user_ID).child("Professeur").child("Anglais")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        profs_anglais.clear();
                        profs_anglais.add("Prof d'Anglais");
                        profs_anglais.add("N'etudie pas");
                        profs_anglais_key.clear();
                        profs_anglais_key.add("0000000");
                        for (DataSnapshot ds : snapshot.getChildren()) {
                            profs_anglais.add(ds.child("Name").getValue().toString());
                            profs_anglais_key.add(ds.getKey().toString());
                        }
                        adapter_anglais.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
        FirebaseDatabase.getInstance().getReference().child("Administration").child(user_ID).child("Professeur").child("Sport")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        profs_sport.clear();
                        profs_sport.add("Prof de Sport");
                        profs_sport_key.clear();
                        profs_sport_key.add("0000000");
                        for (DataSnapshot ds : snapshot.getChildren()) {
                            profs_sport.add(ds.child("Name").getValue().toString());
                            profs_sport_key.add(ds.getKey().toString());
                        }
                        adapter_sport.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
        FirebaseDatabase.getInstance().getReference().child("Administration").child(user_ID).child("Professeur").child("ArtEtMusique")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        profs_art.clear();
                        profs_art.add("Prof d'Art");
                        profs_art_key.clear();
                        profs_art_key.add("0000000");
                        for (DataSnapshot ds : snapshot.getChildren()) {
                            profs_art.add(ds.child("Name").getValue().toString());
                            profs_art_key.add(ds.getKey().toString());
                        }
                        adapter_art.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }

    private void add_Data(String level, String classe, String arabe, String francais, String anglais, String sport, String art) {
        count++;
        classe_prof_data.clear();
        classe_prof_data.put("Level", level);
        classe_prof_data.put("Classe", classe);
        classe_prof_data.put("Prof d'Arabe", arabe);
        if(!francais.equals("N'etudie pas")) classe_prof_data.put("Prof de Francais", francais);
        if(!anglais.equals("N'etudie pas")) classe_prof_data.put("Prof d'Anglais", anglais);
        classe_prof_data.put("Prof de Sport", sport);
        classe_prof_data.put("Prof d'Art Et Musique", art);
        FirebaseDatabase.getInstance().getReference().child("Administration").child(user_ID).child("Classe-Prof").child(String.valueOf(count)).setValue(classe_prof_data);
    }

    public static class MainParent extends AppCompatActivity {

        private CardView c;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            EdgeToEdge.enable(this);
            setContentView(R.layout.main_parent);

            c = findViewById(R.id.contributeCard1);
            c.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(MainParent.this, Login.class);
                    startActivity(i);
                }
            });
        }
    }
}