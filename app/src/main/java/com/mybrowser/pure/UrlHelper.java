package com.mybrowser.pure;

import android.webkit.URLUtil;

public class UrlHelper {
    
    public static String normalizeUrl(String url) {
        if (url == null || url.trim().isEmpty()) {
            return "https://www.google.com";
        }
        
        url = url.trim();
        
        if (!url.startsWith("http://") && !url.startsWith("https://")) {
            if (url.contains(" ") || !url.contains(".")) {
                return "https://www.google.com/search?q=" + url.replace(" ", "+");
            }
            url = "https://" + url;
        }
        
        return url;
    }
    
    public static boolean isValidUrl(String url) {
        return URLUtil.isValidUrl(url);
    }
    
    public static String getDomain(String url) {
        try {
            java.net.URL netUrl = new java.net.URL(url);
            return netUrl.getHost();
        } catch (Exception e) {
            return "";
        }
    }
}
