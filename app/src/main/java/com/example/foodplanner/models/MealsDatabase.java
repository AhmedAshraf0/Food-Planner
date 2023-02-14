package com.example.foodplanner.models;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.foodplanner.models.favorites_model.FavoriteMealDao;
import com.example.foodplanner.models.favorites_model.FavoriteMealModel;
import com.example.foodplanner.models.meal_of_day_model.MealOfDayDao;
import com.example.foodplanner.models.meal_of_day_model.MealOfDayModel;
import com.example.foodplanner.models.plan_model.MealOfPlanModel;

@Database(entities = {FavoriteMealModel.class , MealOfDayModel.class , MealOfPlanModel.class} , version = 1)
abstract class MealsDatabase extends RoomDatabase {
    private static MealsDatabase instance;

    public abstract FavoriteMealDao mealDao();
    public abstract MealOfDayDao mealOfDayDao();
    public abstract MealOfPlanModel mealOfPlanModel();

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
