package com.trodev.mycoachingstudents.syllabus;

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

public class SyllabusAdapter extends RecyclerView.Adapter<SyllabusAdapter.EbookViewHolder> {
    private Context context;
    private List<SyllabusData> list;

    public SyllabusAdapter(Context context, List<SyllabusData> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public EbookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.syllabus_item_layout,parent,false);
        return new EbookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EbookViewHolder holder, int position) {

        holder.syllabusEt.setText(list.get(position).getPdfTitle());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SyllabusViewActivity.class);
                intent.putExtra("pdfUrl",list.get(position).getPdfUrl());
                context.startActivity(intent);
            }
        });


        holder.syllabusDownload.setOnClickListener(new View.OnClickListener() {
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


    public class EbookViewHolder extends RecyclerView.ViewHolder {

        private TextView syllabusEt;
        private ImageView syllabusDownload;

        public EbookViewHolder(@NonNull View itemView) {
            super(itemView);

            syllabusEt = itemView.findViewById(R.id.syllabusEt);
            syllabusDownload = itemView.findViewById(R.id.syllabusDownload);

        }
    }
}
