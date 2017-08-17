package com.example.mohit.eventmanagement;

import android.app.ProgressDialog;
import android.content.Intent;
import java.util.Calendar;
import android.os.Build;
import android.os.Parcelable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.R.attr.data;

public class EventListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    EventListAdapter eventListAdapter;
    ProgressDialog progressDialog;

    int userid;
    String location;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_list);

        userid = getIntent().getIntExtra("userid",0);
        location = getIntent().getStringExtra("location");

        eventListAdapter = new EventListAdapter();

        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setAdapter(eventListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Fetching Events");
        progressDialog.show();

        final Calendar c = Calendar.getInstance();
        int currentYear = c.get(Calendar.YEAR);
        int currentMonth = c.get(Calendar.MONTH);
        currentMonth++;
        int currentDay = c.get(Calendar.DAY_OF_MONTH);

        ApiManager.getApiInterface().getEvents(new EventListRequest(currentDay, currentMonth, currentYear, location))
                .enqueue(new Callback<List<Event>>() {
                    @Override
                    public void onResponse(Call<List<Event>> call, Response<List<Event>> response) {
                        progressDialog.dismiss();
                        if(response.isSuccessful()){
                            eventListAdapter.setData(response.body());
                        }else{
                            Toast.makeText(EventListActivity.this, "Failed", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Event>> call, Throwable t) {
                        progressDialog.dismiss();
                        Toast.makeText(EventListActivity.this, "Failed", Toast.LENGTH_LONG).show();
                    }
                });
    }

    //creating the adapter
    public class EventListAdapter extends RecyclerView.Adapter<EventItemViewHolder>{

        List<Event> eventList = new ArrayList<>();

        @Override
        public EventItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_event, parent, false);
            return new EventItemViewHolder(view);
        }

        public void onBindViewHolder(EventItemViewHolder holder, final int position) {

            holder.eventname.setText(eventList.get(position).getEventname());
            holder.location.setText(eventList.get(position).getLocation());
            holder.date.setText(eventList.get(position).getDay() + "/" + eventList.get(position).getMonth() + "/" + eventList.get(position).getYear());


            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(EventListActivity.this, EventDetailsActivity.class);
                    intent.putExtra("eventname", eventList.get(position).getEventname());
                    intent.putExtra("venue", eventList.get(position).getVenue());
                    intent.putExtra("location", eventList.get(position).getLocation());
                    intent.putExtra("day", eventList.get(position).getDay());
                    intent.putExtra("month", eventList.get(position).getMonth());
                    intent.putExtra("year", eventList.get(position).getYear());
                    intent.putExtra("hours", eventList.get(position).getHours());
                    intent.putExtra("minutes", eventList.get(position).getMinutes());
                    intent.putExtra("price", eventList.get(position).getPrice());
                    intent.putExtra("numberOfTickets", eventList.get(position).getNumberOfTickets());
                    intent.putExtra("description", eventList.get(position).getDescription());
                    intent.putExtra("eventid", eventList.get(position).getEventid());
                    intent.putExtra("userid", userid);

                    startActivity(intent);
                    // Toast.makeText(EventListActivity.this, "Event Clicked"+eventList.get(position),Toast.LENGTH_LONG).show();
                }
            });
        }

        @Override
        public int getItemCount() {
            return eventList.size();
        }

        public void setData(List<Event> data){
            this.eventList = data;
            this.notifyDataSetChanged();
        }
    }

}
