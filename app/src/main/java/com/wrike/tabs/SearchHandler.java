package com.wrike.tabs;

import android.util.Log;

public class SearchHandler implements SearchViewBindingAdapter.OnQueryTextChange,
                                        SearchViewBindingAdapter.OnQueryTextSubmit,
                                        SearchViewBindingAdapter.OnSuggestionClick,
                                        SearchViewBindingAdapter.OnSuggestionSelect {
    private static final String TAG = "SearchHandler";

    @Override
    public boolean onQueryTextChange(String newText) {
        Log.i(TAG, "onQueryTextChange : " + newText);
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
