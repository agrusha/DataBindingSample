package com.wrike.tabs;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.wrike.tabs.databinding.ViewListItemBinding;

public class ContactViewHolder extends RecyclerView.ViewHolder {
    private final ViewListItemBinding binding;

    public ContactViewHolder(View itemView) {
        super(itemView);
        binding = DataBindingUtil.bind(itemView);
    }

    public ViewListItemBinding getBinding() {
        return binding;
    }
}
