package com.phone1000.wanttozhoubianyou.adapter.home;

import android.content.Context;

import com.phone1000.administrator.mylibrary.adapter.MyBasaAdapter;
import com.phone1000.wanttozhoubianyou.R;
import com.phone1000.wanttozhoubianyou.model.home.HomeHeadGrid;

import java.util.List;

/**
 * Created by Administrator on 2016-11-29.
 */
public class HomeHeadGridAdapter extends MyBasaAdapter<HomeHeadGrid>{
    public HomeHeadGridAdapter(Context context, List<HomeHeadGrid> data, int layoutResId) {
        super(context, data, layoutResId);
    }

    @Override
    protected void bindData(ViewHolder holder, HomeHeadGrid item, int position) {
        holder.setText(R.id.home_head_grid_title,getItem(position).getTitle());
        holder.setImage(R.id.home_head_grid_image,getItem(position).getApp_picpath());
    }
}
