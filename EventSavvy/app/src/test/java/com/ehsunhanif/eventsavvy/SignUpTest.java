package com.ehsunhanif.eventsavvy;

import org.junit.Test;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class SignUpTest {
    @Test
    public void signUp() {
        OkHttpClient client = new OkHttpClient();

        try {
            Request request = new Request.Builder()
                    .url("https://events-api-81942.herokuapp.com/eventsavvy-api/main/signup/"+"Ehsun Hanif"+"/"+"ehsun@hanif.com"+"/"+"ehsun786"+"/"+"123456"+"/")
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
                        assertEquals(true, response.isSuccessful());
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}