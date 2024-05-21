package com.example.appmobile;

import static androidx.core.content.ContextCompat.startActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class Administration extends Compte {
    ArrayList<Classe> list_classes = new ArrayList<>();
    ArrayList<Professeur> list_Prof = new ArrayList<>();
    FirebaseAuth auth = FirebaseAuth.getInstance();
    HashMap<String, Object> classeData = new HashMap<>();
    private DatabaseReference dr;
    private String email_profile;

    public void signAdmin(final Activity activity) {
        auth.createUserWithEmailAndPassword(this.getEmail(),this.getPassword()).addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    String userId = auth.getCurrentUser().getUid();
                    Administration.this.setId_compte(userId);

                    FirebaseDatabase.getInstance().getReference().child("Administration").child(userId).setValue(NameData);
                    FirebaseDatabase.getInstance().getReference().child("Administration").child(userId).updateChildren(emailData);
                    FirebaseDatabase.getInstance().getReference().child("Administration").child(Administration.this.getId_compte()).updateChildren(passwordData);

                    Intent i = new Intent(activity,mainadmin2.class);
                    i.putExtra("Current_ID",Administration.this.getId_compte());
                    activity.startActivity(i);
                    activity.finish();
                }
                else Toast.makeText(activity, "failed Auth", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void loginAdmin(final Activity activity){

        auth.signInWithEmailAndPassword(this.getEmail(), this.getPassword()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    String userId = auth.getCurrentUser().getUid();
                    Administration.this.setId_compte(userId);
                    Toast.makeText(activity,"success",Toast.LENGTH_SHORT).show();
                    Intent i2 = new Intent(activity, mainadmin2.class);
                    i2.putExtra("Current_ID",Administration.this.getId_compte());
                    activity.startActivity(i2);
                    activity.finish();
                }
                else Toast.makeText(activity,"failure",Toast.LENGTH_SHORT).show();
            }
        });

    }
    public void logout(final Activity activity) {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(activity, Login.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        activity.startActivity(intent);
        activity.finish();
    }

}



