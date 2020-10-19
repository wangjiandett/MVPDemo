package com.dett.dettmvp.mvp;

/**
 * value callback
 * <p>
 * Created by：wangjian on 2017/12/21 11:12
 */
public interface ValueCallback<T> extends ProgressResultListener {
    
    void onShowProgress();
    
    void onHideProgress();
    
    void onSuccess(T response);
    
    void onFail(String msg);

    void cancel();
    
}
