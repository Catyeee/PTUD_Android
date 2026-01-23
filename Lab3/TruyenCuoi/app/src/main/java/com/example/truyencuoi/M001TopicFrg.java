package com.example.truyencuoi;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class M001TopicFrg extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.m001_frg_topic, container, false);

        // header
        ImageView ivBack = v.findViewById(R.id.iv_back);
        TextView tvTitle = v.findViewById(R.id.tv_title);
        ivBack.setVisibility(View.GONE);
        tvTitle.setText("Truyện cười 😜😜😜😍");

        RecyclerView rv = v.findViewById(R.id.rv_topic);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));

        ArrayList<String> topics = StoryAssetReader.getAllTopics(requireContext());
        rv.setAdapter(new TopicAdapter(topics, topic -> {
            if (getActivity() instanceof MainActivity) {
                ((MainActivity) getActivity()).gotoM002(topic);

            }
        }));

        return v;
    }
}
