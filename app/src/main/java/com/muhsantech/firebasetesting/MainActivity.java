package com.muhsantech.firebasetesting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.muhsantech.firebasetesting.databinding.ActivityMainBinding;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Objects.requireNonNull(getSupportActionBar()).hide();
        binding.btnRegisterA.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, RegisterActivity.class)));
        binding.btnLoginA.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, FirebaseCloudStoreActivity.class)));


        


    }
}