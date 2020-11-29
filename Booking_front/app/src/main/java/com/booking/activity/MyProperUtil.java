package com.booking.activity;

import android.content.Context;
import android.util.Log;

import java.io.InputStream;
import java.util.Properties;

class MyProperUtil {
    private static Properties urlProps;
    public static Properties getProperties(Context c){
        Properties props = new Properties();
        try {
            //方法一：通过activity中的context读取setting.properties的FileInputStream
            InputStream in = c.getAssets().open("config.properties");
            //方法二：通过class获取setting.properties的FileInputStream
            //InputStream in = PropertiesUtill.class.getResourceAsStream("/assets/ setting.properties "));
            props.load(in);
        } catch (Exception e1) {
            Log.d("ex-->","ex");
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        urlProps = props;
        //Log.d("util-->",urlProps.getProperty("serverUrl"));
        return urlProps;
    }

}
