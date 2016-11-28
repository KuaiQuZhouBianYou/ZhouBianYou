package com.phone1000.wanttozhoubianyou.adapter.around;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/13.
 */
public class ViewPagerFragmentAdapter extends FragmentPagerAdapter {
    List<Fragment>data;
    List<String>titles;
    public ViewPagerFragmentAdapter(FragmentManager fm,List<Fragment>data,List<String>titles) {
        super(fm);
        if (data == null) {
            this.data=new ArrayList<>();
        }else {
            this.data=data;
        }
        if (titles == null) {
            this.titles=new ArrayList<>();
        }else {
            this.titles=titles;
        }
    }
    public void updateRes(List<Fragment> data, List<String> titles){
        if (data != null) {
            this.data.clear();
            this.data.addAll(data);
            this.titles .clear();
            this.titles.addAll(titles);
            notifyDataSetChanged();
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
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
