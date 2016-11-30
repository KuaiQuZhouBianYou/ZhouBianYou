package com.phone1000.wanttozhoubianyou.Fragment.around;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.phone1000.wanttozhoubianyou.Fragment.BaseFragment;
import com.phone1000.wanttozhoubianyou.R;
import com.phone1000.wanttozhoubianyou.adapter.around.GridViewAdapter;
import com.phone1000.wanttozhoubianyou.adapter.around.GridViewCityAdapter;
import com.phone1000.wanttozhoubianyou.constant.around.aroundUrl;
import com.phone1000.wanttozhoubianyou.model.around.aroundHost;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

/**
 * Created by 落叶 on 2016-11-28.
 */
public class CityFragment extends BaseFragment {

    private String url;
    private View layout;
    private GridView scenic;
    private GridViewAdapter gridViewAdapter;
    private GridView city;
    private GridViewCityAdapter gridViewCityAdapter;
    private ImageView rim;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        layout = inflater.inflate(R.layout.fragment_around_city, container, false);
        return layout;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle bundle = getArguments();
        String areaCode = bundle.getString("areaCode");
        String listType = bundle.getString("listType");
        rim = ((ImageView) layout.findViewById(R.id.my_rim));
        if ("around".equals(listType)) {
         //&areaCode=370200&listType=around
            url = aroundUrl.AROUND_CITY+"&areaCode=370200&listType=around";
            rim.setVisibility(View.VISIBLE);
        }else{
              url = aroundUrl.AROUND_CITY+"&areaCode="+areaCode+"&listType="+listType;
            rim.setVisibility(View.GONE);
        }

        initView();
        setupDate();
    }

    private void setupDate() {

        RequestParams requestParams = new RequestParams(url);
        x.http().get(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

                Gson gson = new Gson();
                aroundHost aroundHost = gson.fromJson(result, aroundHost.class);
                List<aroundHost.ContentBean.HotScenicBean> hotScenic = aroundHost.getContent().getHotScenic();
                gridViewAdapter.updateRes(hotScenic);
                List<aroundHost.ContentBean.HotCityBean> hotCity = aroundHost.getContent().getHotCity();
                gridViewCityAdapter.updateRes(hotCity);

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
        scenic = ((GridView) layout.findViewById(R.id.fragment_around_allhostscenic));
        gridViewAdapter = new GridViewAdapter(getActivity(),null);
        scenic.setAdapter(gridViewAdapter);
        city = ((GridView) layout.findViewById(R.id.fragment_around_allhostcity));
        gridViewCityAdapter = new GridViewCityAdapter(getActivity(),null);
        city.setAdapter(gridViewCityAdapter);

    }
}
