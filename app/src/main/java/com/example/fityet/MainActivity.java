package com.example.fityet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.fragment.app.Fragment;
import com.example.fityet.fragments.ProfileFragment;

import android.os.Bundle;
import com.parse.ParseQuery;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.fragment.app.FragmentManager;

public class MainActivity extends AppCompatActivity {

    final FragmentManager fragmentManager = getSupportFragmentManager();

    private BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigation = findViewById(R.id.bottomNavigation);

        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fragment;
                switch (menuItem.getItemId()) {
                    case R.id.action_timer:
                        //TODO: Update fragment
                        Toast.makeText(MainActivity.this, "Timer time!", Toast.LENGTH_SHORT).show();
                        //fragment = new TimerFragment();
                        break;
                    case R.id.action_goals:
                        Toast.makeText(MainActivity.this, "Goals time!", Toast.LENGTH_SHORT).show();
                        //fragment = new GoalsFragment();
                        break;
                    case R.id.action_videos:
                        Toast.makeText(MainActivity.this, "Video time!", Toast.LENGTH_SHORT).show();
                        //fragment = new VideoFragment();
                        break;
                    case R.id.action_calendar:
                        Toast.makeText(MainActivity.this, "Calendar time!", Toast.LENGTH_SHORT).show();
                        //fragment = new CalendarFragment();
                        break;
                    case R.id.action_profile:
                    default:
                        //TODO: Update fragment
                        Toast.makeText(MainActivity.this, "Profile time!", Toast.LENGTH_SHORT).show();
                        fragment = new ProfileFragment();
                        break;
                }

                fragmentManager.beginTransaction().replace(R.id.flContainer, fragment).commit();

                return true;

            }
        });

        //default selection will be
        bottomNavigation.setSelectedItemId(R.id.action_goals);

    }
}