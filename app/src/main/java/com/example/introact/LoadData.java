package com.example.introact;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class LoadData extends AppCompatActivity {
    private TextView load;
    private LottieAnimationView animationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_data);

        load = findViewById(R.id.result_load_data);
       // animationView=findViewById(R.id.load_animation);

        final Intent intent = getIntent();
        String str = intent.getStringExtra("category");
        load.setText(str);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                String str = load.getText().toString();
                Intent intent1 = new Intent(getApplicationContext(),fruit_list.class);
                overridePendingTransition(R.anim.slide_in_right,R.anim.stay);
                intent1.putExtra("category",str);
                startActivity(intent1);

            }
        },3000);


    }

}