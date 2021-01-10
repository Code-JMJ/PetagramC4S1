package com.example.petagram.model;

import com.example.petagram.pojo.ProfileItem;

import java.util.ArrayList;

public class PetResponse {
    ArrayList<ProfileItem> profileItems;

    public ArrayList<ProfileItem> getProfileItems() {
        return profileItems;
    }

    public void setProfileItems(ArrayList<ProfileItem> profileItems) {
        this.profileItems = profileItems;
    }
}
