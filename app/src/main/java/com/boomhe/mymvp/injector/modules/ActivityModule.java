package com.boomhe.mymvp.injector.modules;

import android.app.Activity;

import com.boomhe.mymvp.injector.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * @author boomhe on 2017/11/8.
 * Activity Module
 */
@Module
public class ActivityModule {

    private final Activity mActivity;

    public ActivityModule(Activity mActivity) {
        this.mActivity = mActivity;
    }

    @Provides
    @PerActivity
    Activity getActivity(){
        return mActivity;
    }
}
