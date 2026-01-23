package com.example.truyencuoi;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TopicAdapter extends RecyclerView.Adapter<TopicAdapter.VH> {

    public interface OnTopicClick { void onClick(String topic); }

    private final ArrayList<String> topics;
    private final OnTopicClick listener;

    public TopicAdapter(ArrayList<String> topics, OnTopicClick listener) {
        this.topics = topics;
        this.listener = listener;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_topic, parent, false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VH h, int position) {
        String topic = topics.get(position);
        h.tv.setText(topic);

        Bitmap bmp = StoryAssetReader.loadTopicImage(h.itemView.getContext(), topic);
        if (bmp != null) h.iv.setImageBitmap(bmp);
        else h.iv.setImageResource(R.drawable.ic_face);

        h.itemView.setOnClickListener(v -> {
            if (listener != null) listener.onClick(topic);
        });
    }

    @Override
    public int getItemCount() { return topics == null ? 0 : topics.size(); }

    static class VH extends RecyclerView.ViewHolder {
        ImageView iv;
        TextView tv;
        VH(@NonNull View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv_topic);
            tv = itemView.findViewById(R.id.tv_topic);
        }
    }
}
