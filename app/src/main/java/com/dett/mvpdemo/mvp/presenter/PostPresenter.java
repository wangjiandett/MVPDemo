package com.dett.mvpdemo.mvp.presenter;

import com.dett.dettmvp.mvp.SimpleValueCallback;
import com.dett.mvpdemo.mvp.bean.PostRequest;
import com.dett.mvpdemo.mvp.bean.PostResponse;
import com.dett.mvpdemo.mvp.contract.PostContract;

/**
 * Describe
 *
 * @author wangjian
 * Created on 2020/9/30 17:12
 */
public class PostPresenter implements PostContract.IPostPresenter {

    private PostContract.IPostView iView;
    private PostContract.IPostModel iModel;

    public PostPresenter(PostContract.IPostView iView, PostContract.IPostModel iModel) {
        this.iView = iView;
        this.iModel = iModel;
    }

    @Override
    public void sendPostRequest(PostRequest request) {
        iModel.sendPostRequest(request, new SimpleValueCallback<PostResponse>() {
            @Override
            public void onSuccess(PostResponse response) {
                iView.onPostSuccess(response);
            }

            @Override
            public void onFail(int code, String msg) {
                iView.onFail(code, msg);
            }
        });
    }

}
