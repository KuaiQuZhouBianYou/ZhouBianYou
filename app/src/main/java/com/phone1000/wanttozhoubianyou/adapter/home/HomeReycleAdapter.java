package com.phone1000.wanttozhoubianyou.adapter.home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.phone1000.wanttozhoubianyou.R;
import com.phone1000.wanttozhoubianyou.model.home.Home;
import com.phone1000.wanttozhoubianyou.model.home.HomeHeadRecycle;

import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016-11-29.
 */
public class HomeReycleAdapter extends RecyclerView.Adapter<HomeReycleAdapter.ViewHolder> {

    private List<HomeHeadRecycle> data;
    private LayoutInflater inflater;
    private RecyclerView mRecyclerView;

    public HomeReycleAdapter(Context context,List<HomeHeadRecycle> data) {
        inflater = LayoutInflater.from(context);
        if (data!=null) {
            this.data = data;
        }else {
            this.data =new ArrayList<>();
        }
    }

    public void updateRed(List<HomeHeadRecycle> data){
        if(data!=null){
            this.data.clear();
            this.data.addAll(data);
            notifyDataSetChanged();
        }
    }

    /**
     * 适配器依附在RecycleView上  适配器绑定RecyclerView
     */
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        mRecyclerView = recyclerView;
    }

    /**
     * 解除依附
     */
    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        mRecyclerView = null;
    }

    @Override
    public int getItemCount() {
        return data!=null?data.size():0;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.home_list_item_head_recyle_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.title.setText(data.get(position).getScenicName());
        x.image().bind(holder.image,data.get(position).getMudiPic());
    }

    public static  class  ViewHolder extends RecyclerView.ViewHolder{

        TextView title;
        ImageView image;

        public ViewHolder(View itemView) {
            super(itemView);
            //name = (TextView) itemView.findViewById(R.id.ho);
            title = (TextView) itemView.findViewById(R.id.home_head_vp_one_title);
            image = (ImageView) itemView.findViewById(R.id.home_head_vp_one_image);
        }
    }
}
