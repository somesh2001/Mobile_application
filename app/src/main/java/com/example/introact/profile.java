package com.example.introact;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.introact.model.User;
import com.example.introact.util.constants;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class profile extends AppCompatActivity{

    private TextView name,email,phone,name2;
    private SharedPreferences preferencesuser;
    private SharedPreferences preference;
    private CircleImageView profileimg;
    private ImageView back;
    private ArrayList<User> users;
    private VolleySingleton singleton1;
    private VolleySingleton singleton;
    private Button updateBtn;
    private Uri mImageUri;
    private StringRequest stringRequest1;
    private StringRequest stringRequest;
    private SharedPreferences.Editor editor;
    private Bitmap bitmap;
    public static  final  String KEY_IMAGE = "image";
    public static  final  String KEY_EMAIL = "email";
    public static final String KEY_ID = "uid";
    private  static final int PICK_IMAGE_REQUEST=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
//        getSupportActionBar().hide();
        back = findViewById(R.id.home);
        init();
        preference = getSharedPreferences("mypref", MODE_PRIVATE);
        preferencesuser = getSharedPreferences("mypref2",MODE_PRIVATE);
        name2.setText(preferencesuser.getString("username", ""));
        name.setText(preferencesuser.getString("username", ""));
        email.setText(preference.getString("email", ""));
        phone.setText(preferencesuser.getString("contact", ""));
        // String mImageUri = preferencesuser.getString("image", null);
        //profileimg.setImageURI(Uri.parse(mImageUri));

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), NavAct.class));
                overridePendingTransition(R.anim.slide_in_left, R.anim.stay);
            }
        });
    }
    private void init() {
        name = findViewById(R.id.userName);
        email = findViewById(R.id.userEmail);
        phone = findViewById(R.id.userContact);
        name2 = findViewById(R.id.nameprofile);

    }
}