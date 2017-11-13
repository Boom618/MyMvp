package com.boomhe.mymvp.module.news.main;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.boomhe.mymvp.local.NewsTypeInfo;
import com.boomhe.mymvp.module.base.BaseFragment;
import com.boomhe.mymvp.module.base.IRxBusPresenter;

import java.util.List;

/**
 * @author boomhe on 2017/11/9.
 * 新闻主页
 */

public class NewsMainFragment extends BaseFragment<IRxBusPresenter> implements INewsMainView {


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int attachLayoutRes() {
        return 0;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initSwipeRefresh() {

    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }

    @Override
    public void loadData(List<NewsTypeInfo> checkList) {

    }
}
