package com.dabudabu.samsatnotif.activity;

import android.annotation.SuppressLint;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import com.dabudabu.samsatnotif.R;
import com.dabudabu.samsatnotif.adapter.InformasiAdapter;
import com.dabudabu.samsatnotif.model.Informasi;
import com.dabudabu.samsatnotif.model.ItemInformasi;
import com.dabudabu.samsatnotif.service.RestAPIHelper;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InformasiActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{

    private Toolbar toolBar;
    private RecyclerView recyclerView;
    private List<ItemInformasi> informasis = new ArrayList<>();
    private InformasiAdapter informasiAdapter;
    private SwipeRefreshLayout refreshLayout;
    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informasi);
        toolBar = (Toolbar) findViewById(R.id.toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.recyleinformasi);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(informasiAdapter);
        setSupportActionBar(toolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Informasi");
        refreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeinfo);
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.post(new Runnable() {
            @Override
            public void run() {
                refreshLayout.setRefreshing(true);
                loadInformasi();
            }
        });
    }

    private void loadInformasi() {
        refreshLayout.setRefreshing(true);
        Call<Informasi> call = RestAPIHelper.ServiceApi(getApplication()).getInformasi();
        call.enqueue(new Callback<Informasi>() {
            @Override
            public void onResponse(Call<Informasi> call, Response<Informasi> response) {
                if (response.body() != null){
                    informasis = response.body().getInformasi();
                    informasiAdapter = new InformasiAdapter(informasis, getApplicationContext());
                    recyclerView.setAdapter(informasiAdapter);
                }else {
                    Toast.makeText(InformasiActivity.this, "Error Response", Toast.LENGTH_SHORT).show();
                    Log.e("loadInfos","Error on Response");
                }
                refreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<Informasi> call, Throwable t) {
                Toast.makeText(InformasiActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("loadInfos","Error on Failure" + t.getMessage());
                refreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    @Override
    public void onRefresh() {
        informasis.clear();
        informasiAdapter.notifyDataSetChanged();
        loadInformasi();
    }
}
