package com.boomhe.mymvp.module.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;

import com.boomhe.mymvp.R;
import com.boomhe.mymvp.widget.EmptyLayout;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author boomhe on 2017/11/6.
 * 基类 Activity
 */

public abstract class BaseActivity< T extends IBasePresenter > extends RxAppCompatActivity implements IBaseView,EmptyLayout.OnRetryListener {


    /**
     * 把空视图放在基类统一处理，@Nullable 表明 View 可以为 null
     */
    @Nullable
    @BindView(R.id.empty_layout)
    protected EmptyLayout mEmptyLayout;

    /**
     * 把 Presenter 提取到基类需要配合基类的 initInjector() 进行注入，如果继承这个基类则必定要提供一个 Presenter 注入方法，
     * 该APP所有 Presenter 都是在 Module 提供注入实现，也可以选择提供另外不带 Presenter 的基类
     */
    @Inject
    protected T mPresenter;

    /**
     * 刷新控件
     */
    @Nullable
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout mSwipeRefresh;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(attachLayoutRes());
        ButterKnife.bind(this);

        initInjector();
        initViews();
        initSwipeRefresh();
        updateViews(false);
    }

    /**
     * 绑定布局文件
     * @return
     */
    @LayoutRes
    protected abstract int attachLayoutRes();

    /**
     * Dagger 注入
     */
    protected abstract void initInjector();

    /**
     * 初始化视图
     */
    protected abstract void initViews();

    /**
     * 初始化下拉刷新
     */
    private void initSwipeRefresh(){
        if (mSwipeRefresh != null) {

        }
    }

    /**
     * 更新视图控件
     * @param b
     */
    protected abstract void updateViews(boolean b);

    @Override
    public void showLoading() {
        if (mEmptyLayout != null) {
            mEmptyLayout.setEmptyStatus(EmptyLayout.STATUS_LOADING);
        }
    }

    @Override
    public void hideLoading() {
        if (mEmptyLayout != null) {
            mEmptyLayout.hideView();
        }
    }

    @Override
    public void showNetError() {
        if (mEmptyLayout != null) {
            mEmptyLayout.setEmptyStatus(EmptyLayout.STATUS_NO_NET);
            mEmptyLayout.setRetryListener(this);
        }
    }

    @Override
    public void finishRefresh() {
        if (mSwipeRefresh != null) {
            mSwipeRefresh.setRefreshing(false);
        }

    }

    @Override
    public <T> LifecycleTransformer<T> bindToLife() {
        return this.bindToLifecycle();
    }

    @Override
    public void onRetry() {
        updateViews(false);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
