package com.framgia.moviedb_20.screen.detail.cast;

import com.framgia.moviedb_20.screen.BasePresenter;

public interface CastDetailContract {
    interface View {

        void showProfile(String profilePath);

        void showName(String name);

        void showDateOfBirth(String dateOfBirth);

        void showPlaceOfBirth(String placeOfBirth);

        void showBiography(String biography);

    }

    interface Presenter extends BasePresenter<View> {
        void loadCastDetail(int id);

        void loadCastCredit(int id);
    }
}
