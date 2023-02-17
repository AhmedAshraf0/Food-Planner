package com.example.foodplanner.dashboard.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.foodplanner.R;
import com.example.foodplanner.dashboard.presenter.CommunicatorHome;
import com.example.foodplanner.dashboard.presenter.PresenterHome;
import com.example.foodplanner.network.ClientRetrofit;
import com.example.foodplanner.network.models.CategoryModel;
import com.example.foodplanner.network.models.CountryModel;
import com.example.foodplanner.network.models.FilterMealModel;
import com.example.foodplanner.network.models.MealModel;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements CommunicatorHome {
    private static final String TAG = "HomeFragment";
    private ViewPager2 viewPager2;
    private RecyclerView recentRec , category1Rec , category2Rec , country1Rec , country2Rec , categoriesRec, countriesRec;
    private RecentViewAdapter recentViewAdapter;
    private TextView categoryTitleOne, categoryTitleTwo;
    private RandomCategoryAdapter randomCategoryAdapterOne, randomCategoryAdapterTwo;
    private Country1Adapter country1Adapter;
    private Country2Adapter country2Adapter;
    private CountriesAdapter countriesAdapter;
    private CategoriesAdapter categoriesAdapter;
    private SliderAdapter sliderAdapter;
    private List<CategoryModel> allCategories;
    private List<CountryModel> allCountries;
    private List<MealModel> randomMeals;
    private List<List<FilterMealModel>> categoriesMeals;
    private Handler sliderHandler;

    public HomeFragment() {
        // Required empty public constructor
        sliderHandler = new Handler();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PresenterHome presenterHome = new PresenterHome(ClientRetrofit.getInstance() , this);
        presenterHome.getMeals();
        randomCategoryAdapterOne = new RandomCategoryAdapter();
        randomCategoryAdapterTwo = new RandomCategoryAdapter();
        sliderAdapter = new SliderAdapter();
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
        categoryTitleOne = view.findViewById(R.id.category_one);
        categoryTitleTwo = view.findViewById(R.id.category_two);

        recentRec.setLayoutManager(new LinearLayoutManager(view.getContext(),LinearLayoutManager.HORIZONTAL,false));
        category1Rec.setLayoutManager(new LinearLayoutManager(view.getContext(),LinearLayoutManager.HORIZONTAL,false));
        category2Rec.setLayoutManager(new LinearLayoutManager(view.getContext(),LinearLayoutManager.HORIZONTAL,false));
        country1Rec.setLayoutManager(new LinearLayoutManager(view.getContext(),LinearLayoutManager.HORIZONTAL,false));
        country2Rec.setLayoutManager(new LinearLayoutManager(view.getContext(),LinearLayoutManager.HORIZONTAL,false));
        countriesRec.setLayoutManager(new LinearLayoutManager(view.getContext(),LinearLayoutManager.HORIZONTAL,false));
        categoriesRec.setLayoutManager(new LinearLayoutManager(view.getContext(),LinearLayoutManager.HORIZONTAL,false));

        viewPager2.setAdapter(sliderAdapter);
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

//        recentViewAdapter = new RecentViewAdapter(meals,mealsPhotos,getFragmentManager());
//        recentRec.setAdapter(recentViewAdapter);
        category1Rec.setAdapter(randomCategoryAdapterOne);
        category2Rec.setAdapter(randomCategoryAdapterTwo);
//        country1Adapter = new Country1Adapter(meals,mealsPhotos);
//        country1Rec.setAdapter(country1Adapter);
//        country2Adapter = new Country2Adapter(meals,mealsPhotos);
//        country2Rec.setAdapter(country2Adapter);
//        countriesAdapter = new CountriesAdapter(meals,mealsPhotos);
//        countriesRec.setAdapter(countriesAdapter);
//        categoriesAdapter = new CategoriesAdapter(meals,mealsPhotos);
//        categoriesRec.setAdapter(categoriesAdapter);
    }

    @Override
    public void getCategoryResponse(List<CategoryModel> allCategories) {
        //haaaaa7777
//        System.out.println(body.getStrIngredient1());
        this.allCategories = allCategories;
        Log.i(TAG, "getCategoryResponse: "+allCategories.size());
    }

    @Override
    public void getCountryResponse(List<CountryModel> allCountries) {
        this.allCountries = allCountries;
        Log.i(TAG, "getCountryResponse: "+allCountries.size());
    }

    @Override
    public void getRandomMealsResponse(List<MealModel> randomMeals) {
        this.randomMeals = randomMeals;
        Log.i(TAG, "random meals: "+randomMeals.size());
        sliderAdapter.setRandomModel(randomMeals.subList(0,5));
        sliderAdapter.notifyDataSetChanged();
    }

    @Override
    public void getCategoryMeals(List<List<FilterMealModel>> categoryMeals , List<String> categoryNames) {
        categoriesMeals = categoryMeals;
        categoryTitleOne.setText(categoryNames.get(0));
        randomCategoryAdapterOne.setCategoryModel(categoryMeals.get(0));
        randomCategoryAdapterOne.notifyDataSetChanged();

        categoryTitleTwo.setText(categoryNames.get(1));
        randomCategoryAdapterTwo.setCategoryModel(categoryMeals.get(1));
        randomCategoryAdapterTwo.notifyDataSetChanged();
    }
}