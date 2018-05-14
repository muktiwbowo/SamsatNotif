package com.dabudabu.samsatnotif.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.dabudabu.samsatnotif.R;
import com.dabudabu.samsatnotif.notificationmanager.SessionManager;

import java.util.HashMap;

public class UserActivity extends AppCompatActivity {

    private Toolbar toolBar;
    private TextView userName, userEmail;
    private Button btnInformasi, btnEposti, btnEvent, btnLogout;
    private SessionManager session;
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        toolBar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolBar);
        getSupportActionBar().setTitle("Beranda");
        userName = (TextView) findViewById(R.id.username);
        userEmail = (TextView) findViewById(R.id.useremail);
        btnInformasi = (Button) findViewById(R.id.userinformasi);
        btnEposti = (Button) findViewById(R.id.usereposti);
        btnEvent = (Button) findViewById(R.id.userevent);
        btnLogout = (Button) findViewById(R.id.userlogout);
        session = new SessionManager(getApplicationContext());
        HashMap<String, String> user = session.getUser();
        String nameUser = user.get(SessionManager.KEY_USER);
        String emailUser = user.get(SessionManager.KEY_EMAIL);

        userName.setText(nameUser);
        userEmail.setText(emailUser);
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        if (!session.isLoggedIn()) {
            logoutUser();
        }

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logoutUser();
            }
        });
        btnEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserActivity.this, EventActivity.class));
            }
        });
        btnInformasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserActivity.this, InformasiActivity.class));
            }
        });
        btnEposti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserActivity.this, EpostiActivity.class));
            }
        });
    }
    private void logoutUser() {
        session.setLogin(false);
        pDialog.setMessage("Logging out ...");
        showDialog();
        // Launching the login activity
        Intent intent = new Intent(UserActivity.this, LoginActivity.class);
        startActivity(intent);
        hideDialog();
        finish();
    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}
