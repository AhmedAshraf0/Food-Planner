package com.example.foodplanner.dashboard.view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodplanner.R;
import com.example.foodplanner.models.CountryData;
import com.example.foodplanner.network.models.CountryModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CountriesAdapter extends RecyclerView.Adapter<CountriesAdapter.ViewHolder> {
    private static final String TAG = "CountriesAdapter";
    private OnCountryButtonClicked onCountryButtonClicked;
    private List<CountryModel> countries;
    private CountryData countryData;
    private Context context;
    private Map<String,String> countryNames , countryImgs;

    public CountriesAdapter(OnCountryButtonClicked onCountryButtonClicked) {
        countries = new ArrayList<>();
        countryData = new CountryData();
        countryNames = countryData.getCountryNames();
        countryImgs = countryData.getImgLinks();
        this.onCountryButtonClicked = onCountryButtonClicked;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView countryImage;
        private TextView countryTitle;
        private Button mealsButton;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            countryImage = itemView.findViewById(R.id.countryImage);
            countryTitle = itemView.findViewById(R.id.countryTitle);
            mealsButton = itemView.findViewById(R.id.showMealsBtn);
        }

        public ImageView getMealImage() {
            return countryImage;
        }
        public TextView getMealTitle() {
            return countryTitle;
        }

        public Button getMealsButton() {
            return mealsButton;
        }
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater layoutInflater= LayoutInflater.from(parent.getContext());
        View currentView = layoutInflater.inflate(R.layout.country_card,parent,false);
        ViewHolder viewHolder = new ViewHolder(currentView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String countryName = countryNames.get(countries.get(position).getStrArea());
        holder.getMealTitle().setText(countryName);
        Glide.with(context).load(countryImgs.get(countryName))
                .error(R.mipmap.hr)
                .into(holder.getMealImage());
        holder.getMealsButton().setOnClickListener(v -> {
            Log.i("TAG","pressed from Breakfast--------");
            onCountryButtonClicked.onCountryButtonActor(countries.get(position).getStrArea());
        });
    }

    @Override
    public int getItemCount() {
        return countries.size();
    }
    public void setCountries(List<CountryModel> countries){
        final CountryModel[] temp = new CountryModel[1];
        countries.forEach(i->{
            if(i.getStrArea().equals("Unknown"))
                temp[0] = i;
        });
        countries.remove(temp[0]);
        this.countries = countries;
    }
}
