package com.booking.activity;

import android.content.Intent;
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
import com.booking.utils.User;
import java.util.Properties;


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

//    @Override
//    public void onDestroy(){
//        super.onDestroy();
//    }

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
                /*test account
                {
                "account":"12345@qq.com",
                "password":12345,
                "email":"2196411859@qq.com"
                }
                * */
                Log.i("baseUrl", baseUrl);
                String account_str = account.getText().toString();
                String password_str = password.getText().toString();
                String confirmPassword_str = confirmPassword.getText().toString();
                if (account_str == null || account_str.length() == 0) {
                    //Toast.makeText(this, "You clicked Add", Toast.LENGTH_SHORT).show();
                    Toast ts = Toast.makeText(getBaseContext(), "请输入账户", Toast.LENGTH_LONG);
                    ts.show();
                    return;
                }
                if (password_str == null || password_str.length() == 0) {
                    Toast.makeText(RegisterActivity.this, "请输入密码", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (confirmPassword_str == null || confirmPassword_str.length() == 0) {
                    Toast.makeText(getBaseContext(), "请确认密码", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!password_str.equals(confirmPassword_str)) {
                    Toast.makeText(getBaseContext(), "注册密码不一致", Toast.LENGTH_SHORT).show();
                    return;
                }
                register(account_str, password_str);
            }

            private void register(String account_str, String password_str) {
                RequestParams params = new RequestParams();
                params.put("account", account_str);
                params.put("password", password_str);
                String register_url = baseUrl + "/user/register";
                HttpRequest.postRegisterApi(register_url, params, new ResponseCallback() {
                    @Override
                    public void onSuccess(Object responseObj) {
                        //UserInfo userInfo = (UserInfo) responseObj;
                        //System.out.println(userInfo);
                        //Log.d("请求返回",userInfo.toString());
                        User user = (User) responseObj;
                        System.out.println(user);
                        Log.d("请求返回", user.toString());
                        Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(OkHttpException failuer) {
                        //Log.e("TAG", "注册失败：" + failuer);
                        if(failuer.getCode()==4){
                            Toast.makeText(RegisterActivity.this, "账号名已经存在，请更换注册账号", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(RegisterActivity.this, "注册失败:" + failuer.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
    }
}