package com.dett.mvpdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.dett.dettmvp.utils.GsonHelper;
import com.dett.dettmvp.utils.LogUtils;
import com.dett.mvpdemo.mvp.bean.PostRequest;
import com.dett.mvpdemo.mvp.bean.PostResponse;
import com.dett.mvpdemo.mvp.bean.TestResponse;
import com.dett.mvpdemo.mvp.contract.PostContract;
import com.dett.mvpdemo.mvp.contract.TestContract;
import com.dett.mvpdemo.mvp.model.PostModel;
import com.dett.mvpdemo.mvp.model.TestModel;
import com.dett.mvpdemo.mvp.presenter.PostPresenter;
import com.dett.mvpdemo.mvp.presenter.TestPresenter;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements TestContract.ITestView, PostContract.IPostView {

    TestPresenter testPresenter;
    PostPresenter postPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        testPresenter = new TestPresenter(this, new TestModel());
        postPresenter = new PostPresenter(this, new PostModel());
    }

    public void onClick(View view) {
        int viewId = view.getId();
        if (viewId == R.id.btn_send_bean_request) {
            Map<String, String> request = new HashMap<>();
            request.put("type", "android");
            testPresenter.sendTestRequest(request);
        } else if (viewId == R.id.btn_post_bean_request) {
            // post 请求没有找到合适的接口，就随便填写了一个
            PostRequest request = new PostRequest();
            request.phone = "12323232323";
            postPresenter.sendPostRequest(request);
        }
    }

    @Override
    public void onTestSuccess(List<TestResponse> response) {
        LogUtils.d(response);
        showToast(GsonHelper.toJson(response));
    }

    @Override
    public void onPostSuccess(PostResponse response) {
        LogUtils.d(response);
        showToast(GsonHelper.toJson(response));
    }

    @Override
    public void onFail(String msg) {
        showToast(msg);
    }

    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}