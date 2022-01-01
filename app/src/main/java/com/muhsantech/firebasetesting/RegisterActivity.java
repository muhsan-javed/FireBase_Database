package com.muhsantech.firebasetesting;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.muhsantech.firebasetesting.databinding.ActivityRegisterBinding;


public class RegisterActivity extends AppCompatActivity {

    ActivityRegisterBinding binding;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // EditText ==> editTextTextEmailAddress,  editTextTextPassword;
        // Button ==> btnRegisterRA

        auth = FirebaseAuth.getInstance();

        binding.btnRegisterRA.setOnClickListener(v -> {
            String email = binding.editTextTextEmailAddress.getText().toString();
            String password = binding.editTextTextPassword.getText().toString();
            
            if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)){
                Toast.makeText(getApplicationContext(), "Enter Email and Password both", Toast.LENGTH_SHORT).show();
            }
            else {
                RegisterPage(email,password);
            }
            
        });

    }

    private void RegisterPage(String email, String password) {
        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "successfully Registered", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();

                }
            }
        });

    }
}