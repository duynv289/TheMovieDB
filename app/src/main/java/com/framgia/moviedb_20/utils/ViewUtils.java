package com.framgia.moviedb_20.utils;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.framgia.moviedb_20.R;

public final class ViewUtils {

    private ViewUtils() {
        // No-op
    }

    public static void loadImageByUrl(ImageView imageView, String url) {
        Glide.with(imageView.getContext())
                .load(url)
                .apply(RequestOptions.placeholderOf(R.drawable.background_progressbar))
                .apply(RequestOptions.errorOf(R.drawable.ic_launcher_background))
                .into(imageView);
    }
}
