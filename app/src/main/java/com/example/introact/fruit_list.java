package com.example.introact;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.introact.adapter.MyAdapter;
import com.example.introact.model.Fruits;
import com.example.introact.util.constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class fruit_list extends AppCompatActivity implements MyAdapter.OnItemClickListener{
    private RecyclerView recyclerView;
    private MyAdapter adapter;
    private TextView t1;
    private ArrayList<Fruits> fruit_list;
    private VolleySingleton singleton1;
    private VolleySingleton singleton;
    public static final String KEY_ID = "category";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruit_list);
        t1 = findViewById(R.id.result1);
        fruit_list = new ArrayList<>();

        getSupportActionBar().setTitle("Top Recipes");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        String str = intent.getStringExtra("category");
        t1.setText(str);

        loadData();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }
    private void loadData(){
        StringRequest stringRequest = new StringRequest(Request.Method.DEPRECATED_GET_OR_POST,
                constants.URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("INFO",response);
                try{
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    for (int i=0; i<jsonArray.length();i++){
                        JSONObject jOBJ = jsonArray.getJSONObject(i);
                        Fruits mActors = new Fruits(
                                jOBJ.getString("name"),
                                jOBJ.getString("category"),
                                jOBJ.getString("image"),
                                jOBJ.getString("title"),
                                jOBJ.getString("recipea"),
                                jOBJ.getString("recipeb"),
                                jOBJ.getString("recipec"),
                                jOBJ.getString("reciped"),
                                jOBJ.getString("recipee"),
                                jOBJ.getString("recipef"),
                                jOBJ.getString("recipeg"),
                                jOBJ.getString("recipeh"),
                                jOBJ.getString("ina"),
                                jOBJ.getString("inb"),
                                jOBJ.getString("inc"),
                                jOBJ.getString("ind"));
                        fruit_list.add(mActors);
                    }
                    recyclerView = findViewById(R.id.recylcerView);
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(new LinearLayoutManager(fruit_list.this));
                    adapter = new MyAdapter(fruit_list.this,fruit_list);
                    recyclerView.setAdapter(adapter);
                    adapter.setOnItemClickListener(fruit_list.this);

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
                String pp = t1.getText().toString();
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put(KEY_ID, pp);
                return hashMap;
            }
        };
        VolleySingleton singleton = VolleySingleton.getInstance(this);
        singleton.addToRequestQueue(stringRequest);
    }


    @Override
    public void onItemClick(int position) {
        Bundle bundle = new Bundle();
        Fruits mactors = fruit_list.get(position);
        Intent intent = new Intent(getApplicationContext(),RecipeDetail.class);
        overridePendingTransition(R.anim.slide_in_right,R.anim.stay);
        bundle.putString("image",mactors.getImage());
        bundle.putString("name",mactors.getName());
        bundle.putString("category",mactors.getCategory());
        bundle.putString("title",mactors.getTitle());
        bundle.putString("recipea",mactors.getRecipea());
        bundle.putString("recipeb",mactors.getRecipeb());
        bundle.putString("recipec",mactors.getRecipec());
        bundle.putString("reciped",mactors.getReciped());
        bundle.putString("recipee",mactors.getRecipee());
        bundle.putString("recipef",mactors.getRecipef());
        bundle.putString("recipeg",mactors.getRecipeg());
        bundle.putString("recipeh",mactors.getRecipeh());
        bundle.putString("ina",mactors.getIna());
        bundle.putString("inb",mactors.getInb());
        bundle.putString("inc",mactors.getInc());
        bundle.putString("ind",mactors.getInd());
        intent.putExtras(bundle);
        startActivity(intent);
    }
}