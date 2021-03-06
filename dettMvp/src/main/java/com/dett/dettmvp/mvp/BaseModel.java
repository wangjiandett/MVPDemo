/*
 * Copyright (c) 2018.  For more infomation visit https://github.com/wangjiandett/RxDemo
 */
package com.dett.dettmvp.mvp;


import com.dett.dettmvp.exception.ExceptionHandle;
import com.dett.dettmvp.exception.ResponseException;
import com.dett.dettmvp.exception.ServerException;
import com.dett.dettmvp.utils.LogUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


/**
 * Base model 处理请求，返回响应实体Resp中如果没有Data实体，或想直接处理Resp，
 * 可以设置Data实体为Resp，如：xxxModel<Resp, Resp>，解析结果将返回Resp实体
 * <p>
 * Created by：wangjian on 2017/12/21 16:41
 *
 * @param <Resp> 请求响应实体
 * @param <Data> 请求响应实体中的data实体
 */
public abstract class BaseModel<Resp, Data> {

    /**
     * 请求成功 code
     */
    private static final int SUCCESS_CODE = 1;
    protected ValueCallback<Data> mCallback;
    private Disposable mDisposable;

    public BaseModel() {
    }

    /**
     * 获取请求成功响应码
     */
    protected int getSuccessCode(){
        return SUCCESS_CODE;
    }

    /**
     * 发送请求，并解析数据
     *
     * @param observable observable
     */
    protected void request(Observable<Resp> observable) {
        observable.subscribeOn(Schedulers.io())// io线程中执行请求
                .observeOn(AndroidSchedulers.mainThread())// 主线程中执行监听
                .map(new ResultFilter(getSuccessCode()))// 处理响应结果
                .subscribe(new Observer<Data>() {

                    @Override
                    public void onSubscribe(Disposable d) {
                        mDisposable = d;
                        onStart();
                    }

                    protected void onStart() {
                        LogUtils.d("onStart");
                        onShowProgress();
                    }

                    @Override
                    public void onNext(Data value) {
                        LogUtils.d("onNext");
                        onHideProgress();
                        onSuccess(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.e("onError:" + e.getMessage());
                        onHideProgress();
                        dealError(e);
                    }

                    @Override
                    public void onComplete() {
                        LogUtils.d("onComplete");
                    }
                });
    }

    /**
     * 取消请求监听
     */
    public void onCancel() {
        if (this.mDisposable != null && !this.mDisposable.isDisposed()) {
            this.mDisposable.dispose();
        }
    }

    /**
     * 结果处理器
     */
    private class ResultFilter implements Function<Resp, Data> {

        private int SUCCESS_CODE;

        public ResultFilter(int SUCCESS_CODE) {
            this.SUCCESS_CODE = SUCCESS_CODE;
        }

        @Override
        public Data apply(Resp baseResponse) {
            // when status equals success code
            if (baseResponse instanceof BaseResponse) {
                BaseResponse<Data> response = (BaseResponse<Data>) baseResponse;
                if (response.code == SUCCESS_CODE) {
                    // 请求成功，但是返回结果为空的情况
                    if (response.data == null) {
                        // 获取data实例
                        Data data = getDataInstance();
                        if (data == null) {
                            // 否则返回object
                            data = (Data) new Object();
                        }
                        return data;
                    }
                    return (Data) response.data;
                } else {
                    // throw data error exception
                    // 服务器自定义异常
                    throw new ServerException(response.code, response.msg);
                }
            } else {
                return getResponse(baseResponse);
            }
        }
    }

    /**
     * 在baseResponse不是BaseResponse的时候，可以出覆盖此方法重新定义响应结果的解析规则
     */
    protected Data getResponse(Resp baseResponse){
        return (Data) baseResponse;
    }

    /**
     * 通过反射获取Data类型的实例
     *
     * @return data
     */
    private Data getDataInstance() {
        Data data = null;
        try {
            //this指的是当前运行的实例（子类实例）
            Class clz = this.getClass();
            //2.获取类的泛型父类
            //Type: 是Java里面所有类型的父接口
            //获取泛型父类，必须用该方法，此处的泛型父类不是指当前的类，而是具体继承的BaseAction<Standard>，当前类为BaseAction<T>泛型尚未确定
            Type type = clz.getGenericSuperclass();
            //3.把Type转换为具体的类型
            //将泛型父类转换为具体的那种类型
            ParameterizedType pt = (ParameterizedType) type;
            // 通过反射获取model的真实类型
            Class<Data> clazz = (Class<Data>) pt.getActualTypeArguments()[0];
            // 通过反射创建model的实例
            data = clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    /**
     * deal the exception
     *
     * @param e the exception
     */
    protected void dealError(Throwable e) {
        ResponseException throwable = ExceptionHandle.handleException(e);
        onFail(throwable.code, throwable.message);
    }

    /**
     * show loading progress
     */
    protected void onShowProgress() {
    }

    /**
     * hide loading progress
     */
    protected void onHideProgress() {
    }

    /**
     * the success callback
     *
     * @param value the success value
     */
    protected abstract void onSuccess(Data value);

    /**
     * the fail callback
     *
     * @param code the error code
     * @param msg the fail message
     */
    protected abstract void onFail(int code, String msg);

}
