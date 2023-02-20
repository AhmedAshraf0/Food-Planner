package com.example.foodplanner.dashboard.presenter;

import android.util.Log;

import com.example.foodplanner.network.RemoteDataSource;
import com.example.foodplanner.network.models.CategoryModel;
import com.example.foodplanner.network.models.CountryModel;
import com.example.foodplanner.network.models.FilterMealModel;
import com.example.foodplanner.network.models.MealModel;

import java.util.List;

public class PresenterHome implements NetworkDeligate{
    private RemoteDataSource remoteDataSource;
    private CommunicatorHome communicatorHome;

    public PresenterHome(RemoteDataSource remoteDataSource , CommunicatorHome communicatorHome) {
        this.remoteDataSource = remoteDataSource;
        this.communicatorHome = communicatorHome;
    }

    public void getMeals() {
        remoteDataSource.callApi(this);
    }

     public void getCategory(String categoryName , int categoryNumber){
         remoteDataSource.requestMealsOfCategory(this,categoryName , categoryNumber);
     }

     public void getCountry(String countryName , int countryNumber){
        remoteDataSource.requestMealsOfCountry(this,countryName,countryNumber);
     }
     public void requestMealDetails(int mealId , int type){
        remoteDataSource.requestMealDetails(this,mealId,type);
     }
     public void requestCountryMeals(String strArea){
        remoteDataSource.requestCountryMeals(this,strArea);
     }
     public void requestCategoryMeals(String strCategory){
        remoteDataSource.requestCategoryMeals(this,strCategory);
     }

    @Override
    public void setCategoryResponse(List<CategoryModel> allCategories) {
        communicatorHome.getCategoryResponse(allCategories);
        Log.i("TAG", "setCategoryResponse: ----------------------");
    }

    @Override
    public void setCountryResponse(List<CountryModel> allCountries) {
        communicatorHome.getCountryResponse(allCountries);
    }

    @Override
    public void setRandomMealsResponse(List<MealModel> randomMeals) {
        communicatorHome.getRandomMealsResponse(randomMeals);
    }

    @Override
    public void setCategoryMeals(List<FilterMealModel> categoryMeals, String categoryName , int categoryNumber) {
        communicatorHome.getCategoryMeals(categoryMeals,categoryName,categoryNumber);
    }

    @Override
    public void setCountryMeals(List<FilterMealModel> countryMeals, String countryName, int countryNumber) {
        communicatorHome.getCountryMeals(countryMeals,countryName,countryNumber);
    }

    @Override
    public void setMealDetails(MealModel mealDetails , int type) {
        communicatorHome.getMealDetails(mealDetails , type);
    }

    @Override
    public void setCountryAllMeals(List<FilterMealModel> categoryMeals) {
        communicatorHome.getCountryAllMeals(categoryMeals);
    }

    @Override
    public void setCategorySearchMeals(List<FilterMealModel> categoryMeals) {
        communicatorHome.getCategorySearchMeals(categoryMeals);
    }

}
