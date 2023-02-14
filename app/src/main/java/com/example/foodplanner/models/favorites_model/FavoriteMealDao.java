package com.example.foodplanner.models.favorites_model;

import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

public interface FavoriteMealDao {
    @Insert
    void insertMeal(FavoriteMealModel favoriteMealModel);
    @Query("select * from favorites_table")
    List<FavoriteMealModel> getMeals();
}
