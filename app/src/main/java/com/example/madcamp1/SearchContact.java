package com.example.madcamp1;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.madcamp1.ContactListViewAdapter;
import com.example.madcamp1.MainActivity;
import com.example.madcamp1.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.madcamp1.Fragment1.contactList;
import static com.example.madcamp1.Fragment1.dataList;

public class SearchContact extends AppCompatActivity {

    private ContactFilterAdapter adapter;

    @BindView(R.id.recyclerView2)
    RecyclerView recyclerView2;
    @BindView(R.id.editTextTextPersonName)
    EditText editsearch;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_contact);
        ButterKnife.bind(this, this);
        adapter = new ContactFilterAdapter(dataList, contactList);
        recyclerView2.setAdapter(adapter);

        //검색창
        editsearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable arg0) {

                String text = editsearch.getText().toString()

                        .toLowerCase(Locale.getDefault());
                adapter.filter(text);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1,
                                          int arg2, int arg3) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2,
                                      int arg3) {
                // TODO Auto-generated method stub
            }
        });
    }

}