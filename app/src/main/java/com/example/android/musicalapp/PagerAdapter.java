package com.example.android.musicalapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class PagerAdapter extends FragmentStatePagerAdapter {

    private int noOfTabs;

    public PagerAdapter(FragmentManager fm , int noOfTabs) {
        super(fm);
        this.noOfTabs = noOfTabs;
    }


    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return  new ArtistsFragment();
            case 1:
                return  new AlbumsFragment();
            case 2:
                return  new FoldersFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return noOfTabs;
    }
}
