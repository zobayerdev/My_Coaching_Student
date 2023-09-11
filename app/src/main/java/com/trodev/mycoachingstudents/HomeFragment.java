package com.trodev.mycoachingstudents;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.trodev.mycoachingstudents.question.QuestionActivity;
import com.trodev.mycoachingstudents.syllabus.SyllabusActivity;

public class HomeFragment extends Fragment {

    LinearLayout examsyllabus, questionLl;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        /*init views*/
        examsyllabus = view.findViewById(R.id.examsyllabus);
        questionLl = view.findViewById(R.id.questionLl);

        /*click on listener*/
        examsyllabus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), SyllabusActivity.class));
            }
        });

        questionLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), QuestionActivity.class));
            }
        });


        return view;
    }
}