package com.example.mohit.eventmanagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class AcceptLocationActivity extends AppCompatActivity {

    /** Spinner to enter the event's location */
    private Spinner mLocationSpinner;

    //location of the event
    private String location;

    //userid of loggedin user
    int userid;

    Button upcommingEventsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accept_location);

        userid = getIntent().getIntExtra("userid",0);

        upcommingEventsButton = (Button)findViewById(R.id.upcommingEventsButton);
        upcommingEventsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AcceptLocationActivity.this, EventListActivity.class);
                intent.putExtra("userid",userid);
                intent.putExtra("location", location);
                startActivity(intent);
            }
        });

        mLocationSpinner = (Spinner) findViewById(R.id.create_spinner_location);
        setupSpinner();
    }
    /**
     * Setup the dropdown spinner that allows the user to select the location of the event.
     */
    private void setupSpinner() {
        // Create adapter for spinner. The list options are from the String array it will use
        // the spinner will use the default layout
        ArrayAdapter locationSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.array_location_options, android.R.layout.simple_spinner_item);

        // Specify dropdown layout style - simple list view with 1 item per line
        locationSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        // Apply the adapter to the spinner
        mLocationSpinner.setAdapter(locationSpinnerAdapter);

        // Set the string create_location to the selcted city
        mLocationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                location = (String) parent.getItemAtPosition(position);
            }

            // Because AdapterView is an abstract class, onNothingSelected must be defined
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                location = "unknown";
            }
        });
    }
}
