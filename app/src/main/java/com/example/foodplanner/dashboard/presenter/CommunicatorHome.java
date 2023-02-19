package com.example.foodplanner.dashboard.presenter;

import com.example.foodplanner.network.models.CategoryModel;
import com.example.foodplanner.network.models.CountryModel;
import com.example.foodplanner.network.models.FilterMealModel;
import com.example.foodplanner.network.models.MealModel;

import java.util.List;

public interface CommunicatorHome {
    void getCategoryResponse(List<CategoryModel> allCategories);
    void getCountryResponse(List<CountryModel> allCountries);
    void getRandomMealsResponse(List<MealModel> randomMeals);
    void getCategoryMeals(List<FilterMealModel> categoryMeals , String categoryName , int categoryNumber);
    void getCountryMeals(List<FilterMealModel> countryMeals , String countryName , int countryNumber);
    void getMealDetails(MealModel mealDetails);
}
