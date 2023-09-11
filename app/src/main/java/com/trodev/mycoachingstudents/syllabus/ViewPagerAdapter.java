package com.trodev.mycoachingstudents.syllabus;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    public ViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        if (position == 0) {
            return new ClassSixFragment();
        } else if (position == 1) {
            return new ClassSevenFragment();
        } else if (position == 2) {
            return new ClassEightFragment();
        } else if (position == 3) {
            return new ClassNineFragment();
        } else if (position == 4) {
            return new ClassTenFragment();
        } else {
            return new ClassHscFragment();
        }

    }

    @Override
    public int getCount() {
        return 6;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        if (position == 0) {
            return "Class 6";
        } else if (position == 1) {
            return "Class 7";
        } else if (position == 2) {
            return "Class 8";
        } else if (position == 3) {
            return "Class 9";
        } else if (position == 4) {
            return "Class 10";
        } else {
            return "Class HSC";
        }

    }
}
