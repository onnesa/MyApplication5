package com.onnes.testb.ui;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.onnes.testb.R;
import com.onnes.testb.presenter.UserPresenter;
import com.onnes.testb.view.UserView;

public class MainActivity extends AppCompatActivity implements UserView {

    private Button button;
    private ImageView imageView;
    private ProgressDialog mProgressDialog;
    private UserPresenter userPresenter;
    private String PATH = "http://a.hiphotos.baidu.com/zhidao/pic/item/a50f4bfbfbedab640bc293fbf636afc379311e5c.jpg";

    private TextView txtMsg;
    private Button btnSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button);
        imageView = (ImageView) findViewById(R.id.imageView);
        mProgressDialog = new ProgressDialog(this);
        userPresenter = new UserPresenter(this);

        btnSearch = (Button) findViewById(R.id.btnSearch);
        txtMsg = (TextView) findViewById(R.id.txtMsg);

        mProgressDialog.setMessage("正在加载，请稍后..");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userPresenter.getUserImage(PATH);
            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userPresenter.getIPProvince2("223.223.200.68");
            }
        });
    }

    @Override
    public void showPic(byte[] data) {
        if (data != null) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
            imageView.setImageBitmap(bitmap);
        }
    }

    @Override
    public void showProgressDialog() {
        mProgressDialog.show();

    }

    @Override
    public void hideProgressDialog() {
        mProgressDialog.hide();
    }

    @Override
    public void showError(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProvinceName(String proviceName) {
        txtMsg.setText(proviceName);
    }
}
