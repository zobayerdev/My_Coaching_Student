package com.trodev.mycoachingstudents.question;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class QuestionPagerAdapter extends FragmentPagerAdapter {
    public QuestionPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        if (position == 0) {
            return new QuestionSixFragment();
        } else if (position == 1) {
            return new QuestionSevenFragment();
        } else if (position == 2) {
            return new QuestionEightFragment();
        } else if (position == 3) {
            return new QuestionNineFragment();
        } else if (position == 4) {
            return new QuestionTenFragment();
        } else {
            return new QuestionHscFragment();
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
