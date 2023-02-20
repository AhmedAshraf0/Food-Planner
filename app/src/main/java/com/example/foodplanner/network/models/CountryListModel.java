package com.example.foodplanner.network.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CountryListModel {
    @SerializedName("meals")
    private List<CountryModel> countries;

    public List<CountryModel> getCountries() {
        return countries;
    }
}
