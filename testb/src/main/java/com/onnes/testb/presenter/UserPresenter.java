package com.onnes.testb.presenter;

import android.util.Log;

import com.onnes.testb.bean.TPInitInfo;
import com.onnes.testb.model.UserModel;
import com.onnes.testb.view.UserView;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/9/22.
 */

public class UserPresenter {
    private UserView userView;
    private UserModel userModel;

    public UserPresenter(UserView userView) {
        this.userView = userView;
        userModel = new UserModel();

    }

    public void getUserImage(final String path) {
        userView.showProgressDialog();
        userModel.getUserImage(path).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<byte[]>() {
            @Override
            public void onCompleted() {
                userView.hideProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                userView.showError(e.getMessage());
                userView.hideProgressDialog();
            }

            @Override
            public void onNext(byte[] bytes) {
                userView.showPic(bytes);
            }
        });
    }

    public void getIPProvince(final String userIP) {
        userView.showProgressDialog();
        userModel.provideRetrofitService(userModel.provideOkHttpClient())
                .getIPProvince(userIP)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<TPInitInfo>() {
                    @Override
                    public void onCompleted() {

                        userView.hideProgressDialog();
                    }

                    @Override
                    public void onError(Throwable e) {
                        userView.showError(e.getMessage());
                        userView.hideProgressDialog();
                        Log.i("tt123456", e.getMessage());
                    }

                    @Override
                    public void onNext(TPInitInfo TPInitInfo1) {
                        userView.showProvinceName(TPInitInfo1.getRetMsg());
                    }
                });
        ;
    }

    public void getIPProvince2(final String userIP) {
        userView.showProgressDialog();
        userModel.provideRetrofitService(userModel.provideOkHttpClient())
                .getIPProvince2(userIP)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                        userView.hideProgressDialog();
                    }

                    @Override
                    public void onError(Throwable e) {
                        userView.showError(e.getMessage());
                        userView.hideProgressDialog();
                        Log.i("tt123456", e.getMessage());
                    }

                    @Override
                    public void onNext(String TPInitInfo1) {
                        userView.showProvinceName(TPInitInfo1);
                    }
                });
        ;
    }
}
