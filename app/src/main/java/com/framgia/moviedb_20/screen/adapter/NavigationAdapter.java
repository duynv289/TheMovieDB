package com.framgia.moviedb_20.screen.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.framgia.moviedb_20.R;
import com.framgia.moviedb_20.data.model.Genre;

import java.util.List;

public class NavigationAdapter extends RecyclerView.Adapter<NavigationAdapter.GenreViewHolder> {

    private List<Genre> mGenres;
    private OnItemClickListener mListener;
    private RecyclerView mRecyclerView;

    public NavigationAdapter(List<Genre> genres) {
        this.mGenres = genres;

    }

    public void setItemListener(OnItemClickListener listener) {
        mListener = listener;
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        mRecyclerView = recyclerView;
    }

    @NonNull
    @Override
    public GenreViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.item_nav, viewGroup, false);
        return new GenreViewHolder(view, mRecyclerView, mGenres, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull GenreViewHolder holder, int i) {
        holder.bindData(mGenres.get(i));
    }

    @Override
    public int getItemCount() {
        return mGenres != null ? mGenres.size() : 0;
    }

    static class GenreViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView mTextView;
        List<Genre> mGenres;
        OnItemClickListener mListener;
        RecyclerView mRecyclerView;

        public GenreViewHolder(@NonNull View itemView, RecyclerView recyclerView, List<Genre> genres, OnItemClickListener listener) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.text_genre);
            mGenres = genres;
            mListener = listener;
            mRecyclerView = recyclerView;
            itemView.setOnClickListener(this);
        }

        private void bindData(Genre genre) {
            mTextView.setText(genre.getName());
        }

        @Override
        public void onClick(View view) {
            if (mListener != null) {
                mListener.onItemClick(mRecyclerView, mGenres.get(getAdapterPosition()).getId(),null);
            }
        }
    }
}
