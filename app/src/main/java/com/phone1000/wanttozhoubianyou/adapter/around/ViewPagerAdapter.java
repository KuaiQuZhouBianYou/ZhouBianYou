package com.phone1000.wanttozhoubianyou.adapter.around;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import uk.co.senab.photoview.PhotoView;

/**
 * Created by 落叶 on 2016-12-02.
 */
public class ViewPagerAdapter extends PagerAdapter {

    List<PhotoView>data;

    public ViewPagerAdapter(List<PhotoView>data){
        if (data!=null) {
            this.data = data;
        }else {
           this.data = new ArrayList<>();
        }

    }
    public void updataRes(List<PhotoView>data){
        if (data!=null) {
            this.data.clear();
            this.data.addAll(data);
            notifyDataSetChanged();
        }

    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(data.get(position));
        return data.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
       container.removeView(data.get(position));
    }

    @Override
    public int getCount() {
        return data!=null?data.size():0;
    }

    @Override
    public boolean isViewFromObject(View view, Object o) {
        return view==o;
    }
}
