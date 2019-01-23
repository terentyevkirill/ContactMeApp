package com.example.contactmeapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.media.Image;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView callImage = findViewById(R.id.call_button);
        callImage.setOnClickListener(v -> {
            makeCall();
        });

        ImageView smsImage = findViewById(R.id.sms_button);
        smsImage.setOnClickListener(v -> {
//            sendSms();
        });

        ImageView emailImage = findViewById(R.id.email_button);
        emailImage.setOnClickListener(v -> {
            sendEmail();
        });
    }

    private void makeCall() {
        String phoneNumber = "+380509658065";
        Intent i = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber));
        if (validateIntent(i)) {
            startActivity(i);
        } else {
            Toast.makeText(this, "Невозможно выполнить действие!", Toast.LENGTH_SHORT).show();
        }
    }

    private void sendEmail() {
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("text/hmtl");
        i.putExtra(Intent.EXTRA_EMAIL, "kirillterentyev1999@gmail.com");
        i.putExtra(Intent.EXTRA_SUBJECT, "Subject");
        i.putExtra(Intent.EXTRA_TEXT, "Hello, Kirill!");
        if (validateIntent(i)) {
            startActivity(i);
        } else {
            Toast.makeText(this, "Невозможно выполнить действие!", Toast.LENGTH_SHORT).show();
        }
    }

    private void telegram() {
//        Intent i = new Intent(Intent.)
    }

    private Boolean validateIntent(Intent i) {
        PackageManager manager = this.getPackageManager();
        List<ResolveInfo> infos = manager.queryIntentActivities(i, 0);
        return infos.size() > 0;
    }
}
