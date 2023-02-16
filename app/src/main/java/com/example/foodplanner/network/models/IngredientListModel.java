package com.example.foodplanner.network.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class IngredientListModel {
    @SerializedName("meals")
    private List<IngredientModel> ingredients;

    public List<IngredientModel> getIngredients() {
        return ingredients;
    }
}
