package com.dett.mvpdemo.net;

import com.dett.dettmvp.mvp.BaseResponse;
import com.dett.mvpdemo.mvp.bean.UploadResp;
import com.dett.mvpdemo.mvp.bean.PostRequest;
import com.dett.mvpdemo.mvp.bean.PostResponse;
import com.dett.mvpdemo.mvp.bean.TestResponse;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;

/**
 * 统一管理网络请求接口
 *
 * post请求的时候
 * 注解介绍：@File，@FieldMap参数提交的类型是'application/x-www-form-urlencoded'
 * 注解介绍：@Body参数提交的类型是'application/json'
 * 官方介绍：https://square.github.io/retrofit/
 */
public interface ApiInterfaces {

    @POST("image/upload")
    Observable<BaseResponse<UploadResp>> uploadFile(@Body RequestBody body);

    // "16891/apk/55259F8EF9824AF1BF80106B0E00BCD1.apk"
    // "dmusic/NeteaseCloudMusic_Music_official_7.3.0.1599039901.apk"
    @Streaming
    @GET("16891/apk/55259F8EF9824AF1BF80106B0E00BCD1.apk")
    Observable<ResponseBody> downloadFile();


    // retrofit自定义请求头
    // @Headers(value = {"Content-type:application/x-www-form-urlencoded;charset=UTF-8"})
    // @Headers(value = {"Content-type:application/json;charset=UTF-8"})

    @GET("cardBanner/list")
    Observable<BaseResponse<List<TestResponse>>> getBanners(@QueryMap Map<String, String> map);

    @GET("cardBanner/list")
    Observable<BaseResponse<List<TestResponse>>> getBanners(@Query("type") String type);

    @POST("login")
    Observable<BaseResponse<PostResponse>> testPost(@Body PostRequest request);


}
