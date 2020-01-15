package com.ehsunhanif.eventsavvy;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class ReportActivity extends AppCompatActivity {


    private final OkHttpClient client = new OkHttpClient();
    String username;
    SharedPreferences sharedPref;

    EditText reportText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        sharedPref =  PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        username = sharedPref.getString("username", "");

        reportText = (EditText) findViewById(R.id.report_text);

    }

    // Method to handle report submission
    public void submitReport(View view) {
        reportText = (EditText) findViewById(R.id.report_text);
        try {
            Request request = new Request.Builder()
                    .url("https://events-api-81942.herokuapp.com/eventsavvy-api/main/report/"+username+"/"+reportText.getText())
                    .build();

            client.newCall(request).enqueue(new Callback() {
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

    public void cancelReport(View view) {
        Intent intent = new Intent(getApplicationContext(), ToolbarActivity.class);
        startActivity(intent);
    }


}
