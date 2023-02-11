package com.example.foodplanner.dashboard.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodplanner.R;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private ViewPager2 viewPager2;
    private RecyclerView recentRec , category1Rec , category2Rec , country1Rec , country2Rec , categoriesRec, countriesRec;
    private RecentViewAdapter recentViewAdapter;
    private Category1Adapter category1Adapter;
    private Category2Adapter category2Adapter;
    private Country1Adapter country1Adapter;
    private Country2Adapter country2Adapter;
    private CountriesAdapter countriesAdapter;
    private CategoriesAdapter categoriesAdapter;
    private List<SliderItem> images;
    private List<String> meals;
    private List<Integer> mealsPhotos;
    private Handler sliderHandler;

    public HomeFragment() {
        // Required empty public constructor
        sliderHandler = new Handler();
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
        category1Rec = view.findViewById(R.id.category_one_rec);
        category2Rec = view.findViewById(R.id.category_two_rec);
        country1Rec=  view.findViewById(R.id.country_one_rec);
        country2Rec = view.findViewById(R.id.country_two_rec);
        countriesRec = view.findViewById(R.id.countries_rec);
        categoriesRec = view.findViewById(R.id.categories_rec);
        LinearLayoutManager llMgrRecent , llMgrCat1 , llMgrCat2 , llMgrCount1 , llMgrCount2 , llMgrCats , llMgrCounts;
        llMgrRecent= new LinearLayoutManager(view.getContext());
        llMgrCat1 = new LinearLayoutManager(view.getContext());
        llMgrCat2 = new LinearLayoutManager(view.getContext());
        llMgrCount1 = new LinearLayoutManager(view.getContext());
        llMgrCount2 = new LinearLayoutManager(view.getContext());
        llMgrCats = new LinearLayoutManager(view.getContext());
        llMgrCounts = new LinearLayoutManager(view.getContext());
        llMgrCat1.setOrientation(RecyclerView.HORIZONTAL);
        llMgrCat2.setOrientation(RecyclerView.HORIZONTAL);
        llMgrRecent.setOrientation(RecyclerView.HORIZONTAL);
        llMgrCount1.setOrientation(RecyclerView.HORIZONTAL);
        llMgrCount2.setOrientation(RecyclerView.HORIZONTAL);
        llMgrCounts.setOrientation(RecyclerView.HORIZONTAL);
        llMgrCats.setOrientation(RecyclerView.HORIZONTAL);
        recentRec.setLayoutManager(llMgrRecent);
        category1Rec.setLayoutManager(llMgrCat1);
        category2Rec.setLayoutManager(llMgrCat2);
        country1Rec.setLayoutManager(llMgrCount1);
        country2Rec.setLayoutManager(llMgrCount2);
        countriesRec.setLayoutManager(llMgrCounts);
        categoriesRec.setLayoutManager(llMgrCats);

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
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                sliderHandler.removeCallbacks(() -> viewPager2.setCurrentItem(viewPager2.getCurrentItem()+1));
                sliderHandler.postDelayed(() -> viewPager2.setCurrentItem(viewPager2.getCurrentItem()+1),4000);
            }
        });

        recentViewAdapter = new RecentViewAdapter(meals,mealsPhotos);
        recentRec.setAdapter(recentViewAdapter);
        category1Adapter = new Category1Adapter(meals,mealsPhotos);
        category1Rec.setAdapter(category1Adapter);
        category2Adapter = new Category2Adapter(meals,mealsPhotos);
        category2Rec.setAdapter(category2Adapter);
        country1Adapter = new Country1Adapter(meals,mealsPhotos);
        country1Rec.setAdapter(country1Adapter);
        country2Adapter = new Country2Adapter(meals,mealsPhotos);
        country2Rec.setAdapter(country2Adapter);
        countriesAdapter = new CountriesAdapter(meals,mealsPhotos);
        countriesRec.setAdapter(countriesAdapter);
        categoriesAdapter = new CategoriesAdapter(meals,mealsPhotos);
        categoriesRec.setAdapter(categoriesAdapter);
    }
}