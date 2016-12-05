package com.phone1000.wanttozhoubianyou.Fragment.around;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.google.gson.Gson;
import com.phone1000.wanttozhoubianyou.Fragment.BaseFragment;
import com.phone1000.wanttozhoubianyou.R;
import com.phone1000.wanttozhoubianyou.activity.around.ScienicActivity;
import com.phone1000.wanttozhoubianyou.adapter.around.GridViewAdapter;
import com.phone1000.wanttozhoubianyou.adapter.around.GridViewtwoAdapter;
import com.phone1000.wanttozhoubianyou.constant.around.aroundUrl;
import com.phone1000.wanttozhoubianyou.model.around.aroundHost;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

/**
 * Created by 落叶 on 2016-11-28.
 */
public class HostFragment extends BaseFragment implements AdapterView.OnItemClickListener {

    private static final String TAG = HostFragment.class.getSimpleName();

    private View layout;
    private GridView scenic;
    private GridViewAdapter gridViewAdapter;
    private GridView city;
    private GridViewtwoAdapter gridViewtwoAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        layout = inflater.inflate(R.layout.fragment_around_host_layout,container,false);


        return layout;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        setupData();
    }

    private void setupData() {
        RequestParams requestParams = new RequestParams(aroundUrl.AROUND_HOST);
        x.http().get(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

                Gson gson = new Gson();
                aroundHost aroundHost = gson.fromJson(result, aroundHost.class);
                List<aroundHost.ContentBean.HotScenicBean> hotScenic = aroundHost.getContent().getHotScenic();
                gridViewAdapter.updateRes(hotScenic);
                List<aroundHost.ContentBean.HotCityBean> hotCity = aroundHost.getContent().getHotCity();
                gridViewtwoAdapter.updateRes(hotCity);

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
        scenic = ((GridView) layout.findViewById(R.id.fragment_around_host_allhostscenic));
        scenic.setOnItemClickListener(this);
        gridViewAdapter = new GridViewAdapter(getActivity(),null);
        scenic.setAdapter(gridViewAdapter);
        city = ((GridView) layout.findViewById(R.id.fragment_around_host_allhostcity));
        gridViewtwoAdapter = new GridViewtwoAdapter(getActivity(),null);
        city.setAdapter(gridViewtwoAdapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
          case R.id.fragment_around_host_allhostscenic:
              aroundHost.ContentBean.HotScenicBean item = gridViewAdapter.getItem(position);
              String scenicId = item.getScenicId();
              Intent intent = new Intent(getActivity(), ScienicActivity.class);
              intent.putExtra("id",scenicId);
              startActivity(intent);


              break;
            case R.id.fragment_around_host_allhostcity:
                break;
        }
    }
}

















