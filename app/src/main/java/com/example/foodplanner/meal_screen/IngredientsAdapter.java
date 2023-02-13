package com.example.foodplanner.meal_screen;

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
import com.example.foodplanner.dashboard.view.CategoriesAdapter;

import java.util.List;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.ViewHolder> {
    List<String> measures , ingredentNames;

    public IngredientsAdapter(List<String> measures, List<String> ingredentNames) {
        this.measures = measures;
        this.ingredentNames = ingredentNames;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView measure , ingredients;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            measure = itemView.findViewById(R.id.measure);
            ingredients = itemView.findViewById(R.id.ingredient_name);
        }

        public TextView getMeasure() {
            return measure;
        }

        public TextView getIngredients() {
            return ingredients;
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater= LayoutInflater.from(parent.getContext());
        View currentView = layoutInflater.inflate(R.layout.ingredent_card,parent,false);
        IngredientsAdapter.ViewHolder viewHolder = new IngredientsAdapter.ViewHolder(currentView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.getMeasure().setText(measures.get(position));
        holder.getIngredients().setText(ingredentNames.get(position));
    }

    @Override
    public int getItemCount() {
        return measures.size();
    }
}
