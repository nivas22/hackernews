package com.example.hackernews.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.example.hackernews.App;
import com.example.hackernews.Constants;
import com.example.hackernews.R;
import com.example.hackernews.adapter.TopicsAdapter;
import com.example.hackernews.utils.Utils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HomeActivity extends AppCompatActivity {

    @BindView(R.id.progress)
    ProgressBar progress;

    @BindView(R.id.topic_list)
    RecyclerView recyclerView;

    @BindView(R.id.swipe_container)
    SwipeRefreshLayout refreshLayout;

    private TopicsAdapter adapter;
    private List<String> topics = new ArrayList<>();
    private OkHttpClient ok = App.get().getOk();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new TopicsAdapter( topics, new TopicsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(String item) {
                Intent mainIntent = new Intent(HomeActivity.this, ItemActivity.class);
                mainIntent.putExtra("item", item);
                HomeActivity.this.startActivity(mainIntent);
            }
        });
        recyclerView.setAdapter(adapter);

        loadRecyclerViewData(true);

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadRecyclerViewData(false);
            }
        });

        refreshLayout.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);

    }

    private void loadRecyclerViewData(boolean isShowProgress) {
        refreshLayout.setRefreshing(true);
        //Call Get Stories Rest API
        new GetTopStoriesTask(isShowProgress).execute();
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
    private class GetTopStoriesTask extends AsyncTask<Void, Void, Integer> {

        private List<String> topics = new ArrayList<>();
        private boolean isShowProgress = false;

        public GetTopStoriesTask(boolean isShowProgress) {
            this.isShowProgress = isShowProgress;
        }

        @Override
        protected void onPreExecute() {
            showProgress(isShowProgress);
        }

        @Override
        protected Integer doInBackground(Void... params) {

            if (!Utils.isInternetAvailable(App.get())) {
                return Constants.INTERNET_DISCONNECTED;
            }

            Request request = new Request.Builder()
                    .url(Constants.GET_TOP_STORIES)
                    .get()
                    .build();

            try {
                Response response = ok.newCall(request).execute();
                Log.e("response", String.valueOf(response.code()));
                if (response.isSuccessful()) {

                    Gson gson = new Gson();
                    if (response.body() != null) {
                        topics = gson.fromJson(response.body().string(),
                                new TypeToken<List<String>>() {
                                }.getType());
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
                Utils.showSimpleDialogMessage(R.string.internet_disconnected, HomeActivity.this);
                return;
            }

            if (Constants.FAILURE == result) {
                Utils.showSimpleDialogMessage(R.string.something_went_wrong, HomeActivity.this);
                return;
            }

            if (Constants.SUCCESS == result) {
                if (topics.size() > 0) {
                    adapter.setTopics(topics);
                    adapter.notifyDataSetChanged();
                    refreshLayout.setRefreshing(false);
                    Log.e("Topics count:", String.valueOf(topics.size()));
                }
            }
        }
    }

}
