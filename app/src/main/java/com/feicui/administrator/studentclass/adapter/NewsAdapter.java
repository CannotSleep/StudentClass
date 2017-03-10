package com.feicui.administrator.studentclass.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.util.LruCache;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.feicui.administrator.studentclass.R;
import com.feicui.administrator.studentclass.entity.News;
import com.feicui.administrator.studentclass.util.TimeUtil;
import com.feicui.administrator.studentclass.util.VolleyRequest;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

/**
 * Created by Administrator on 2017/2/22.
 */

public class NewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
//    //上拉加载更多
//    public static final int PULLUP_LOAD_MORE = 0;
//    //正在加载中
//    public static final int LOADING_MORE = 1;
//    //上拉加载更多状态-默认为0
//    private int load_more_status = 0;


    private List<News> list;
    private RecyclerView mRecyclerView;
    private LruCache<String, Bitmap> mLruCache;
    private Context mContext;

    private static final int VIEW_TYPE_FOOT = 1;
    private static final int VIEW_TYPE_DEFAULT = 2;


    public NewsAdapter(List<News> list,RecyclerView mRecyclerView,Context context) {
        super();
        this.list = list;
        this.mRecyclerView = mRecyclerView;
        this.mContext=context;
        mLruCache = new LruCache<>(1024 * 1024);
    }

    /**
     * 设置不同的item布局
     *
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
//        if (getItemCount() == position + 1&& getItemCount()>7) {
//            return VIEW_TYPE_FOOT;
//        } else {
            return VIEW_TYPE_DEFAULT;
       // }
    }

//    public News backLastNews(){
//        Log.i("aaa",list.get(list.size()-1).getNid()+"");
//        return list.get(list.size()-1);
//    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        if (viewType == VIEW_TYPE_FOOT) {
//            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleviewgetmore, parent, false);
//            return new MyFootHolder(view);
//        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new MyViewHolder(view);
        //}
    }


    @Override
    public int getItemCount() {
//        if(list.size()>7){
//            return list.size() + 1;
//        }
        return list.size();
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyViewHolder) {
            ((MyViewHolder) holder).tv_title.setText(list.get(position).getTitle());
            ((MyViewHolder) holder).tv_summary.setText("\t\t\t" + list.get(position).getSummary());
            TimeUtil timeUtil = new TimeUtil();
            String time=timeUtil.setData(list.get(position).getStamp());
            ((MyViewHolder) holder).tv_time.setText(time);


            Bitmap bitmap = getBitmap(list.get(position).getIcon());
            if (bitmap != null) {
                ((MyViewHolder) holder).mImageView.setImageBitmap(bitmap);
            } else {
                ((MyViewHolder) holder).mImageView.setTag(list.get(position).getIcon());
            }

            if(onItemClickListener!=null){
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int pos=holder.getLayoutPosition();
                        onItemClickListener.ItemClick(holder.itemView,pos);
                    }
                });
            }
        }
    }


    class MyViewHolder extends RecyclerView.ViewHolder  {
        TextView tv_title;
        TextView tv_summary;
        TextView tv_time;
        ImageView mImageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_title = (TextView) itemView.findViewById(R.id.new_item_title_tv);
            tv_summary = (TextView) itemView.findViewById(R.id.new_item_summary_tv);
            tv_time = (TextView) itemView.findViewById(R.id.new_item_time_tv);
            mImageView = (ImageView) itemView.findViewById(R.id.new_item_icon_im);
        }


    }

    /**
     * 获取图片
     */
    public Bitmap getBitmap(final String string) {
        Bitmap bitmap;
        bitmap = getFromCache(string);
        if (bitmap != null) {
            return bitmap;
        }
        bitmap = getFromCacheDir(string);
        if (bitmap != null) {
            return bitmap;
        }
        getBitmapForNetWork(string);
        return null;
    }

    /**
     * 网络获取图片
     */
    public void getBitmapForNetWork(final String string) {

        RequestQueue mQueue = Volley.newRequestQueue(mContext);
        ImageRequest imageRequest = new ImageRequest(
                string,
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {
                        ImageView imageView = (ImageView) mRecyclerView.findViewWithTag(string);
                        if (imageView != null) {
                            imageView.setImageBitmap(response);

                            //将图片存储到本地
                            saveBitmapCache(string, response);
                        }
                    }
                }, 0, 0, Bitmap.Config.RGB_565, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        mQueue.add(imageRequest);
//        VolleyRequest.getmRequest().getNewsImage(string,
//                new Response.Listener<Bitmap>() {
//                    @Override
//                    public void onResponse(Bitmap bitmap) {
//                        ImageView imageView = (ImageView) mRecyclerView.findViewWithTag(string);
//                        if (imageView != null) {
//                            imageView.setImageBitmap(bitmap);
//
//                            //将图片存储到本地
//                            saveBitmapCache(string, bitmap);
//                        }
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//
//                    }
//                });
    }
    /**
     * 内存获取图片
     */
    private Bitmap getFromCache(String s) {
        s = s.substring(s.lastIndexOf("/"), s.length());
        return mLruCache.get(s);
    }

    /**
     * 本地获取图片
     */
    private Bitmap getFromCacheDir(String string) {
        Bitmap bitmap = null;
        string = string.substring(string.lastIndexOf("/"), string.length());
        File file = new File(mContext.getCacheDir(), string);
        if (file.exists()) {
            bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
            mLruCache.put(string, bitmap);
        }
        return bitmap;
    }

    /**
     * 本地存储图片
     */
    public void saveBitmapCache(String string, Bitmap bitmap) {
        string = string.substring(string.lastIndexOf("/"), string.length());
        Log.i("aaa", "保存到内存和本地");
        //保存到缓存
        mLruCache.put(string, bitmap);
        //保存到本地的cachefile文件夹
        File file = new File(mContext.getCacheDir(), string);
        try {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, new FileOutputStream(file));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public interface OnItemClickListener{
        void ItemClick(View view,int position);
    }

    private OnItemClickListener onItemClickListener;


    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
