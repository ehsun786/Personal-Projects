package com.ehsunhanif.eventsavvy;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class LoginActivity extends AppCompatActivity {
    public static final String TOOLBAR_FRAGMENT = "toolbar_fragment";
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;

    private final OkHttpClient client = new OkHttpClient();

    RelativeLayout relativeLayout;
    EditText username;
    EditText password;
    TextView error;
    SharedPreferences sharedPref;

    // Method invoked upon start of activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = (EditText) findViewById(R.id.signup_username);
        password = (EditText) findViewById(R.id.password);
        error = (TextView) findViewById(R.id.error);
        relativeLayout = (RelativeLayout) findViewById(R.id.loginLayout);



        Context context = getApplicationContext();
        sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String username = sharedPref.getString("username", "");
        System.out.println("Login£££££££££££££££££££"+username);

        if(!username.equals("")){
            Intent intent = new Intent(getApplicationContext(), ToolbarActivity.class);
            startActivity(intent);
            finish();
        }
        //Use line below to logout
        //sharedPref.edit().clear().commit();
    }

    // Method to shift to registration activity
    public void registerActivity(View view) {
        Intent intent = new Intent(this, SignupActivity.class);
        startActivity(intent);
    }

    // Method to handle the logging in of a user
    public void login(View view) throws IOException {
        FormBody.Builder formBuilder = new FormBody.Builder().add("username", username.getText().toString());
        formBuilder.add("password", password.getText().toString());
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
                        if(res.equals("Authenticated")) {
                            sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                            SharedPreferences.Editor editor = sharedPref.edit();
                            editor.putString("username",username.getText().toString());
                            editor.apply();
                            Intent intent = new Intent(getApplicationContext(), ToolbarActivity.class);
                            startActivity(intent);
                            finish();
                        } else if(res.equals("Wrong Credentials")) {
                            Snackbar.make(relativeLayout, "Wrong Credentials", Snackbar.LENGTH_LONG).show();
                        } else if(res.equals("Not Registred")) {
                            Snackbar.make(relativeLayout, "Not Registered", Snackbar.LENGTH_LONG).show();
                        }
                    }
                });
    }
}
