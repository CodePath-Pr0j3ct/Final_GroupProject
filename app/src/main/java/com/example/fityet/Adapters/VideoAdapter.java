package com.example.fityet.Adapters;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityOptionsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fityet.Models.DetailActivity;
import com.example.fityet.Models.Video;
import com.example.fityet.R;

import org.parceler.Parcels;

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
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("VideoAdapter", "onCreateViewHolder");
        View videoView = LayoutInflater.from(context).inflate(R.layout.item_video, parent, false);
        return new ViewHolder(videoView);
    }

    //involves populating data into item through holder
    @Override
    public void onBindViewHolder(@NonNull VideoAdapter.ViewHolder holder, int position) {
        Log.d("MovieAdapter", "onBindViewHolder " + position);
        Video video = videos.get(position);
        holder.videoWeb.loadData(videos.get(position).getVideoURL(), "text/html", "utf-8");
        holder.bind(video);
    }

    @Override
    public int getItemCount() {
        return videos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        WebView videoWeb;
        RelativeLayout container;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            container = itemView.findViewById(R.id.container);
            videoWeb = (WebView) itemView.findViewById(R.id.wvVideo);
            videoWeb.getSettings().setJavaScriptEnabled(true);
            videoWeb.setWebChromeClient(new WebChromeClient());
        }

        public void bind(Video video) {
            // 1.Register click listener on the whole row
            container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v){
                    // 2.Navigate to a new activity on tap
                    Intent i = new Intent(context, DetailActivity.class);
                    i.putExtra("movie", Parcels.wrap(video));
                    // Shared element transition
                    ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) context, itemView, "profile");
                    context.startActivity(i, options.toBundle());
                }
            });
        }
    }
}
