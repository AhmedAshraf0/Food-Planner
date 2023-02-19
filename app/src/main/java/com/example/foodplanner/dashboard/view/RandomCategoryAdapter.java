package com.example.foodplanner.dashboard.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodplanner.R;
import com.example.foodplanner.network.models.FilterMealModel;

import java.util.ArrayList;
import java.util.List;

public class RandomCategoryAdapter extends RecyclerView.Adapter<RandomCategoryAdapter.ViewHolder> {
    private OnCardClickListener onCardClickListener;
    private List<FilterMealModel> filterMealModel;
    private TextView categoryTitleOne, categoryTitleTwo;
    public static String category1 , category2;
    private Context context;

    public RandomCategoryAdapter(OnCardClickListener onCardClickListener) {
        filterMealModel = new ArrayList<>();
        this.onCardClickListener = onCardClickListener;
    }


    class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView mealImage;
        private ImageButton favBtn;
        private TextView mealTitle;
        private Button addBtn;
        private CardView card;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mealImage = itemView.findViewById(R.id.mealImage);
            favBtn = itemView.findViewById(R.id.buttonF);
            mealTitle = itemView.findViewById(R.id.mealTitle);
            addBtn = itemView.findViewById(R.id.addToScedule);
            card = itemView.findViewById(R.id.mealCardView);
        }

        public ImageView getMealImage() {
            return mealImage;
        }

        public ImageButton getFavBtn() {
            return favBtn;
        }

        public TextView getMealTitle() {
            return mealTitle;
        }

        public Button getAddBtn() {
            return addBtn;
        }

        public CardView getCard() {
            return card;
        }
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater layoutInflater= LayoutInflater.from(parent.getContext());
        View currentView = layoutInflater.inflate(R.layout.meal_cardview,parent,false);
        ViewHolder viewHolder = new ViewHolder(currentView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Glide.with(context).load(filterMealModel.get(position).getStrMealThumb()+"/preview")
                .override(150,150)
                .into(holder.getMealImage());
        holder.getMealTitle().setText(filterMealModel.get(position).getStrMeal());
        holder.getAddBtn().setOnClickListener(v -> {
            Log.i("TAG","pressed from seafood--------");
        });
        holder.getCard().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("TAG", "onClick: pressed");
                onCardClickListener.onCardClickActor(Integer.parseInt(filterMealModel.get(position).getIdMeal()));
//                Navigation.findNavController(v).navigate(R.id.action_homeFragment_to_mealActivity);

                //1.request to api to get mealdetails
                //2.navigate to mealScreen
                //3.send mealDetails there
            }
        });
    }

    @Override
    public int getItemCount() {
        return filterMealModel.size();
    }
    public void setFilterMealModel(List<FilterMealModel> filterMealModel,TextView title ,String categoryName) {
        if(filterMealModel == null){
            Log.i("TAG", "setCategoryModel: null");
        }
        this.filterMealModel = filterMealModel;
        title.setText(categoryName);
    }
}
