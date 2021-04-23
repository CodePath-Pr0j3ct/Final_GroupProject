package com.example.fityet.Models;

public class Video {

    String videoURL;

    public Video(){

    }

    public Video(String URL){
        this.videoURL=URL;
    }

    public String getVideoURL(){
        return videoURL;
    }

    public void setVideoURL(String videoURL) {
        this.videoURL = videoURL;
    }
}
