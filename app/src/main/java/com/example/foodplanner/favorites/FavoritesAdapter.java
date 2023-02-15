package com.example.foodplanner.favorites;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodplanner.R;
import com.example.foodplanner.dashboard.view.Category1Adapter;

import java.util.List;

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.ViewHolder> {
    List<String> mealNames , mealTags;
    List<Integer> mealsPhotos;
    class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView mealImage;
        private TextView mealTitle , mealTags;
        private Button removeFav;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mealImage = itemView.findViewById(R.id.mealImage);
            mealTitle = itemView.findViewById(R.id.mealTitle);
            mealTags = itemView.findViewById(R.id.mealTags);
            removeFav = itemView.findViewById(R.id.removeFromFavs);
        }

        public TextView getMealTags() {
            return mealTags;
        }

        public Button getRemoveFav() {
            return removeFav;
        }

        public ImageView getMealImage() {
            return mealImage;
        }

        public TextView getMealTitle() {
            return mealTitle;
        }
    }

    public FavoritesAdapter(List<String> mealNames, List<String> mealTags, List<Integer> mealsPhotos) {
        this.mealNames = mealNames;
        this.mealTags = mealTags;
        this.mealsPhotos = mealsPhotos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater= LayoutInflater.from(parent.getContext());
        View currentView = layoutInflater.inflate(R.layout.favorite_card,parent,false);
        FavoritesAdapter.ViewHolder viewHolder = new FavoritesAdapter.ViewHolder(currentView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.getMealImage().setImageResource(mealsPhotos.get(position));
        holder.getMealTitle().setText(mealNames.get(position));
        holder.getMealTags().setText(mealTags.get(position));
        holder.getRemoveFav().setOnClickListener(v -> Log.i("TAG","REmove is pressed"));
    }

    @Override
    public int getItemCount() {
        return mealsPhotos.size();
    }
}
