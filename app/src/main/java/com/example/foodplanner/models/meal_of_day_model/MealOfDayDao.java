package com.example.foodplanner.models.meal_of_day_model;

import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

public interface MealOfDayDao {
    @Insert
    void insertMealOfDay(MealOfDayModel mealModel);
    @Query("select * from meals_of_day_table")
    List<MealOfDayModel> getMealsOfDay();
}
