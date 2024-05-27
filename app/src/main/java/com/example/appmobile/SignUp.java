package com.example.appmobile;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class SignUp extends AppCompatActivity {

    private Button btn_signup;
    private EditText e1;
    private EditText e2;
    private EditText e3;
    FirebaseAuth auth = FirebaseAuth.getInstance();
    private Administration admin = new Administration();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.sign_up);


        btn_signup = findViewById(R.id.button);
        e1 = findViewById(R.id.useredit);
        e2 = findViewById(R.id.emailedit);
        e3 = findViewById(R.id.passedit);


        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                admin.setName(e1.getText().toString());
                admin.setEmail(e2.getText().toString());
                admin.setPassword(e3.getText().toString());

                if (admin.getName().isEmpty()) Toast.makeText(SignUp.this, "name empty", Toast.LENGTH_SHORT).show();
                else if (admin.getEmail().isEmpty()) Toast.makeText(SignUp.this, "email empty", Toast.LENGTH_SHORT).show();
                else if (admin.getPassword().isEmpty()) Toast.makeText(SignUp.this, "password empty", Toast.LENGTH_SHORT).show();
                else if (!admin.check_name()) Toast.makeText(SignUp.this, "invalid name", Toast.LENGTH_SHORT).show();
                else if(!admin.check_email()) Toast.makeText(SignUp.this, "invalid email", Toast.LENGTH_SHORT).show();
                else {
                    admin.setNameData();
                    admin.setEmailData();
                    admin.setPasswordData();
                    admin.signAdmin(SignUp.this);
                }
            }
        });

    }

}

