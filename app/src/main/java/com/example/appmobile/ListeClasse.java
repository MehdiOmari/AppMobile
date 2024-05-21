package com.example.appmobile;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.constraintlayout.widget.Constraints;

public class ListeClasse extends AppCompatActivity {

    private LevelsClasses l;
    private TextView t;
    private Button btn;
    private int ClassCounter = 1;
    LinearLayout linearLayout;
    private String level;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.liste_classe);

        t = findViewById(R.id.level_class);
        linearLayout = findViewById(R.id.container);
        btn = findViewById(R.id.addclasse);

        def_texte();
        level = t.getText().toString();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddNewCard();
                if (ClassCounter == 2){
                    String s = t.getText().toString() + "1";
                    t.setText(s);
                }
            }
        });


    }

    public void def_texte(){
        if(l.check_1M) {
            t.setText("1M");
            l.check_1M = false;
        }
        else if(l.check_2M) {
            t.setText("2M");
            l.check_2M = false;
        }
        else if(l.check_3M) {
            t.setText("3M");
            l.check_3M = false;
        }
        else {
            t.setText("4M");
            l.check_4M = false;
        }
    }

    private void AddNewCard() {
        ClassCounter++;
        CardView c;
        View view = getLayoutInflater().inflate(R.layout.card_classe, null);

        TextView txt = view.findViewById(R.id.level_class_);
        String x = level + String.valueOf(ClassCounter);
        txt.setText(x);

        c = view.findViewById(R.id.class_M);
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ListeClasse.this, Creer_Liste_Eleve.class);
                startActivity(i);
            }
        });
        linearLayout.addView(view);

    }


}