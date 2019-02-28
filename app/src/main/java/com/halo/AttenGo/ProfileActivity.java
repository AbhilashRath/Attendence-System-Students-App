package com.halo.AttenGo;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {

    RelativeLayout rellay1;
    private EditText PName,PRollNo;
    private Button PFnish;
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;


    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            rellay1.setVisibility(View.VISIBLE);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        rellay1 = (RelativeLayout) findViewById(R.id.rellay1);

        handler.postDelayed(runnable, 1700);



        databaseReference = FirebaseDatabase.getInstance().getReference("Student Info");
        firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() == null){
            finish();
            startActivity(new Intent(this,Activity_Login.class));
        }

        PName=(EditText)findViewById(R.id.pname);
        PRollNo=(EditText)findViewById(R.id.prollno);
        PFnish=(Button)findViewById(R.id.pfnish);
        PFnish.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        if (view ==PFnish){
            saveUserInfromation();
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        finish();
        startActivity(intent);

    }


    private void saveUserInfromation() {
        String Name = PName.getText().toString().trim();
        String RollNo = PRollNo.getText().toString().trim();
        Integer Chemistry = 0;
        Integer Physics = 0;
        Integer Petroleum = 0;
        Integer Maths = 0;


        UserInformation userInformation =new UserInformation(Name,RollNo,Chemistry,Physics,Petroleum,Maths);

        FirebaseUser user = firebaseAuth.getCurrentUser();

        databaseReference.child(user.getUid()).setValue(userInformation);

        Toast.makeText(this,"Information Saved",Toast.LENGTH_LONG).show();
        user = null;
        startActivity(new Intent(this,Activity_Login.class));

    }
}
