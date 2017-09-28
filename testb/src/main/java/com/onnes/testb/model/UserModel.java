package com.onnes.testb.model;

import com.onnes.testb.api.RetrofitApi;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import dagger.Provides;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by Administrator on 2017/9/22.
 */

public class UserModel {
    private OkHttpClient client;

    public UserModel() {

        //client = new OkHttpClient();
        client = provideOkHttpClient();
    }

    @Provides
    public OkHttpClient provideOkHttpClient() {

        //LoggingInterceptor logging = new LoggingInterceptor(new Logger());
        // logging.setLevel(LoggingInterceptor.Level.BODY);

        OkHttpClient.Builder builder = new OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(20 * 1000, TimeUnit.MILLISECONDS)
                .readTimeout(20 * 1000, TimeUnit.MILLISECONDS)
                .retryOnConnectionFailure(true); // 失败重发
        //.addInterceptor(new HeaderInterceptor())
        // .addInterceptor(logging);
        return builder.build();
    }

    @Provides
    public RetrofitApi provideRetrofitService(OkHttpClient okHttpClient) {
        return RetrofitApi.getInstance(okHttpClient);
    }

    public Observable<byte[]> getUserImage(final String path) {
        return Observable.create(new Observable.OnSubscribe<byte[]>() {
            @Override
            public void call(final Subscriber<? super byte[]> subscriber) {
                Request request = new Request.Builder().url(path).build();
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        subscriber.onError(e);
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        if (response.isSuccessful()) {
                            byte[] data = response.body().bytes();
                            if (data != null) {
                                subscriber.onNext(data);
                            }
                        }
                        subscriber.onCompleted();
                    }
                });
            }
        });
    }
}
