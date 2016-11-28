package com.phone1000.wanttozhoubianyou.Fragment.around;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.phone1000.wanttozhoubianyou.Fragment.BaseFragment;
import com.phone1000.wanttozhoubianyou.R;
import com.phone1000.wanttozhoubianyou.adapter.around.ViewPagerFragmentAdapter;
import com.phone1000.wanttozhoubianyou.constant.around.aroundUrl;
import com.phone1000.wanttozhoubianyou.model.around.aroundCityName;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;


/**
 *
 */


public class AroundFragment extends BaseFragment {
    public View layout;
    public static final String TAG=AroundFragment.class.getSimpleName();
    private TabLayout mTabLayout;
    private ViewPager mViewPage;
    private   List<aroundCityName.ContentBean> content;
    private List<String>titles;
    private ViewPagerFragmentAdapter adapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        layout = inflater.inflate(R.layout.fragment_around_activity,container,false);
        return layout;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getTitleDate();
        initView();
    }

    private void initView() {
        mTabLayout = ((TabLayout) layout.findViewById(R.id.fragment_around_toobar));
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        mViewPage = ((ViewPager) layout.findViewById(R.id.fragment_around_viewpager));
        adapter = new ViewPagerFragmentAdapter(getChildFragmentManager(), null,null);
        mViewPage.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPage);

    }

    public List<Fragment> getFragmentData() {
        List<Fragment> data = new ArrayList<>();
        Log.e(TAG, "getFragmentData: "+content.size() );

        HostFragment hostFragment = new HostFragment();
        data.add(hostFragment);

        for (int i = 1; i <content.size() ; i++) {
            CityFragment fragment = new CityFragment();

            Bundle bundle = new Bundle();
            bundle.putString("areaCode",content.get(i).getAreaCode()+"");
            fragment.setArguments(bundle);

            data.add(fragment);

        }

        return data;
    }

    public void getTitleDate() {
       titles=new ArrayList<>();
        RequestParams requestParams = new RequestParams(aroundUrl.AROUND_HEADER_TAB);
        x.http().get(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                aroundCityName aroundCityName = gson.fromJson(result, aroundCityName.class);
                content = aroundCityName.getContent();
                for (aroundCityName.ContentBean bean:content) {
                    titles.add(bean.getName());
                    Log.e(TAG, "onSuccess: "+bean.getName() );
                }

                adapter.updateRes(getFragmentData(),titles);

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
}
