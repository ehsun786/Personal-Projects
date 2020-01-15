package com.ehsunhanif.eventsavvy;

import android.Manifest;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Address;
import android.location.Geocoder;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ehsunhanif.eventsavvy.domain.Event;
import com.google.android.gms.ads.internal.gmsg.HttpClient;
import com.google.android.gms.awareness.Awareness;
import com.google.android.gms.awareness.snapshot.WeatherResult;
import com.google.android.gms.awareness.state.Weather;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.gson.Gson;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Locale;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class EventDetails extends AppCompatActivity {

    TextView category;
    TextView title;
    TextView dateAndTime;
    TextView venue;
    TextView weather;
    ImageView staticMap;
    Event event = new Event();

    GoogleApiClient client;
    private final OkHttpClient httpclient = new OkHttpClient();
    SharedPreferences sharedPref;
    String username;
    String mode = "walking";
    StringBuilder stringBuilder = new StringBuilder("");
    String temp = "";
    Gson googleJson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);

        // Show the details of the event here and set information for the ui elements.
        Event actualEvent = getEventDetails();

        category = (TextView) findViewById(R.id.detail_category);
        title = (TextView) findViewById(R.id.detail_title);
        dateAndTime = (TextView) findViewById(R.id.detail_date_and_time);
        venue = (TextView) findViewById(R.id.detail_venue);
        weather = (TextView) findViewById(R.id.detail_weather);
        staticMap = (ImageView) findViewById(R.id.detail_static_map);

        sharedPref =  PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        username = sharedPref.getString("username", "");

        category.setText("Category: "+ actualEvent.getCategory());
        title.setText("Title: " + actualEvent.getTitle());
        dateAndTime.setText("Date: " +actualEvent.getDate()+"\nTime: "+actualEvent.getStartTime()+"-"+actualEvent.getEndTime());
        venue.setText("Venue: " + getVenue());
        weather.setText("Weather: "+ getWeatherConditions());
        staticMap.setImageBitmap(getGoogleMapThumbnail(Double.parseDouble(actualEvent.getLatitude()),Double.parseDouble(actualEvent.getLongitude())));

    }

    // We try to get the weather at the location of the venue here.
    public String getWeatherConditions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        } else {
            Awareness.SnapshotApi.getWeather(client)
                    .setResultCallback(new ResultCallback<WeatherResult>() {
                        @Override
                        public void onResult(@NonNull WeatherResult weatherResult) {
                            if (weatherResult.getStatus().isSuccess()) {
                                Weather weather = weatherResult.getWeather();
                                // do stuff with Weather object!
                                float temprature = weather.getTemperature(Weather.CELSIUS);
                                DecimalFormat df = new DecimalFormat("#.##");
                                temp = df.format(temprature);
                                int[] conditions = weather.getConditions();
                                stringBuilder = new StringBuilder();
                                if (conditions.length > 0) {
                                    for (int i = 0; i < conditions.length; i++) {
                                        if (i > 0) {
                                            stringBuilder.append(", ");
                                        }
                                        stringBuilder.append(retrieveConditionString(conditions[i]));
                                    }
                                }
                            }
                        }
                    });
        }
        return temp+" ℃ "+stringBuilder.toString();
    }

    // https://github.com/hitherejoe/Aware || Some of the code taken from this source is boiler plate code so there might be a complete match.
    private String retrieveConditionString(int condition) {
        if(condition==Weather.CONDITION_CLEAR) {
            return getString(R.string.condition_clear);
        } else if(condition==Weather.CONDITION_CLOUDY) {
            return getString(R.string.condition_cloudy);
        } else if(condition==Weather.CONDITION_FOGGY) {
            return getString(R.string.condition_foggy);
        } else if(condition==Weather.CONDITION_HAZY) {
            return getString(R.string.condition_hazy);
        } else if(condition==Weather.CONDITION_ICY) {
            return getString(R.string.condition_icy);
        } else if(condition==Weather.CONDITION_RAINY) {
            return getString(R.string.condition_rainy);
        } else if(condition==Weather.CONDITION_SNOWY) {
            return getString(R.string.condition_snowy);
        } else if(condition==Weather.CONDITION_STORMY) {
            return getString(R.string.condition_stormy);
        } else if(condition==Weather.CONDITION_WINDY) {
            return getString(R.string.condition_windy);
        } else {
            return getString(R.string.condition_unknown);
        }
    }

    public void setStringBuilder(StringBuilder stringBuilder) {
        this.stringBuilder = stringBuilder;
    }

    public StringBuilder getStringBuilder() {
        return stringBuilder;
    }

    // We try to get the event details here to show it's details.
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

    // We use the Google Static Maps API here to get the image of the map location of the event venue.
    public static Bitmap getGoogleMapThumbnail(double latitude, double longitude){
        String googleStaticUrl = "http://maps.google.com/maps/api/staticmap?center=" +
                latitude + "," + longitude + "&zoom=15&size=340x430&maptype=roadmap&sensor=false&key=AIzaSyABGh98N82xsBN-BLyThifLeRWOR6n5h5w";
        Bitmap bmp = null;
        try {
            URL actionUrl = new URL(googleStaticUrl);
            final HttpURLConnection connection = (HttpURLConnection) actionUrl.openConnection();
            connection.setRequestMethod("GET");
            InputStream in = new BufferedInputStream(connection.getInputStream());
            bmp = BitmapFactory.decodeStream(in);
            in.close();
        } catch (Exception e) {
        }
        return bmp;
    }

    // Here we translate the latitude and longitude to a human readable address using
    // the Google Geocoding API.
    public String getVenue() {
        Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
        String address = "";
        try {
            List<Address> addressList =
                    geocoder.getFromLocation(Double.parseDouble(event.getLatitude()), Double.parseDouble(event.getLongitude()), 1);
            if(addressList != null && addressList.size()>0) {
                if(addressList.get(0).getThoroughfare()!= null) {
                    address+=addressList.get(0).getThoroughfare()+" ";
                }
                if(addressList.get(0).getLocality()!= null) {
                    address+=addressList.get(0).getLocality()+" ";
                }
                if(addressList.get(0).getPostalCode()!= null) {
                    address+=addressList.get(0).getPostalCode()+" ";
                }
                if(addressList.get(0).getAdminArea()!= null) {
                    address+=addressList.get(0).getAdminArea();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        if(address.equals("")) {
          return "Venue Not Found";
        } else {
            return address;
        }
    }

    // Method invoked when the user wishes to help with an event voluntarily.
    public void eventHelper(View view) {
        Request request = new Request.Builder()
                .url("https://events-api-81942.herokuapp.com/eventsavvy-api/main/sendHelpEmail/"+username+"/"+event.getId())
                .build();
        httpclient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });
    }

    // Method invoked if the user wishes to attend an event.
    public void attendEvent(View view) {
        Request request = new Request.Builder()
                .url("https://events-api-81942.herokuapp.com/eventsavvy-api/main/attendEvent/"+username+"/"+event.getId())
                .build();
        httpclient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });
    }
}
