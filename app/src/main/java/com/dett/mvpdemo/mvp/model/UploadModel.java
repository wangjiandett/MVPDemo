package com.dett.mvpdemo.mvp.model;

import com.dett.dettmvp.mvp.BaseResponse;
import com.dett.dettmvp.mvp.BaseUploadFileModel;
import com.dett.dettmvp.mvp.ValueCallback;
import com.dett.mvpdemo.mvp.bean.UploadResp;
import com.dett.mvpdemo.net.ApiCreator;

import io.reactivex.Observable;
import okhttp3.RequestBody;

/**
 * 上传文件model， 如果请求数据格式不是BaseResponse，可自行更换
 *
 * @author wangjian
 * Created on 2020/10/19 10:24
 */
public class UploadModel extends BaseUploadFileModel<BaseResponse<UploadResp>, UploadResp> {

    public UploadModel(ValueCallback<UploadResp> callback) {
        super(callback);
    }

    @Override
    protected Observable<BaseResponse<UploadResp>> getUploadObservable(RequestBody body) {
        return ApiCreator.getUploadFileInterface(this).uploadFile(body);
    }

    @Override
    protected void onSuccess(UploadResp value) {
        mCallback.onSuccess(value);
    }

    @Override
    protected void onFail(int code, String msg) {
        mCallback.onFail(code, msg);
    }

    @Override
    public void onProgressChange(int percent) {
        mCallback.onProgressChange(percent);
    }


}
