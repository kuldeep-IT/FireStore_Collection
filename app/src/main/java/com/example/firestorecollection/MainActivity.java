package com.example.firestorecollection;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class MainActivity extends AppCompatActivity {

    TextView title,thoughts,showData;
    Button save,update;

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    CollectionReference collectionReference = db.collection("Collection Data");



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        title = findViewById(R.id.title);
        thoughts = findViewById(R.id.thoughts);
        showData = findViewById(R.id.showData);
        save = findViewById(R.id.save);
        update = findViewById(R.id.update);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String t1 = title.getText().toString().trim();
                String t2 = thoughts.getText().toString().trim();

                Data data = new Data();

                data.setTitle(t1);
                data.setThoughts(t2);

                collectionReference.add(data)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(MainActivity.this, "Sucess!", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                    }
                });



            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                collectionReference.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                        String allData = "";

                        for (QueryDocumentSnapshot querySnapshot : queryDocumentSnapshots)
                        {

                            Data data = querySnapshot.toObject(Data.class);

                            allData += "Title: " + data.getTitle() +"\n"+ "Thoughts: "+data.getThoughts() +"\n\n\n";



                            Log.d("MainActivityTAG",querySnapshot.getId());

                        }

                        showData.setText(allData);

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });


    }
}
