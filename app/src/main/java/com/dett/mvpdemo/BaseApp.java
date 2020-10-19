package com.dett.mvpdemo;
import androidx.multidex.MultiDexApplication;

import com.dett.dettmvp.DettMvp;

public class BaseApp extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        DettMvp.init(this);
    }
}
