import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.madcamp1.ContactListViewAdapter;
import com.example.madcamp1.MainActivity;
import com.example.madcamp1.R;

import java.util.Locale;

public class SearchContact extends AppCompatActivity {

    private ContactListViewAdapter adapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_search);
        init();

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

    public void init() {
        ContactList ContactList = new ContactList(this);
        adapter = new ContactListViewAdapter(this, ContactList);
        listView.setAdapter(adapter);
    }

    @Bind(R.id.listview)
    ListView listView;
    @Bind(R.id.editsearch)
    EditText editsearch;
    @Bind(R.id.Layout_Internet)
    RelativeLayout internetLayout;
}