package com.framgia.moviedb_20.screen.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.framgia.moviedb_20.R;
import com.framgia.moviedb_20.data.model.MovieCast;
import com.framgia.moviedb_20.utils.Constants;
import com.framgia.moviedb_20.utils.ViewUtils;

import java.util.List;

public class CastAdapter extends RecyclerView.Adapter<CastAdapter.CastHolder> {

    private List<MovieCast> mMovieCasts;
    private OnItemClickListener mListener;
    private RecyclerView mRecyclerView;

    public CastAdapter(List<MovieCast> movieCasts) {
        mMovieCasts = movieCasts;
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        mRecyclerView = recyclerView;
    }

    public void onItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    @NonNull
    @Override
    public CastHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.item_cast, viewGroup, false);
        return new CastHolder(view, mRecyclerView, mMovieCasts, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CastHolder holder, int i) {
        holder.setView(mMovieCasts.get(i));
    }

    @Override
    public int getItemCount() {
        return mMovieCasts != null ? mMovieCasts.size() : 0;
    }

    static class CastHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        List<MovieCast> movieCasts;
        OnItemClickListener listener;
        ImageView mImageView;
        TextView mTextView;
        RecyclerView mRecyclerView;

        public CastHolder(@NonNull View itemView, RecyclerView recyclerView, final List<MovieCast> movieCasts, final OnItemClickListener listener) {
            super(itemView);
            this.movieCasts = movieCasts;
            this.listener = listener;
            mRecyclerView = recyclerView;
            mImageView = itemView.findViewById(R.id.image_cast_profile);
            mTextView = itemView.findViewById(R.id.text_cast_name);
            itemView.setOnClickListener(this);
        }

        private void setView(MovieCast movieCast) {
            mTextView.setText(movieCast.getCastName());
            ViewUtils.loadImageByUrl(mImageView, Constants.DOMAIN_POSTER_IMAGE + movieCast.getCastProfilePath());
        }

        @Override
        public void onClick(View view) {
            if (listener != null) {
                listener.onItemClick(mRecyclerView, movieCasts.get(getAdapterPosition()).getCastId(),null);
            }
        }
    }
}
