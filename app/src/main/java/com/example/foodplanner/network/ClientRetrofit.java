package com.example.foodplanner.network;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.foodplanner.dashboard.presenter.NetworkDeligate;
import com.example.foodplanner.network.models.CategoryModel;
import com.example.foodplanner.network.models.CountryModel;
import com.example.foodplanner.network.models.FilterMealModel;
import com.example.foodplanner.network.models.GenericFilterModel;
import com.example.foodplanner.network.models.MealModel;
import com.example.foodplanner.network.models.SearchModel;

import java.util.ArrayList;
import java.util.List;


import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ClientRetrofit implements RemoteDataSource {
    private static final String TAG = "ClientRetrofit class";
    private final String LIST_KEY = "list";
    private final String BASE_URL = "https://www.themealdb.com/api/json/v1/1/";
    private static ClientRetrofit instance = null;
    //to go here
    private ApiInterface apiInterface;

    private ClientRetrofit() {
        Retrofit retrofit = new Retrofit
                .Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
        apiInterface = retrofit.create(ApiInterface.class);
    }

    public static synchronized ClientRetrofit getInstance() {
        if (instance == null) {
            instance = new ClientRetrofit();
        }
        return instance;
    }

    //ana 3aez aklm presenter msh 3arf
    //option 1 to create object form presenter ->XXX cause 2 objects
    //option 2 create interface
    @SuppressLint("CheckResult")
    @Override
    public void callApi(NetworkDeligate networkDeligate) {
        //look at apiInterface. to understand
        @NonNull
        Single<List<CategoryModel>> observableCategories = apiInterface.getAllCategories(LIST_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(i -> i.getCategories());
        observableCategories.subscribe(
                categories -> {
                    Log.i(TAG, "callApi: S-getAllCategories()" + categories.size());
                    networkDeligate.setCategoryResponse(categories);
                },
                e -> Log.i(TAG, "callApi: E-getAllCategories()" + e.getMessage())
        );

        //requesting list of countries and request meals of them
        @NonNull
        Single<List<CountryModel>> observableCountries = apiInterface.getAllCountries(LIST_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(i -> i.getCountries());
        observableCountries.subscribe(
                countries -> {
                    Log.i(TAG, "callApi: S-getAllCountries()" + countries.size());
                    networkDeligate.setCountryResponse(countries);
                },
                e -> Log.i(TAG, "callApi: E-getAllCountries()" + e.getMessage())
        );

        //getting 25 random meals
        @NonNull
        Single<List<MealModel>> observableRandomMeals = apiInterface.getSearchResult("")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(i -> i.getMeals());
        observableRandomMeals.subscribe(
                meals -> {
                    Log.i(TAG, "callApi: S-getSearchResult()-RandomMeals" + meals.size());
                    networkDeligate.setRandomMealsResponse(meals);
                },
                e -> Log.i(TAG, "callApi: E-getSearchResult()-RandomMeals" + e.getMessage())
        );

        //
    }

    @SuppressLint("CheckResult")
    @Override
    public void requestMealsOfCategory(NetworkDeligate networkDeligate, String categoryName, int categoryNumber) {
        Single<List<FilterMealModel>> observableCatMeals = apiInterface
                .getMealsOfCategory(categoryName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(i -> i.getMeals());
        observableCatMeals.subscribe(
                meals -> {
                    Log.i(TAG, "callApi: S-requestMealsOfCategory() " + categoryNumber + "  " + meals.get(0).getStrMeal());
                    networkDeligate.setCategoryMeals(meals, categoryName, categoryNumber);
                },
                e -> Log.i(TAG, "callApi: E-requestMealsOfCategory()" + categoryNumber + "  " + e.getMessage())
        );
    }

    @SuppressLint("CheckResult")
    @Override
    public void requestMealsOfCountry(NetworkDeligate networkDeligate, String countryName, int countryNumber) {
        Single<List<FilterMealModel>> observableCountryMeals = apiInterface
                .getMealsOfCountry(countryName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(i -> i.getMeals());
        observableCountryMeals.subscribe(
                filterMealModels -> {
                    Log.i(TAG, "callApi: S-requestMealsOfCountry() "+ countryNumber + "  " + filterMealModels.size());
                    networkDeligate.setCountryMeals(filterMealModels,countryName,countryNumber);
                },
                e -> Log.i(TAG, "callApi: E-requestMealsOfCountry()"+ countryNumber + "  " + e.getMessage())
        );
    }

    @Override
    public void requestMealDetails(NetworkDeligate networkDeligate , int mealId , int type) {
        Single<List<MealModel>> observable = apiInterface
                .getMealById(mealId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(i->i.getMeals());
        observable.subscribe(
                mealModels -> {
                    Log.i(TAG, "callApi: S-requestMealDetails() "+mealModels.get(0).getStrMeal());
                    networkDeligate.setMealDetails(mealModels.get(0),type);
                },
                e-> Log.i(TAG, "callApi: E-requestMealDetails(): "+e.getMessage())
        );
    }

    @Override
    public void requestCountryMeals(NetworkDeligate networkDeligate, String strArea) {
        Single<List<FilterMealModel>> observable = apiInterface
                .getMealsOfCountry(strArea)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(i->i.getMeals());
        observable.subscribe(
                filterMealModels -> {
                    Log.i(TAG, "callApi: S-requestCountryMeals: ");
                    networkDeligate.setCountryAllMeals(filterMealModels);
                },
                e-> Log.i(TAG, "callApi: E-requestCountryMeals: ")
        );
    }

    @Override
    public void requestCategoryMeals(NetworkDeligate networkDeligate, String strCategory) {
        Single<List<FilterMealModel>> observable = apiInterface
                .getMealsOfCategory(strCategory)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(i->i.getMeals());
        observable.subscribe(
                filterMealModels -> {
                    Log.i(TAG, "callApi: S-requestCategoryMeals: ");
                    networkDeligate.setCategorySearchMeals(filterMealModels);
                },
                e-> Log.i(TAG, "callApi: E-requestCategoryMeals: ")
        );
    }
}