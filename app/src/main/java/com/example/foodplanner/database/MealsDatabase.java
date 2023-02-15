package com.example.foodplanner.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.foodplanner.models.favorites_model.FavoriteMealModel;
import com.example.foodplanner.models.meal_of_day_model.MealOfDayModel;
import com.example.foodplanner.models.plan_model.MealOfPlanModel;

@Database(entities = {FavoriteMealModel.class , MealOfDayModel.class , MealOfPlanModel.class} , version = 1)
public abstract class MealsDatabase extends RoomDatabase {
    private static MealsDatabase instance;

    public abstract MealsDao mealsDao();

    public static synchronized MealsDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    MealsDatabase.class,
                    "meals_app_database"
                    ).build();
        }
        return instance;
    }
}
