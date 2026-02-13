package com.mybrowser.pure;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SuggestionsManager {
    
    private static final String PREFS_NAME = "suggestions_prefs";
    private static final String KEY_SUGGESTIONS = "suggestions_list";
    
    private Context context;
    private SharedPreferences prefs;
    private Gson gson;
    
    public SuggestionsManager(Context context) {
        this.context = context;
        this.prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        this.gson = new Gson();
    }
    
    public List<Suggestion> getSuggestions() {
        String json = prefs.getString(KEY_SUGGESTIONS, null);
        if (json != null) {
            Type type = new TypeToken<List<Suggestion>>(){}.getType();
            return gson.fromJson(json, type);
        }
        return getDefaultSuggestions();
    }
    
    public void addSuggestion(Suggestion suggestion) {
        List<Suggestion> suggestions = getSuggestions();
        suggestions.add(0, suggestion);
        saveSuggestions(suggestions);
    }
    
    public void removeSuggestion(int position) {
        List<Suggestion> suggestions = getSuggestions();
        if (position >= 0 && position < suggestions.size()) {
            suggestions.remove(position);
            saveSuggestions(suggestions);
        }
    }
    
    private void saveSuggestions(List<Suggestion> suggestions) {
        String json = gson.toJson(suggestions);
        prefs.edit().putString(KEY_SUGGESTIONS, json).apply();
    }
    
    private List<Suggestion> getDefaultSuggestions() {
        List<Suggestion> suggestions = new ArrayList<>();
        suggestions.add(new Suggestion("يوتيوب", "https://www.youtube.com"));
        suggestions.add(new Suggestion("تويتر", "https://twitter.com"));
        suggestions.add(new Suggestion("ريديت", "https://www.reddit.com"));
        return suggestions;
    }
              }
