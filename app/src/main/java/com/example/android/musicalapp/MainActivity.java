package com.example.android.musicalapp;

import android.app.Fragment;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toolbar;

public class MainActivity extends AppCompatActivity implements AlbumsFragment.OnFragmentInteractionListener , FoldersFragment.OnFragmentInteractionListener , ArtistsFragment.OnFragmentInteractionListener {
    private int currentTab = 0;
    private ViewPager viewPager;
    private PagerAdapter adapter;
    final int ARTISTS_TAB = 0;
    final int ALBUMS_TAB = 1;
    final int FOLDERS_TAB = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        SearchView searchView = findViewById(R.id.main_searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchText(newText);
                return false;
            }
        });

        TabLayout tabLayout = findViewById(R.id.main_tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("Artists"));
        tabLayout.addTab(tabLayout.newTab().setText("Albums"));
        tabLayout.addTab(tabLayout.newTab().setText("Folders"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        viewPager = findViewById(R.id.main_viewPager);
        adapter = new PagerAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                currentTab = tab.getPosition();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    private void searchText(String text){
        switch(currentTab){
            case ARTISTS_TAB: {
                ArtistsFragment currentFragment = (ArtistsFragment) adapter.getRegisteredFragment(viewPager.getCurrentItem());
                currentFragment.filterSongs(text); }
                break;
            case ALBUMS_TAB: {
                AlbumsFragment currentFragment = (AlbumsFragment) adapter.getRegisteredFragment(viewPager.getCurrentItem());
                currentFragment.filterSongs(text); }
                break;
            case FOLDERS_TAB: {
                FoldersFragment currentFragment = (FoldersFragment) adapter.getRegisteredFragment(viewPager.getCurrentItem());
                currentFragment.filterSongs(text); }
                break;

        }

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
