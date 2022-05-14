package com.example.cocoandtoto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity  {

    private Button signup;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://coco1-baa44-default-rtdb.firebaseio.com/");

    private TextView backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final Button LogButton = findViewById(R.id.LogButton);
        final TextInputEditText Password = findViewById(R.id.Password);
        final TextInputEditText mail = findViewById(R.id.mail);

        backButton = findViewById(R.id.backButton);

        LogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String mailtext = mail.getText().toString();
                final String Passwordtext = Password.getText().toString();

                if(mailtext.isEmpty() || Passwordtext.isEmpty()){
                    Toast.makeText(Login.this, "Please enter Your user name or password", Toast.LENGTH_SHORT).show();
                }else {
                    databaseReference.child("Profile").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            //check if username
                            if(snapshot.hasChild(mailtext)){
                                final String getPassword = snapshot.child(mailtext).child("pass").getValue(String.class);

                                if(getPassword.equals(Passwordtext)){
                                    Toast.makeText(Login.this, "Successfully Logged in", Toast.LENGTH_SHORT).show();
                                    //startActivity(new Intent(LoginActivity.this,Shop.class));

                                    Intent intent = new Intent(Login.this,Recycle_view.class);

                                    startActivity(intent);

                                }
                                else{
                                    Toast.makeText(Login.this, "Wrong Password", Toast.LENGTH_SHORT).show();
                                }
                            }else{
                                Toast.makeText(Login.this, "Wrong Password", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }

            }
        });








        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, form_1.class);
                startActivity(intent);
            }
        });
    }
}