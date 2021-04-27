package com.example.fityet.Models;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseFile;
import com.parse.ParseUser;

@ParseClassName("User")
public class User extends ParseObject{


    public  final String KEY_AVATAR = "Avatar";
    public static final String KEY_GOAL = "goal";
    public static final String KEY_USER = "user";
    public static final String KEY_PROGRESS = "";
    public static final String KEY_WEIGHT = "";
    public static final String KEY_HEIGHT = "";
    public static final String KEY_PROFILE_PIC = "profilepic";

    public ParseFile getAvatar(){

        return getParseFile(KEY_AVATAR);

    }

    public void setAvatar(ParseFile avatar){

        put(KEY_AVATAR, avatar);

    }

    public int getProgress(){

        return getInt(KEY_PROGRESS);

    }

    public void setProgress(int progress){

        put(KEY_PROGRESS, progress);

    }

    public int getWeight(){

        return getInt(KEY_WEIGHT);

    }

    public void setWeight(int weight){

        put(KEY_WEIGHT, weight);

    }

    public int getHeight(){

        return getInt(KEY_HEIGHT);

    }

    public void setHeight(int height){

        put(KEY_HEIGHT, height);

    }

    public String getKeyGoal(){

        return getString(KEY_GOAL);

    }

    public void setKeyGoal(String goal) {

        put(KEY_GOAL, goal);
    }

    public ParseUser getUser(){

        return getParseUser(KEY_USER);

    }

    public void setUser(ParseUser user) {

        put(KEY_USER, user);
    }

    public ParseFile getProfileImage(){
        return getParseFile(KEY_PROFILE_PIC);
    }

    public void setProfileImage(ParseFile profileImage){
        put(KEY_PROFILE_PIC, profileImage);
    }

}