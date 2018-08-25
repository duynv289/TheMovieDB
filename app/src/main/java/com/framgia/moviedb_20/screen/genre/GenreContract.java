package com.framgia.moviedb_20.screen.genre;

import com.framgia.moviedb_20.screen.BasePresenter;

public interface GenreContract {
    interface View {

    }

    interface Presenter extends BasePresenter<View>{
        void getListMovieByGenre(int id);
    }
}
