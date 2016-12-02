package com.phone1000.wanttozhoubianyou.adapter.home;

import android.content.Context;

import com.phone1000.administrator.mylibrary.adapter.MyBasaAdapter;
import com.phone1000.wanttozhoubianyou.R;
import com.phone1000.wanttozhoubianyou.model.home.HomeHeadRecycle;

import java.util.List;

/**
 * Created by Administrator on 2016-11-29.
 */
public class HomeFragmentVpAdapter extends MyBasaAdapter<HomeHeadRecycle> {

    public HomeFragmentVpAdapter(Context context, List<HomeHeadRecycle> data, int layoutResId) {
        super(context, data, layoutResId);
    }

    @Override
    protected void bindData(ViewHolder holder, HomeHeadRecycle item, int position) {
        holder.setText(R.id.home_head_vp_one_title,getItem(position).getScenicName());
        holder.setImage(R.id.home_head_vp_one_image,getItem(position).getMudiPic());
    }
}
