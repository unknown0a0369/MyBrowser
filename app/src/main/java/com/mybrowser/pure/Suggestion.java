package com.mybrowser.pure;

public class Suggestion {
    
    private String title;
    private String url;
    private String imageUrl;
    private long timestamp;
    
    public Suggestion(String title, String url) {
        this.title = title;
        this.url = url;
        this.timestamp = System.currentTimeMillis();
    }
    
    public Suggestion(String title, String url, String imageUrl) {
        this.title = title;
        this.url = url;
        this.imageUrl = imageUrl;
        this.timestamp = System.currentTimeMillis();
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getUrl() {
        return url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }
    
    public String getImageUrl() {
        return imageUrl;
    }
    
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    
    public long getTimestamp() {
        return timestamp;
    }
}
