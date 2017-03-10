package com.feicui.administrator.studentclass.util;

import android.content.Context;
import android.graphics.Bitmap;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/28.
 */

public class VolleyRequest {
    private RequestQueue mQueue;

    private  static VolleyRequest mRequest;

    private VolleyRequest(Context context) {
        mQueue = Volley.newRequestQueue(context);
    }
    public static VolleyRequest getmRequest(Context context){
        if(mRequest==null){
            mRequest=new VolleyRequest(context);
        }
        return mRequest;
    }

    public static VolleyRequest getmRequest(){
        return mRequest;
    }

//    /**
//     * 注册方法
//     * @param name
//     * @param ps
//     * @param mail
//     * @param listener
//     * @param errorListener
//     */
//    public void design(final String name, final String ps, final String mail, Response.Listener<String>  listener, Response.ErrorListener errorListener){
//        StringRequest stringRequest = new StringRequest(
//                Request.Method.POST,
//                HttpApi.ADRESS+ HttpApi.DESIGN+ HttpApi.VERSION,
//                listener,
//                errorListener
//        ){
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> map = new HashMap<>();
//                map.put("uid",name);
//                map.put("email",mail);
//                map.put("pwd",ps);
//                return map;
//            }
//        };
//        mQueue.add(stringRequest);
//    }

//    /**
//     * 登陆方法
//     * @param name
//     * @param pass
//     * @param listener
//     * @param errorListener
//     */
//    public void login(final String name , final String pass, Response.Listener<String>listener,
//                      Response.ErrorListener errorListener){
//        StringRequest stringRequest=new StringRequest(
//                Request.Method.POST,
//                HttpApi.ADRESS+ HttpApi.Login+HttpApi.VERSION,
//                listener,
//                errorListener
//        ){
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> map = new HashMap<>();
//                map.put("uid",name);
//                map.put("pwd",pass);
//                map.put(HttpApi.DEVICE,0+"");
//                return map;
//            }
//        };
//        mQueue.add(stringRequest);
//    }


//    /**
//     * 获取头像方法
//     * @param token
//     * @param listener
//     * @param errorListener
//     */
//    public void loadImage(final String token, Response.Listener<String>listener, Response.ErrorListener errorListener){
//        StringRequest request=new StringRequest(
//                Request.Method.POST,
//                HttpApi.ADRESS+ HttpApi.HOME+HttpApi.VERSION,
//                listener,
//                errorListener
//        )
//        {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> map = new HashMap<>();
//                map.put("imei","1");
//                map.put("token",token);
//
//                return map;
//            }
//        };
//        mQueue.add(request);
//    }


//    /**
//     * 加载头像方法
//     */
//    public void readIm(String imurl, Response.Listener<Bitmap>listener, Response.ErrorListener errorListener){
//        ImageRequest imageRequest=new ImageRequest(
//                imurl,listener
//                , 0, 0, Bitmap.Config.RGB_565,
//                errorListener
//        );
//        mQueue.add(imageRequest);
//    }


//    /*
//     *上传头像
//     */
//    public void upImage(File fileim, String token, Response.Listener<String>listener, Response.ErrorListener errorListener){
//        String uri=HttpApi.ADRESS+HttpApi.USERPOTRAIT+"token="+token;
//        MultiPostRequest request = new MultiPostRequest(uri,listener,errorListener);
//        request.buildMultipartEntity("portrait",fileim);
//        mQueue.add(request);
//    }


//    /**
//     * 获取新闻分类
//     */
//    public void getNewsType(Response.Listener<String>listener,
//                            Response.ErrorListener errorListener){
//            StringRequest stringRequest=new StringRequest(
//                    Request.Method.POST,
//                    HttpApi.ADRESS+ HttpApi.NEWSTYPE+"ver=1&imei=1",
//                    listener,
//                    errorListener
//            );
//            mQueue.add(stringRequest);
//        }

    /**
     * 获取新闻
     */
    public void getNews(final int cnt, final String stamp, final int nid, final int dir, final int subid, Response.Listener<String>listener,
                        Response.ErrorListener errorListener){
        StringRequest stringRequest=new StringRequest(
                Request.Method.POST,
                HttpApi.ADRESS+ HttpApi.NEWS+"ver=1",
                listener,
                errorListener
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("subid",subid+"");
                map.put("dir",dir+"");
                map.put("nid",nid+"");
                map.put("stamp",stamp);
                map.put("cnt",cnt+"");
                return map;
            }
        };
        mQueue.add(stringRequest);
    }

    /**
     * 网络获取新闻图片
     */
    public void getNewsImage(String s, Response.Listener<Bitmap>  listener, Response.ErrorListener errorListener ){
        ImageRequest imageRequest= new ImageRequest(
                s,listener,0,0, Bitmap.Config.RGB_565
                ,errorListener
        );
        mQueue.add(imageRequest);
    }

}
