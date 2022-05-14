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
                final String passtext = c_pass.getText().toString();

                if(nametext.isEmpty() || Adresstext.isEmpty() || mailtext.isEmpty() || contacttext.isEmpty() ||passtext.isEmpty() ){
                    Toast.makeText(form_1.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                }
              //  else if (passtext.length()<5){
                //    Toast.makeText(form_1.this, "Minimum password length should be 5 characters", Toast.LENGTH_SHORT).show();
               // }
                else {
                    databaseReference.child("Profile").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.hasChild(mailtext)){
                                Toast.makeText(form_1.this, "User name is already registered", Toast.LENGTH_SHORT).show();
                            }else {
                                databaseReference.child("Profile").child(mailtext).child("Name").setValue(nametext);
                                databaseReference.child("Profile").child(mailtext).child("adress").setValue(Adresstext);
                                databaseReference.child("Profile").child(mailtext).child("mail").setValue(mailtext);
                                databaseReference.child("Profile").child(mailtext).child("contact").setValue(contacttext);
                                databaseReference.child("Profile").child(mailtext).child("pass").setValue(passtext);

                                Toast.makeText(form_1.this, "User register Successfully", Toast.LENGTH_SHORT).show();
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

/* }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.CoConToTo:
                startActivity(new Intent(this,MainActivity.class));
                break;
            case R.id.c_btn_sub:
               btn_sub();
                break;
        }

    }

    private void btn_sub() {

        String name = nameC.getText().toString().trim();
        String address = addressC.getText().toString().trim();
        String email = mail1C.getText().toString().trim();
        String contact = contactC.getText().toString().trim();
        String pwd = passC.getText().toString().trim();

        if(name.isEmpty()){
            nameC.setError("Name is required");
            nameC.requestFocus();
            return;
        }


        if(address.isEmpty()){
            addressC.setError(" address is required");
            addressC.requestFocus();
            return;
        }

        if(email.isEmpty()){
            mail1C.setError("Email is required");
            mail1C.requestFocus();
            return;
        }



        if(contact.isEmpty()){
            contactC.setError("Phone number is required");
            contactC.requestFocus();
            return;
        }

        if(pwd.isEmpty()){
            passC.setError("Password is required");
            passC.requestFocus();
            return;
        }

        if(pwd.length()<5){
            passC.setError("Minimum password length should be 5 characters");
            passC.requestFocus();
            return;
        }


        mAuth.createUserWithEmailAndPassword(email,pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                   profile_form_1 proF =new profile_form_1(name,email,address,pwd,contact );

                   FirebaseDatabase.getInstance().getReference("profile").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                           .setValue(proF).addOnCompleteListener(new OnCompleteListener<Void>() {
                       @Override
                       public void onComplete(@NonNull Task<Void> task) {

                           if(task.isSuccessful()){
                               Toast.makeText(form_1.this, "Register Successful", Toast.LENGTH_SHORT).show();

                           }else{
                               Toast.makeText(form_1.this, "Register Failed", Toast.LENGTH_SHORT).show();
                           }
                       }
                   });
                }

            }
        });*/