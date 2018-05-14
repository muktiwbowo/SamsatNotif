package com.dabudabu.samsatnotif.fragment;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.dabudabu.samsatnotif.R;
import com.dabudabu.samsatnotif.notificationmanager.SharedPrefManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentRegister extends Fragment{

    private EditText edtNama, edtEmail, edtPass, edtJenis, edtPlat;
    private Button buttonRegister;
    private ProgressDialog progressDialog;

    private static final String URL_REGISTER_DEVICE
            = "https://heruary.000webhostapp.com/samsatnotif/RegisterDevice.php";


    public FragmentRegister() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        edtNama = view.findViewById(R.id.txtnama);
        edtEmail = view.findViewById(R.id.txtemail);
        edtPass = view.findViewById(R.id.txtpassword);
        edtJenis = view.findViewById(R.id.txtjeniskendaraan);
        edtPlat = view.findViewById(R.id.txtplat);
        buttonRegister = view.findViewById(R.id.btn_daftar);
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendTokenToServer();
            }
        });
        return view;
    }

    private void sendTokenToServer() {
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Registering Device...");
        progressDialog.show();

        final String token = SharedPrefManager.getmInstance(getContext()).getDeviceToken();
        final String nama = edtNama.getText().toString();
        final String email = edtEmail.getText().toString();
        final String pass = edtPass.getText().toString();
        final String jenis = edtJenis.getText().toString();
        final String plat = edtPlat.getText().toString();

        if (token == null) {
            progressDialog.dismiss();
            Toast.makeText(getContext(), "Token not generated", Toast.LENGTH_LONG).show();
            return;
        }

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_REGISTER_DEVICE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONObject obj = new JSONObject(response);
                            Toast.makeText(getContext(), obj.getString("message"), Toast.LENGTH_LONG).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("email", email);
                params.put("password", pass);
                params.put("name", nama);
                params.put("jeniskendaraan", jenis);
                params.put("nomorplat", plat);
                params.put("token", token);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        requestQueue.add(stringRequest);
    }

}
