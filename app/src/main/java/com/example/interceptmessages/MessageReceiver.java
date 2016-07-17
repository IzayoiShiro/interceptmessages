package com.example.interceptmessages;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsMessage;
import android.util.Log;

/**
 * Created by 杨天宇 on 2016/7/17.
 */
public class MessageReceiver extends BroadcastReceiver {
    private static final String TAG = "MessageReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Object[] objs = (Object[]) intent.getExtras().get("pdus");
        for (Object obj : objs) {
            SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) obj);
            String body = smsMessage.getMessageBody();
            String sender = smsMessage.getOriginatingAddress();
            Log.i(TAG, "body: " + body);
            Log.i(TAG, "sender: " + sender);
            if("10086".equals(sender)){
                Log.i(TAG, "onReceive: 垃圾短信，立刻终止");
                abortBroadcast();
            }
        }
    }
}
