package com.boomhe.mymvp.module.base;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

/**
 * @author boomhe on 2017/11/6.
 * 基类 Activity
 */

public abstract class BaseActivity< T extends IBasePresenter > extends RxAppCompatActivity implements IBaseView {


}
