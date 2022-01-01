package com.muhsantech.firebasetesting;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.muhsantech.firebasetesting.databinding.ActivityHomeBinding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class HomeActivity extends AppCompatActivity {

    ActivityHomeBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Objects.requireNonNull(getSupportActionBar()).hide();

        // EditText ==> editTextTextPersonName,  editTextNumber;
        // Button ==> btnSaveData, btnShow



        binding.btnSavaDataFBCS.setOnClickListener(v -> {

//            FirebaseDatabase.getInstance().getReference().child("message").setValue("Ram");

            // Write a message to the database
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference myRef = database.getReference("message");
//        myRef.setValue("Hello, World!");

            // Multi Data Save
            HashMap<String, Object > map = new HashMap<>();
            map.put("name",binding.edName.getText().toString());
            map.put("age", binding.edAge.getText().toString());
            FirebaseDatabase.getInstance().getReference().child("user").push().setValue(map);


            Toast.makeText(getApplicationContext(), "save data", Toast.LENGTH_SHORT).show();
        });


        binding.btnShowDataFBCS.setOnClickListener(v -> {

            ArrayList<String> arrayList = new ArrayList<>();
//            ArrayAdapter adapter = new ArrayAdapter(HomeActivity.this,R.layout.items ,arrayList);

            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayList);

            binding.listViewFBCS.setAdapter(adapter);

            FirebaseDatabase.getInstance().getReference().child("user").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()){
                        arrayList.clear();
                        for (DataSnapshot snapshot1 : snapshot.getChildren()){
                            user i = snapshot1.getValue(user.class);
                            String t = "Name: "+i.getName() + "   Age: " + i.getAge();
                            arrayList.add(t);
                        }
                        adapter.notifyDataSetChanged();
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        });

    }
}