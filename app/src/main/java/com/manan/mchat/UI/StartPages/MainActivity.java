package com.manan.mchat.UI.StartPages;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.facebook.accountkit.AccessToken;
import com.facebook.accountkit.AccountKit;
import com.manan.mchat.R;
import com.manan.mchat.UI.ContactListPage.ContactListActivity;

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
