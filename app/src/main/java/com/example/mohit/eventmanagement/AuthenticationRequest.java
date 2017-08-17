package com.example.mohit.eventmanagement;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import static com.example.mohit.eventmanagement.R.id.username;

/**
 * Created by Mohit on 8/4/2017.
 */

public class AuthenticationRequest {

    @SerializedName("username")
    String username;

    @SerializedName("password")
    String password;

    public AuthenticationRequest(String username, String password){
        this.username = username;
        this.password = password;
    }
}
