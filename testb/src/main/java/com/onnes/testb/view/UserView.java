package com.onnes.testb.view;

/**
 * Created by Administrator on 2017/9/22.
 */

public interface UserView {
    void showPic(byte[] data);

    void showProgressDialog();

    void hideProgressDialog();

    void showError(String msg);

    void showProvinceName(String proviceName);
}
