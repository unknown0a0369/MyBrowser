package com.mybrowser.pure;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class AdBlocker {
    
    private static final Set<String> AD_HOSTS = new HashSet<>(Arrays.asList(
        "doubleclick.net",
        "googleadservices.com",
        "googlesyndication.com",
        "advertising.com",
        "ads.yahoo.com",
        "adserver",
        "adservice",
        "adsystem",
        "ad-delivery",
        "adtech",
        "admob",
        "adsense"
    ));
    
    public static boolean isAd(String url) {
        if (url == null || url.isEmpty()) {
            return false;
        }
        
        url = url.toLowerCase();
        
        for (String adHost : AD_HOSTS) {
            if (url.contains(adHost)) {
                return true;
            }
        }
        
        return false;
    }
    
    public static boolean shouldBlock(String url) {
        return isAd(url);
    }
}
