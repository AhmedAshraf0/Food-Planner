package com.example.foodplanner.dashboard.view;

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
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodplanner.R;
import com.example.foodplanner.network.models.CategoryModel;

import java.util.ArrayList;
import java.util.List;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.ViewHolder> {
    private List<CategoryModel> categories;
    public CategoriesAdapter() {
        categories = new ArrayList<>();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView categoryTitle;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryTitle = itemView.findViewById(R.id.category_name);
        }

        public TextView getMealTitle() {
            return categoryTitle;
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
        holder.getMealTitle().setText(categories.get(position).getStrCategory());
        holder.getMealTitle().setOnClickListener(v -> {
            Log.i("TAG","pressed from Breakfast--------");
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
