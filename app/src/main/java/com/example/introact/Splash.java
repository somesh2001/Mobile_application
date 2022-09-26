package com.example.introact;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class Splash extends AppCompatActivity {
    private String email;
    private String password;
    private ImageView image;
    private TextView logo;
    private LottieAnimationView  lottieAnimationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash2);


//        image = findViewById(R.id.imageView2);
        lottieAnimationView = findViewById(R.id.lottie);
        SharedPreferences preferences = getSharedPreferences("mypref", MODE_PRIVATE);
        email = preferences.getString("email","");
        password = preferences.getString("password","");
//        getSupportActionBar().hide();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                overridePendingTransition(R.anim.slide_in_right,R.anim.stay);
            }
        },3000);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent;
    }
}