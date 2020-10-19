package com.dett.dettmvp;
import android.app.Application;
import androidx.multidex.MultiDexApplication;

public class BaseApp extends MultiDexApplication {

    protected static BaseApp mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public static Application getAppContext() {
        return mInstance;
    }

}
