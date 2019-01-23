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
            sendSms();
        });

        ImageView emailImage = findViewById(R.id.email_button);
        emailImage.setOnClickListener(v -> {
            sendEmail();
        });

        ImageView telegram = findViewById(R.id.telegram_button);
        telegram.setOnClickListener(v -> {
            sendTelegram();
        });
    }

    private void sendSms() {
        Intent i = new Intent(android.content.Intent.ACTION_VIEW);
        i.setType("vnd.android-dir/mms-sms");
        i.putExtra("address","+380509658065");
        i.putExtra("sms_body","Hello Kirill!");
        startActivity(i);
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
        Intent i = new Intent(Intent.ACTION_SENDTO);
        i.setData(Uri.parse("mailto:kirillterentyev1999@gmail.com")); // only email apps should handle this
//        i.putExtra(Intent.EXTRA_EMAIL, "kirillterentyev1999@gmail.com");
        i.putExtra(Intent.EXTRA_SUBJECT, "Subject");
        i.putExtra(Intent.EXTRA_TEXT, "Hello Kirill!");

        if (validateIntent(i)) {
            startActivity(i);
        } else {
            Toast.makeText(this, "Невозможно выполнить действие!", Toast.LENGTH_SHORT).show();
        }
    }

    private void sendTelegram() {
        final String app = "org.telegram.messenger";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setPackage(app);
        i.setData(Uri.parse("http://telegram.me/terentyev_k"));
        if (validateIntent(i)) {
            startActivity(i);
        } else {
            Toast.makeText(this, "Невозможно выполнить действие!", Toast.LENGTH_SHORT).show();
        }
    }

    private Boolean validateIntent(Intent i) {
        PackageManager manager = this.getPackageManager();
        List<ResolveInfo> infos = manager.queryIntentActivities(i, 0);
        return infos.size() > 0;
    }
}
