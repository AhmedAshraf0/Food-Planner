package com.example.foodplanner.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.foodplanner.models.favorites_model.FavoriteMealModel;
import com.example.foodplanner.models.meal_of_day_model.MealOfDayModel;
import com.example.foodplanner.models.plan_model.MealOfPlanModel;

import java.util.List;

@Dao
public interface MealsDao {
    @Insert
    void insertFavorite(FavoriteMealModel favoriteMealModel);
    @Query("select * from favorites_table")
    List<FavoriteMealModel> getFavorites();

    @Insert
    void insertMealOfDay(MealOfDayModel mealModel);
    @Query("select * from meals_of_day_table")
    List<MealOfDayModel> getMealsOfDay();

    @Insert
    void insertMealToPlan(MealOfPlanModel mealModel);
    @Query("select * from meals_of_plan_table")
    List<MealOfPlanModel> getMealsOfPlan();
}
