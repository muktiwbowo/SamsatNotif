package com.dabudabu.samsatnotif.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.dabudabu.samsatnotif.R;
import com.dabudabu.samsatnotif.activity.InformasiActivity;
import com.dabudabu.samsatnotif.adapter.EpostiAdapter;
import com.dabudabu.samsatnotif.model.Eposti;
import com.dabudabu.samsatnotif.model.ItemEposti;
import com.dabudabu.samsatnotif.service.RestAPIHelper;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentEposti extends Fragment implements SwipeRefreshLayout.OnRefreshListener{
    private List<ItemEposti> epostis = new ArrayList<>();
    private EpostiAdapter epostiAdapter;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout refreshLayout;

    public FragmentEposti() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_eposti, container, false);
        recyclerView = view.findViewById(R.id.recycleeposti);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(epostiAdapter);
        refreshLayout = view.findViewById(R.id.swipeeposti);
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.post(new Runnable() {
            @Override
            public void run() {
                refreshLayout.setRefreshing(true);
                loadEposti();
            }
        });
        return view;
    }

    private void loadEposti() {
        refreshLayout.setRefreshing(true);
        retrofit2.Call<Eposti> call = RestAPIHelper.ServiceApi(getActivity().getApplication()).getEposti();
        call.enqueue(new Callback<Eposti>() {
            @Override
            public void onResponse(Call<Eposti> call, Response<Eposti> response) {
                if (response.body() != null){
                    epostis = response.body().getEposti();
                    epostiAdapter = new EpostiAdapter(epostis, getActivity().getApplicationContext());
                    recyclerView.setAdapter(epostiAdapter);
                }else {
                    Toast.makeText(getContext(), "Error Response", Toast.LENGTH_SHORT).show();
                    Log.e("loadInfos","Error on Response");
                }
                refreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<Eposti> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("loadInfos","Error on Failure" + t.getMessage());
                refreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    public void onRefresh() {
        epostis.clear();
        epostiAdapter.notifyDataSetChanged();
        loadEposti();
    }
}
