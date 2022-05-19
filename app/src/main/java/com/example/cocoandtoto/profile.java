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
    private Button  UpdateButton,DeleteButton ,HomeButton;

    private TextView bName ,twn ,pAccept,pWatch,pLeft,pDays,pEme,pContact;
    private  Button pUpdateButton ,pDeleteButton;

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
       HomeButton = findViewById(R.id.pHomeButton);

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

        bName = findViewById(R.id.bName);
        twn = findViewById(R.id.twn);
        pAccept = findViewById(R.id.pAccept);
        pWatch = findViewById(R.id.pWatch);
        pLeft = findViewById(R.id.pLeft);
        pDays = findViewById(R.id. pDays);
        pEme = findViewById(R.id. pEme);
        pContact = findViewById(R.id.pContact);

        pUpdateButton = findViewById(R.id.pUpdateButton);
        pDeleteButton = findViewById(R.id.pDeleteButton);

        //Intent intent = getIntent();
        final String b_name = String.valueOf(intent.getStringExtra("bName"));

        databaseReference.child("Dataset").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String BoardingNameFromDB = snapshot.child(b_name).child("bName").getValue(String.class);
                String townFromDB = snapshot.child(b_name).child("twn").getValue(String.class);
                String acceptFromDB = snapshot.child(b_name).child("pAccept").getValue(String.class);
                String watchFromDB = snapshot.child(b_name).child("pWatch").getValue(String.class);
                String leftFromDB = snapshot.child(b_name).child("pLeft").getValue(String.class);
                String daysFromDB = snapshot.child(b_name).child("pDays").getValue(String.class);
                String emergencyFromDB = snapshot.child(b_name).child("pEme").getValue(String.class);
                String ContactFromDB = snapshot.child(b_name).child("pContact").getValue(String.class);

                bName.setText(BoardingNameFromDB);
                twn.setText(townFromDB);
                pAccept .setText(acceptFromDB);
                pWatch.setText(watchFromDB );
                pLeft.setText(leftFromDB);
                pDays.setText(daysFromDB);
                pEme.setText(emergencyFromDB);
                pContact.setText(ContactFromDB);

                UpdateButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent intent = new Intent(getApplicationContext(),edit_form_2.class);

                        intent.putExtra( "bName",BoardingNameFromDB);
                        startActivity(intent);
                    }
                });

                DeleteButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        user = databaseReference.child(BoardingNameFromDB);
                        userID = user.getKey();
                        databaseReference.child("Dataset").child(userID).setValue(null);
                        Intent intent = new Intent(profile.this,home.class);
                        startActivity(intent);
                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });

        HomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(profile.this, home.class);
                startActivity(intent);
            }
        });
    }


}