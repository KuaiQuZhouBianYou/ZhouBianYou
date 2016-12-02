package com.phone1000.wanttozhoubianyou;

import android.app.Application;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.services.weather.LocalWeatherLive;
import com.amap.api.services.weather.WeatherSearch;
import com.amap.api.services.weather.WeatherSearchQuery;
import com.phone1000.administrator.mylibrary.ImageLoader;

import org.xutils.x;

/**
 * Created by 落叶 on 2016-11-28.
 */
public class MyApp extends Application implements AMapLocationListener {


    private AMapLocationClient aMapLocationClient;
    private AMapLocationClientOption mLocationOption;
    private static String cityname;
    private String cityCode;
    private WeatherSearchQuery mquery;
    private WeatherSearch mweathersearch;
    private LocalWeatherLive liveResult;
    private static String weather;


    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.isDebug();
        ImageLoader.init(this);
        location();
    }


    private void location() {
        aMapLocationClient = new AMapLocationClient(getApplicationContext());

        aMapLocationClient.setLocationListener(this);

        mLocationOption = new AMapLocationClientOption();
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);
        //设置是否只定位一次,默认为false
        mLocationOption.setOnceLocation(false);
        //设置是否强制刷新WIFI，默认为强制刷新
        mLocationOption.setWifiActiveScan(true);
        //设置是否允许模拟位置,默认为false，不允许模拟位置
        mLocationOption.setMockEnable(false);
        //设置定位间隔,单位毫秒,默认为2000ms
        mLocationOption.setInterval(2000);
        //给定位客户端对象设置定位参数
        aMapLocationClient.setLocationOption(mLocationOption);
        //启动定位
        aMapLocationClient.startLocation();


    }
public static String getCityname(){

    return cityname;
}
    public static String getWeather(){

        return weather;
    }


    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        cityname = aMapLocation.getCity();
        cityCode = aMapLocation.getCityCode();

    }


}
