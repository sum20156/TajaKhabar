package com.example.suman.tajaKhabar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class PagerAdapter extends FragmentPagerAdapter {

    int mnooftabs;

    public PagerAdapter(FragmentManager fm,int numberoftabs) {
        super(fm);
        this.mnooftabs=numberoftabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){

            case 0:
                Trending trending = new Trending();
                return trending;
            case 1:
                Technology technology = new Technology();
                return technology;

            case 2:
                Sports sports = new Sports();
                return sports;
            case 3:
                Entertainement entertainement = new Entertainement();
                return entertainement;
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return mnooftabs;
    }
}
