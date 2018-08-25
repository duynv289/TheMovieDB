package com.framgia.moviedb_20.screen.genre;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.framgia.moviedb_20.R;
import com.framgia.moviedb_20.data.api.BaseGetDataAsyncTask;
import com.framgia.moviedb_20.data.model.BaseMovie;
import com.framgia.moviedb_20.data.repository.MovieRepository;
import com.framgia.moviedb_20.data.source.remote.MovieRemoteDataSource;
import com.framgia.moviedb_20.screen.adapter.CardViewAdapter;
import com.framgia.moviedb_20.screen.adapter.OnItemClickListener;
import com.framgia.moviedb_20.screen.adapter.OnItemLongClickListener;
import com.framgia.moviedb_20.screen.detail.movie.MovieDetailFragment;
import com.framgia.moviedb_20.screen.main.MainActivity;

import java.util.List;

public class GenreFragment extends Fragment implements BaseGetDataAsyncTask.OnGetDataListener<BaseMovie>, OnItemClickListener, OnItemLongClickListener {

    private static int mId;
    private CardViewAdapter mCardViewAdapter;
    private RecyclerView mRecyclerView;

    private GenreContract.Presenter mPresenter;

    public static GenreFragment getNewInstance(int id) {
        mId = id;
        return new GenreFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MovieRemoteDataSource remoteDataSource = new MovieRemoteDataSource(this);
        MovieRepository repository = new MovieRepository(remoteDataSource);
        mPresenter = new GenrePresenter(repository);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        initView(view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPresenter.getListMovieByGenre(mId);
    }

    private void initView(View view) {
        mRecyclerView = view.findViewById(R.id.recycler_card_movie);
    }

    @Override
    public void onGetDataSuccess(List<BaseMovie> data) {
        mCardViewAdapter = new CardViewAdapter(data);
        mCardViewAdapter.setItemClickListener(this);
        mCardViewAdapter.setItemLongClickListener(this);
        mRecyclerView.setAdapter(mCardViewAdapter);
    }

    @Override
    public void onGetDataFailure(Exception e) {

    }

    @Override
    public void onItemClick(RecyclerView recyclerView, int id, String mediaType) {
        if (getActivity() != null) {
            ((MainActivity) getActivity()).replaceFragment(R.id.fragment_home, MovieDetailFragment.getNewInstance(id));
        }
    }

    @Override
    public void onItemLongClickListener(int id) {
        if (getActivity() != null) {
            ((MainActivity) getActivity()).replaceFragment(R.id.fragment_home, MovieDetailFragment.getNewInstance(id));
        }
    }
}
