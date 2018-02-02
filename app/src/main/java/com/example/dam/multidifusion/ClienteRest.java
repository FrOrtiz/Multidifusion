package com.example.dam.multidifusion;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;


public interface ClienteRest {
    @GET("llamada")
    Call<ArrayList<Llamada>> getLlamada();

    @POST("llamada")
    Call<Llamada> postLlamada(@Body Llamada llamada);
}