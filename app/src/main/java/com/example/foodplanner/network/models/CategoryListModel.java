package com.example.foodplanner.network.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CategoryListModel {
    @SerializedName("meals")
    private List<CategoryModel> categories;

    public List<CategoryModel> getCategories() {
        return categories;
    }
}
