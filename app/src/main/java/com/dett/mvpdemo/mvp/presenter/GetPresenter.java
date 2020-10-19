package com.dett.mvpdemo.mvp.presenter;


import java.util.List;
import java.util.Map;

import com.dett.dettmvp.mvp.SimpleValueCallback;
import com.dett.mvpdemo.mvp.bean.GetResponse;
import com.dett.mvpdemo.mvp.contract.GetContract;

/**
 * Describe
 *
 * @author wangjian
 * Created on 2020/9/30 17:12
 */
public class GetPresenter implements GetContract.IGetPresenter {

    private GetContract.IGetView iView;
    private GetContract.IGetModel iModel;

    public GetPresenter(GetContract.IGetView iView, GetContract.IGetModel iModel) {
        this.iView = iView;
        this.iModel = iModel;
    }

    @Override
    public void sendTestRequest(Map<String, String> request) {
        iModel.sendTestRequest(request, new SimpleValueCallback<List<GetResponse>>() {
            @Override
            public void onSuccess(List<GetResponse> response) {
                iView.onGetSuccess(response);
            }

            @Override
            public void onFail(String msg) {
                iView.onFail(msg);
            }
        });
    }

}
