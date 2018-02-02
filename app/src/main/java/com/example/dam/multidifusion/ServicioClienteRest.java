package com.example.dam.multidifusion;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import java.util.ArrayList;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServicioClienteRest extends Service {

    public ServicioClienteRest() {

    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        int state = intent.getIntExtra("state", -1);
        String phone = intent.getStringExtra("phone");
        String tipo = intent.getStringExtra("tipo");

        Retrofit retrofit =
                new Retrofit.Builder()
                        .baseUrl("http://json-franor21.c9users.io:8080/")//direccion base del servidor REST
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

        ClienteRest cliente = retrofit.create(ClienteRest.class);

        Call<Llamada> call = null;

        switch (state){
            case 0:
//                call = cliente.postLlamada(new Llamada("inactiva", phone, tipo, new Date()));
                call = cliente.postLlamada(new Llamada("inactiva", phone, tipo));
                break;
            case 1:
//                call = cliente.postLlamada(new Llamada("sonando", phone, tipo, new Date()));
                call = cliente.postLlamada(new Llamada("sonando", phone, tipo));
                break;
            case 2:
//                call = cliente.postLlamada(new Llamada("descolgado", phone, tipo, new Date()));
                call = cliente.postLlamada(new Llamada("descolgado", phone, tipo));
            break;
        }

        call.enqueue(new Callback<Llamada>() {
            @Override
            public void onResponse(Call<Llamada> call,
                                   Response<Llamada> response) {}
            @Override
            public void onFailure(Call<Llamada> call, Throwable t) {
                t.getLocalizedMessage();
            }
        });

        Call<ArrayList<Llamada>> call2 = cliente.getLlamada();
        call2.enqueue(new Callback<ArrayList<Llamada>>() {
            @Override
            public void onResponse(Call<ArrayList<Llamada>> call, Response<ArrayList<Llamada>> response) {}

            @Override
            public void onFailure(Call<ArrayList<Llamada>> call, Throwable t) {
                t.getLocalizedMessage();
            }
        });

        return START_REDELIVER_INTENT;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
