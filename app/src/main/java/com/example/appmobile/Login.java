package com.example.appmobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Login extends AppCompatActivity {

    private EditText email;
    private EditText password;
    private TextView signup_;
    private Button login;
    private Administration ad = new Administration();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.login);

        email = findViewById(R.id.emailedit);
        password = findViewById(R.id.passedit);
        login = findViewById(R.id.login);
        signup_ = findViewById(R.id.signup);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ad.setEmail(email.getText().toString());
                ad.setPassword(password.getText().toString());

                if(ad.getEmail().isEmpty()) Toast.makeText(Login.this, "le champ email est vide", Toast.LENGTH_SHORT).show();
                else if(ad.getPassword().isEmpty()) Toast.makeText(Login.this, "le champ password est vide", Toast.LENGTH_SHORT).show();
                else if(!ad.check_email()) Toast.makeText(Login.this, "email invalide", Toast.LENGTH_SHORT).show();
                else ad.loginAdmin(Login.this);
            }
        });

        signup_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent(Login.this, SignUp.class);
                startActivity(i1);
            }
        });
    }
}
