package com.feicui.administrator.studentclass.main;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.feicui.administrator.studentclass.R;
import com.feicui.administrator.studentclass.adapter.MyfragmentAdapter;
import com.feicui.administrator.studentclass.base.BaseActivity;
import com.feicui.administrator.studentclass.entity.LoginStudent;
import com.feicui.administrator.studentclass.fragment.Fragmentmain;
import com.feicui.administrator.studentclass.fragment.Fragmentsearch;
import com.feicui.administrator.studentclass.fragment.Fragmentuser;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private ViewPager mViewPager;
    List<Fragment> mFragmentList = new ArrayList<Fragment>();
    Fragmentmain mFragmentmain;
    Fragmentsearch mFragmentsearch;
    Fragmentuser mFragmentuser;
    MyfragmentAdapter mMyfragmentAdapter;
    LinearLayout mainlinea;
    LinearLayout searchlinea;
    LinearLayout userlinea;
    LoginStudent mStudent;
   public  String number;


    @Override
    protected boolean fullscreen() {
        return false;
    }

    @Override
    protected int contentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        Intent intent =getIntent();
        number=intent.getStringExtra("num");
        mStudent= (LoginStudent) intent.getSerializableExtra("student");
        Log.i("nnn",mStudent.getIdnum());
        Log.i("nnn",number+"");
        mViewPager = (ViewPager) findViewById(R.id.main_viewpager);
        mainlinea = (LinearLayout) findViewById(R.id.main_linea);
        mainlinea.setBackgroundResource(R.color.colorDarkGrey);
        searchlinea = (LinearLayout) findViewById(R.id.search_linea);

        userlinea = (LinearLayout) findViewById(R.id.user_linea);
        mFragmentmain = new Fragmentmain();
        mFragmentsearch = new Fragmentsearch();

        mFragmentuser = new Fragmentuser();
        mFragmentList.add(mFragmentmain);
        mFragmentList.add(mFragmentsearch);

        mFragmentList.add(mFragmentuser);
        mMyfragmentAdapter = new MyfragmentAdapter(getSupportFragmentManager(), mFragmentList);
        mViewPager.setAdapter(mMyfragmentAdapter);
        pagerswach();
    }

    public void pagerswach() {
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                int currentPage = mViewPager.getCurrentItem();
                switch (currentPage) {
                    case 0:
                        resetLayout();
                        mainlinea.setBackgroundResource(R.color.colorDarkGrey);
                        break;
                    case 1:
                        resetLayout();
                        searchlinea.setBackgroundResource(R.color.colorDarkGrey);
                        break;
                    case 2:
                        resetLayout();
                        userlinea.setBackgroundResource(R.color.colorDarkGrey);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public void resetLayout() {
        mainlinea.setBackgroundResource(R.color.colorWhite);
        searchlinea.setBackgroundResource(R.color.colorWhite);
        userlinea.setBackgroundResource(R.color.colorWhite);
    }

    @Override
    protected void setListener() {
        mainlinea.setOnClickListener(this);
        searchlinea.setOnClickListener(this);
        userlinea.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_linea:
                mViewPager.setCurrentItem(0);
                resetLayout();
                mainlinea.setBackgroundResource(R.color.colorDarkGrey);
                break;
            case R.id.search_linea:
                mViewPager.setCurrentItem(1);
                resetLayout();
                searchlinea.setBackgroundResource(R.color.colorDarkGrey);
                break;

            case R.id.user_linea:
                mViewPager.setCurrentItem(3);
                resetLayout();
                userlinea.setBackgroundResource(R.color.colorDarkGrey);
                break;

        }
    }
}
