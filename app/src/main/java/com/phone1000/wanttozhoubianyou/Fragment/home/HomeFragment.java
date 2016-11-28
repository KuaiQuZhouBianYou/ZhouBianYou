package com.phone1000.wanttozhoubianyou.Fragment.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.phone1000.wanttozhoubianyou.Fragment.BaseFragment;
import com.phone1000.wanttozhoubianyou.R;
import com.phone1000.wanttozhoubianyou.adapter.home.HomeAdapter;

import org.xutils.x;

/**
 * Created by Administrator on 2016/11/26.
 */
public class HomeFragment extends BaseFragment  {
    public View layout;
    public static final String TAG=HomeFragment.class.getSimpleName();
    private TextView location;
    private ListView mListView;
    private HomeAdapter adapter;
    // private AMapLocationClient aMapLocationClient;
  //  private AMapLocationClientOption  mLocationOption;
   // public String cityname;
   // public String cityCode;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        layout = inflater.inflate(R.layout.fragment_home_activity,container,false);
        return layout;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

       // location();
        initView();
        setupView();
    }

    private void setupView() {
    }

    private void initView() {
       // location = ((TextView) layout.findViewById(R.id.fragment_home_top_location));
        mListView = (ListView) layout.findViewById(R.id.home_listview);
        adapter = new HomeAdapter(getActivity(),null);
        mListView.setAdapter(adapter);



    }
    /*private void location() {
        aMapLocationClient = new AMapLocationClient(getActivity().getApplicationContext());

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


    }*/
   /* @Override
    public void onLocationChanged(AMapLocation aMapLocation) {

        cityname = aMapLocation.getCity();
        cityCode = aMapLocation.getCityCode();
        location.setText(cityname);
    }*/

}
