package com.ehsunhanif.eventsavvy;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ehsunhanif.eventsavvy.domain.Event;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class EditEventActivity extends AppCompatActivity {

    Button cancel;
    Button create;
    EditText title;
    EditText date;
    EditText startTime;
    EditText endTime;
    EditText category;
    String username= "";

    private final OkHttpClient httpclient = new OkHttpClient();
    Gson googleJson;
    Event event = new Event();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_edit);

        // We populate the inputs with details of the event to edit here.
        event = getEventDetails();

        cancel = (Button) findViewById(R.id.edit_cancel);
        create = (Button) findViewById(R.id.edit_create);
        title = (EditText) findViewById(R.id.edit_title);
        date = (EditText) findViewById(R.id.edit_date);
        startTime = (EditText) findViewById(R.id.edit_startTime);
        endTime = (EditText) findViewById(R.id.edit_endTime);
        category = (EditText) findViewById(R.id.edit_category);

        title.setText(event.getTitle());
        date.setText(event.getDate());
        startTime.setText(event.getStartTime());
        endTime.setText(event.getEndTime());
        category.setText(event.getCategory());

    }

    //Method to handle editing of Event.
    public void editEvent(View view) {
        cancel = (Button) findViewById(R.id.edit_cancel);
        create = (Button) findViewById(R.id.edit_create);
        title = (EditText) findViewById(R.id.edit_title);
        date = (EditText) findViewById(R.id.edit_date);
        startTime = (EditText) findViewById(R.id.edit_startTime);
        endTime = (EditText) findViewById(R.id.edit_endTime);
        category = (EditText) findViewById(R.id.edit_category);


        FormBody.Builder formBuilder = new FormBody.Builder().add("title", title.getText().toString());
        formBuilder.add("category", category.getText().toString());
        formBuilder.add("date", date.getText().toString());
        formBuilder.add("startTime", startTime.getText().toString());
        formBuilder.add("endTime", endTime.getText().toString());
        formBuilder.add("longitude", event.getLongitude());
        formBuilder.add("latitude", event.getLatitude());
        formBuilder.add("eventId", event.getId().toString());
        formBuilder.add("username", username);
        RequestBody formBody = formBuilder.build();

        Request request = new Request.Builder()
                .url("https://events-api-81942.herokuapp.com/eventsavvy-api/main/editEvent/")
                .post(formBody)
                .build();

        httpclient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String res = response.body().string();
                if(res.equals("Event Edited!")) {
                    Toast.makeText(EditEventActivity.this, "Event Edited!", Toast.LENGTH_LONG).show();
                    Handler mHandler = new Handler();
                    mHandler.postDelayed(startNewActivity, 3500);
                } else {
                    Toast.makeText(EditEventActivity.this, "Sorry, Error!", Toast.LENGTH_LONG).show();
                    //Give pause after new activity starts to show a message to the user.
                    Handler mHandler = new Handler();
                    mHandler.postDelayed(startNewActivity, 3500);
                }
            }
        });
    }

    // New process that starts after successful event edit.
    private Runnable startNewActivity = new Runnable() {
        @Override
        public void run() {
            Intent intent = new Intent(getApplicationContext(), ToolbarActivity.class);
            startActivity(intent);
            finish();
        }
    };

    // Method used to handle if the user wishes to cancel creating the event.
    public void cancelEvent(View view) {
        Intent myIntent = new Intent(this, ToolbarActivity.class);
        startActivity(myIntent);
    }

    //Method used to get details of the event to edit.
    public Event getEventDetails() {
        int id = getIntent().getIntExtra("eventId", 0);
        Request request = new Request.Builder()
                .url("https://events-api-81942.herokuapp.com/eventsavvy-api/main/getEventById/"+id)
                .build();
        httpclient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String res = response.body().string();
                //Type objectType = new TypeToken<Event>(){}.getType();
                event = googleJson.fromJson(res, Event.class);
            }
        });
        return event;
    }

    //Method to handle the deletion of an event.
    public void deleteEvent(View view) {
        int id = getIntent().getIntExtra("eventId", 0);
        try {
            Request request = new Request.Builder()
                    .url("https://events-api-81942.herokuapp.com/eventsavvy-api/main/deleteEvent/"+id)
                    .build();

            httpclient.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    e.printStackTrace();
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    try (ResponseBody responseBody = response.body()) {
                        if (!response.isSuccessful())
                            throw new IOException("Unexpected code " + response);

                        Headers responseHeaders = response.headers();
                        for (int i = 0, size = responseHeaders.size(); i < size; i++) {
                            System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
                        }

                        System.out.println(responseBody.string());

                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
