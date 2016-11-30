package com.phone1000.wanttozhoubianyou;

import android.app.Application;

import com.phone1000.administrator.mylibrary.ImageLoader;

import org.xutils.x;

/**
 * Created by 落叶 on 2016-11-28.
 */
public class MyApp extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.isDebug();
        ImageLoader.init(this);
    }
}
