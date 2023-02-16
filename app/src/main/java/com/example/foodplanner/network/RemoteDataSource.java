package com.example.foodplanner.network;

import com.example.foodplanner.dashboard.presenter.NetworkDeligate;

public interface RemoteDataSource {

    void callApi(NetworkDeligate networkDeligate);
}
