package com.example.petagram.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.petagram.R;
import com.example.petagram.adapter.PetAdapter;
import com.example.petagram.pojo.PetItem;

import java.util.ArrayList;

public class FragmentHome extends Fragment {

    private ArrayList<PetItem> petItems = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new PetAdapter(petItems, getActivity()));
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        petItems.add(new PetItem(R.drawable.grogut, "Grogut", "0", "0"));
        petItems.add(new PetItem(R.drawable.mascota5, "Pink", "1", "0"));
        petItems.add(new PetItem(R.drawable.terror, "Terror", "2", "0"));
        petItems.add(new PetItem(R.drawable.barth, "Barth", "3", "0"));
        petItems.add(new PetItem(R.drawable.dalyn, "Dalyn", "4", "0"));
        petItems.add(new PetItem(R.drawable.mascota4, "Katy", "5", "0"));

        return view;
    }
}
