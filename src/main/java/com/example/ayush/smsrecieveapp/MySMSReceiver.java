package com.example.ayush.smsrecieveapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

public class MySMSReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // if the action received is equal to message received
        if(intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")){
            final Bundle bundle = intent.getExtras();

            try{
                if (bundle!=null){
                    final Object[] pdusObj = (Object[]) bundle.get("pdus");
                    String phoneNumber = null;
                    for(int i  = 0;i < pdusObj.length;i++){
                        SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[i]);
                        //getting sender's phone number
                        phoneNumber = currentMessage.getOriginatingAddress();

                        String senderNum = phoneNumber;
                        //getting Sender's text message
                        String message = currentMessage.getDisplayMessageBody();

                        Log.i("SmsReceiver", "senderNum: "+ senderNum + "; message: " + message);
                        //displaying toast
                        Toast.makeText(context, "senderNum: "+ senderNum + ", message: " + message, Toast.LENGTH_LONG).show();

                    }
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
