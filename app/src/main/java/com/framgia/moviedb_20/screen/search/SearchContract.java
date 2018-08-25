package com.framgia.moviedb_20.screen.search;

import com.framgia.moviedb_20.screen.BasePresenter;

public interface SearchContract {
    interface View {

    }

    interface Presenter extends BasePresenter<View> {
        void getResultBySearch(String query);
    }
}
