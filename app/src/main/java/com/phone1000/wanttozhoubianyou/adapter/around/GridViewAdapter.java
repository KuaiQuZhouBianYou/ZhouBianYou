package com.phone1000.wanttozhoubianyou.adapter.around;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.phone1000.wanttozhoubianyou.R;
import com.phone1000.wanttozhoubianyou.model.around.aroundHost;

import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 落叶 on 2016-11-28.
 */
public class GridViewAdapter extends BaseAdapter {

    private LayoutInflater inflater;


        private List<aroundHost.ContentBean.HotScenicBean>data;
    public GridViewAdapter(Context context,List<aroundHost.ContentBean.HotScenicBean>data){
        inflater = LayoutInflater.from(context);
        if (data!=null) {
           this.data = data;
        }else {
            this.data = new ArrayList<>();
        }

    }
  public void  updateRes(List<aroundHost.ContentBean.HotScenicBean>data){

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
    public aroundHost.ContentBean.HotScenicBean getItem(int position) {
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
            convertView = inflater.inflate(R.layout.item_around_scenic,parent,false);
            holder=  new ViewHolder(convertView);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

       holder.name.setText(getItem(position).getScenicName());
        x.image().bind(holder.img,getItem(position).getMudiPic());



        return convertView;
    }



    class ViewHolder{

               ImageView img ;
               TextView name;

        public ViewHolder(View view) {
           img = (ImageView) view.findViewById(R.id.item_around_scenic_img);
            name = (TextView) view.findViewById(R.id.item_around_scenic_name);
        }
    }












}
