package com.feicui.administrator.studentclass.main;

import android.content.Intent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.feicui.administrator.studentclass.R;
import com.feicui.administrator.studentclass.base.BaseActivity;

/**
 * Created by Administrator on 2016/9/1.
 * 开头动画
 */
public class BeginActivity extends BaseActivity {
    //动画对象
    ImageView im_begin;

    Intent intent;

    @Override
    protected boolean fullscreen() {
        return true;
    }

    @Override
    protected int contentView() {
        return R.layout.activity_begin;
    }

    @Override
    protected void initView() {
        im_begin = (ImageView) findViewById(R.id.im_beginActivity);
        initAnim();
    }

    @Override
    protected void setListener() {

    }

    /**
     *装载动画
    */
    private void initAnim(){
        final Animation animation = AnimationUtils.loadAnimation(BeginActivity.this,R.anim.begin_anim);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                intent= new Intent(BeginActivity.this,IntroduceActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        im_begin.startAnimation(animation);

    }
}
