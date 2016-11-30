package com.phone1000.wanttozhoubianyou.adapter.home;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016-11-29.
 */
public class HomeTwoPagerAdapter extends FragmentPagerAdapter{
    private List<Fragment> data;

    public HomeTwoPagerAdapter(FragmentManager fm,List<Fragment> data) {
        super(fm);
        if (data!=null) {
            this.data = data;
        }else {
            this.data = new ArrayList<>();
        }

    }
    public void updateRed(List<Fragment> data){
        if (data!=null){
            this.data.clear();
            this.data = data ;
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
