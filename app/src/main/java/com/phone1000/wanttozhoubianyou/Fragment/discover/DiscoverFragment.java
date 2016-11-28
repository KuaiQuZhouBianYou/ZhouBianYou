package com.phone1000.wanttozhoubianyou.Fragment.discover;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.phone1000.wanttozhoubianyou.Fragment.BaseFragment;
import com.phone1000.wanttozhoubianyou.R;

/**
 * Created by Administrator on 2016/11/26.
 */
public class DiscoverFragment extends BaseFragment {
    public View layout;
    public static final String TAG=DiscoverFragment.class.getSimpleName();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        layout = inflater.inflate(R.layout.fragment_discover_activity,container,false);
        return layout;
    }
}
