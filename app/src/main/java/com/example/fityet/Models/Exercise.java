package com.example.fityet.Models;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseUser;

@ParseClassName("Exercise")
public class Exercise extends ParseObject {
    public final static String keyUser = "User";
    public final static String keyAlarmId = "Id";
    public final static String keyExercise = "exercise";
    public final static String keyHour = "hour";
    public final static String keyMin = "minute";
    public final static String keyDaysOfWeek = "daysOfWeek1";
    public final Boolean keyMonday = false;
    public final Boolean keyTuesday = false;
    public final Boolean keyWednesday = false;
    public final Boolean keyThursday = false;
    public final Boolean keyFriday = false;
    public final Boolean keySaturday = false;
    public final Boolean keySunday = false;

    public Exercise(){


    }

    public String getAlarmId(){

        return getString(keyAlarmId);

    }

    public void setAlarmId(String id){

        put(keyAlarmId, id);

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

    public Boolean getMondayCheck(){

        return getBoolean(keyMonday.toString());

    }

    public void setMondayCheck(Boolean mondayCheck){

        put(keyMonday.toString(), mondayCheck);

    }

    public Boolean getTuesdayCheck(){

        return getBoolean(keyTuesday.toString());

    }

    public void setTuesdayCheck(Boolean tuesdayCheck){

        put(keyTuesday.toString(), tuesdayCheck);

    }

    public Boolean getWednesdayCheck(){

        return getBoolean(keyWednesday.toString());

    }

    public void setWednesdayCheck(Boolean wednesdayCheck){

        put(keyWednesday.toString(), wednesdayCheck);

    }
    public Boolean getThursdayCheck(){

        return getBoolean(keyThursday.toString());

    }

    public void setThursdayCheck(Boolean thursdayCheck){

        put(keyThursday.toString(), thursdayCheck);

    }
    public Boolean getFridayCheck(){

        return getBoolean(keyFriday.toString());

    }

    public void setFridayCheck(Boolean fridayCheck){

        put(keyFriday.toString(), fridayCheck);

    }
    public Boolean getSaturdayCheck(){

        return getBoolean(keySaturday.toString());

    }

    public void setSaturdayCheck(Boolean saturdayCheck){

        put(keySaturday.toString(), saturdayCheck);

    }
    public Boolean getSundayCheck(){

        return getBoolean(keySunday.toString());

    }

    public void setSundayCheck(Boolean sundayCheck){

        put(keySunday.toString(), sundayCheck);

    }


}

