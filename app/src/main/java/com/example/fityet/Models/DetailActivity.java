package com.example.fityet.Models;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.fityet.Adapters.VideoAdapter;
import com.example.fityet.R;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import org.parceler.Parcels;

import java.util.List;
import java.util.Vector;

public class DetailActivity extends YouTubeBaseActivity {

    private static final String YOUTUBE_API_KEY = "AIzaSyCXsZD807pV8ze1nhrd3Gx-e7QVG3WjuSA";
    RecyclerView recyclerView;
    TextView tvTitle;
    YouTubePlayerView youTubePlayerView;


    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Video video = Parcels.unwrap(getIntent().getParcelableExtra("movie"));
        tvTitle = findViewById(R.id.tvTitle);
        tvTitle.setText(video.getTitle());
        recyclerView = (RecyclerView) findViewById(R.id.rvVideos);
        youTubePlayerView = findViewById(R.id.player);
        VideoAdapter videoAdapter = new VideoAdapter(Video.videoList());

        recyclerView.setAdapter(videoAdapter);

    }

 /*   private void initializeYoutube(final String youtubeKey) {
        Video video = Parcels.unwrap(getIntent().getParcelableExtra("movie"));
        youTubePlayerView.initialize(YOUTUBE_API_KEY, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                Log.d("DetailActivity", "onInitializationSuccess");

                    //the video starts playing
                    youTubePlayer.loadVideo(youtubeKey);
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                Log.d("DetailActivity", "onInitializationFailure");

            }
        });
    } */


}
