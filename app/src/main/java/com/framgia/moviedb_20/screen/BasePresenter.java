package com.framgia.moviedb_20.screen;

public interface BasePresenter<T> {

    void onStart();

    void onStop();

    void setView(T view);
}
