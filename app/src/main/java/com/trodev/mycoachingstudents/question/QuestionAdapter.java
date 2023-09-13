package com.trodev.mycoachingstudents.question;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.trodev.mycoachingstudents.R;

import java.util.List;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.QuestionViewHolder> {

    private Context context;
    private List<QuestionData> list;

    public QuestionAdapter(Context context, List<QuestionData> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public QuestionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.exam_item_layout,parent,false);

        return new QuestionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionViewHolder holder, int position) {

        holder.examEt.setText(list.get(position).getPdfTitle());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, QuestionViewActivity.class);
                intent.putExtra("pdfUrl",list.get(position).getPdfUrl());
                context.startActivity(intent);
            }
        });


        /*pdf downloader method*/
        /*it's make by me*/
        holder.questionDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent download = new Intent(Intent.ACTION_VIEW);
                download.setData(Uri.parse(list.get(position).getPdfUrl()));
                context.startActivity(download);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class QuestionViewHolder extends RecyclerView.ViewHolder {

        private TextView examEt;
        private ImageView questionDownload;

        public QuestionViewHolder(@NonNull View itemView) {
            super(itemView);

            /*init views from data layout*/
            examEt = itemView.findViewById(R.id.examEt);
            questionDownload = itemView.findViewById(R.id.questionDownload);
        }
    }
}
