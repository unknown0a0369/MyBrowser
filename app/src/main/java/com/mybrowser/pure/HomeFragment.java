package com.mybrowser.pure;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class HomeFragment extends Fragment {
    
    private RecyclerView recyclerView;
    private SuggestionsAdapter adapter;
    private SuggestionsManager suggestionsManager;
    
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        
        recyclerView = view.findViewById(R.id.suggestionsRecyclerView);
        suggestionsManager = new SuggestionsManager(requireContext());
        
        setupRecyclerView();
        
        return view;
    }
    
    private void setupRecyclerView() {
        List<Suggestion> suggestions = suggestionsManager.getSuggestions();
        
        adapter = new SuggestionsAdapter(suggestions, suggestion -> {
            if (getActivity() instanceof MainActivity) {
                ((MainActivity) getActivity()).loadUrl(suggestion.getUrl());
            }
        });
        
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(adapter);
    }
    
    public void loadUrl(String url) {
        // سيتم تنفيذها في MainActivity
    }
}
