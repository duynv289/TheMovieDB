package com.framgia.moviedb_20.screen.favorite;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.framgia.moviedb_20.R;
import com.framgia.moviedb_20.data.model.BaseMovie;
import com.framgia.moviedb_20.data.repository.FavoriteRepository;
import com.framgia.moviedb_20.data.source.local.FavoriteLocalDataSource;
import com.framgia.moviedb_20.data.source.local.config.DBMovie;
import com.framgia.moviedb_20.screen.adapter.CardViewAdapter;
import com.framgia.moviedb_20.screen.adapter.OnItemClickListener;
import com.framgia.moviedb_20.screen.adapter.OnItemLongClickListener;
import com.framgia.moviedb_20.screen.detail.movie.MovieDetailFragment;
import com.framgia.moviedb_20.screen.main.MainActivity;
import com.framgia.moviedb_20.screen.main.MainPresenter;

import java.util.List;

public class FavoriteFragment extends Fragment implements OnItemClickListener, OnItemLongClickListener, FavoriteContract.View {

    private static DBMovie mDBMovie;
    private CardViewAdapter mCardViewAdapter;
    private RecyclerView mRecyclerView;
    private FavoriteContract.Presenter mPresenter;

    public static FavoriteFragment getNewInstance(DBMovie dbMovie) {
        mDBMovie = dbMovie;
        return new FavoriteFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDBMovie = new DBMovie(getActivity());
        FavoriteLocalDataSource favoriteLocalDataSource = new FavoriteLocalDataSource(mDBMovie);
        FavoriteRepository favoriteRepository = new FavoriteRepository(favoriteLocalDataSource);
        mPresenter = new FavoritePresenter(favoriteRepository);
        mPresenter.setView(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        initView(view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        showListMovie();
    }

    private void initView(View view) {
        mRecyclerView = view.findViewById(R.id.recycler_card_movie);
    }

    @Override
    public void onItemClick(RecyclerView recyclerView, int id, String mediaType) {
        if (getActivity() != null) {
            ((MainActivity) getActivity()).replaceFragment(R.id.fragment_home, MovieDetailFragment.getNewInstance(id));
        }
    }

    @Override
    public void onItemLongClickListener(int id) {
        mPresenter.removeFromFavorite(id);
        showListMovie();
    }

    @Override
    public void showListMovie() {
        mCardViewAdapter = new CardViewAdapter(mPresenter.loadMoviesIntoFavorite());
        mRecyclerView.setAdapter(mCardViewAdapter);
        mCardViewAdapter.setItemClickListener(this);
        mCardViewAdapter.setItemLongClickListener(this);
    }
}
