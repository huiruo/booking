package com.booking.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

import com.booking.R;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
    }
}
