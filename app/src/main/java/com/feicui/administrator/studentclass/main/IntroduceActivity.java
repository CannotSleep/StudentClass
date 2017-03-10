package com.feicui.administrator.studentclass.main;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.feicui.administrator.studentclass.R;
import com.feicui.administrator.studentclass.adapter.IntroduceAdapter;
import com.feicui.administrator.studentclass.base.BaseActivity;

/**
 * Created by Administrator on 2016/11/1.
 * 第一次登陆时显示的界面
 */
public class IntroduceActivity extends BaseActivity {
    //ViewPager 控件
    ViewPager mViewPager;
    //利用sharedpreference来写入数据改变登陆的状态
    SharedPreferences sf;
    //显示的4张图片
    ImageView ima;
    ImageView imb;
    ImageView imc;
    ImageView imd;
    //进入主界面的按钮
    ImageView im_in;
    //Viewpager的适配器
    IntroduceAdapter mAdapter;
    //第一次登陆判断
    public Boolean isFirst;
    //加载的view
    View mView[];

    @Override
    protected boolean fullscreen() {
        return false;
    }

    @Override
    protected int contentView() {
        return R.layout.activity_introduce;
    }

    @Override
    protected void initView() {
        sf=getSharedPreferences("data",0);
        isFirst=sf.getBoolean("isfirst",true);
        if(isFirst==false){
            startActivity(new Intent(IntroduceActivity.this,LoginActivity.class));
            finish();
        }
        if(isFirst==true){
            SharedPreferences.Editor editor=sf.edit();
            editor.putBoolean("isfirst",false);
            editor.commit();
            initViewpager();
        }
        initViewpager();
    }

    public void initViewpager(){
        mView = new View[4];
        mView[0] = LayoutInflater.from(IntroduceActivity.this).inflate(R.layout.introduce_four, null);
        mView[1] = LayoutInflater.from(IntroduceActivity.this).inflate(R.layout.introduce_two, null);
        mView[2] = LayoutInflater.from(IntroduceActivity.this).inflate(R.layout.introduce_three, null);
        mView[3] = LayoutInflater.from(IntroduceActivity.this).inflate(R.layout.introduce_one, null);
        //关联进入主界面的按钮
        im_in = (ImageView) mView[3].findViewById(R.id.im_in);
        //关联ViewPager控件
        mViewPager = (ViewPager) findViewById(R.id.introduc_pager);
        //底部指示器
        ima = (ImageView) findViewById(R.id.point_a);
        imb = (ImageView) findViewById(R.id.point_b);
        imc = (ImageView) findViewById(R.id.point_c);
        imd = (ImageView) findViewById(R.id.point_d);

        ima.setImageResource(R.drawable.point_focus);
        imb.setImageResource(R.drawable.point_normal);
        imd.setImageResource(R.drawable.point_normal);
        imc.setImageResource(R.drawable.point_normal);
        //ViewPager 适配器
        mAdapter = new IntroduceAdapter(mView);
        mViewPager.setAdapter(mAdapter);

        //ViewPager改变时设置监听
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        ima.setImageResource(R.drawable.point_focus);
                        imb.setImageResource(R.drawable.point_normal);
                        imd.setImageResource(R.drawable.point_normal);
                        imc.setImageResource(R.drawable.point_normal);
                        break;
                    case 1:
                        ima.setImageResource(R.drawable.point_normal);
                        imb.setImageResource(R.drawable.point_focus);
                        imd.setImageResource(R.drawable.point_normal);
                        imc.setImageResource(R.drawable.point_normal);
                        break;
                    case 3:
                        ima.setImageResource(R.drawable.point_normal);
                        imb.setImageResource(R.drawable.point_normal);
                        imd.setImageResource(R.drawable.point_focus);
                        imc.setImageResource(R.drawable.point_normal);
                        break;
                    case 2:
                        ima.setImageResource(R.drawable.point_normal);
                        imb.setImageResource(R.drawable.point_normal);
                        imd.setImageResource(R.drawable.point_normal);
                        imc.setImageResource(R.drawable.point_focus);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        //进入图标设置点击事件
        im_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(IntroduceActivity.this, LoginActivity.class));
                finish();
            }
        });
    }

    @Override
    protected void setListener() {

    }
}
