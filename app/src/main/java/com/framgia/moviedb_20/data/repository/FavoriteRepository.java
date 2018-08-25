package com.framgia.moviedb_20.data.repository;

import com.framgia.moviedb_20.data.model.BaseMovie;
import com.framgia.moviedb_20.data.model.MovieDetail;
import com.framgia.moviedb_20.data.source.FavoriteDataSource;

import java.util.List;

public class FavoriteRepository implements FavoriteDataSource.LocalDataSource, FavoriteDataSource.RemoteDataSource {

    private FavoriteDataSource.LocalDataSource mLocalDataSource;

    public FavoriteRepository(FavoriteDataSource.LocalDataSource localDataSource) {
        mLocalDataSource = localDataSource;
    }

    @Override
    public void addMovie(BaseMovie baseMovie) {
        mLocalDataSource.addMovie(baseMovie);
    }

    @Override
    public void removeMovie(int id) {
        mLocalDataSource.removeMovie(id);
    }

    @Override
    public boolean checkMovieInFavorite(BaseMovie baseMovie) {
        return mLocalDataSource.checkMovieInFavorite(baseMovie);
    }

    @Override
    public List<BaseMovie> getAllMovieFromFavorite() {
        return mLocalDataSource.getAllMovieFromFavorite();
    }

}
