package com.example.appmobile.PFE;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;

import com.example.appmobile.Administration;
import com.example.appmobile.ClasseProf;
import com.example.appmobile.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class SignUpParent2 extends AppCompatActivity {

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
    HashMap<String, String> eleveData = new HashMap<>();
    HashMap<String, String> compteData = new HashMap<>();
    private CardView liste_eleve;
    private String Matiere;
    private ImageButton back;
    private String type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up_parent2);

        e1 = findViewById(R.id.useredit);
        e2 = findViewById(R.id.IDedit);
        e3 = findViewById(R.id.emailedit);
        e4 = findViewById(R.id.passedit);
        Sign_up = findViewById(R.id.signup);
        auth = FirebaseAuth.getInstance();
        back = findViewById(R.id.back);
        type = getIntent().getStringExtra("Type");

        Sign_up.setOnClickListener((v) -> {
            Name = e1.getText().toString();
            ID = e2.getText().toString();
            email = e3.getText().toString();
            passeword = e4.getText().toString();

            if (type.equals("Prof")) signProf();
            else signParent();
        });

        back.setOnClickListener((v) -> {finish();});

    }

    public void signProf() {
        Toast.makeText(SignUpParent2.this, "correct",Toast.LENGTH_SHORT).show();
        profData.put("Name", Name);
        profData.put("ID", ID);
        profData.put("Email", email);
        profData.put("Password", passeword);
        definir_matiere();

        FirebaseDatabase.getInstance().getReference().child("Administration").child(Administration.user_ID).child("Classe-Prof")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                            definir_classe_level(snapshot);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
        auth.createUserWithEmailAndPassword(email,passeword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    String userId = auth.getCurrentUser().getUid();
                    FirebaseDatabase.getInstance().getReference().child("Professeur").child(userId).setValue(profData);
                    Intent i = new Intent(SignUpParent2.this, MainProf2.class);
                    i.putExtra("user_ID", userId);
                    startActivity(i);
                }
                else Toast.makeText(SignUpParent2.this, "failed Auth", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void definir_classe_level(DataSnapshot snapshot) {

        if(profData.get("Matiere").equals("Arabe")){
            for (DataSnapshot ds : snapshot.getChildren()){
                if(ds.child("Prof d'Arabe").getValue(String.class).equals(profData.get("Name"))){
                    profData.put("Classe", ds.child("Classe").getValue(String.class));
                    profData.put("Level", ds.child("Level").getValue(String.class));
                }
            }
        }
    }

    public void signParent() {
        Toast.makeText(SignUpParent2.this, "correct",Toast.LENGTH_SHORT).show();
        eleveData.put("Name", Name);
        compteData.put("Email", email);
        compteData.put("Password", passeword);
        definir_level();
        definir_classe();

        auth.createUserWithEmailAndPassword(email,passeword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    String userId = auth.getCurrentUser().getUid();
                    FirebaseDatabase.getInstance().getReference().child("Parent").child(userId).setValue(compteData);
                    FirebaseDatabase.getInstance().getReference().child("Parent").child(userId).child(ID).setValue(eleveData);
                    Intent i = new Intent(SignUpParent2.this, MainParent2.class);
                    i.putExtra("user_ID", userId);
                    i.putExtra("ID",ID);
                    startActivity(i);
                }
                else Toast.makeText(SignUpParent2.this, "failed Auth", Toast.LENGTH_SHORT).show();
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

    public void definir_classe(){
        char c = ID.charAt(1);
        switch (c){
            case '1' : eleveData.put("Classe","Classe 1");
            break;
            case '2' : eleveData.put("Classe", "Classe 2");
            break;
        }
    }

    public void definir_level(){
        char c = ID.charAt(0);
        switch (c){
            case '1' : eleveData.put("Level", "A-1");
            break;
            case '2' : eleveData.put("Level", "A-2");
            break;
            case '3' : eleveData.put("Level", "A-3");
            break;
            case '4' : eleveData.put("Level", "A-4");
            break;
            case '5' : eleveData.put("Level", "A-5");
            break;
        }
    }


}