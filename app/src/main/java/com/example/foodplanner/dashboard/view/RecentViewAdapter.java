package com.example.foodplanner.dashboard.view;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodplanner.R;
import com.example.foodplanner.meal_screen.MealFragment;

import java.util.List;

public class RecentViewAdapter extends RecyclerView.Adapter<RecentViewAdapter.ViewHolder> {
    List<String> meals;
    List<Integer> mealsPhotos;
    FragmentManager fragmentManager;

    public RecentViewAdapter(List<String> meals, List<Integer> mealsPhotos , FragmentManager fragmentManager) {
        this.meals = meals;
        this.mealsPhotos = mealsPhotos;
        this.fragmentManager = fragmentManager;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private CardView cardView;
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
            cardView = itemView.findViewById(R.id.mealCardView);
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

        public CardView getCardView() {
            return cardView;
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
//        holder.getCardView().setOnClickListener(v -> {
//            FragmentTransaction transaction = fragmentManager.beginTransaction();
//            transaction.replace(R.id.main_activity,new MealFragment());
//            transaction.commit();
//        });
    }

    @Override
    public int getItemCount() {
        return meals.size();
    }
}
