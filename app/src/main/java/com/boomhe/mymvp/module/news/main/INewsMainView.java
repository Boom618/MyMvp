package com.boomhe.mymvp.module.news.main;

import com.boomhe.mymvp.local.NewsTypeInfo;

import java.util.List;

/**
 * @author boomhe on 2017/11/13.
 * 主页接口
 */

public interface INewsMainView {

    /**
     * 数据显示
     * @param checkList 选中栏目
     */
    void loadData(List<NewsTypeInfo> checkList);
}
