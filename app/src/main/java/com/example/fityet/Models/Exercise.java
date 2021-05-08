package com.example.fityet.Models;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseUser;

@ParseClassName("Exercise")
public class Exercise extends ParseObject implements Comparable {
    public final static String keyUser = "User";
    public final static String keyExercise = "exercise";
    public final static String keyHour = "hour";
    public final static String keyMin = "minute";
    public final static String keyDaysOfWeek = "daysOfWeek1";

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

    @Override
    public int compareTo(Object exer) {
        Exercise ex = new Exercise();
        ex = (Exercise) exer;
        if (this.getHour() > ex.getHour()){
            return 1;
        }
        else if(this.getHour() < ex.getHour()){
            return -1;
        }
        else {
            if (this.getMinutes() > ex.getMinutes()){
                return 1;
            }
            else if(this.getMinutes() < ex.getMinutes()) {
                return -1;
            }
            else { return 0; }
        }
    }
}
