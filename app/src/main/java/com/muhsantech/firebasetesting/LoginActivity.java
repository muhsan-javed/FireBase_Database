package com.muhsantech.firebasetesting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.muhsantech.firebasetesting.databinding.ActivityLoginBinding;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding binding;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Objects.requireNonNull(getSupportActionBar()).hide();

        // EditText ==> editTextTextEmailAddress,  editTextTextPassword;
        // Button ==> btnLoginL

        auth = FirebaseAuth.getInstance();
        binding.btnLoginL.setOnClickListener(v -> {
            String email = binding.editTextTextEmailAddress.getText().toString();
            String password = binding.editTextTextPassword.getText().toString();

            if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)){
                Toast.makeText(getApplicationContext(), "Enter Email and Password both", Toast.LENGTH_SHORT).show();
            }
            else {
                LoginPage(email,password);
                startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                finish();
            }


        });
    }

    private void LoginPage(String email, String password) {
        auth.signInWithEmailAndPassword(email, password).addOnSuccessListener(LoginActivity.this, new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Toast.makeText(getApplicationContext(), "Successfully LogIn", Toast.LENGTH_SHORT).show();
            }
        });

    }


}