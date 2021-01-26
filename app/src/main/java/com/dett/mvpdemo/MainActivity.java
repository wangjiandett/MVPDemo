package com.dett.mvpdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.dett.dettmvp.mvp.SimpleValueCallback;
import com.dett.dettmvp.utils.GsonHelper;
import com.dett.dettmvp.utils.LogUtils;
import com.dett.mvpdemo.mvp.bean.PostRequest;
import com.dett.mvpdemo.mvp.bean.PostResponse;
import com.dett.mvpdemo.mvp.bean.GetResponse;
import com.dett.mvpdemo.mvp.bean.UploadResp;
import com.dett.mvpdemo.mvp.contract.PostContract;
import com.dett.mvpdemo.mvp.contract.GetContract;
import com.dett.mvpdemo.mvp.model.DownloadModel;
import com.dett.mvpdemo.mvp.model.PostModel;
import com.dett.mvpdemo.mvp.model.GetModel;
import com.dett.mvpdemo.mvp.model.UploadModel;
import com.dett.mvpdemo.mvp.presenter.PostPresenter;
import com.dett.mvpdemo.mvp.presenter.GetPresenter;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * get,post,upload,download 测试案例
 * <p>
 * Created by：wangjian on 2018/8/21 15:11
 */
public class MainActivity extends AppCompatActivity implements GetContract.IGetView, PostContract.IPostView {

    GetPresenter getPresenter;
    PostPresenter postPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getPresenter = new GetPresenter(this, new GetModel());
        postPresenter = new PostPresenter(this, new PostModel());
    }

    public void onClick(View view) {
        int viewId = view.getId();

        if (viewId == R.id.btn_send_bean_request) {
            // 测试get请求
            Map<String, String> request = new HashMap<>();
            request.put("type", "android");
            getPresenter.sendTestRequest(request);
        } else if (viewId == R.id.btn_post_bean_request) {
            // post 请求没有找到合适的接口，就随便填写了一个
            PostRequest request = new PostRequest();
            request.phone = "12323232323";
            postPresenter.sendPostRequest(request);
        } else if (viewId == R.id.btn_post_upload_request) {
            // 文件上传请求
            File uploadFile = new File("your file path");
            new UploadModel(new SimpleValueCallback<UploadResp>() {

                @Override
                public void onSuccess(UploadResp response) {

                }

                @Override
                public void onFail(int code, String msg) {
                    showToast(msg);
                }
            }).uploadFile("files", uploadFile);
        } else if (viewId == R.id.btn_post_download_request) {
            // 文件下载请求
            File downloadFile = new File(getCacheDir() + "/" + "xxx.apk");
            Button button = findViewById(R.id.btn_post_download_request);
            new DownloadModel(new SimpleValueCallback<File>() {
                @Override
                public void onSuccess(File response) {
                    LogUtils.d(response.getAbsolutePath());
                    button.setText(response.getAbsolutePath());
                }

                @Override
                public void onFail(int code, String msg) {
                    LogUtils.e(msg);
                    showToast(msg);
                }

                @Override
                public void onProgressChange(int percent) {
                    LogUtils.d("percent: " + percent);
                    button.setText("进度: " + percent);

                }
            }).download("https://imtt.dd.qq.com/16891/apk/55259F8EF9824AF1BF80106B0E00BCD1.apk", downloadFile);
        }
    }

    @Override
    public void onGetSuccess(List<GetResponse> response) {
        LogUtils.d(response);
        showToast(GsonHelper.toJson(response));
    }

    @Override
    public void onPostSuccess(PostResponse response) {
        LogUtils.d(response);
        showToast(GsonHelper.toJson(response));
    }

    @Override
    public void onFail(int code, String msg) {
        showToast(msg);
    }

    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}