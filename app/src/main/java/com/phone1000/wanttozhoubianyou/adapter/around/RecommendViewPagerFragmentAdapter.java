package com.phone1000.wanttozhoubianyou.adapter.around;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/13.
 */
public class RecommendViewPagerFragmentAdapter extends FragmentPagerAdapter {
    List<Fragment>data;
    public RecommendViewPagerFragmentAdapter(FragmentManager fm, List<Fragment>data) {
        super(fm);
        if (data == null) {
            this.data=new ArrayList<>();
        }else {
            this.data=data;
        }

    }
    public void updateRes(List<Fragment> data){
        if (data != null) {
            this.data.clear();
            this.data.addAll(data);
            notifyDataSetChanged();
        }
    }

    @Override
    public Fragment getItem(int position) {
        return data.get(position);
    }

    @Override
    public int getCount() {
        return data!=null?data.size():0;
    }
}
