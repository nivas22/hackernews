package com.example.hackernews.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.hackernews.App;
import com.example.hackernews.Constants;
import com.example.hackernews.R;
import com.example.hackernews.adapter.TopicsAdapter;
import com.example.hackernews.modal.News;
import com.example.hackernews.utils.Utils;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ItemActivity extends AppCompatActivity {

    @BindView(R.id.comment_list)
    RecyclerView recyclerView;

    @BindView(R.id.main_card)
    CardView cardView;

    @BindView(R.id.type_card)
    CardView typeCard;

    @BindView(R.id.date)
    TextView date;

    @BindView(R.id.content)
    TextView content;

    @BindView(R.id.comments)
    TextView comments;

    @BindView(R.id.by)
    TextView by;

    @BindView(R.id.type)
    TextView type;

    @BindView(R.id.progress)
    ProgressBar progress;

    private TopicsAdapter adapter;
    private List<String> topics = new ArrayList<>();
    private OkHttpClient ok = App.get().getOk();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.topic_item);

        ButterKnife.bind(this);

        // Get the transferred data from source activity.
        Intent intent = getIntent();
        String item = intent.getStringExtra("item");

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(item);
        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new TopicsAdapter(topics);
        adapter.setListener(new TopicsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, String item) {

            }
        });
        recyclerView.setAdapter(adapter);

        new GetTopicItemTask(item).execute();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {// API 5+ solution
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void loadView(News news) {
        if (news.getKids() != null && news.getKids().length > 0) {
            adapter.setTopics(Arrays.asList(news.getKids()));
            adapter.notifyDataSetChanged();
        }
        cardView.setVisibility(View.VISIBLE);
        typeCard.setVisibility(View.VISIBLE);
        by.setText("By " + news.getBy());
        type.setText(news.getType().toUpperCase());

        if (news.getType().equals("story")) {
            content.setText(news.getTitle());
        } else {
            content.setText(news.getText());
        }

        if (news.getKids() != null && news.getKids().length > 0) {
            comments.setText("Total comments: " + news.getKids().length);
        } else {
            comments.setText("Total comments: 0");
        }

        date.setText(Utils.getTimeString(news.getTime() * 1000));
    }

    private void showProgress(final boolean show) {
        int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);
        progress.setVisibility(show ? View.VISIBLE : View.GONE);
        progress.animate().setDuration(shortAnimTime).alpha(
                show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                progress.setVisibility(show ? View.VISIBLE : View.GONE);
            }
        });
    }


    @SuppressLint("StaticFieldLeak")
    private class GetTopicItemTask extends AsyncTask<Void, Void, Integer> {

        private String item;
        private News news;

        public GetTopicItemTask(String item) {
            this.item = item;
        }

        @Override
        protected void onPreExecute() {
            showProgress(true);
        }

        @Override
        protected Integer doInBackground(Void... params) {

            if (!Utils.isInternetAvailable(App.get())) {
                return Constants.INTERNET_DISCONNECTED;
            }

            Request request = new Request.Builder()
                    .url(Constants.GET_STORY_ITEM + item + ".json?print=pretty")
                    .get()
                    .build();

            try {
                Response response = ok.newCall(request).execute();
                if (response.isSuccessful()) {
                    Gson gson = new Gson();
                    if (response.body() != null) {
                        news = gson.fromJson(response.body().string(), News.class);
                    } else {
                        return Constants.FAILURE;
                    }
                    return Constants.SUCCESS;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return Constants.FAILURE;
        }

        @Override
        protected void onPostExecute(Integer result) {

            showProgress(false);

            if (Constants.INTERNET_DISCONNECTED == result) {
                Utils.showSimpleDialogMessage(R.string.internet_disconnected, ItemActivity.this);
                return;
            }

            if (Constants.FAILURE == result) {
                Utils.showSimpleDialogMessage(R.string.something_went_wrong, ItemActivity.this);
                return;
            }

            if (Constants.SUCCESS == result) {
                Log.e("Topics count:", String.valueOf(news));
                if (news != null) {
                    loadView(news);
                }
            }
        }
    }


}
