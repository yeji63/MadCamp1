package com.example.madcamp1;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class ViewHolder extends RecyclerView.ViewHolder{
    ImageView imageView;
    TextView title;
    TextView grade;

    ViewHolder(View itemView)
    {
        super(itemView);

        imageView = itemView.findViewById(R.id.imageView2);
        title = itemView.findViewById(R.id.title);
        grade = itemView.findViewById(R.id.grade);
    }
}