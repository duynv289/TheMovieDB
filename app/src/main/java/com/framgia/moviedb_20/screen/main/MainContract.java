package com.framgia.moviedb_20.screen.main;


import com.framgia.moviedb_20.screen.BasePresenter;

public interface MainContract {

    interface View {

    }

    interface Presenter extends BasePresenter<View> {
        void loadGenres();

    }

}
