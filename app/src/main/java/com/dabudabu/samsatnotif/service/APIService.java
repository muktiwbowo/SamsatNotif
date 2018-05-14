package com.dabudabu.samsatnotif.service;

import com.dabudabu.samsatnotif.model.Eposti;
import com.dabudabu.samsatnotif.model.Event;
import com.dabudabu.samsatnotif.model.Informasi;
import com.dabudabu.samsatnotif.model.SamsatDesa;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIService {
    String BASE_URL = "https://heruary.000webhostapp.com/";

    @GET("samsatnotif/GetInformasi.php")
    Call<Informasi> getInformasi();

    @GET("samsatnotif/GetEposti.php")
    Call<Eposti> getEposti();

    @GET("samsatnotif/GetSamsatDesa.php")
    Call<SamsatDesa> getSamsatDesa();

    @GET("samsatnotif/GetEvent.php")
    Call<Event> getEvent();
}
