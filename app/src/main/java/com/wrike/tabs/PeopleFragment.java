package com.wrike.tabs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class PeopleFragment extends Fragment implements SearchViewBindingAdapter.OnQueryTextChange {
    @Nullable
    private SearchHandler searchHandler;

    private ContactAdapter contactAdapter = new ContactAdapter();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_people, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(contactAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        return view;
    }

    public void initData() {
        Log.i("PEOPLE", "init");
        Contact sevilla = new Contact("", "Sevilla Fordragon", "aggressive");
        Contact orientir = new Contact("", "Orientir Downhouse", "indifferent");
        Contact paris = new Contact("", "Paris Johnson", "drunk as fuck");
        Contact rulon = new Contact("", "Rulon Oboev", "ignorant");
        Contact vissarion = new Contact("", "Vissarion Van der Brachden", "acceptable");
        Contact deriv = new Contact("", "Deriv Rubelstein", "");
        List<Contact> contacts = new ArrayList<>();
        contacts.add(sevilla);
        contacts.add(orientir);
        contacts.add(paris);
        contacts.add(rulon);
        contacts.add(vissarion);
        contacts.add(deriv);
        contactAdapter.addContacts(contacts);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (searchHandler != null) {
            searchHandler.addTextChangeListener(this);
        }
    }

    @Override
    public void onPause() {
        if (searchHandler != null) {
            searchHandler.removeTextChangeListener(this);
        }
        super.onPause();
    }

    public void setSearchHandler(SearchHandler searchHandler) {
        this.searchHandler = searchHandler;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        contactAdapter.getFilter().filter(newText);
        return false;
    }
}
