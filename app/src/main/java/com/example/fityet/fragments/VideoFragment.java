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

    private static final String YOUTUBE_API_KEY = "AIzaSyCXsZD807pV8ze1nhrd3Gx-e7QVG3WjuSA";

    TextView tvTitle;
    YouTubePlayerView youTubePlayerView;
    Vector<Video> youtubeVideos;
    private RecyclerView rvVideos;
    protected VideoAdapter adapter;


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
        // Setup any handles to view objects here
        super.onViewCreated(view,savedInstanceState);

        rvVideos = view.findViewById(R.id.rvVideos);
        youtubeVideos= new Vector<Video>();
        adapter = new VideoAdapter(getContext(), youtubeVideos);

        //Steps to use the recycler view
        // 0. create layout for one row in the list
        // 1. create the adapter
        // 2. create the data source
        // 3. set the adapter on the recycler view
        rvVideos.setAdapter(adapter);
        // 4. set the layout manager on the recycler view
        rvVideos.setLayoutManager(new LinearLayoutManager(getContext()));


        }



    }
