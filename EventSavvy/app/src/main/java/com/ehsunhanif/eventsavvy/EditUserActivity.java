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

public class EditUserActivity extends AppCompatActivity {


    private final OkHttpClient client = new OkHttpClient();
    String username;
    SharedPreferences sharedPref;

    EditText fullName;
    EditText email;
    EditText password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);

        sharedPref =  PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        username = sharedPref.getString("username", "");

    }

    // Method to handle the cancellation of editing for the user.
    public void cancelUpdate(View view) {
        Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
        startActivity(intent);
    }

    // Method to handle the update of the users information
    public void updateUser(View view) {
        fullName = (EditText) findViewById(R.id.edit_user_fullName);
        email = (EditText) findViewById(R.id.edit_user_email);
        password = (EditText) findViewById(R.id.edit_user_password);

        try {
            Request request = new Request.Builder()
                    .url("https://events-api-81942.herokuapp.com/eventsavvy-api/main/signup/"+fullName.getText()+"/"+email.getText()+"/"+username+"/"+password.getText()+"/")
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
                        Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
                        startActivity(intent);
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
