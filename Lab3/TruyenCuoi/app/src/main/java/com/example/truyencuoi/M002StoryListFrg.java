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

public class M002StoryListFrg extends Fragment {

    private static final String KEY_TOPIC = "topic";

    public static M002StoryListFrg newInstance(String topic) {
        Bundle b = new Bundle();
        b.putString(KEY_TOPIC, topic);
        M002StoryListFrg f = new M002StoryListFrg();
        f.setArguments(b);
        return f;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.m002_frg_story_list, container, false);

        String topic = requireArguments().getString(KEY_TOPIC, "");

        ImageView ivBack = v.findViewById(R.id.iv_back);
        TextView tvTitle = v.findViewById(R.id.tv_title);
        ivBack.setVisibility(View.VISIBLE);
        tvTitle.setText(topic);

        ivBack.setOnClickListener(view -> {
            if (getActivity() != null) getActivity().onBackPressed();
        });

        ArrayList<StoryEntity> stories = StoryAssetReader.readStoriesOfTopic(requireContext(), topic);

        RecyclerView rv = v.findViewById(R.id.rv_story);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setAdapter(new StoryAdapter(stories, pos -> {
            if (getActivity() instanceof MainActivity) {
                ((MainActivity) getActivity()).gotoM003Screen(stories, pos);
            }
        }));

        return v;
    }
}
