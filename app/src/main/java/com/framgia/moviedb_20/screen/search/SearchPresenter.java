package com.framgia.moviedb_20.screen.search;

import com.framgia.moviedb_20.data.repository.SearchRepository;

public class SearchPresenter implements SearchContract.Presenter {

    private SearchContract.View mView;
    private SearchRepository mRepository;

    public SearchPresenter(SearchRepository repository) {
        mRepository = repository;
    }

    @Override
    public void getResultBySearch(String query) {
        mRepository.getResult(query);
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void setView(SearchContract.View view) {
        mView = view;
    }
}
