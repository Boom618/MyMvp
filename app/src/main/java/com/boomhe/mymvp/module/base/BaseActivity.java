package com.boomhe.mymvp.module.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.boomhe.mymvp.AndroidApplication;
import com.boomhe.mymvp.R;
import com.boomhe.mymvp.injector.components.ApplicationComponent;
import com.boomhe.mymvp.injector.modules.ActivityModule;
import com.boomhe.mymvp.utils.SwipeRefreshHelper;
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
            SwipeRefreshHelper.init(mSwipeRefresh, new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    updateViews(true);
                }
            });
        }
    }

    protected ApplicationComponent getAppComponent(){
        return AndroidApplication.getAppComponent();
    }

    /**
     * 获取 ActivityModule
     * @return
     */
    protected ActivityModule getActivityModule(){
        return new ActivityModule(this);
    }

    /**
     * 初始化 Toolbar
     * @param toolbar
     * @param homeAsUpEnabled
     * @param title
     */
    protected void initToolBar(Toolbar toolbar, boolean homeAsUpEnabled, String title){
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(homeAsUpEnabled);
    }

    protected void initToolBar(Toolbar toolbar, boolean homeAsUpEnabled, int resTitle){
        initToolBar(toolbar,homeAsUpEnabled,getString(resTitle));
    }


    /**
     * 添加 Fragment
     * @param containerViewId
     * @param fragment
     * @param tag
     */
    protected void addFragment(int containerViewId, Fragment fragment, String tag){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        // 设置tag，不然下面 findFragmentByTag(tag)找不到
        transaction.add(containerViewId,fragment,tag);
        transaction.addToBackStack(tag);
        transaction.commit();
    }

    /**
     * 替换 fragment
     * @param containerViewId
     * @param fragment
     * @param tag
     */
    protected void replaceFragment(int containerViewId, Fragment fragment, String tag){
        if (getSupportFragmentManager().findFragmentByTag(tag) == null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            // 设置 Tag
            transaction.replace(containerViewId,fragment,tag);
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            // 设置返回栈中的 Tag
            transaction.addToBackStack(tag);
            transaction.commit();
        }else{
            // 存在则弹出在它上面的所有fragment，并显示对应fragment
            getSupportFragmentManager().popBackStack(tag,0);
        }
    }

    /**
     * 更新视图控件
     * @param isRefresh
     */
    protected abstract void updateViews(boolean isRefresh);

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
        return this.<T>bindToLifecycle();
    }

    @Override
    public void onRetry() {
        updateViews(false);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
