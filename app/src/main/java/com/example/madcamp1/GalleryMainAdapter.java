package com.example.madcamp1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import java.util.List;

public class GalleryMainAdapter extends ArrayAdapter<GalleryMaiItem> {
    private Context mContext;
    private List<GalleryMaiItem> mItemList;

    public GalleryMainAdapter(Context a_context, List<GalleryMaiItem> a_itemList){
        super(a_context, R.layout.content_grid_item, a_itemList);
        mContext=a_context;
        mItemList=a_itemList;
    }

//
//    @Override
//    public int getCount() {
//        return mThumbIds.length;
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return mThumbIds[position];
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return 0;
//    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        GalleryMainItemViewHolder viewHolder;
        if(convertView==null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.content_grid_item, parent, false);

            viewHolder=new GalleryMainItemViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (GalleryMainItemViewHolder) convertView.getTag();
        }
        final GalleryMaiItem countryItem = mItemList.get(position);

        //img 설정
        viewHolder.ivIcon.setImageResource(countryItem.getImageResId());
        viewHolder.ivIcon.setScaleType(ImageView.ScaleType.CENTER_CROP);
        //name 설정
        viewHolder.tvName.setText(countryItem.getName());
        return convertView;

//        ImageView imageView = new ImageView(mContext);
//        imageView.setImageResource(mThumbIds[position]);
//        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//        imageView.setLayoutParams(new ViewGroup.LayoutParams(350, 350));
//        return imageView;
    }
}
