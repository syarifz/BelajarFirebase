package com.ahliandroid.belajarfirebase;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class Authentication extends AppCompatActivity {

    EditText etEmail, etPassword, etFullName;
    RadioGroup rgAuthentication;
    Button btnSignIn, btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);

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
    }
}
