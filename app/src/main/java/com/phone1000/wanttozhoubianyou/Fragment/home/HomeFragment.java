package com.phone1000.wanttozhoubianyou.Fragment.home;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.phone1000.wanttozhoubianyou.Fragment.BaseFragment;
import com.phone1000.wanttozhoubianyou.R;
import com.phone1000.wanttozhoubianyou.adapter.home.HomeAdapter;
import com.phone1000.wanttozhoubianyou.adapter.home.HomeHeadGridAdapter;
import com.phone1000.wanttozhoubianyou.adapter.home.HomeOneViewPagerAdapter;
import com.phone1000.wanttozhoubianyou.adapter.home.HomeReycleAdapter;
import com.phone1000.wanttozhoubianyou.adapter.home.HomeTwoPagerAdapter;
import com.phone1000.wanttozhoubianyou.model.home.Home;
import com.phone1000.wanttozhoubianyou.model.home.HomeHeadGrid;
import com.phone1000.wanttozhoubianyou.model.home.HomeHeadRecycle;
import com.phone1000.wanttozhoubianyou.model.home.HomeOneVP;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/26.
 */
public class HomeFragment extends BaseFragment implements PullToRefreshBase.OnRefreshListener2 {
    private static final java.lang.String URL_PATH = "http://apiphp.yaochufa.com/playpoint/qualitySimple?system=android&pageIndex=1&cityCode=370200&channel=yaochufa&imei=ffffffff-8f6e-f1b6-197c-94460033c587&regId=170976fa8a8e9eae6b4&lang=app&userId=0&version=5.5.1&currentCityCode=370200&deviceToken=ffffffff-8f6e-f1b6-197c-94460033c587&latitude=36.084319&longitude=120.371082";
    private static final java.lang.String URL_PATH_ONE = "http://apiphp.yaochufa.com/playpoint/qualitySimple?system=android&pageIndex=";
    private static final java.lang.String URL_PATH_TWO = "&cityCode=370200&channel=yaochufa&imei=ffffffff-8f6e-f1b6-197c-94460033c587&regId=170976fa8a8e9eae6b4&lang=app&userId=0&version=5.5.1&currentCityCode=370200&deviceToken=ffffffff-8f6e-f1b6-197c-94460033c587&latitude=36.084319&longitude=120.371082";
    private static final java.lang.String URL_PATH_HEAD = "http://apiphp.yaochufa.com/you/advertiselist/AdList?system=android&area_code=370200&column=bannerRound%2CbannerScroll%2CbannerSquare%2CAPPFind%2CappSuspend%2CappBanner%2CappHeadlines&channel=yaochufa&imei=ffffffff-8f6e-f1b6-197c-94460033c587&regId=170976fa8a8e9eae6b4&lang=app&version=5.5.1&deviceToken=ffffffff-8f6e-f1b6-197c-94460033c587";
    private static final java.lang.String URL_PATH_TWO_VP = "http://apiphp.yaochufa.com/portal/dest/scenicCity?system=android&channel=yaochufa&imei=ffffffff-8f6e-f1b6-197c-94460033c587&regId=170976fa8a8e9eae6b4&lang=app&version=5.5.1&deviceToken=ffffffff-8f6e-f1b6-197c-94460033c587&areaCode=370200&listType=around";

    public View layout;
    public static final String TAG=HomeFragment.class.getSimpleName();
    private TextView location;
    private ListView mListView;
    private HomeAdapter adapter;
    private View mHead;
    private ViewPager mViewPager;
    private LinearLayout mHomeLlPoint;
    private HomeOneViewPagerAdapter mHeadOneVP;
    private List<HomeOneVP> data;
    private List<Fragment> dataFrg;
    private List<HomeHeadRecycle> dataVp;
    private static int pageIndex = 1;
   // PullToRefreshListView

