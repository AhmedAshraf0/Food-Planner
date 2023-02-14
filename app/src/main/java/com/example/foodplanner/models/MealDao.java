package com.example.foodplanner.models;

import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

public interface MealDao {
    @Insert
    void insertMeal(MealModel mealModel);
    @Query("select * from favorites_table")
    List<MealModel> getMeals();
}
