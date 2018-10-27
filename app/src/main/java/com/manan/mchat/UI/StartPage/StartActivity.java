package com.manan.mchat.UI.StartPage;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.QuoteSpan;
import android.text.style.RelativeSizeSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.accountkit.AccessToken;
import com.facebook.accountkit.AccountKit;
import com.facebook.accountkit.AccountKitLoginResult;
import com.facebook.accountkit.ui.AccountKitActivity;
import com.facebook.accountkit.ui.AccountKitConfiguration;
import com.facebook.accountkit.ui.LoginType;
import com.manan.mchat.R;
import com.manan.mchat.UI.MainChatPage.MainChatActivity;


public class StartActivity extends AppCompatActivity {

    public static int APP_REQUEST_CODE = 99;
    public Button LoginButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        setContentView(R.layout.activity_start);

        LoginButton = findViewById(R.id.btn_login);
        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              phoneLogin();
            }
        });

        TextView text1 = findViewById(R.id.text1);
        TextView text2 = findViewById(R.id.text2);
        String s1 = "M Chat APP";
        String s2 = "Welcome to Mchat \n Now chat with your friends on the go!";
        SpannableStringBuilder ssb = new SpannableStringBuilder(s1);
        ssb.setSpan(new ForegroundColorSpan(Color.MAGENTA), 7 , s1.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ssb.setSpan(new RelativeSizeSpan(1.65f), 2, 6, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        text1.setText(ssb);
        SpannableStringBuilder ssb2 = new SpannableStringBuilder(s2);
        ssb2.setSpan(new ForegroundColorSpan(Color.GRAY), 16, s2.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE );
        ssb2.setSpan(new RelativeSizeSpan(1.35f), 1, 16, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        text2.setText(ssb2);

    }
    @Override
    protected void onActivityResult(
            final int requestCode,
            final int resultCode,
            final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == APP_REQUEST_CODE) { // confirm that this response matches your request
            AccountKitLoginResult loginResult = data.getParcelableExtra(AccountKitLoginResult.RESULT_KEY);
            String toastMessage;
            if (loginResult.getError() != null) {
                toastMessage = loginResult.getError().getErrorType().getMessage();
            } else if (loginResult.wasCancelled()) {
                toastMessage = "Login Cancelled";
            } else {
                if (loginResult.getAccessToken() != null) {
                    toastMessage = "Success:" + loginResult.getAccessToken().getAccountId();
                } else {
                    toastMessage = String.format(
                            "Success:%s...",
                            loginResult.getAuthorizationCode().substring(0,10));
                }

                // If you have an authorization code, retrieve it from
                // loginResult.getAuthorizationCode()
                // and pass it to your server and exchange it for an access token.
                // Success! Start your next activity...
                final Intent intent = new Intent(getApplicationContext(), MainChatActivity.class);
                startActivity(intent);
            }

            // Surface the result to your user in an appropriate way.
            Toast.makeText(
                    this,
                    toastMessage,
                    Toast.LENGTH_LONG)
                    .show();
        }
    }

    public void phoneLogin() {
        final Intent intent = new Intent(this,AccountKitActivity.class);
        AccountKitConfiguration.AccountKitConfigurationBuilder configurationBuilder =
                new AccountKitConfiguration.AccountKitConfigurationBuilder(
                        LoginType.PHONE,
                        AccountKitActivity.ResponseType.TOKEN); // or .ResponseType.TOKEN
        // ... perform additional configuration ...
        intent.putExtra(
                AccountKitActivity.ACCOUNT_KIT_ACTIVITY_CONFIGURATION,
                configurationBuilder.build());
        startActivityForResult(intent, APP_REQUEST_CODE);
    }

    @Override
    protected void onStart() {
        super.onStart();
        AccessToken accessToken = AccountKit.getCurrentAccessToken();

        if (accessToken != null) {
            startActivity(new Intent(this, MainChatActivity.class));
        }
    }
}
