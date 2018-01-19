package com.esp.yzq.retrofitdemo;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by yzq on 2018/1/9.
 * <p>
 * retrofit 工厂类
 */

public class RetrofitFactory {


    private static RetrofitFactory retrofitFactory;
    private static ApiService service;
    private final Retrofit retrofit;

    /*构造方法  用来初始化Retrofit*/
    public RetrofitFactory() {

        retrofit = new Retrofit.Builder()
                .baseUrl(Urls.BASE_URL)
                .client(initClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        service = retrofit.create(ApiService.class);

    }


    /*OKhttp的配置*/
    private OkHttpClient initClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(initLoggingInterceptor())
                .readTimeout(5000, TimeUnit.SECONDS)
                .connectTimeout(5000, TimeUnit.SECONDS)
                .build();

    }

    /*日志拦截器*/
    private Interceptor initLoggingInterceptor() {
        return new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {

                Log.w("okhttp", message);
            }
        }).setLevel(HttpLoggingInterceptor.Level.BODY);

    }

    public static RetrofitFactory getInstence() {

        if (retrofitFactory == null) {

            synchronized (RetrofitFactory.class) {
                if (retrofitFactory == null) {
                    retrofitFactory = new RetrofitFactory();
                }
            }
        }


        return retrofitFactory;
    }


    public ApiService getService() {
        return service;
    }

}
