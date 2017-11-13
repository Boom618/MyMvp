package com.boomhe.mymvp.rxbus;

import java.util.HashMap;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subscriptions.CompositeSubscription;

/**
 * @author boomhe on 2017/11/8.
 */

public class RxBus {

    private SerializedSubject<Object, Object> mSubject;
    private HashMap<String, CompositeSubscription> mSubscriptionMap;

    public RxBus() {
        mSubject = new SerializedSubject<>(PublishSubject.create());
    }

    /**
     * 发送事件
     *
     * @param object
     */
    public void post(Object object) {
        mSubject.onNext(object);
    }

    /**
     * 返回制定类型的 Observable
     *
     * @param type
     * @param <T>
     * @return
     */
    public <T> Observable<T> toObservable(Class<T> type) {
        return mSubject.ofType(type);
    }

    /**
     * 是否有观察者订阅
     *
     * @return
     */
    public Boolean hasObservers() {
        return mSubject.hasObservers();
    }

    /**
     * 默认订阅方法
     *
     * @param type
     * @param next
     * @param error
     * @param <T>
     * @return
     */
    public <T> Subscription doSubscribe(Class<T> type, Action1<T> next, Action1<Throwable> error) {
        Subscription subscribe = toObservable(type)
                .onBackpressureBuffer()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(next, error);
        return subscribe;
    }

    /**
     * 保存订阅后的 subscription
     *
     * @param object
     * @param subscription
     */
    public void addSubscription(Object object, Subscription subscription) {
        if (mSubscriptionMap == null) {
            mSubscriptionMap = new HashMap<>();
        }

        String key = object.getClass().getName();
        if (mSubscriptionMap.get(key) != null) {
            mSubscriptionMap.get(key).add(subscription);
        } else {
            CompositeSubscription CS = new CompositeSubscription();
            CS.add(subscription);
            mSubscriptionMap.put(key, CS);
        }
    }

    /**
     * 取消订阅
     * @param object
     */
    public void unSubscribe(Object object){
        if (mSubscriptionMap == null) {
            return;
        }
        String key = object.getClass().getName();
        if (!mSubscriptionMap.containsKey(key)) {
            return;
        }

        if (mSubscriptionMap.get(key) != null) {
            mSubscriptionMap.get(key).unsubscribe();
        }
        mSubscriptionMap.remove(key);
    }


}
