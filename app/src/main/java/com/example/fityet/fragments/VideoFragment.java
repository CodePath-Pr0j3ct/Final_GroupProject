package com.example.fityet.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.fityet.Adapters.VideoAdapter;
import com.example.fityet.Exercise;
import com.example.fityet.Models.Video;
import com.example.fityet.R;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class VideoFragment extends Fragment {

    List<Exercise> exercises1; //Goal-specific exercises
    List<Exercise> exercises2; //Other exercises
    YouTubePlayerView youTubePlayerView;
    RecyclerView rvForYou;
    RecyclerView rvMore;
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
        exercises2 = new ArrayList<>();

        rvForYou = view.findViewById(R.id.rvForYou);
        rvMore = view.findViewById(R.id.rvMore);

        userGoalName = view.findViewById(R.id.tvCustomGoal);
        super.onCreate(savedInstanceState);
        fillExercises();

        //FARIHA, IF YOU MADE PROFILE FRAGMENT'S GETTERS/SETTERS RUN PROPERLY, FIX THIS CODE TOO
        //userGoalName.setText(User.KEY_GOAL);


        //Create adapters for both lists
        VideoAdapter videoAdapter1 = new VideoAdapter(getContext(), exercises1);
        VideoAdapter videoAdapter2 = new VideoAdapter(getContext(), exercises2);

        //Set adapters to each recycler view
        //if user has lose fat as goal
//        if(User.KEY_GOAL = "Lose fat/Build Muscle") {
//
//            rvForYou.setAdapter(videoAdapter1);
//
//            rvMore.setAdapter(videoAdapter2);
//
//        }
//
//        else{
//
//            rvForYou.setAdapter(videoAdapter2);
//
//            rvMore.setAdapter(videoAdapter1);
//
//        }

        rvForYou.setAdapter(videoAdapter1);

        rvMore.setAdapter(videoAdapter2);

        //Set a layout manager, required for RecyclerView to know how to layout different view on the screen

            rvForYou.setLayoutManager(new LinearLayoutManager(getContext()));

            rvMore.setLayoutManager(new LinearLayoutManager(getContext()));

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
        exercises2.add(new Exercise("Standing Crunch with Clap", "Z30Pac8SmLY"));
        exercises2.add(new Exercise("Lunge Hip Flexor Stretch", "mGxRdw5IOGs"));
        exercises2.add(new Exercise("Flamingo Stand", "3N1Arv4C1BE"));
        exercises2.add(new Exercise("Chair Leg Raises", "fAhHS10FDPU"));
        exercises2.add(new Exercise("Quad Stretch", "FBO9-8nTbsM"));
        exercises2.add(new Exercise("Butterfly Stretch", "2I4dNZYVIUU"));
        exercises2.add(new Exercise("Tree Pose", "Fr5kiIygm0c"));
        exercises2.add(new Exercise("T-Stand With Side Bend", "lgtW65j_UVI"));
        exercises2.add(new Exercise("Wall Pushups", "EgU3CbtQTlw"));
        exercises2.add(new Exercise("Side Kick", "RckjjT-T0ZA"));

    }

}
