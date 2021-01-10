package com.example.petagram.RestApi.deserializador;

import com.example.petagram.model.PetResponse;
import com.example.petagram.pojo.ProfileItem;
import com.example.petagram.RestApi.JsonKeys;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class PetDeserializador implements JsonDeserializer<PetResponse> {
    @Override
    public PetResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        Gson gson = new Gson();
        PetResponse petResponse = gson.fromJson(json, PetResponse.class);
        JsonArray petResponseData = json.getAsJsonObject().getAsJsonArray(JsonKeys.MEDIA_RESPONSE_ARRAY);
        petResponse.setProfileItems(deserializarPetDeJson(petResponseData));
        return petResponse;

    }

    private ArrayList<ProfileItem> deserializarPetDeJson(JsonArray petResponseData) {
        ArrayList<ProfileItem> profileItems = new ArrayList<>();
        for (int i = 0; i < petResponseData.size(); i++) {

            JsonObject petResponseDataObject    = petResponseData.get(i).getAsJsonObject();
            String id                           = petResponseDataObject.get(JsonKeys.USER_ID).getAsString();
            String username                     = petResponseDataObject.get(JsonKeys.USER_USER_NAME).getAsString();
            String media_url                    = petResponseDataObject.get(JsonKeys.MEDIA_URL).getAsString();
            int media_likes                     = petResponseDataObject.get(JsonKeys.MEDIA_LIKES).getAsInt();

            ProfileItem currentPetProfile = new ProfileItem();
            currentPetProfile.setId(id);
            currentPetProfile.setPetName(username);
            currentPetProfile.setUrlPetPic(media_url);
            currentPetProfile.setLikes(media_likes);

            profileItems.add(currentPetProfile);
            
        }
        return profileItems;
    }
}
