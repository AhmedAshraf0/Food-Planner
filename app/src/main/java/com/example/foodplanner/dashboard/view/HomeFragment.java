package com.example.foodplanner.dashboard.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.foodplanner.R;
import com.example.foodplanner.dashboard.presenter.CommunicatorHome;
import com.example.foodplanner.dashboard.presenter.PresenterHome;
import com.example.foodplanner.network.ClientRetrofit;
import com.example.foodplanner.network.models.CategoryModel;
import com.example.foodplanner.network.models.CountryModel;
import com.example.foodplanner.network.models.FilterMealModel;
import com.example.foodplanner.network.models.MealModel;

import java.util.List;

public class HomeFragment extends Fragment implements CommunicatorHome {
    private static final String TAG = "HomeFragment";
    private PresenterHome presenterHome;
    private ViewPager2 viewPager2;
    private ScrollView scrollView;
    private RecyclerView  category1Rec , category2Rec , country1Rec , country2Rec , categoriesRec, countriesRec;
    private TextView categoryTitleOne, categoryTitleTwo , countryTitleOne , countryTitleTwo;
    private RandomCategoryAdapter randomCategoryAdapterOne, randomCategoryAdapterTwo;
    private RandomCountryAdapter randomCountryAdapterOne , randomCountryAdapterTwo;
    private CountriesAdapter countriesAdapter;
    private CategoriesAdapter categoriesAdapter;
    private SliderAdapter sliderAdapter;
    private List<CategoryModel> allCategories;
    private List<CountryModel> allCountries;
    private List<MealModel> randomMeals;
    private Handler sliderHandler;

    public HomeFragment() {
        // Required empty public constructor
        presenterHome = new PresenterHome(ClientRetrofit.getInstance() , this);
        presenterHome.getMeals();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sliderHandler = new Handler();
        randomCategoryAdapterOne = new RandomCategoryAdapter();
        randomCategoryAdapterTwo = new RandomCategoryAdapter();
        randomCountryAdapterOne = new RandomCountryAdapter();
        randomCountryAdapterTwo = new RandomCountryAdapter();
        categoriesAdapter = new CategoriesAdapter();
        countriesAdapter = new CountriesAdapter();
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
        Log.i(TAG, "onCreate: hi");
        viewPager2 = view.findViewById(R.id.viewPager);
        category1Rec = view.findViewById(R.id.category_one_rec);
        category2Rec = view.findViewById(R.id.category_two_rec);
        country1Rec=  view.findViewById(R.id.country_one_rec);
        country2Rec = view.findViewById(R.id.country_two_rec);
        countriesRec = view.findViewById(R.id.countries_rec);
        categoriesRec = view.findViewById(R.id.categories_rec);
        categoryTitleOne = view.findViewById(R.id.category_one);
        categoryTitleTwo = view.findViewById(R.id.category_two);
        countryTitleOne = view.findViewById(R.id.country_one);
        countryTitleTwo = view.findViewById(R.id.country_two);
        scrollView = view.findViewById(R.id.scrollView);
        if(RandomCountryAdapter.country1 != null){
            countryTitleOne.setText(RandomCountryAdapter.country1);
            countryTitleTwo.setText(RandomCountryAdapter.country2);
            categoryTitleOne.setText(RandomCategoryAdapter.category1);
            categoryTitleTwo.setText(RandomCategoryAdapter.category2);
        }

        category1Rec.setLayoutManager(new LinearLayoutManager(view.getContext(),LinearLayoutManager.HORIZONTAL,false));
        category2Rec.setLayoutManager(new LinearLayoutManager(view.getContext(),LinearLayoutManager.HORIZONTAL,false));
        country1Rec.setLayoutManager(new LinearLayoutManager(view.getContext(),LinearLayoutManager.HORIZONTAL,false));
        country2Rec.setLayoutManager(new LinearLayoutManager(view.getContext(),LinearLayoutManager.HORIZONTAL,false));
        categoriesRec.setLayoutManager(new LinearLayoutManager(view.getContext(),LinearLayoutManager.HORIZONTAL,false));
        countriesRec.setLayoutManager(new GridLayoutManager(view.getContext(),3));

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

        category1Rec.setAdapter(randomCategoryAdapterOne);
        category2Rec.setAdapter(randomCategoryAdapterTwo);
        country1Rec.setAdapter(randomCountryAdapterOne);
        country2Rec.setAdapter(randomCountryAdapterTwo);
        categoriesRec.setAdapter(categoriesAdapter);
        countriesRec.setAdapter(countriesAdapter);
    }

