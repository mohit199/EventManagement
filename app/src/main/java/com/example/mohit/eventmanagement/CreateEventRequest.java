package com.example.mohit.eventmanagement;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Mohit on 8/6/2017.
 */

public class CreateEventRequest {

    @SerializedName("create_eventname")
    String create_eventname;

    @SerializedName("create_location")
    String create_location;

    @SerializedName("create_venue")
    String create_venue;

    @SerializedName("create_day")
    int create_day;

    @SerializedName("create_month")
    int create_month;

    @SerializedName("create_year")
    int create_year;

    @SerializedName("create_hours")
    int create_hours;

    @SerializedName("create_minutes")
    int create_minutes;

    @SerializedName("create_numberOfTickets")
    int create_numberOfTickets;

    @SerializedName("create_price")
    int create_price;

    @SerializedName("create_description")
    String create_description;

    @SerializedName("artistid")
    int artistid;

    public CreateEventRequest(int artistid, String create_eventname, String create_location,String create_venue, int create_day, int create_month, int create_year, int create_hours, int create_minutes, int create_numberOfTickets, int create_price, String create_description) {
        this.create_eventname = create_eventname;
        this.create_location = create_location;
        this.create_venue = create_venue;
        this.create_day = create_day;
        this.create_month = create_month;
        this.create_year = create_year;
        this.create_hours = create_hours;
        this.create_minutes = create_minutes;
        this.create_numberOfTickets = create_numberOfTickets;
        this.create_price = create_price;
        this.create_description = create_description;
        this.artistid = artistid;
    }

}
