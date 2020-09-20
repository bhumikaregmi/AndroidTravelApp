package com.example.yourdestination;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {
    Toolbar toolbar;
    EditText mFullName , mEmail, mPassword, mPhone;
    Button mRegisterBtn;
    TextView  mLoginBtn;
    FirebaseAuth fAuth;
    ProgressBar mProgress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitle("Registration Form");

        mFullName = findViewById(R.id.name);
        mEmail = findViewById(R.id.email);
        mPassword = findViewById(R.id.password);
        mPhone = findViewById(R.id.phone);
        mRegisterBtn=findViewById(R.id.register);
        mLoginBtn= findViewById(R.id.account);

        fAuth = FirebaseAuth.getInstance();
        mProgress = findViewById(R.id.bar2);


        mRegisterBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();

                        if(TextUtils.isEmpty(email)){
                            mEmail.setError("Email is required.");
                            return;
                        }

                        if(TextUtils.isEmpty(password)){
                            mPassword.setError("Password is required.");
                            return;
                        }
                        if (password.length()<6){
                            mPassword.setError("Password must be >=6 Characters.");
                            return;
                        }
                        mProgress.setVisibility(View.VISIBLE);

                fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener
                        (new OnCompleteListener<AuthResult>(){
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()){
                                    Toast.makeText(Register.this ,"User created",Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getApplicationContext(), Addplaces.class));
                                }else {
                                    Toast.makeText(Register.this ,"Error!" +
                                            task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                                    mProgress.setVisibility(View.GONE);
                                }
                            }

                        });
                mLoginBtn.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getApplicationContext(), Login.class));
                    }
                });
            }

        });

    }
}


