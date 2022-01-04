package com.ford.loginactivities;

import static com.ford.loginactivities.LoginActivity.*;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;


public class HomeActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences ;
    private static final String PREFER_NAME = "Account details";
    TextView messageWelcome;
    Button logoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        sharedPreferences = getSharedPreferences(PREFER_NAME,0);

        messageWelcome = findViewById(R.id.uname);
        messageWelcome.setText("Welcome "+nameOfUser+"!");
        logoutButton= findViewById(R.id.logoutButton);

        logoutButton.setOnClickListener(view -> {
            sharedPreferences.edit().putBoolean("logged", false).apply();
//            keepLogged.setChecked(false);
            Intent intent2 = new Intent(HomeActivity.this,LoginActivity.class);
            startActivity(intent2);
            finish();

        });


        }
    }
