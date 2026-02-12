package com.mybrowser.pure;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class CustomWebView extends WebView {

    private OnProgressChangeListener progressListener;
    private OnUrlChangeListener urlChangeListener;
    private RenderTreeInterceptor renderInterceptor;

    public interface OnProgressChangeListener {
        void onProgressChanged(int progress);
    }

    public interface OnUrlChangeListener {
        void onUrlChanged(String url);
    }

    public CustomWebView(Context context) {
        super(context);
        init();
    }

    public CustomWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        renderInterceptor = new RenderTreeInterceptor(getContext());
        
        getSettings().setJavaScriptEnabled(true);
        getSettings().setDomStorageEnabled(true);
        getSettings().setLoadWithOverviewMode(true);
        getSettings().setUseWideViewPort(true);
        getSettings().setBuiltInZoomControls(false);
        getSettings().setDisplayZoomControls(false);
        getSettings().setMediaPlaybackRequiresUserGesture(false);

        setWebViewClient(new WebViewClient() {
            
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                
                // ★★★ هنا: حقن CSS قبل بناء شجرة العرض ★★★
                injectBlockingCSS();
                
                if (urlChangeListener != null) {
                    urlChangeListener.onUrlChanged(url);
                }
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                
                // استخراج وعرض المحتوى
                extractAndShowContent();
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return false;
            }
        });

        setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (progressListener != null) {
                    progressListener.onProgressChanged(newProgress);
                }
            }
        });
    }

    private void injectBlockingCSS() {
        String blockingScript = 
            "javascript:(function() {" +
            "    const style = document.createElement('style');" +
            "    style.id = 'early-blocker';" +
            "    style.textContent = '* { display: none !important; }';" +
            "    (document.head || document.documentElement).appendChild(style);" +
            "})()";
        
        loadUrl(blockingScript);
    }

    private void extractAndShowContent() {
        String extractScript = renderInterceptor.getInterceptorCode();
        evaluateJavascript(extractScript, null);
    }

    public void setProgressListener(OnProgressChangeListener listener) {
        this.progressListener = listener;
    }

    public void setUrlChangeListener(OnUrlChangeListener listener) {
        this.urlChangeListener = listener;
    }
                  }
