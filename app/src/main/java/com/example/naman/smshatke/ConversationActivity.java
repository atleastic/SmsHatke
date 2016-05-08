package com.example.naman.smshatke;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.Telephony;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ConversationActivity extends AppCompatActivity {

    private String TAG="ConversationActivity";
    String PROJECTIONS[]=new String[]{
            Telephony.Sms.Inbox._ID,
            Telephony.Sms.Inbox.THREAD_ID,
            Telephony.Sms.Inbox.ADDRESS,
            Telephony.Sms.Inbox.BODY
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversation);
        Log.d(TAG,"onCreate");
        Intent i=getIntent();
        String THREAD_ID=i.getStringExtra("1");
        Toast.makeText(getBaseContext(),THREAD_ID,Toast.LENGTH_SHORT).show();

        ListView mListView=(ListView)findViewById(R.id.listView_conversation);

        String selection= Telephony.Sms.Inbox.THREAD_ID + " LIKE ?";
        String[] selectionargs={THREAD_ID};
        Cursor cursor = getContentResolver().query(Uri.parse("content://sms/inbox"), PROJECTIONS, selection, selectionargs, null);

        String[] conversation_from=new String[]{
                Telephony.Sms.Inbox.ADDRESS,
                Telephony.Sms.Inbox.BODY
        };
        int[] conversation_to=new int[]{R.id.cardview_title,R.id.cardview_body};
        SimpleCursorAdapter mCursorAdapter=new SimpleCursorAdapter(getApplicationContext(),R.layout.adapter_cardview,cursor,conversation_from,conversation_to,0);
        mListView.setAdapter(mCursorAdapter);
        if (cursor.moveToFirst()) { // must check the result to prevent exception
            do {
                String msgData = "";
                for(int idx=0;idx<cursor.getColumnCount();idx++)
                {
                    msgData += " " + cursor.getColumnName(idx) + ":" + cursor.getString(idx);
                    Log.d("NamanConverse",msgData);
                }
                // use msgData
            } while (cursor.moveToNext());
        } else {
            // empty box, no SMS
            Log.d("Naman","Empty inbox");
        }
    }
}