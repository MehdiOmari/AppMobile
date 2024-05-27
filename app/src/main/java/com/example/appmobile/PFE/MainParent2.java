package com.example.appmobile.PFE;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.appmobile.R;
import com.example.appmobile.choixprofil;
import com.google.android.material.navigation.NavigationView;

public class MainParent2 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    CardView Grade;
    CardView Homework;
    CardView Remarque;
    CardView profdisponible;
    CardView edt;
    CardView absence;
    CardView attestation;
    CardView convocation;
    private String User_ID;
    public static String ID_fils1;
    public static String ID_fils2;
    public static String ID_fils3;
    public static String ID_fils4;
    public static String ID_fils5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_parent2);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout2);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer,toolbar,
                R.string.navigation_drawer_close, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        Grade = findViewById(R.id.grade);
        Homework = findViewById(R.id.homework);
        Remarque = findViewById(R.id.remarque);
        profdisponible = findViewById(R.id.profdisponible);
        edt = findViewById(R.id.edt);
        absence = findViewById(R.id.absence);
        attestation = findViewById(R.id.attestation);
        convocation = findViewById(R.id.convocation);
        User_ID = getIntent().getStringExtra("user_ID");
        ID_fils1 = getIntent().getStringExtra("ID");
        Bundle bundel = new Bundle();
        bundel.putString("user_ID",User_ID);

        Grade.setOnClickListener((v) -> {
            GradeParent gradeParent = new GradeParent();
            gradeParent.setArguments(bundel);
            getSupportFragmentManager().beginTransaction().replace(R.id.drawer_layout2, gradeParent).addToBackStack(null).commit();
        } );

        /*Homework.setOnClickListener(v -> openFragment(new homework()));
        absence.setOnClickListener(v -> openFragment(new absence()));
        Remarque.setOnClickListener(v -> openFragment(new remarque()));
        profdisponible.setOnClickListener(v -> openFragment(new profdisponible()));
        edt.setOnClickListener(v -> openFragment(new edt()));
        attestation.setOnClickListener(v -> openFragment(new certificat()));
        convocation.setOnClickListener(v -> openFragment(new convocation()));*/
    }

    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_profil) {
            Toast.makeText(this, "Home clicked", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_changerprofil) {
            Intent intent = new Intent(this, choixprofil.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.message) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("mailto:"));
            intent.putExtra(Intent.EXTRA_EMAIL, new String[] {"example@gmail.com"}); // optional, if you want to prefill the recipient
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            } }
        else if (id == R.id.nav_logout) {
            showLogoutConfirmationDialog();
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private void showLogoutConfirmationDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Logout")
                .setMessage("Are you sure you want to logout?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Continue with logout
                        Intent intent = new Intent(MainParent2.this, FirstPage.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish(); // close the current activity
                    }
                })
                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

   /* private void openFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.drawer_layout2, fragment).commit();
    }*/

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
