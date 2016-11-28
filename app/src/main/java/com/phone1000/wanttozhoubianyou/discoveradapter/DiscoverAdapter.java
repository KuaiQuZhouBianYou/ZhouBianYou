package com.phone1000.wanttozhoubianyou.discoveradapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.phone1000.wanttozhoubianyou.R;
import com.phone1000.wanttozhoubianyou.discovermodel.DiscoverModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/28.
 */
public class DiscoverAdapter extends BaseAdapter {

    private List<DiscoverModel> data;
    private LayoutInflater inflater;

    public DiscoverAdapter(Context context,List<DiscoverModel>data){
        inflater=LayoutInflater.from(context);
        if (data == null) {
            this.data=new ArrayList<>();
        }else {
            this.data=data;
        }
    }
    public void updataRes(List<DiscoverModel>data){
        if (data!=null) {
            this.data.clear();
            this.data.addAll(data);
            this.notifyDataSetChanged();
        }
    }
    @Override
    public int getCount() {
        return data!=null?data.size():0;
    }
    @Override
    public DiscoverModel getItem(int position) {
        return data.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder=null;
        if (convertView == null) {
            convertView=inflater.inflate(R.layout.discover_item,parent,false);
            holder=new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        //加载数据
        holder.title.setText(getItem(position).getTitle());
        holder.subtitle.setError(getItem(position).getSubTitle());
        return convertView;
    }
    public static class ViewHolder{
        ImageView image;
        TextView title;
        TextView subtitle;
        public ViewHolder(View itemView){
            image= (ImageView) itemView.findViewById(R.id.discover_item_image);
            title= (TextView) itemView.findViewById(R.id.discover_item_title);
            subtitle= (TextView) itemView.findViewById(R.id.discover_item_subtitle);
        }
    }
}
