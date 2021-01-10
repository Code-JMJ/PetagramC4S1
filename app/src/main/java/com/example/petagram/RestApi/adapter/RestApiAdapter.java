package com.example.petagram.RestApi.adapter;

import com.example.petagram.RestApi.deserializador.BioDeserializer;
import com.example.petagram.model.BioResponse;
import com.example.petagram.model.PetResponse;
import com.example.petagram.RestApi.ConstantsRestApi;
import com.example.petagram.RestApi.EndpointsAPI;
import com.example.petagram.RestApi.deserializador.PetDeserializador;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestApiAdapter {

    public EndpointsAPI stablishConnectionRestAPInstagram(Gson gson) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantsRestApi.ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit.create(EndpointsAPI.class);
    }

    public Gson buildGsonDeserializeMediaRecent() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(PetResponse.class, new PetDeserializador());
        return gsonBuilder.create();
    }

    public Gson builGsonDeserializerBioInfo() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(BioResponse.class, new BioDeserializer());
        return gsonBuilder.create();
    }
}
