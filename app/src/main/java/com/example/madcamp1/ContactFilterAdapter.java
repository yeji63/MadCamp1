package com.example.madcamp1;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ContactFilterAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {

    private ArrayList<Contact> myDataList;
    private List<Contact> contactList;

    ContactFilterAdapter(ArrayList<Contact> dataList, List<Contact> contactList)
    {
        this.myDataList = dataList;
        this.contactList = contactList;
    }

    @Override
    public ContactAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //전개자(Inflater)를 통해 얻은 참조 객체를 통해 뷰홀더 객체 생성
        View view = inflater.inflate(R.layout.contact_recyclerview, parent, false);
        ContactAdapter.ViewHolder viewHolder= new ContactAdapter.ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ContactAdapter.ViewHolder viewHolder, int position)
    {
        //ViewHolder가 관리하는 View에 position에 해당하는 데이터 바인딩
        viewHolder.imageView.setImageDrawable(contactList.get(position).getImage());
//        viewHolder.imageView.setImageResource(myDataList.get(position).getImage());
        viewHolder.name.setText(contactList.get(position).getName());
        viewHolder.number.setText(contactList.get(position).getNumber());
        viewHolder.email.setText(contactList.get(position).getEmail());
    }

    @Override
    public int getItemCount()
    {
        //Adapter가 관리하는 전체 데이터 개수 반환
        return contactList.size();
    }

    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        contactList.clear();
        if (charText.length() == 0) {
            contactList.addAll(myDataList);
        } else {
            for (Contact contact : myDataList) {
                if (contact.getName().toLowerCase().contains(charText)) {
                    contactList.add(contact);
                    Log.d("<<CONTACTSFILTER>>", "name=" + contact.getName() + ", number=" + contact.getNumber());
                } else if (contact.getNumber().toLowerCase().contains(charText)) {
                    contactList.add(contact);
                    Log.d("<<CONTACTSFILTER>>", "name=" + contact.getName() + ", number=" + contact.getNumber());
                } else if (contact.getEmail() != null && contact.getEmail().toLowerCase().contains(charText)) {
                    contactList.add(contact);
                    Log.d("<<CONTACTSFILTER>>", "name=" + contact.getName() + ", number=" + contact.getNumber());
                }

            }
        }
        notifyDataSetChanged();
    }
}
