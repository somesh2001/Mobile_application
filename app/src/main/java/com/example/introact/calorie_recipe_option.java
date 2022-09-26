package com.example.introact;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class calorie_recipe_option extends AppCompatActivity {
    private TextView result;
    private CardView calorie,recipe;
    private AlertDialog.Builder adb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calorie_recipe_option);
        getSupportActionBar().setTitle("Calorie and Recipe Details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        result = findViewById(R.id.detail_result);
        calorie = findViewById(R.id.calorie_detail);
        recipe =findViewById(R.id.recipe_detail);

        Intent intent = getIntent();
        String str = intent.getStringExtra("category");
        result.setText(str);


        recipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str2 = result.getText().toString();
                Intent intent = new Intent(getApplicationContext(),LoadData.class);
                intent.putExtra("category",str2);
                startActivity(intent);
            }
        });
        calorie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str3 = result.getText().toString();
                Intent intent1 = new Intent(getApplicationContext(),LoadCalorie.class);
                intent1.putExtra("category",str3);
                startActivity(intent1);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        adb = new AlertDialog.Builder(this);
        adb.setIcon(R.drawable.ic_warning);
        adb.setTitle("Alert");
    }
}