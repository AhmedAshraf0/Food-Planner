package com.example.foodplanner.dashboard.view;

import android.content.Context;
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

import com.bumptech.glide.Glide;
import com.example.foodplanner.R;
import com.example.foodplanner.network.models.FilterMealModel;

import java.util.ArrayList;
import java.util.List;

public class RandomCountryAdapter extends RecyclerView.Adapter<RandomCountryAdapter.ViewHolder> {
    private List<FilterMealModel> filterMealModel;
    private Context context;

    public RandomCountryAdapter() {
        filterMealModel = new ArrayList<>();
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
        context = parent.getContext();
        LayoutInflater layoutInflater= LayoutInflater.from(parent.getContext());
        View currentView = layoutInflater.inflate(R.layout.meal_cardview,parent,false);
        ViewHolder viewHolder = new ViewHolder(currentView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(filterMealModel.get(position).getStrMealThumb()+"/preview")
                .override(150,150)
                .into(holder.getMealImage());
        holder.getMealTitle().setText(filterMealModel.get(position).getStrMeal());
        holder.getAddBtn().setOnClickListener(v -> {
            Log.i("TAG","pressed from Breakfast--------");
        });
    }

    @Override
    public int getItemCount() {
        return filterMealModel.size();
    }
    public void setMealsOfCountry(List<FilterMealModel> filterMealModel) {
        if(filterMealModel == null){
            Log.i("TAG", "setCategoryModel: null");
        }
        this.filterMealModel = filterMealModel;
    }
}
