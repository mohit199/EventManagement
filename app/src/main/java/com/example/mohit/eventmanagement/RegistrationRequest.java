package com.example.mohit.eventmanagement;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Mohit on 8/5/2017.
 */

public class RegistrationRequest {

    @SerializedName("register_firstname")
    String register_firstname;

    @SerializedName("register_lastname")
    String register_lastname;

    @SerializedName("register_username")
    String register_username;

    @SerializedName("register_password")
    String register_password;

    public RegistrationRequest(String register_firstname, String register_lastname, String register_username, String register_password){
        this.register_firstname = register_firstname;
        this.register_lastname = register_lastname;
        this.register_username = register_username;
        this.register_password = register_password;
    }
}
