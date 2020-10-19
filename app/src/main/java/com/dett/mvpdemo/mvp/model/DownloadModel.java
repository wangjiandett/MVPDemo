package com.dett.mvpdemo.mvp.model;

import com.dett.dettmvp.mvp.BaseDownloadFileModel;
import com.dett.dettmvp.mvp.ProgressResultListener;
import com.dett.dettmvp.mvp.ValueCallback;
import com.dett.mvpdemo.net.ApiCreator;

import java.io.File;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

/**
 * Describe
 *
 * @author wangjian
 * Created on 2020/10/19 11:10
 */
public class DownloadModel extends BaseDownloadFileModel {

    public DownloadModel(ValueCallback<File> mCallback) {
        super(mCallback);
    }

    @Override
    protected Observable<ResponseBody> getDownloadObservable(String url) {
        return ApiCreator.getDownloadFileInterface(this).downloadFile2(url);
    }

    @Override
    protected void onSuccess(File value) {
        mCallback.onSuccess(value);
    }

    @Override
    protected void onFail(String msg) {
        mCallback.onFail(msg);
    }

    @Override
    public void onProgressChange(int percent) {
        mCallback.onProgressChange(percent);
    }
}
