package com.example.naman.smshatke;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ComposeMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose_message);

        final EditText mAddressEditText,mMessageEditText;
        Button send;

        mAddressEditText=(EditText)findViewById(R.id.to_address);
        mMessageEditText=(EditText)findViewById(R.id.to_message);
        send=(Button)findViewById(R.id.to_send);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SmsManager smsManager=SmsManager.getDefault();
                smsManager.sendTextMessage(mAddressEditText.getText().toString(),null,mMessageEditText.getText().toString(),null,null);
            }
        });
    }
}
