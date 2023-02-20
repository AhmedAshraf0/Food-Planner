package com.example.foodplanner.dashboard.view;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodplanner.R;
import com.example.foodplanner.network.models.CategoryModel;

import java.util.ArrayList;
import java.util.List;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.ViewHolder> {
    private OnCategoryCardListener onCategoryCardListener;
    private List<CategoryModel> categories;
    public CategoriesAdapter(OnCategoryCardListener onCategoryCardListener) {
        categories = new ArrayList<>();
        this.onCategoryCardListener = onCategoryCardListener;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView categoryTitle;
        private CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryTitle = itemView.findViewById(R.id.category_name);
            cardView = itemView.findViewById(R.id.category_card);
        }

        public TextView getCategoryTitle() {
            return categoryTitle;
        }

        public CardView getCardView() {
            return cardView;
        }
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater= LayoutInflater.from(parent.getContext());
        View currentView = layoutInflater.inflate(R.layout.category_card,parent,false);
        ViewHolder viewHolder = new ViewHolder(currentView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.getCategoryTitle().setText(categories.get(position).getStrCategory());
        holder.getCategoryTitle().setOnClickListener(v -> {
            Log.i("TAG","pressed from Breakfast--------");
            onCategoryCardListener.onCategoryCardListener(categories.get(position).getStrCategory());
        });
        holder.getCardView().setOnClickListener(v->{
            Log.i("TAG", "onBindViewHolder: test");
        });
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }
    public void setCategories(List<CategoryModel> categories){
        this.categories = categories;
    }
}
