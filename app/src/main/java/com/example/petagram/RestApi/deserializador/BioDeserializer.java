package com.example.petagram.RestApi.deserializador;

import android.util.Log;

import com.example.petagram.RestApi.JsonKeys;
import com.example.petagram.model.BioResponse;
import com.example.petagram.pojo.BioItem;
import com.example.petagram.pojo.PetItem;
import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class BioDeserializer implements JsonDeserializer<BioResponse> {
    @Override
    public BioResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        Gson gson = new Gson();
        BioResponse bioResponse = gson.fromJson(json, BioResponse.class);

        JsonObject jsonObject = json.getAsJsonObject().getAsJsonObject();
        Log.d("Pefil", jsonObject.toString());

        String biography = jsonObject.get(JsonKeys.BIOGRAPHY).getAsString();
        int followers_count = jsonObject.get(JsonKeys.FOLLOWERS_COUNT).getAsInt();
        int follows_count = jsonObject.get(JsonKeys.FOLLOWS_COUNT).getAsInt();
        int media_count = jsonObject.get(JsonKeys.MEDIA_COUNT).getAsInt();
        String name = jsonObject.get(JsonKeys.NAME).getAsString();
        String profile_picture_url = jsonObject.get(JsonKeys.PROFILE_PICTURE_URL).getAsString();
        String username = jsonObject.get(JsonKeys.USER_USER_NAME).getAsString();

        BioItem bioItem = new BioItem(biography, followers_count, follows_count, media_count, name, profile_picture_url, username);
        bioResponse.setBioItem(bioItem);

        return bioResponse;
    }
}
