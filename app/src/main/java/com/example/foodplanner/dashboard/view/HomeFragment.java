package com.example.foodplanner.dashboard.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodplanner.R;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private ViewPager2 viewPager2;
    private RecyclerView recentRec;
    private RecentViewAdapter recentViewAdapter;
    private List<SliderItem> images;
    private List<String> meals;
    private List<Integer> mealsPhotos;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewPager2 = view.findViewById(R.id.viewPager);
        recentRec = view.findViewById(R.id.recentRec);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recentRec.setLayoutManager(linearLayoutManager);

        images = new ArrayList<>();
        meals =new ArrayList<>();
        mealsPhotos =new ArrayList<>();
        meals.add("first meal");
        meals.add("second meal");
        meals.add("third meal");
        meals.add("forth meal");
        meals.add("fifth meal");
        mealsPhotos.add(R.mipmap.image1);
        mealsPhotos.add(R.mipmap.image2);
        mealsPhotos.add(R.mipmap.image3);
        mealsPhotos.add(R.mipmap.image4);
        mealsPhotos.add(R.mipmap.image5);

        images.add(new SliderItem(R.mipmap.image1,"first meal"));
        images.add(new SliderItem(R.mipmap.image2,"second meal"));
        images.add(new SliderItem(R.mipmap.image3,"third meal"));
        images.add(new SliderItem(R.mipmap.image4,"forth meal"));
        images.add(new SliderItem(R.mipmap.image5,"fifth meal"));
        viewPager2.setAdapter(new SliderAdapter(images));
        viewPager2.setClipToPadding(false);
        viewPager2.setClipChildren(false);
        viewPager2.setOffscreenPageLimit(2);
        viewPager2.getChildAt(0).setOverScrollMode(View.OVER_SCROLL_NEVER);

        recentViewAdapter = new RecentViewAdapter(meals,mealsPhotos);
        recentRec.setAdapter(recentViewAdapter);
    }
}