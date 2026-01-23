package com.example.truyencuoi;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;

public class StoryPagerAdapter extends FragmentStateAdapter {

    private final ArrayList<StoryEntity> list;

    public StoryPagerAdapter(@NonNull Fragment fragment, ArrayList<StoryEntity> list) {
        super(fragment);
        this.list = list;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return M003StoryPageFrg.newInstance(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }
}
