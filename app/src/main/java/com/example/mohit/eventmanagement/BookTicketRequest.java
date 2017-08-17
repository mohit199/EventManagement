package com.example.mohit.eventmanagement;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Mohit on 8/9/2017.
 */

public class BookTicketRequest {

    @SerializedName("eventid")
    int eventid;

    @SerializedName("numberOfSeats")
    int numberOfSeats;

    @SerializedName("ticketsLeft")
    int ticketsLeft;

    @SerializedName("userid")
    int userid;

    public BookTicketRequest(int eventid, int numberOfSeats, int ticketsLeft, int userid) {
        this.eventid = eventid;
        this.numberOfSeats = numberOfSeats;
        this.ticketsLeft = ticketsLeft;
        this.userid = userid;
    }
}
