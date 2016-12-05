package com.phone1000.wanttozhoubianyou.activity.around;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;

import com.google.gson.Gson;
import com.phone1000.wanttozhoubianyou.R;
import com.phone1000.wanttozhoubianyou.adapter.around.ViewPagerAdapter;
import com.phone1000.wanttozhoubianyou.model.around.Scienic;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by 落叶 on 2016-12-02.
 */
public class PictureActivity extends AppCompatActivity implements View.OnClickListener {


    private static final String TAG = PictureActivity.class.getSimpleName();

    private ViewPager viewPager;
    private ViewPagerAdapter adapter;
    private String url;
    private RadioButton changeScreen;
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



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_picture);

        Intent intent = getIntent();
        url = intent.getStringExtra("url");

        initView();
        setupData();
/*
* if(getRequestedOrientation()!=ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE){
  setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
 }
* */

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
        changeScreen = (RadioButton) findViewById(R.id.activity_pic_changesreen);

          changeScreen.setOnClickListener(this);
        viewPager = (ViewPager) findViewById(R.id.activity_pic_viewpager);
        adapter = new ViewPagerAdapter(null);
        viewPager.setAdapter(adapter);
    }

    public void getPic() {
        List<PhotoView>data = new ArrayList<>();

        for (int i = 0; i <photoAlbum.size() ; i++) {
           // ImageView imageView = new ImageView(this);
            PhotoView photo = new PhotoView(this);
            x.image().bind(photo,photoAlbum.get(i).getFile());
            PhotoViewAttacher mAttacher=new PhotoViewAttacher(photo);
            data.add(photo);
        }
        Log.e(TAG, "getPic: "+photoAlbum.size() );
        adapter.updataRes(data);



    }

    @Override
    public void onClick(View v) {
        Log.e(TAG, "onClick: " );
        if(getRequestedOrientation()== ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }else{
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
    }

   /* @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            if(getRequestedOrientation()!= ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE){
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            }

            }
        else {
            if(getRequestedOrientation()!= ActivityInfo.SCREEN_ORIENTATION_PORTRAIT){
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            }
        }
    }*/
}
