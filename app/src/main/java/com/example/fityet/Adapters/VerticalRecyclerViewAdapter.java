package com.example.fityet.Adapters;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fityet.Models.HorizontalModel;
import com.example.fityet.Models.VerticalModel;
import android.content.Context;
import com.example.fityet.R;
import java.util.ArrayList;
import android.widget.TextView;

public class VerticalRecyclerViewAdapter extends RecyclerView.Adapter<VerticalRecyclerViewAdapter.VerticalRVViewHolder> {

    
    Context context;
    ArrayList<VerticalModel> arrayList;

    public VerticalRecyclerViewAdapter(Context context, ArrayList<VerticalModel> arrayList){

        this.arrayList = arrayList;
        this.context = context;

    }

    @NonNull
    @Override
    public VerticalRVViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vertical, parent, false);

        return new VerticalRVViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull VerticalRVViewHolder holder, int position) {

        VerticalModel verticalModel = arrayList.get(position);

        String title = verticalModel.getTitle();

        ArrayList<HorizontalModel> singleItem = verticalModel.getArrayList();

        holder.textViewTitle.setText(title);
        HorizontalRecyclerViewAdapter horizontalRecyclerViewAdapter = new HorizontalRecyclerViewAdapter(context, singleItem);

        holder.recyclerView.setHasFixedSize(true);
        holder.recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));

        holder.recyclerView.setAdapter(horizontalRecyclerViewAdapter);

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class VerticalRVViewHolder extends RecyclerView.ViewHolder{

        RecyclerView recyclerView;
        TextView textViewTitle;

        public VerticalRVViewHolder(View itemView){

            super(itemView);

            recyclerView = itemView.findViewById(R.id.recyclerview1);

            textViewTitle = itemView.findViewById(R.id.tvSuggestedVids);
        }

    }

}
