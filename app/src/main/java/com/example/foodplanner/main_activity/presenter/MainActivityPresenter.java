package com.example.foodplanner.main_activity.presenter;

import com.example.foodplanner.main_activity.MainActivityInterface;

public class MainActivityPresenter {
    MainActivityInterface mainActivityInterface;

    public MainActivityPresenter(MainActivityInterface mainActivityInterface) {
        this.mainActivityInterface = mainActivityInterface;
    }

    public void changeBottomNavBar(){
        mainActivityInterface.onChangeBottomNavBar();
    }
}
