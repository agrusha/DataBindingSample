package com.wrike.tabs;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.google.common.base.Predicate;

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

    public static class ContactPredicate implements Predicate<Contact> {
        private final CharSequence text;

        public ContactPredicate(CharSequence text) {
            this.text = text;
        }

        @Override
        public boolean apply(Contact input) {
            return input.getUsername().contains(text);
        }
    }
}
