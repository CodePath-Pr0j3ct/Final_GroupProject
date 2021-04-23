package com.example.fityet.Models;

import android.os.Bundle;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.fityet.Adapters.VideoAdapter;
import com.example.fityet.R;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.List;
import java.util.Vector;

public class DetailActivity extends YouTubeBaseActivity {

    private static final String YOUTUBE_API_KEY = "AIzaSyCXsZD807pV8ze1nhrd3Gx-e7QVG3WjuSA";
    RecyclerView recyclerView;
    TextView tvTitle;
    YouTubePlayerView youTubePlayerView;
    Vector<Video> youtubeVideos = new Vector<Video>();

    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_detail);
        tvTitle = findViewById(R.id.tvTitle);
        youTubePlayerView = findViewById(R.id.player);

        //Add videos in youtube video list/vector
        youtubeVideos.add(new Video("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/N3aSoC0HHdU\" frameborder=\"0\" allowfullscreen></iframe>") );

        recyclerView = (RecyclerView) findViewById(R.id.rvVideos);

        VideoAdapter videoAdapter = new VideoAdapter(youtubeVideos);

        recyclerView.setAdapter(videoAdapter);
    }



}
