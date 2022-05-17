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

public class form_1 extends AppCompatActivity {



    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://coco1-baa44-default-rtdb.firebaseio.com/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form1);

        final TextInputEditText c_name = findViewById(R.id.c_name);
        final TextInputEditText c_address = findViewById(R.id.c_address);
        final TextInputEditText c_mail1 = findViewById(R.id.c_mail1);
        final TextInputEditText c_contact = findViewById(R.id.c_contact);
        final TextInputEditText c_pass = findViewById(R.id.c_pass);
        final Button c_btn_sub = findViewById(R.id.c_btn_sub);

        c_btn_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String nametext = c_name.getText().toString();
                final String Adresstext = c_address.getText().toString();
                final String mailtext = c_mail1.getText().toString();
                final String contacttext = c_contact.getText().toString();
                final String Passwordtext = c_pass.getText().toString();

                if(nametext.isEmpty() || Adresstext.isEmpty() || mailtext.isEmpty() || contacttext.isEmpty() ||Passwordtext.isEmpty() ){
                    Toast.makeText(form_1.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                }

                else {
                    databaseReference.child("Profile").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.hasChild(nametext)){
                                Toast.makeText(form_1.this, "User name is already registered", Toast.LENGTH_SHORT).show();
                            }else {
                                databaseReference.child("Profile").child(nametext).child("Name").setValue(nametext);
                                databaseReference.child("Profile").child(nametext).child("adress").setValue(Adresstext);
                                databaseReference.child("Profile").child(nametext).child("mail").setValue(mailtext);
                                databaseReference.child("Profile").child(nametext).child("contact").setValue(contacttext);
                                databaseReference.child("Profile").child(nametext).child("pass").setValue(Passwordtext);

                                Toast.makeText(form_1.this, "User register Successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),form_2.class);
                                startActivity(intent);
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

