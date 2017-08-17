package com.example.mohit.eventmanagement;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Mohit on 8/4/2017.
 */

public class ApiResponse {
    @SerializedName("message")
    String message;

    @SerializedName("status")
    String status;

    @SerializedName("userid")
    int userid;

    public String getMessage(){
        return message;
    }

    public String getStatus() {
        return status;
    }

    public int getUserid() {
        return userid;
    }
}
