package com.example.android.bluetoothphone;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.Set;


// phone code adapted from http://www.mkyong.com/android/how-to-make-a-phone-call-in-android/

public class MainActivity extends Activity {

    private final String LOG_TAG = "Candlestick";

    // name we'll use for the bluetooth device eventually
    private final String CANDLESTICK_DEVICE = "Candlestick";

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TODO: class declaration test (don't need to import a file?)
        PhoneStatusFragment fragment = new PhoneStatusFragment();



        // bluetooth code starts here
        // ref http://developer.android.com/guide/topics/connectivity/bluetooth.html
        // first confirm that we actually have bluetooth on this device

        // turn on bluetooth if not on
        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (!mBluetoothAdapter.isEnabled())
        {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, 1);
        }

        if (mBluetoothAdapter != null && mBluetoothAdapter.isEnabled()) {
            // bluetooth is enabled
            Log.i(LOG_TAG, "bluetooth working, querying paired devices" + "\n");
            Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();

            // TODO: will hold the actual names of the devices
            //Array<String> pairedDeviceNames[] = pairedDevices.getName();


            // check to see that our target device has been found

            // If there are paired devices
            if (pairedDevices.size() > 0) {
                // Loop through paired devices
                for (BluetoothDevice device : pairedDevices) {
                    // Add the name and address to an array adapter to show in a ListView
                    //mFoundDevices.add(device.getName() + "\n" + device.getAddress());

                    // i'm going to print them to logcat actually
                    Log.i(LOG_TAG, device.getName() + "\n" + device.getAddress());



                    // just for testing, for now just allow the call if the VW phone is found
                    if (device.getName().equals(CANDLESTICK_DEVICE))
                    {
                        // add PhoneStateListener
                        PhoneCallListener phoneListener = new PhoneCallListener();
                        TelephonyManager telephonyManager = (TelephonyManager) this
                                .getSystemService(Context.TELEPHONY_SERVICE);
                        telephonyManager.listen(phoneListener, PhoneStateListener.LISTEN_CALL_STATE);

                        Button button = (Button) findViewById(R.id.buttonCall);
                        button.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent callIntent = new Intent(Intent.ACTION_CALL);
                                callIntent.setData(Uri.parse("tel:"));
                                startActivity(callIntent);
                            }
                        });

                    }
                }
            }
        }










    }


    private class PhoneCallListener extends PhoneStateListener
    {
        private boolean isPhoneCalling = false;
        String LOG_TAG = "LOGGING 123";
        @Override
        public void onCallStateChanged(int state, String incomingNumber)
        {
            if (TelephonyManager.CALL_STATE_RINGING == state)
            {
                // phone ringing
                Log.i(LOG_TAG, "RINGING, number: " + incomingNumber);
            }
            if (TelephonyManager.CALL_STATE_OFFHOOK == state)
            {
                // active
                Log.i(LOG_TAG, "OFFHOOK");
                isPhoneCalling = true;
            }
            if (TelephonyManager.CALL_STATE_IDLE == state)
            {
                // run when class initial and phone call ended,
                // need detect flag from CALL_STATE_OFFHOOK
                Log.i(LOG_TAG, "IDLE");
                if (isPhoneCalling)
                {
                    Log.i(LOG_TAG, "restart app");
                    // restart app
                    Intent i = getBaseContext()
                            .getPackageManager()
                            .getLaunchIntentForPackage(getBaseContext().getPackageName());
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);
                    isPhoneCalling = false;
                }
            }
        }
    }
}
