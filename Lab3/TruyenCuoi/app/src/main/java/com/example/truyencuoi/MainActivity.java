package com.example.truyencuoi;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_main);

        if (b == null) {
            showFrg(new M000SplashFrg(), false);
        }
    }

    private void showFrg(Fragment frg, boolean addBackStack) {
        var tx = getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.ln_main, frg);
        if (addBackStack) tx.addToBackStack(null);
        tx.commit();
    }

    public void gotoM001Screen() {
        showFrg(new M001TopicFrg(), false);
    }

    public void gotoM002(String topic) {
        showFrg(M002StoryListFrg.newInstance(topic), true);}

    public void gotoM003Screen(ArrayList<StoryEntity> list, int pos) {
        showFrg(M003StoryDetailFrg.newInstance(list, pos), true);
    }
}
