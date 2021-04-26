package com.example.fityet.Adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fityet.Models.DetailActivity;
import com.example.fityet.R;
import com.example.fityet.Exercise;


import org.parceler.Parcels;

import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder> {

    Context context;
    List<Exercise> exercises;

    public VideoAdapter(Context context, List<Exercise> exercises) {
        this.context = context;
        this.exercises = exercises;
    }

    // Usually involves inflating a layout from XML and returning the holder
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("Exercise", "onCreateViewHolder");
        View exerciseView = LayoutInflater.from(context).inflate(R.layout.item_video, parent, false);
        return new ViewHolder(exerciseView);
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Get movie at the passed in position
        Exercise exercise = exercises.get(position);
        // Bind the movie data into the ViewHolder
        holder.bind(exercise);
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return exercises.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView Name;
        ImageView thumbNail;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Name = itemView.findViewById(R.id.Name);
            thumbNail = itemView.findViewById(R.id.thumbNail);
        }

        public void bind(Exercise exercise) {
            Name.setText(exercise.getName());
            String imageUrl;
            // Adjust imageUrl according to orientation
            imageUrl = "https://img.youtube.com/vi/" + exercise.getId() + "/0.jpg";
            Glide.with(context).load(imageUrl).into(thumbNail);

            // Register click listener on whole row, and
            // Navigate to new activity upon tap
            thumbNail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(context, DetailActivity.class);
                    i.putExtra("exercise", Parcels.wrap(exercise));
                    context.startActivity(i);
                }
            });
        }
    }
}
