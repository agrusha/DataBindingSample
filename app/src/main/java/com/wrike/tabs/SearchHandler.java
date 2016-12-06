package com.wrike.tabs;

import android.util.Log;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SearchHandler implements SearchViewBindingAdapter.OnQueryTextChange,
                                        SearchViewBindingAdapter.OnQueryTextSubmit,
                                        SearchViewBindingAdapter.OnSuggestionClick,
                                        SearchViewBindingAdapter.OnSuggestionSelect {
    private static final String TAG = "SearchHandler";
    private Set<SearchViewBindingAdapter.OnQueryTextChange> textChangeListeners = new HashSet<>();
    private Set<SearchViewBindingAdapter.OnQueryTextSubmit> textSubmitListeners = new HashSet<>();
    private Set<SearchViewBindingAdapter.OnSuggestionClick> suggestionClickListeners = new HashSet<>();
    private Set<SearchViewBindingAdapter.OnSuggestionSelect> suggestionSelectListeners = new HashSet<>();

    public void addTextChangeListener(SearchViewBindingAdapter.OnQueryTextChange listener) {
        textChangeListeners.add(listener);
    }

    public void removeTextChangeListener(SearchViewBindingAdapter.OnQueryTextChange listener) {
        if (textChangeListeners.contains(listener)) {
            textChangeListeners.remove(listener);
        }
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        Log.i(TAG, "onQueryTextChange : " + newText);
        for (SearchViewBindingAdapter.OnQueryTextChange listener : textChangeListeners) {
            listener.onQueryTextChange(newText);
        }
        return false;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        Log.i(TAG, "onQueryTextSubmit : " + query);
        return false;
    }

    @Override
    public boolean onSuggestionClick(int position) {
        Log.i(TAG, "onSuggestionClick : " + position);
        return false;
    }

    @Override
    public boolean onSuggestionSelect(int position) {
        Log.i(TAG, "onSuggestionSelect : " + position);
        return false;
    }
}
