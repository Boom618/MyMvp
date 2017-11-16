package com.boomhe.mymvp.module.base;

/**
 * @author boomhe on 2017/11/16.
 * 加载数据
 */

public interface ILoadDataView<T> extends IBaseView {

    /**
     * 加载数据
     * @param data
     */
    void loadData(T data);

    /**
     * 加载更多
     * @param data
     */
    void loadMoreData(T data);


    /**
     * 没有数据
     */
    void loadNoData();
}
