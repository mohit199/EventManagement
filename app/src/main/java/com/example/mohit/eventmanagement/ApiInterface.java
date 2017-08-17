package com.example.mohit.eventmanagement;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Mohit on 8/3/2017.
 */

public interface ApiInterface {

    @POST(NetworkURL.AUDIENCE_LOGIN)
    Call<ApiResponse> audience_login(@Body AuthenticationRequest body);

    @POST(NetworkURL.ARTIST_LOGIN)
    Call<ApiResponse> artist_login(@Body AuthenticationRequest body);

    @POST(NetworkURL.AUDIENCE_REGISTRATION)
    Call<ApiResponse> audience_registration(@Body RegistrationRequest body);

    @POST(NetworkURL.ARTIST_REGISTRATION)
    Call<ApiResponse> artist_registration(@Body RegistrationRequest body);

    @POST(NetworkURL.CREATE_EVENT)
    Call<ApiResponse> create_event(@Body CreateEventRequest body);

    @POST(NetworkURL.EVENT_LIST)
    Call<List<Event>> getEvents(@Body EventListRequest body);

    @POST(NetworkURL.BOOK_TICKET)
    Call<ApiResponse> book_ticket(@Body BookTicketRequest body);
}
