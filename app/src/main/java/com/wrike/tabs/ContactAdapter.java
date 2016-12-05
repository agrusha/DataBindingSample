package com.wrike.tabs;

import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.wrike.tabs.databinding.ViewListItemBinding;

import java.util.ArrayList;
import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactViewHolder> implements Filterable {
    private List<Contact> contactList = new ArrayList<>();
    private List<Contact> filteredContactList = new ArrayList<>();
    private ItemFilter filter = new ItemFilter();
    private String currentFilter = "";

    public void addContacts(List<Contact> newContacts) {
        contactList.addAll(newContacts);
        // apply filter to new data
        getFilter().filter(currentFilter);
    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewListItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.view_list_item, parent, false);

        return new ContactViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(final ContactViewHolder holder, int position) {
        Contact contact = filteredContactList.get(position);
        holder.getBinding().setContact(contact);
    }

    @Override
    public int getItemCount() {
        return filteredContactList.size();
    }

    @BindingAdapter("bind:imageUrl")
    public static void loadAvatar(ImageView view, String avatarUrl) {
        // TODO: load avatar here
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    private class ItemFilter extends Filter {
        @Override
        protected FilterResults performFiltering(final CharSequence constraint) {
            List<Contact> rawContacts = contactList;
            currentFilter = constraint.toString().toLowerCase();
            if (currentFilter.isEmpty()) {
                return fillFromIterable(rawContacts);
            } else {
                return fillFromIterable(
                        Iterables.filter(rawContacts, new Contact.ContactPredicate(currentFilter))
                );
            }
        }

        private FilterResults fillFromIterable(Iterable<Contact> contacts) {
            FilterResults results = new FilterResults();
            results.values = contacts;
            results.count = Iterables.size(contacts);
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            filteredContactList = Lists.newArrayList((Iterable<Contact>) filterResults);
            notifyDataSetChanged();
        }
    }
}
