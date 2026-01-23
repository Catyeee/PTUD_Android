package com.example.truyencuoi;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class M000SplashFrg extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.m000_frg_splash, container, false);

        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            if (getActivity() instanceof MainActivity) {
                ((MainActivity) getActivity()).gotoM001Screen();
            }
        }, 1500);

        return v;
    }
}
