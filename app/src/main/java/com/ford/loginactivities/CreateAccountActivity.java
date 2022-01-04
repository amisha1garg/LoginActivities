package com.ford.loginactivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateAccountActivity extends AppCompatActivity {

    EditText userName, email, passwd,confPasswd;
    Button buttonCreAcc;
    SharedPreferences sharedPreferences;
    Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
//        Intent intent = getIntent();
        userName = findViewById(R.id.personName);
        email = findViewById(R.id.emailAddress);
        passwd = findViewById(R.id.password1);
        confPasswd = findViewById(R.id.password2);
        buttonCreAcc = findViewById(R.id.createAccButton);

        sharedPreferences = getSharedPreferences("Account details",MODE_PRIVATE);
        editor = sharedPreferences.edit();

        buttonCreAcc.setOnClickListener(view -> {
            String name =userName.getText().toString();
            String email1 = email.getText().toString();
            String pass1 = passwd.getText().toString();
            String pass2 = confPasswd.getText().toString();

            String emailRegex = "^(.+)@(.+)$";
            Pattern emailPattern = Pattern.compile(emailRegex);
            Matcher emailMatcher = emailPattern.matcher(email1);

            String passwordRegex = "^(?=.*[0-9])"
                    + "(?=.*[a-z])(?=.*[A-Z])"
                    + "(?=.*[@#$%^&+=])"
                    + "(?=\\S+$).{8,20}$";
            Pattern passwordPattern = Pattern.compile(passwordRegex);
            Matcher passwordMatcher = passwordPattern.matcher(pass1);

            if (name.length()<1 || name.length()>15){
                Toast.makeText(CreateAccountActivity.this, "Invalid Username Length(Length>0 or Length<15)", Toast.LENGTH_SHORT).show();
            }
            else if (!(emailMatcher.matches())){
                Toast.makeText(CreateAccountActivity.this,"Invalid E-Mail" , Toast.LENGTH_SHORT).show();
            }
            else if (!(passwordMatcher.matches())){
                Toast.makeText(CreateAccountActivity.this,"Invalid Password" , Toast.LENGTH_SHORT).show();
            }
            else if (!pass1.equals(pass2)){
                Toast.makeText(CreateAccountActivity.this, "Passwords mismatch", Toast.LENGTH_SHORT).show();
            }
            else{
                editor.putString("Name",name);
                editor.putString("E-mail",email1);
                editor.putString("txtPassword",pass1);
                editor.commit();
                Intent intent1 = new Intent(CreateAccountActivity.this,LoginActivity.class);
                startActivity(intent1);
                finish();
            }
        });
    }
}