package com.boomhe.mymvp.module.news.newslist;

import android.support.v7.widget.RecyclerView;

import com.boomhe.mymvp.R;
import com.boomhe.mymvp.adapter.item.NewsMultiItem;
import com.boomhe.mymvp.api.bean.NewsInfo;
import com.boomhe.mymvp.module.base.BaseFragment;
import com.boomhe.mymvp.module.base.IBasePresenter;
import com.boomhe.mymvp.module.base.IBaseView;
import com.boomhe.mymvp.module.news.main.INewsMainView;
import com.boomhe.recycler.adapter.BaseQuickAdapter;
import com.daimajia.slider.library.SliderLayout;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * @author boomhe on 2017/11/16.
 * 新闻列表
 */

public class NewsListFragment extends BaseFragment<IBasePresenter> implements INewsListView {


    public static final String NEWS_TYPE_KEY = "NewsTypeKey";


    @BindView(R.id.rv_news_list)
    RecyclerView mRvNewsList;

    @Inject
    BaseQuickAdapter mAdapter;
    /**
     * 图片轮播
     */
    private SliderLayout mAdSlider;

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_news_list;
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
    public void loadData(List<NewsMultiItem> data) {

    }

    @Override
    public void loadMoreData(List<NewsMultiItem> data) {

    }

    @Override
    public void loadNoData() {

    }

    @Override
    public void loadAdData(NewsInfo newsBean) {

    }
}
