package com.feicui.administrator.studentclass.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2016/11/1.
 */
public class IntroduceAdapter extends PagerAdapter {

    //ViewPager切换的数量
    View views[];
    public IntroduceAdapter(View[] views) {
        this.views = views;
    }
    //得到viewpager顶部横线
//    @Override
//    public CharSequence getPageTitle(int position) {
//        return super.getPageTitle(position);
//    }
    @Override
    public int getCount() {
        return views.length;
    }
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(views[position]);
    }
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(views[position]);
        return views[position];
    }
}
