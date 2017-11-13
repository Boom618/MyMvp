package com.boomhe.mymvp.module.base;

import rx.functions.Action1;

/**
 * @author boomhe on 2017/11/13.
 * RxBus Presenter
 */

public interface IRxBusPresenter extends IBasePresenter{

    /**
     * 注册
     * @param eventType
     * @param action1
     * @param <T>
     */
    <T> void registerRxBus(Class<T> eventType, Action1<T> action1);

    /**
     * 注销
     */
    void unregisterRxBus();
}
