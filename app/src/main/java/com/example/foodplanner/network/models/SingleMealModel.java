package com.example.foodplanner.network.models;

import java.util.List;

public class SingleMealModel {
    private List<MealModel> meals;

    public MealModel getMeal() {
        return meals.get(0);
    }
}
