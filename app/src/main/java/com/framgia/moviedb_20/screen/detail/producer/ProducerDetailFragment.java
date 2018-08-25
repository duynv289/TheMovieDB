package com.framgia.moviedb_20.screen.detail.producer;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.framgia.moviedb_20.R;
import com.framgia.moviedb_20.data.api.BaseGetDataAsyncTask;
import com.framgia.moviedb_20.data.model.BaseMovie;
import com.framgia.moviedb_20.data.model.MovieDetail;
import com.framgia.moviedb_20.data.model.Producer;
import com.framgia.moviedb_20.data.repository.ProducerRepository;
import com.framgia.moviedb_20.data.source.remote.ProducerRemoteDataSource;
import com.framgia.moviedb_20.screen.adapter.MovieAdapter;
import com.framgia.moviedb_20.screen.adapter.OnItemClickListener;
import com.framgia.moviedb_20.screen.detail.movie.MovieDetailFragment;
import com.framgia.moviedb_20.screen.main.MainActivity;
import com.framgia.moviedb_20.utils.Constants;
import com.framgia.moviedb_20.utils.ViewUtils;

import java.util.List;

public class ProducerDetailFragment extends Fragment implements OnItemClickListener,ProducerDetailContract.View, BaseGetDataAsyncTask.OnGetDataListener {

    private static int mId;
    private List<BaseMovie> mBaseMovies;
    private MovieAdapter mMovieAdapter;
    private ImageView mImageView;
    private TextView mTextViewName;
    private TextView mTextViewHeadquarters;
    private TextView mTextViewHomePage;
    private TextView mTextViewOriginCountry;
    private RecyclerView mRecyclerView;
    private ProducerDetailContract.Presenter mPresenter;

    public static ProducerDetailFragment getNewInstance(int id) {
        mId = id;
        return new ProducerDetailFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ProducerRemoteDataSource remoteDataSource = new ProducerRemoteDataSource(this);
        ProducerRepository repository = new ProducerRepository(remoteDataSource);
        mPresenter = new ProducerDetailPresenter(repository);
        mPresenter.setView(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_producer_detail, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mImageView = view.findViewById(R.id.image_producer);
        mTextViewName = view.findViewById(R.id.text_producer);
        mTextViewHeadquarters = view.findViewById(R.id.text_headquarters);
        mTextViewHomePage = view.findViewById(R.id.text_home_page);
        mTextViewOriginCountry = view.findViewById(R.id.text_origin_country);
        mRecyclerView = view.findViewById(R.id.recycler_cast);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPresenter.getProducer(mId);
        mPresenter.getMovieDetail(String.valueOf(mId));
    }

    @Override
    public void onGetDataSuccess(List data) {
        if (data.get(0) instanceof Producer) {
            mPresenter.fetchData(data);
        } else if (data.get(0) instanceof BaseMovie) {
            mBaseMovies = data;
            mMovieAdapter = new MovieAdapter(mBaseMovies);
            mRecyclerView.setAdapter(mMovieAdapter);
            mMovieAdapter.onItemClickListener(this);
        }
    }

    @Override
    public void onGetDataFailure(Exception e) {

    }

    @Override
    public void showProducerLogo(String logoPath) {
        ViewUtils.loadImageByUrl(mImageView, Constants.DOMAIN_POSTER_IMAGE + logoPath);
    }

    @Override
    public void showName(String name) {
        mTextViewName.setText(name);
    }

    @Override
    public void showHeadquarters(String headquarters) {
        mTextViewHeadquarters.setText(headquarters);
    }

    @Override
    public void showHomPage(String homePage) {
        mTextViewHomePage.setText(homePage);
    }

    @Override
    public void showOriginCountry(String originCountry) {
        mTextViewOriginCountry.setText(originCountry);
    }

    @Override
    public void onItemClick(RecyclerView recyclerView, int id, String mediaType) {
        if(getActivity() != null){
            ((MainActivity)getActivity()).replaceFragment(R.id.fragment_home, MovieDetailFragment.getNewInstance(id));
        }
    }
}
