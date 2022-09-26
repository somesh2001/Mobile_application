package com.example.introact;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.introact.model.User;
import com.example.introact.util.constants;


import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private AppCompatButton button;
    @SuppressLint("StaticFieldLeak")
    private static EditText nameEdit, emailEdit, passwordEdit, contactEdit;
    private AlertDialog.Builder adb;
    private SharedPreferences preferences, preferencesuser;
    private StringRequest stringRequest;
    private VolleySingleton singleton;
    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL= "email";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_CONTACT = "contact";

    private ImageView gotologin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
//        getSupportActionBar().hide();
        preferences = getSharedPreferences("mypref",MODE_PRIVATE);
        preferencesuser = getSharedPreferences("mypref2",MODE_PRIVATE);
        init();
    }

    /*public void onLoginClick(View view){
        startActivity(new Intent(this,LoginActivity.class));
        overridePendingTransition(R.anim.slide_in_right,android.R.anim.slide_out_right);

    }*/
    @Override
    protected void onResume() {
        super.onResume();
        button.setOnClickListener(this);
        adb = new AlertDialog.Builder(this);
        adb.setIcon(R.drawable.ic_warning);
        adb.setTitle("Alert");
    }
    private void init() {
        nameEdit = findViewById(R.id.name);
        emailEdit = findViewById(R.id.email);
        passwordEdit = findViewById(R.id.password);
        contactEdit = findViewById(R.id.contact);
        button = findViewById(R.id.signup);
        //  gotologin = findViewById(R.id.gotologin);
    }

    @Override
    public void onClick(View v) {
        if (v == button) {
            registerUser();
        }
    }

    private void registerUser()
    {
        final User user= new DAOClass().setData();//creating the instance of the class return object of user class

        stringRequest = new StringRequest(Request.Method.POST, constants.REG_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("INFO", response);
                        if (user != null) {
                            if (response.equals("success")) {
                                Toast.makeText(getApplicationContext(), "User Registered successfully.", Toast.LENGTH_SHORT).show();
                                SharedPreferences.Editor editor = preferencesuser.edit();
                                editor.putString("username",user.getName());
                                editor.putString("contact", user.getContact());
                                editor.putString("email",user.getEmail());
                                editor.putString("password", user.getPassword());

                                editor.apply();
                                editor.commit();

                                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);

                            } else {
                                adb.setMessage("User already exists try with new email and username");
                                adb.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                    }
                                });
                                adb.create().show();                            }
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error", error.getMessage());
                //Toast.makeText(getApplicationContext(),"sdfffcf",Toast.LENGTH_SHORT).show();
            }
        })
        {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> hashMap = new HashMap<>();  //creating maping
                hashMap.put(KEY_NAME,user.getName());
                hashMap.put(KEY_EMAIL,user.getEmail());
                hashMap.put(KEY_PASSWORD,user.getPassword());
                hashMap.put(KEY_CONTACT,user.getContact());
                return hashMap;
            }
        };
        singleton = VolleySingleton.getInstance(this);
        singleton.addToRequestQueue(stringRequest);
    }
    //inner class
    private static class DAOClass {
        public User setData() {
            String name = nameEdit.getText().toString().trim();
            String email = emailEdit.getText().toString().trim();
            String passwd = passwordEdit.getText().toString().trim();
            String contact = contactEdit.getText().toString().trim();
            String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

            if (TextUtils.isEmpty(name)) {
                nameEdit.setError("please fill the field");
            } else if (TextUtils.isEmpty(email)) {
                emailEdit.setError("please fill the field");
            } else if (TextUtils.isEmpty(passwd)) {
                passwordEdit.setError("please fill the field");
            } else if (passwd.length() < 8) {
                passwordEdit.setError("password is too short");
            } else if (TextUtils.isEmpty(contact)) {
                contactEdit.setError("please fill the field");
            } else if (contact.length() != 10) {
                contactEdit.setError("contact number is incorrect");
            } else if (!email.matches(emailPattern))
            {
                emailEdit.setError("Invalid email");
            }else {
                User user = new User(name,email,passwd, contact);
                return user; //return the object of user
            }
            return null;
        }
    }
}
