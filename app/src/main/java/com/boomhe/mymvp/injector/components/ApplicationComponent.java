package com.boomhe.mymvp.injector.components;

import android.content.Context;

import javax.inject.Singleton;

/**
 * @author boomhe on 2017/11/8.
 */

/**
 * @Singleton :单例
 */
@Singleton
public interface ApplicationComponent {

    Context getContext();

}
