package com.example.hackernews.activity;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;

import com.example.hackernews.R;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(RobolectricTestRunner.class)
public class HomeActivityTest {

    private HomeActivity activity;
    private RecyclerView recyclerView;
    private ProgressBar progress;
    private SwipeRefreshLayout refreshLayout;
    private List<String> transcript;

    @Before
    public void setUp() throws Exception {
        activity = Robolectric.buildActivity(HomeActivity.class).create().get();
        recyclerView = (RecyclerView) activity.findViewById(R.id.topic_list);
        progress = (ProgressBar) activity.findViewById(R.id.progress);
        refreshLayout = (SwipeRefreshLayout) activity.findViewById(R.id.swipe_container);
    }

    @Test
    public void shouldNotBeNull() {
        assertNotNull(activity);
    }

    @Test
    public void componentsTest() {
        assertNotNull(recyclerView);
        assertNotNull(progress);
        assertNotNull(refreshLayout);
    }

    @Test
    public void checkTopics() {
        List<String> topics = activity.getTopics();
        assertNotNull(topics);
        assertEquals(0, topics.size());
    }
}