package com.phone1000.wanttozhoubianyou.activity.around;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.phone1000.wanttozhoubianyou.R;
import com.phone1000.wanttozhoubianyou.adapter.around.ViewPagerAdapter;

/**
 * Created by 落叶 on 2016-12-02.
 */
public class PictureActivity extends AppCompatActivity {


    private ViewPager viewPager;
    private ViewPagerAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_picture);
        initView();
        setupData();

    }

    private void setupData() {



    }

    private void initView() {
        viewPager = (ViewPager) findViewById(R.id.activity_pic_viewpager);
        adapter = new ViewPagerAdapter(null);
        viewPager.setAdapter(adapter);
    }
}
