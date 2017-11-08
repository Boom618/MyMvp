package com.boomhe.mymvp;

import android.app.Application;
import android.content.Intent;

import com.boomhe.mymvp.injector.components.ApplicationComponent;
import com.tencent.tinker.loader.app.DefaultApplicationLike;

/**
 * @author boomhe on 2017/11/8.
 *
 * Application
 */

public class AndroidApplication extends DefaultApplicationLike {

    private static ApplicationComponent sAppComponent;

    public AndroidApplication(Application application, int tinkerFlags, boolean tinkerLoadVerifyFlag, long applicationStartElapsedTime, long applicationStartMillisTime, Intent tinkerResultIntent) {
        super(application, tinkerFlags, tinkerLoadVerifyFlag, applicationStartElapsedTime, applicationStartMillisTime, tinkerResultIntent);
    }


    public static ApplicationComponent getAppComponent(){
        return sAppComponent;
    }
}
