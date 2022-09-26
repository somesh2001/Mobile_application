package com.example.introact;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

public class LoadCalorie extends AppCompatActivity {
    private TextView load;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_calorie);
        load = findViewById(R.id.result_load_calorie);

        final Intent intent = getIntent();
        String str = intent.getStringExtra("category");
        load.setText(str);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                String str = load.getText().toString();
                Intent intent1 = new Intent(getApplicationContext(),calorie_protein.class);
                overridePendingTransition(R.anim.slide_in_right,R.anim.stay);
                intent1.putExtra("category",str);
                startActivity(intent1);

            }
        },3000);

    }
}