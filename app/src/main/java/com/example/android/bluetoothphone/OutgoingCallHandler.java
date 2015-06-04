package com.example.android.bluetoothphone;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by triley on 6/1/2015.
 * ref http://android-developers.blogspot.com/2013/05/handling-phone-call-requests-right-way.html
 */

public class OutgoingCallHandler extends BroadcastReceiver
{
    @Override
    public void onReceive(Context context, Intent intent) {
        // Extract phone number reformatted by previous receivers
        String phoneNumber = getResultData();
        if (phoneNumber == null) {
            // No reformatted number, use the original
            phoneNumber = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);
        }
        // My app will bring up the call, so cancel the broadcast
        setResultData(null);
        // Start my app to bring up the call
    }
}
