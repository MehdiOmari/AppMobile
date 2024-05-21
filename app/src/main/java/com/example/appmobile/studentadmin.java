package com.example.appmobile;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TextView;

public class studentadmin extends AppCompatActivity {

    private ImageButton back;
    private AlertDialog dialog;
    private TableLayout tableLayout;
    private Button add_etudiant;
    private AppCompatButton add_classes;

    private NestedScrollView scrollView;
    private LinearLayout linearLayoutclasse;
    private int classcounter = 1;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_admin);

        back = findViewById(R.id.back);
        tableLayout = findViewById(R.id.table_etudiants);
        add_etudiant = findViewById(R.id.ajoute_etudiant);
        scrollView = findViewById(R.id.scrollView_container);
        add_classes = findViewById(R.id.add_new_classe);
        linearLayoutclasse = findViewById(R.id.btnclasselayout);

        build_dialogue();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        add_etudiant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });

        add_classes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    add_new_btn_classe();
            }
        });
    }

    private void build_dialogue(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.taper_nom, null);

        builder.setView(view);
        EditText e1 = view.findViewById(R.id.nameEdit);
        EditText e2 = view.findViewById(R.id.dateEdit);
        EditText e3 = view.findViewById(R.id.lieuEdit);

        builder.setTitle("Informations").setPositiveButton("ajouter", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String s1 = e1.getText().toString();
                String s2 = e2.getText().toString();
                String s3 = e3.getText().toString();
                addrow(s1,s2,s3);
            }
        }).setNegativeButton("annuler", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        dialog = builder.create();
    }

    private void addrow(String name, String date, String lieu){
        View v = getLayoutInflater().inflate(R.layout.etudiant_row, null);
        TextView t1 = v.findViewById(R.id.etudianttxt);
        TextView t2 = v.findViewById(R.id.datedenaissancettxt);
        TextView t3 = v.findViewById(R.id.lieudenaissancetxt);

        t1.setText(name);
        t2.setText(date);
        t3.setText(lieu);

        tableLayout.addView(v);


    }
    private void add_new_btn_classe(){
        classcounter++;
        AppCompatButton button = new AppCompatButton(this);
        button.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));

        int widthindp = 120;
        float density = getResources().getDisplayMetrics().density;
        int widthinpx = (int) (widthindp * density);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                widthinpx,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );

        params.setMargins(10, 10, 10, 10);
        button.setLayoutParams(params);

        button.setText("Classe" + String.valueOf(classcounter));
        button.setTextColor(Color.parseColor("#1261A0")); 
        button.setBackground(ContextCompat.getDrawable(this, R.drawable.btnstule));
        button.setTypeface(null, Typeface.BOLD);

        int index = linearLayoutclasse.indexOfChild(add_classes);
        linearLayoutclasse.addView(button, index);
    }
}
