package com.example.cocoandtoto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import android.os.Bundle;

public class form_2 extends AppCompatActivity {

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://coco1-baa44-default-rtdb.firebaseio.com/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form2);

        final TextInputEditText c_bName = findViewById(R.id.c_bName);
        final TextInputEditText c_town = findViewById(R.id.c_town);
        final TextInputEditText c_petAccept = findViewById(R.id.c_petAccept);
        final TextInputEditText c_petWatch = findViewById(R.id.c_petWatch);
        final TextInputEditText c_petLeft = findViewById(R.id.c_petLeft);
        final TextInputEditText c_petDays = findViewById(R.id.c_petDays);
        final TextInputEditText c_petEme = findViewById(R.id.c_petEme);
        final TextInputEditText c_contactP = findViewById(R.id.c_contactP);
        final Button c_btn_con = findViewById(R.id.c_btn_con);

        c_btn_con.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String nametext = c_bName.getText().toString();
                final String towntext = c_town.getText().toString();
                final String accepttext =  c_petAccept.getText().toString();
                final String watchtext = c_petWatch.getText().toString();
                final String lefttext =  c_petLeft.getText().toString();
                final String daystext =  c_petDays.getText().toString();
                final String emetext = c_petEme.getText().toString();
                final String cantacttext =  c_contactP.getText().toString();

                if(nametext .isEmpty() ||  towntext.isEmpty() || accepttext.isEmpty() || watchtext.isEmpty() ||lefttext.isEmpty() ||daystext.isEmpty() ||emetext.isEmpty() ||cantacttext.isEmpty() ){
                    Toast.makeText(form_2.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                }

                else {
                    databaseReference.child("Dataset").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.hasChild(nametext)){
                                Toast.makeText(form_2.this, " Name is already registered", Toast.LENGTH_SHORT).show();
                            }else {
                                databaseReference.child("Dataset").child(nametext).child("bName").setValue(nametext);
                                databaseReference.child("Dataset").child(nametext).child("town").setValue(towntext);
                                databaseReference.child("Dataset").child(nametext).child("petAccept").setValue(accepttext);
                                databaseReference.child("Dataset").child(nametext).child("petWatch").setValue(watchtext);
                                databaseReference.child("Dataset").child(nametext).child("petLeft").setValue(lefttext);
                                databaseReference.child("Dataset").child(nametext).child("petDays").setValue(daystext);
                                databaseReference.child("Dataset").child(nametext).child("c_petEme").setValue(emetext);
                                databaseReference.child("Dataset").child(nametext).child("contactP").setValue(cantacttext);

                                Toast.makeText(form_2.this, "User register Successfully", Toast.LENGTH_SHORT).show();
                                finish();

                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });



                }

            }
        });
    }
}