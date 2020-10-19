package com.dett.mvpdemo.mvp.model;


import java.util.List;
import java.util.Map;

import com.dett.dettmvp.mvp.ValueCallback;
import com.dett.mvpdemo.mvp.bean.TestResponse;
import com.dett.mvpdemo.mvp.contract.TestContract;
import com.dett.mvpdemo.net.ApiCreator;
import com.dett.mvpdemo.net.AppModel;

/**
 * Describe
 *
 * @author wangjian
 * Created on 2020/9/30 17:12
 */
public class TestModel extends AppModel<List<TestResponse>> implements TestContract.ITestModel {

    @Override
    public void sendTestRequest(Map<String, String> request, ValueCallback<List<TestResponse>> callback) {
        this.mCallback = callback;
        request(ApiCreator.apiInterfaces.getBanners(request));
    }

    @Override
    protected void onShowProgress() {// optional
        mCallback.onShowProgress();
    }

    @Override
    protected void onHideProgress() {// optional
        mCallback.onHideProgress();
    }

    @Override
    protected void onSuccess(List<TestResponse> value) {
        mCallback.onSuccess(value);
    }

    @Override
    protected void onFail(String msg) {
        mCallback.onFail(msg);
    }

}
