package com.example.appmobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpParent extends AppCompatActivity {

    private Button btn_signup;
    private FirebaseAuth auth;
    private EditText e1;
    private EditText e2;
    private EditText e3;
    HashMap<String, String> emailData = new HashMap<>();
    HashMap<String, Object> passwordData = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up_parent);

        auth = FirebaseAuth.getInstance();
        e1 = findViewById(R.id.emailedit);
        e2 = findViewById(R.id.useredit);
        e3 = findViewById(R.id.passedit);
        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailsign = e1.getText().toString();
                String usernamesign = e2.getText().toString();
                String passewordsign = e3.getText().toString();

                Pattern p = Pattern.compile(".+@[a-z]+\\.[a-z]+$");
                Pattern p2 = Pattern.compile("([A-Z][a-z]+\\s)+");
                Matcher m = p.matcher(emailsign);
                Matcher m2 = p2.matcher(usernamesign);
                boolean x = false, y = false;
                if (emailsign.equals("") || usernamesign.equals("") || passewordsign.equals("")) {
                    Toast.makeText(SignUpParent.this, "le champ est vide", Toast.LENGTH_SHORT).show();
                } else y = true;
                if (!m.matches()) {
                    Toast.makeText(SignUpParent.this, "email invalide", Toast.LENGTH_SHORT).show();
                } else x = true;

                if (x && y) {
                    emailData.put("Email", emailsign);
                    passwordData.put("Password", passewordsign);
                }
            }
        });

    }


}
