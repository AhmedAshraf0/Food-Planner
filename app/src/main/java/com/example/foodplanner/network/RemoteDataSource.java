package com.example.foodplanner.network;

import com.example.foodplanner.dashboard.presenter.NetworkDeligate;

public interface RemoteDataSource {

    void callApi(NetworkDeligate networkDeligate);
    void requestMealsOfCategory(NetworkDeligate networkDeligate , String categoryName ,
                                int categoryNumber);
    void requestMealsOfCountry(NetworkDeligate networkDeligate , String countryName ,
                               int countryNumber);
    //implement this method
    //after implementing it should send data through networkDeligate
    //so new method in networkDeligate & communicator
    //method in networkdeligate should send adapternumber if one
    //so data it's coming for categoryone and if two it's for the other one
    //with the same function
}
