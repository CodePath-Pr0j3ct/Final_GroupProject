package com.example.fityet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.parse.ParseUser;

import java.util.Locale;

public class DisplayExercise extends YouTubeBaseActivity {
    protected static final String YOUTUBE_API_KEY = "AIzaSyBWQ_RUXfBeW6V5O3b61DRCCLJbb1ZLKpY";
    private static final long START_TIME_IN_MILLIS = 300000;
    YouTubePlayerView youTubePlayerView;
    public TextView mTextViewCountDown;
    private Button mButtonStartPause;
    public CountDownTimer mCountDownTimer;
    public static boolean mTimerRunning;
    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;
    public static boolean timerCancel;
    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_exercise);
        youTubePlayerView = findViewById(R.id.player);
        mTextViewCountDown = findViewById(R.id.text_view_countdown);
        mButtonStartPause = findViewById(R.id.button_start_pause);
        btnBack = findViewById(R.id.btnBack);
        //String youtubeKey = exercise.getId();
        String youtubeKey = "ASdvN_XEl_c";
        timerCancel = false;
        initializeYoutube(youtubeKey);
        updateCountDownText();

        mButtonStartPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mTimerRunning){
                    pauseTimer();
                }
                else{
                    startTimer();
                }
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    public void onBackPressed(){
            super.onBackPressed();
    }



    private void initializeYoutube(String youtubeKey) {
        youTubePlayerView.initialize(YOUTUBE_API_KEY, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.cueVideo(youtubeKey);
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                Log.d("d", "onInitializationFailure");
            }
        });
    }


    public void startTimer(){
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisLeftUntilFinish) {
                mTimeLeftInMillis = millisLeftUntilFinish;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                mTimerRunning = false;
                mButtonStartPause.setText("Start");

                timerCancel=true;
            }
        }.start();
        mTimerRunning = true;
        mButtonStartPause.setText("pause");
    }


   // private void pauseTimer(){
   public void pauseTimer(){
        mCountDownTimer.cancel();
        mTimerRunning = false;
        mButtonStartPause.setText("Start");
    }

    public void updateCountDownText(){
        int minutes = (int)  (mTimeLeftInMillis / 1000) / 60;
        int seconds = (int)  (mTimeLeftInMillis / 1000) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(),"%02d:%02d", minutes, seconds);
        mTextViewCountDown.setText(timeLeftFormatted);
      /*  if(minutes == 0 && seconds == 0){
            timerCancel = true;
        } */

    }

}