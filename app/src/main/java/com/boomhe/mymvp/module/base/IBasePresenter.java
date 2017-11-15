package com.boomhe.mymvp.module.base;

/**
 * @author boomhe on 2017/11/6.
 *         基础 Presenter
 */

public interface IBasePresenter {


    /**
     * 获取网络数据，更新界面
     *
     * @param isRefresh 用来判断是否为下拉刷新调用，下拉刷新的时候不应该再显示加载界面和异常界面
     */
    void getData(boolean isRefresh);

    /**
     * 获取更多数据
     */
    void getMoreData();
}
