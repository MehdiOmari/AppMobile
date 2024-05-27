package com.example.appmobile.PFE;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.appmobile.Administration;
import com.example.appmobile.ClasseProf;
import com.example.appmobile.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    private EditText email;
    private EditText password;
    private TextView signup_;
    private Button login;
    private Administration ad = new Administration();
    private String type;
    FirebaseAuth auth;
    private String user_ID;
    private ImageButton back;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.login);

        email = findViewById(R.id.emailedit);
        password = findViewById(R.id.passedit);
        login = findViewById(R.id.login);
        signup_ = findViewById(R.id.signup);
        type = getIntent().getStringExtra("Type");
        back = findViewById(R.id.back);
        auth = FirebaseAuth.getInstance();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email_ = email.getText().toString();
                String password_ = password.getText().toString();
                if (type.equals("Prof")){
                    loginProf(email_,password_);
                }
                else if (type.equals("Parent")) loginParent(email_,password_);
                else {
                    ad.setEmail(email.getText().toString());
                    ad.setPassword(password.getText().toString());

                    if (ad.getEmail().isEmpty())
                        Toast.makeText(Login.this, "le champ email est vide", Toast.LENGTH_SHORT).show();
                    else if (ad.getPassword().isEmpty())
                        Toast.makeText(Login.this, "le champ password est vide", Toast.LENGTH_SHORT).show();
                    else if (!ad.check_email())
                        Toast.makeText(Login.this, "email invalide", Toast.LENGTH_SHORT).show();
                    else ad.loginAdmin(Login.this);
                }
            }
        });

        signup_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (type.equals("Parent") || type.equals("Prof")) {
                    Intent i1 = new Intent(Login.this, SignUpParent2.class);
                    i1.putExtra("Type",type);
                    startActivity(i1);
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
    public void loginProf(String e, String p){
        auth.signInWithEmailAndPassword(e, p).addOnCompleteListener(new OnCompleteListener<AuthResult>() {

            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    user_ID = auth.getCurrentUser().getUid();
                    Toast.makeText(Login.this, "success",Toast.LENGTH_SHORT).show();
                    Intent i2 = new Intent(Login.this, MainProf2.class);
                    i2.putExtra("user_ID",user_ID);
                    startActivity(i2);
                    finish();
                }
                else Toast.makeText(Login.this,"failure",Toast.LENGTH_SHORT).show();
            }
        });

    }
    public void loginParent(String e, String p){
        auth.signInWithEmailAndPassword(e, p).addOnCompleteListener(new OnCompleteListener<AuthResult>() {

            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    user_ID = auth.getCurrentUser().getUid();
                    Toast.makeText(Login.this, "success",Toast.LENGTH_SHORT).show();
                    Intent i2 = new Intent(Login.this, MainParent2.class);
                    i2.putExtra("user_ID",user_ID);
                    startActivity(i2);
                    finish();
                }
                else Toast.makeText(Login.this,"failure",Toast.LENGTH_SHORT).show();
            }
        });

    }
}
