package com.ahliandroid.belajarfirebase;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Authentication extends AppCompatActivity {

    EditText etEmail, etPassword, etFullName;
    RadioGroup rgAuthentication;
    Button btnSignIn, btnSignUp;

    FirebaseDatabase mDatabase;
    DatabaseReference mRef, mRoot;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);

        mDatabase = FirebaseDatabase.getInstance();
        mRef = mDatabase.getReference();
        mAuth = FirebaseAuth.getInstance();

        etEmail = (EditText) findViewById(R.id.et_email);
        etPassword = (EditText) findViewById(R.id.et_password);
        etFullName = (EditText) findViewById(R.id.et_fullname);

        rgAuthentication = (RadioGroup) findViewById(R.id.rg_authentication);

        btnSignIn = (Button) findViewById(R.id.btn_signin);
        btnSignUp = (Button) findViewById(R.id.btn_signup);

        rgAuthentication.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkId) {
                RadioButton rb = (RadioButton) findViewById(checkId);
                if(rb.getText().toString().equals("Sign In")) {
                    etFullName.setVisibility(View.GONE);
                    btnSignUp.setVisibility(View.GONE);
                    btnSignIn.setVisibility(View.VISIBLE);
                } else if (rb.getText().toString().equals("Sign Up")){
                    etFullName.setVisibility(View.VISIBLE);
                    btnSignIn.setVisibility(View.GONE);
                    btnSignUp.setVisibility(View.VISIBLE);
                }
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SignUp();
            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });
    }

    private void signIn() {
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();

        if(TextUtils.isEmpty(email)) {
            etEmail.setError("Email is required!");
        } else if(TextUtils.isEmpty(password)) {
            etPassword.setError("Password is required!");
        } else {
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()) {
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                finish();
                            } else {
                                Toast.makeText(getApplicationContext(), "Sign In failed!",Toast.LENGTH_LONG).show();
                            }

                        }
                    });
        }
    }

    private void SignUp() {
        final String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();
        final String fullName = etFullName.getText().toString();

        if(TextUtils.isEmpty(email)) {
          etEmail.setError("Email is required!");
        } else if(TextUtils.isEmpty(password)) {
            etPassword.setError("Password is required!");
        } else if(TextUtils.isEmpty(fullName)) {
            etFullName.setError("FullName is required!");
        } else {
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()) {
                                String userId = task.getResult().getUser().getUid();
                                writeNewUser(userId, email, fullName);
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                finish();
                            } else {
                                Toast.makeText(getApplicationContext(), "Sign Up failed!",Toast.LENGTH_LONG).show();
                            }

                        }
                    });
        }
    }

    private void writeNewUser(String userId, String email, String fullName) {
        User user = new User(email, fullName);
        mRoot = mRef.child("users").child(userId);
        mRoot.setValue(user);
    }
}
