package com.example.introact;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.introact.model.Calories;
import com.example.introact.model.Fruits;
import com.example.introact.util.constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class calorie_protein extends AppCompatActivity {

    private int progr = 0;
    private FrameLayout home;
    public TextView calorie_result,calorie,carbs,fat,protein;
    private ArrayList<Calories> calorie_list;
    private ProgressBar progress_bar;
    public static final String KEY_ID = "category";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calorie_protein);
        calorie_result = findViewById(R.id.calorie_result);
        calorie = findViewById(R.id.calorie);
        carbs = findViewById(R.id.carbdata);
        fat = findViewById(R.id.fat);
        protein = findViewById(R.id.proteindata);
        home = findViewById(R.id.home);
        calorie_list = new ArrayList<>();
        Intent intent = getIntent();
        String str = intent.getStringExtra("category");
        calorie_result.setText(str);

        progress_bar = findViewById(R.id.progress_bar);
        loadData();
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),NavAct.class));
                overridePendingTransition(R.anim.slide_in_left,R.anim.stay);
            }
        });

    }


    private void loadData(){
        StringRequest stringRequest = new StringRequest(Request.Method.DEPRECATED_GET_OR_POST,
                constants.URL_NEW, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("INFO",response);
                try{
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    for (int i=0; i<jsonArray.length();i++){
                        JSONObject jOBJ = jsonArray.getJSONObject(i);
                        Calories mActors = new Calories(
                                jOBJ.getString("calories"),
                                jOBJ.getString("carbs"),
                                jOBJ.getString("Fat"),
                                jOBJ.getString("protein"));
                        calorie_list.add(mActors);
                        calorie.setText(jOBJ.getString("calories"));
                        carbs.setText(jOBJ.getString("carbs"));
                        fat.setText(jOBJ.getString("Fat"));
                        protein.setText(jOBJ.getString("protein"));

                        String num1 = calorie.getText().toString();
                        int num = Integer.parseInt(num1);
                        progress_bar.setProgress(num);
                    }


                }catch(JSONException ex){
                    Log.e("JSON",ex.getMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
//                Bundle bundle = getIntent().getExtras();
                Intent intent = getIntent();
                String str = intent.getStringExtra("category");
                String pp = calorie_result.getText().toString();
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put(KEY_ID, pp);

                return hashMap;
            }
        };
        VolleySingleton singleton = VolleySingleton.getInstance(this);
        singleton.addToRequestQueue(stringRequest);
    }





}