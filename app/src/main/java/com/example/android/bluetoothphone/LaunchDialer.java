package com.example.android.bluetoothphone;

import android.app.Activity;

/**
 * Created by triley on 6/3/2015.
 * Will be used eventually to dial a number passed to the app via bluetooth
 * See http://stackoverflow.com/questions/11699819/how-do-i-get-the-dialer-to-open-with-phone-number-displayed
 */
public class LaunchDialer extends Activity
{
    private String phoneNumber;


    public void launchDialer(String number)
    {
        this.phoneNumber = number;
    }

    public void dialNumber(String phoneNumber)
    {
        // to call immediately use Intent.ACTION_CALL instead
        // and add <uses-permission android:name="android.permission.CALL_PHONE" /> to
        // android manifest xml

        //TODO: find out what must be imported to get this working
        //Intent intent = new Intent(Intent.ACTION_DIAL);
        //intent.setData(Uri.parse("tel:0123456789"));
        //startActivity(intent);
    }
}
