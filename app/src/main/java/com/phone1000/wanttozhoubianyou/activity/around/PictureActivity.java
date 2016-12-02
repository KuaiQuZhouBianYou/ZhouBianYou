package com.phone1000.wanttozhoubianyou.activity.around;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.google.gson.Gson;
import com.phone1000.wanttozhoubianyou.R;
import com.phone1000.wanttozhoubianyou.adapter.around.ViewPagerAdapter;
import com.phone1000.wanttozhoubianyou.model.around.Scienic;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by 落叶 on 2016-12-02.
 */
public class PictureActivity extends AppCompatActivity {


    private ViewPager viewPager;
    private ViewPagerAdapter adapter;
    private String url;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_picture);

        Intent intent = getIntent();
        url = intent.getStringExtra("url");

        initView();
        setupData();

    }

    private void setupData() {
        RequestParams requestParams = new RequestParams(url);
        x.http().get(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                Scienic scienic = gson.fromJson(result, Scienic.class);





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

    private void initView() {
        viewPager = (ViewPager) findViewById(R.id.activity_pic_viewpager);
        adapter = new ViewPagerAdapter(null);
        viewPager.setAdapter(adapter);
    }
}
