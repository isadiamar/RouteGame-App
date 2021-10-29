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

public class RegistrerActivity extends AppCompatActivity {

    EditText etEmailInput, etPasswordInput;
    TextView tvLoginHere;
    Button btnSignUp;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrer);

        //Fields
        etEmailInput = findViewById(R.id.etEmailInput);
        etPasswordInput = findViewById(R.id.etPasswordInput);
        btnSignUp = findViewById(R.id.btnSignUp);
        tvLoginHere = findViewById(R.id.tvLoginHere);

        //Firebase Auth
        firebaseAuth = FirebaseAuth.getInstance();

        //ClickListeners
        tvLoginHere.setOnClickListener(view -> {
            startActivity(new Intent(RegistrerActivity.this, LoginActivity.class));
        });

        btnSignUp.setOnClickListener(view -> {
            createUser();
        });
    }


    public boolean isFormValidated() {
        return Validation.validate(etEmailInput) && Validation.validate(etPasswordInput);
    }

    private void createUser() {
        String email = etEmailInput.getText().toString();
        String password = etPasswordInput.getText().toString();

        if (isFormValidated()) {
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(RegistrerActivity.this, "User register successfully", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(RegistrerActivity.this, LoginActivity.class));
                            } else {
                                new AuthAlertDialog().show(getSupportFragmentManager(), "ALERT_DIALOG");
                            }
                        }
                    });
        }
    }

}