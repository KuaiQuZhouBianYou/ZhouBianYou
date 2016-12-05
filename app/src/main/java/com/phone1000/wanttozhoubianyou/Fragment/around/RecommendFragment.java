package com.phone1000.wanttozhoubianyou.Fragment.around;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.phone1000.wanttozhoubianyou.Fragment.BaseFragment;
import com.phone1000.wanttozhoubianyou.R;
import com.phone1000.wanttozhoubianyou.activity.WebActivity;

import org.xutils.x;

/**
 * Created by 落叶 on 2016-11-30.
 */
public class RecommendFragment extends BaseFragment implements View.OnClickListener {
    private View layout;
    private String img;
    private String indexNum;
    private String indexType;
    private String intro;
    private String property;
    private String propertyName;
    private String title;
    private String url;
    private ImageView pic;
    private TextView title_view;
    private TextView indexNum_view;
    private TextView indexType_view;
    private TextView intro_view;
    private TextView property_view;
    private TextView propertyName_view;
    private String URL_HEADER="http://m.yaochufa.com/mmm/appscenic/scenicInfo?strategyId=";
    private String URL_FOOD="&version=5.5.1&appcitycode=370200&system=android&DeviceToken=00000000-7e69-c3b3-8e65-6234475ccbc6";
    private String id;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        layout = inflater.inflate(R.layout.fragment_recomment,container,false);
        return layout;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle bundle = getArguments();
        img = bundle.getString("img");
        indexNum = bundle.getString("indexNum");
        indexType = bundle.getString("indexType");
        intro = bundle.getString("intro");
        property = bundle.getString("property");
        propertyName = bundle.getString("propertyName");
        title = bundle.getString("title");
        id = bundle.getString("id");
        url =URL_HEADER+id+URL_FOOD;
        initView();

    }

    private void initView() {

        pic = ((ImageView) layout.findViewById(R.id.fragment_recommend_img));
        pic.setOnClickListener(this);
        x.image().bind(pic,img);
        title_view = ((TextView) layout.findViewById(R.id.fragment_recommend_title));
        title_view.setText(title);
        indexNum_view = ((TextView) layout.findViewById(R.id.fragment_recommend_indexNum));
        indexNum_view.setText(indexNum+"颗星");

        indexType_view = ((TextView) layout.findViewById(R.id.fragment_recommend_indexType));
        indexType_view.setText(indexType+":");
        intro_view = ((TextView) layout.findViewById(R.id.fragment_recommend_introduction));
        intro_view.setText(intro);
        property_view = ((TextView) layout.findViewById(R.id.fragment_recommend_property));
        property_view.setText(property);
        propertyName_view = ((TextView) layout.findViewById(R.id.fragment_recommend_propertyName));
        propertyName_view.setText( propertyName+":");



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fragment_recommend_img:

                Intent intent = new Intent(getActivity(), WebActivity.class);
                intent.putExtra("url",url);
                intent.putExtra("title",title);
                startActivity(intent);

                break;
        }
    }
}
