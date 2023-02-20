package com.example.foodplanner.dashboard.view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.example.foodplanner.R;
import com.example.foodplanner.network.models.FilterMealModel;
import com.example.foodplanner.network.models.MealModel;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.List;

public class SliderAdapter extends RecyclerView.Adapter<SliderAdapter.SliderViewHolder>{
    private static final String TAG = "Slider Adapter --";
    private OnSliderItemClicked onSliderItemClicked;
    private List<MealModel> randomMeals;
    private Context context;

    public SliderAdapter(OnSliderItemClicked onSliderItemClicked) {
        randomMeals = new ArrayList<>();
        this.onSliderItemClicked = onSliderItemClicked;
    }
    class SliderViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        private TextView textView;
        private CardView card;

        public SliderViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.tvHeading);
            card = itemView.findViewById(R.id.sliderCard);
        }

        public ImageView getImageView() {
            return imageView;
        }

        public TextView getTextView() {
            return textView;
        }

        public CardView getCard() {
            return card;
        }
    }


    @NonNull
    @Override
    public SliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater layoutInflater =LayoutInflater.from(parent.getContext());
        View currentView = layoutInflater.inflate(R.layout.slide_item_container,parent,false);
        SliderViewHolder sliderViewHolder = new SliderViewHolder(currentView);
        return sliderViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SliderViewHolder holder, int position) {
        Glide.with(context).load(randomMeals.get(position).getStrMealThumb())
                .into(holder.getImageView());
        holder.getTextView().setText(randomMeals.get(position).getStrMeal());
        holder.getCard().setOnClickListener(v -> {
            Log.i(TAG, "onClick: pressed");
            onSliderItemClicked.sliderItemClicked(randomMeals.get(position));
        });
    }

    @Override
    public int getItemCount() {
        return randomMeals.size();
    }

    public void setRandomModel(List<MealModel> randomMeals){
        this.randomMeals = randomMeals;
    }
}
