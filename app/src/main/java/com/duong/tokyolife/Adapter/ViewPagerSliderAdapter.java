package com.duong.tokyolife.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class ViewPagerSliderAdapter extends FragmentPagerAdapter {

    List<Fragment> dsFragment;
    public ViewPagerSliderAdapter(FragmentManager fm, List<Fragment> dsFragment) {
        super(fm);
        this.dsFragment=dsFragment;
    }

    @Override
    public Fragment getItem(int i) {
        return dsFragment.get(i);
    }

    @Override
    public int getCount() {
        return dsFragment.size();
    }
}
