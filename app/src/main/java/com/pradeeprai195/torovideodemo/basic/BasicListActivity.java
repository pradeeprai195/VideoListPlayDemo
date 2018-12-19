package com.pradeeprai195.torovideodemo.basic;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.pradeeprai195.torovideodemo.R;

import im.ene.toro.widget.Container;
import im.ene.toro.widget.PressablePlayerSelector;

public class BasicListActivity extends AppCompatActivity {

  Container container;
  LinearLayoutManager layoutManager;
  BasicListAdapter adapter;

  PressablePlayerSelector selector;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.fragment_basic);

    container = findViewById(R.id.player_container);
    layoutManager = new LinearLayoutManager(this);
    container.setLayoutManager(layoutManager);
    selector = new PressablePlayerSelector(container);
    container.setPlayerSelector(selector);

    adapter = new BasicListAdapter(selector);
    container.setAdapter(adapter);
    container.setNestedScrollingEnabled(false);
  }

  @Override protected void onDestroy() {
    layoutManager = null;
    adapter = null;
    selector = null;
    super.onDestroy();
  }
}
