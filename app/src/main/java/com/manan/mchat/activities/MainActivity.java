package com.manan.mchat.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.facebook.accountkit.AccessToken;
import com.facebook.accountkit.AccountKit;
import com.manan.mchat.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AccessToken accessToken = AccountKit.getCurrentAccessToken();

        if (accessToken != null) {
            startActivity(new Intent(this,ContactListActivity.class));
        } else {
            startActivity(new Intent(this,StartActivity.class));
        }
    }
}
