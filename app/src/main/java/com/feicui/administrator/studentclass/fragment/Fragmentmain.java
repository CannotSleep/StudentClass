package com.feicui.administrator.studentclass.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.feicui.administrator.studentclass.R;
import com.feicui.administrator.studentclass.adapter.NewsAdapter;
import com.feicui.administrator.studentclass.entity.News;
import com.feicui.administrator.studentclass.main.WebViewActivity;
import com.feicui.administrator.studentclass.util.MyToast;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.StaticPagerAdapter;
import com.jude.rollviewpager.hintview.ColorPointHintView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/18.
 */
public class Fragmentmain extends Fragment{
    private RecyclerView recyclerView;
    private RollPagerView mRollViewPager;
    private NewsAdapter mAdapter;
    private List<News> mList;
    private LinearLayoutManager mManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_main,container,false);
        mRollViewPager = (RollPagerView) view.findViewById(R.id.roll_view_pager);
        recyclerView= (RecyclerView) view.findViewById(R.id.recycleview);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initImage();
        initRecycleView();

    }
    private void initImage(){

        //设置播放时间间隔
        mRollViewPager.setPlayDelay(3000);
        //设置透明度
        mRollViewPager.setAnimationDurtion(500);
        //设置适配器
        mRollViewPager.setAdapter(new TestNormalAdapter());

        //设置指示器（顺序依次）
        //自定义指示器图片
        //设置圆点指示器颜色
        //设置文字指示器
        //隐藏指示器
        //mRollViewPager.setHintView(new IconHintView(this, R.drawable.point_focus, R.drawable.point_normal));
        mRollViewPager.setHintView(new ColorPointHintView(getActivity(), Color.BLUE,Color.WHITE));
        //mRollViewPager.setHintView(new TextHintView(this));
        //mRollViewPager.setHintView(null);
    }

    private class TestNormalAdapter extends StaticPagerAdapter {
        private int[] imgs = {
                R.drawable.im1,
                R.drawable.im2,
                R.drawable.im3,
                R.drawable.im4,

        };

        @Override
        public View getView(ViewGroup container, int position) {
            ImageView view = new ImageView(container.getContext());
            view.setImageResource(imgs[position]);
            view.setScaleType(ImageView.ScaleType.CENTER_CROP);
            view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            return view;
        }

        @Override
        public int getCount() {
            return imgs.length;
        }
    }

    public void initRecycleView(){
        initNewsList();


    }

    public void initNewsList(){
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest = new StringRequest("http://118.244.212.82:9092/newsClient/news_list?ver=1&subid=1&dir=2&nid=10&stamp=20161013&cnt=20",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        mList=getNews(response);
                        Log.i("bb",mList.size()+"");
                        mAdapter=new NewsAdapter(mList,recyclerView,getContext());
//        mAdapter = new NewsAdapter(mList, recyclerView, getContext());
                        mManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                        recyclerView.setLayoutManager(mManager);

//        mManager = new LoadMoreScrollListener(mAdapter, mManager, mRefreshLayout, this);
//        mRecyclerView.addOnScrollListener(mListener);
                        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL_LIST));
                        recyclerView.setAdapter(mAdapter);
                       mAdapter.setOnItemClickListener(new NewsAdapter.OnItemClickListener() {
                           @Override
                           public void ItemClick(View view, int position) {
                               Intent intent = new Intent(getActivity(), WebViewActivity.class);
                               Bundle bundle = new Bundle();
                               bundle.putString("webview",mList.get(position).getLink());
                               intent.putExtra("web",bundle);
                               startActivity(intent);
                           }
                       });
//        mRefreshLayout.setOnRefreshListener(this);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                MyToast.showToast(getContext(),"无网络连接");
            }
        });
        requestQueue.add(stringRequest);
    }

    public List<News> getNews(String string){
        List<News> mList= new ArrayList<>();
        try {
            JSONObject object =new JSONObject(string);
            String message= object.getString("message");
            int status = object.getInt("status");
            if(message.equals("OK")&&status==0){
                JSONArray jsonArray_a=object.getJSONArray("data");
                for(int i=0;i<jsonArray_a.length();i++){
                    JSONObject object1 = jsonArray_a.getJSONObject(i);
                    String summary= object1.getString("summary");
                    String icon= object1.getString("icon");
                    String stamp= object1.getString("stamp");
                    String title= object1.getString("title");
                    int nid= object1.getInt("nid");
                    String link= object1.getString("link");
                    News news = new News();
                    news.setIcon(icon);
                    news.setLink(link);
                    news.setNid(nid);
                    news.setStamp(stamp);
                    news.setSummary(summary);
                    news.setTitle(title);
                    mList.add(news);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mList;
    }

}
