package com.dett.mvpdemo.mvp.contract;


import java.util.List;
import java.util.Map;

import com.dett.dettmvp.mvp.ValueCallback;
import com.dett.mvpdemo.mvp.bean.GetResponse;

/**
 * Describe
 *
 * @author wangjian
 * Created on 2020/9/30 17:12
 */
public interface GetContract {


    interface IGetModel {
        void sendTestRequest(Map<String, String> request, ValueCallback<List<GetResponse>> callback);
    }

    interface IGetPresenter {
        void sendTestRequest(Map<String, String> request);
    }

    interface IGetView {
        void onGetSuccess(List<GetResponse> response);

        void onFail(int code, String msg);
    }

}
