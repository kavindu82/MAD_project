package com.example.cocoandtoto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    private ImageView logo;
    private TextView title,slogen;
    Animation topAnimation, bottomAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logo = findViewById(R.id.logo);
        title = findViewById(R.id.title);
        slogen = findViewById(R.id.slogen);

        topAnimation = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnimation = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        logo.setAnimation(topAnimation);
        title.setAnimation(bottomAnimation);
        slogen.setAnimation(bottomAnimation);

        int SPLASH_SCREEN = 4300;

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent =new Intent(MainActivity.this, home.class);
                startActivity(intent);
                finish();
            }
        },SPLASH_SCREEN);
    }
}