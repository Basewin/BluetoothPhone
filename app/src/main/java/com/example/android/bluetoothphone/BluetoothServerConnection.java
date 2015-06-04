package com.example.android.bluetoothphone;

/**
 * Created by triley on 6/1/2015.
 *
 * Creates a connection on the phone as a bluetooth server
 * (external device will be client)
 */
//public class BluetoothServerConnection extends Thread
//{
//    private final BluetoothServerSocket mmServerSocket;
//
//    public BluetoothServerConnection()
//    {
//        // Use a temporary object that is later assigned to mmServerSocket,
//        // because mmServerSocket is final
//        BluetoothServerSocket tmp = null;
//        try
//        {
//            // MY_UUID is the app's UUID string, also used by the client code
//            tmp = mBluetoothAdapter.listenUsingRfcommWithServiceRecord(NAME, MY_UUID);
//        }
//        catch (IOException e)
//        {
//        }
//        mmServerSocket = tmp;
//    }
//
//    public void run()
//    {
//        BluetoothSocket socket = null;
//        // Keep listening until exception occurs or a socket is returned
//        while (true) {
//            try {
//                socket = mmServerSocket.accept();
//            } catch (IOException e) {
//                break;
//            }
//            // If a connection was accepted
//            if (socket != null) {
//                // Do work to manage the connection (in a separate thread)
//                manageConnectedSocket(socket);
//                mmServerSocket.close();
//                break;
//            }
//        }
//    }
//
//    /** Will cancel the listening socket, and cause the thread to finish */
//    public void cancel() {
//        try {
//            mmServerSocket.close();
//        } catch (IOException e) { }
//    }
//}