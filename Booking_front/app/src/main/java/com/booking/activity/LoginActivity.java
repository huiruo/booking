package com.booking.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.booking.R;
import com.booking.net.OkHttpException;
import com.booking.net.RequestParams;
import com.booking.net.ResponseCallback;
import com.booking.reqApi.HttpRequest;
import com.booking.utils.User;

import java.util.Properties;

public class LoginActivity extends BaseActivity {
    Button btn_register;
    EditText account, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);
        init();
    }

    private void init() {
        btn_register = findViewById(R.id.register);
        account = findViewById(R.id.account);
        password = findViewById(R.id.password);

        btn_register.setOnClickListener(new View.OnClickListener() {
            Properties properties = MyProperUtil.getProperties(getApplicationContext());
            String baseUrl = properties.getProperty("booking_url");

            @Override
            public void onClick(View v) {
                String account_str = account.getText().toString();
                String password_str = password.getText().toString();
                if (account_str == null || account_str.length() == 0) {
                    Toast ts = Toast.makeText(getBaseContext(), "请输入账户", Toast.LENGTH_LONG);
                    ts.show();
                    return;
                }
                if (password_str == null || password_str.length() == 0) {
                    Toast.makeText(LoginActivity.this, "请输入密码", Toast.LENGTH_SHORT).show();
                    return;
                }
                loginAction(account_str, password_str);
            }

            private void loginAction(String account_str, String password_str) {
                RequestParams params = new RequestParams();
                params.put("account", account_str);
                params.put("password", password_str);
                /*
                *测试：admin 123456
                {
                    "account":"admin",
                    "password":"123456"
                }
                * */
                String login_url = baseUrl + "/user/login";
                HttpRequest.postLoginApi(login_url, params, new ResponseCallback() {
                    @Override
                    public void onSuccess(Object responseObj) {
                        User user = (User) responseObj;
                        Toast.makeText(LoginActivity.this, "登录成功" + user.toString(), Toast.LENGTH_SHORT).show();
                        Log.d("user:", user.toString());
                        ///*
                        /*
                        new Handler(new Handler.Callback() {
                            @Override
                            public boolean handleMessage(@NonNull Message msg) {
                                Log.d("启动任务","--->");
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);
                                LoginActivity.this.finish();
                                return false;
                            }
                        }).sendEmptyMessageDelayed(0,1000);*/
                        //
                    }

                    @Override
                    public void onFailure(OkHttpException failuer) {
                        Log.e("TAG", "登录失败:" + failuer.getMsg());
                        Toast.makeText(LoginActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }
}