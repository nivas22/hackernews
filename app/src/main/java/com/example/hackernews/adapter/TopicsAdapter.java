package com.example.hackernews.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hackernews.R;

import java.util.ArrayList;
import java.util.List;

public class TopicsAdapter extends RecyclerView.Adapter<TopicsAdapter.ViewHolder> {

    private OnItemClickListener listener;
    private List<String> topics;

    public TopicsAdapter(List<String> topics) {
        if(topics == null){
            throw new NullPointerException();
        }
        this.topics = topics;
    }

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public OnItemClickListener getListener() {
        return listener;
    }

    public List<String> getTopics() {
        return topics;
    }

    public void setTopics(List<String> topics) {
        this.topics = topics;
    }

    @Override
    public int getItemCount() {
        return topics == null ? 0 : topics.size();
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.topic_item_layout, viewGroup, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        final String item = getItemAtPosition(i);
        holder.topicName.setText(item);

        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(v, item);
            }
        });
    }

    public String getItemAtPosition(int position) {
        return topics.get(position);
    }

    public interface OnItemClickListener {
        void onItemClick(View v, String item);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView topicName;
        CardView card;

        ViewHolder(View v) {
            super(v);
            topicName = v.findViewById(R.id.topic_name);
            card = v.findViewById(R.id.topic_card);
        }
    }
}
