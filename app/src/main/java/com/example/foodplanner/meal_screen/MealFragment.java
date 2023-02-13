package com.example.foodplanner.meal_screen;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.foodplanner.R;

import java.util.ArrayList;
import java.util.List;

public class MealFragment extends Fragment {
    private RecyclerView ingredentsRec;
    private IngredientsAdapter ingredientsAdapter;
    private List<String> measuresTest , ingredentsTest;
    private ImageView imageView;
    public MealFragment() {
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
        return inflater.inflate(R.layout.fragment_meal, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        measuresTest = new ArrayList<>();
        ingredentsTest = new ArrayList<>();
        ingredentsTest.add("Vegetable Oil");
        ingredentsTest.add("Onion");
        ingredentsTest.add("Garlic");
        ingredentsTest.add("Ginger");
        ingredentsTest.add("Coriander");
        measuresTest.add("1 tbls");
        measuresTest.add("1 finely chopped");
        measuresTest.add("2 cloves chopped");
        measuresTest.add("1 part");
        measuresTest.add("1 Packet");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
        ingredentsRec = view.findViewById(R.id.ingredients_rec);
        imageView = view.findViewById(R.id.headerImage);
        imageView.setImageResource(R.mipmap.image3);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        ingredentsRec.setLayoutManager(linearLayoutManager);
        ingredientsAdapter = new IngredientsAdapter(measuresTest,ingredentsTest);
        ingredentsRec.setAdapter(ingredientsAdapter);
    }
}