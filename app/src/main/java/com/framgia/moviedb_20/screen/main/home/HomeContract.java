package com.framgia.moviedb_20.screen.main.home;

import com.framgia.moviedb_20.screen.BasePresenter;

public interface HomeContract {

    interface View {

        void showUpComing();

        void showPopular();

        void showNowPlaying();

        void showTopRated();

        void showProgress();

        void hideProgress();

    }

    interface Presenter extends BasePresenter<View> {

        void setAdapter(int tag);

        void loadUpComing(int tag);

        void loadPopular(int tag);

        void loadNowPlaying(int tag);

        void loadTopRated(int tag);

    }
}
