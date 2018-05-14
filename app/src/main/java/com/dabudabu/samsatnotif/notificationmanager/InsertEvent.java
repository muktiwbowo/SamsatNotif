package com.dabudabu.samsatnotif.notificationmanager;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class InsertEvent {
    private Context context;

    public InsertEvent(Context context) {
        this.context = context;
    }

    private static final String URL_INSERT_BIG
            = "https://heruary.000webhostapp.com/samsatnotif/InsertMessage.php";
    private static final String URL_INSERT_SMALL
            = "https://heruary.000webhostapp.com/samsatnotif/InsertSmallMessage.php";

    public void insertBigEvent(final String title, final String content, final String imageurl){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_INSERT_BIG,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);
                            Log.d("InsertEventResponse", obj.getString("message"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("ErrorInsertEvent", error.getMessage());
                    }
                }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("title", title);
                params.put("content", content);
                params.put("imageurl", imageurl);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        requestQueue.add(stringRequest);
    }

    public void insertSmallEvent(final String title, final String content){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_INSERT_SMALL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);
                            Log.d("InsertEventResponse", obj.getString("message"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("ErrorInsertEvent", error.getMessage());
                    }
                }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("title", title);
                params.put("content", content);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        requestQueue.add(stringRequest);
    }
}
