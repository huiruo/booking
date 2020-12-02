package com.booking.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.booking.R;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RegisterActivity extends AppCompatActivity {
    Button btn_register;
    EditText account, password, confirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();
    }

    private void init() {
        btn_register = findViewById(R.id.register);
        account = findViewById(R.id.account);
        password = findViewById(R.id.password);
        confirmPassword = findViewById(R.id.confirmPassword);

        btn_register.setOnClickListener(new View.OnClickListener() {
            Properties properties = MyProperUtil.getProperties(getApplicationContext());
            String baseUrl = properties.getProperty("booking_url");

            @Override
            public void onClick(View v) {
                Log.i("baseUrl", baseUrl);
                String account_str = account.getText().toString();
                String password_str = password.getText().toString();
                String confirmPassword_str = confirmPassword.getText().toString();
                if (account_str == null || account_str.length() == 0) {
                    Log.w("注册", "请输入账户");
                    return;
                }
                if (password_str == null || password_str.length() == 0) {
                    Log.w("注册", "请输入密码");
                    return;
                }
                if (confirmPassword_str == null || confirmPassword_str.length() == 0) {
                    Log.w("注册", "请确认密码");
                    return;
                }
                if (!password_str.equals(confirmPassword_str)) {
                    Log.w("注册", "注册密码不一致");
                    return;
                }
                Log.d("data", account_str);
                Log.d("data", password_str);
                Log.d("data", confirmPassword_str);
                registerWithOkHttp(account_str, password_str);
            }

            private void registerWithOkHttp(String account_str, String password_str) {
                Map<String, String> params = new HashMap<String, String>();
                params.put("account", account_str);
                params.put("password", password_str);
                Gson gson = new Gson();
                //使用Gson将对象转换为json字符串
                String json = gson.toJson(params);
                System.out.println("json->" + json);
                MediaType mediaType = MediaType.parse("application/json;charset=UTF-8");
                RequestBody stringBody = RequestBody.Companion.create(json, mediaType);
                String register_url = baseUrl + "/user/register";
                OkHttpClient okHttpClient = new OkHttpClient();
                Request request = new Request.Builder()
                        .url(register_url)
                        .post(stringBody)
                        // .addHeader("Content-Type", "application/json")
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

