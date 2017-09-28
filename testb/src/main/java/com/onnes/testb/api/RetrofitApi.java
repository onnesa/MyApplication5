package com.onnes.testb.api;

import com.onnes.testb.base.Constant;
import com.onnes.testb.bean.Book;
import com.onnes.testb.bean.TPInitInfo;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * Created by Administrator on 2017/9/25.
 */

public class RetrofitApi {
    public static RetrofitApi instance;

    private RetrofitService service;

    public RetrofitApi(OkHttpClient okHttpClient) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.API_BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()) // 添加Rx适配器
                .addConverterFactory(GsonConverterFactory.create()) // 添加Gson转换器
                .client(okHttpClient)
                .build();
        service = retrofit.create(RetrofitService.class);
    }

    public static RetrofitApi getInstance(OkHttpClient okHttpClient) {
        if (instance == null)
            instance = new RetrofitApi(okHttpClient);
        return instance;
    }

    public Observable<Book> getRecommend(String gender) {
        return service.getRecomend(gender);
    }

    public Observable<TPInitInfo> getIPProvince(String userIP) {
        return service.getIPProvince(userIP);
    }

    public Observable<String> getIPProvince2(String userIP) {
        return service.getIPProvince2(userIP);
    }
}
