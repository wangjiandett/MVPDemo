package com.dett.dettmvp.mvp;


import com.google.gson.annotations.SerializedName;

/**
 * Net response
 * <p>
 * Created by：wangjian on 2017/12/20 16:12
 */
public class BaseResponse<T> {

    public T data;
    @SerializedName("code")
    public int code;
    @SerializedName("msg")
    public String msg;
    
}
