package com.example.fityet.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.example.fityet.R;

import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class TimerFragment extends Fragment {

    TextView tvCountdown, tvComingUp, tvExercise;
    ImageView tvThumbnail;
    Button btnTimer;

    CountDownTimer countdownTimer;
    long milliRemaining = 600000;
    boolean timerRunning;

    public TimerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_timer, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        tvCountdown = view.findViewById(R.id.tvCountdown);

        tvComingUp = view.findViewById(R.id.tvComingUp);

        tvExercise = view.findViewById(R.id.tvExercise);

        tvThumbnail = view.findViewById(R.id.thumbNail);

        btnTimer = view.findViewById(R.id.btnTime);

        btnTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startStop();

            }
        });

        updateTimer();

    }

    public void startStop() {

        if(timerRunning) {

            stopTimer();

        }

        else {

            startTimer();

        }

    }

    public void startTimer() {

        countdownTimer = new CountDownTimer(milliRemaining, 1000) {

            @Override
            public void onTick(long l) {

                milliRemaining = l;

                updateTimer();

            }

            public void onFinish() {


            }

        }.start();

        timerRunning = true;

    }

    public void stopTimer() {

        countdownTimer.cancel();

        timerRunning = false;

    }

    public void updateTimer(){

        int minutes = (int) (milliRemaining / 1000) / 60;

        int seconds = (int) (milliRemaining /1000) % 60;

        String timeRemaining = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

        tvCountdown.setText(timeRemaining);

    }

}