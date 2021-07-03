package com.example.madcamp1;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class ContactViewHolder extends RecyclerView.ViewHolder{
    ImageView imageView;
    TextView name;
    TextView number;
    TextView email;

    ContactViewHolder(View itemView)
    {
        super(itemView);

        imageView = itemView.findViewById(R.id.imageView2);
        name = itemView.findViewById(R.id.name);
        number = itemView.findViewById(R.id.number);
        email = itemView.findViewById(R.id.email);
    }
}