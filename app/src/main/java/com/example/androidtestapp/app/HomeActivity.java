package com.example.androidtestapp.app;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import com.example.androidtestapp.app.services.DataFetcherService;
import com.example.androidtestapp.app.tabsswipe.adapter.TabsPagerAdapter;


public class HomeActivity extends FragmentActivity implements ActionBar.TabListener {

    private static final String LOG_TAG = "HOME_ACTIVITY";
    private ViewPager mViewPager;
    private ActionBar actionBar;
    private TabsPagerAdapter mAdapter;

    private String[] tabs = {"News", "Map", "Order", "MyAccount", "Basket"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mAdapter = new TabsPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mAdapter);

        actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(false);
            actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

            // Add tabs
            for (String tabName : tabs) {
                actionBar.addTab(actionBar.newTab().setText(tabName).setTabListener(this));
            }
        }

        /**
         * on swiping the viewpager make respective tab selected
         * */
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                // on changing the page
                // make respected tab selected
                actionBar.setSelectedNavigationItem(position);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });

        Log.i(LOG_TAG, "Starting service");
        Intent mServiceIntent = new Intent(this, DataFetcherService.class);
        //mServiceIntent.setData("");
        startService(mServiceIntent);
    }

    @Override
    public void onTabReselected(Tab tab, FragmentTransaction ft) {
    }
    @Override
     public void onTabSelected(Tab tab, FragmentTransaction ft) {
        Log.i(LOG_TAG, "position: " + tab.getPosition());
        mViewPager.setCurrentItem(tab.getPosition());
    }
    @Override
    public void onTabUnselected(Tab tab, FragmentTransaction ft) {
    }
}
