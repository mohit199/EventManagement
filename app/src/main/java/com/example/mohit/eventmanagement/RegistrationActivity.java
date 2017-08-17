package com.example.mohit.eventmanagement;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.mohit.eventmanagement.R.id.isArtist;
import static com.example.mohit.eventmanagement.R.id.signInButton;
import static com.example.mohit.eventmanagement.R.id.username;
import com.example.mohit.eventmanagement.AuthenticationActivity;

public class RegistrationActivity extends AppCompatActivity {

    private EditText register_username, register_firstname, register_lastname, register_password;
    private CheckBox register_isArtist;
    private Button register_submit;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        register_username = (EditText)findViewById(R.id.register_username);
        register_firstname = (EditText)findViewById(R.id.register_firstname);
        register_lastname = (EditText)findViewById(R.id.register_lastname);
        register_password = (EditText)findViewById(R.id.register_password);

        register_isArtist = (CheckBox)findViewById(R.id.register_isArtist);
        register_submit = (Button)findViewById(R.id.register_submit);

        register_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isFormValid()){
                    //perform signIn
                    performRegistration();
                }
            }
        });

        // initialising progressDialog
        progressDialog = new ProgressDialog(this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Please wait");
    }

    private void performRegistration(){

        if(register_isArtist.isChecked()) {
            showProgressDialog(true);
            ApiManager.getApiInterface().artist_registration(new RegistrationRequest(register_firstname.getText().toString().trim(), register_lastname.getText().toString().trim(), register_username.getText().toString().trim(), register_password.getText().toString().trim()))
                    .enqueue(new Callback<ApiResponse>() {
                        @Override
                        public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                            showProgressDialog(false);
                            if(response.body().getStatus().contentEquals("success")){
                                showAlert("Welocome", "Registered Successfully");
                            }else{
                                showAlert("Registration Failed", "Username must be unique");
                            }
                        }

                        @Override
                        public void onFailure(Call<ApiResponse> call, Throwable t) {
                            showProgressDialog(false);
                            showAlert("Registration Failed", "Please check your network connection");
                        }
                    });
        }
        else {
            showProgressDialog(true);
            ApiManager.getApiInterface().audience_registration(new RegistrationRequest(register_firstname.getText().toString().trim(), register_lastname.getText().toString().trim(), register_username.getText().toString().trim(), register_password.getText().toString().trim()))
                    .enqueue(new Callback<ApiResponse>() {
                        @Override
                        public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                            showProgressDialog(false);
                            if(response.body().getStatus().contentEquals("success")){
                                showAlert("Welocome", "Registered Successfully");
                            }else{
                                showAlert("Registration Failed", "Username must be unique");
                            }
                        }

                        @Override
                        public void onFailure(Call<ApiResponse> call, Throwable t) {
                            showProgressDialog(false);
                            showAlert("Registration Failed", "Please check your network connection");
                        }
                    });
        }
    }

    //if any of the field is empty, returns false otherwise returns true
    private Boolean isFormValid(){

        if(register_firstname.getText().toString().trim().isEmpty()){
            Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(register_lastname.getText().toString().trim().isEmpty()){
            Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(register_username.getText().toString().trim().isEmpty()){
            Toast.makeText(this, "Please enter username", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(register_password.getText().toString().trim().isEmpty()){
            Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
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
                NavUtils.navigateUpFromSameTask(RegistrationActivity.this);
            }
        });
        builder.show();
    }
}
