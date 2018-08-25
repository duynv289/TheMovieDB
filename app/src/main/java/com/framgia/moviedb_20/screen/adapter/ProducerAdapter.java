package com.framgia.moviedb_20.screen.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.framgia.moviedb_20.R;
import com.framgia.moviedb_20.data.model.Producer;
import com.framgia.moviedb_20.utils.Constants;
import com.framgia.moviedb_20.utils.ViewUtils;

import java.util.List;

public class ProducerAdapter extends RecyclerView.Adapter<ProducerAdapter.ProducerHolder> {

    private List<Producer> mProducers;
    private OnItemClickListener mListener;

    private RecyclerView mRecyclerView;

    public ProducerAdapter(List<Producer> producers) {
        mProducers = producers;
    }

    public void onItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        mRecyclerView = recyclerView;
    }

    @NonNull
    @Override
    public ProducerHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.item_producer, viewGroup, false);
        return new ProducerHolder(view,mRecyclerView, mProducers, mListener);    }

    @Override
    public void onBindViewHolder(@NonNull ProducerHolder holder, int i) {
        holder.setView(mProducers.get(i));
    }

    @Override
    public int getItemCount() {
        return mProducers != null ? mProducers.size() : 0;
    }

    static class ProducerHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        List<Producer> producers;
        OnItemClickListener listener;
        ImageView mImageView;
        TextView mTextView;
        RecyclerView mRecyclerView;

        public ProducerHolder(@NonNull View itemView, RecyclerView recyclerView,
                              final List<Producer> producers, final OnItemClickListener listener) {
            super(itemView);
            this.producers = producers;
            this.listener = listener;
            mRecyclerView = recyclerView;
            mImageView = itemView.findViewById(R.id.image_producer_logo);
            mTextView = itemView.findViewById(R.id.text_producer_name);
            itemView.setOnClickListener(this);
        }

        private void setView(Producer producer) {
            mTextView.setText(producer.getName());
            ViewUtils.loadImageByUrl(mImageView, Constants.DOMAIN_POSTER_IMAGE +
                    producer.getLogoPath());
        }

        @Override
        public void onClick(View view) {
            if (listener != null) {
                listener.onItemClick(mRecyclerView, producers.get(getAdapterPosition()).getId(),
                        null);
            }
        }
    }
}
