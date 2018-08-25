package com.framgia.moviedb_20.screen.detail.cast;

import com.framgia.moviedb_20.data.repository.CastRepository;

public class CastDetailPresenter implements CastDetailContract.Presenter {

    private CastRepository mCastRepository;
    private CastDetailContract.View mView;


    public CastDetailPresenter(CastRepository castRepository) {
        mCastRepository = castRepository;
    }

    @Override
    public void loadCastDetail(int id) {
        mCastRepository.getCastDetail(id);
    }

    @Override
    public void loadCastCredit(int id) {
        mCastRepository.getCastCredit(id);
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void setView(CastDetailContract.View view) {
        mView = view;
    }
}
