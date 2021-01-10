package com.example.petagram.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.petagram.R;
import com.example.petagram.adapter.ProfileAdapter;
import com.example.petagram.pojo.BioItem;
import com.example.petagram.pojo.ProfileItem;
import com.example.petagram.presenter.IProfileFragmentPresenter;
import com.example.petagram.presenter.ProfileFragmentPresenter;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class FragmentProfile extends Fragment implements IProfileFragmentView {

    private IProfileFragmentPresenter iProfileFragmentPresenter;
    private RecyclerView recyclerView;

    private CircularImageView civProfilePic;
    private TextView tvProfileName;
    private TextView tvProfileUsername;
    private TextView tvProfileDescription;
    private TextView tvPost;
    private TextView tvFollowers;
    private TextView tvFollwing;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        iProfileFragmentPresenter = new ProfileFragmentPresenter(this, getContext());
        recyclerView = view.findViewById(R.id.recyclerViewFragmentProfile);

        //recyclerView.setHasFixedSize(true);
        //recyclerView.setAdapter(new ProfileAdapter(profileItems, getActivity()));
        //recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));

        civProfilePic           = view.findViewById(R.id.civProfilePic);
        tvProfileName           = view.findViewById(R.id.tvProfileName);
        tvProfileUsername       = view.findViewById(R.id.tvProfileUsername);
        tvProfileDescription    = view.findViewById(R.id.tvProfileDescription);
        tvPost                  = view.findViewById(R.id.tvPost);
        tvFollowers             = view.findViewById(R.id.tvFollowers);
        tvFollwing              = view.findViewById(R.id.tvFollwing);

        return view;
    }

    @Override
    public void generateGridLayout() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);
        recyclerView.setLayoutManager(gridLayoutManager);
    }

    @Override
    public ProfileAdapter createAdapter(ArrayList<ProfileItem> profileItems) {
        ProfileAdapter profileAdapter = new ProfileAdapter(profileItems, getActivity());
        return profileAdapter;
    }

    @Override
    public void initializeAdapter(ProfileAdapter profileAdapter) {
        recyclerView.setAdapter(profileAdapter);
    }

    @Override
    public void showProfile(BioItem bioItem) {
        Picasso.with(getContext())
                .load(bioItem.getProfile_picture_url())
                .placeholder(R.drawable.grogut)
                .into(civProfilePic);
        tvProfileName.setText(bioItem.getName());
        tvProfileUsername.setText(bioItem.getUsername());
        tvProfileDescription.setText(bioItem.getBiography());
        tvPost.setText(String.valueOf(bioItem.getMedia_count()));
        tvFollowers.setText(String.valueOf(bioItem.getFollowers_count()));
        tvFollwing.setText(String.valueOf(bioItem.getFollows_count()));
    }
}