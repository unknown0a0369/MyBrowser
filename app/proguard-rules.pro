# Keep WebView
-keepclassmembers class * extends android.webkit.WebView {
   public *;
}

-keepclassmembers class * extends android.webkit.WebViewClient {
    public void *(android.webkit.WebView, java.lang.String);
}

# Keep JavaScript Interface
-keepclassmembers class * {
    @android.webkit.JavascriptInterface <methods>;
}

# Keep application classes
-keep class com.mybrowser.pure.** { *; }
