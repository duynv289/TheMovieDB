package com.framgia.moviedb_20.screen.favorite;


import com.framgia.moviedb_20.data.model.BaseMovie;
import com.framgia.moviedb_20.data.model.MovieDetail;
import com.framgia.moviedb_20.data.repository.FavoriteRepository;

import java.util.List;

public class FavoritePresenter implements FavoriteContract.Presenter {

    private FavoriteContract.View mView;
    private FavoriteRepository mFavoriteRepository;

    public FavoritePresenter(FavoriteRepository favoriteRepository) {
        mFavoriteRepository = favoriteRepository;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void setView(FavoriteContract.View view) {
        mView = view;
    }

    @Override
    public List<BaseMovie> loadMoviesIntoFavorite() {
        return mFavoriteRepository.getAllMovieFromFavorite();
    }

    @Override
    public void removeFromFavorite(int id) {
        mFavoriteRepository.removeMovie(id);
    }
}
