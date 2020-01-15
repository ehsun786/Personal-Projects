package com.ehsunhanif.eventsavvy;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class CreateEventActivity extends AppCompatActivity {


    Button cancel;
    Button create;
    EditText title;
    EditText date;
    EditText startTime;
    EditText endTime;
    EditText category;
    String username= "";

    private final OkHttpClient client = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);

        cancel = (Button) findViewById(R.id.create_cancel);
        create = (Button) findViewById(R.id.create_createButton);
        title = (EditText) findViewById(R.id.create_title);
        date = (EditText) findViewById(R.id.create_date);
        startTime = (EditText) findViewById(R.id.create_startTime);
        endTime = (EditText) findViewById(R.id.create_endTime);
        category = (EditText) findViewById(R.id.create_category);

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);

        username = sharedPref.getString("username", "");
        System.out.println("CreateEvent£££££££££££££££££££"+username);
    }

    // Method to handle the creation of event.
    public void createEvent(View view) {
        title = (EditText) findViewById(R.id.create_title);
        category = (EditText) findViewById(R.id.create_category);
        date = (EditText) findViewById(R.id.create_date);
        startTime = (EditText) findViewById(R.id.create_startTime);
        endTime = (EditText) findViewById(R.id.create_endTime);

        Intent intent = getIntent();

        double latitude = intent.getDoubleExtra("latitude",0.0);
        double longitude = intent.getDoubleExtra("longitude",0.0);

        // Add data to the request body to be sent to Spring API.
        FormBody.Builder formBuilder = new FormBody.Builder().add("title", title.getText().toString());
        formBuilder.add("category", category.getText().toString());
        formBuilder.add("date", date.getText().toString());
        formBuilder.add("startTime", startTime.getText().toString());
        formBuilder.add("endTime", endTime.getText().toString());
        formBuilder.add("longitude", Double.toString(longitude));
        formBuilder.add("latitude", Double.toString(latitude));

        formBuilder.add("username", username);
        RequestBody formBody = formBuilder.build();

        Request request = new Request.Builder()
                .url("https://events-api-81942.herokuapp.com/eventsavvy-api/main/createEvent/")
                .post(formBody)
                .build();

        client.newCall(request)
                .enqueue(new Callback() {
                    @Override
                    public void onFailure(final Call call, IOException e) {
                        // Error

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                // For the example, you can show an error dialog or a toast
                                // on the main UI thread
                            }
                        });
                    }

                    @Override
                    public void onResponse(Call call, final Response response) throws IOException {
                        String res = response.body().string();

                        // Do something with the response

                        System.out.println(res);

                        if(res.equals("Event Created")) {
                            Intent intent = new Intent(getApplicationContext(), ToolbarActivity.class);
                            startActivity(intent);
                            finish();
                        } else if(res.equals("Empty Field")) {
                            Toast.makeText(getApplicationContext(),"All Field Required!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    // Method used to handle if the user wishes to cancel creating the event.
    public void cancelEvent(View view) {
        Intent myIntent = new Intent(this, ToolbarActivity.class);
        startActivity(myIntent);
    }
}
