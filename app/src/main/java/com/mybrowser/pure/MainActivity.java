package com.mybrowser.pure;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ProgressBar;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private CustomWebView webView;
    private EditText urlBar;
    private ProgressBar progressBar;
    private BottomNavigationView bottomNav;
    private HomeFragment homeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        setupWebView();
        setupUrlBar();
        setupBottomNav();
        
        showHome();
    }

    private void initViews() {
        webView = findViewById(R.id.webView);
        urlBar = findViewById(R.id.urlBar);
        progressBar = findViewById(R.id.progressBar);
        bottomNav = findViewById(R.id.bottomNav);
        homeFragment = new HomeFragment();
    }

    private void setupWebView() {
        webView.setProgressListener(progress -> {
            if (progress < 100) {
                progressBar.setVisibility(View.VISIBLE);
                progressBar.setProgress(progress);
            } else {
                progressBar.setVisibility(View.GONE);
            }
        });

        webView.setUrlChangeListener(url -> {
            urlBar.setText(url);
        });
    }

    private void setupUrlBar() {
        urlBar.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_GO || 
                (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                String url = urlBar.getText().toString().trim();
                if (!url.isEmpty()) {
                    loadUrl(UrlHelper.normalizeUrl(url));
                }
                return true;
            }
            return false;
        });
    }

    private void setupBottomNav() {
        bottomNav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_home) {
                showHome();
                return true;
            } else if (id == R.id.nav_search) {
                urlBar.requestFocus();
                return true;
            }
            return false;
        });
    }
    
    private void showHome() {
        webView.setVisibility(View.GONE);
        getSupportFragmentManager()
            .beginTransaction()
            .replace(R.id.fragmentContainer, homeFragment)
            .commit();
    }
    
    public void loadUrl(String url) {
        webView.setVisibility(View.VISIBLE);
        getSupportFragmentManager()
            .beginTransaction()
            .remove(homeFragment)
            .commit();
        webView.loadUrl(url);
    }

    @Override
    public void onBackPressed() {
        if (webView.getVisibility() == View.VISIBLE && webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onDestroy() {
        if (webView != null) {
            webView.destroy();
        }
        super.onDestroy();
    }
    }
