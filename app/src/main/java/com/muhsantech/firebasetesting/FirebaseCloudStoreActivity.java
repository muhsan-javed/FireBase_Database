package com.muhsantech.firebasetesting;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.WriteBatch;
import com.muhsantech.firebasetesting.databinding.ActivityFirebaseCloudStoreBinding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class FirebaseCloudStoreActivity extends AppCompatActivity {

    ActivityFirebaseCloudStoreBinding binding;
    private List<String> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFirebaseCloudStoreBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Objects.requireNonNull(getSupportActionBar()).hide();
        // EditText ==> edName,  edAge;
        // Button ==> btnSaveDataFS, btnShowFS

        binding.btnSave.setOnClickListener(v -> {
            HashMap<String, String> map = new HashMap<>();
            map.put("name", binding.Name.getText().toString());
            map.put("age", binding.Age.getText().toString());

//           // FirebaseFirestore.getInstance().collection("user")
//                    .document("personal details").set(map).addOnCompleteListener(task ->
//                            Toast.makeText(getApplicationContext(), "Inserted", Toast.LENGTH_SHORT).show());

            FirebaseFirestore.getInstance().collection("user").add(map).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                @Override
                public void onComplete(@NonNull Task<DocumentReference> task) {
                    Toast.makeText(getApplicationContext(), "Inserted", Toast.LENGTH_SHORT).show();
                }
            });


        });

        binding.btnShow.setOnClickListener(v -> {

            FirebaseFirestore.getInstance().collection("user").addSnapshotListener(new EventListener<QuerySnapshot>() {
                @Override
                public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                    list.clear();
                    for (DocumentSnapshot documentSnapshot : value){

                        list.add("Name: " +documentSnapshot.getString("name") + "   Age: " + documentSnapshot.getString("age"));
                    }
                    ArrayAdapter adapter = new ArrayAdapter(FirebaseCloudStoreActivity.this, android.R.layout.simple_list_item_1,list);
                    adapter.notifyDataSetChanged();
                    binding.myListView.setAdapter(adapter);
                }
            });

        });


        binding.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Delete url only
              /*  FirebaseFirestore.getInstance().collection("user").document(
                        "KxwHlR5uK6XoMQoIbVnK").delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(getApplicationContext(), "Delete", Toast.LENGTH_SHORT).show();
                    }
                });*/


            /// Search and Delete
                String searchAgeDelete = binding.Age.getText().toString();
                FirebaseFirestore.getInstance().collection("user").whereEqualTo("age",searchAgeDelete).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        WriteBatch writeBatch = FirebaseFirestore.getInstance().batch();
                        List<DocumentSnapshot>  dSList = queryDocumentSnapshots.getDocuments();

                        for (DocumentSnapshot snapshot : dSList){
                            writeBatch.delete(snapshot.getReference());
                        }
                        writeBatch.commit().addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(getApplicationContext(), "Delete", Toast.LENGTH_SHORT).show();

                            }
                        });

                    }
                });

            }
        });


    }
}








