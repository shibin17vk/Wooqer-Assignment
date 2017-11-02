package com.webapp.wooqerassignment.topstories.ui;

import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.webapp.wooqerassignment.HackerNewsBaseActivity;
import com.webapp.wooqerassignment.R;
import com.webapp.wooqerassignment.constants.AppConstants;
import com.webapp.wooqerassignment.database.DatabaseManager;
import com.webapp.wooqerassignment.widget.DataContainerLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HackerNewsDetailsActivity extends HackerNewsBaseActivity {

    @BindView(R.id.articleDetails)
    protected WebView articleDetails;

    @BindView(R.id.dataLayout)
    protected DataContainerLayout dataLayout;

    private AppWebViewClient mAppWebViewClient;
    private String articleUrl;
    private long articleId;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hacker_news_details);
        setStatusBarColor(ContextCompat.getColor(this,R.color.statusBarColor));
        ButterKnife.bind(this);
        getBundleInfo();
        dataLayout.showData();
        initWebView();
    }

    private void initWebView() {
        mAppWebViewClient = new AppWebViewClient();
        String cachePath = getString(R.string.cache_path);
        articleDetails.getSettings().setJavaScriptEnabled(true);
        articleDetails.getSettings().setAppCacheMaxSize(1024*1024*8);
        articleDetails.getSettings().setAppCachePath(cachePath);
        articleDetails.getSettings().setAppCacheEnabled(true);
        articleDetails.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        articleDetails.setWebViewClient(mAppWebViewClient);
        articleDetails.loadUrl(articleUrl);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private void getBundleInfo() {
        Bundle bundle = getIntent().getExtras();
        if(bundle != null && bundle.containsKey(AppConstants.TAG_URL)) {
            articleUrl = bundle.getString(AppConstants.TAG_URL);
        }
        if(bundle != null && bundle.containsKey(AppConstants.TAG_ID)) {
            articleId = bundle.getLong(AppConstants.TAG_ID);
        }
    }

    private class AppWebViewClient extends WebViewClient {

        @Override
        public void onPageStarted(final WebView view, final String url, final Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            dataLayout.startLoader();
        }

        @Override
        public void onPageFinished(final WebView view, final String url) {
            super.onPageFinished(view, url);
            DatabaseManager.getInstance().updateReadStatus(articleId, HackerNewsDetailsActivity.this);
            dataLayout.showData();
        }

        @Override
        public void onReceivedError(final WebView view, final WebResourceRequest request,
                final WebResourceError error) {
            super.onReceivedError(view, request, error);
            Toast.makeText(getApplicationContext(), "This article can not be loaded at this moment !", Toast
                    .LENGTH_SHORT).show();

        }
    }
}
