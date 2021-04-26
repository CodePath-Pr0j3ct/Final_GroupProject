package com.example.fityet.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.TextView;

import com.example.fityet.Adapters.VideoAdapter;
import com.example.fityet.Exercise;
import com.example.fityet.Models.Video;
import com.example.fityet.R;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class VideoFragment extends Fragment {

    List<Exercise> exercises;
    YouTubePlayerView youTubePlayerView;
    RecyclerView rvExercises;
    VideoAdapter videoAdapter;


    public VideoFragment() {
        // Required empty public constructor
    }

    // The onCreateView method is called when Fragment should create its View object hierarchy,
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        // Defines the xml file for the fragment
        return inflater.inflate(R.layout.fragment_video, parent, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        exercises = new ArrayList<>();
        fillExercises();
        super.onCreate(savedInstanceState);
        rvExercises = view.findViewById(R.id.rvExercises);


        // Create an adapter
        VideoAdapter videoAdapter = new VideoAdapter(getContext(), exercises);

        // Set adapter to the recycler view
        rvExercises.setAdapter(videoAdapter);

        // Set a layout manager, required for RecyclerView to know how to layout different views
        // on the screen
        rvExercises.setLayoutManager(new LinearLayoutManager(getContext()));



    }

    private void fillExercises() {
        exercises.add(new Exercise("Sit-ups", "jDwoBqPH0jk"));
        exercises.add(new Exercise("Pushups", "IODxDxX7oi4"));
        exercises.add(new Exercise("Planks", "ASdvN_XEl_c"));
        exercises.add(new Exercise("Pike Pushups", "sposDXWEB0A"));
        exercises.add(new Exercise("Dips", "2z8JmcrW-As"));
        exercises.add(new Exercise("Burpees", "dZgVxmf6jkA"));
        exercises.add(new Exercise("Jumping Jacks", "UpH7rm0cYbM"));
        exercises.add(new Exercise("Squats", "YaXPRqUwItQ"));
        exercises.add(new Exercise("Calf Raises", "ommnfVcLWxQ"));
        exercises.add(new Exercise("Pull-ups", "eGo4IYlbE5g"));
    }


}
