package com.phone1000.wanttozhoubianyou.Fragment.around;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.phone1000.wanttozhoubianyou.Fragment.BaseFragment;
import com.phone1000.wanttozhoubianyou.R;


/**
 *
 */


public class AroundFragment extends BaseFragment {
    public View layout;
    public static final String TAG=AroundFragment.class.getSimpleName();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        layout = inflater.inflate(R.layout.fragment_around_activity,container,false);
        return layout;
    }





}
