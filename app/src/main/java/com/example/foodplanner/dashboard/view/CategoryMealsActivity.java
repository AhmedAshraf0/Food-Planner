package com.example.foodplanner.dashboard.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.foodplanner.R;
import com.example.foodplanner.meal_screen.MealActivity;
import com.example.foodplanner.network.models.MealModel;

import java.util.List;

public class CategoryMealsActivity extends AppCompatActivity implements MealOfCategoryClicked{

    private static final String TAG = "CountryMealsActivity";
    private CategoryMealsAdapter categoryMealsAdapter;
    private RecyclerView categoriesRecyclerView;
    private List<MealModel> meals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_meal);
        categoryMealsAdapter = new CategoryMealsAdapter(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        categoriesRecyclerView = findViewById(R.id.meals_of_countries_rec);
        categoriesRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        categoriesRecyclerView.setAdapter(categoryMealsAdapter);

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("categoriesBundle");
        meals = (List<MealModel>) bundle.getSerializable("categoriesList");
        Log.i(TAG, "onCreate: "+meals.size());
        categoryMealsAdapter.setMealsOfCategory(meals);
        categoryMealsAdapter.notifyDataSetChanged();
    }

    @Override
    public void onMealCardActor(MealModel mealModel) {
        Intent i = new Intent(this, MealActivity.class);
        i.putExtra("meal",mealModel);
        startActivity(i);
    }
}