package com.wrike.tabs;

import android.annotation.TargetApi;
import android.databinding.BindingAdapter;
import android.databinding.BindingMethod;
import android.databinding.BindingMethods;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.support.v7.widget.SearchView;


@BindingMethods({
        @BindingMethod(type = SearchView.class, attribute = "android:onQueryTextFocusChange", method = "setOnQueryTextFocusChangeListener"),
        @BindingMethod(type = SearchView.class, attribute = "android:onSearchClick", method = "setOnSearchClickListener"),
        @BindingMethod(type = SearchView.class, attribute = "android:onClose", method = "setOnCloseListener"),
})
public class SearchViewBindingAdapter {
    @BindingAdapter(value = {"android:onQueryTextSubmit", "android:onQueryTextChange"},
            requireAll = false)
    public static void setOnQueryTextListener(SearchView view, final OnQueryTextSubmit submit,
                                              final OnQueryTextChange change) {
        if (VERSION.SDK_INT >= VERSION_CODES.HONEYCOMB) {
            if (submit == null && change == null){
                view.setOnQueryTextListener(null);
            } else {
                view.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        if (submit != null) {
                            return submit.onQueryTextSubmit(query);
                        } else {
                            return false;
                        }
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        if (change != null) {
                            return change.onQueryTextChange(newText);
                        } else {
                            return false;
                        }
                    }
                });
            }
        }
    }

    @BindingAdapter(value = {"android:onSuggestionSelect", "android:onSuggestionClick"},
            requireAll = false)
    public static void setOnSuggestListener(SearchView view, final OnSuggestionSelect submit,
                                            final OnSuggestionClick change) {
        if (VERSION.SDK_INT >= VERSION_CODES.HONEYCOMB) {
            if (submit == null && change == null) {
                view.setOnSuggestionListener(null);
            } else {
                view.setOnSuggestionListener(new SearchView.OnSuggestionListener() {
                    @Override
                    public boolean onSuggestionSelect(int position) {
                        if (submit != null) {
                            return submit.onSuggestionSelect(position);
                        } else {
                            return false;
                        }
                    }

                    @Override
                    public boolean onSuggestionClick(int position) {
                        if (change != null) {
                            return change.onSuggestionClick(position);
                        } else {
                            return false;
                        }
                    }
                });
            }
        }
    }

    @TargetApi(VERSION_CODES.HONEYCOMB)
    public interface OnQueryTextSubmit {
        boolean onQueryTextSubmit(String query);
    }

    @TargetApi(VERSION_CODES.HONEYCOMB)
    public interface OnQueryTextChange {
        boolean onQueryTextChange(String newText);
    }

    @TargetApi(VERSION_CODES.HONEYCOMB)
    public interface OnSuggestionSelect {
        boolean onSuggestionSelect(int position);
    }

    @TargetApi(VERSION_CODES.HONEYCOMB)
    public interface OnSuggestionClick {
        boolean onSuggestionClick(int position);
    }
}

