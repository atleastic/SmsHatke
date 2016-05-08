package com.example.naman.smshatke.homescreen.fragments;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Telephony;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.example.naman.smshatke.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class InboxFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String TAG = "InboxFragment";
    private final String[] INBOX_PROJECTION=new String[]{
            Telephony.Sms._ID,
            Telephony.Sms.THREAD_ID,
            Telephony.Sms.ADDRESS,
            Telephony.Sms.BODY
    };
    String[] inbox_from=new String[]{
            Telephony.Sms.ADDRESS,
            Telephony.Sms.BODY};
    int inbox_to[]=new int[]{R.id.cardview_title,R.id.cardview_body};
    private final int INBOX_LOADER=0;
    private final Uri INBOX_TABLE_URI= Telephony.Sms.CONTENT_URI;
    SimpleCursorAdapter mInboxCursorAdapter;

    // TODO: Rename and change types of parameter

    public InboxFragment() {
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


        return inflater.inflate(R.layout.fragment_inbox, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mInboxCursorAdapter=new SimpleCursorAdapter(getActivity(),R.layout.adapter_cardview,null,inbox_from,inbox_to,0);
        getLoaderManager().initLoader(INBOX_LOADER,null,this);
        ListView mInboxListView=(ListView)getActivity().findViewById(R.id.inbox_listview);
        mInboxListView.setAdapter(mInboxCursorAdapter);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Log.d(TAG,"OnCreateLoader");
        switch(id)
        {
            case INBOX_LOADER:
                Log.d(TAG,"switch");
                return new CursorLoader(getActivity(),INBOX_TABLE_URI,INBOX_PROJECTION,null,null,null);

            default:
                return null;
        }
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        mInboxCursorAdapter.changeCursor(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mInboxCursorAdapter.changeCursor(null);
    }
}
