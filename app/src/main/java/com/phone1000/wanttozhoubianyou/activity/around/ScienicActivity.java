package com.phone1000.wanttozhoubianyou.activity.around;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.phone1000.wanttozhoubianyou.R;

/**
 * Created by 落叶 on 2016-11-29.
 */
public class ScienicActivity extends AppCompatActivity {
    private static final String TAG = ScienicActivity.class.getSimpleName();
    private String url_header = "http://apiphp.yaochufa.com/portal/scenic/scenicDetail430?system=android&channel=yaochufa&imei=ffffffff-8f6e-f1b6-197c-94460033c587&regId=170976fa8a8e9eae6b4&id=";
    private String url_later="&lang=app&version=5.5.1&deviceToken=ffffffff-8f6e-f1b6-197c-94460033c587";
    private String url;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scienic);

        Intent intent = getIntent();
       String id = intent.getStringExtra("id");

        url = url_header+id+url_later;

        Log.e(TAG, "onCreate: "+id );

        initView();
    }

    private void initView() {

    }

}
