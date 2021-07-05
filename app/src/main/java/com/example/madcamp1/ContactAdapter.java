package com.example.madcamp1;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {

    public static ArrayList<Contact> myDataList = null;

    ContactAdapter(ArrayList<Contact> dataList)
    {
        myDataList = dataList;
    }

    @Override
    public ContactAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //전개자(Inflater)를 통해 얻은 참조 객체를 통해 뷰홀더 객체 생성
        View view = inflater.inflate(R.layout.contact_recyclerview, parent, false);
        ContactAdapter.ViewHolder vh = new ContactAdapter.ViewHolder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(ContactAdapter.ViewHolder viewHolder, int position)
    {

        //ViewHolder가 관리하는 View에 position에 해당하는 데이터 바인딩
        viewHolder.imageView.setImageDrawable(myDataList.get(position).getImage());
//        viewHolder.imageView.setImageResource(myDataList.get(position).getImage());
        viewHolder.name.setText(myDataList.get(position).getName());
        viewHolder.number.setText(myDataList.get(position).getNumber());
        viewHolder.email.setText(myDataList.get(position).getEmail());

    }

    @Override
    public int getItemCount()
    {
        //Adapter가 관리하는 전체 데이터 개수 반환
        return myDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView name;
        TextView number;
        TextView email;

        public ViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION){
                        Contact item = myDataList.get(pos);
                        Intent i = new Intent(v.getContext(), ContactDetailActivity.class);
                        i.putExtra("contactpos", pos);
                        v.getContext().startActivity(i);
                    }
                }
            });

            imageView = itemView.findViewById(R.id.imageView2);
            name = itemView.findViewById(R.id.name);
            number = itemView.findViewById(R.id.number);
            email = itemView.findViewById(R.id.email);

        }
    }
}
