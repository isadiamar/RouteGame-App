package com.upm.miw.routegameapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.upm.miw.routegameapp.AuthAlertDialog;
import com.upm.miw.routegameapp.R;
import com.upm.miw.routegameapp.Validation;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private EditText etInputEmail, etInputPassword;
    private Button btnLogIn;
    private TextView tvSignupHere;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Fields
        etInputEmail = findViewById(R.id.etEmailInput);
        etInputPassword = findViewById(R.id.etPasswordInput);
        btnLogIn = findViewById(R.id.btnLogIn);
        tvSignupHere = findViewById(R.id.tvSignupHere);

        // Initialize Firebase Auth
        firebaseAuth = FirebaseAuth.getInstance();

        //Click Listeners
        btnLogIn.setOnClickListener(view -> {
            loginUser();
        });

        tvSignupHere.setOnClickListener(view -> {
            startActivity(new Intent(LoginActivity.this, RegistrerActivity.class));
        });

    }

    public boolean isFormValidated() {
        return Validation.validate(etInputEmail) && Validation.validate(etInputPassword);
    }

    private void loginUser() {
        String email = etInputEmail.getText().toString();
        String password = etInputPassword.getText().toString();

        if (isFormValidated()) {
            firebaseAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(LoginActivity.this, "User logged in successfully", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            } else {
                                Toast.makeText(LoginActivity.this, "Log in error", Toast.LENGTH_SHORT).show();
                                new AuthAlertDialog().show(getSupportFragmentManager(), "ALERT_DIALOG");

                            }
                        }
                    });
        }

    }


}