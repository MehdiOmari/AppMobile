package com.example.appmobile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpParent extends AppCompatActivity {

    private AppCompatButton Sign_up;
    private EditText e1;
    private EditText e2;
    private EditText e3;
    private EditText e4;
    private String Name;
    private String ID;
    private String email;
    private String passeword;
    private FirebaseAuth auth;
    HashMap<String, String> profData = new HashMap<>();
    private CardView liste_eleve;
    private String Matiere;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up_parent);

        e1 = findViewById(R.id.useredit);
        e2 = findViewById(R.id.IDedit);
        e3 = findViewById(R.id.emailedit);
        e4 = findViewById(R.id.passedit);
        Sign_up = findViewById(R.id.button);
        auth = FirebaseAuth.getInstance();


        Sign_up.setOnClickListener((v) -> {
            Name = e1.getText().toString();
            ID = e2.getText().toString();
            email = e3.getText().toString();
            passeword = e4.getText().toString();

            signProf();
        });

        /*Sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signProf();
            }
        });*/

    }

    public void signProf() {
        Toast.makeText(SignUpParent.this, "correct",Toast.LENGTH_SHORT).show();
        profData.put("Name", Name);
        profData.put("ID", ID);
        profData.put("Email", email);
        profData.put("Password", passeword);
        definir_matiere();
        definir_classe_level();
        auth.createUserWithEmailAndPassword(email,passeword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    String userId = auth.getCurrentUser().getUid();
                    FirebaseDatabase.getInstance().getReference().child("Professeur").child(userId).setValue(profData);
                    Intent i = new Intent(SignUpParent.this, MainProf.class);
                    i.putExtra("user_ID", userId);
                    startActivity(i);
                }
                else Toast.makeText(SignUpParent.this, "failed Auth", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void definir_classe_level() {
        FirebaseDatabase.getInstance().getReference().child("Administration").child(Administration.user_ID).child("classe-prof")
            .addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
    }

    public void definir_matiere(){
        char c = ID.charAt(0);
        switch (c){
            case '1' : profData.put("Matiere", "Arabe");
                break;
            case '2' : profData.put("Matiere", "Francais");
                break;
            case '3' : profData.put("Matiere", "Anglais");
                break;
            case '4' : profData.put("Matiere", "Sport");
                break;
            case '5' : profData.put("Matiere", "ArtEtMusique");
                break;

        }
    }

}
