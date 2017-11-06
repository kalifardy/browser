package com.android.kalif.browser;

import android.content.res.Configuration;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    //deklarasi masing masing item yg akan di gunakan
    private WebView web;
    private EditText txtUrl;
    private Button btnGo;
    private ProgressBar progressBar;
    String url;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //berikan id pada masing masing item pada layout
        web = (WebView)findViewById(R.id.webview);
        txtUrl = (EditText)findViewById(R.id.txturl);
        btnGo =(Button)findViewById(R.id.btncari);
        //default browser pada pertamakali membuka aplikasi
        String url ="https://www.google.com";
        //berikan akses seperti java script,zoom control dan database
        web.getSettings().setJavaScriptEnabled(true);
        web.getSettings().setAllowContentAccess(true);
        web.getSettings().setAllowFileAccessFromFileURLs(true);
        web.getSettings().setAllowFileAccess(true);
        web.getSettings().setAllowUniversalAccessFromFileURLs(true);
        web.getSettings().setDatabaseEnabled(true);
        web.getSettings().setDisplayZoomControls(true);
        
        web.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        web.getSettings().setLoadWithOverviewMode(true);
        web.getSettings().setUseWideViewPort(true);
        web.getSettings().setBuiltInZoomControls(true);
        web.getSettings().setDomStorageEnabled(true);
        //perintah untuk progressbar
        progressBar = (ProgressBar)findViewById(R.id.progresBar);
        
        web.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                progressBar.setVisibility(View.VISIBLE);
                progressBar.setProgress(newProgress);
                if (newProgress == 100){
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
        web.loadUrl(url);
        web.setWebViewClient(new WebViewClient());
        //perintah button supaya ketika di klik bisa menuju ke url
        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = txtUrl.getText().toString();
                web.getSettings().setJavaScriptEnabled(true);
                web.getSettings().setDisplayZoomControls(true);
                
                progressBar=(ProgressBar)findViewById(R.id.progresBar);
                web.setWebChromeClient(new WebChromeClient(){
                    @Override
                    public void onProgressChanged(WebView view, int newProgress) {
                       progressBar.setVisibility(View.VISIBLE);
                       progressBar.setProgress(newProgress);
                       if (newProgress==100){
                           progressBar.setVisibility(View.GONE);
                       }
                    }
                });
                web.loadUrl(url);

            }
        });
    }

    //perintah button back,next,refresh,stop
    public void goBack(View view){
        web.goBack();
    }
    public void goNext(View view){web.goForward();}
    public void Stop(View view){web.stopLoading();}
    public void Refresh(View view){web.reload();}

    //perintah untuk rotate supaya tidak balik lagi ke halaman awal
    public  void  onConfigurationChanged(Configuration newConfig){
        super.onConfigurationChanged(newConfig);
    }
    //masuk kedalam androidmanifest dan berikan  android:configChanges="orientation|screenSize pada class mainactivity
    //berikan  <uses-permission android:name="android.permission.INTERNET"></uses-permission> pada androidmanifest
}

