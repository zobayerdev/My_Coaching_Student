package com.trodev.mycoachingstudents.syllabus;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.trodev.mycoachingstudents.R;

import java.util.ArrayList;
import java.util.List;

public class ClassHscFragment extends Fragment {
    private RecyclerView recyclerView;
    private DatabaseReference reference;
    private List<SyllabusData> list;
    private SyllabusAdapter adapter;
    ProgressBar progressBar;

    public ClassHscFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_class_hsc, container, false);
        /*init views*/
        recyclerView = view.findViewById(R.id.recyclerView);
        progressBar = view.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        /*database path*/
        //reference = FirebaseDatabase.getInstance().getReference().child("syllabus").child("syllabus_class_hsc/");
        reference = FirebaseDatabase.getInstance().getReference().child("questions").child("question_class_hsc/");


        /*load data*/
        loadSyllabus();

        return view;
    }
    private void loadSyllabus() {

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                list = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    SyllabusData data = snapshot.getValue(SyllabusData.class);
                    list.add(data);
                }

                progressBar.setVisibility(View.GONE);
                adapter = new SyllabusAdapter(getContext(), list);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                progressBar.setVisibility(View.VISIBLE);
                Toast.makeText(getContext(), "Failed", Toast.LENGTH_SHORT).show();
            }
        });

    }
}