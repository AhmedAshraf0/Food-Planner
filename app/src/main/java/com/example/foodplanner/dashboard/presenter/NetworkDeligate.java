package com.example.foodplanner.dashboard.presenter;

import com.example.foodplanner.network.models.CategoryModel;
import com.example.foodplanner.network.models.CountryModel;
import com.example.foodplanner.network.models.FilterMealModel;
import com.example.foodplanner.network.models.MealModel;

import java.util.List;

public interface NetworkDeligate {

    void setCategoryResponse(List<CategoryModel> allCategories);
    void setCountryResponse(List<CountryModel> allCountries);
    void setRandomMealsResponse(List<MealModel> randomMeals);
    void setCategoryMeals(List<FilterMealModel> categoryMeals , String categoryName , int categoryNumber);
    void setCountryMeals(List<FilterMealModel> countryMeals , String countryName , int countryNumber);
}
