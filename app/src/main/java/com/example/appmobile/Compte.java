package com.example.appmobile;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Compte {
    private String id_compte;
    private String Name;
    private String Email;
    private String Password;
    HashMap<String, Object> NameData = new HashMap<>();
    HashMap<String, Object> emailData = new HashMap<>();
    HashMap<String, Object> passwordData = new HashMap<>();
    public void setName(String name){
        this.Name=name;
    }

    public String getName(){
        return this.Name;
    }
    public void setEmail(String email){
        this.Email=email;
    }

    public String getEmail(){
        return this.Email;
    }
    public void setPassword(String password) {
        this.Password = password;
    }

    public String getPassword() {
        return this.Password;
    }

    public void setId_compte(String id_compte) {
        this.id_compte = id_compte;
    }
    public String getId_compte(){
        return this.id_compte;
    }

    public boolean check_name(){
        Pattern p = Pattern.compile("([A-Z][a-z]+\\s)+");
        Matcher m = p.matcher(this.Name);
        if (m.matches()) return true;
        else return false;
    }
    public boolean check_email(){
        Pattern p = Pattern.compile(".+@[a-z]+\\.[a-z]+$");
        Matcher m = p.matcher(this.Email);
        if (m.matches()) return true;
        else return false;
    }

    public void setNameData() {
        this.NameData.put("Name",getName());
    }

    public void setEmailData() {
        this.emailData.put("Email",getEmail());
    }

    public void setPasswordData() {
        this.passwordData.put("Password",getPassword());
    }


}
