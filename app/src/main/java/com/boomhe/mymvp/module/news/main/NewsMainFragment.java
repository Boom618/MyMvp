package com.boomhe.mymvp.module.news.main;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.widget.Toolbar;

import com.boomhe.mymvp.R;
import com.boomhe.mymvp.adapter.ViewPagerAdapter;
import com.boomhe.mymvp.local.NewsTypeInfo;
import com.boomhe.mymvp.module.base.BaseFragment;
import com.boomhe.mymvp.module.base.IRxBusPresenter;
import com.boomhe.mymvp.rxbus.event.ChannelEvent;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import rx.functions.Action1;

/**
 * @author boomhe on 2017/11/9.
 *         新闻主页
 */

public class NewsMainFragment extends BaseFragment<IRxBusPresenter> implements INewsMainView {


    @BindView(R.id.tool_bar)
    android.support.v7.widget.Toolbar mToolBar;
    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.view_page)
    ViewPager mViewPage;

    @Inject
    ViewPagerAdapter mPagerAdapter;

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_news_main;
    }

    @Override
    protected void initInjector() {
    }

    @Override
    protected void initViews() {
        initToolBar(mToolBar, true, "新闻");
        setHasOptionsMenu(true);
        mViewPage.setAdapter(mPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPage);
        mPresenter.registerRxBus(ChannelEvent.class, new Action1<ChannelEvent>() {
            @Override
            public void call(ChannelEvent channelEvent) {
                handleChannelEvent(channelEvent);
            }
        });

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

    /**
     * 处理频道事件
     *
     * @param channelEvent
     */
    private void handleChannelEvent(ChannelEvent channelEvent) {

    }
}
