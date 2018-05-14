package com.dabudabu.samsatnotif.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dabudabu.samsatnotif.R;
import com.dabudabu.samsatnotif.model.ItemEvent;

import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.HolderEvent> {

    private List<ItemEvent> events;
    private Context context;

    public EventAdapter(List<ItemEvent> events, Context context) {
        this.events = events;
        this.context = context;
    }

    @Override
    public EventAdapter.HolderEvent onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_event, parent, false);
        return new HolderEvent(view);
    }

    @Override
    public void onBindViewHolder(EventAdapter.HolderEvent holder, int position) {
        ItemEvent itemEvent = events.get(position);
        holder.titleEvent.setText(itemEvent.getTitle());
        holder.contentEvent.setText(itemEvent.getContent());
        Glide.with(context)
                .load(itemEvent.getImageurl())
                .into(holder.imgEvent);
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    class HolderEvent extends RecyclerView.ViewHolder{
        private TextView titleEvent, contentEvent;
        private ImageView imgEvent;
        public HolderEvent(View itemView) {
            super(itemView);
            titleEvent = itemView.findViewById(R.id.titleevent);
            contentEvent = itemView.findViewById(R.id.contentevent);
            imgEvent = itemView.findViewById(R.id.imgevent);
        }
    }
}
