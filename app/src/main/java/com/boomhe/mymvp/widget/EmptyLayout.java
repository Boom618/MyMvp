package com.boomhe.mymvp.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * @author boomhe on 2017/11/6.
 */

public class EmptyLayout extends FrameLayout {

    public static final int STATUS_HIDE = 1001;
    public static final int STATUS_LOADING = 1;
    public static final int STATUS_NO_NET = 2;
    public static final int STATUS_NO_DATA = 3;

    private Context mContext;
    private OnRetryListener mOnRetryListener;


    public EmptyLayout(@NonNull Context context) {
        this(context,null);
    }

    public EmptyLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
    }

    /**
     * 点击重试
     */
    public interface OnRetryListener{
        void onRetry();
    }
}
