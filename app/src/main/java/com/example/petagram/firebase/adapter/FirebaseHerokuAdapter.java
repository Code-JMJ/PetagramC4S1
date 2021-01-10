package com.example.petagram.firebase.adapter;

import com.example.petagram.firebase.RestApi.ConstantsFirebaseHeroku;
import com.example.petagram.firebase.RestApi.EndpointsFirebaseHeroku;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FirebaseHerokuAdapter {

    public EndpointsFirebaseHeroku establercerConexionFirebaseHeroku() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantsFirebaseHeroku.ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(EndpointsFirebaseHeroku.class);
    }

    /*public EndpointsFirebaseHeroku establercerInstagramFirebaseHeroku() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantsFirebaseHeroku.ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(EndpointsFirebaseHeroku.class);
    }*/
}
