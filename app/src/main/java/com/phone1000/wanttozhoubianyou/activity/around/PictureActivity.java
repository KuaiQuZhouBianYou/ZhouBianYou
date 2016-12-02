package com.phone1000.wanttozhoubianyou.activity.around;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.phone1000.wanttozhoubianyou.R;
import com.phone1000.wanttozhoubianyou.adapter.around.ViewPagerAdapter;
import com.phone1000.wanttozhoubianyou.model.around.Scienic;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 落叶 on 2016-12-02.
 */
public class PictureActivity extends AppCompatActivity {


    private static final String TAG = PictureActivity.class.getSimpleName();

    private ViewPager viewPager;
    private ViewPagerAdapter adapter;
    private String url;
    private  List<Scienic.ContentBean.PhotoAlbumBean> photoAlbum;



    public static final int PIC_OK=1;

    public Handler hander = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case PIC_OK:

                    for (int i = 0; i <photoAlbum.size() ; i++) {

                        getPic();

                    }

                    break;
            }




        }
    };
    private Object pic;

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
               photoAlbum = scienic.getContent().getPhotoAlbum();
                hander.sendEmptyMessage(PIC_OK);



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

    public void getPic() {
        List<ImageView>data = new ArrayList<>();

        for (int i = 0; i <photoAlbum.size() ; i++) {
            ImageView imageView = new ImageView(this);
            x.image().bind(imageView,photoAlbum.get(i).getFile());
            data.add(imageView);
        }
        Log.e(TAG, "getPic: "+photoAlbum.size() );
        adapter.updataRes(data);



    }
}
