package com.example.petagram.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.petagram.R;
import com.example.petagram.database.Database;
import com.example.petagram.pojo.PetItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PetAdapter extends RecyclerView.Adapter<PetAdapter.ViewHolder> {

    private ArrayList<PetItem> petItems;
    private Context context;
    private Database database;

    public PetAdapter(ArrayList<PetItem> petItems, Context context) {
        this.petItems = petItems;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        database = new Database(context);
        SharedPreferences prefs = context.getSharedPreferences("prefs", Context.MODE_PRIVATE);
        boolean firstStart = prefs.getBoolean("firstStart", true);
        if (firstStart){
            createTableOnFirstStart();
        }

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_home, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PetAdapter.ViewHolder holder, int position) {
        final PetItem petItem = petItems.get(position);

        readCursorData(petItem, holder);
        //holder.ivPetPic.setImageResource(petItem.getPetPic());
        Picasso.with(context)
                .load(petItem.getPetPic())
                .resize(600, 600)
                .centerCrop()
                .into(holder.ivPetPic);
        holder.tvPetName.setText(petItem.getPetName());
    }

    @Override
    public int getItemCount() {
        return petItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView   ivPetPic;
        private TextView    tvPetName;
        private Button      favBtn;

        public ViewHolder(View itemView) {
            super(itemView);

            ivPetPic    = itemView.findViewById(R.id.ivPetPic);
            tvPetName   = itemView.findViewById(R.id.tvPetName);
            favBtn      = itemView.findViewById(R.id.favBtn);

            //add to fav button
            favBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    PetItem petItem = petItems.get(position);

                    if (petItem.getFavStatus().equals("0")) {
                        petItem.setFavStatus("1");
                        database.insertIntoTheDatabase(petItem.getPetName(), petItem.getPetPic(), petItem.getKey_id(), petItem.getFavStatus());
                        favBtn.setBackgroundResource(R.drawable.ic_red_favorite_24);
                    } else {
                        petItem.setFavStatus("0");
                        database.remove_fav(petItem.getKey_id());
                        favBtn.setBackgroundResource(R.drawable.ic_shadow_favorite_24);
                    }
                }
            });
        }
    }

    private void createTableOnFirstStart() {
        database.insertEmpty();

        SharedPreferences prefs = context.getSharedPreferences("prefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("firstStart", false);
        editor.apply();
    }

    private void readCursorData(PetItem petItem, ViewHolder viewHolder) {
        Cursor cursor = database.read_all_data(petItem.getKey_id());
        SQLiteDatabase db = database.getReadableDatabase();
        try{
            while (cursor.moveToNext()) {
                String item_fav_status = cursor.getString(cursor.getColumnIndex(database.FAVORITE_STATUS));
                petItem.setFavStatus(item_fav_status);

                //check fav status
                if (item_fav_status != null && item_fav_status.equals("1")) {
                    viewHolder.favBtn.setBackgroundResource(R.drawable.ic_red_favorite_24);
                } else if (item_fav_status != null && item_fav_status.equals("0")) {
                    viewHolder.favBtn.setBackgroundResource(R.drawable.ic_shadow_favorite_24);
                }
            }
        } finally {
            if (cursor != null && cursor.isClosed()) {
                cursor.close();
            db.close();
            }
        }
    }

}
