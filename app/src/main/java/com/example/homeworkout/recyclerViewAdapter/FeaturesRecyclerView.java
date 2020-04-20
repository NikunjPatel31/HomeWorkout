package com.example.homeworkout.recyclerViewAdapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homeworkout.R;
import com.example.homeworkout.UserDefined.ExerciseDetails;

import java.util.ArrayList;

public class FeaturesRecyclerView extends RecyclerView.Adapter<FeaturesRecyclerView.FeaturesViewHolder> {

    @NonNull
    private static final String TAG = "FeaturesRecyclerView";
    private
    String[] features;

    public FeaturesRecyclerView(@NonNull String[] features) {
        this.features = features;
    }

    @Override
    public FeaturesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.features_list_item_layout,parent,false);
        return new FeaturesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FeaturesViewHolder holder, int position) {
        holder.setFeature(features[position]);
    }

    @Override
    public int getItemCount() {
        return features.length;
    }

    public static class FeaturesViewHolder extends RecyclerView.ViewHolder
    {
        View view;
        public FeaturesViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
        }
        public void setFeature(String feature)
        {
            TextView featureTV = view.findViewById(R.id.feature_text_view);
            featureTV.setText(feature);
        }
    }
}
