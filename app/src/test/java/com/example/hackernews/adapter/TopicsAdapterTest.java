package com.example.hackernews.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hackernews.BuildConfig;
import com.example.hackernews.R;
import com.example.hackernews.RobolectricGradleTestRunner;
import com.example.hackernews.activity.HomeActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.sql.Array;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(RobolectricGradleTestRunner.class)
public class TopicsAdapterTest {

    private TopicsAdapter.ViewHolder holder;
    private TopicsAdapter adapter;
    private TopicsAdapter.OnItemClickListener listener;


    @Before
    public void setUp() throws Exception {
        adapter = Mockito.mock(TopicsAdapter.class);

        List<String> topics = Arrays.asList("One", "Two", "Three");
        adapter.setTopics(topics);
        this.listener = new TopicsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(String item) {

            }
        };
        adapter.setListener(listener);
        this.adapter = new TopicsAdapter(topics, listener);
    }

    @Test
    public void setTopics() {
        List<String> topics = Arrays.asList("One", "Two", "Three");
        adapter.setTopics(topics);
        assertEquals(topics.size(), adapter.getTopics().size());
    }

    @Test
    public void getItemCount() {
        assertEquals(adapter.getItemCount(), 3);
    }

    @Test
    public void getItemAtPosition() {
        assertEquals(adapter.getItemAtPosition(0), "One");
    }

//    @Test
//    public void onCreateViewHolder() {
//    }
//
    @Test
    public void onBindViewHolder() {
        LayoutInflater inflater = (LayoutInflater) RuntimeEnvironment.application.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View recyclerView = inflater.inflate(R.layout.topic_item_layout, null, false);
        holder = new TopicsAdapter.ViewHolder(recyclerView);
        adapter.onBindViewHolder(holder, 0);
        assertEquals(holder.topicName.getText().toString(), "One");
    }

}