package com.example.foodplanner.main_activity.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.foodplanner.R;
import com.example.foodplanner.dashboard.view.HomeFragment;
import com.example.foodplanner.databinding.ActivityMainBinding;
import com.example.foodplanner.favorites.FavoriteFragment;
import com.example.foodplanner.meal_screen.MealFragment;
import com.example.foodplanner.planner.PlannerFragment;
import com.example.foodplanner.search.SearchFragment;
import com.example.foodplanner.setting.SettingFragment;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        replaceFragment(new HomeFragment());
        binding.bottomNavBar.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        replaceFragment(new HomeFragment());
                        break;
                    case R.id.search:
                        replaceFragment(new SearchFragment());
                        break;
                    case R.id.calender:
                        replaceFragment(new PlannerFragment());
                        break;
                    case R.id.favorites:
                        replaceFragment(new FavoriteFragment());
                        break;
                    case R.id.settings:
                        replaceFragment(new SettingFragment());
                        break;
                }
                return true;
            }
        });
    }

    public void replaceFragment(Fragment newFragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.mainContainer,newFragment);
        transaction.commit();
    }
}