package com.framgia.moviedb_20.screen.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.framgia.moviedb_20.R;
import com.framgia.moviedb_20.data.model.BaseMovie;
import com.framgia.moviedb_20.utils.Constants;
import com.framgia.moviedb_20.utils.ViewUtils;

import java.util.List;

public class CardViewAdapter extends RecyclerView.Adapter<CardViewAdapter.CardViewHolder> {

    private List<BaseMovie> mBaseMovies;
    private OnItemClickListener mItemClickListener;
    private OnItemLongClickListener mItemLongClickListener;
    private RecyclerView mRecyclerView;

    public CardViewAdapter(List<BaseMovie> baseMovies) {
        mBaseMovies = baseMovies;
    }

    public void setItemClickListener(OnItemClickListener listener) {
        mItemClickListener = listener;
    }

    public void setItemLongClickListener(OnItemLongClickListener listener) {
        mItemLongClickListener = listener;
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        mRecyclerView = recyclerView;
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.item_cardview_movie, viewGroup, false);
        return new CardViewHolder(view, mRecyclerView, mBaseMovies, mItemClickListener,mItemLongClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int i) {
        holder.bindData(mBaseMovies.get(i));
    }

    @Override
    public int getItemCount() {
        return mBaseMovies != null ? mBaseMovies.size() : 0;
    }

    static class CardViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        private TextView mTextView;
        private ImageView mImageView;
        private List<BaseMovie> mBaseMovies;
        private RecyclerView mRecyclerView;
        private OnItemClickListener mItemClickListener;
        private OnItemLongClickListener mItemLongClickListener;

        public CardViewHolder(@NonNull View itemView, RecyclerView recyclerView,
                              List<BaseMovie> movies, OnItemClickListener itemClickListener,
                              OnItemLongClickListener itemLongClickListener) {
            super(itemView);
            mRecyclerView = recyclerView;
            mBaseMovies = movies;
            mItemClickListener = itemClickListener;
            mItemLongClickListener = itemLongClickListener;
            mTextView = itemView.findViewById(R.id.text_name);
            mImageView = itemView.findViewById(R.id.image_poster);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        private void bindData(BaseMovie baseMovie) {
            mTextView.setText(baseMovie.getTitle());
            ViewUtils.loadImageByUrl(mImageView, Constants.DOMAIN_POSTER_IMAGE + baseMovie.getPosterPath());
        }

        @Override
        public void onClick(View view) {
            if (mItemClickListener != null) {
                mItemClickListener.onItemClick(mRecyclerView, mBaseMovies.get(getAdapterPosition()).getId(), null);
            }
        }

        @Override
        public boolean onLongClick(View view) {
            if (mItemLongClickListener != null) {
                mItemLongClickListener.onItemLongClickListener(mBaseMovies.get(getAdapterPosition()).getId());
            }
            return true;
        }
    }
}
