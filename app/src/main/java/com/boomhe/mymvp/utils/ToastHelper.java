package com.boomhe.mymvp.utils;

import android.content.Context;
import android.widget.Toast;

import es.dmoral.toasty.Toasty;

/**
 * @author boomhe on 2017/11/9.
 */

public class ToastHelper {

    public static void toastError(Context context,String msg){
        Toasty.error(context, msg, Toast.LENGTH_SHORT, true).show();
    }

    public static void toastSuccess(Context context,String msg){
        Toasty.success(context, msg, Toast.LENGTH_SHORT, true).show();
    }

    public static void toastWarning(Context context,String msg){
        Toasty.warning(context, msg, Toast.LENGTH_SHORT, true).show();
    }

    public static void toastNormal(Context context,String msg){
        Toasty.normal(context, msg).show();
    }
}
