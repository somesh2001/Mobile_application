package com.example.introact;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.introact.util.constants;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class NavAct extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private SharedPreferences preferencesuser;
    private SharedPreferences preferences;
    private TextView unameText, emailText;
    private NavigationView navigationView;
    private RecyclerView recyclerView;
    private AppCompatButton filterdoc,cat;
    private String email;
    private CardView fruit,vegetable;
    private String password;

    ImageView imageView;
    Bundle bundle = null;
    private VolleySingleton singleton1;
    private VolleySingleton singleton;
    private StringRequest stringRequest1;
    private StringRequest stringRequest;
    private SharedPreferences.Editor editor;
    private SharedPreferences.Editor Editor;
    GoogleSignInClient mGoogleSignInClient;
    public static final String KEY_ID = "email";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        preferences = getSharedPreferences("mypref", MODE_PRIVATE);
        preferencesuser = getSharedPreferences("mypref2",MODE_PRIVATE);
        Toolbar toolbar = findViewById(R.id.toolbar);
        unameText = findViewById(R.id.usernameText);
        fruit = findViewById(R.id.fruits);
        vegetable = findViewById(R.id.vegetables);

        fruit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),FruitImage.class));
                overridePendingTransition(R.anim.slide_in_right,R.anim.stay);
            }
        });


        vegetable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),VegetableFruitImage.class));
                overridePendingTransition(R.anim.slide_in_right,R.anim.stay);

            }
        });

        emailText = findViewById(R.id.emailText);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer,toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
        unameText= navigationView.getHeaderView(0).findViewById(R.id.usernameText);
        emailText= navigationView.getHeaderView(0).findViewById(R.id.emailText);
        imageView = navigationView.getHeaderView(0).findViewById(R.id.imageView);
        bundle = getIntent().getExtras();
        if (bundle !=null){
           /* Uri uri = bundle.getParcelable("display_image");
            Picasso.get().load(uri.getPath()).fit().into(imageView);*/
            unameText.setText(bundle.getCharSequence("display_name"));
            emailText.setText(bundle.getCharSequence("email"));
        }
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail().build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        email = preferences.getString("email","");
        password = preferences.getString("password","");
        bundle = getIntent().getExtras();

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!email.equals("")){
      unameText= navigationView.getHeaderView(0).findViewById(R.id.usernameText);
        emailText= navigationView.getHeaderView(0).findViewById(R.id.emailText);
        unameText.setText(preferencesuser.getString("username", ""));
        emailText.setText(preferences.getString("email", ""));
    }}

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        Intent intent = null;
        if(id == R.id.nav_profile){
            intent = new Intent(getApplicationContext(), profile.class);
            overridePendingTransition(R.anim.slide_in_right,R.anim.stay);
        }
        else if(id == R.id.logout) {

            SharedPreferences.Editor editor = preferences.edit();
            editor.remove("email");
            editor.remove("password");
            editor.clear();
            editor.apply();
            editor.commit();
            intent = new Intent(getApplicationContext(), LoginActivity.class);
            overridePendingTransition(R.anim.slide_in_left,R.anim.stay);
        }

        startActivity(intent);
        return true;
    }
}
