package com.dett.mvpdemo.net;


import com.dett.dettmvp.exception.ExceptionHandle;
import com.dett.dettmvp.exception.ResponseException;
import com.dett.dettmvp.mvp.BaseResponseModel;

/**
 * app model 重写部分方法，自定义处理登录失败的情况
 *
 * @author wangjian
 * Created on 2020/10/4 11:23
 */
public abstract class AppModel<Data> extends BaseResponseModel<Data> {

    private static final int SUCCESS_CODE = 1;
    private static final int NO_LOGIN_CODE = -1;

    @Override
    protected int getSuccessCode() {
        return SUCCESS_CODE;
    }

    @Override
    protected void dealError(Throwable e) {
        ResponseException throwable = ExceptionHandle.handleException(e);
        // 未登录时执行的操作
        if(throwable.code == NO_LOGIN_CODE){
            // 跳转到登录页
        }
        onFail(throwable.message);
    }
}