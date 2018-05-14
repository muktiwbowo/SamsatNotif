package com.dabudabu.samsatnotif.activity;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.dabudabu.samsatnotif.R;
import com.dabudabu.samsatnotif.adapter.EventAdapter;
import com.dabudabu.samsatnotif.model.Event;
import com.dabudabu.samsatnotif.model.ItemEvent;
import com.dabudabu.samsatnotif.service.RestAPIHelper;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private List<ItemEvent> events = new ArrayList<>();
    private EventAdapter eventAdapter;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout refreshLayout;
    private Toolbar toolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        toolBar = (Toolbar) findViewById(R.id.toolbarevent);
        setSupportActionBar(toolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Event");
        recyclerView = (RecyclerView) findViewById(R.id.recycleevent);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(eventAdapter);
        refreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeevent);
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.post(new Runnable() {
            @Override
            public void run() {
                refreshLayout.setRefreshing(true);
                loadEvent();
            }
        });
    }

    private void loadEvent() {
        refreshLayout.setRefreshing(true);
        retrofit2.Call<Event> call = RestAPIHelper.ServiceApi(getApplication()).getEvent();
        call.enqueue(new Callback<Event>() {
            @Override
            public void onResponse(Call<Event> call, Response<Event> response) {
                if (response.body() != null){
                    events = response.body().getEvent();
                    eventAdapter = new EventAdapter(events, getApplicationContext());
                    recyclerView.setAdapter(eventAdapter);
                }else {
                    Toast.makeText(getApplicationContext(), "Error Response", Toast.LENGTH_SHORT).show();
                    Log.e("loadEvents","Error on Response");
                }
                refreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<Event> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("loadEvents","Error on Failure" + t.getMessage());
                refreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    public void onRefresh() {
        events.clear();
        eventAdapter.notifyDataSetChanged();
        loadEvent();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}
