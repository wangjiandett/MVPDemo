package com.dett.mvpdemo.mvp.model;

import com.dett.dettmvp.mvp.ValueCallback;
import com.dett.mvpdemo.mvp.bean.PostRequest;
import com.dett.mvpdemo.mvp.bean.PostResponse;
import com.dett.mvpdemo.mvp.contract.PostContract;
import com.dett.mvpdemo.net.ApiCreator;
import com.dett.mvpdemo.net.AppModel;

/**
 * Describe
 *
 * @author wangjian
 * Created on 2020/9/30 17:12
 */
public class PostModel extends AppModel<PostResponse> implements PostContract.IPostModel {

    @Override
    public void sendPostRequest(PostRequest request, ValueCallback<PostResponse> callback) {
        this.mCallback = callback;
        request(ApiCreator.apiInterfaces.testPost(request));
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
    protected void onSuccess(PostResponse value) {
        mCallback.onSuccess(value);
    }

    @Override
    protected void onFail(String msg) {
        mCallback.onFail(msg);
    }

}
