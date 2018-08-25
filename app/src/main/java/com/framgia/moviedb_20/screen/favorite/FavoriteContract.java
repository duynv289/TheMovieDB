package com.framgia.moviedb_20.screen.favorite;

import com.framgia.moviedb_20.data.model.BaseMovie;
import com.framgia.moviedb_20.screen.BasePresenter;

import java.util.List;

public interface FavoriteContract {
    interface View {
        void showListMovie();
    }

    interface Presenter extends BasePresenter<View> {
        List<BaseMovie> loadMoviesIntoFavorite();

        void removeFromFavorite(int id);
    }
}
