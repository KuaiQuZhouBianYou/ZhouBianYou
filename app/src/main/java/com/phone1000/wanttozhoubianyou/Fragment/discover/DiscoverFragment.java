package com.phone1000.wanttozhoubianyou.Fragment.discover;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.phone1000.wanttozhoubianyou.Fragment.BaseFragment;
import com.phone1000.wanttozhoubianyou.R;
import com.phone1000.wanttozhoubianyou.discoveractivity.SecondDsicoverActivity;
import com.phone1000.wanttozhoubianyou.discoveradapter.DiscoverAdapter;
import com.phone1000.wanttozhoubianyou.discovercontest.DiscoverContest;
import com.phone1000.wanttozhoubianyou.discovermodel.DiscoverModel;

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
public class DiscoverFragment extends BaseFragment implements AdapterView.OnItemClickListener {

    public View layout;
    public static final String TAG=DiscoverFragment.class.getSimpleName();
    private ListView mListView;
    private DiscoverAdapter adapter;
    private int id=475;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        layout = inflater.inflate(R.layout.fragment_discover_activity,container,false);
        return layout;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        //setData();
        getData();
    }

    private void setData() {
        List<DiscoverModel>data=new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            DiscoverModel discoverModel = new DiscoverModel();
            discoverModel.setTitle("管 "+i);
            data.add(discoverModel);
        }
        adapter.updataRes(data);
    }
    private void getData() {
        final List<DiscoverModel>datas=new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            RequestParams requestParams = new RequestParams(DiscoverContest.DISCOVER_URL1+id+DiscoverContest.DISCOVER_URL12);
            x.http().get(requestParams, new Callback.CommonCallback<String>() {
                @Override
                public void onSuccess(String result) {

                    //Log.e(TAG, Thread.currentThread().getName()+"onSuccess: 000000" );
                    try {
                        Gson gson = new Gson();
                        JSONObject jsonObject = new JSONObject(result);
                        JSONObject content = jsonObject.getJSONObject("content");
                        Type type = new TypeToken<DiscoverModel>() {
                        }.getType();
                        DiscoverModel data=gson.fromJson(content.toString(),type);
                        datas.add(data);
                        adapter.updataRes(datas);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                @Override
                public void onError(Throwable ex, boolean isOnCallback) {
                    Log.e(TAG, Thread.currentThread().getName()+"onError: 000000" );
                    Log.e(TAG, "onError: 000000"+ex.getMessage() );
                    Log.e(TAG, "onError: 000000"+ex.getCause() );
                }
                @Override
                public void onCancelled(CancelledException cex) {
                    Log.e(TAG, Thread.currentThread().getName()+"onCancelled: 000000" );
                }
                @Override
                public void onFinished() {
                    Log.e(TAG, Thread.currentThread().getName()+"onFinished: 000000" );
                }
            });
            id++;
        }


    }
    private void initView() {
        mListView = ((ListView) layout.findViewById(R.id.discover_lv));
        adapter = new DiscoverAdapter(getActivity(),null);
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(this);
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getActivity(), SecondDsicoverActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("themeId",adapter.getItem(position).getThemeId());
        intent.putExtras(bundle);
        startActivity(intent);
    }

}
