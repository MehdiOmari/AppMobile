package com.example.appmobile.PFE;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.appmobile.Administration;
import com.example.appmobile.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;


public class GradeParent extends Fragment {

    private String User_ID;
    private String classe;
    private String level;
    private int index;

    int[][] textView_tableau = {
            {R.id.matiert1txt,   R.id.cc1txt,    R.id.cc21txt,   R.id.exam1txt,     R.id.moytxt},
            {R.id.matiert2txt,   R.id.cc2txt,    R.id.cc22txt,   R.id.exam2txt,     R.id.moy1txt},
            {R.id.matiert3txt,   R.id.cc3txt,    R.id.cc23txt,   R.id.exam3txt,     R.id.moy2txt},
            {R.id.matiert4txt,   R.id.cc4txt,    R.id.cc24txt,   R.id.exam4txt,     R.id.moy3txt},
            {R.id.matiert5txt,   R.id.cc5txt,    R.id.cc25txt,   R.id.exam5txt,     R.id.moy4txt},
            {R.id.matiert6txt,   R.id.cc6txt,    R.id.cc26txt,   R.id.exam6txt,     R.id.moy5txt},
            {R.id.matiert7txt,   R.id.cc7txt,    R.id.cc27txt,   R.id.exam7txt,     R.id.moy6txt},
            {R.id.matiert8txt,   R.id.cc8txt,    R.id.cc28txt,   R.id.exam8txt,     R.id.moy7txt},
            {R.id.matiert9txt,   R.id.cc9txt,    R.id.cc29txt,   R.id.exam9txt,     R.id.moy8txt},
            {R.id.matiert10txt,  R.id.cc10txt,   R.id.cc210txt,  R.id.exam10txt,    R.id.moy9txt},

    };



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_grade_parent, container, false);

        Bundle bundle = new Bundle();
        bundle = getArguments();
        User_ID = bundle.getString("user_ID");

        System.out.println(User_ID);
        System.out.println(MainParent2.ID_fils1);
        FirebaseDatabase.getInstance().getReference().child("Parent").child(User_ID).child(MainParent2.ID_fils1).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                classe = snapshot.child("Classe").getValue(String.class);
                level = snapshot.child("Level").getValue(String.class);
                get_Data(classe, level);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        return rootView;
    }
    public void get_Data(String c, String l){
        for (int i = 1;i < textView_tableau.length; i++) {

            TextView textView_matiere = getView().findViewById(textView_tableau[1][0]);
            TextView textView_cc1 = getView().findViewById(textView_tableau[i][1]);
            TextView textView_cc2 = getView().findViewById(textView_tableau[i][2]);
            TextView textView_exam = getView().findViewById(textView_tableau[i][3]);
            TextView textView_moyenne = getView().findViewById(textView_tableau[i][4]);

            FirebaseDatabase.getInstance().getReference().child("Administration").child(Administration.user_ID).child(level).child(classe)
                    .child(MainParent2.ID_fils1).child("Notes").child("Trimestre 1").child(textView_matiere.getText().toString())
                    .addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            textView_cc1.setText(snapshot.child("cc1").getValue(String.class));
                            textView_cc2.setText(snapshot.child("cc2").getValue(String.class));
                            textView_exam.setText(snapshot.child("exam").getValue(String.class));
                            if (!textView_cc1.getText().toString().equals("") && !textView_cc2.getText().toString().equals("") && !textView_exam.getText().toString().equals("")){
                                textView_moyenne.setText(String.valueOf((Float.parseFloat(textView_cc1.getText().toString()) + Float.parseFloat(textView_cc2.getText().toString()) + Float.parseFloat(textView_exam.getText().toString()) * 2) / 4));
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });


        }
    }
}