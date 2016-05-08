package com.example.naman.smshatke;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.telephony.SmsMessage;
import android.util.Log;

/**
 * Created by naman on 5/8/2016.
 */
public class SmsReceiver extends BroadcastReceiver {
    private String TAG="SmsReceiver";
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle=intent.getExtras();

        SmsMessage msgs[]=null;
        String title="",body="";
        int mId=0;

        if(bundle!=null){
            Object []pdus=(Object[])bundle.get("pdus");
            msgs=new SmsMessage[pdus.length];
            for(int i=0;i<msgs.length;i++)
            {
                msgs[i]=SmsMessage.createFromPdu((byte[]) pdus[i]);
                title="SMS from:" + msgs[i].getOriginatingAddress();
                body += msgs[i].getMessageBody().toString();
            }
            mId++;
            NotificationCompat.Builder mBuilder =
                    new NotificationCompat.Builder(context)
                            .setSmallIcon(R.drawable.ic_friends)
                            .setContentTitle(title)
                            .setContentText(body);
            NotificationManager mNotificationManager =
                    (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
// mId allows you to update the notification later on.
            mNotificationManager.notify(mId, mBuilder.build());

            Log.d(TAG,body);
        }
    }
}
