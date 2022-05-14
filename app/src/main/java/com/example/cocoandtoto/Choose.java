package com.example.cocoandtoto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Choose extends AppCompatActivity {

    Button BtnServices , BtnCustomer ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);

        BtnCustomer  = findViewById(R.id.customerButton);
        BtnServices = findViewById(R.id.RegisterButton);

        BtnServices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Choose.this,form_2.class);
                startActivity(intent);
            }
        });
        BtnCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Choose.this, Recycle_view.class);
                startActivity(intent);
            }
        });
    }
}