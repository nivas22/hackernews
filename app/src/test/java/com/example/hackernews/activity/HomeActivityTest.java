package com.example.hackernews.activity;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;

import com.example.hackernews.R;
import com.example.hackernews.adapter.TopicsAdapter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.shadows.ShadowApplication;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(RobolectricTestRunner.class)
public class HomeActivityTest {

    private HomeActivity activity;

    @Before
    public void setUp() throws Exception {
        activity = Robolectric.buildActivity(HomeActivity.class).create().get();
    }

    @Test
    public void componentsTest() {
        RecyclerView recyclerView = (RecyclerView) activity.findViewById(R.id.topic_list);
        ProgressBar progress = (ProgressBar) activity.findViewById(R.id.progress);
        SwipeRefreshLayout refreshLayout = (SwipeRefreshLayout) activity.findViewById(R.id.swipe_container);

        assertNotNull(recyclerView);
        assertNotNull(progress);
        assertNotNull(refreshLayout);
    }

    @Test
    public void checkTopics() {
        List<String> topics = activity.getTopics();
        assertNotNull(topics);
        assertEquals(0,topics.size());
    }
}