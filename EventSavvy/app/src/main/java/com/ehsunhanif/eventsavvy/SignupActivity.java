package com.ehsunhanif.eventsavvy;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class SignupActivity extends AppCompatActivity {

    EditText fullName;
    EditText email;
    EditText username;
    EditText password;
    TextView loginPageButton;

    private final OkHttpClient client = new OkHttpClient();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        fullName = (EditText) findViewById(R.id.signup_fullName);
        email = (EditText) findViewById(R.id.signup_email);
        username = (EditText) findViewById(R.id.signup_username);
        password = (EditText) findViewById(R.id.signup_password);
    }

    public void signUp(View view) throws IOException {
        fullName = (EditText) findViewById(R.id.signup_fullName);
        email = (EditText) findViewById(R.id.signup_email);
        username = (EditText) findViewById(R.id.signup_username);
        password = (EditText) findViewById(R.id.signup_password);
        try {
            Request request = new Request.Builder()
                    .url("https://events-api-81942.herokuapp.com/eventsavvy-api/main/signup/"+fullName.getText()+"/"+email.getText()+"/"+username.getText()+"/"+password.getText()+"/") //"http://publicobject.com/helloworld.txt")
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
        Snackbar.make(findViewById(R.id.layout_signup),"Successfully Registered!",Snackbar.LENGTH_LONG).show();
        // Handler to creat pause before shifting activity to show message to user.
        Handler mHandler = new Handler();
        mHandler.postDelayed(startNewActivity, 3500);
    }

    public void loginActivity(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    // New thread to shift to login activity once sign up is successful.
    private Runnable startNewActivity = new Runnable() {
        @Override
        public void run() {
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
            finish();
        }
    };

    public void run() throws Exception {

    }


}
