package com.phone1000.wanttozhoubianyou.adapter.around;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.phone1000.wanttozhoubianyou.R;
import com.phone1000.wanttozhoubianyou.model.around.aroundCityName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 落叶 on 2016-11-28.
 */
public class GridViewareaAdapter extends BaseAdapter {

    private LayoutInflater inflater;


        private List<aroundCityName.ContentBean>data;


          private   int num ;
    public GridViewareaAdapter(Context context, List<aroundCityName.ContentBean> data){
        inflater = LayoutInflater.from(context);
        if (data!=null) {
           this.data = data;

        }else {
            this.data = new ArrayList<>();
        }

    }
  public void  updateRes(List<aroundCityName.ContentBean>data){

      if (data!=null) {
          this.data.clear();
          this.data.addAll(data);
          notifyDataSetChanged();
      }

  }
    public void  setSlection(int num){
        this.num = num;
        notifyDataSetChanged();

    }



    @Override
    public int getCount() {
        return data!=null?data.size():0;
    }

    @Override
    public aroundCityName.ContentBean getItem(int position) {
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
        if (num==position){
            holder.name.setTextColor(Color.RED);
        }
       holder.name.setText(getItem(position).getName());




        return convertView;
    }



    class ViewHolder{


               TextView name;

        public ViewHolder(View view) {
            name = (TextView) view.findViewById(R.id.around_city_name);
        }
    }












}
