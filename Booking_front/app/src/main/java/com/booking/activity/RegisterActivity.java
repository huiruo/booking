package com.booking.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.booking.R;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Properties;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RegisterActivity extends AppCompatActivity {
    Button btn_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();
    }

    private void init() {
        btn_register = findViewById(R.id.register);
        btn_register.setOnClickListener(new View.OnClickListener() {
            Properties properties = MyProperUtil.getProperties(getApplicationContext());
            String baseUrl = properties.getProperty("booking_url");

            @Override
            public void onClick(View v) {
                Log.i("baseUrl", baseUrl);
            }

            private void registerWithOkHttp(String userName, String password) {
                String register_url = baseUrl + "/user/register";
                OkHttpClient okHttpClient = new OkHttpClient();
                Request request = new Request.Builder()
                        .get()
                        .url(register_url)
                        .build();
                Call call = okHttpClient.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                        try {
                            if (response.code() == 200) {
                                final String result = response.body().string();
                                System.out.println("获取成功-->" + result);
                                RegisterActivity.this.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
//                                    Button bt =findViewById(R.id.btnLogin);
//                                    bt.setText("改变的文字");
//                                    Intent intent=new Intent(MainActivity.this, RegisterActivity.class);
//                                    startActivity(intent);
                                    }
                                });
                            }
                        } catch (Exception e) {
                            System.out.println("请求报错" + e.getMessage());
                        }
                    }

                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {
                        System.out.println("未知错误" + e.getMessage());
                    }
                });
            }
        });
    }
}

