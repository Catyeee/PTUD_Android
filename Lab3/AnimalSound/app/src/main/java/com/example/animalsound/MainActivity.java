package com.example.animalsound;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // nếu bạn muốn mở thẳng màn m003_act_detail:
        startActivity(new Intent(this, M003ActDetail.class));
        finish();
    }
}
