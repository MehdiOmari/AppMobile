package com.example.appmobile.PFE;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.AppCompatButton;

import android.view.View;
import android.widget.ImageButton;

import com.example.appmobile.R;

import java.util.HashMap;

public class studentadmin extends AppCompatActivity {

    private ImageButton back;
    // private AlertDialog dialog;
    //private TableLayout tableLayout;
    //private Button add_etudiant;
    private HashMap<Integer, AppCompatButton> btn_levels;

    //private LinearLayout linearLayoutclasse;
    //private int classcounter = 1;
    private String Current_ID;
    //private Administration admin = new Administration();
    //HashMap<String, Integer> id_btn_classe = new HashMap<>();
    //private boolean color_btn_classe[] = new boolean[20];
    //private AppCompatButton classe_1;
    //private ArrayList<TableauEleve> fragment_eleve;
    private Bundle bundle_classe;
    private HashMap<Integer, Liste_Classes> fragment_classe;
    private boolean btn_color_levels[] = new boolean[5];
    private Liste_Classes fragment_c;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_admin);

        back = findViewById(R.id.back);
        //tableLayout = findViewById(R.id.table_etudiants);
        //add_etudiant = findViewById(R.id.ajoute_etudiant);
        //add_classes = findViewById(R.id.add_new_classe);
        //linearLayoutclasse = findViewById(R.id.btnclasselayout);
        Current_ID = getIntent().getStringExtra("Current_ID");
        //classe_1 = findViewById(R.id.class_1);
        btn_levels = new HashMap<>(5);
        btn_levels.put(1, findViewById(R.id.buttonniveau1));
        btn_levels.put(2, findViewById(R.id.buttonniveau2));
        btn_levels.put(3, findViewById(R.id.buttonniveau3));
        btn_levels.put(4, findViewById(R.id.buttonniveau4));
        btn_levels.put(5, findViewById(R.id.buttonniveau5));


        bundle_classe = new Bundle();
        bundle_classe.putString("user_ID", Current_ID);

        btn_color_levels[0] = true;
        for (int i = 1; i < 5; i++) {
            btn_color_levels[i] = false;
        }

        fragment_classe = new HashMap<>();
        fragment_c = new Liste_Classes();
        fragment_c.setLevel("A-1");
        fragment_c.setArguments(bundle_classe);
        fragment_classe.put(1, fragment_c);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.layout_container_classe, fragment_c)
                .addToBackStack(null)
                .commit();


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btn_levels.get(1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 1; i < 5; i++) {
                    if (btn_color_levels[i]) btn_color_levels[i] = false;
                    btn_levels.get(i + 1).setBackground(AppCompatResources.getDrawable(studentadmin.this, R.drawable.btnstule));
                    btn_levels.get(1).setBackground(AppCompatResources.getDrawable(studentadmin.this, R.drawable.btnstyle));
                    Liste_Classes frg_c = new Liste_Classes();
                    frg_c.setLevel("A-1");
                    frg_c.setArguments(bundle_classe);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.layout_container_classe, frg_c)
                            .addToBackStack(null)
                            .commit();
                }
            }

        });

        btn_levels.get(2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //if(fragment_classe.get(2) == null) add_fragment_classe(2);
                for (int i = 0; i < 5; i++) {
                    if (btn_color_levels[i]) btn_color_levels[i] = false;
                    btn_levels.get(i + 1).setBackground(AppCompatResources.getDrawable(studentadmin.this, R.drawable.btnstule));
                    btn_levels.get(2).setBackground(AppCompatResources.getDrawable(studentadmin.this, R.drawable.btnstyle));
                    Liste_Classes frg_c = new Liste_Classes();
                    frg_c.setLevel("A-2");
                    frg_c.setArguments(bundle_classe);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.layout_container_classe, frg_c)
                            .addToBackStack(null)
                            .commit();
                }
            }

        });
        btn_levels.get(3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //if(fragment_classe.get(3) == null) add_fragment_classe(3);
                for (int i = 0; i < 5; i++) {
                    if (btn_color_levels[i]) btn_color_levels[i] = false;
                    btn_levels.get(i + 1).setBackground(AppCompatResources.getDrawable(studentadmin.this, R.drawable.btnstule));
                    btn_levels.get(3).setBackground(AppCompatResources.getDrawable(studentadmin.this, R.drawable.btnstyle));
                    Liste_Classes frg_c = new Liste_Classes();
                    frg_c.setLevel("A-3");
                    frg_c.setArguments(bundle_classe);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.layout_container_classe, frg_c)
                            .addToBackStack(null)
                            .commit();
                }
            }

        });
        btn_levels.get(4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //if(fragment_classe.get(4) == null) add_fragment_classe(4);
                for (int i = 0; i < 5; i++) {
                    if (btn_color_levels[i]) btn_color_levels[i] = false;
                    btn_levels.get(i + 1).setBackground(AppCompatResources.getDrawable(studentadmin.this, R.drawable.btnstule));
                    btn_levels.get(4).setBackground(AppCompatResources.getDrawable(studentadmin.this, R.drawable.btnstyle));
                    Liste_Classes frg_c = new Liste_Classes();
                    frg_c.setLevel("A-4");
                    frg_c.setArguments(bundle_classe);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.layout_container_classe, frg_c)
                            .addToBackStack(null)
                            .commit();
                }
            }

        });
        btn_levels.get(5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //if(fragment_classe.get(5) == null) add_fragment_classe(5);
                for (int i = 0; i < 4; i++) {
                    if (btn_color_levels[i]) btn_color_levels[i] = false;
                    btn_levels.get(i + 1).setBackground(AppCompatResources.getDrawable(studentadmin.this, R.drawable.btnstule));
                    btn_levels.get(5).setBackground(AppCompatResources.getDrawable(studentadmin.this, R.drawable.btnstyle));
                    Liste_Classes frg_c = new Liste_Classes();
                    frg_c.setLevel("A-5");
                    frg_c.setArguments(bundle_classe);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.layout_container_classe, frg_c)
                            .addToBackStack(null)
                            .commit();
                }
            }

        });
    }

    public void add_fragment_classe(int i) {

        Liste_Classes frg_c = new Liste_Classes();
        frg_c.setLevel("A-" + String.valueOf(i));
        frg_c.setArguments(bundle_classe);
        fragment_classe.put(i, frg_c);

    }
}

