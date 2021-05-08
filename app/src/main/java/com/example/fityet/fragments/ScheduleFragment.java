package com.example.fityet.fragments;

import android.annotation.SuppressLint;
import android.app.LoaderManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.fityet.Adapters.ExerciseAdapter;
import com.example.fityet.AlarmComponents.AlarmDialog;
import com.example.fityet.MainActivity;
import com.example.fityet.Models.Exercise;
import com.example.fityet.Models.ScheduleActivity;
import com.example.fityet.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ScheduleFragment #newInstance} factory method to
 * create an instance of this fragment.
 */
public class ScheduleFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    int counterForDay = 0;
    String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
    private TextView dayOfTheWeek;
    private Calendar calendar;
    private SimpleDateFormat dateFormat;
    private String date;
    private Button btnAdd;
    private Button btnNext;
    private RecyclerView rvExercises;
    protected ExerciseAdapter exerciseAdapter;
    //AlarmCursorAdapter cursorAdapter;
    // AlarmReminderDbHelper alarmReminderDbHelper = new AlarmReminderDbHelper(this);
    List<Exercise> exercisesForDay;
    List<Exercise> allExercises;
    ProgressDialog prgDialog;

    private static final int VEHICLE_LOADER=0;


    public ScheduleFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_schedule, container, false);
    }

    @SuppressLint("SimpleDateFormat")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dayOfTheWeek = view.findViewById(R.id.dayOfWeek);
        calendar = Calendar.getInstance();
        dayOfTheWeek.setText(days[counterForDay]);

        rvExercises = view.findViewById(R.id.rvExercises);
        btnNext = view.findViewById(R.id.next);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextDay();
            }
        });

        btnAdd = view.findViewById(R.id.add);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToScheduleAdd();
            }
        });
        exercisesForDay = new ArrayList<>();
        allExercises = new ArrayList<>();
        exerciseAdapter = new ExerciseAdapter(getContext(), exercisesForDay);
        rvExercises.setAdapter(exerciseAdapter);
        rvExercises.setLayoutManager(new LinearLayoutManager(getContext()));
        queryExercises();

        // Get a reference for the week view in the layout.
        /*mWeekView = view.findViewById(R.id.weekView);

        // Set an action when any event is clicked.
       // mWeekView.setOnEventClickListener(this);

        // The week view has infinite scrolling horizontally. We have to provide the events of a
        // month every time the month changes on the week view.
        mWeekView.setMonthChangeListener(this);

        // Set long press listener for events.
       // mWeekView.setEventLongPressListener(this);

        // Set up empty view click listener.
        mWeekView.setEmptyViewClickListener(this);

        // Initially, there will be no events on the week view because the user has not tapped on
        // it yet.
        mNewEvents = new ArrayList<WeekViewEvent>();*/


    }

    protected void queryExercises() {

        ParseQuery<Exercise> query = ParseQuery.getQuery(Exercise.class);
        query.whereEqualTo(Exercise.keyUser, ParseUser.getCurrentUser());
        query.findInBackground(new FindCallback<Exercise>() {
            @Override
            public void done(List<Exercise> objects, ParseException e) {
                exercisesForDay = new ArrayList<>();
                for (Exercise object : objects) {
                   // Log.d("fiiin" , ""+ Character.compare(object.getDaysOfTheWeek().charAt(counterForDay), '1'));
                    if (Character.compare(object.getDaysOfTheWeek().charAt(counterForDay), '1') == 0){
                        exercisesForDay.add(object);
                        //Log.d("fiiin", "" + exercises.size());
                    }
                }
                allExercises.addAll(objects);
                Log.d("ScheduleFragmentTag", exercisesForDay.toString());
                exerciseAdapter.clear();
                exerciseAdapter.addAll(exercisesForDay);
                exerciseAdapter.notifyDataSetChanged();
            }
            //this is where u can pass the data into something for alarm
        });
    }

    private void goToScheduleAdd() {
        Intent i = new Intent(getContext(), ScheduleActivity.class);
        startActivity(i);
        getActivity().onBackPressed();
    }

    private void nextDay() {
        counterForDay++;
        if (counterForDay == 7){
            counterForDay = 0;
        }
        dayOfTheWeek.setText(days[counterForDay]);
        queryExercises();

    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}