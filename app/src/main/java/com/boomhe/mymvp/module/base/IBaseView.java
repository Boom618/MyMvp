package com.boomhe.mymvp.module.base;

import com.trello.rxlifecycle2.LifecycleTransformer;

/**
 * @author boomhe on 2017/11/6.
 */

public interface IBaseView {

    /**
     * 显示加载动画
     */
    void showLoading();

    /**
     * 隐藏加载动画
     */
    void hideLoading();

    /**
     * 显示网络错误，对网络 error 在 BaseActivity 和 BaseFragment 中处理
     */
    void showNetError();

    /**
     * 完成刷新
     */
    void finishRefresh();

    /**
     * 绑定生命周期
     * @param <T>
     * @return
     */
    <T> LifecycleTransformer<T> bindToLife();


}
