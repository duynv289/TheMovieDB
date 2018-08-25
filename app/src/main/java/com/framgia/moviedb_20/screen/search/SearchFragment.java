package com.framgia.moviedb_20.screen.search;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.framgia.moviedb_20.R;
import com.framgia.moviedb_20.data.api.BaseGetDataAsyncTask;
import com.framgia.moviedb_20.data.model.SearchResult;
import com.framgia.moviedb_20.data.repository.SearchRepository;
import com.framgia.moviedb_20.data.source.remote.SearchRemoteDataSource;
import com.framgia.moviedb_20.screen.adapter.OnItemClickListener;
import com.framgia.moviedb_20.screen.adapter.SearchAdapter;
import com.framgia.moviedb_20.screen.detail.cast.CastDetailFragment;
import com.framgia.moviedb_20.screen.detail.movie.MovieDetailFragment;
import com.framgia.moviedb_20.screen.main.MainActivity;

import java.util.List;

public class SearchFragment extends Fragment implements OnItemClickListener, BaseGetDataAsyncTask.OnGetDataListener<SearchResult>, SearchContract.View {

    private static String mQuery;
    private SearchAdapter mSearchAdapter;
    private RecyclerView mRecyclerView;
    private SearchContract.Presenter mPresenter;
    private static OnItemSelectedListener sOnItemSelectedListener;
    private static final String MEDIA_TYPE_PERSON = "person";
    private static final String MEDIA_TYPE_MOVIE = "movie";
    public static SearchFragment getNewInstance(String query) {
        mQuery = query;
        return new SearchFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SearchRemoteDataSource remoteDataSource = new SearchRemoteDataSource(this);
        SearchRepository repository = new SearchRepository(remoteDataSource);
        mPresenter = new SearchPresenter(repository);
        mPresenter.setView(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        mRecyclerView = view.findViewById(R.id.recycler_search);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPresenter.getResultBySearch(mQuery);
    }

    @Override
    public void onGetDataSuccess(List<SearchResult> data) {
        mSearchAdapter = new SearchAdapter(data);
        mSearchAdapter.setItemClickListener(this);
        mRecyclerView.setAdapter(mSearchAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void onGetDataFailure(Exception e) {
        // TODO: 22/08/2018
    }

    @Override
    public void onItemClick(RecyclerView recyclerView, int id, String mediaType) {
        if (getActivity() != null) {
            if(mediaType.equals(MEDIA_TYPE_PERSON)) {
                ((MainActivity) getActivity()).replaceFragment(R.id.fragment_home, CastDetailFragment.getNewInstance(id));
            }else if (mediaType.equals(MEDIA_TYPE_MOVIE)){
                ((MainActivity) getActivity()).replaceFragment(R.id.fragment_home, MovieDetailFragment.getNewInstance(id));
            }
        }
    }
    public interface OnItemSelectedListener{
        void setOnItemSelected(int id);
    }
}
