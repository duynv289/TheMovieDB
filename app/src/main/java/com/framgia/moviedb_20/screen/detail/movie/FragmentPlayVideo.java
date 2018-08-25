package com.framgia.moviedb_20.screen.detail.movie;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.framgia.moviedb_20.BuildConfig;
import com.framgia.moviedb_20.R;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;

public class FragmentPlayVideo extends Fragment implements YouTubePlayer.OnInitializedListener {
    private static String mMovieKey;
    private YouTubePlayerFragment videoView;

    public static FragmentPlayVideo getNewInstance(String movieKey) {
        mMovieKey = movieKey;
        return new FragmentPlayVideo();
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_player, container, false);
        if(videoView == null){
            videoView = (YouTubePlayerFragment) getActivity().getFragmentManager().findFragmentById(R.id.fragment_youtube);
            videoView.initialize(BuildConfig.YOUTUBE_KEY, this);
        }
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        videoView = (YouTubePlayerFragment) getActivity().getFragmentManager().findFragmentById(R.id.fragment_youtube);
        if (videoView != null)
            getActivity().getFragmentManager().beginTransaction().remove(videoView).commit();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        if (mConvertView != null)
//            getChildFragmentManager().beginTransaction().remove(this).commit();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (videoView != null)
            getActivity().getFragmentManager().beginTransaction().remove(videoView).commit();
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        youTubePlayer.cueVideo(mMovieKey);
        youTubePlayer.setShowFullscreenButton(false);
        youTubePlayer.play();
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        // TODO: 22/08/2018  
    }
}
