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

import java.util.HashMap;

public class edit_form_2 extends AppCompatActivity {

    private TextView pBackBtn;
    private Button pConformButton;

    DatabaseReference user;
    String userID;

    edit_profile binding;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://coco1-baa44-default-rtdb.firebaseio.com/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_form2);

        final TextView bName = findViewById(R.id.bName);
        final TextInputEditText town = findViewById(R.id.town);
        final TextInputEditText pAccept = findViewById(R.id.pAccept);
        final TextInputEditText pWatch = findViewById(R.id.pWatch);
        final TextInputEditText pLeft = findViewById(R.id.pLeft);
        final TextInputEditText pDays = findViewById(R.id.pDays);
        final TextInputEditText pEme = findViewById(R.id.pEme);
        final TextInputEditText pCon = findViewById(R.id.pCon);

        pConformButton = findViewById(R.id.pConformBtn);
        pBackBtn = findViewById(R.id.pBackBtn);

        Intent intent = getIntent();
        final String b_name = String.valueOf(intent.getStringExtra("bName"));

        databaseReference.child("Dataset").addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //check if username
                String BoardingNameFromDB = snapshot.child(b_name).child("bName").getValue(String.class);
                String townFromDB = snapshot.child(b_name).child("twn").getValue(String.class);
                String acceptFromDB = snapshot.child(b_name).child("pAccept").getValue(String.class);
                String watchFromDB = snapshot.child(b_name).child("pWatch").getValue(String.class);
                String leftFromDB = snapshot.child(b_name).child("pLeft").getValue(String.class);
                String daysFromDB = snapshot.child(b_name).child("pDays").getValue(String.class);
                String emergencyFromDB = snapshot.child(b_name).child("pEme").getValue(String.class);
                String ContactFromDB = snapshot.child(b_name).child("pContact").getValue(String.class);

                bName.setText(BoardingNameFromDB);
                town.setText(townFromDB);
                pAccept .setText(acceptFromDB);
                pWatch.setText(watchFromDB );
                pLeft.setText(leftFromDB);
                pDays.setText(daysFromDB);
                pEme.setText(emergencyFromDB);
                pCon.setText(ContactFromDB);


                pConformButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        final String nametext =  bName.getText().toString();
                        final String Towntext = town.getText().toString();
                        final String accepttext = pAccept.getText().toString();
                        final String Watchtext = pWatch.getText().toString();
                        final String Lefttext = pLeft.getText().toString();
                        final String Daystext = pDays.getText().toString();
                        final String Emetext = pEme.getText().toString();
                        final String Context = pCon.getText().toString();

                        if(nametext.isEmpty() || Towntext.isEmpty() || accepttext.isEmpty() ||  Watchtext.isEmpty() ||Lefttext.isEmpty()||Daystext.isEmpty()||Emetext.isEmpty()||Context.isEmpty()){
                            Toast.makeText(edit_form_2.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                        }

                        else{
                            updateData(nametext,Towntext,accepttext,Watchtext,Lefttext,Daystext,Emetext,Context);

                        }
                    }
                });

                pBackBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent intent = new Intent(getApplicationContext(),profile.class);

                        intent.putExtra( "bName",BoardingNameFromDB);
                        startActivity(intent);
                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void updateData(String nametext,String Towntext,String accepttext,String Watchtext,String Lefttext,String Daystext ,String Emetext,String Context){

        HashMap User = new HashMap();

        User.put("bName",nametext);
        User.put("town",Towntext);
        User.put("pAccept",accepttext);
        User.put("pWatch",Watchtext);
        User.put("pLeft",Lefttext);
        User.put("pDays",Daystext);
        User.put("pEme",Emetext);
        User.put("pCon", Context);


//(Password  Email  phone  name  address

        user = databaseReference.child(nametext);
        userID = user.getKey();
        databaseReference.child("Dataset").child(userID).updateChildren(User);
        Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(getApplicationContext(),profile.class);
        intent.putExtra( "bName",nametext);
        startActivity(intent);

    }
}