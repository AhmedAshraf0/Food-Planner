package com.example.foodplanner.dashboard.presenter;

import com.example.foodplanner.network.models.MealModel;

public interface CommunicatorHome {
    void getResponse(MealModel body);
}
