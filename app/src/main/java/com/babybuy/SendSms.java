package com.babybuy;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;


public class SendSms extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Intent smsIntent = new Intent(Intent.ACTION_VIEW);
        smsIntent.setData(Uri.parse("smsto:"));
        smsIntent.putExtra("address"  , new String("0123456789;3393993300"));
        smsIntent.putExtra("sms_body"  , "Test SMS to Angilla");
        startActivity(smsIntent);
    }
}
