package com.example.hackernews.activity;

import com.example.hackernews.RobolectricGradleTestRunner;
import com.example.hackernews.adapter.TopicsAdapter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;

import static org.junit.Assert.*;
@RunWith(RobolectricGradleTestRunner.class)
public class HomeActivityTest {

    private HomeActivity activity;
    private TopicsAdapter topicsAdapter;


    @Before
    public void setUp() throws Exception {
        activity = Robolectric.setupActivity(HomeActivity.class);
    }


    @Test
    public void secondActivityStarted() {
//        activity.findViewById(R.id.findRestaurantsButton).performClick();
//        Intent expectedIntent = new Intent(activity, RestaurantsActivity.class);
//        ShadowActivity shadowActivity = org.robolectric.Shadows.shadowOf(activity);
//        Intent actualIntent = shadowActivity.getNextStartedActivity();
//        assertTrue(actualIntent.filterEquals(expectedIntent));
    }
}