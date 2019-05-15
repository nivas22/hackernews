package com.example.hackernews.activity;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;

import com.example.hackernews.Constants;
import com.example.hackernews.R;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(RobolectricTestRunner.class)
public class HomeActivityTest {

    private HomeActivity activity;
    private RecyclerView recyclerView;
    private ProgressBar progress;
    private SwipeRefreshLayout refreshLayout;
    private HomeActivity.TaskFactory taskFactory;

    @Before
    public void setUp() {
        activity = Robolectric.buildActivity(HomeActivity.class).create().get();
        recyclerView = (RecyclerView) activity.findViewById(R.id.topic_list);
        progress = (ProgressBar) activity.findViewById(R.id.progress);
        refreshLayout = (SwipeRefreshLayout) activity.findViewById(R.id.swipe_container);
        taskFactory = activity.new TaskFactory();
    }

    @Test
    public void shouldNotBeNull() {
        assertNotNull(activity);
        assertNotNull(taskFactory);
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

    @Test
    public void checkActivityTitle() {
        assertThat(activity.getTitle()).isNotNull();
        assertThat(activity.getTitle().toString()).isEqualTo(activity.getString(R.string.app_name));
    }

    @Test
    public void testServerReturnSuccess() {
        activity.factory = activity.new TaskFactory() {
            @Override
            public HomeActivity.GetTopStoriesTask getTask() {

                return activity.new GetTopStoriesTask(false) {
                    @Override
                    protected Integer doInBackground(Void... params) {
                        return Constants.SUCCESS;
                    }
                };
            }
        };
    }

    @Test
    public void testServerReturnFail() {
        activity.factory = activity.new TaskFactory() {
            @Override
            public HomeActivity.GetTopStoriesTask getTask() {

                return activity.new GetTopStoriesTask(false) {
                    @Override
                    protected Integer doInBackground(Void... params) {
                        return Constants.FAILURE;
                    }
                };
            }
        };
    }

    @Test
    public void testServerReturnDisconnect() {
        activity.factory = activity.new TaskFactory() {
            @Override
            public HomeActivity.GetTopStoriesTask getTask() {

                return activity.new GetTopStoriesTask(false) {
                    @Override
                    protected Integer doInBackground(Void... params) {
                        return Constants.INTERNET_DISCONNECTED;
                    }
                };
            }
        };
    }
}