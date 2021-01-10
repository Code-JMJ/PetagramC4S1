package com.example.petagram.RestApi;

import com.example.petagram.model.BioResponse;
import com.example.petagram.model.PetResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface EndpointsAPI {

    @GET(ConstantsRestApi.URL_USER_MEDIA)
    Call<PetResponse> getRecentMedia();

    @GET(ConstantsRestApi.URL_USER_BIO)
    Call<BioResponse> getBioInfo();
}
