package com.boomhe.mymvp.injector;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * @author boomhe on 2017/11/8.
 * 控制生命周期
 */

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerActivity {
}
