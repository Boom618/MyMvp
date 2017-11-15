package com.boomhe.mymvp.module.news.main;

import com.boomhe.mymvp.module.base.IRxBusPresenter;
import com.boomhe.mymvp.rxbus.RxBus;

import rx.functions.Action1;

/**
 * @author boomhe on 2017/11/15.
 *         主页 Presenter
 */

public class NewsMainPresenter implements IRxBusPresenter {

    private final INewsMainView mView;
    //    private final NewsTypeInfoDao mDbDao;
    private final RxBus mRxBus;

    public NewsMainPresenter(INewsMainView view, RxBus rxBus) {
        mView = view;
        mRxBus = rxBus;
    }

    @Override
    public void getData(boolean isRefresh) {

    }

    @Override
    public void getMoreData() {

    }

    @Override
    public <T> void registerRxBus(Class<T> eventType, Action1<T> action1) {

    }

    @Override
    public void unregisterRxBus() {

    }
}
