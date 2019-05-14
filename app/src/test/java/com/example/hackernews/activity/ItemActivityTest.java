package com.example.hackernews.activity;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.hackernews.R;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

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
    public void loadView() {

    }
}