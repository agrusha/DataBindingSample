package com.wrike.tabs;

import android.databinding.DataBindingUtil;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.wrike.tabs.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        SearchHandler searchHandler = new SearchHandler();
        binding.setHandler(searchHandler);

        setSupportActionBar(binding.toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(false);


        ViewPager viewPager = binding.viewpager;
        setupViewPager(viewPager, searchHandler);

        TabLayout tabLayout = binding.tablayout;
        tabLayout.setupWithViewPager(viewPager);
        for (int i = 0; i< tabLayout.getTabCount(); i++) {
            tabLayout.getTabAt(i).setIcon(getResources().getDrawable(android.R.drawable.sym_def_app_icon));
        }

    }

    private void setupViewPager(ViewPager viewPager, SearchHandler searchHandler) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        PeopleFragment peopleFragment = new PeopleFragment();
        peopleFragment.initData();
        peopleFragment.setSearchHandler(searchHandler);
        adapter.addFragment(peopleFragment, "People");
        adapter.addFragment(new PeopleFragment(), "Group");
        adapter.addFragment(new PeopleFragment(), "Calls");
        viewPager.setAdapter(adapter);
    }
}
