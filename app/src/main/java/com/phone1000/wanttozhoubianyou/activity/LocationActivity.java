package com.phone1000.wanttozhoubianyou.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.phone1000.wanttozhoubianyou.R;

/**
 * Created by 落叶 on 2016-12-01.
 */
public  class LocationActivity extends AppCompatActivity {


    private static final String TAG = LocationActivity.class.getSimpleName();


    private MapView mMapView;
    private AMap aMap;
    private UiSettings uiSettings;

    private double longitude;
    private double latitude;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);


        Intent intent = getIntent();
        latitude = Double.parseDouble(intent.getStringExtra("latitude"));
        longitude = Double.parseDouble(intent.getStringExtra("longitude"));

        Log.e(TAG, "onCreate: "+latitude );

        //获取地图控件引用
        mMapView = (MapView) findViewById(R.id.mapview);
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，实现地图生命周期管理
        mMapView.onCreate(savedInstanceState);

       initView();

    }

    private void initView() {

        if (aMap == null) {
            aMap = mMapView.getMap();
        }
        aMap.setMapType(AMap.MAP_TYPE_NORMAL);
        uiSettings = aMap.getUiSettings();
        uiSettings.setZoomControlsEnabled(true);
        CameraUpdateFactory.newCameraPosition(new CameraPosition(
                new LatLng(latitude,longitude),//新的中心点坐标
                12, //新的缩放级别
                0, //俯仰角0°~45°（垂直与地图时为0）
                0  ////偏航角 0~360° (正北方为0)
        ));
       /* mMapView.setBuiltInZoomControls(true); // 设置启用内置的缩放控件
        mMapController = mMapView.getController(); // 得到mMapView
        // 的控制权,可以用它控制和驱动平移和缩放
        point = new GeoPoint((int) (39.982378 * 1E6), (int) (116.304923 * 1E6));*/

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
    }
    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，实现地图生命周期管理
        mMapView.onSaveInstanceState(outState);
    }





}
