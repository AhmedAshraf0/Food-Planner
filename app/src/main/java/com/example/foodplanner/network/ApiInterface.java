package com.example.foodplanner.network;

import com.example.foodplanner.network.models.CategoryListModel;
import com.example.foodplanner.network.models.CountryListModel;
import com.example.foodplanner.network.models.GenericFilterModel;
import com.example.foodplanner.network.models.IngredientListModel;
import com.example.foodplanner.network.models.SearchMealsModel;
import com.example.foodplanner.network.models.SearchModel;
import com.example.foodplanner.network.models.SingleMealModel;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    //get random meal
    @GET("random.php")
    Observable<SingleMealModel> getRandomMeal();

    //----searching----
    //search by any text for meals or send "" ot get 25  random meals
    @GET("search.php")
    Single<SearchMealsModel> getSearchResult(@Query("s") String mealName);

    //---filtering---
    //get meals by country
    @GET("filter.php")
    Observable<GenericFilterModel> getMealsOfCountry(@Query("a") String country);

    //----requesting data---
    //get meals by category
    @GET("filter.php")
    Observable<GenericFilterModel> getMealsOfCategory(@Query("c") String category);

    //get meals by ingredient
    @GET("filter.php")
    Single<GenericFilterModel> getMealsOfIngredient(@Query("i") String ingredient);

    //get meal by id
    @GET("lookup.php")
    Single<SearchModel> getMealById(@Query("i") int mealId);

    //get categories list- category = list always
    @GET("list.php")
    Single<CategoryListModel> getAllCategories(@Query("c") String category);

    //get countries list- area = list always
    @GET("list.php")
    Single<CountryListModel> getAllCountries(@Query("a") String area);

    //get ingredients list- ingredient = list always
    @GET("list.php")
    Single<IngredientListModel> getAllIngredients(@Query("i") String ingredient);


}
