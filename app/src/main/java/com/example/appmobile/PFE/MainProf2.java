package com.example.appmobile.PFE;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.appmobile.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainProf2 extends AppCompatActivity {

    private String user_ID;
    private CardView grade;
    private TextView t1,t2;

;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_prof2);

        user_ID = getIntent().getStringExtra("user_ID");
        grade = findViewById(R.id.grade);
        t1 = findViewById(R.id.nomtxt);
        t2 = findViewById(R.id.niveautxt);
        Bundle bundle = new Bundle();
        bundle.putString("user_ID",user_ID);

        FirebaseDatabase.getInstance().getReference().child("Professeur").child(user_ID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                t1.setText("NOM : " + snapshot.child("Name").getValue(String.class));
                t2.setText("MATIERE : " + snapshot.child("Matiere").getValue(String.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        grade.setOnClickListener((v) -> {
            GradeProf2 g = new GradeProf2();
            g.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().replace(R.id.container_layout, g).addToBackStack(null).commit();
        });


    }
}