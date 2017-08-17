package com.example.mohit.eventmanagement;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Mohit on 8/15/2017.
 */

public class EventListRequest {

    @SerializedName("currentDay")
    int currentDay;

    @SerializedName("currentMonth")
    int currentMonth;

    @SerializedName("currentYear")
    int currentYear;

    @SerializedName("location")
    String location;

    public EventListRequest(int currentDay, int currentMonth, int currentYear, String location) {
        this.currentDay = currentDay;
        this.currentMonth = currentMonth;
        this.currentYear = currentYear;
        this.location = location;
    }
}
