package com.example.naman.smshatke.homescreen.fragments;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Telephony;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.naman.smshatke.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {
    String PROJECTIONS[]=new String[]{
            Telephony.Sms._ID,
            Telephony.Sms.ADDRESS,
            Telephony.Sms.BODY
    };

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final EditText mSearchEdittext=(EditText)getActivity().findViewById(R.id.search_edit_text);
        Button mSearchButton=(Button)getActivity().findViewById(R.id.search_button);
        mSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectionargs[]={"%" + mSearchEdittext.getText().toString()+"%"};
                String selection=Telephony.Sms.BODY + " LIKE ?";
                Cursor cursor = getActivity().getContentResolver().query(Uri.parse("content://sms"), PROJECTIONS,
                        selection, selectionargs, null);
                ListView mListView=(ListView)getActivity().findViewById(R.id.search_list_view);
                String[] from=new String[]{
                        Telephony.Sms.ADDRESS,
                        Telephony.Sms.BODY
                };
                int to[]=new int[]{
                        R.id.cardview_title,R.id.cardview_body
                };
                SimpleCursorAdapter mAdapter=new SimpleCursorAdapter(getActivity().getApplicationContext(),R.layout.adapter_cardview,cursor,from,to,0);
                mListView.setAdapter(mAdapter);
                if (cursor.moveToFirst()) { // must check the result to prevent exception
                    do {
                        String msgData = "";
                        for(int idx=0;idx<cursor.getColumnCount();idx++)
                        {
                            msgData += " " + cursor.getColumnName(idx) + ":" + cursor.getString(idx);
                            Log.d("NamanSearch",msgData);
                        }
                        // use msgData
                    } while (cursor.moveToNext());
                } else {
                    // empty box, no SMS
                    Log.d("Naman","Empty inbox");
                }
            }
        });
    }
}
