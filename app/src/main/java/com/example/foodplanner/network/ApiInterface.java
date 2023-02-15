package com.example.foodplanner.network;

import com.example.foodplanner.network.models.CategoryModel;
import com.example.foodplanner.network.models.CountryModel;
import com.example.foodplanner.network.models.IngredientModel;
import com.example.foodplanner.network.models.MealModel;
import com.example.foodplanner.network.models.FilterForMealModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    //get random meal
    @GET("random.php")
    Call<MealModel> getRandomMeal();

    //----searching----
    //search by any text for meals
    @GET("search.php")
    Call<List<MealModel>> getSearchResult(@Query("s") String mealName);

    //---filtering---
    //get meals by country
    @GET("filter.php")
    Call<List<FilterForMealModel>> getMealsOfCountry(@Query("a") String country);

    //----requesting data---
    //get meals by category
    @GET("filter.php")
    Call<List<FilterForMealModel>> getMealsOfCategory(@Query("c") String category);

    //get meals by ingredient
    @GET("filter.php")
    Call<List<FilterForMealModel>> getMealsOfIngredient(@Query("i") String ingredient);

    //get meal by id
    @GET("lookup.php")
    Call<List<MealModel>> getMealById(@Query("i") int mealId);

    //get categories list- category = list always
    @GET("list.php")
    Call<List<CategoryModel>> getAllCategories(@Query("c") String category);

    //get countries list- area = list always
    @GET("list.php")
    Call<List<CountryModel>> getAllCountries(@Query("a") String area);

    //get ingredients list- ingredient = list always
    @GET("list.php")
    Call<List<IngredientModel>> getAllIngredients(@Query("i") String ingredient);


}
