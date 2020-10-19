package com.dett.mvpdemo.mvp.contract;


import com.dett.dettmvp.mvp.ValueCallback;
import com.dett.mvpdemo.mvp.bean.PostRequest;


import com.dett.mvpdemo.mvp.bean.PostResponse;

/**
 * Describe
 *
 * @author wangjian
 * Created on 2020/9/30 17:12
 */
public interface PostContract {


    interface IPostModel {
        void sendPostRequest(PostRequest request, ValueCallback<PostResponse> callback);
    }

    interface IPostPresenter {
        void sendPostRequest(PostRequest request);
    }

    interface IPostView {
        void onPostSuccess(PostResponse response);

        void onFail(String msg);
    }

}
