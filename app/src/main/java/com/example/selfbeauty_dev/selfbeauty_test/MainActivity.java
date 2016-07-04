package com.example.selfbeauty_dev.selfbeauty_test;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends FragmentActivity {

    //git test !!
    public static WebView mWebView;
    public static String domain = "https://www.selfbeauty.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //레이아웃 세팅
        setContentView(R.layout.activity_main);

        //웹뷰 선언
        mWebView = (WebView) findViewById(R.id.webView);

        //웹뷰 관련 설정
        //mWebView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE); //캐쉬 설정
        mWebView.getSettings().setJavaScriptEnabled(true); //자바스크립트 사용

        //javascript의 window.open 허용
        mWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);

        //meta태그의 viewport사용 가능
        mWebView.getSettings().setUseWideViewPort(true);

        mWebView.setVerticalScrollBarEnabled(false);   //세로 스크롤
        mWebView.getSettings().setBuiltInZoomControls(true); //줌 기능 사용
        mWebView.getSettings().setSupportZoom(true);

        mWebView.setBackgroundColor(0); //배경색

        //이미지나 동영상의 해상도를 폰의 크기에 맞춰줌
        mWebView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);

        //mWebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY); // 스크롤바
        //mWebView.setFocusableInTouchMode(false); // 터치포커스

        final Context myApp = this;
        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsAlert(WebView view, String url, String message, final android.webkit.JsResult result)
            {
                new AlertDialog.Builder(myApp)
                        .setTitle("AlertDialog")
                        .setMessage(message)
                        .setPositiveButton(android.R.string.ok,
                                new AlertDialog.OnClickListener()
                                {
                                    public void onClick(DialogInterface dialog, int which)
                                    {
                                        result.confirm();
                                    }
                                })
                        .setCancelable(false)
                        .create()
                        .show();
                return true;
            };
        });

        //웹뷰 로딩
        mWebView.loadUrl(domain);
        mWebView.setWebViewClient(new WishWebViewClient());

        ImageButton homeBtn = (ImageButton) this.findViewById(R.id.homeBtn);
        ImageButton loginBtn = (ImageButton) this.findViewById(R.id.loginBtn);
        ImageButton postBtn = (ImageButton) this.findViewById(R.id.postBtn);
        ImageButton eventBtn = (ImageButton) this.findViewById(R.id.eventBtn);
        ImageButton tempBtn = (ImageButton) this.findViewById(R.id.tempBtn);

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWebView.loadUrl(domain);
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWebView.loadUrl("https://selfbeauty.com/loginGate.do");
            }
        });

        postBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWebView.loadUrl("https://selfbeauty.com/tip/tipMain.do");
            }
        });

        eventBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWebView.loadUrl("https://selfbeauty.com/event/eventMain.do");
            }
        });

        tempBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWebView.loadUrl("https://selfbeauty.com/now/nowMain.do");
            }
        });
    }
    /*
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()){
            mWebView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    */

    private class WishWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url){
            view.loadUrl(url);
            return true;
        }
    }


}
