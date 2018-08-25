package com.framgia.moviedb_20.screen.detail.cast;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.framgia.moviedb_20.R;
import com.framgia.moviedb_20.data.api.BaseGetDataAsyncTask;
import com.framgia.moviedb_20.data.model.CastCredit;
import com.framgia.moviedb_20.data.model.CastDetail;
import com.framgia.moviedb_20.data.repository.CastRepository;
import com.framgia.moviedb_20.data.source.remote.CastRemoteDataSource;
import com.framgia.moviedb_20.screen.adapter.MovieAdapter;
import com.framgia.moviedb_20.screen.adapter.OnItemClickListener;
import com.framgia.moviedb_20.screen.detail.movie.MovieDetailFragment;
import com.framgia.moviedb_20.screen.main.MainActivity;
import com.framgia.moviedb_20.utils.Constants;
import com.framgia.moviedb_20.utils.ViewUtils;

import java.util.List;

public class CastDetailFragment extends Fragment implements View.OnClickListener, CastDetailContract.View, BaseGetDataAsyncTask.OnGetDataListener, OnItemClickListener {

    private static int mId;
    private CastDetailPresenter mPresenter;
    private MovieAdapter mMovieAdapter;
    private List<CastCredit> mCastCredit;
    private List<CastDetail> mCastDetails;
    private ImageView mImageView;
    private TextView mTextViewName;
    private TextView mTextViewBirthDay;
    private TextView mTextViewPlaceOfBirth;
    private TextView mTextViewBiography;
    private RecyclerView mRecyclerViewKnowFor;

    public static CastDetailFragment getNewInstance(int id) {
        mId = id;
        return new CastDetailFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CastRemoteDataSource remoteDataSource = new CastRemoteDataSource(this);
        CastRepository repository = new CastRepository(remoteDataSource);
        mPresenter = new CastDetailPresenter(repository);
        mPresenter.setView(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cast_detail, container, false);
        initView(view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPresenter.loadCastDetail(mId);
    }

    private void initView(View view) {
        mImageView = view.findViewById(R.id.image_profile_path);
        mTextViewName = view.findViewById(R.id.text_cast_name);
        mTextViewBirthDay = view.findViewById(R.id.text_date_of_birth);
        mTextViewPlaceOfBirth = view.findViewById(R.id.text_place_of_birth);
        mTextViewBiography = view.findViewById(R.id.text_biography);
        mRecyclerViewKnowFor = view.findViewById(R.id.recycler_movie);
    }

    @Override
    public void onGetDataSuccess(List data) {

        if (data.get(0) instanceof CastDetail) {
            setCast(data);
            mPresenter.loadCastCredit(mId);
        } else if (data.get(0) instanceof CastCredit) {
            mCastCredit = data;
            CastCredit castCredit = mCastCredit.get(0);
            mMovieAdapter = new MovieAdapter(castCredit.getBaseMovies());
            mRecyclerViewKnowFor.setAdapter(mMovieAdapter);
            mMovieAdapter.onItemClickListener(this);
        }
    }

    @Override
    public void onGetDataFailure(Exception e) {
        // TODO: 23/08/2018
    }

    private void setCast(List data) {
        mCastDetails = data;
        CastDetail castDetail = mCastDetails.get(0);
        showProfile(castDetail.getProfilePath());
        showName(Constants.STRING_COLON + castDetail.getName());
        showDateOfBirth(Constants.STRING_COLON + castDetail.getBirthday());
        showPlaceOfBirth(castDetail.getPlaceOfBirth());
        showBiography(castDetail.getBiography());
    }

    @Override
    public void showProfile(String profilePath) {
        ViewUtils.loadImageByUrl(mImageView, Constants.DOMAIN_POSTER_IMAGE + profilePath);
    }

    @Override
    public void showName(String name) {
        mTextViewName.setText(name);
    }

    @Override
    public void showDateOfBirth(String dateOfBirth) {
        mTextViewBirthDay.setText(dateOfBirth);
    }

    @Override
    public void showPlaceOfBirth(String placeOfBirth) {
        mTextViewPlaceOfBirth.setText(placeOfBirth);
    }

    @Override
    public void showBiography(String biography) {
        mTextViewBiography.setText(biography);
        mTextViewBiography.setOnClickListener(this);
    }

    @Override
    public void onItemClick(RecyclerView recyclerView, int id, String mediaType) {
        if(getActivity() != null){
            ((MainActivity)getActivity()).replaceFragment(R.id.fragment_home, MovieDetailFragment.getNewInstance(id));
        }
    }

    @Override
    public void onClick(View view) {
        mTextViewBiography.setMaxLines(Constants.MAX_LINES);
    }
}
