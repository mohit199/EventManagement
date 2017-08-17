package com.example.mohit.eventmanagement;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import java.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.R.attr.y;


public class CreateEventActivity extends AppCompatActivity {

    /** Spinner to enter the event's location */
    private Spinner mLocationSpinner;

    //location of the event
    private String create_location;

    //to accept the event name from the UI
    private EditText create_eventname;
    private String eventname;

    //to accept the venue from the UI
    private EditText create_venue;
    private String venue;

    //to accept the number of tickets from the UI
    private EditText create_numberOfTickets;
    private int numberOfTickets;

    //to accept the price from the UI
    private EditText create_price;
    private int price;

    //to accept the description from the UI
    private EditText create_description;
    private String description;

    //Submit button
    private Button create_submitButton;

    private static TextView create_dateText;
    private static int create_day, create_month, create_year;

    private static TextView create_timeText;
    private static int create_hours, create_minutes;

    int artistid;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);

        artistid = getIntent().getIntExtra("artistid", 0);

        create_eventname = (EditText)findViewById(R.id.create_eventname);
        create_venue = (EditText)findViewById(R.id.create_venue);
        create_numberOfTickets = (EditText)findViewById(R.id.create_numberOfTickets);
        create_price = (EditText)findViewById(R.id.create_price);
        create_description = (EditText)findViewById(R.id.create_description);

        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        int hours = c.get(Calendar.HOUR_OF_DAY);
        int minutes = c.get(Calendar.MINUTE);
        month++;

        create_year = year;
        create_month = month;
        create_day = day;
        create_hours = hours;
        create_minutes = minutes;

        create_dateText = (TextView)findViewById(R.id.create_dateText);
        create_dateText.setText(day+"/"+month+"/"+year);

        create_timeText = (TextView)findViewById(R.id.create_timeText);
        create_timeText.setText(hours+":"+minutes);

        mLocationSpinner = (Spinner) findViewById(R.id.create_spinner_location);
        setupSpinner();

        create_submitButton = (Button)findViewById(R.id.create_submitButton);
        create_submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isFormValid()){
                    createEvent();
                }
            }
        });

    }

    //makes a POST request to the database to create the event
    private void createEvent(){

        eventname = create_eventname.getText().toString().trim();
        venue = create_venue.getText().toString().trim();
        description = create_description.getText().toString().trim();
        numberOfTickets = Integer.parseInt(create_numberOfTickets.getText().toString().trim());
        price = Integer.parseInt(create_price.getText().toString().trim());

        ApiManager.getApiInterface().create_event(new CreateEventRequest(artistid, eventname, create_location,venue, create_day, create_month, create_year, create_hours, create_minutes, numberOfTickets, price, description))
                .enqueue(new Callback<ApiResponse>() {
                    @Override
                    public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                        if(response.body().getStatus().contentEquals("success")){
                            Toast.makeText(CreateEventActivity.this, "Event created successfully", Toast.LENGTH_SHORT).show();
                            NavUtils.navigateUpFromSameTask(CreateEventActivity.this);
                        }else{
                            Toast.makeText(CreateEventActivity.this, "Event Creation Failed", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ApiResponse> call, Throwable t) {
                        Toast.makeText(CreateEventActivity.this, "Check your network connection", Toast.LENGTH_SHORT).show();
                    }
                });
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
                create_location = (String) parent.getItemAtPosition(position);
            }

            // Because AdapterView is an abstract class, onNothingSelected must be defined
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
               create_location = "unknown";
            }
        });
    }

    //if any of the field is empty, returns false otherwise returns true
    private Boolean isFormValid(){

        if(create_eventname.getText().toString().trim().isEmpty()){
            Toast.makeText(this, "Please enter eventname", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(create_location.trim().isEmpty()){
            Toast.makeText(this, "Please enter location", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(create_venue.getText().toString().trim().isEmpty()){
            Toast.makeText(this, "Please enter venue", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(create_numberOfTickets.getText().toString().trim().isEmpty()){
            Toast.makeText(this, "Please enter number of tickets", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(create_price.getText().toString().trim().isEmpty()){
            Toast.makeText(this, "Please enter price of ticket", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(create_description.getText().toString().trim().isEmpty()){
            Toast.makeText(this, "Please give description", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }

    public static class TimePickerFragment extends DialogFragment
            implements TimePickerDialog.OnTimeSetListener {

        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current time as the default values for the picker
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            // Create a new instance of TimePickerDialog and return it
            return new TimePickerDialog(getActivity(), this, hour, minute,
                    DateFormat.is24HourFormat(getActivity()));
        }

        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            // Do something with the time chosen by the user
            create_hours = hourOfDay;
            create_minutes = minute;
            create_timeText.setText(create_hours+":"+create_minutes);
        }
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            // Do something with the date chosen by the user
            month++;
            create_year = year;
            create_month = month;
            create_day = day;
            create_dateText.setText(create_day+"/"+create_month+"/"+create_year);

        }
    }
}
