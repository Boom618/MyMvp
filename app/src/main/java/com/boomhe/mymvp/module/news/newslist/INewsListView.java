package com.boomhe.mymvp.module.news.newslist;

import com.boomhe.mymvp.adapter.item.NewsMultiItem;
import com.boomhe.mymvp.api.bean.NewsInfo;
import com.boomhe.mymvp.module.base.ILoadDataView;

import java.util.List;

/**
 * @author boomhe on 2017/11/16.
 * 新闻列表视图接口
 */

public interface INewsListView extends ILoadDataView<List<NewsMultiItem>> {

    /**
     * 加载广告数据
     * @param newsBean
     */
    void loadAdData(NewsInfo newsBean);

}
