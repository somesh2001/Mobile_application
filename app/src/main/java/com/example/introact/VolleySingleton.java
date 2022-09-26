package com.example.introact;

import android.annotation.SuppressLint;
import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class VolleySingleton {

    @SuppressLint("StaticFieldLeak")
    private static VolleySingleton mInstance;
    private RequestQueue mRequestQueue;
    @SuppressLint("StaticFieldLeak")
    private static Context mContext;

    private VolleySingleton(Context context){
        // Specify the application context
        mContext = context;
        // Get the request queue
        mRequestQueue = getRequestQueue();
    }

    //synchronized Method-->(multiple threading)
    public static synchronized VolleySingleton getInstance(Context context){
        // If Instance is null then initialize new Instance
        if(mInstance == null){
            mInstance = new VolleySingleton(context);
        }
        // Return MySingleton new Instance
        return mInstance;
    }
    //predefined callback method getRequestQueue
    public RequestQueue getRequestQueue(){
        // If RequestQueue is null the initialize new RequestQueue (anannya is the first request)
        if(mRequestQueue == null){ //library example lock the book by annanya
            mRequestQueue = Volley.newRequestQueue(mContext.getApplicationContext());  //first request
        }

        // Return RequestQueue
        return mRequestQueue;
    }
    //add the second request in the queue and hold (muskan is in the request queue)
    public<T> void addToRequestQueue(Request<T> request){
        // Add the specified request to the request queue
        getRequestQueue().add(request);
    }
}
