package com.pradeeprai195.torovideodemo.basic;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pradeeprai195.torovideodemo.R;

import im.ene.toro.widget.PressablePlayerSelector;

class BasicListAdapter extends RecyclerView.Adapter<BasicPlayerViewHolder> {

    @SuppressWarnings("MismatchedQueryAndUpdateOfCollection") //
    private MediaList mediaList = new MediaList();

    @Nullable
    private final PressablePlayerSelector selector;

    BasicListAdapter(@Nullable PressablePlayerSelector selector) {
        this.selector = selector;
    }

    @NonNull
    @Override
    public BasicPlayerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_holder_exoplayer_basic, parent, false);
        BasicPlayerViewHolder viewHolder = new BasicPlayerViewHolder(view, this.selector);
        if (this.selector != null) viewHolder.itemView.setOnLongClickListener(this.selector);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BasicPlayerViewHolder holder, int position) {
        holder.bind(mediaList.get(position));
    }

    @Override
    public int getItemCount() {
        return mediaList.size();
    }
}
