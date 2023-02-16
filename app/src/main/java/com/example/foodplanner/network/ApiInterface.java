package com.example.foodplanner.network;

import com.example.foodplanner.network.models.CategoryListModel;
import com.example.foodplanner.network.models.CountryListModel;
import com.example.foodplanner.network.models.GenericFilterModel;
import com.example.foodplanner.network.models.IngredientListModel;
import com.example.foodplanner.network.models.SearchMealsModel;
import com.example.foodplanner.network.models.SearchModel;
import com.example.foodplanner.network.models.SingleMealModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    //get random meal
    @GET("random.php")
    Call<SingleMealModel> getRandomMeal();

    //----searching----
    //search by any text for meals
    @GET("search.php")
    Call<SearchMealsModel> getSearchResult(@Query("s") String mealName);

    //---filtering---
    //get meals by country
    @GET("filter.php")
    Call<GenericFilterModel> getMealsOfCountry(@Query("a") String country);

    //----requesting data---
    //get meals by category
    @GET("filter.php")
    Call<GenericFilterModel> getMealsOfCategory(@Query("c") String category);

    //get meals by ingredient
    @GET("filter.php")
    Call<GenericFilterModel> getMealsOfIngredient(@Query("i") String ingredient);

    //get meal by id
    @GET("lookup.php")
    Call<SearchModel> getMealById(@Query("i") int mealId);

    //get categories list- category = list always
    @GET("list.php")
    Call<CategoryListModel> getAllCategories(@Query("c") String category);

    //get countries list- area = list always
    @GET("list.php")
    Call<CountryListModel> getAllCountries(@Query("a") String area);

    //get ingredients list- ingredient = list always
    @GET("list.php")
    Call<IngredientListModel> getAllIngredients(@Query("i") String ingredient);


}
