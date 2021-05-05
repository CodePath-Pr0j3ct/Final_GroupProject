package com.example.fityet.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fityet.Models.Exercise;
import com.example.fityet.R;

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
        //exercises.addAll(list);
        this.notifyDataSetChanged();
        Log.d("ExerciseAdapterTag", exercises.toString());
    }


    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tvTime;
        private TextView tvExercise;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvExercise = itemView.findViewById(R.id.exercise);
            tvTime = itemView.findViewById(R.id.time);
        }
        public void bind(Exercise exercise){
            tvExercise.setText(exercise.getExercise());
            tvTime.setText(exercise.getHour() + ":" + exercise.getMinutes());
        }
    }
}
