package com.boomhe.mymvp.injector.modules;

import com.boomhe.mymvp.adapter.ViewPagerAdapter;
import com.boomhe.mymvp.injector.PerFragment;
import com.boomhe.mymvp.module.base.IRxBusPresenter;
import com.boomhe.mymvp.module.news.main.NewsMainFragment;
import com.boomhe.mymvp.module.news.main.NewsMainPresenter;
import com.boomhe.mymvp.rxbus.RxBus;

import dagger.Provides;

/**
 * @author boomhe on 2017/11/15.
 * 新闻主页 Module
 */

public class NewsMainModule {

    private NewsMainFragment mView;

    public NewsMainModule(NewsMainFragment view){
        this.mView = view;
    }

    @PerFragment
    @Provides
    public IRxBusPresenter provideMainPresenter( RxBus rxBus){
        return new NewsMainPresenter(mView,rxBus);
    }

    @PerFragment
    @Provides
    public ViewPagerAdapter provideViewPagerAdapter(){
        return new ViewPagerAdapter(mView.getChildFragmentManager());
    }
}
