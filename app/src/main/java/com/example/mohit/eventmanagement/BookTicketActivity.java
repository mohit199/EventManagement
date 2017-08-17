package com.example.mohit.eventmanagement;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class BookTicketActivity extends AppCompatActivity {

    Button book_minusButton, book_addButton, book_bookTicketButton;
    TextView book_price, book_total, book_numberOfSeats;
    int price;
    int numberOfSeats = 1;
    int eventid;
    int ticketsLeft;
    int userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_ticket);

        userid = getIntent().getIntExtra("userid", 0);

        eventid = getIntent().getIntExtra("eventid",0);
        ticketsLeft = getIntent().getIntExtra("numberOfTickets", 0);

        book_price = (TextView)findViewById(R.id.book_price);
        price = (getIntent().getIntExtra("price", 0));
        book_price.setText(String.valueOf(price));

        book_numberOfSeats = (TextView)findViewById(R.id.book_numberOfSeats);
        book_numberOfSeats.setText(String.valueOf(numberOfSeats));

        book_total = (TextView)findViewById(R.id.book_total);
        book_total.setText(String.valueOf(numberOfSeats * price));

        book_minusButton = (Button)findViewById(R.id.book_minusButton);
        book_minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(numberOfSeats > 1) {
                    numberOfSeats--;
                    book_numberOfSeats.setText(String.valueOf(numberOfSeats));
                    book_total.setText(String.valueOf(numberOfSeats * price));
                }else{
                    Toast.makeText(BookTicketActivity.this, "Minimum 1 ticket has to be booked", Toast.LENGTH_SHORT).show();
                }
            }
        });

        book_addButton = (Button)findViewById(R.id.book_addButton);
        book_addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(numberOfSeats < 10 && numberOfSeats < ticketsLeft) {
                    numberOfSeats++;
                    book_numberOfSeats.setText(String.valueOf(numberOfSeats));
                    book_total.setText(String.valueOf(numberOfSeats * price));
                }
                else{
                    Toast.makeText(BookTicketActivity.this, "You can't book more", Toast.LENGTH_SHORT).show();
                }
            }
        });

        book_bookTicketButton = (Button)findViewById(R.id.book_bookTicketButton);
        book_bookTicketButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ticketsLeft = ticketsLeft - numberOfSeats;


                ApiManager.getApiInterface().book_ticket(new BookTicketRequest(eventid, numberOfSeats, ticketsLeft, userid))
                        .enqueue(new Callback<ApiResponse>() {
                            @Override
                            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                                if(response.body().getStatus().contentEquals("success")){
                                    showAlert("Success", "Ticket booked succesfully");
                                    //Toast.makeText(BookTicketActivity.this, "Ticket booked succesfully", Toast.LENGTH_SHORT).show();
                                }else {
                                    //Toast.makeText(BookTicketActivity.this, "Ticket booking Failed", Toast.LENGTH_SHORT).show();
                                    showAlert("Failed", "Ticket booking unsuccessful");
                                }
                            }

                            @Override
                            public void onFailure(Call<ApiResponse> call, Throwable t) {
                                Toast.makeText(BookTicketActivity.this, "Check network connection", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }

    private void showAlert(String title, String message){

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setNeutralButton("Dismiss", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Intent intent = new Intent(BookTicketActivity.this, AcceptLocationActivity.class);
                intent.putExtra("userid", userid);
                startActivity(intent);
            }
        });
        builder.show();
    }
}
