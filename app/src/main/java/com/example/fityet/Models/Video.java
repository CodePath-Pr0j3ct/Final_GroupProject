package com.example.fityet.Models;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Video {

 //   private static List<Video> youtubeVideos;
    String videoURL;
    String title;
   // Vector<Video> youtubeVideos;

    public Video(){

    }

    public static List<Video> videoList() {
        Vector<Video> youtubeVideos = new Vector<Video>();
        //Add videos in youtube video list/vector
        youtubeVideos.add(new Video("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/N3aSoC0HHdU\" frameborder=\"0\" allowfullscreen></iframe>") );
        youtubeVideos.add(new Video("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/7KSNmziMqog\" frameborder=\"0\" allowfullscreen></iframe>") );
        youtubeVideos.add(new Video("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/H3jJ29oE8Zg\" frameborder=\"0\" allowfullscreen></iframe>") );

        return youtubeVideos;
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

    public String getTitle(){
        return title;
    }
}
