package com.example.truyencuoi;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class M003StoryPageFrg extends Fragment {

    private static final String KEY_STORY = "story";

    public static M003StoryPageFrg newInstance(StoryEntity story) {
        Bundle b = new Bundle();
        b.putSerializable(KEY_STORY, story);
        M003StoryPageFrg f = new M003StoryPageFrg();
        f.setArguments(b);
        return f;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.m003_page_story, container, false);
        StoryEntity s = (StoryEntity) requireArguments().getSerializable(KEY_STORY);

        ((TextView) v.findViewById(R.id.tv_story_title)).setText(s.title);
        ((TextView) v.findViewById(R.id.tv_story_content)).setText(
                (s.content == null || s.content.isEmpty()) ? "(Chưa có nội dung)" : s.content
        );

        return v;
    }
}
