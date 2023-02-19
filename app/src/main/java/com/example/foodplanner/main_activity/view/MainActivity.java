package com.example.foodplanner.main_activity.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

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

        NavController navController = Navigation.findNavController(this,R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(binding.bottomNavBar,navController);
    }
}