package com.example.introact;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class RecipeDetail extends AppCompatActivity {
    private TextView r1,r2,r3,r4,r5,r6,r7,r8,in1,in2,in3,in4,title;

    ImageView popup;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.arecipe_detailnew);
        init();
        popup = findViewById(R.id.popup2);
        dialog = new Dialog(this);

        popup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.setContentView(R.layout.popup);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(getApplicationContext(),NavAct.class));
                        overridePendingTransition(R.anim.slide_in_left,R.anim.stay);
                    }
                },3000);
            }
        });

    }
    private void init(){


        r1 = findViewById(R.id.r1);
        r2 = findViewById(R.id.r2);
        r3 = findViewById(R.id.r3);
        r4 = findViewById(R.id.r4);

        r5 = findViewById(R.id.r5);
        r6 = findViewById(R.id.r6);
        r7 = findViewById(R.id.r7);
        r8 = findViewById(R.id.r8);

        title = findViewById(R.id.title);

        in1 = findViewById(R.id.in1);
        in2 = findViewById(R.id.in2);
        in3 = findViewById(R.id.in3);
        in4 = findViewById(R.id.in4);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {

            // Picasso.get().load(String.valueOf(bundle.getCharSequence("image"))).into(image);
            title.setText(bundle.getCharSequence("title"));
            r1.setText(bundle.getCharSequence("recipea"));
            r2.setText(bundle.getCharSequence("recipeb"));
            r3.setText(bundle.getCharSequence("recipec"));
            r4.setText(bundle.getCharSequence("reciped"));
            r5.setText(bundle.getCharSequence("recipee"));
            r6.setText(bundle.getCharSequence("recipef"));
            r7.setText(bundle.getCharSequence("recipeg"));
            r8.setText(bundle.getCharSequence("recipeh"));

            in1.setText(bundle.getCharSequence("ina"));
            in2.setText(bundle.getCharSequence("inb"));
            in3.setText(bundle.getCharSequence("inc"));
            in4.setText(bundle.getCharSequence("ind"));

        }
    }
}
