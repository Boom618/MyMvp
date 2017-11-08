package com.boomhe.mymvp.utils;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * @author boomhe on 2017/11/8.
 *         <p>
 *         RxJava 帮助类
 */

public class RxHelper {

    private RxHelper() {
        throw new AssertionError();
    }

    public static Observable<Integer> countdown(int time) {
        if (time < 0) {
            time = 0;
        }

        final int countTime = time;

        Observable<Integer> observable = Observable.interval(0, 1, TimeUnit.SECONDS)
                .map(new Func1<Long, Integer>() {
                    @Override
                    public Integer call(Long increaseTime) {
                        return countTime - increaseTime.intValue();
                    }
                })
                .take(countTime + 1)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(AndroidSchedulers.mainThread());

        return observable;

    }
}
