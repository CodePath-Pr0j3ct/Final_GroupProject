package com.example.fityet.Adapters;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.fityet.Models.HorizontalModel;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import com.example.fityet.Models.DetailActivity;
import java.util.ArrayList;
import android.view.LayoutInflater;
import com.example.fityet.R;

import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ImageView;

import org.parceler.Parcels;

public class HorizontalRecyclerViewAdapter extends RecyclerView.Adapter<HorizontalRecyclerViewAdapter.HorizontalRVViewHolder>{

    Context context;
    ArrayList<HorizontalModel> arrayList;

    public HorizontalRecyclerViewAdapter(Context context, ArrayList<HorizontalModel> arrayList){

        this.context = context;
        this.arrayList = arrayList;

    }

    @NonNull
    @Override
    public HorizontalRVViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_horizontal, parent, false);
        return new HorizontalRVViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HorizontalRVViewHolder holder, int position) {

        final HorizontalModel horizontalModel = arrayList.get(position);
        HorizontalModel exercise = arrayList.get(position);
        holder.textViewTitle.setText(horizontalModel.getName());
        holder.bind(exercise);
    }

    @Override
    public int getItemCount() {

        return arrayList.size();
    }

    public class HorizontalRVViewHolder extends RecyclerView.ViewHolder{

        TextView textViewTitle;
        ImageView imageViewThumb;

        public HorizontalRVViewHolder(View itemView) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.Name);
            imageViewThumb = itemView.findViewById(R.id.thumbNail);

        }

        public void bind(HorizontalModel exercise) {
            textViewTitle.setText(exercise.getName());
            String imageUrl;
            // Adjust imageUrl according to orientation
            imageUrl = "https://img.youtube.com/vi/" + exercise.getId() + "/0.jpg";
            Glide.with(context).load(imageUrl).into(imageViewThumb);

            // Register click listener on whole row, and
            // Navigate to new activity upon tap
            imageViewThumb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(context, DetailActivity.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    i.putExtra("exercise", Parcels.wrap(exercise));
                    context.startActivity(i);
                }
            });
        }
    }

}
