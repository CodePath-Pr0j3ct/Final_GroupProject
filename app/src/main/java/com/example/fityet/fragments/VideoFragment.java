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

    List<Exercise> exercises1; //Goal-specific exercises
    List<Exercise> exercises2; //Other exercises
    YouTubePlayerView youTubePlayerView;
    RecyclerView rvExercises1; //For You recyclerView
    RecyclerView rvExercises2; //More recyclerView
    VideoAdapter videoAdapter;
    TextView userGoalName;


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
        exercises1 = new ArrayList<>();
        rvExercises1 = view.findViewById(R.id.rvExercises1);
        exercises2 = new ArrayList<>();
        rvExercises2 = view.findViewById(R.id.rvExercises2);
        userGoalName = view.findViewById(R.id.tvCustomGoal);
        super.onCreate(savedInstanceState);
        fillExercises();

        //FARIHA, IF YOU MADE PROFILE FRAGMENT'S GETTERS/SETTERS RUN PROPERLY, FIX THIS CODE TOO
        //userGoalName.setText(User.KEY_GOAL);


        // Create adapters for both lists
        VideoAdapter videoAdapter = new VideoAdapter(getContext(), exercises1);
        //VideoAdapter videoAdapter = new VideoAdapter(getContext(), exercises2);

        // Set adapters to each recycler view
        rvExercises1.setAdapter(videoAdapter);
        //rvExercises2.setAdapter(videoAdapter);

        // Set a layout manager, required for RecyclerView to know how to layout different views
        // on the screen
        rvExercises1.setLayoutManager(new LinearLayoutManager(getContext()));
        //rvExercises2.setLayoutManager(new LinearLayoutManager(getContext()));


    }

    private void fillExercises() {
        exercises1.add(new Exercise("Sit-ups", "jDwoBqPH0jk"));
        exercises1.add(new Exercise("Pushups", "IODxDxX7oi4"));
        exercises1.add(new Exercise("Planks", "ASdvN_XEl_c"));
        exercises1.add(new Exercise("Pike Pushups", "sposDXWEB0A"));
        exercises1.add(new Exercise("Dips", "2z8JmcrW-As"));
        exercises1.add(new Exercise("Burpees", "dZgVxmf6jkA"));
        exercises1.add(new Exercise("Jumping Jacks", "UpH7rm0cYbM"));
        exercises1.add(new Exercise("Squats", "YaXPRqUwItQ"));
        exercises1.add(new Exercise("Calf Raises", "ommnfVcLWxQ"));
        exercises1.add(new Exercise("Pull-ups", "eGo4IYlbE5g"));
    }


}
