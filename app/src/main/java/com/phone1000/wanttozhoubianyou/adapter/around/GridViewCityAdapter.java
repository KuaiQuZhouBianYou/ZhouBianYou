package com.phone1000.wanttozhoubianyou.adapter.around;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.phone1000.wanttozhoubianyou.R;
import com.phone1000.wanttozhoubianyou.model.around.aroundHost;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 落叶 on 2016-11-28.
 */
public class GridViewCityAdapter extends BaseAdapter {

    private LayoutInflater inflater;


        private List<aroundHost.ContentBean.HotCityBean>data;
    public GridViewCityAdapter(Context context, List<aroundHost.ContentBean.HotCityBean>data){
        inflater = LayoutInflater.from(context);
        if (data!=null) {
           this.data = data;
        }else {
            this.data = new ArrayList<>();
        }

    }
  public void  updateRes(List<aroundHost.ContentBean.HotCityBean>data){

      if (data!=null) {
          this.data.clear();
          this.data.addAll(data);
          notifyDataSetChanged();
      }

  }



    @Override
    public int getCount() {
        return data!=null?data.size():0;
    }

    @Override
    public aroundHost.ContentBean.HotCityBean getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView==null) {
            convertView = inflater.inflate(R.layout.item_around_host_city,parent,false);
            holder=  new ViewHolder(convertView);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

       holder.name.setText(getItem(position).getCityName());




        return convertView;
    }



    class ViewHolder{


               TextView name;

        public ViewHolder(View view) {
            name = (TextView) view.findViewById(R.id.around_city_name);
        }
    }












}
