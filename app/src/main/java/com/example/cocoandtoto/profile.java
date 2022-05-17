package com.example.cocoandtoto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;



import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class profile extends AppCompatActivity {


    private TextView Name;
    private TextView password;
    private TextView address;
    private TextView Email;
    private TextView phoneno;

    private Button  UpdateButton,DeleteButton;

    DatabaseReference user;
    String userID;

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://coco1-baa44-default-rtdb.firebaseio.com/");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        Name = findViewById(R.id.name);
        password = findViewById(R.id.password);
        address = findViewById(R.id.address);
        Email = findViewById(R.id.Email);
        phoneno = findViewById(R.id.phoneno);

        UpdateButton = findViewById(R.id.UpdateButton);
        DeleteButton = findViewById(R.id.DeleteButton);

        Intent intent = getIntent();
        final String name = String.valueOf(intent.getStringExtra("Name"));

        databaseReference.child("Profile").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String UsernameFromDB = snapshot.child(name).child("Name").getValue(String.class);
                String passwordFromDB = snapshot.child(name).child("pass").getValue(String.class);
                String addressFromDB = snapshot.child(name).child("adress").getValue(String.class);
                String contactFromDB = snapshot.child(name).child("contact").getValue(String.class);
                String mailFromDB = snapshot.child(name).child("mail").getValue(String.class);

                Name.setText(UsernameFromDB);
                password.setText(passwordFromDB);
                address .setText(addressFromDB );
                Email.setText(mailFromDB);
                phoneno.setText(contactFromDB);

                UpdateButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent intent = new Intent(getApplicationContext(),edit_profile.class);

                        intent.putExtra( "Name",UsernameFromDB);
                        startActivity(intent);
                    }
                });

                DeleteButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        user = databaseReference.child(UsernameFromDB);
                        userID = user.getKey();
                        databaseReference.child("Profile").child(userID).setValue(null);
                        Intent intent = new Intent(profile.this,home.class);
                        startActivity(intent);
                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });




    }
}