package com.phone1000.wanttozhoubianyou.discoveractivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.phone1000.wanttozhoubianyou.R;
import com.phone1000.wanttozhoubianyou.discoveradapter.SecondDiscoverAdapter;
import com.phone1000.wanttozhoubianyou.discovercontest.DiscoverContest;
import com.phone1000.wanttozhoubianyou.discovermodel.SecondDiscoverModel;
import com.phone1000.wanttozhoubianyou.discovermodel.SecondHeaderModel;
import com.phone1000.wanttozhoubianyou.discovermodel.SecondTopTitleModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.tencent.qq.QQ;

public class SecondDsicoverActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = SecondDsicoverActivity.class.getSimpleName();
    private String themeId;
    private ListView mListView;
    private SecondDiscoverAdapter adapter;
    private ImageView headerImage;
    private TextView headerTitle;
    private TextView headerDetail;
    private ImageView mShare;
    private ImageView mBack;
    private TextView topTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_dsicover);
        initView();
        //setData();
        getData();
        getHeaderData();
        getTopTitle();
    }

    private void getTopTitle() {
        RequestParams requestParams = new RequestParams(DiscoverContest.DISCOVER_URL1 +Integer.parseInt(themeId )+ DiscoverContest.DISCOVER_URL12);
        x.http().get(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    JSONObject content = jsonObject.getJSONObject("content");
                    Type type = new TypeToken<SecondTopTitleModel>() {
                    }.getType();
                    SecondTopTitleModel data=gson.fromJson(content.toString(),type);
                    topTitle.setText(data.getTitle());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    private void getHeaderData() {
        RequestParams requestParams = new RequestParams(DiscoverContest.DISCOVER_URL1 +Integer.parseInt(themeId )+ DiscoverContest.DISCOVER_URL12);
        x.http().get(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    JSONObject content = jsonObject.getJSONObject("content");
                    Type type = new TypeToken<SecondHeaderModel>() {
                    }.getType();
                    SecondHeaderModel data=gson.fromJson(content.toString(),type);
                    headerTitle.setText(data.getSubTitle());
                    headerDetail.setText(data.getDescription());
                    x.image().bind(headerImage,data.getImageUrl());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    private void getData() {

        RequestParams requestParams = new RequestParams(DiscoverContest.DISCOVER_URL1 +Integer.parseInt(themeId )+ DiscoverContest.DISCOVER_URL12);
        x.http().get(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e(TAG, "onSuccess: 1111111" );
                try {
                    Gson gson = new Gson();
                    JSONObject jsonObject = new JSONObject(result);
                    JSONObject content = jsonObject.getJSONObject("content");
                    JSONArray productList = content.getJSONArray("productList");
                    Type type = new TypeToken<List<SecondDiscoverModel>>() {
                    }.getType();
                    List<SecondDiscoverModel>data=gson.fromJson(productList.toString(),type);
                    adapter.updataRes(data);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e(TAG, "onError: 111111" );
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Log.e(TAG, "onCancelled: 111111" );
            }

            @Override
            public void onFinished() {
                Log.e(TAG, "onFinished: 111111" );
            }
        });
    }

    private void setData() {
        List<SecondDiscoverModel>data=new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            SecondDiscoverModel secondDiscoverModel = new SecondDiscoverModel();
            secondDiscoverModel.setCityName("临沂"+i);
            data.add(secondDiscoverModel);
        }
        adapter.updataRes(data);
    }

    private void initView() {
        Intent intent = getIntent();
        Bundle bundle=intent.getExtras();
        themeId=bundle.getString("themeId");
        Log.e(TAG, "initView: 111111"+themeId );

        //分享
        mShare = (ImageView) findViewById(R.id.second_discover_share);
        mShare.setOnClickListener(this);
        //回退
        mBack = ((ImageView) findViewById(R.id.second_discover_back));
        mBack.setOnClickListener(this);
        //头部标题
        topTitle = (TextView) findViewById(R.id.second_discover_topTitle);

        mListView = (ListView) findViewById(R.id.second_second_discover);

        //加载头部
        View header = LayoutInflater.from(this).inflate(R.layout.second_discover_header, null);
        headerImage = ((ImageView) header.findViewById(R.id.second_header_iamge));
        headerTitle = ((TextView) header.findViewById(R.id.second_header_title));
        headerDetail = ((TextView) header.findViewById(R.id.second_header_detail));
        mListView.addHeaderView(header);

        adapter = new SecondDiscoverAdapter(this,null);
        mListView.setAdapter(adapter);


    }
    private void showShare() {
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
         // 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle("标题");
        // titleUrl是标题的网络链接，仅在Linked-in,QQ和QQ空间使用
        oks.setTitleUrl("http://sharesdk.cn");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");
        //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
        oks.setImageUrl("http://f1.sharesdk.cn/imgs/2014/02/26/owWpLZo_638x960.jpg");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite("ShareSDK");
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");
        // 启动分享GUI
        oks.show(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.second_discover_share:
                showShare();
                break;
            case R.id.second_discover_back:
                this.finish();
                break;
        }
    }


}
