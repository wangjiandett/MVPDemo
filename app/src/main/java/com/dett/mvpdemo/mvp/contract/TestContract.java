package com.dett.mvpdemo.mvp.contract;


import java.util.List;
import java.util.Map;

import com.dett.dettmvp.mvp.ValueCallback;
import com.dett.mvpdemo.mvp.bean.TestResponse;

/**
 * Describe
 *
 * @author wangjian
 * Created on 2020/9/30 17:12
 */
public interface TestContract {


    interface ITestModel {
        void sendTestRequest(Map<String, String> request, ValueCallback<List<TestResponse>> callback);
    }

    interface ITestPresenter {
        void sendTestRequest(Map<String, String> request);
    }

    interface ITestView {
        void onTestSuccess(List<TestResponse> response);

        void onFail(String msg);
    }

}
