package com.framgia.moviedb_20.data.source;

import com.framgia.moviedb_20.data.model.BaseMovie;
import com.framgia.moviedb_20.data.model.MovieDetail;

import java.util.List;

public interface FavoriteDataSource {
    interface LocalDataSource {
        void addMovie(BaseMovie baseMovie);

        void removeMovie(int id);

        boolean checkMovieInFavorite(BaseMovie baseMovie);

        List<BaseMovie> getAllMovieFromFavorite();
    }

    interface RemoteDataSource {

    }
}
