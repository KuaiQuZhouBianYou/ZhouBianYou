package com.phone1000.wanttozhoubianyou;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;

import com.phone1000.wanttozhoubianyou.Fragment.around.AroundFragment;
import com.phone1000.wanttozhoubianyou.Fragment.discover.DiscoverFragment;
import com.phone1000.wanttozhoubianyou.Fragment.home.HomeFragment;

import java.lang.reflect.InvocationTargetException;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {



    private RadioGroup mRadioGroup;
    private Fragment showFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initView();

    }
    private void initView() {
        mRadioGroup = (RadioGroup) findViewById(R.id.main_bottom_radiogroup);
        mRadioGroup.setOnCheckedChangeListener(this);
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        showFragment = new HomeFragment();
        transaction.add(R.id.main_container, showFragment,HomeFragment.TAG);
        transaction.commit();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.main_bottom_home:
                switchPage(HomeFragment.TAG,HomeFragment.class);
                break;
            case R.id.main_bottom_around:
                switchPage(AroundFragment.TAG,AroundFragment.class);
                break;
            case R.id.main_bottom_discover:
                switchPage(DiscoverFragment.TAG,DiscoverFragment.class);
                break;

        }
    }

    private void switchPage(String tag, Class<? extends Fragment> cls) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.hide(showFragment);
        showFragment=manager.findFragmentByTag(tag);
        if (showFragment!=null) {
            transaction.show(showFragment);
        }else {
            try {
                showFragment=cls.getConstructor().newInstance();
                transaction.add(R.id.main_container,showFragment,tag);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
        transaction.commit();
    }
}
