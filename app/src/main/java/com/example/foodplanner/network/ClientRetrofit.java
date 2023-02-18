package com.example.foodplanner.network;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.foodplanner.dashboard.presenter.NetworkDeligate;
import com.example.foodplanner.network.models.CategoryListModel;
import com.example.foodplanner.network.models.CategoryModel;
import com.example.foodplanner.network.models.CountryListModel;
import com.example.foodplanner.network.models.CountryModel;
import com.example.foodplanner.network.models.FilterMealModel;
import com.example.foodplanner.network.models.MealModel;
import com.example.foodplanner.network.models.SearchMealsModel;

import java.util.ArrayList;
import java.util.List;


import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
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
    private List<List<FilterMealModel>> twoCategoriesMeals , twoRandomMeals;
    private List<String> categoryNames , countryNames;
    private int randomCategory = 0 , randomCountry = 0;

    private ClientRetrofit() {
        Retrofit retrofit = new Retrofit
                .Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
        apiInterface = retrofit.create(ApiInterface.class);
        twoCategoriesMeals = new ArrayList<>();
        categoryNames = new ArrayList<>();
        twoRandomMeals = new ArrayList<>();
        countryNames = new ArrayList<>();
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
    public void callApi(NetworkDeligate networkDeligate) {//look at apiInterface. to understand
        //1.requesting list of categories
        @NonNull
        Single<List<CategoryModel>> observableCategories = apiInterface.getAllCategories(LIST_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(i -> i.getCategories());
        observableCategories.subscribe(
                categories -> {
                    Log.i(TAG, "callApi: S-getAllCategories()" + categories.size());
                    //2.sending this list to view
                    networkDeligate.setCategoryResponse(categories);
                    //3.getting random num to get two different categories of list
                    randomCategory = (int) (Math.random() * (categories.size() - 1));
                    randomCategory %= (categories.size() - 1);//not out of bounds
                    categoryNames.add(categories.get(randomCategory).getStrCategory());
                    //4.creating list that holds my two meals for two different categories to call networkdeligate once

                    //5.requesting first specific category
                    Observable<List<FilterMealModel>> observableCatMeals = apiInterface
                            .getMealsOfCategory(categories.get(randomCategory).getStrCategory())
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .map(i -> i.getMeals());
                    observableCatMeals.subscribe(
                            meals -> {
                                Log.i(TAG, "callApi: S-getAllCategories()-getMealsOfCategory()1 " + meals.get(0).getStrMeal());
                                twoCategoriesMeals.add(meals);
                            },
                            e -> Log.i(TAG, "callApi: E-getAllCategories()-getMealsOfCategory()1" + e.getMessage())
                    );
                    Log.i(TAG, "callApi: randomCategory-------" + categories.get(randomCategory).getStrCategory());
                    //6.requesting second specific category
                    Observable<List<FilterMealModel>> observableCatMeals2 = apiInterface
                            .getMealsOfCategory(categories.get((randomCategory + 1) % (categories.size() - 1))
                                    .getStrCategory())
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .map(i -> i.getMeals());
                    observableCatMeals2.subscribe(
                            meals -> {
                                categoryNames.add(categories.get((randomCategory + 1) % (categories.size() - 1)).getStrCategory());
                                Log.i(TAG, "callApi: S-getAllCategories()-getMealsOfCategory()2 " + meals.size());
                                Log.i(TAG, "testing---- 1 " + twoCategoriesMeals.size());
                                twoCategoriesMeals.add(meals);
                                Log.i(TAG, "testing 2 " + twoCategoriesMeals.size());
                                //7.sending list of two meals for different two categories using onComplete
                            },
                            e -> Log.i(TAG, "callApi: E-getAllCategories()-getMealsOfCategory()2" + e.getMessage()),
                            () -> networkDeligate.setCategoryMeals(twoCategoriesMeals, categoryNames)
                    );
                    //if it happened it will go to the next e
                    //----------------here twoCategoriesMeals list is zero not 2 ?!
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

                    //getting meals of two random countries
                    randomCountry = (int) (Math.random() * (countries.size() - 1));
                    randomCountry %= (countries.size() - 1);//not out of bounds
                    countryNames.add(countries.get(randomCountry).getStrArea());

                    //get meals for first country
                    Observable<List<FilterMealModel>> observableCountryMeals = apiInterface
                            .getMealsOfCountry(countries.get(randomCountry).getStrArea())
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .map(i->i.getMeals());
                    observableCountryMeals.subscribe(
                            filterMealModels -> {
                                Log.i(TAG, "callApi: S-1 -getAllCountries()->getMealsOfcountry() "+filterMealModels.size());
                                twoRandomMeals.add(filterMealModels);
                            },
                            e-> Log.i(TAG, "callApi: E-1 -getAllCountries()->getMealsOfcountry()" + e.getMessage())
                    );

                    //get meals for second country
                    Observable<List<FilterMealModel>> observableCountryMeals2 = apiInterface
                            .getMealsOfCountry(countries.get((randomCategory + 1) % (countries.size() - 1)).getStrArea())
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .map(i->i.getMeals());
                    observableCountryMeals2.subscribe(
                            filterMealModels -> {
                                countryNames.add(countries.get((randomCategory + 1) % (countries.size() - 1)).getStrArea());
                                Log.i(TAG, "callApi: S-2 -getAllCountries()->getMealsOfcountry() "+filterMealModels.size());
                                twoRandomMeals.add(filterMealModels);
                            },
                            e-> Log.i(TAG, "callApi: E-2 -getAllCountries()->getMealsOfcountry()" + e.getMessage()),
                            ()->networkDeligate.setRandomMealResponse(twoRandomMeals,countryNames)
                    );
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
}

//testing
/*
*
* apiInterface.getMealsOfCategory("Seafoo").enqueue(new Callback<GenericFilterModel>() {
            @Override
            public void onResponse(Call<GenericFilterModel> call, Response<GenericFilterModel> response) {
                //must check if not null
                Log.i(TAG, "onResponse: "+response.body().getMeals().size());
            }

            @Override
            public void onFailure(Call<GenericFilterModel> call, Throwable t) {
                Log.i(TAG, "onFailure: "+t.getMessage());
            }
        });
*
* */
