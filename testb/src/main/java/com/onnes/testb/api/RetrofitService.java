package com.onnes.testb.api;

import com.onnes.testb.bean.Book;
import com.onnes.testb.bean.TPInitInfo;

import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2017/9/25.c
 */

public interface RetrofitService {
    @GET("/book/recommend")
    rx.Observable<Book> getRecomend(@Query("gender") String gender);

    @GET("/mch/initorderno.ashx")
    rx.Observable<TPInitInfo> getIPProvince(@Query("ip") String userIP);

    @GET("/test/succback.aspx")
    rx.Observable<String> getIPProvince2(@Query("ip") String userIP);
}
