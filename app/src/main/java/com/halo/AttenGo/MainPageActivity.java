package com.halo.AttenGo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainPageActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView MSubjectPhysics,MSubjectChemistry,MSubjectPetroleum,MSubjectMaths;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        MSubjectChemistry=(ImageView)findViewById(R.id.subchemistry);
        MSubjectPhysics=(ImageView)findViewById(R.id.subphysics);
        MSubjectPetroleum=(ImageView)findViewById(R.id.subpetroleum);
        MSubjectMaths=(ImageView)findViewById(R.id.submaths);
        MSubjectChemistry.setOnClickListener(this);
        MSubjectPhysics.setOnClickListener(this);
        MSubjectPetroleum.setOnClickListener(this);
        MSubjectMaths.setOnClickListener(this);

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        finish();
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        if (view == MSubjectChemistry){
            Intent intent = new Intent(MainPageActivity.this, SubjectActivity.class);
            intent.putExtra("Selected Subject", "Chemistry");
            startActivity(intent);
        }
        if (view == MSubjectPhysics){
            Intent intent = new Intent(MainPageActivity.this, SubjectActivity.class);
            intent.putExtra("Selected Subject", "Physics");
            startActivity(intent);
        }
        if (view == MSubjectPetroleum){
            Intent intent = new Intent(MainPageActivity.this, SubjectActivity.class);
            intent.putExtra("Selected Subject", "Petroleum");
            startActivity(intent);
        }
        if (view == MSubjectMaths){
            Intent intent = new Intent(MainPageActivity.this, SubjectActivity.class);
            intent.putExtra("Selected Subject", "Maths");
            startActivity(intent);
        }
    }
}
