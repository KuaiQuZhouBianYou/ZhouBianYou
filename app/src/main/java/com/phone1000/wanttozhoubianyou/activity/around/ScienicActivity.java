package com.phone1000.wanttozhoubianyou.activity.around;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.amap.api.services.weather.LocalWeatherForecastResult;
import com.amap.api.services.weather.LocalWeatherLive;
import com.amap.api.services.weather.LocalWeatherLiveResult;
import com.amap.api.services.weather.WeatherSearch;
import com.amap.api.services.weather.WeatherSearchQuery;
import com.google.gson.Gson;
import com.phone1000.wanttozhoubianyou.Fragment.around.RecommendFragment;
import com.phone1000.wanttozhoubianyou.R;
import com.phone1000.wanttozhoubianyou.activity.LocationActivity;
import com.phone1000.wanttozhoubianyou.adapter.around.RecommendViewPagerFragmentAdapter;
import com.phone1000.wanttozhoubianyou.model.around.Scienic;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 落叶 on 2016-11-29.
 */
@TargetApi(Build.VERSION_CODES.M)
public class ScienicActivity extends AppCompatActivity implements View.OnClickListener, WeatherSearch.OnWeatherSearchListener, View.OnScrollChangeListener {
    private static final String TAG = ScienicActivity.class.getSimpleName();
    private String url_header = "http://apiphp.yaochufa.com/portal/scenic/scenicDetail430?system=android&channel=yaochufa&imei=ffffffff-8f6e-f1b6-197c-94460033c587&regId=170976fa8a8e9eae6b4&id=";
    private String url_later="&lang=app&version=5.5.1&deviceToken=ffffffff-8f6e-f1b6-197c-94460033c587";
    private String url;
    private ImageView back;
    private ImageView share;
    private TextView topTitle;
    private ViewPager recommend;
    private ImageView coverimg;
    private RecommendViewPagerFragmentAdapter recommendAdapter;
    private TextView optenTime;
    private TextView adress;
    private TextView scienicName;
    private TextView weather;
    private ImageView gotoMap;
    private String longitude;
    private String latitude;
    private WeatherSearchQuery mquery;
    private WeatherSearch mweathersearch;
    private ScrollView scroll;
    private TextView pic;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scienic);

        Intent intent = getIntent();
       String id = intent.getStringExtra("id");

        url = url_header+id+url_later;

        Log.e(TAG, "onCreate: "+id );

        initView();
        setupDate();

    }

    private void setupDate() {
        RequestParams requestParams = new RequestParams(url);
        x.http().get(requestParams, new Callback.CommonCallback<String>() {


            @Override
            public void onSuccess(String result) {
                Log.e(TAG, "onSuccess: " );

                Gson gson = new Gson();
                Scienic scienic = gson.fromJson(result, Scienic.class);
                topTitle.setText( scienic.getContent().getScenicName());
                x.image().bind(coverimg,scienic.getContent().getCoverPic());
                scienicName.setText(scienic.getContent().getScenicName());
                optenTime.setText(scienic.getContent().getOpenTime());
                adress.setText(scienic.getContent().getAddress());

                latitude = scienic.getContent().getLatitude();
                longitude = scienic.getContent().getLongitude();
                gotoMap.setVisibility(View.VISIBLE);
                Weather(scienic.getContent().getCityName());
                int total = scienic.getContent().getStrategyList().getTotal();
                if (total!=0) {
                    recommend.setVisibility(View.VISIBLE);
                    List<Scienic.ContentBean.StrategyListBean.StrategyBean.PlayPointBean> playPoint = scienic.getContent().getStrategyList().getStrategy().get(0).getPlayPoint();
                    List<Fragment> data = new ArrayList<Fragment>();
                    for (int i = 0; i <playPoint.size() ; i++) {
                        Scienic.ContentBean.StrategyListBean.StrategyBean.PlayPointBean playPointBean = playPoint.get(i);

                        Bundle bundle = new Bundle();
                        bundle.putString("img",playPointBean.getImg());
                        bundle.putString("indexNum",playPointBean.getIndexNum());
                        bundle.putString("indexType",playPointBean.getIndexType());
                        bundle.putString("intro",playPointBean.getIntro());
                        bundle.putString("property",playPointBean.getProperty());
                        bundle.putString("propertyName",playPointBean.getPropertyName());
                        bundle.putString("title",playPointBean.getTitle());
                        bundle.putString("url",playPointBean.getUrl());
                        bundle.putString("id",playPointBean.getStrategyId());
                        RecommendFragment recommendFragment = new RecommendFragment();
                        recommendFragment.setArguments(bundle);
                        data.add(recommendFragment);

                    }

                    recommendAdapter.updateRes(data);


                }else {
                    recommend.setVisibility(View.GONE);
                }


            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e(TAG, "onError: " );
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

        back = (ImageView) findViewById(R.id.activity_scienic_top_back);
     back.setOnClickListener(this);
        share = (ImageView) findViewById(R.id.activity_scienic_top_share);
        topTitle = (TextView) findViewById(R.id.activity_scienic_top_title);
        coverimg = (ImageView) findViewById(R.id.activity_scienic_top_coverimg);
        optenTime = (TextView) findViewById(R.id.activity_scienic_open_time);
        adress = (TextView) findViewById(R.id.activity_scienic_adress);
        scienicName = (TextView) findViewById(R.id.activity_scienic_name);
        weather = (TextView) findViewById(R.id.activity_scienic_weather);
        gotoMap = (ImageView) findViewById(R.id.activity_scienic_map);
        pic = (TextView) findViewById(R.id.activity_scienic_pic);
        pic.setOnClickListener(this);
        gotoMap.setOnClickListener(this);
        gotoMap.setVisibility(View.GONE);


        scroll = (ScrollView) findViewById(R.id.activity_scienic_scrollview);

        scroll.setOnScrollChangeListener(this);



        recommend = (ViewPager) findViewById(R.id.activity_scienic_recommend);
        recommendAdapter = new RecommendViewPagerFragmentAdapter(getSupportFragmentManager(),null);
        recommend.setAdapter(recommendAdapter);


    }

    public  void Weather(String city){
        mquery = new WeatherSearchQuery(city, WeatherSearchQuery.WEATHER_TYPE_LIVE);
        mweathersearch = new WeatherSearch(this);
        mweathersearch.setOnWeatherSearchListener(this);
        mweathersearch.setQuery(mquery);
        mweathersearch.searchWeatherAsyn(); //异步搜索

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_scienic_map:
                Intent intent = new Intent(this, LocationActivity.class);
                intent.putExtra("latitude",latitude);
                intent.putExtra("longitude",longitude);
                startActivity(intent);

                break;
            case R.id.activity_scienic_top_back:
                finish();
                break;
            case R.id.activity_scienic_pic:
                Intent picIntent = new Intent(this, PictureActivity.class);
                picIntent.putExtra("url",url);
                startActivity(picIntent);

                break;


        }
    }

    @Override
    public void onWeatherLiveSearched(LocalWeatherLiveResult weatherLiveResult ,int rCode) {
        if (rCode == 1000) {
            if (weatherLiveResult != null && weatherLiveResult.getLiveResult() != null) {
                LocalWeatherLive liveResult = weatherLiveResult.getLiveResult();
                weather.setText(liveResult.getWeather());
                Log.e(TAG, "onWeatherLiveSearched: ");
            }
        }
    }

    @Override
    public void onWeatherForecastSearched(LocalWeatherForecastResult localWeatherForecastResult, int i) {

    }

    @Override
    public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
        Log.e(TAG, "onScrollChange: "+scrollY+"*******"+oldScrollY );

        if (scrollY==0) {
            topTitle.setAlpha(0);
            back.setImageResource(R.mipmap.nav_bar_back_white_icon);
            share.setImageResource(R.mipmap.icon_dark_share);
        }

        if(scrollY>0&&scrollY<=400){
            float x=  (float) scrollY/(float)255;
         float y=  (float) 255.0/(float)400.0;
            Log.e(TAG, "onScrollChange: "+y );
            topTitle.setAlpha(y*x);
        }
        if (scrollY>400) {
            back.setImageResource(R.mipmap.nav_bar_back_icon);
            share.setImageResource(R.mipmap.nav_bar_share_icon);
        }
    }
}
