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
import android.widget.TextView;
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

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    @SuppressLint("StaticFieldLeak")
    private AlertDialog.Builder adb;
    private static EditText emailEdit, passwordEdit;
    private TextView create_new;
    private static AppCompatButton signInBtn;
    private SharedPreferences preferences,preferencesuser ;
    private StringRequest stringRequest;
    private VolleySingleton singleton;
    private String email;
    private String password;
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASSWORD = "password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
//        getSupportActionBar().hide();
        preferences = getSharedPreferences("mypref",MODE_PRIVATE);
        preferencesuser = getSharedPreferences("mypref2",MODE_PRIVATE);

        email = preferences.getString("email","");
        password = preferences.getString("password","");

        init();
        create_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),(RegisterActivity.class)));
                overridePendingTransition(R.anim.slide_in_right,R.anim.stay);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        signInBtn.setOnClickListener(this);

        adb = new AlertDialog.Builder(this);
        adb.setIcon(R.drawable.ic_warning);
        adb.setTitle("Alert");
        Intent intent;
        if(!email.equals("") && !password.equals(""))
        {
            intent = new Intent(getApplicationContext(), NavAct.class);
            startActivity(intent);
        }

    }

    private void init(){
        emailEdit = findViewById(R.id.loginemail);
        passwordEdit = findViewById(R.id.loginpassword);
        signInBtn = findViewById(R.id.login);
        create_new = findViewById(R.id.createnew);
    }


    @Override
    public void onClick(View v) {
        if(v == signInBtn)


            loginUser();

    }
    @SuppressLint("CommitPrefEdits")
    private void loginUser() {
        final User user = new DAOClass().setData();
        stringRequest = new StringRequest(Request.Method.POST, constants.LOGIN_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("INFO",response);

                        if(user!=null) {
                                if (response.equals("success")) {
                                    Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();
                                    SharedPreferences.Editor editor = preferences.edit();
                                    editor.putString("email", user.getEmail());
                                    editor.putString("password", user.getPassword());
                                    editor.apply();
                                    editor.commit();
                                    startActivity(new Intent(getApplicationContext(),NavAct.class));

                                } else {
                                    adb.setMessage("Invalid Password\nPlease Try Again");
                                    adb.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                        }
                                    });
                                    adb.create().show();
                                }
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("ERROR",error.toString());
                //Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_SHORT).show();
            }
        })
        {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put(KEY_EMAIL, user.getEmail());
                hashMap.put(KEY_PASSWORD, user.getPassword());
                return hashMap;
            }
        };
        singleton = VolleySingleton.getInstance(this);
        singleton.addToRequestQueue(stringRequest);
    }



    public static class DAOClass
    {
        public User setData()
        {
            String email = emailEdit.getText().toString().trim();
            String password = passwordEdit.getText().toString().trim();
            if(TextUtils.isEmpty(email))
                emailEdit.setError("Please Fill the Field");
            else if(TextUtils.isEmpty(password))
                passwordEdit.setError("Please Fill the Field");
            else if(password.length()<8)
                passwordEdit.setError("Password is too short");
            else{
                return new User(email,password);
            }
            return null;
        }

    }

}