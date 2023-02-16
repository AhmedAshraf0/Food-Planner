package com.example.foodplanner.dashboard.presenter;

import com.example.foodplanner.network.RemoteDataSource;
import com.example.foodplanner.network.models.MealModel;

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

    @Override
    public void setResponse(MealModel body) {
        communicatorHome.getResponse(body);
    }
}
