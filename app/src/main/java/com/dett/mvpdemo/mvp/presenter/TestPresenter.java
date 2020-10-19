package com.dett.mvpdemo.mvp.presenter;


import java.util.List;
import java.util.Map;

import com.dett.dettmvp.mvp.SimpleValueCallback;
import com.dett.mvpdemo.mvp.bean.TestResponse;
import com.dett.mvpdemo.mvp.contract.TestContract;

/**
 * Describe
 *
 * @author wangjian
 * Created on 2020/9/30 17:12
 */
public class TestPresenter implements TestContract.ITestPresenter {

    private TestContract.ITestView iView;
    private TestContract.ITestModel iModel;

    public TestPresenter(TestContract.ITestView iView, TestContract.ITestModel iModel) {
        this.iView = iView;
        this.iModel = iModel;
    }

    @Override
    public void sendTestRequest(Map<String, String> request) {
        iModel.sendTestRequest(request, new SimpleValueCallback<List<TestResponse>>() {
            @Override
            public void onSuccess(List<TestResponse> response) {
                iView.onTestSuccess(response);
            }

            @Override
            public void onFail(String msg) {
                iView.onFail(msg);
            }
        });
    }

}
