package com.example.petagram.adapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.transition.Explode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.app.ActivityOptionsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.petagram.Detail;
import com.example.petagram.R;
import com.example.petagram.pojo.ProfileItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ViewHolder> {

    private ArrayList<ProfileItem> profileItems;
    private Activity activity;

    public ProfileAdapter(ArrayList<ProfileItem> profileItems, Activity activity) {
        this.profileItems = profileItems;
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_profile, parent, false);

        return new ProfileAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProfileAdapter.ViewHolder holder, int position) {
        final ProfileItem profileItem = profileItems.get(position);
        //holder.ivPetPic.setImageResource(profileItem.getUrlPetPic());
        holder.tvLikes.setText(String.valueOf(profileItem.getLikes()));
        Picasso.with(activity)
                .load(profileItem.getUrlPetPic())
                .placeholder(R.drawable.grogut)
                .into(holder.ivPetPic);

        holder.ivPetPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, Detail.class);
                intent.putExtra("url", profileItem.getUrlPetPic());
                intent.putExtra("likes", profileItem.getLikes());

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Explode explode = new Explode();
                    explode.setDuration(1000);

                    activity.getWindow().setExitTransition(explode);
                    activity.startActivity(intent, ActivityOptionsCompat
                            .makeSceneTransitionAnimation(activity, view, activity.getString(R.string.pic_transition))
                            .toBundle());
                } else {
                    activity.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return profileItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivPetPic;
        private TextView tvLikes;

        public ViewHolder(View itemView) {
            super(itemView);

            ivPetPic = itemView.findViewById(R.id.ivPetPic);
            tvLikes = itemView.findViewById(R.id.tvLikes);
        }
    }
}
