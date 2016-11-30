package com.phone1000.wanttozhoubianyou.Fragment.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.phone1000.wanttozhoubianyou.Fragment.BaseFragment;
import com.phone1000.wanttozhoubianyou.R;
import com.phone1000.wanttozhoubianyou.adapter.home.HomeFragmentVpAdapter;
import com.phone1000.wanttozhoubianyou.model.home.HomeHeadRecycle;

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
 * Created by Administrator on 2016-11-29.
 */
public class HomeFragmentVp extends BaseFragment {
    private static final java.lang.String URL_PATH_TWO_VP = "http://apiphp.yaochufa.com/portal/dest/scenicCity?system=android&channel=yaochufa&imei=ffffffff-8f6e-f1b6-197c-94460033c587&regId=170976fa8a8e9eae6b4&lang=app&version=5.5.1&deviceToken=ffffffff-8f6e-f1b6-197c-94460033c587&areaCode=370200&listType=around";
    private static final String TAG = HomeFragmentVp.class.getSimpleName();
    public View layout;
    private GridView mGridView;
    private HomeFragmentVpAdapter adapter;
    private List<HomeHeadRecycle> dataVp;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        layout = inflater.inflate(R.layout.home_list_item_head_vp_one, container, false);
        return layout;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        setupView();
    }

    private void setupView() {
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

                    Bundle bundle = getArguments();
                    int appid = bundle.getInt("appid");
                    Log.e(TAG, "onSuccess:--------------------------------------- " + appid);

                    List<HomeHeadRecycle> data =null;
                    if (appid == 0){
                       data = new ArrayList<HomeHeadRecycle>();
                        for (int i = 0; i < dataVp.size(); i++) {
                            if (i<3){
                                data.add(dataVp.get(i));
                            }
                        }
                    }
                    if (appid == 1){
                        data = new ArrayList<HomeHeadRecycle>();
                        for (int i = 0; i < dataVp.size(); i++) {
                            if (i>=3 || i<6){
                                data.add(dataVp.get(i));
                            }
                        }
                    }

                    /*for (int i = 0; i < dataVp.size(); i++) {
                        if (i >= appid * 3 || i < appid * 3 + 3) { //appid=0  0 -2  appid=1  3-5
                            data.add(dataVp.get(i));
                        }
                    }*/
                    adapter.updateRes(data);


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

    private void initView() {
        mGridView = (GridView) layout.findViewById(R.id.home_head_vp_grid);
        adapter = new HomeFragmentVpAdapter(getActivity(), null, R.layout.home_list_item_head_recyle_item);
        mGridView.setAdapter(adapter);
    }
}
