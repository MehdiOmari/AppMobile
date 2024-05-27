package com.example.appmobile.PFE;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.appmobile.R;
import com.example.appmobile.TableauEleve;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;


public class Liste_Classes extends Fragment {
    private AppCompatButton classe_1;
    private AppCompatButton add_classes;
    private String user_ID;
    private boolean color_btn_classe[] = new boolean[20];
    private LinearLayout linearLayoutclasse;
    private int classcounter = 1;
    HashMap<String, Integer> id_btn_classe = new HashMap<>();
    private String id_classe;
    private String level;
    private ArrayList<TableauEleve> fragment_eleve = new ArrayList<>();
    private Bundle bundle_eleve;
    private DatabaseReference rf, rf1;
    private LinearLayout v;

    public void setLevel(String level) {
        this.level = level;
    }

    public String getLevel() {
        return this.level;
    }

    private void add_new_btn_classe() {
        AppCompatButton button = new AppCompatButton(getActivity());
        button.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));

        int widthindp = 120;
        float density = getResources().getDisplayMetrics().density;
        int widthinpx = (int) (widthindp * density);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                widthinpx,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );

        params.setMarginEnd(40);
        params.setMarginStart(10);
        button.setLayoutParams(params);

        int buttonid = View.generateViewId();
        button.setId(buttonid);
        id_btn_classe.put("Classe_" + classcounter, buttonid);
        button.setText("Classe " + classcounter);
        button.setTextColor(Color.parseColor("#1261A0"));
        if (classcounter == 1) button.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.btnstyle));
        else button.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.btnstule));
        button.setTypeface(null, Typeface.BOLD);

        int in = linearLayoutclasse.indexOfChild(add_classes);
        linearLayoutclasse.addView(button, in);

        setupButtonClickListener(button, classcounter);

    }

    private void setupButtonClickListener(AppCompatButton button, int counter) {
        final int index = counter;  // Copy of counter for use in OnClickListener

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (color_btn_classe[index - 1])
                    Toast.makeText(getActivity(), "you are in the typed class", Toast.LENGTH_SHORT).show();
                else {
                    button.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.btnstyle));

                    for (int i1 = 0; i1 < color_btn_classe.length; i1++) {
                        if (color_btn_classe[i1]) {
                            AppCompatButton b1 = getView().findViewById(id_btn_classe.get("Classe_" + (i1 + 1)));
                            b1.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.btnstule));
                            color_btn_classe[i1] = false;
                        }
                    }

                    color_btn_classe[index - 1] = true;

                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    TableauEleve frgg;
                    frgg = new TableauEleve();
                    frgg.setContext(getActivity());
                    frgg.setName_classe("Classe " + index);
                    frgg.setLevel(Liste_Classes.this.getLevel());
                    frgg.setArguments(bundle_eleve);

                    ft.replace(R.id.layout_container, frgg);
                    ft.addToBackStack(null);
                    ft.commit();
                }
            }
        });
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        user_ID = bundle.getString("user_ID");
        //Toast.makeText(getActivity(),"onCreate" + getLevel(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.fragment_liste__classes, container, false);
        //Toast.makeText(getActivity(),"onCreateView" + getLevel(), Toast.LENGTH_SHORT).show();
        add_classes = rootView.findViewById(R.id.add_new_classe);
        //classe_1 = rootView.findViewById(R.id.class_1);
        linearLayoutclasse = rootView.findViewById(R.id.btnclasselayout);
        bundle_eleve = new Bundle();
        bundle_eleve.putString("user_ID", user_ID);


        TableauEleve frg = new TableauEleve();
        frg.setName_classe("Classe 1");
        frg.setLevel(this.getLevel());
        frg.setArguments(bundle_eleve);

        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.layout_container, frg)
                .addToBackStack(null)
                .commit();


        //id_btn_classe.put("Classe_1", R.id.class_1);
        for (int i = 0; i < color_btn_classe.length; i++) {
            color_btn_classe[i] = i == 0;
        }

        add_classes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase.getInstance().getReference().child("Administration").child(user_ID).child("Levels").child(getLevel()).child("Classe " + classcounter).setValue("");
            }
        });
        /*classe_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (color_btn_classe[0])
                    Toast.makeText(getActivity(), "you are in the typed class", Toast.LENGTH_SHORT).show();
                else {
                    classe_1.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.btnstyle));
                    color_btn_classe[0] = true;
                    for (int i = 1; i < color_btn_classe.length; i++) {
                        if (color_btn_classe[i]) {
                            color_btn_classe[i] = false;
                            AppCompatButton b1 = getView().findViewById(id_btn_classe.get("Classe_" + (i + 1)));
                            b1.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.btnstule));
                        }
                    }

                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.layout_container, frg).commit();
                }
            }
        });*/

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        Toast.makeText(getActivity(), getLevel(), Toast.LENGTH_SHORT).show();
        rf = FirebaseDatabase.getInstance().getReference().child("Administration").child(user_ID).child("Levels").child(getLevel());
        rf.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                add_new_btn_classe();
                classcounter++;
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void upgradeFragmentData(DataSnapshot snapshot) {
        for (DataSnapshot ds : snapshot.getChildren()) {
            if (!ds.getKey().toString().equals("Classe 1")) {
                add_new_btn_classe();
            }
        }
    }
}

