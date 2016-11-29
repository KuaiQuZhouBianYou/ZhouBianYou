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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SecondDsicoverActivity extends AppCompatActivity {

    private static final String TAG = SecondDsicoverActivity.class.getSimpleName();
    private String themeId;
    private ListView mListView;
    private SecondDiscoverAdapter adapter;
    private ImageView headerImage;
    private TextView headerTitle;
    private TextView headerDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_dsicover);
        initView();
        //setData();
        getData();
        getHeaderData();
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

}
