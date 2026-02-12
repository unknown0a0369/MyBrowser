package com.mybrowser.pure;

public class ContentExtractor {
    
    public String getExtractionScript() {
        return "(function() {" +
               "    if (window.extractedContent) {" +
               "        return JSON.stringify(window.extractedContent);" +
               "    }" +
               "    return null;" +
               "})();";
    }
    
    public boolean isVideoUrl(String url) {
        if (url == null) return false;
        url = url.toLowerCase();
        return url.contains("youtube.com") ||
               url.contains("youtu.be") ||
               url.contains("vimeo.com") ||
               url.contains("dailymotion.com") ||
               url.contains(".mp4") ||
               url.contains(".webm");
    }
    
    public boolean isImageUrl(String url) {
        if (url == null) return false;
        url = url.toLowerCase();
        return url.endsWith(".jpg") ||
               url.endsWith(".jpeg") ||
               url.endsWith(".png") ||
               url.endsWith(".gif") ||
               url.endsWith(".webp");
    }
}
