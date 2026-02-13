package com.mybrowser.pure;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class SuggestionsAdapter extends RecyclerView.Adapter<SuggestionsAdapter.ViewHolder> {
    
    private List<Suggestion> suggestions;
    private OnSuggestionClickListener listener;
    
    public interface OnSuggestionClickListener {
        void onSuggestionClick(Suggestion suggestion);
    }
    
    public SuggestionsAdapter(List<Suggestion> suggestions, OnSuggestionClickListener listener) {
        this.suggestions = suggestions;
        this.listener = listener;
    }
    
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_suggestion, parent, false);
        return new ViewHolder(view);
    }
    
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Suggestion suggestion = suggestions.get(position);
        holder.bind(suggestion);
    }
    
    @Override
    public int getItemCount() {
        return suggestions.size();
    }
    
    class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleText;
        TextView urlText;
        ImageView imageView;
        
        ViewHolder(View itemView) {
            super(itemView);
            titleText = itemView.findViewById(R.id.suggestionTitle);
            urlText = itemView.findViewById(R.id.suggestionUrl);
            imageView = itemView.findViewById(R.id.suggestionImage);
            
            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION && listener != null) {
                    listener.onSuggestionClick(suggestions.get(position));
                }
            });
        }
        
        void bind(Suggestion suggestion) {
            titleText.setText(suggestion.getTitle());
            urlText.setText(suggestion.getUrl());
        }
    }
              }
