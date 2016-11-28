package com.phone1000.wanttozhoubianyou.adapter.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.phone1000.wanttozhoubianyou.R;
import com.phone1000.wanttozhoubianyou.model.home.Home;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016-11-28.
 */
public class HomeAdapter extends BaseAdapter {

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
        //加载数据
        holder.price.setText(getItem(position).getPrice());
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
        public ViewHolder(View itemView) {
            image = (ImageView) itemView.findViewById(R.id.home_item_image);
            price = (TextView) itemView.findViewById(R.id.home_item_price);
            oldprice = (TextView) itemView.findViewById(R.id.product_oldprice);
            productname = (TextView) itemView.findViewById(R.id.home_item_productname);
            statetext = (TextView) itemView.findViewById(R.id.home_item_statetext);
            cityname = (TextView) itemView.findViewById(R.id.home_item_cityname);
            labeltext = (TextView) itemView.findViewById(R.id.home_item_labeltext);
            saledcount = (TextView) itemView.findViewById(R.id.home_item_saledcount);

        }
    }
}
