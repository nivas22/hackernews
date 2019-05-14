package com.example.hackernews.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;

import java.util.Arrays;
import java.util.List;

import static org.assertj.android.api.Assertions.assertThat;
import static org.junit.Assert.*;

@RunWith(RobolectricTestRunner.class)
public class TopicsAdapterTest {

    private Context context;
    private TopicsAdapter adapter;
    private List<String> topics;

    @Before
    public void setUp() throws Exception {
        context = RuntimeEnvironment.application;
        topics = Arrays.asList("One", "Two", "Three");
        adapter = new TopicsAdapter(topics);
    }

    @Test()
    public void testConstructor() {
        new TopicsAdapter(topics);
    }

    @Test(expected=NullPointerException.class)
    public void testConstructorNullContext() {
        new TopicsAdapter(null);
    }

    @Test
    public void setListener() {
        adapter.setListener(new TopicsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, String item) {
            }
        });
        assertNotNull(adapter.getListener());
    }

    @Test
    public void getListener() {
        adapter.setListener(new TopicsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, String item) {
            }
        });
        assertNotNull(adapter.getListener());
    }

    @Test
    public void getTopics() {
        assertEquals(topics.size(), adapter.getTopics().size());
    }

    @Test
    public void setTopics() {
        topics = Arrays.asList("One", "Two", "Three", "four");
        adapter.setTopics(topics);
        assertEquals(topics.size(), adapter.getTopics().size());
    }

    @Test
    public void getItemCount() {
        assertEquals(adapter.getItemCount(), 3);
    }

    @Test
    public void onCreateViewHolder() {
        RecyclerView parent = new RecyclerView(context);
        parent.setLayoutManager(new LinearLayoutManager(context));

        TopicsAdapter.ViewHolder viewHolder = adapter.onCreateViewHolder(parent, 0);
        adapter.onBindViewHolder(viewHolder, 0);
        adapter.onBindViewHolder(viewHolder, 1);
        assertNotNull(viewHolder.topicName);
    }

    @Test
    public void onBindViewHolder() {
        RecyclerView parent = new RecyclerView(context);
        parent.setLayoutManager(new LinearLayoutManager(context));

        TopicsAdapter.ViewHolder viewHolder = adapter.onCreateViewHolder(parent, 0);
        adapter.onBindViewHolder(viewHolder, 0);

        adapter.onBindViewHolder(viewHolder, 1);

        // JUnit Assertion
        assertEquals("Two", viewHolder.topicName.getText().toString());
        assertThat(viewHolder.topicName).isVisible().containsText("Two");
    }

    @Test
    public void getItemAtPosition() {
        assertEquals(adapter.getItemAtPosition(0), "One");
    }
}