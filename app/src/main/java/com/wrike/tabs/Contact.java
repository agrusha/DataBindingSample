package com.wrike.tabs;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

public class Contact extends BaseObservable {
    private String avatarUrl;
    private String username;
    private String status;

    public Contact(String avatarUrl, String username, String status) {
        this.avatarUrl = avatarUrl;
        this.username = username;
        this.status = status;
    }

    @Bindable
    public String getAvatarUrl() {
        return avatarUrl;
    }

    @Bindable
    public String getUsername() {
        return username;
    }

    @Bindable
    public String getStatus() {
        return status;
    }

    public static class ContactPredicate {
        private final CharSequence text;

        public ContactPredicate(CharSequence text) {
            this.text = text;
        }

        public boolean apply(Contact input) {
            return input.getUsername().toLowerCase().contains(text);
        }
    }
}
