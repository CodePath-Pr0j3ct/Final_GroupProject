package com.example.fityet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.fityet.fragments.ProfileFragment;
import com.example.fityet.fragments.VideoFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.core.app.NotificationManagerCompat;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import androidx.fragment.app.Fragment;
import com.example.fityet.fragments.TimerFragment;
import android.os.Build;
import com.example.fityet.fragments.ScheduleFragment;
import android.os.Bundle;
import android.view.MenuItem;
import androidx.fragment.app.FragmentManager;

public class MainActivity extends AppCompatActivity {

    public static final String CHANNEL_ID = "ALARM_SERVICE_CHANNEL";
    final FragmentManager fragmentManager = getSupportFragmentManager();
    private NotificationManagerCompat notificationManager;

    private BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createNotificationChannel();
        setContentView(R.layout.activity_main);

        notificationManager = NotificationManagerCompat.from(this);

        bottomNavigation = findViewById(R.id.bottomNavigation);

        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fragment;
                switch (menuItem.getItemId()) {
                    case R.id.action_timer:
                        //TODO: Update fragment
                        fragment = new TimerFragment();
                        break;
                    case R.id.action_videos:
                        fragment = new VideoFragment();
                        break;
                    case R.id.action_calendar:
                        fragment = new ScheduleFragment();
                        break;
                    case R.id.action_profile:
                    default:
                        fragment = new ProfileFragment();
                        break;
                }

                fragmentManager.beginTransaction().replace(R.id.flContainer, fragment).commit();

                return true;

            }
        });

        //default selection will be
        bottomNavigation.setSelectedItemId(R.id.action_calendar);

    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel serviceChannel = new NotificationChannel(
                    CHANNEL_ID,
                    "Alarm Service Channel",
                    NotificationManager.IMPORTANCE_DEFAULT
            );

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(serviceChannel);
        }
    }

}