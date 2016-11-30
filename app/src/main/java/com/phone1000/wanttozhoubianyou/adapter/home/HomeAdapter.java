package com.phone1000.wanttozhoubianyou.adapter.home;

import android.content.Context;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.phone1000.wanttozhoubianyou.R;
import com.phone1000.wanttozhoubianyou.model.home.Home;

import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016-11-28.
 */
public class HomeAdapter extends BaseAdapter {

    private static final String TAG = HomeAdapter.class.getSimpleName();
    private List<Home> data;
    private LayoutInflater inflater;

    public HomeAdapter(Context context, List<Home> data) {
        inflater = LayoutInflater.from(context);
        if (data!=null) {
            this.data = data;
        }else {
            this.data  =  new ArrayList<>();
        }
    }

    public void  updateRes(List<Home> data){
        if (data!=null){
            this.data.clear();
            this.data = data;
            notifyDataSetChanged();
        }
    }
    public void addRes(List<Home> data){
        if (data!=null) {
            this.data.addAll(data);
            notifyDataSetChanged();
        }
    }

    @Override
    public int getCount() {
        return data!=null?data.size():0;
    }

    @Override
    public Home getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null){
            convertView = inflater.inflate(R.layout.home_list_item,parent,false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        if (position==0){
            holder.layout.setVisibility(View.VISIBLE);
        }else {
            holder.layout.setVisibility(View.GONE);
        }
        //加载数据
        Log.e(TAG, "getView:--------------- " +getItem(position).getPrice() );
        x.image().bind(holder.image,getItem(position).getBigImageUrl());
        holder.price.setText("￥"+getItem(position).getPrice()+"起");
        holder.oldprice.setText(getItem(position).getRetailPrice());
      //  holder.productname.setText(getItem(position).getProductName() + getItem(position).getProductTitleContent());
        holder.productname.setText(Html.fromHtml("<b><tt>"+getItem(position).getProductName()+" </tt></b>"+getItem(position).getProductTitleContent()));
        holder.statetext.setText(getItem(position).getStateText());
        if (getItem(position).getStateText() == null) {
            holder.statetext.setVisibility(View.GONE);
        }else {
            holder.statetext.setVisibility(View.VISIBLE);
            holder.statetext.setText(getItem(position).getStateText());
        }

        if (getItem(position).getCityName()==null){
            holder.cityname.setVisibility(View.GONE);
        }else {
            holder.cityname.setVisibility(View.VISIBLE);
            holder.cityname.setText(getItem(position).getCityName());
        }
        if (getItem(position).getLabelText()==null){
            holder.labeltext.setVisibility(View.GONE);
        }else {
            holder.labeltext.setVisibility(View.VISIBLE);
            holder.labeltext.setText(getItem(position).getLabelText());
        }
        holder.saledcount.setText("已售"+getItem(position).getSaledCount());
        return convertView;
    }

    private class ViewHolder{
        ImageView image;
        TextView price;
        TextView oldprice;
        TextView productname;
        TextView statetext;
        TextView cityname;
        TextView labeltext;
        TextView saledcount;
        LinearLayout layout;
        public ViewHolder(View itemView) {
            image = (ImageView) itemView.findViewById(R.id.home_item_image);
            price = (TextView) itemView.findViewById(R.id.home_item_price);
            oldprice = (TextView) itemView.findViewById(R.id.product_oldprice);
            productname = (TextView) itemView.findViewById(R.id.home_item_productname);
            statetext = (TextView) itemView.findViewById(R.id.home_item_statetext);
            cityname = (TextView) itemView.findViewById(R.id.home_item_cityname);
            labeltext = (TextView) itemView.findViewById(R.id.home_item_labeltext);
            saledcount = (TextView) itemView.findViewById(R.id.home_item_saledcount);
            layout = (LinearLayout) itemView.findViewById(R.id.item_top_layout);

        }
    }
}
