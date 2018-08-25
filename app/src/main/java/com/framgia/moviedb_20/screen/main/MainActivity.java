package com.framgia.moviedb_20.screen.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.framgia.moviedb_20.R;
import com.framgia.moviedb_20.data.api.BaseGetDataAsyncTask;
import com.framgia.moviedb_20.data.model.Genre;
import com.framgia.moviedb_20.data.repository.MovieRepository;
import com.framgia.moviedb_20.data.repository.SearchRepository;
import com.framgia.moviedb_20.data.source.local.config.DBMovie;
import com.framgia.moviedb_20.data.source.remote.MovieRemoteDataSource;
import com.framgia.moviedb_20.data.source.remote.SearchRemoteDataSource;
import com.framgia.moviedb_20.screen.adapter.NavigationAdapter;
import com.framgia.moviedb_20.screen.adapter.OnItemClickListener;
import com.framgia.moviedb_20.screen.detail.movie.MovieDetailFragment;
import com.framgia.moviedb_20.screen.favorite.FavoriteFragment;
import com.framgia.moviedb_20.screen.genre.GenreFragment;
import com.framgia.moviedb_20.screen.main.home.HomeFragment;
import com.framgia.moviedb_20.screen.search.SearchFragment;

import java.util.List;

public class MainActivity extends AppCompatActivity implements HomeFragment.OnMovieSelectedListener,MenuItem.OnActionExpandListener, SearchView.OnQueryTextListener, View.OnClickListener, OnItemClickListener, BaseGetDataAsyncTask.OnGetDataListener, NavigationView.OnNavigationItemSelectedListener,
        MainContract.View {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private NavigationView mView;
    private String mQuery;
    private SearchView mSearchView;
    private RecyclerView mRecyclerViewGenres;
    private MenuItem mMenuItem;
    private LinearLayout mLinearLayoutFavorite;
    private LinearLayout mLinearLayoutHome;
    private NavigationAdapter mNavigationAdapter;
    private MainContract.Presenter mPresenter;
    private DBMovie mDBMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MovieRemoteDataSource movieRemoteDataSource = new MovieRemoteDataSource(this);
        mDBMovie = new DBMovie(this);
        MovieRepository movieRepository = new MovieRepository(movieRemoteDataSource);
        SearchRemoteDataSource searchRemoteDataSource = new SearchRemoteDataSource(this);
        SearchRepository searchRepository = new SearchRepository(searchRemoteDataSource);
        mPresenter = new MainPresenter(movieRepository, searchRepository);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initNavigation();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fragment_home, HomeFragment.getNewInstance(this));
        transaction.commit();
    }

    private void initNavigation() {
        mDrawerLayout = findViewById(R.id.drawer_layout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.action_open, R.string.action_close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mLinearLayoutFavorite = findViewById(R.id.linear_favorite);
        mLinearLayoutHome = findViewById(R.id.linear_home);
        mLinearLayoutHome.setOnClickListener(this);
        mLinearLayoutFavorite.setOnClickListener(this);
        mView = findViewById(R.id.nav_view);
        mView.setNavigationItemSelectedListener(this);
        mRecyclerViewGenres = findViewById(R.id.recycler_genres);
        mPresenter.loadGenres();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        mMenuItem = menu.findItem(R.id.search);
        mSearchView = (SearchView) mMenuItem.getActionView();
        mSearchView.setIconifiedByDefault(true);
        mSearchView.setOnClickListener(this);
        mSearchView.setSubmitButtonEnabled(true);
        mSearchView.setOnQueryTextListener(this);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return mToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        return false;
    }

    public void replaceFragment(int groupID, Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(groupID, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onGetDataSuccess(List data) {
        if (data.get(0) instanceof Genre) {
            mNavigationAdapter = new NavigationAdapter(data);
            mNavigationAdapter.setItemListener(this);
            mRecyclerViewGenres.setAdapter(mNavigationAdapter);
        }
    }

    @Override
    public void onGetDataFailure(Exception e) {
        // TODO: 22/08/2018
    }

    @Override
    public void onItemClick(RecyclerView recyclerView, int id, String mediaType) {
        switch (recyclerView.getId()) {
            case R.id.recycler_genres:
                if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
                    mDrawerLayout.closeDrawer(GravityCompat.START);
                    replaceFragment(R.id.fragment_home, GenreFragment.getNewInstance(id));
                }
                break;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.linear_home:
                if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
                    mDrawerLayout.closeDrawer(GravityCompat.START);
                    replaceFragment(R.id.fragment_home, HomeFragment.getNewInstance(this));
                }
                break;
            case R.id.linear_favorite:
                if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
                    mDrawerLayout.closeDrawer(GravityCompat.START);
                    replaceFragment(R.id.fragment_home, FavoriteFragment.getNewInstance(mDBMovie));
                }
                break;
        }
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        mQuery = s.toLowerCase();
        mMenuItem.setOnActionExpandListener(this);
        replaceFragment(R.id.fragment_home, SearchFragment.getNewInstance(mQuery));
        return true;
    }

    @Override
    public boolean onMenuItemActionExpand(MenuItem menuItem) {
        replaceFragment(R.id.fragment_home, SearchFragment.getNewInstance(mQuery));
        return true;
    }

    @Override
    public boolean onMenuItemActionCollapse(MenuItem menuItem) {
        replaceFragment(R.id.fragment_home, HomeFragment.getNewInstance(this));
        return true;
    }

    @Override
    public void setOnMovieSelected(int id) {
        replaceFragment(R.id.fragment_home, MovieDetailFragment.getNewInstance(id));
    }
}
