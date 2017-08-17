package com.example.mohit.eventmanagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.example.mohit.eventmanagement.EventListActivity;

public class EventDetailsActivity extends AppCompatActivity {

    TextView detail_eventname, detail_venuelocation, detail_timedate, detail_price, detail_numberOfTickets, detail_description;
    Button detail_bookTicketButton;

    int userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);

        userid = getIntent().getIntExtra("userid",  0);

        detail_eventname = (TextView)findViewById(R.id.detail_eventname );
        detail_eventname.setText(getIntent().getStringExtra("eventname"));

        detail_venuelocation = (TextView)findViewById(R.id.detail_venuelocation );
        detail_venuelocation.setText(getIntent().getStringExtra("venue") +", "+ getIntent().getStringExtra("location") );

        detail_timedate = (TextView)findViewById(R.id.detail_timedate );
        detail_timedate.setText(String.valueOf(getIntent().getIntExtra("hours", 0)) +":"+ String.valueOf(getIntent().getIntExtra("minutes", 0)) +", "+ String.valueOf(getIntent().getIntExtra("day", 0)) +"/"+ String.valueOf(getIntent().getIntExtra("month", 0)) +"/"+ String.valueOf(getIntent().getIntExtra("year", 0)));

        detail_price = (TextView)findViewById(R.id.detail_price );
        detail_price.setText(String.valueOf(getIntent().getIntExtra("price", 0)));

        detail_numberOfTickets = (TextView)findViewById(R.id.detail_numberOfTickets );
        detail_numberOfTickets.setText(String.valueOf(getIntent().getIntExtra("numberOfTickets", 0)));

        detail_description = (TextView)findViewById(R.id.detail_description );
        detail_description.setText(getIntent().getStringExtra("description"));

        final int detail_eventid = getIntent().getIntExtra("eventid", 0);

        detail_bookTicketButton = (Button)findViewById(R.id.detail_bookTicketButton);
        detail_bookTicketButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EventDetailsActivity.this, BookTicketActivity.class);
                intent.putExtra("eventid", detail_eventid);
                intent.putExtra("price", getIntent().getIntExtra("price", 0));
                intent.putExtra("numberOfTickets",getIntent().getIntExtra("numberOfTickets", 0));
                intent.putExtra("userid",userid);
                startActivity(intent);

            }
        });

    }
}
