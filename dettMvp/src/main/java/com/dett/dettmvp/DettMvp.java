package com.dett.dettmvp;

import android.app.Application;
import android.content.Context;

/**
 * Describe
 *
 * @author wangjian
 * Created on 2020/10/19 14:30
 */
public class DettMvp {

    private Context mContext;

    private volatile static DettMvp instance = null;

    public static DettMvp getInstance() {
        if (instance == null) {
            synchronized (DettMvp.class) {
                if (instance == null) {
                    instance = new DettMvp();
                }
            }
        }
        return instance;
    }

    public static void init(Application application){
        DettMvp config = getInstance();
        if(config != null){
            config.mContext = application;
        }
    }

    public Context getContext() {
        return mContext;
    }
}
