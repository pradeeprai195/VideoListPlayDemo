package com.pradeeprai195.torovideodemo.basic;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pradeeprai195.torovideodemo.R;

import im.ene.toro.PlayerSelector;
import im.ene.toro.widget.Container;

public class BasicListFragment extends Fragment {

    @SuppressWarnings("unused")
    public static BasicListFragment newInstance() {
        Bundle args = new Bundle();
        BasicListFragment fragment = new BasicListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle bundle) {
        return inflater.inflate(R.layout.fragment_basic, container, false);
    }

    Container container;
    LinearLayoutManager layoutManager;
    BasicListAdapter adapter;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);

        container = view.findViewById(R.id.player_container);
        layoutManager = new LinearLayoutManager(getContext());
        container.setLayoutManager(layoutManager);
        adapter = new BasicListAdapter(null);
        container.setAdapter(adapter);
        container.setPlayerSelector(selector);
    }

    @Override
    public void onDestroyView() {
        handler.removeCallbacksAndMessages(null);
        layoutManager = null;
        adapter = null;
        selector = null;
        super.onDestroyView();
    }

    PlayerSelector selector = PlayerSelector.DEFAULT; // visible to user by default.
    final Handler handler = new Handler();  // post a delay due to the visibility change

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            selector = PlayerSelector.DEFAULT;
        } else {
            selector = PlayerSelector.NONE;
        }
        if (container != null) container.setPlayerSelector(selector);
    }
}
