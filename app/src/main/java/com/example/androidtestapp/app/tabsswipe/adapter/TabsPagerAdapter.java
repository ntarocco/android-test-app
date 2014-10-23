package com.example.androidtestapp.app.tabsswipe.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.example.androidtestapp.app.*;

public class TabsPagerAdapter extends FragmentStatePagerAdapter {

    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int index) {
        switch (index) {
            case 0:
                return new NewsFragment();
            case 1:
                return new MapFragment();
            case 2:
                return new OrderFragment();
            case 3:
                return new MyAccountFragment();
            case 4:
                return new BasketFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 5;
    }
}
