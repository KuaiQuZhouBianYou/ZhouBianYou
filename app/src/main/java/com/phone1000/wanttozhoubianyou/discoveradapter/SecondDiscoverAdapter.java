package com.phone1000.wanttozhoubianyou.discoveradapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.phone1000.wanttozhoubianyou.R;
import com.phone1000.wanttozhoubianyou.discovermodel.SecondDiscoverModel;
import org.xutils.x;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/28.
 */
public class SecondDiscoverAdapter extends BaseAdapter {

    private List<SecondDiscoverModel> data;
    private LayoutInflater inflater;

    public SecondDiscoverAdapter(Context context,List<SecondDiscoverModel>data){
        inflater=LayoutInflater.from(context);
        if (data == null) {
            this.data=new ArrayList<>();
        }else {
            this.data=data;
        }
    }
    public void updataRes(List<SecondDiscoverModel>data){
        this.data.clear();
        this.data.addAll(data);
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return data!=null?data.size():0;
    }

    @Override
    public SecondDiscoverModel getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if (viewHolder == null) {
            convertView=inflater.inflate(R.layout.second_discover_item,parent,false);
            viewHolder=new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        //加载数据
        viewHolder.cityName.setText(data.get(position).getCityName());
        viewHolder.price.setText(data.get(position).getPrice()+"");
        viewHolder.productTitleContent.setText(data.get(position).getProductTitleContent());
        viewHolder.productName.setText(data.get(position).getProductName());
        viewHolder.saledCount.setText(data.get(position).getSaledCount()+"");
        //x.image().bind(viewHolder.image,data.get(position).getmImageUrl());
        return convertView;
    }
    public static class ViewHolder{
        ImageView image;
        TextView cityName;
        TextView price;
        TextView productName;
        TextView productTitleContent;
        TextView saledCount;
        public ViewHolder(View itemView){
            image= (ImageView) itemView.findViewById(R.id.second_discover_item_iamge);
            cityName= (TextView) itemView.findViewById(R.id.second_discover_item_cityName);
            price= (TextView) itemView.findViewById(R.id.second_discover_item_price);
            productName= (TextView) itemView.findViewById(R.id.second_discover_item_productName);
            productTitleContent= (TextView) itemView.findViewById(R.id.second_discover_item_productTitleContent);
            saledCount= (TextView) itemView.findViewById(R.id.second_discover_item_saledCount);
        }
    }

}
