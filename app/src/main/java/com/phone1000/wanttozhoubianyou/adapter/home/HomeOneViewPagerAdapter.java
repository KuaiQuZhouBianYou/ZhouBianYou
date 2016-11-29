package com.phone1000.wanttozhoubianyou.adapter.home;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.phone1000.administrator.mylibrary.ImageLoader;
import com.phone1000.wanttozhoubianyou.model.home.HomeOneVP;



import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016-11-29.
 */
public class HomeOneViewPagerAdapter extends PagerAdapter {

    private List<HomeOneVP> data;
    private Context context;

    public HomeOneViewPagerAdapter(Context context,List<HomeOneVP> data) {
        this.context =context;
        if (data!=null) {
            this.data = data;
        }else {
            this.data = new ArrayList<>();
        }
    }

    //更新数据源
    public void updateRes(List<HomeOneVP> data) {
        // this.title = title;
        if (data != null) {
            //清除旧数据
            this.data.clear();
            //添加新数据
            this.data.addAll(data);
            //通知适配器刷新
            notifyDataSetChanged();
        }
    }


    @Override
    public int getCount() {
        return data!=null?data.size():0;
    }

    @Override
    public ImageView instantiateItem(ViewGroup container, int position) {

        ImageView imageView = new ImageView(context);
        ImageLoader.display(imageView,data.get(position).getNew_app_picpath());
        container.addView(imageView);
        return imageView;
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        Log.i("TAG","object++++++++++"+object);
        // container.removeView(imageView);
    }


    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }
}
