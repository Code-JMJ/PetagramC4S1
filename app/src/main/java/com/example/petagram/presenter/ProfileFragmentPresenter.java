package com.example.petagram.presenter;

import android.content.Context;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.petagram.MainActivity;
import com.example.petagram.fragment.IProfileFragmentView;
import com.example.petagram.model.BioResponse;
import com.example.petagram.model.PetResponse;
import com.example.petagram.pojo.BioItem;
import com.example.petagram.pojo.ProfileItem;
import com.example.petagram.RestApi.EndpointsAPI;
import com.example.petagram.RestApi.adapter.RestApiAdapter;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileFragmentPresenter implements IProfileFragmentPresenter {

    private IProfileFragmentView iProfileFragmentView;
    private Context context;
    private ArrayList<ProfileItem> profileItems;
    private BioItem bioItem;

    public ProfileFragmentPresenter(IProfileFragmentView iProfileFragmentView, Context context) {
        this.iProfileFragmentView = iProfileFragmentView;
        this.context = context;
        obtenerMediosRecientes();
        obtenerInformacionUsuario();
    }

    @Override
    public void obtenerMediosRecientes() {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gsonMediaRecent = restApiAdapter.buildGsonDeserializeMediaRecent();
        EndpointsAPI endpointsAPI = restApiAdapter.stablishConnectionRestAPInstagram(gsonMediaRecent);
        Call<PetResponse> petResponseCall = endpointsAPI.getRecentMedia();
        petResponseCall.enqueue(new Callback<PetResponse>() {
            @Override
            public void onResponse(Call<PetResponse> call, Response<PetResponse> response) {
                PetResponse petResponse = response.body();
                profileItems = petResponse.getProfileItems();
                mostrarContactosRecyclerView();

                MainActivity.profileItems = profileItems;
            }

            @Override
            public void onFailure(Call<PetResponse> call, Throwable t) {
                Toast.makeText(context, "Falló la conexión con servidor", Toast.LENGTH_LONG).show();
                Log.e("Connection failed", t.toString());
            }
        });
    }

    @Override
    public void obtenerInformacionUsuario() {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gsonBioInfo = restApiAdapter.builGsonDeserializerBioInfo();
        EndpointsAPI endpointsAPI = restApiAdapter.stablishConnectionRestAPInstagram(gsonBioInfo);
        Call<BioResponse> bioResponseCall = endpointsAPI.getBioInfo();
        bioResponseCall.enqueue(new Callback<BioResponse>() {
            @Override
            public void onResponse(Call<BioResponse> call, Response<BioResponse> response) {
                BioResponse bioResponse = response.body();
                bioItem = bioResponse.getBioItem();
                iProfileFragmentView.showProfile(bioItem);
            }

            @Override
            public void onFailure(Call<BioResponse> call, Throwable t) {
                Toast.makeText(context, "Falló la conexión con servidor", Toast.LENGTH_LONG).show();
                Log.e("Connection failed", t.toString());
            }
        });
    }

    @Override
    public void mostrarContactosRecyclerView() {
        iProfileFragmentView.initializeAdapter(iProfileFragmentView.createAdapter(profileItems));
        iProfileFragmentView.generateGridLayout();
    }
}
