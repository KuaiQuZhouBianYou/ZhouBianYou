package com.phone1000.wanttozhoubianyou.activity.around;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.phone1000.wanttozhoubianyou.R;
import com.phone1000.wanttozhoubianyou.adapter.around.GridViewareaAdapter;
import com.phone1000.wanttozhoubianyou.constant.around.aroundUrl;
import com.phone1000.wanttozhoubianyou.model.around.aroundCityName;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

/**
 * Created by 落叶 on 2016-11-29.
 */
public class ChangeAreaActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    private static final String TAG =ChangeAreaActivity.class.getSimpleName() ;
    private ImageView back;
    private GridView gridView;
    private GridViewareaAdapter gridViewareaAdapter;
    private int position;
    private Intent intent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_change_area);
        intent = getIntent();
        position = intent.getIntExtra("position", 0);
        Log.e(TAG, "onCreate: "+position );



        initView();
        gridViewareaAdapter.setSlection(position);
        setupData();
    }

    private void initView() {
        back = (ImageView) findViewById(R.id.back_to_around);
        back.setOnClickListener(this);

        gridView = (GridView) findViewById(R.id.changearea_gridview);
        gridViewareaAdapter = new GridViewareaAdapter(this,null);
        gridView.setAdapter(gridViewareaAdapter);

        gridView.setOnItemClickListener(this);

    }
    private void setupData() {
        RequestParams requestParams = new RequestParams(aroundUrl.AROUND_HEADER_TAB);
        x.http().get(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                aroundCityName aroundCityName = gson.fromJson(result, aroundCityName.class);
                Log.e("TAG", "onSuccess: ");
                List<aroundCityName.ContentBean> content = aroundCityName.getContent();
                gridViewareaAdapter.updateRes(content);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e("TAG", "onError: " +ex.getMessage()+ex.getCause());

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_to_around:
                intent.putExtra("pager",position);

                this.setResult(RESULT_OK,intent);
                this.finish();

                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        intent.putExtra("pager",position);

        this.setResult(RESULT_OK,intent);
        this.finish();
    }
}
