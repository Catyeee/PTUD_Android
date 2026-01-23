package com.example.truyencuoi;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;

public class M003StoryDetailFrg extends Fragment {

    private static final String KEY_LIST = "list";
    private static final String KEY_POS  = "pos";

    public static M003StoryDetailFrg newInstance(ArrayList<StoryEntity> list, int pos) {
        Bundle b = new Bundle();
        b.putSerializable(KEY_LIST, list);
        b.putInt(KEY_POS, pos);

        M003StoryDetailFrg f = new M003StoryDetailFrg();
        f.setArguments(b);
        return f;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.m003_viewpager_host, container, false);

        ArrayList<StoryEntity> list =
                (ArrayList<StoryEntity>) requireArguments().getSerializable(KEY_LIST);
        int pos = requireArguments().getInt(KEY_POS, 0);

        // ===== HEADER =====
        ImageView ivBack = v.findViewById(R.id.iv_back);
        TextView tvTitle = v.findViewById(R.id.tv_title);

        ivBack.setVisibility(View.VISIBLE);
        tvTitle.setText(list.get(pos).topic);

        ivBack.setOnClickListener(view -> {
            if (getActivity() != null) getActivity().onBackPressed();
        });

        // ===== VIEWPAGER =====
        ViewPager2 vp = v.findViewById(R.id.vp_story);
        vp.setAdapter(new StoryPagerAdapter(this, list));
        vp.setCurrentItem(pos, false);

        vp.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tvTitle.setText(list.get(position).topic);
            }
        });

        // ===== XOAY MÀN HÌNH =====
        bindRotateButtons(v);

        return v;
    }

    // ================= XOAY MÀN HÌNH =================

    private void bindRotateButtons(View v) {
        View btnOn  = v.findViewById(R.id.btnRotateOn);
        View btnOff = v.findViewById(R.id.btnRotateOff);

        if (btnOn == null || btnOff == null) return;

        btnOff.setOnClickListener(view -> lockCurrentOrientation());
        btnOn.setOnClickListener(view -> allowAutoRotate());
    }

    private void allowAutoRotate() {
        if (getActivity() == null) return;
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
    }

    private void lockCurrentOrientation() {
        if (getActivity() == null) return;
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

}
