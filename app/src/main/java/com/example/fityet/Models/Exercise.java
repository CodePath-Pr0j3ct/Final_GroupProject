package com.example.fityet.Models;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseUser;

@ParseClassName("Exercise")
public class Exercise extends ParseObject {
    public final static String keyUser = "User";
    public final static String keyExercise = "exercise";
    public final static String keyHour = "hour";
    public final static String keyMin = "minute";
    public final static String keyDaysOfWeek = "daysOfWeek1";
    public final static String keyProgress = "progress";

    public Exercise(){


    }

    public String getExercise(){
        return getString(keyExercise);
    }
    public void setExercise(String exercise){
        put(keyExercise, exercise);
    }

    public int getHour(){
        return getInt(keyHour);
    }
    public void setHour(int hour){
        put(keyHour, hour);
    }

    public int getMinutes(){
        return getInt(keyMin);
    }
    public void setMinutes(int minutes){
        put(keyMin, minutes);
    }

    public String getDaysOfTheWeek(){
        return getString(keyDaysOfWeek);
    } //maybe wrong
    public void setDaysOfWeek(String daysOfWeek){ put(keyDaysOfWeek, daysOfWeek);
    }

    public ParseUser getUser(){
        return getParseUser(keyUser);
    }

    public void setUser(ParseUser addy){
        put(keyUser, addy);
    }


}
