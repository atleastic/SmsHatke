package com.example.naman.smshatke.homescreen.fragments;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Telephony;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.naman.smshatke.Adapter.ConversationAdapter;
import com.example.naman.smshatke.ConversationActivity;
import com.example.naman.smshatke.HomeActivity;
import com.example.naman.smshatke.Model.Conversation;
import com.example.naman.smshatke.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ConversationFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    // TODO: Rename and change types of parameters
    private ArrayList<Conversation> mConversationsList=new ArrayList<>();
    private String TAG="ConversationFragment";
    private String PROJECTIONS[]=new String[]{
            Telephony.Sms.ADDRESS
    };
    public ConversationFragment() {
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
        return inflater.inflate(R.layout.fragment_conversation, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Log.d(TAG,"onActivityCreated");
        super.onActivityCreated(savedInstanceState);
        ListView mConversationListView=(ListView)getActivity().findViewById(R.id.conversation_listview);
        Cursor cursor = getActivity().getContentResolver().query(Uri.parse("content://sms/conversations"), null, null, null, null);
        if (cursor.moveToFirst()) { // must check the result to prevent exception
            do {
                Conversation c=new Conversation(cursor.getString(0),cursor.getString(1),cursor.getString(2));
                mConversationsList.add(c);

                String msgData = "";
                for(int idx=0;idx<cursor.getColumnCount();idx++)
                {
                    msgData += " " + cursor.getColumnName(idx) + ":" + cursor.getString(idx);
                    Log.d("Naman",msgData);
                }
                // use msgData
            } while (cursor.moveToNext());
        } else {
            // empty box, no SMS
            Log.d("Naman","Empty inbox");
        }
        ConversationAdapter conversationAdapter=new ConversationAdapter(this.getActivity(),mConversationsList);
        mConversationListView.setAdapter(conversationAdapter);
        mConversationListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Conversation c= (Conversation) parent.getItemAtPosition(position);
                //Toast.makeText(getActivity(),c.THREAD_ID,Toast.LENGTH_SHORT).show();
                Intent i=new Intent(getActivity(),ConversationActivity.class);
                i.putExtra("1",c.THREAD_ID);
                startActivity(i);
            }
        });

    }
}
