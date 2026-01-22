package com.example.myapplication;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class M000ActSplash extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m001_act_splash);
        View loading = findViewById(R.id.loadingView);
        loading.postDelayed(() -> loading.setVisibility(View.GONE), 1500); } }