package com.example.foodplanner.network;

import android.util.Log;

import com.example.foodplanner.dashboard.presenter.NetworkDeligate;
import com.example.foodplanner.network.models.MealModel;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ClientRetrofit implements RemoteDataSource{
    private static final String TAG = "tag";
    private final String BASE_URL = "https://www.themealdb.com/api/json/v1/1/";
    private static ClientRetrofit instance = null;
    //to go here
    private ApiInterface apiInterface;
    private ClientRetrofit(){
        Retrofit retrofit = new Retrofit
                .Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiInterface = retrofit.create(ApiInterface.class);
    }
    public static synchronized ClientRetrofit getInstance(){
        if(instance == null){
            instance = new ClientRetrofit();
        }
        return instance;
    }

    //ana 3aez aklm presenter msh 3arf
    //option 1 to create object form presenter ->XXX cause 2 objects
    //option 2 create interface
    @Override
    public void callApi(NetworkDeligate networkDeligate) {
        apiInterface.getRandomMeal().enqueue(new Callback<MealModel>() {
            @Override
            public void onResponse(Call<MealModel> call, Response<MealModel> response) {
                networkDeligate.setResponse(response.body());
                Log.i(TAG,"onSuccess: hiiiii--------------");
                    Log.i(TAG,response.body().getIdMeal()+"");
            }

            @Override
            public void onFailure(Call<MealModel> call, Throwable t) {
                Log.i(TAG, "onFailure: ClientRetrofit.java");
            }
        });
    }
}
