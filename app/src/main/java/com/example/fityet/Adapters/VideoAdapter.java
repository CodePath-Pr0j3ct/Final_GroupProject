package com.example.fityet.Adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fityet.Models.Video;
import com.example.fityet.R;

import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder> {
    Context context;
    List<Video> videos;

    public VideoAdapter(Context context, List<Video> videos){
        this.context=context;
        this.videos=videos;
    }
    public VideoAdapter(List<Video> videos){

        this.videos=videos;
    }

    @NonNull
    @Override
    public VideoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View videoView = LayoutInflater.from(context).inflate(R.layout.item_video, parent, false);
        return new ViewHolder(videoView);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoAdapter.ViewHolder holder, int position) {
        holder.videoWeb.loadData(videos.get(position).getVideoURL(), "text/html", "utf-8");
    }

    @Override
    public int getItemCount() {
        return videos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        WebView videoWeb;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            videoWeb = (WebView) itemView.findViewById(R.id.wvVideo);
            videoWeb.getSettings().setJavaScriptEnabled(true);
            videoWeb.setWebChromeClient(new WebChromeClient());
        }
    }
}
