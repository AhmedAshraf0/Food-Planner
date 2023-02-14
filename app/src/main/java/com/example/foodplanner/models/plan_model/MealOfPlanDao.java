package com.example.foodplanner.models.plan_model;

import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

public interface MealOfPlanDao {
    @Insert
    void insertMealOfDay(MealOfPlanModel mealModel);
    @Query("select * from meals_of_plan_table")
    List<MealOfPlanModel> getMealsOfDay();
}
