package com.example.petagram.firebase.RestApi;

import com.example.petagram.firebase.model.InstagramResponse;
import com.example.petagram.firebase.model.UserResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface EndpointsFirebaseHeroku {

    /*@FormUrlEncoded
    @POST(ConstantsFirebaseHeroku.KEY_POST_ID_TOKEN)
    Call<UserResponse> registrarTokenID(@Field("token") String token);*/

    @FormUrlEncoded
    @POST(ConstantsFirebaseHeroku.KEY_POST_ID_INSTAGRAM)
    Call<UserResponse> registrarUsuarioInstagram(@Field("token") String token,
                                                      @Field("id_usuario_instagram") String id_usuario_instagram);

}
