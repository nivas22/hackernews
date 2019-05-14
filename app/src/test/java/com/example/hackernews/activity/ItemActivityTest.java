package com.example.hackernews.activity;

import android.support.v4.widget.SwipeRefreshLayout;
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

import static org.junit.Assert.*;

@RunWith(RobolectricTestRunner.class)
public class ItemActivityTest {

    private ItemActivity activity;

    @Before
    public void setUp() throws Exception {
        activity = Robolectric.buildActivity(ItemActivity.class).create().get();
    }

    @Test
    public void componentsTest() {
        RecyclerView recyclerView = (RecyclerView) activity.findViewById(R.id.comment_list);
        CardView cardView = (CardView) activity.findViewById(R.id.main_card);
        CardView typecardView = (CardView) activity.findViewById(R.id.type_card);
        TextView date = (TextView) activity.findViewById(R.id.date);
        TextView content = (TextView) activity.findViewById(R.id.content);
        TextView by = (TextView) activity.findViewById(R.id.by);
        TextView type = (TextView) activity.findViewById(R.id.type);
        TextView comments = (TextView) activity.findViewById(R.id.comments);
        ProgressBar progress = (ProgressBar) activity.findViewById(R.id.progress);

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
}