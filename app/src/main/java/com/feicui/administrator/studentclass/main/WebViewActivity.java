package com.feicui.administrator.studentclass.main;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;

import com.feicui.administrator.studentclass.R;
import com.feicui.administrator.studentclass.base.BaseActivity;

/**
 * Created by Z on 2017/3/7.
 */

public class WebViewActivity extends BaseActivity {
    WebView  webView;
    private ImageView backim;
    @Override
    protected boolean fullscreen() {
        return false;
    }

    @Override
    protected int contentView() {
        return R.layout.activity_webview;
    }

    @Override
    protected void initView() {
          webView= (WebView) findViewById(R.id.news_webview);
        backim= (ImageView) findViewById(R.id.subnews_back);
        backim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        setWebView();
        setToolbar();
    }

    @Override
    protected void setListener() {

    }

    public void setWebView(){
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("web");
        String news=bundle.getString("webview");
        webView.getSettings().setJavaScriptEnabled(true);
        //        // 设置支持javascript
//        mWebView.getSettings().setJavaScriptEnabled(true);
//        //启动缓存
//        // mWebView.getSettings().setAppCacheEnabled(true);
//        //设置缓存模式
//        //mWebView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
//        mWebView.loadUrl(web);

        webView.loadUrl(news);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    public  void setToolbar(){

    }
}
