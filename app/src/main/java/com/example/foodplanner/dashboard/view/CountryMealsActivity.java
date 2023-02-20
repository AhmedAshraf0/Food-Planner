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

public class CountryMealsActivity extends AppCompatActivity implements MealOfCountryClicked{
    private static final String TAG = "CountryMealsActivity";
    private CountryMealsAdapter countryMealsAdapter;
    private RecyclerView mealsRecyclerView;
    private List<MealModel> meals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_meals);

        countryMealsAdapter = new CountryMealsAdapter(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mealsRecyclerView = findViewById(R.id.meals_of_countries_rec);
        mealsRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        mealsRecyclerView.setAdapter(countryMealsAdapter);

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("mealsBundle");
        meals = (List<MealModel>) bundle.getSerializable("mealsList");
        Log.i(TAG, "onCreate: "+meals.size());
        countryMealsAdapter.setMealsOfCountry(meals);
        countryMealsAdapter.notifyDataSetChanged();
    }

    @Override
    public void onMealCardActor(MealModel mealModel) {
        Intent i = new Intent(this, MealActivity.class);
        i.putExtra("meal",mealModel);
        startActivity(i);
    }
}