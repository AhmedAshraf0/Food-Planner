package com.example.foodplanner.favorites;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodplanner.R;

import java.util.ArrayList;
import java.util.List;

public class FavoriteFragment extends Fragment {
    private RecyclerView favRecyclerView;
    private FavoritesAdapter favoritesAdapter;
    List<String> mealsTitles , mealTags;
    List<Integer> mealsPhotos;

    public FavoriteFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        favRecyclerView = view.findViewById(R.id.favRecyclerView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(view.getContext(),2);
        favRecyclerView.setLayoutManager(gridLayoutManager);
        mealsTitles =new ArrayList<>();
        mealsPhotos =new ArrayList<>();
        mealTags = new ArrayList<>();
        mealsTitles.add("first meal");
        mealsTitles.add("second meal");
        mealsTitles.add("third meal");
        mealsTitles.add("forth meal");
        mealsTitles.add("fifth meal");
        mealsPhotos.add(R.mipmap.image1);
        mealsPhotos.add(R.mipmap.image2);
        mealsPhotos.add(R.mipmap.image3);
        mealsPhotos.add(R.mipmap.image4);
        mealsPhotos.add(R.mipmap.image5);
        mealTags.add("Vegetarian,Spicy");
        mealTags.add("Vegetarian,Spicy");
        mealTags.add("Vegetarian,Spicy");
        mealTags.add("Vegetarian,Spicy");
        mealTags.add("Vegetarian,Spicy");
        favoritesAdapter = new FavoritesAdapter(mealsTitles,mealTags,mealsPhotos);
        favRecyclerView.setAdapter(favoritesAdapter);
    }
}