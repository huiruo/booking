package com.booking.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.booking.R;

public class StartActivity extends BaseActivity {
    Button goToRegister;
    Button goToLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_start);
        //是否监听网络
        setCheckNetworkStatusChangeListenerEnable(false);
        init();
    }

    private void init() {
        goToRegister = findViewById(R.id.registerBtn);
        goToLogin = findViewById(R.id.loginBtn);
        goToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this, RegisterActivity.class);
                startActivity(intent);
//                StartActivity.this.finish();
            }
        });
        goToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
