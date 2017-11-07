package com.boomhe.mymvp.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.boomhe.mymvp.R;
import com.github.ybq.android.spinkit.SpinKitView;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author boomhe on 2017/11/6.
 * 空视图
 */

public class EmptyLayout extends FrameLayout {

    public static final int STATUS_HIDE = 1001;
    public static final int STATUS_LOADING = 1;
    public static final int STATUS_NO_NET = 2;
    public static final int STATUS_NO_DATA = 3;

    private Context mContext;
    private int mBgColor;
    private int mEmptyStatus = STATUS_LOADING;
    private OnRetryListener mOnRetryListener;

    @BindView(R.id.tv_net_error)
    TextView mTvEmptyMessage;
    @BindView(R.id.rl_empty_container)
    View mFLEmptyContainer;
    @BindView(R.id.empty_loading)
    SpinKitView mEmptyLoading;
    @BindView(R.id.empty_layout)
    FrameLayout mEmptyLayout;


    public EmptyLayout(@NonNull Context context) {
        this(context, null);
    }

    public EmptyLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        TypedArray array = mContext.obtainStyledAttributes(attrs, R.styleable.EmptyLayout);
        mBgColor = array.getColor(R.styleable.EmptyLayout_background_color, Color.WHITE);
        View.inflate(mContext, R.layout.layout_empty_loading, this);
        ButterKnife.bind(this);
        mEmptyLayout.setBackgroundColor(mBgColor);
        array.recycle();

        switchView();
    }

    /**
     * 切换视图
     */
    private void switchView() {
        switch (mEmptyStatus) {
            case STATUS_LOADING:
                setVisibility(VISIBLE);
                mFLEmptyContainer.setVisibility(GONE);
                mEmptyLayout.setVisibility(VISIBLE);
                break;
            case STATUS_NO_NET:
            case STATUS_NO_DATA:
                setVisibility(VISIBLE);
                mFLEmptyContainer.setVisibility(VISIBLE);
                mEmptyLayout.setVisibility(GONE);
                break;
            case STATUS_HIDE:
                setVisibility(GONE);
                break;
            default:
                break;
        }
    }

    /**
     * 隐藏视图
     */
    public void hideView(){
        mEmptyStatus = STATUS_HIDE;
        switchView();
    }

    /**
     * 设置状态
     * @param emptyStatus
     */
    public void setEmptyStatus(@EmptyStatus int emptyStatus){
        mEmptyStatus = emptyStatus;
        switchView();
    }

    /**
     * 获取状态
     * @return
     */
    public int getEmptyStatus(){
        return mEmptyStatus;
    }

    /**
     * 点击重试
     */
    public interface OnRetryListener {
        /**
         * 重试
         */
        void onRetry();
    }

    public void setRetryListener(OnRetryListener retryListener){
        this.mOnRetryListener = retryListener;
    }

    @OnClick(R.id.tv_net_error)
    public void onClick(){
        if (mOnRetryListener != null) {
            mOnRetryListener.onRetry();
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({STATUS_LOADING,STATUS_NO_NET,STATUS_NO_DATA})
    public @interface EmptyStatus{}
}
