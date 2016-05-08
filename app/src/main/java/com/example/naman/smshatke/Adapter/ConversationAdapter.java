package com.example.naman.smshatke.Adapter;

/**
 * Created by naman on 5/8/2016.
 */

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.naman.smshatke.Model.Conversation;
import com.example.naman.smshatke.R;

import java.util.ArrayList;

/**
 * Created by naman on 5/8/2016.
 */
public class ConversationAdapter extends ArrayAdapter<Conversation> {

    public ConversationAdapter(Context context, ArrayList<Conversation> conversations)
    {
        super(context,0,conversations);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        Conversation conversation=getItem(position);
        if(convertView==null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.adapter_cardview, parent, false);
            if(convertView==null)
                Log.d("NamanNull","IS still NUll");
        }
        TextView AdapterTitle=(TextView)convertView.findViewById(R.id.cardview_title);
        TextView AdapterBody=(TextView)convertView.findViewById(R.id.cardview_title);

        AdapterTitle.setText(conversation.COUNT);
        AdapterBody.setText(conversation.SNIPPET);

        return convertView;
    }
}



