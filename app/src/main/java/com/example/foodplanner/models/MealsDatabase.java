package com.example.foodplanner.models;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = MealModel.class , version = 1)
abstract class MealsDatabase extends RoomDatabase {
    private static MealsDatabase instance;

    public abstract MealDao mealDao();

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
