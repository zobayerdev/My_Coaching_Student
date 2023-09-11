package com.trodev.mycoachingstudents.question;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.trodev.mycoachingstudents.R;

public class QuestionActivity extends AppCompatActivity {

    TabLayout tab;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        getSupportActionBar().setTitle("Exam Question");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tab = findViewById(R.id.tab);
        viewPager = findViewById(R.id.viewPager);

        QuestionPagerAdapter adapter = new QuestionPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        tab.setupWithViewPager(viewPager);
    }
}