    private int prevIndex = 0;
    private int pointCount = 0;
    // private AMapLocationClient aMapLocationClient;
  //  private AMapLocationClientOption  mLocationOption;
   // public String cityname;
   // public String cityCode;
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0x100:
                    // 得到mVp当前页面的索引
                    int currentItem = prevIndex;
                    // 要显示的下一个页面的索引
                    currentItem++;
                    // 设置ViewPager显示的页面
                    mViewPager.setCurrentItem(currentItem % data.size());
                    break;
            }
        };
    };
    private GridView mGridView;
    private HomeHeadGridAdapter mGridAdapter;
    private ViewPager mViewPagerTwo;
    private LinearLayout mHomeTwoLlPoint;
    private HomeTwoPagerAdapter mViewPagerTwoAdapter;
    private RecyclerView mRecycle;
    private HomeReycleAdapter recycleAdapter;
    private PullToRefreshListView mRefreshListView;
    private View mFood;

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
        getDataFromNet(State.DOWM);
    }

    private void getDataFromNet(final State state) {
      /*  if (pageIndex==2){
          //  mRefreshListView.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
            mRefreshListView.setFinish(true);

            mFood = LayoutInflater.from(getActivity()).inflate(R.layout.home_list_item_bottom,null);
            mListView.addFooterView(mFood);
            Log.e(TAG, "getDataFromNet: ***********************");
            mRefreshListView.onRefreshComplete();
            return;
        }*/
        switch (state) {
            case DOWM:
                pageIndex = 1;
                break;
            case UP:
                pageIndex = pageIndex + 1;
                Log.e(TAG, "getDataFromNet: " + pageIndex);

                break;
        }
        RequestParams params = new RequestParams(URL_PATH_ONE + pageIndex + URL_PATH_TWO);
        x.http().get(params, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String result) {
                Log.e(TAG, "onSuccess: " +result);

                try {
                    JSONObject jsonObject = new JSONObject(result);
                    JSONObject content = jsonObject.getJSONObject("content");
                    JSONArray array = content.getJSONArray("content");

                    Type type = new TypeToken<List<Home>>() {
                    }.getType();
                    Gson gson = new Gson();
                    List<Home> data = gson.fromJson(array.toString(),type);
                    Log.e(TAG, "onSuccess: " + data.size());
                    Log.e(TAG, "onSuccess: " + data.get(0).getPrice());
                    switch (state) {
                        case DOWM:
                            adapter.updateRes(data);
                            break;
                        case UP:
                            adapter.addRes(data);
                            break;
                    }
                    //调用刷新完成
                    if (pageIndex ==2){
                        //  mRefreshListView.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
                        mRefreshListView.setFinish(true);
                        mFood = LayoutInflater.from(getActivity()).inflate(R.layout.home_list_item_bottom,null);
                        mListView.addFooterView(mFood);
                    }
                    mRefreshListView.onRefreshComplete();
                    Log.e(TAG, "onSuccess:---------------------------- " );
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e(TAG, "onError: ");
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Log.e(TAG, "onCancelled: " );
            }

            @Override
            public void onFinished() {
                Log.e(TAG, "onFinished: ");
            }
        });
    }

    private void setupView() {

        //加载viewpager数据
        RequestParams paramsHead = new RequestParams(URL_PATH_HEAD);
        x.http().get(paramsHead, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String result) {
                Log.e(TAG, "onSuccess: " +result);

                try {
                    data = new ArrayList<HomeOneVP>();
                    JSONObject jsonObject = new JSONObject(result);
                    JSONArray array = jsonObject.getJSONArray("content");
                    //获取ViewPager的数据
                    JSONObject object = (JSONObject) array.get(0);
                    JSONArray ad = object.getJSONArray("ad");
                    for (int i = 0; i < ad.length(); i++) {
                        JSONObject obj = (JSONObject) ad.get(i);
                        JSONObject ct = obj.getJSONObject("ct");
                        HomeOneVP homeOneVP = new HomeOneVP();
                        homeOneVP.setApp_url(ct.getString("app_url"));
                        homeOneVP.setNew_app_picpath(ct.getString("app_picpath"));
                        data.add(homeOneVP);
                    }
                    for (int i = 0; i < data.size(); i++) {
                        //加在变色的点
                        initPoint();
                    }
                    //为ViewPager添加 数据
                    pointCount =data.size();
                    mHomeLlPoint.getChildAt(prevIndex).setBackgroundResource(R.mipmap.home_switching_dot_current);
                    mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                        @Override
                        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                            mHomeLlPoint.getChildAt(prevIndex).setBackgroundResource(R.mipmap.home_switching_dot);
                            mHomeLlPoint.getChildAt(position).setBackgroundResource(R.mipmap.home_switching_dot_current);

                            prevIndex = position;
                        }

                        @Override
                        public void onPageSelected(int position) {

                        }

                        @Override
                        public void onPageScrollStateChanged(int state) {

                        }
                    });
                    // 设置是否进行自动轮播
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            while (true) {
                                SystemClock.sleep(3000);
                                handler.sendEmptyMessage(0x100);
                            }
                        }
                    }).start();
                    mHeadOneVP.updateRes(data);

                    JSONObject objectGrid = (JSONObject) array.get(1);
                    JSONArray jsonArray = objectGrid.getJSONArray("ad");
                    List<HomeHeadGrid> dataGrid = new ArrayList<HomeHeadGrid>();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject obj = (JSONObject) jsonArray.get(i);
                        JSONObject cthead = obj.getJSONObject("ct");
                        HomeHeadGrid homeHeadGrid = new HomeHeadGrid();
                        homeHeadGrid.setTitle(cthead.getString("title"));
                        homeHeadGrid.setApp_picpath(cthead.getString("app_picpath"));
                        dataGrid.add(homeHeadGrid);
                    }
                    mGridAdapter.updateRes(dataGrid);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e(TAG, "onError: ");
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Log.e(TAG, "onCancelled: " );
            }

            @Override
            public void onFinished() {
                Log.e(TAG, "onFinished: ");
            }
        });
        //加载RecycleView的数据
        RequestParams paramsVp = new RequestParams(URL_PATH_TWO_VP);
        x.http().get(paramsVp, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String result) {
                Log.e(TAG, "onSuccess: " + result);

                try {
                    JSONObject jsonObject = new JSONObject(result);
                    JSONObject content = jsonObject.getJSONObject("content");

                    JSONArray array = content.getJSONArray("hotScenic");

                    Type type = new TypeToken<List<HomeHeadRecycle>>() {
                    }.getType();
                    Gson gson = new Gson();
                    dataVp = gson.fromJson(array.toString(), type);
                    recycleAdapter.updateRed(dataVp);


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e(TAG, "onError: ");
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Log.e(TAG, "onCancelled: ");
            }

            @Override
            public void onFinished() {
                Log.e(TAG, "onFinished: ");
            }
        });

    }
    //加点
    private void initPoint() {
        View view = new View(getActivity());
        view.setBackgroundResource(R.mipmap.home_switching_dot);

        int width = 20;
        int height = width;

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, height);
        params.leftMargin = 20;
        view.setLayoutParams(params);

        mHomeLlPoint.addView(view);
    }

    private void initView() {
       // location = ((TextView) layout.findViewById(R.id.fragment_home_top_location));
       // mListView = (ListView) layout.findViewById(R.id.home_listview);
        mRefreshListView = (PullToRefreshListView) layout.findViewById(R.id.home_listview);
        mRefreshListView.setOnRefreshListener(this);

        //可以通过refreshListView 获得刷新的头
        ILoadingLayout loadingStar = mRefreshListView.getLoadingLayoutProxy(true,false);
        // loadingStar.setLastUpdatedLabel("上次刷新时间:"+ lasttime);
        loadingStar.setPullLabel("继续下拉进入刷新");
        loadingStar.setReleaseLabel("释放刷新...");
        loadingStar.setRefreshingLabel("正在刷新...");

        //获得刷新尾
        ILoadingLayout loadingEnd = mRefreshListView.getLoadingLayoutProxy(false, true);
        //loadingEnd.setLastUpdatedLabel("即将为您刷新20条记录");
        loadingEnd.setPullLabel("继续上拉进入刷新");
        loadingEnd.setReleaseLabel("释放刷新...");
        loadingEnd.setRefreshingLabel("正在刷新...");

        mRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);
        mListView = mRefreshListView.getRefreshableView();

        adapter = new HomeAdapter(getActivity(),null);
        mListView.setAdapter(adapter);
        //加载头部
        mHead = LayoutInflater.from(getActivity()).inflate(R.layout.home_list_item_head,null);
        //加载第一个viewPager
        mViewPager = (ViewPager) mHead.findViewById(R.id.home_header_vp);
        mHomeLlPoint = (LinearLayout) mHead.findViewById(R.id.home_header_ll_points);
        //加载头部的gridview
        mGridView = (GridView) mHead.findViewById(R.id.home_head_grid);
        //加载第二个viewPage
        mRecycle = (RecyclerView) mHead.findViewById(R.id.home_head_recycle);
        mListView.addHeaderView(mHead);


        //为ViewPager绑定适配器
        mHeadOneVP = new HomeOneViewPagerAdapter(getActivity(),null);
        mViewPager.setAdapter(mHeadOneVP);
        //为GridView绑定适配器
        mGridAdapter = new HomeHeadGridAdapter(getActivity(),null,R.layout.home_list_item_head_griditem);
        mGridView.setAdapter(mGridAdapter);
        //为RecycleView绑定适配器
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL,false);
        mRecycle.setLayoutManager(layoutManager);
        recycleAdapter = new HomeReycleAdapter(getActivity(),null);
        mRecycle.setAdapter(recycleAdapter);

    }

    /**
     * 枚举  只能有下拉 上拉
     *
     */
    enum State{
        DOWM,UP
    }



    /**
     * 下拉刷新的回调
     * @param refreshView
     */
    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
        Log.e(TAG, "onPullDownToRefresh: " );
        getDataFromNet(State.DOWM);

    }

    /**
     * 上拉加载的回调函数
     * @param refreshView
     */
    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {
        Log.e(TAG, "onPullUpToRefresh: " );
        getDataFromNet(State.UP);

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
