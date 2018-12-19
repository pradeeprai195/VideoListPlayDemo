package com.pradeeprai195.torovideodemo.basic;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.android.exoplayer2.ui.PlayerView;
import com.pradeeprai195.torovideodemo.R;

import im.ene.toro.ToroPlayer;
import im.ene.toro.ToroUtil;
import im.ene.toro.exoplayer.ExoPlayerDispatcher;
import im.ene.toro.exoplayer.ExoPlayerViewHelper;
import im.ene.toro.helper.ToroPlayerHelper;
import im.ene.toro.media.PlaybackInfo;
import im.ene.toro.widget.Container;
import im.ene.toro.widget.PressablePlayerSelector;

@SuppressWarnings({"WeakerAccess", "unused"})
class BasicPlayerViewHolder extends RecyclerView.ViewHolder implements ToroPlayer {

    private static final String TAG = "Toro:Basic:Holder";

    static final int LAYOUT_RES = R.layout.view_holder_exoplayer_basic;

    ToroPlayerHelper helper;
    Uri mediaUri;
    PlayerView playerView;

    public BasicPlayerViewHolder(View itemView, PressablePlayerSelector selector) {
        super(itemView);
        playerView = itemView.findViewById(R.id.player);
        if (selector != null)
            playerView.setControlDispatcher(new ExoPlayerDispatcher(selector, this));
    }

    @NonNull
    @Override
    public View getPlayerView() {
        return playerView;
    }

    @NonNull
    @Override
    public PlaybackInfo getCurrentPlaybackInfo() {
        return helper != null ? helper.getLatestPlaybackInfo() : new PlaybackInfo();
    }

    @Override
    public void initialize(@NonNull Container container, @NonNull PlaybackInfo playbackInfo) {
        if (helper == null) {
            helper = new ExoPlayerViewHelper(this, mediaUri);
        }
        helper.initialize(container, playbackInfo);
    }

    @Override
    public void play() {
        if (helper != null) helper.play();
    }

    @Override
    public void pause() {
        if (helper != null) helper.pause();
    }

    @Override
    public boolean isPlaying() {
        return helper != null && helper.isPlaying();
    }

    @Override
    public void release() {
        if (helper != null) {
            helper.release();
            helper = null;
        }
    }

    @Override
    public boolean wantsToPlay() {
        return ToroUtil.visibleAreaOffset(this, itemView.getParent()) >= 0.85;
    }

    @Override
    public int getPlayerOrder() {
        return getAdapterPosition();
    }

    @Override
    public String toString() {
        return "ExoPlayer{" + hashCode() + " " + getAdapterPosition() + "}";
    }

    void bind(Content.Media media) {
        this.mediaUri = media.mediaUri;
    }
}