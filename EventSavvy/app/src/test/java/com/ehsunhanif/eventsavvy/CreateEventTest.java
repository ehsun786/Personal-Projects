package com.ehsunhanif.eventsavvy;

import android.content.Intent;
import android.widget.Toast;

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

public class CreateEventTest {

    static OkHttpClient client = new OkHttpClient();

    public static void main(String... args) {
        createEvent();
    }

    public static void createEvent() {
        FormBody.Builder formBuilder = new FormBody.Builder().add("title", "Singing Session");
        formBuilder.add("category", "Music/Entertainment");
        formBuilder.add("date", "03/08/2019");
        formBuilder.add("startTime", "6:00 PM");
        formBuilder.add("endTime", "8:00 PM");
        formBuilder.add("longitude", "40.7644691");
        formBuilder.add("latitude", "-73.9766764");
        formBuilder.add("username", "ehsun786");
        RequestBody formBody = formBuilder.build();
        Request request = new Request.Builder()
                .url("https://events-api-81942.herokuapp.com/eventsavvy-api/main/createEvent/")
                .post(formBody)
                .build();
        client.newCall(request)
                .enqueue(new Callback() {
                    @Override
                    public void onFailure(final Call call, IOException e) {
                    }
                    @Override
                    public void onResponse(Call call, final Response response) throws IOException {
                        String res = response.body().string();
                        assertEquals(true, response.isSuccessful());
                        System.out.println(res);
                    }
                });
    }

}
