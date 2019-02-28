package com.halo.AttenGo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText SUername,SPassword,SCPassword;
    private TextView SLogIn;
    private Button SSignUp;

    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    RelativeLayout rellay1, rellay2;

    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            rellay1.setVisibility(View.VISIBLE);
            rellay2.setVisibility(View.VISIBLE);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        rellay1 = (RelativeLayout) findViewById(R.id.rellay1);
        rellay2 = (RelativeLayout) findViewById(R.id.rellay2);

        handler.postDelayed(runnable, 600);
        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        SUername = (EditText)findViewById(R.id.susername);
        SPassword = (EditText)findViewById(R.id.spassword);
        SLogIn = (TextView)findViewById(R.id.slogin);
        SSignUp = (Button)findViewById(R.id.ssignup);
        SCPassword = (EditText)findViewById(R.id.scpassword);
        SSignUp.setOnClickListener(this);
        SLogIn.setOnClickListener(this);
    }

    private void createAccount(){
        String username = SUername.getText().toString().trim();
        String password = SPassword.getText().toString().trim();
        String conpassword = SCPassword.getText().toString().trim();

        if (TextUtils.isEmpty(username)){
            Toast.makeText(this, "Please Enter Username",Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(password)){
            Toast.makeText(this, "Please Enter Password",Toast.LENGTH_LONG).show();
            return;
        }
        if (!TextUtils.equals(password,conpassword)){
            Toast.makeText(this,"Password Don't Match",Toast.LENGTH_LONG).show();
            return;
        }
        progressDialog.setMessage("Signing Up...");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(username, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()){
                            finish();
                            Toast.makeText(SignUpActivity.this,"Sign Up Successful",Toast.LENGTH_LONG).show();
                            startActivity(new Intent(SignUpActivity.this,ProfileActivity.class));
                        }
                        else {
                            Toast.makeText(SignUpActivity.this,"Sign Up Unsuccessful Please Try Again",Toast.LENGTH_LONG).show();
                        }

                    }
                });

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
        if (view == SSignUp){
            createAccount();
        }
        if (view == SLogIn){
            startActivity(new Intent(SignUpActivity.this,Activity_Login.class));
        }

    }
}
