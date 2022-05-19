package com.example.cocoandtoto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class edit_profile extends AppCompatActivity {

    private TextView backBtn;
    private Button ConformButton;

    DatabaseReference user;
    String userID;

    edit_profile binding;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://coco1-baa44-default-rtdb.firebaseio.com/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        final TextView userName = findViewById(R.id.userName);
        final TextInputEditText Password = findViewById(R.id.Password);
        final TextInputEditText address = findViewById(R.id.address);
        final TextInputEditText email = findViewById(R.id.email);
        final TextInputEditText phone = findViewById(R.id.phone);

        ConformButton = findViewById(R.id.conformBtn);
        backBtn = findViewById(R.id.backBtn);

        Intent intent = getIntent();
        final String name = String.valueOf(intent.getStringExtra("Name"));

        databaseReference.child("Profile").addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //check if username
                String UsernameFromDB = snapshot.child(name).child("Name").getValue(String.class);
                String passwordFromDB = snapshot.child(name).child("pass").getValue(String.class);
                String addressFromDB = snapshot.child(name).child("adress").getValue(String.class);
                String mailFromDB = snapshot.child(name).child("mail").getValue(String.class);
                String contactFromDB = snapshot.child(name).child("contact").getValue(String.class);


                userName.setText(UsernameFromDB);
                Password.setText(passwordFromDB);
                address .setText(addressFromDB );
                email.setText(mailFromDB);
                phone.setText(contactFromDB);


               ConformButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        final String Usernametext =  userName.getText().toString();
                        final String Passwordtext = Password.getText().toString();
                        final String addresstext = address.getText().toString();
                        final String emailtext = email.getText().toString();
                        final String phonetext = phone.getText().toString();

                        if(Usernametext.isEmpty() || Passwordtext.isEmpty() || addresstext.isEmpty() ||  emailtext.isEmpty() ||phonetext.isEmpty() ){
                            Toast.makeText(edit_profile.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                        }

                    else{
                            updateData(Usernametext,Passwordtext,addresstext,emailtext,phonetext);

                        }
                    }
                });

                backBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent intent = new Intent(getApplicationContext(),profile.class);

                        intent.putExtra( "Name",UsernameFromDB);
                        startActivity(intent);
                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void updateData(String Usernametext,String Passwordtext,String addresstext,String emailtext,String phonetext){

        HashMap User = new HashMap();

        User.put("Name",Usernametext);
        User.put("pass",Passwordtext);
        User.put("adress",addresstext);
        User.put("mail",emailtext);
        User.put("contact",phonetext);


//(Password  Email  phone  name  address

        user = databaseReference.child(Usernametext);
        userID = user.getKey();
        databaseReference.child("Profile").child(userID).updateChildren(User);
        Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(getApplicationContext(),profile.class);
        intent.putExtra( "Name",Usernametext);
        startActivity(intent);

    }
}
