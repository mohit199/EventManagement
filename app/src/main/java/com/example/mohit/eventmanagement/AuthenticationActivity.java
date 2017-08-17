package com.example.mohit.eventmanagement;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ActionMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import com.example.mohit.eventmanagement.ApiResponse;

import static android.R.attr.onClick;
import static com.example.mohit.eventmanagement.R.id.signInButton;

public class AuthenticationActivity extends AppCompatActivity {

    //takes the username and password as input
    private EditText username, password;

    //checks whether the user is an arrtist or not
    private CheckBox isArtist;

    //for showing progress of signIn action
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);

        //contains the username
        username = (EditText)findViewById(R.id.username);

        //contains the password
        password = (EditText)findViewById(R.id.password);

        //checks whether the user is an artist or not
        isArtist = (CheckBox)findViewById(R.id.isArtist);

        //adding functionality to signInButton
        Button signInButton = (Button)findViewById(R.id.signInButton);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isFormValid()){
                    //perform signIn
                    performSignIn();
                }
            }
        });

        //adding functionality to registeration text
        TextView registerText = (TextView) findViewById(R.id.registerText);
        registerText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AuthenticationActivity.this, RegistrationActivity.class);
                startActivity(intent);
                }
            }
        );

        // initialising progressDialog
        progressDialog = new ProgressDialog(this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Please wait");
    }

    //if any of the username or password field is empty, returns false otherwise returns true
    private Boolean isFormValid(){

        if(username.getText().toString().trim().isEmpty()){
            Toast.makeText(this, "Please enter username", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(password.getText().toString().trim().isEmpty()){
            Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private void performSignIn(){

        if(isArtist.isChecked()) {
            showProgressDialog(true);
            //make a call to artist database
            ApiManager.getApiInterface().artist_login(new AuthenticationRequest(username.getText().toString().trim(), password.getText().toString().trim()))
                    .enqueue(new Callback<ApiResponse>() {
                        @Override
                        public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                            showProgressDialog(false);
                            if(response.body().getStatus().contentEquals("success")){
                                Intent intent = new Intent(AuthenticationActivity.this, CreateEventActivity.class);
                                intent.putExtra("artistid",response.body().getUserid());
                                startActivity(intent);
                            }else{
                                showAlert("SignIn Failed", "username/password incorrect");
                            }
                        }

                        @Override
                        public void onFailure(Call<ApiResponse> call, Throwable t) {
                            showProgressDialog(false);
                            showAlert("SignIn Failed", "Check your network connection");
                        }
                    });
        }
        else {
            showProgressDialog(true);
            //make a call to audience database
            ApiManager.getApiInterface().audience_login(new AuthenticationRequest(username.getText().toString().trim(), password.getText().toString().trim()))
                    .enqueue(new Callback<ApiResponse>() {
                        @Override
                        public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                            showProgressDialog(false);
                            if(response.body().getStatus().contentEquals("success")){
                                Log.v("userid:",String.valueOf(response.body().getUserid()));
                                Intent intent = new Intent(AuthenticationActivity.this, AcceptLocationActivity.class);
                                intent.putExtra("userid",response.body().getUserid());
                                startActivity(intent);
                                finish();
                            }else{
                                showAlert("SignIn Failed", "username/password incorrect");
                            }
                        }

                        @Override
                        public void onFailure(Call<ApiResponse> call, Throwable t) {
                            showProgressDialog(false);
                            showAlert("SignIn Failed", "Check your network connection");
                        }
                    });
        }
    }

    private void showProgressDialog(boolean shouldShould){
        if(shouldShould)
            progressDialog.show();
        else
            progressDialog.dismiss();
    }

    private void showAlert(String title, String message){

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setNeutralButton("Dismiss", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }
}
