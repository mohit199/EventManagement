package com.example.mohit.eventmanagement;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Mohit on 8/8/2017.
 */

public class EventItemViewHolder extends RecyclerView.ViewHolder {

    TextView eventname, location, date;
    CardView cardView;
    public EventItemViewHolder(View itemView) {
        super(itemView);

        eventname = (TextView)itemView.findViewById(R.id.eventname);
        location = (TextView)itemView.findViewById(R.id.location);
        date = (TextView)itemView.findViewById(R.id.date);

        cardView = (CardView)itemView.findViewById(R.id.cardView);
    }
}
