package com.example.mohit.eventmanagement;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Mohit on 8/8/2017.
 */

public class Event {

    @SerializedName("eventid")
    int eventid;

    public int getEventid() {
        return eventid;
    }

    @SerializedName("eventname")
    String eventname;

    public String getEventname() {
        return eventname;
    }

    @SerializedName("location")
    String location;

    public String getLocation() {
        return location;
    }

    @SerializedName("venue")
    String venue;

    public String getVenue() {
        return venue;
    }

    @SerializedName("description")
    String description;

    public String getDescription() {
        return description;
    }

    @SerializedName("day")
    int day;

    public int getDay() {
        return day;
    }

    @SerializedName("month")
    int month;

    public int getMonth() {
        return month;
    }

    @SerializedName("year")
    int year;

    public int getYear() {
        return year;
    }

    @SerializedName("hours")
    int hours;

    public int getHours() {
        return hours;
    }

    @SerializedName("minutes")
    int minutes;

    public int getMinutes() {
        return minutes;
    }

    @SerializedName("numberOfTickets")
    int numberOfTickets;

    public int getNumberOfTickets() {
        return numberOfTickets;
    }

    @SerializedName("price")
    int price;

    public int getPrice() {
        return price;
    }
}
