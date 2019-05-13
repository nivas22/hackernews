package com.example.hackernews.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hackernews.R;

import java.util.List;

public class TopicsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final OnItemClickListener listener;
    private List<String> topics;
    private Context mContext;

    public TopicsAdapter(Context context, List<String> topics, OnItemClickListener listener) {
        this.topics = topics;
        this.mContext = context;
        this.listener = listener;
    }

    public void setTopics(List<String> topics) {
        this.topics = topics;
    }

    @Override
    public int getItemCount() {
        return topics.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.topic_item_layout, parent, false);
        viewHolder = new ViewHolder(view);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder viewholder, final int position) {
        if (topics.size() == 0) {
            return;
        }
        ViewHolder holder = (ViewHolder) viewholder;
        String dueDate = topics.get(position);
        holder.topicName.setText(dueDate);

        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(topics.get(position));
            }
        });
    }

    public interface OnItemClickListener {
        void onItemClick(String item);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView topicName;
        CardView card;

        ViewHolder(View v) {
            super(v);
            topicName = v.findViewById(R.id.topic_name);
            card = v.findViewById(R.id.topic_card);
        }
    }
}
