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
import com.dabudabu.samsatnotif.adapter.SamsatDesaAdapter;
import com.dabudabu.samsatnotif.model.ItemSamsatDesa;
import com.dabudabu.samsatnotif.model.SamsatDesa;
import com.dabudabu.samsatnotif.service.RestAPIHelper;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentSamsatDesa extends Fragment implements SwipeRefreshLayout.OnRefreshListener{

    private List<ItemSamsatDesa> desas = new ArrayList<>();
    private SamsatDesaAdapter desaAdapter;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout refreshLayout;

    public FragmentSamsatDesa() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_samsat_desa, container, false);
        recyclerView = view.findViewById(R.id.recyclesamsatdesa);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(desaAdapter);
        refreshLayout = view.findViewById(R.id.swipesamsatdesa);
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.post(new Runnable() {
            @Override
            public void run() {
                refreshLayout.setRefreshing(true);
                loadSamsatDesa();
            }
        });
        return view;
    }

    private void loadSamsatDesa() {
        refreshLayout.setRefreshing(true);
        retrofit2.Call<SamsatDesa> call = RestAPIHelper.ServiceApi(getActivity().getApplication()).getSamsatDesa();
        call.enqueue(new Callback<SamsatDesa>() {
            @Override
            public void onResponse(Call<SamsatDesa> call, Response<SamsatDesa> response) {
                if (response.body() != null){
                    desas = response.body().getSamsatdesa();
                    desaAdapter = new SamsatDesaAdapter(desas, getActivity().getApplicationContext());
                    recyclerView.setAdapter(desaAdapter);
                }else {
                    Toast.makeText(getContext(), "Error Response", Toast.LENGTH_SHORT).show();
                    Log.e("loadDesas","Error on Response");
                }
                refreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<SamsatDesa> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("loadDesas","Error on Failure" + t.getMessage());
                refreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    public void onRefresh() {
        desas.clear();
        desaAdapter.notifyDataSetChanged();
        loadSamsatDesa();
    }
}
