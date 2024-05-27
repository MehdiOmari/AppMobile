package com.example.appmobile.PFE;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.appmobile.Administration;
import com.example.appmobile.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class GradeProf2 extends Fragment {

    String epreuveQuiEstEntrainDEtreModifie = "";
    ImageButton back;

    HashMap<String, Object> students_grade_data = new HashMap<>();
    private DatabaseReference rf;
    String level;
    String classe;
    private String IdProf;

    int[][] tableRows = {
            {R.id.etudiant1layout, R.id.etudiant1txt, R.id.etudiant1cc1, R.id.etudiant1cc2, R.id.etudiant1exam},
            {R.id.etudiant2layout, R.id.etudiant2txt, R.id.etudiant2cc1, R.id.etudiant2cc2, R.id.etudiant2exam},
            {R.id.etudiant3layout, R.id.etudiant3txt, R.id.etudiant3cc1, R.id.etudiant3cc2, R.id.etudiant3exam},
            {R.id.etudiant4layout, R.id.etudiant4txt, R.id.etudiant4cc1, R.id.etudiant4cc2, R.id.etudiant4exam},
            {R.id.etudiant5layout, R.id.etudiant5txt, R.id.etudiant5cc1, R.id.etudiant5cc2, R.id.etudiant5exam},
            {R.id.etudiant6layout, R.id.etudiant6txt, R.id.etudiant6cc1, R.id.etudiant6cc2, R.id.etudiant6exam},
            {R.id.etudiant7layout, R.id.etudiant7txt, R.id.etudiant7cc1, R.id.etudiant7cc2, R.id.etudiant7exam},
            {R.id.etudiant8layout, R.id.etudiant8txt, R.id.etudiant8cc1, R.id.etudiant8cc2, R.id.etudiant8exam},
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_grade_prof2, container, false);

        // Find the back button after inflating the layout
        back = view.findViewById(R.id.back);
        Bundle bundel = getArguments();
        IdProf = bundel.getString("user_ID");

        // Set an onClickListener for the back button
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Use getActivity() as the context for the Intent
                getActivity().finish();
            }
        });

        // Set up the click listeners for the EditText fields
        // setUpEditTextListeners(view);

        FirebaseDatabase.getInstance().getReference().child("Professeur").child(IdProf).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                classe = snapshot.child("Classe").getValue(String.class);
                level = snapshot.child("Level").getValue(String.class);

                rf = FirebaseDatabase.getInstance().getReference().child("Administration").child(Administration.user_ID).child("Levels").child(level).child(classe);
                rf.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String studentId = null;
                        int studentIndex = 0;

                        for (DataSnapshot ds : snapshot.getChildren()) {
                            LinearLayout studentLayout = view.findViewById(tableRows[studentIndex][0]);

                            TextView setudentNameEditText = view.findViewById(tableRows[studentIndex][1]);
                            TextView setudentCC1EditText = view.findViewById(tableRows[studentIndex][2]);
                            TextView setudentCC2EditText = view.findViewById(tableRows[studentIndex][3]);
                            TextView setudentExamEditText = view.findViewById(tableRows[studentIndex][4]);

                            setudentNameEditText.setText(ds.child("Name").getValue(String.class));

                            try {
                                setudentCC1EditText.setText(ds.child("Notes").child("Trimestre 1").child("Arabe").child("cc1").getValue(String.class));
                            } catch (Exception e) {
                                setudentCC1EditText.setText("00");
                            }

                            System.out.println("Setup of CC1 Click Listener");

                            setudentCC1EditText.setOnClickListener((view) -> {
                                System.out.println("Clicked CC1");
                                showEditDialog(setudentCC1EditText, "cc1", ds.getKey());
                            });

                            try {
                                setudentCC2EditText.setText(ds.child("Notes").child("Trimestre 1").child("Arabe").child("cc2").getValue(String.class));
                            } catch (Exception e) {
                                setudentCC2EditText.setText("00");
                            }

                            setudentCC2EditText.setOnClickListener((view) -> {
                                System.out.println("Clicked CC2");
                                showEditDialog(setudentCC2EditText, "cc2", ds.getKey());
                            });

                            try {
                                setudentExamEditText.setText(ds.child("Notes").child("Trimestre 1").child("Arabe").child("exam").getValue(String.class));
                            } catch (Exception e) {
                                setudentExamEditText.setText("00");
                            }

                            setudentExamEditText.setOnClickListener((view) -> {
                                System.out.println("Clicked Exam");
                                showEditDialog(setudentExamEditText, "exam", ds.getKey());
                            });

                            studentLayout.setVisibility(View.VISIBLE);

                            studentIndex++;
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return view;
    }


    private void showEditDialog(final TextView editText, String epreuve, String studentId) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Modifier l'information");

        // Create a layout inflater to inflate the custom dialog view
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_edit_student, null);
        builder.setView(dialogView);

        // final EditText editStudentName = dialogView.findViewById(R.id.editStudentName);
        final EditText editStudentGrade = dialogView.findViewById(R.id.editStudentGrade);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                FirebaseDatabase.getInstance().getReference().child("Professeur").child(IdProf).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String classe = snapshot.child("Classe").getValue(String.class);
                        String level = snapshot.child("Level").getValue(String.class);

                        FirebaseDatabase.getInstance().getReference().child("Administration").child(Administration.user_ID).child("Levels").child(level).child(classe).child(studentId).child("Notes").child("Trimestre 1").child("Arabe").child(epreuve).setValue(editStudentGrade.getText().toString())
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        Toast.makeText(getActivity(), "data added succefully", Toast.LENGTH_SHORT).show();
                                    }
                                });;


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


                editText.setText(editStudentGrade.getText().toString());
            }
        });

        builder.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }
}