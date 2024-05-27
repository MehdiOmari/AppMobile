package com.example.appmobile;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;


public class Tableau_Note_Eleve_A1_A2 extends Fragment {

    private ArrayList<String> matiere = new ArrayList<>();
    private ArrayList<String> note = new ArrayList<>();

    private ArrayAdapter adapter_matiere;
    private ArrayAdapter adapter_note;
    private String matiere_note,type_note, valeur_note;

    public void build_dialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View v = getLayoutInflater().inflate(R.layout.dialog_note_a1_a2_prof_arabe, null);

        builder.setView(v);
        Spinner s1 = v.findViewById(R.id.matiere);
        Spinner s2 = v.findViewById(R.id.note);
        EditText e = v.findViewById(R.id.tapernote);

        s1.setAdapter(adapter_matiere);
        s2.setAdapter(adapter_note);



        s1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                matiere_note = parent.getItemAtPosition(position).toString();
            }
        });

        s2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                type_note = parent.getItemAtPosition(position).toString();
            }
        });

        String valeur_note = e.getText().toString();

        builder.setTitle("Note d'Eleve")
                .setPositiveButton("AJOUTER", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        adddata(matiere_note,type_note,valeur_note);
                    }
                })
                .setNegativeButton("ANNULER", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });

    }

    private void adddata(String matiereNote, String typeNote, String valeurNote) {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        matiere.add("MATIERE");
        matiere.add("ARABE");
        matiere.add("MATH");
        matiere.add("ED ISLAMIQUE");
        matiere.add("ED CIVILE");
        matiere.add("ED SCIENTIFIQUE");
        matiere.add("HISTOIRE");
        matiere.add("GEOGRAPHIQUE");
        matiere.add("ART ET MUSIQUE");

        note.add("NOTE DE");
        note.add(("CC1"));
        note.add("CC2");
        note.add("EXAMEN");

        adapter_matiere = new ArrayAdapter<>(getActivity(), R.layout.spinner_item, matiere);
        adapter_note = new ArrayAdapter<>(getActivity(), R.layout.spinner_item, note);


        View rootView = inflater.inflate(R.layout.fragment_tableau__note__eleve__a1__a2, container, false);
        return rootView;
    }
}