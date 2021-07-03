package com.example.madcamp1;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class GalleryMainItemViewHolder {
    public ImageView ivIcon;

    public TextView tvName;

    public GalleryMainItemViewHolder(View a_view) {
        ivIcon = a_view.findViewById(R.id.iv_icon);
        tvName = a_view.findViewById(R.id.tv_name);
    }
}
