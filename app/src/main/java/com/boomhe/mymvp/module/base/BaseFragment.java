package com.boomhe.mymvp.module.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.boomhe.mymvp.AndroidApplication;
import com.boomhe.mymvp.R;
import com.boomhe.mymvp.injector.components.ApplicationComponent;
import com.boomhe.mymvp.utils.SwipeRefreshHelper;
import com.boomhe.mymvp.widget.EmptyLayout;
import com.trello.rxlifecycle.components.support.RxFragment;
import com.trello.rxlifecycle2.LifecycleTransformer;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author boomhe on 2017/11/9.
 *         Fragment 基类
 */

public abstract class BaseFragment<T extends IBasePresenter> extends RxFragment implements IBaseView, EmptyLayout.OnRetryListener {

    @BindView(R.id.empty_layout)
    EmptyLayout mEmptyLayout;

    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout mSwipeRefresh;

    /**
     * Inject 注入类
     */
    @Inject
    protected T mPresenter;

    /**
     * 缓存 Fragment view
     */
    private View mRootView;
    protected Context mContext;
    private boolean mIsMulti = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(attachLayoutRes(), null);
            ButterKnife.bind(this, mRootView);
            initInjector();
            initViews();
            initSwipeRefresh();
        }
        ViewGroup parent = (ViewGroup) mRootView.getParent();
        if (parent != null) {
            parent.removeView(mRootView);
        }
        return mRootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getUserVisibleHint() && mRootView != null && !mIsMulti) {
            mIsMulti = true;
            updateViews(false);
        }
    }

    /**
     * 设置懒 Fragment 加载
     *
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (isVisibleToUser && isVisible() && mRootView != null && !mIsMulti) {
            mIsMulti = true;
            updateViews(false);
        } else {
            super.setUserVisibleHint(isVisibleToUser);
        }
    }

    /**
     * Layout
     *
     * @return XML 布局
     */
    protected abstract int attachLayoutRes();

    /**
     * 初始化 Dagger 注入
     */
    protected abstract void initInjector();

    /**
     * 初始化视图
     */
    protected abstract void initViews();

    /**
     * 初始化下拉刷新
     */
    protected abstract void initSwipeRefresh();

    /**
     * 更新视图控件
     *
     * @param isRefresh
     */
    protected abstract void updateViews(boolean isRefresh);


    @Override
    public void showLoading() {
        if (mEmptyLayout != null) {
            mEmptyLayout.setEmptyStatus(EmptyLayout.STATUS_LOADING);
            SwipeRefreshHelper.enableRefresh(mSwipeRefresh, false);
        }
    }

    @Override
    public void hideLoading() {
        if (mEmptyLayout != null) {
            mEmptyLayout.hideView();
            SwipeRefreshHelper.enableRefresh(mSwipeRefresh, true);
            SwipeRefreshHelper.controlRefresh(mSwipeRefresh, false);
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
        return null;
    }

    @Override
    public void onRetry() {
        updateViews(false);
    }

    protected ApplicationComponent getAppComponent() {
        return AndroidApplication.getAppComponent();
    }
}
