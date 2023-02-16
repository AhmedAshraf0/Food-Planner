package com.example.foodplanner.network.models;

import java.util.List;

//it could be used to get 25 random meals by passing empty string or to regular search
public class SearchMealsModel {
    private List<MealModel> meals;

    public List<MealModel> getMeals() {
        return meals;
    }
}
