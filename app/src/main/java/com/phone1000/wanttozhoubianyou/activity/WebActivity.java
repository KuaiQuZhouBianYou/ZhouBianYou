package com.phone1000.wanttozhoubianyou.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebView;
import android.widget.TextView;

import com.phone1000.wanttozhoubianyou.R;

/**
 * Created by 落叶 on 2016-12-02.
 */
public class WebActivity extends AppCompatActivity {
    private WebView web;
    private String url;
    private String title;
    private TextView name;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        Intent intent = getIntent();
        url = intent.getStringExtra("url");
        title = intent.getStringExtra("title");
        Log.e("TAG", "onCreate:"+url );

        initView();

    }

    private void initView() {
        web = (WebView) findViewById(R.id.web_activity);
        name = (TextView) findViewById(R.id.web_activity_title);
        web.getSettings().setJavaScriptEnabled(true);
        web.loadUrl(url);
        name.setText(title);
    }
}
