package com.esp.yzq.retrofitdemo;

import com.blankj.ALog;

import java.util.Observable;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by yzq on 2018/1/9.
 *
 */

public abstract class BaseObserver<T> implements Observer<T> {


    @Override
    public void onSubscribe(Disposable d) {
        ALog.i("onSubscribe");
    }

    @Override
    public void onNext(T t) {
        ALog.i("onNext");

    }

    @Override
    public void onComplete() {
        ALog.i("onComplete");
    }

    @Override
    public void onError(Throwable e) {
        ALog.i("onError");
    }
}
