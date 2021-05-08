package com.example.fityet.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fityet.Models.Exercise;
import com.example.fityet.R;
import com.parse.ParseQuery;

import java.util.Collections;
import java.util.List;

public class ExerciseAdapter extends RecyclerView.Adapter<ExerciseAdapter.ViewHolder> {

    private Context context;
    private List<Exercise> exercises;

    public ExerciseAdapter(Context context, List<Exercise> exercises){
        this.context = context;
        this.exercises = exercises;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_schedule_exercise, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Exercise exercise = exercises.get(position);
        holder.bind(exercise);
    }

    @Override
    public int getItemCount() {
        return exercises.size();
    }

    public void clear(){
        exercises.clear();
        this.notifyDataSetChanged();
    }


    public void addAll(List<Exercise> list){
        for(Exercise exerciseAd : list){
            exercises.add(exerciseAd);
        }
        this.notifyDataSetChanged();
        Collections.sort(exercises);
        Log.d("ExerciseAdapterTag", exercises.toString());
    }


    class ViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener{
        private TextView tvTime;
        private TextView tvExercise;
        private LinearLayout linearLayout;
        public String id;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvExercise = itemView.findViewById(R.id.exercise);
            tvTime = itemView.findViewById(R.id.time);
            linearLayout = itemView.findViewById(R.id.ly);
            linearLayout.setOnLongClickListener(this);
        }



        public void bind(Exercise exercise){
            tvExercise.setText(exercise.getExercise());
            id = exercise.getObjectId();
            int hourOfDay = exercise.getHour();
            int minute = exercise.getMinutes();
            if (minute <= 9){
                if (hourOfDay < 12) {
                    hourOfDay = (hourOfDay == 0)? 12 : hourOfDay;
                    tvTime.setText(hourOfDay + ":0" + minute + " AM");
                }
                else{
                    if (hourOfDay > 12) {
                        tvTime.setText(hourOfDay % 12 + ":0" + minute + " PM");
                    }
                    else{
                        tvTime.setText(hourOfDay + ":0" + minute + " PM");
                    }
                }
            }
            else {
                if (hourOfDay < 12) {
                    hourOfDay = (hourOfDay == 0)? 12 : hourOfDay;
                    tvTime.setText(hourOfDay + ":" + minute + " AM");
                }
                else{
                    if (hourOfDay > 12) {
                        tvTime.setText(hourOfDay % 12 + ":" + minute + " PM");
                    }
                    else{
                        tvTime.setText(hourOfDay + ":" + minute + " PM");
                    }
                }
            }
        }

        @Override
        public boolean onLongClick(View v) {
            Log.d("Taggg", "messi");
            exercises.remove(getAdapterPosition());
            ParseQuery<Exercise> query = ParseQuery.getQuery(Exercise.class);
            query.getInBackground(this.id, (object, e) -> {
                if (e == null) {
                    // Deletes the fetched ParseObject from the database
                    object.deleteInBackground(e2 -> {
                        if(e2==null){
                           // Toast.makeText(this, "Delete Successful", Toast.LENGTH_SHORT).show();
                        }else{
                            //Something went wrong while deleting the Object
                            //Toast.makeText(this, "Error: "+e2.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }else{
                    //Something went wrong while retrieving the Object
                   // Toast.makeText(this, "Error: "+e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

            notifyItemRemoved(getAdapterPosition());
            notifyItemRangeChanged(getAdapterPosition(),exercises.size());
            return true;
        }
    }
}
