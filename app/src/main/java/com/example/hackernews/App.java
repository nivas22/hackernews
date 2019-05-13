package com.example.hackernews;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.v7.app.AppCompatDelegate;

import com.example.hackernews.utils.AuthInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class App extends Application {

    private static App sApp;
    private OkHttpClient okclient;

    public static App get() {
        return sApp;
    }

    public OkHttpClient getOk() {
        return okclient;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        //App Font Settings
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Avenir-Medium.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

        sApp = this;

        okclient = new OkHttpClient.Builder()
                .addInterceptor(new AuthInterceptor())
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build();
    }
}
