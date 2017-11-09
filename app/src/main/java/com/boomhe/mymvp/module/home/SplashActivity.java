package com.boomhe.mymvp.module.home;

import android.content.Intent;

import com.boomhe.mymvp.R;
import com.boomhe.mymvp.module.base.BaseActivity;
import com.boomhe.mymvp.utils.RxHelper;

import rx.Subscriber;


/**
 * @author boomhe on 2017/11/8.
 *         启动页
 */

public class SplashActivity extends BaseActivity {

    private boolean mIsSkip = false;

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void updateViews(boolean isRefresh) {

        RxHelper.countdown(2)
                //.compose(this.<Integer>bindToLife())
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {
                        toSkip();
                    }

                    @Override
                    public void onError(Throwable e) {
                        toSkip();
                    }

                    @Override
                    public void onNext(Integer integer) {

                    }
                });
    }

    /**
     * 跳过
     */
    private void toSkip() {
        if (!mIsSkip) {
            mIsSkip = true;
            finish();
            startActivity(new Intent(this, MainActivity.class));
            overridePendingTransition(R.anim.hold, R.anim.zoom_in_exit);
        }
    }

    @Override
    public void onBackPressed() {
        // 不响应后退键
        return;
    }
}
