package com.ford.loginactivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    Button buttonLogin;
    Button buttonCreateAcc;

    EditText textEmail, txtPassword;
    public static CheckBox keepLogged;
    private SharedPreferences sharedPreferences;
    private static final String PREFER_NAME = "Account details";
    public static String emailAddr;
    public static String nameOfUser;
    public static String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        textEmail = findViewById(R.id.eMail);
        txtPassword = findViewById(R.id.password);
        buttonLogin = findViewById(R.id.loginButton);
        keepLogged = findViewById(R.id.checkBox);
        buttonCreateAcc = findViewById(R.id.createAccountButton);

        sharedPreferences = getSharedPreferences(PREFER_NAME, MODE_PRIVATE);

        if (sharedPreferences.contains("Name"))
        {
            nameOfUser = sharedPreferences.getString("Name","");
        }

//        If Keep me logged in is checked and user logged in
        if (sharedPreferences.getBoolean("logged",false)){
            Intent intent1 = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(intent1);
            finish();
        }

        buttonCreateAcc.setOnClickListener(view -> {
            Intent intent1 = new Intent(LoginActivity.this, CreateAccountActivity.class);
            startActivity(intent1);
            finish();
//                Toast.makeText(getApplicationContext(), "Login failed", Toast.LENGTH_SHORT).show();
        });

        buttonLogin.setOnClickListener(view -> {

        emailAddr = textEmail.getText().toString();
        password = txtPassword.getText().toString();

        if(emailAddr.trim().length() > 0 && password.trim().length() > 0){
            String uName = null;
            String uPassword =null;

            if (sharedPreferences.contains("E-mail"))
            {
                uName = sharedPreferences.getString("E-mail", "");

            }

            if (sharedPreferences.contains("txtPassword"))
            {
                uPassword = sharedPreferences.getString("txtPassword", "");

            }

            if(emailAddr.equals(uName) && password.equals(uPassword)){

                Intent i = new  Intent(getApplicationContext(), HomeActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);

                if (keepLogged.isChecked()) {

                    sharedPreferences.edit().putBoolean("logged", true).apply();
                }

                finish();

            }else{

                Toast.makeText(getApplicationContext(),"Please create account",Toast.LENGTH_LONG).show();

            }
        }else{

            Toast.makeText(getApplicationContext(),"Please enter username and password",Toast.LENGTH_LONG).show();

        }

    });
    }
}