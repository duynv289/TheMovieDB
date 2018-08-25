package com.framgia.moviedb_20.screen.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.framgia.moviedb_20.R;
import com.framgia.moviedb_20.data.model.SearchResult;
import com.framgia.moviedb_20.utils.Constants;
import com.framgia.moviedb_20.utils.ViewUtils;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {

    private static final String MEDIA_TYPE_PERSON = "person";
    private List<SearchResult> mSearchResults;
    private OnItemClickListener mListener;
    private LayoutInflater mInflater;

    public SearchAdapter(List<SearchResult> searchResults) {
        mSearchResults = searchResults;
    }

    public void setItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (mInflater == null) {
            mInflater = LayoutInflater.from(viewGroup.getContext());
        }
        View view = mInflater.inflate(R.layout.item_search_result, viewGroup, false);
        return new SearchViewHolder(view, mSearchResults, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int i) {
        holder.bindData(mSearchResults.get(i));
    }

    @Override
    public int getItemCount() {
        return mSearchResults != null ? mSearchResults.size() : 0;
    }

    static class SearchViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView mImageView;
        TextView mTextView;
        List<SearchResult> mResults;
        OnItemClickListener mListener;

        public SearchViewHolder(@NonNull View itemView, List<SearchResult> results,
                                OnItemClickListener listener) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.image_search_result);
            mTextView = itemView.findViewById(R.id.text_search_result);
            mResults = results;
            mListener = listener;
            itemView.setOnClickListener(this);
        }

        private void bindData(SearchResult result) {
            String strName = result.getName() + result.getTitle();
            mTextView.setText(strName);
            if (result.getMediaType().equals(MEDIA_TYPE_PERSON)) {
                ViewUtils.loadImageByUrl(mImageView, Constants.DOMAIN_POSTER_IMAGE +
                        result.getProfilePath());
            } else {
                ViewUtils.loadImageByUrl(mImageView, Constants.DOMAIN_POSTER_IMAGE +
                        result.getPosterPath());
            }
        }

        @Override
        public void onClick(View view) {
            if (mListener != null) {
                mListener.onItemClick(null, mResults.get(getAdapterPosition()).getId()
                        , mResults.get(getAdapterPosition()).getMediaType());
            }
        }
    }
}
