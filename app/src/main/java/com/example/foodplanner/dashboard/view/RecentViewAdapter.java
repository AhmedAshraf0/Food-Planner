package com.example.foodplanner.dashboard.view;

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

import java.util.List;

public class RecentViewAdapter extends RecyclerView.Adapter<RecentViewAdapter.ViewHolder> {
    List<String> meals;
    List<Integer> mealsPhotos;

    public RecentViewAdapter(List<String> meals, List<Integer> mealsPhotos) {
        this.meals = meals;
        this.mealsPhotos = mealsPhotos;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView mealImage;
        private ImageButton favBtn;
        private TextView mealTitle;
        private Button addBtn;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mealImage = itemView.findViewById(R.id.mealImage);
            favBtn = itemView.findViewById(R.id.buttonF);
            mealTitle = itemView.findViewById(R.id.mealTitle);
            addBtn = itemView.findViewById(R.id.addToScedule);
        }

        public ImageView getMealImage() {
            return mealImage;
        }

        public ImageButton getFavBtn() {
            return favBtn;
        }

        public TextView getMealTitle() {
            return mealTitle;
        }

        public Button getAddBtn() {
            return addBtn;
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater= LayoutInflater.from(parent.getContext());
        View currentView = layoutInflater.inflate(R.layout.meal_cardview,parent,false);
        ViewHolder viewHolder = new ViewHolder(currentView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.getMealImage().setImageResource(mealsPhotos.get(position));
        holder.getMealTitle().setText(meals.get(position));
        //actions on btns or whole card
    }

    @Override
    public int getItemCount() {
        return meals.size();
    }
}
