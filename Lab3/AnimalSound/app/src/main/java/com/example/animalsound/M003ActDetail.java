package com.example.animalsound;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class M003ActDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m003_act_detail);
        // Không cần setText bằng code vì text lấy từ strings.xml theo ngôn ngữ máy
    }
}
