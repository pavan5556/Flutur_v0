package com.sailstudios.apps.flutur_v0;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class PagerAdapter extends FragmentPagerAdapter {


    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        Fragment fragment = new PageFragment();

        Bundle args = new Bundle();
        args.putInt(Utils.PAGE_POSITION, position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public float getPageWidth(int position) {
        return 0.93f;
    }

    @Override
    public int getCount() {
        return 10;
    }
}
