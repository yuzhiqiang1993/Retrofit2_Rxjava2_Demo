package com.esp.yzq.retrofitdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.ALog;
import com.google.gson.Gson;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    /**
     * 传统方式
     */
    private Button mCallBtn;
    /**
     * rxJava方式
     */
    private Button mRxBtn;
    private TextView mCallResultTv;
    private TextView mRxResultTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.callBtn:
                getCallData();
                break;
            case R.id.rxBtn:
                getRxData();
                break;
        }
    }

    private void getCallData() {
        RetrofitFactory.getInstence()
                .getService()
                .getNewsCall()
                .enqueue(new Callback<NewsBean>() {
                    @Override
                    public void onResponse(Call<NewsBean> call, Response<NewsBean> response) {

                        mCallResultTv.setText(new Gson().toJson(response.body()));
                    }

                    @Override
                    public void onFailure(Call<NewsBean> call, Throwable t) {
                        Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void getRxData() {


        RetrofitFactory.getInstence()
                .getService()
                .getNews()
                .compose(this.<NewsBean>applyScheduler())
                .subscribe(new BaseObserver<NewsBean>() {
                    @Override
                    public void onNext(NewsBean newsBean) {
                        super.onNext(newsBean);
                        ALog.i(new Gson().toJson(newsBean));
                        mRxResultTv.setText(newsBean.getReason());
                    }
                });

    }


    private void initView() {
        mCallBtn = (Button) findViewById(R.id.callBtn);
        mCallBtn.setOnClickListener(this);
        mRxBtn = (Button) findViewById(R.id.rxBtn);
        mRxBtn.setOnClickListener(this);
        mCallResultTv = (TextView) findViewById(R.id.callResultTv);
        mRxResultTv = (TextView) findViewById(R.id.rxResultTv);
    }


    public <T> ObservableTransformer<T, T> applyScheduler() {

        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }
}
