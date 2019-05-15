package com.example.hackernews.activity;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.hackernews.Constants;
import com.example.hackernews.R;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(RobolectricTestRunner.class)
public class ItemActivityTest {

    private ItemActivity activity;
    private RecyclerView recyclerView;
    private CardView cardView;
    private CardView typecardView;
    private TextView date;
    private TextView content;
    private TextView by;
    private TextView type;
    private TextView comments;
    private ProgressBar progress;

    @Before
    public void setUp() throws Exception {
        activity = Robolectric.buildActivity(ItemActivity.class).create().get();
        recyclerView = (RecyclerView) activity.findViewById(R.id.comment_list);
        cardView = (CardView) activity.findViewById(R.id.main_card);
        typecardView = (CardView) activity.findViewById(R.id.type_card);
        date = (TextView) activity.findViewById(R.id.date);
        content = (TextView) activity.findViewById(R.id.content);
        by = (TextView) activity.findViewById(R.id.by);
        type = (TextView) activity.findViewById(R.id.type);
        comments = (TextView) activity.findViewById(R.id.comments);
        progress = (ProgressBar) activity.findViewById(R.id.progress);
    }

    @Test
    public void shouldNotBeNull() {
        assertNotNull(activity);
    }

    @Test
    public void componentsTest() {
        assertNotNull(recyclerView);
        assertNotNull(cardView);
        assertNotNull(typecardView);
        assertNotNull(date);
        assertNotNull(content);
        assertNotNull(by);
        assertNotNull(type);
        assertNotNull(comments);
        assertNotNull(progress);
    }

    @Test
    public void checkActivityTitle() throws Exception {
        assertThat(activity.getTitle()).isNotNull();
    }

    @Test
    public void checkActivityActionBar() throws Exception {
        assertThat(activity.getSupportActionBar()).isNotNull();
    }

    @Test
    public void loadView() {
        by.setText("Sample");
        date.setText("14/05/2019");
        type.setText("STORY");
        comments.setText("Comments");
        assertEquals("Sample", by.getText().toString());
        assertEquals("14/05/2019", date.getText().toString());
        assertEquals("STORY", type.getText().toString());
        assertEquals("Comments", comments.getText().toString());
        assertEquals(progress.getVisibility(), View.GONE);
    }

    @Test
    public void showProgress() {
        progress.setVisibility(View.VISIBLE);
        assertEquals(progress.getVisibility(), View.VISIBLE);
    }

    @Test
    public void hideProgress() {
        progress.setVisibility(View.GONE);
        assertEquals(progress.getVisibility(), View.GONE);
    }

    @Test
    public void testServerReturnSuccess() {
        activity.factory = activity.new TaskFactory() {
            @Override
            public ItemActivity.GetTopicItemTask getTask() {

                return activity.new GetTopicItemTask("item") {
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
            public ItemActivity.GetTopicItemTask getTask() {

                return activity.new GetTopicItemTask("item") {
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
            public ItemActivity.GetTopicItemTask getTask() {

                return activity.new GetTopicItemTask("item") {
                    @Override
                    protected Integer doInBackground(Void... params) {
                        return Constants.INTERNET_DISCONNECTED;
                    }
                };
            }
        };
    }
}