    @Override
    public void getCategoryResponse(List<CategoryModel> allCategories) {
        //haaaaa7777
        this.allCategories = allCategories;
        categoriesAdapter.setCategories(allCategories);
        categoriesAdapter.notifyDataSetChanged();

        //get 2 random meals of categories
        //calc randomNumber->send categoryName
        int randomCategory = (int) (Math.random() * (allCategories.size() - 1));
        randomCategory %= (allCategories.size() - 1);//not out of bounds

        //first category
        presenterHome.getCategory(allCategories.get(randomCategory).getStrCategory(),1);
        randomCategory = (randomCategory + 4) % (allCategories.size() - 1);
        //second category
        presenterHome.getCategory(allCategories.get(randomCategory).getStrCategory(),2);
    }

    @Override
    public void getCountryResponse(List<CountryModel> allCountries) {
        this.allCountries = allCountries;
        countriesAdapter.setCountries(allCountries);
        countriesAdapter.notifyDataSetChanged();

        //get 2 random meals of countries
        //calc randomNumber->send countryName
        int randomCountry = (int) (Math.random() * (allCountries.size() - 1));
        randomCountry %= (allCountries.size() - 1);//not out of bounds

        //first country
        presenterHome.getCountry(allCountries.get(randomCountry).getStrArea(),1);
        randomCountry = (randomCountry + 4) % (allCountries.size() - 1);
        //second country
        presenterHome.getCountry(allCountries.get(randomCountry).getStrArea(),2);
    }

    @Override
    public void getRandomMealsResponse(List<MealModel> randomMeals) {
        this.randomMeals = randomMeals;
        Log.i(TAG, "random meals: "+randomMeals.size());
        sliderAdapter.setRandomModel(randomMeals.subList(0,5));
        sliderAdapter.notifyDataSetChanged();
    }

    @Override
    public void getCategoryMeals(List<FilterMealModel> categoryMeals, String categoryName , int categoryNumber) {
        switch (categoryNumber){
            case 1:
                randomCategoryAdapterOne.setFilterMealModel(categoryMeals,categoryTitleOne,categoryName);
                RandomCategoryAdapter.category1 = categoryName;
                randomCategoryAdapterOne.notifyDataSetChanged();
                break;
            case 2:
                randomCategoryAdapterTwo.setFilterMealModel(categoryMeals,categoryTitleTwo,categoryName);
                RandomCategoryAdapter.category2 = categoryName;
                randomCategoryAdapterTwo.notifyDataSetChanged();
                break;
            default:
                Log.i(TAG, "getCategoryMeals: error");
                break;
        }
    }

    @Override
    public void getCountryMeals(List<FilterMealModel> countryMeals, String countryName, int countryNumber) {
        Log.i(TAG, "getCountryMeals: here");
        switch (countryNumber){
            case 1:
                countryTitleOne.setText(countryName+" food");
                RandomCountryAdapter.country1 = countryName+" food";
                randomCountryAdapterOne.setMealsOfCountry(countryMeals);
                randomCountryAdapterOne.notifyDataSetChanged();
                break;
            case 2:
                countryTitleTwo.setText(countryName+" food");
                RandomCountryAdapter.country2 = countryName+" food";
                randomCountryAdapterTwo.setMealsOfCountry(countryMeals);
                randomCountryAdapterTwo.notifyDataSetChanged();
                break;
            default:
                Log.i(TAG, "getCountryMeals: error");
                break;
        }
    }
}