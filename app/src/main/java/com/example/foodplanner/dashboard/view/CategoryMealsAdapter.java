package com.example.foodplanner.dashboard.view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodplanner.R;
import com.example.foodplanner.network.models.MealModel;

import java.util.ArrayList;
import java.util.List;

public class CategoryMealsAdapter extends RecyclerView.Adapter<CategoryMealsAdapter.ViewHolder>{
    private MealOfCategoryClicked mealOfCategoryClicked;
    private Context context;
    private List<MealModel> mealsOfCategory;

    public CategoryMealsAdapter(MealOfCategoryClicked mealOfCategoryClicked) {
        mealsOfCategory = new ArrayList<>();
        this.mealOfCategoryClicked = mealOfCategoryClicked;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView mealImage;
        private TextView mealTitle;
        private Button mealsButton;
        private CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mealImage = itemView.findViewById(R.id.meal_of_country_Image);
            mealTitle = itemView.findViewById(R.id.meal_of_country_Title);
            mealsButton = itemView.findViewById(R.id.meal_of_country_Button);
            cardView = itemView.findViewById(R.id.meal_of_country_CardView);
        }

        public ImageView getMealImage() {
            return mealImage;
        }
        public TextView getMealTitle() {
            return mealTitle;
        }

        public Button getMealsButton() {
            return mealsButton;
        }

        public CardView getCardView() {
            return cardView;
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater layoutInflater= LayoutInflater.from(parent.getContext());
        View currentView = layoutInflater.inflate(R.layout.meal_of_country,parent,false);
        ViewHolder viewHolder = new ViewHolder(currentView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.getMealTitle().setText(mealsOfCategory.get(position).getStrMeal());
        Glide.with(context).load(mealsOfCategory.get(position).getStrMealThumb())
                .into(holder.getMealImage());
        holder.getMealsButton().setOnClickListener(v -> {
            Log.i("TAG","pressed from Breakfast--------");
        });
        holder.getCardView().setOnClickListener(v->{
            mealOfCategoryClicked.onMealCardActor(mealsOfCategory.get(position));
        });
    }

    @Override
    public int getItemCount() {
        return mealsOfCategory.size();
    }

    public void setMealsOfCategory(List<MealModel> meals){
        mealsOfCategory = meals;
    }
}
