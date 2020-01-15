package com.ehsunhanif.eventsavvy;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;

import org.junit.Test;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static org.junit.Assert.assertEquals;

public class LoginTest {
    OkHttpClient client = new OkHttpClient();

    @Test
    public void login() {
        FormBody.Builder formBuilder = new FormBody.Builder().add("username", "ehsun786");
        formBuilder.add("password", "123456");
        RequestBody formBody = formBuilder.build();
        Request request = new Request.Builder()
                .url("https://events-api-81942.herokuapp.com/eventsavvy-api/main/login/")
                .post(formBody)
                .build();
        client.newCall(request)
                .enqueue(new Callback() {
                    @Override
                    public void onFailure(final Call call, IOException e) {
                        // Error
                    }
                    @Override
                    public void onResponse(Call call, final Response response) throws java.io.IOException {
                        String res = response.body().string();
                        // Do something with the response
                        assertEquals(true, res.equals("Authenticated"));
                    }
                });
    }



}
