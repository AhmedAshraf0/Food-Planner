package com.example.foodplanner.network;

import com.example.foodplanner.dashboard.presenter.NetworkDeligate;

public interface RemoteDataSource {

    void callApi(NetworkDeligate networkDeligate);
    void requestMealsOfCategory(NetworkDeligate networkDeligate , String categoryName ,
                                int categoryNumber);
    void requestMealsOfCountry(NetworkDeligate networkDeligate , String countryName ,
                               int countryNumber);
    void requestMealDetails(NetworkDeligate networkDeligate , int mealId , int type);
    void requestCountryMeals(NetworkDeligate networkDeligate , String strArea);
    void requestCategoryMeals(NetworkDeligate networkDeligate , String strCategory);
}
