package com.booking.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.booking.R;
import com.booking.net.OkHttpException;
import com.booking.net.RequestParams;
import com.booking.net.ResponseCallback;
import com.booking.reqApi.HttpRequest;
import com.booking.utils.UserInfo;
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
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
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
                    //Toast.makeText(this, "You clicked Add", Toast.LENGTH_SHORT).show();
                    Toast ts = Toast.makeText(getBaseContext(),"请输入账户",Toast.LENGTH_LONG);
                    ts.show();
                    return;
                }
                if (password_str == null || password_str.length() == 0) {
                    //Log.w("注册", "请输入密码");
                    Toast.makeText(RegisterActivity.this, "请输入密码", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (confirmPassword_str == null || confirmPassword_str.length() == 0) {
                    //Log.w("注册", "请确认密码");
                    Toast.makeText(getBaseContext(), "请确认密码", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!password_str.equals(confirmPassword_str)) {
//                    Log.w("注册", "注册密码不一致");
                    Toast.makeText(getBaseContext(), "注册密码不一致", Toast.LENGTH_SHORT).show();
                    return;
                }
                Log.d("data", account_str);
                Log.d("data", password_str);
                Log.d("data", confirmPassword_str);
                //registerWithOkHttp(account_str, password_str);
                register(account_str, password_str);
            }
            private void register(String account_str, String password_str) {
                RequestParams params = new RequestParams();
                params.put("account", account_str);
                params.put("password", password_str);
                String register_url = baseUrl + "/user/register";
                HttpRequest.postRegisterApi(register_url,params, new ResponseCallback() {
                    @Override
                    public void onSuccess(Object responseObj) {
                        UserInfo userInfo = (UserInfo) responseObj;
                        Toast.makeText(RegisterActivity.this, "请求成功"+userInfo.toString(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(OkHttpException failuer) {
                        Log.e("TAG", "请求失败=" + failuer.getEmsg());
                        Toast.makeText(RegisterActivity.this, "请求失败="+failuer.getEmsg(), Toast.LENGTH_SHORT).show();
                    }
                });

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

