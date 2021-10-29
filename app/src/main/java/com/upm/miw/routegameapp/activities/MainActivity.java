package com.upm.miw.routegameapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.upm.miw.routegameapp.R;

public class MainActivity extends AppCompatActivity {

    Button btnLogOut;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Fields
        btnLogOut = findViewById(R.id.btnLogOut);

        // Initialize Firebase Auth
        firebaseAuth = FirebaseAuth.getInstance();
        
        //ClickListeners
        btnLogOut.setOnClickListener(view -> {
           firebaseAuth.signOut();
           startActivity(new Intent(MainActivity.this, LoginActivity.class));
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if (user == null) {
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        }
    }

}