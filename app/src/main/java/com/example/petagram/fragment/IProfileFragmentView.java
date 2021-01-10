package com.example.petagram.fragment;

import com.example.petagram.adapter.ProfileAdapter;
import com.example.petagram.pojo.BioItem;
import com.example.petagram.pojo.ProfileItem;

import java.util.ArrayList;

public interface IProfileFragmentView {

    public void generateGridLayout();
    public ProfileAdapter createAdapter(ArrayList<ProfileItem> profileItems);
    public void initializeAdapter(ProfileAdapter profileAdapter);
    public void showProfile(BioItem bioItem);
}
