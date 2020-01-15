package com.ehsunhanif.eventsavvy;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.ehsunhanif.eventsavvy.domain.Event;
import com.ehsunhanif.eventsavvy.domain.RouteDrawer;
import com.google.android.gms.awareness.Awareness;
import com.google.android.gms.awareness.snapshot.DetectedActivityResult;
import com.google.android.gms.awareness.snapshot.LocationResult;
import com.google.android.gms.awareness.snapshot.WeatherResult;
import com.google.android.gms.awareness.state.Weather;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.ActivityRecognitionResult;
import com.google.android.gms.location.DetectedActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.gson.Gson;

import org.w3c.dom.Document;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ToolbarActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, OnMapReadyCallback {

    private GoogleMap mMap;
    LocationManager locationManager;
    LocationListener locationListener;
    GoogleApiClient client;
    private final OkHttpClient httpclient = new OkHttpClient();
    SharedPreferences sharedPref;
    double userLocationLong = 0;
    double userLocationLat = 0;
    String username;
    String mode = "walking";
    StringBuilder stringBuilder = new StringBuilder("");
    String temp = "";
    Gson googleJson;
    List<Event> events = new ArrayList<Event>();
    MarkerOptions marker;
    Polyline polylin;
    double initLatitude;
    double initLongitude;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ToolbarActivity.class);
                startActivity(intent);
                finish();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        sharedPref =  PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        username = sharedPref.getString("username", "");
        System.out.println("Toolbar£££££££££££££££££££"+username);
        client = new GoogleApiClient.Builder(this)
                .addApi(Awareness.API)
                .build();
        client.connect();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }

    // Handle top right menu button selection
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_report) {
            Intent intent = new Intent(getApplicationContext(), ReportActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // Hanlde top left menu button selection
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_settings) {
            Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_help) {
            Intent intent = new Intent(getApplicationContext(), HelpActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_logout) {
            sharedPref =  PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            sharedPref.edit().remove("username").commit();
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    // Method invoked once the user has granted location permission.
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    locationManager.requestLocationUpdates(locationManager.GPS_PROVIDER, 0, 0, locationListener);

                }
            }
        }
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                LatLng currentUserLocation = new LatLng(location.getLatitude(), location.getLongitude());
                mMap.clear();
                mMap.addMarker(new MarkerOptions().position(currentUserLocation).title("Your Location"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(currentUserLocation));

                Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());

                try {
                    List<Address> listAddresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                    if (listAddresses != null && listAddresses.size() > 0) {
                        Log.i("PlaceInfo", listAddresses.get(0).toString());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };

        if (Build.VERSION.SDK_INT >= 23) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            } else {
                locationManager.requestLocationUpdates(locationManager.GPS_PROVIDER, 600000, 100, locationListener);
            }
        } else {
            locationManager.requestLocationUpdates(locationManager.GPS_PROVIDER, 600000, 100, locationListener);
            Location lastKnowLocation = locationManager.getLastKnownLocation(locationManager.GPS_PROVIDER);

            LatLng currentUserLocation = new LatLng(lastKnowLocation.getLatitude(), lastKnowLocation.getLongitude());
            mMap.clear();
            mMap.addMarker(new MarkerOptions().position(currentUserLocation).title("Your Location"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(currentUserLocation));
        }


        mMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
            @Override
            public void onMapLoaded() {
                Awareness.SnapshotApi.getLocation(client)
                        .setResultCallback(new ResultCallback<LocationResult>() {
                            @Override
                            public void onResult(@NonNull LocationResult locationResult) {
                                if (locationResult.getStatus().isSuccess()) {
                                    Location location = locationResult.getLocation();
                                    // do stuff with Location object!
                                    double latitude = location.getLatitude();
                                    double longitude = location.getLongitude();
                                    userLocationLat = latitude;
                                    userLocationLong = longitude;
                                    final LatLng userLocation = new LatLng(latitude, longitude);
                                    BitmapDescriptor userLocationBitmap
                                            = BitmapDescriptorFactory.defaultMarker(
                                            BitmapDescriptorFactory.HUE_GREEN);
                                    // Add marker to signify current location of the use.
                                    mMap.addMarker(new MarkerOptions().position(userLocation).title("Your Location").icon(userLocationBitmap));
                                    // Populate map with all available events.
                                    try {
                                        Request request = new Request.Builder()
                                                .url("https://events-api-81942.herokuapp.com/eventsavvy-api/main/getAllEvents")
                                                .build();

                                        httpclient.newCall(request).enqueue(new Callback() {
                                            @Override
                                            public void onFailure(Call call, IOException e) {
                                                e.printStackTrace();
                                            }

                                            @Override
                                            public void onResponse(Call call, Response response) throws IOException {
                                                initLatitude = userLocation.latitude;
                                                initLongitude = userLocation.longitude;
                                                double eventLatitude = 0;
                                                double eventLongitude = 0;
                                                String res = response.body().string();
                                                res = res.substring(1, res.length()-1);
                                                List<String> tmpList = Arrays.asList(res.split(","));
                                                googleJson = new Gson();

                                                for(String s: tmpList) {
                                                    Request request = new Request.Builder()
                                                            .url("https://events-api-81942.herokuapp.com/eventsavvy-api/main/getEventById/"+s)
                                                            .build();
                                                    httpclient.newCall(request).enqueue(new Callback() {
                                                        @Override
                                                        public void onFailure(Call call, IOException e) {

                                                        }

                                                        @Override
                                                        public void onResponse(Call call, Response response) throws IOException {
                                                            String res = response.body().string();
                                                            //Type objectType = new TypeToken<Event>(){}.getType();
                                                            System.out.println("Res!!!!!!!!!!!!!!!!"+res);
                                                            Event event = googleJson.fromJson(res, Event.class);
                                                            //events.add(event);
                                                            double eventLatitude = Double.parseDouble(event.getLatitude());
                                                            double eventLongitude = Double.parseDouble(event.getLongitude());
                                                            LatLng eventLocation = new LatLng(eventLatitude, eventLongitude);
                                                            BitmapDescriptor eventLocationBitmap
                                                                    = BitmapDescriptorFactory.defaultMarker(
                                                                    BitmapDescriptorFactory.HUE_RED);
//                                                            if(!username.equals("")) {
//                                                                eventLocationBitmap = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE);
//                                                            }
                                                            String weatherCond = getWeatherConditions();
                                                            double distance = getDistance(initLatitude,initLongitude,eventLatitude,eventLongitude);
                                                            if(distance<5000){
                                                                marker =new MarkerOptions()
                                                                        .position(eventLocation)
                                                                        .title(event.getCategory()).icon(eventLocationBitmap)
                                                                        .snippet(event.getId()+ ". "+event.getTitle()+
                                                                                "\nDate: "+event.getDate()+
                                                                                "\nTime: "+event.getStartTime()+"-"+event.getEndTime()+
                                                                                "\nWeather: " + weatherCond
                                                                                +"\nOrganizer: "+event.getUsername()
                                                                                +"\nAttendees: "+getAttendees(event.getId())
                                                                                +"\nDistance: "+distance);

                                                                runOnUiThread(new Runnable() {
                                                                    @Override
                                                                    public void run() {
                                                                        Marker m = mMap.addMarker(marker);
                                                                        m.showInfoWindow();
                                                                    }
                                                                });

                                                            }
                                                        }
                                                    });
                                                }
                                                // Populate map with events from the list.
                                                for(Event event: events) {

                                                }

                                            }
                                        });
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    // Move map position to the users location with the specified zoom level 15
                                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation,15));
                                }
                            }
                        });
            }
        });

        // Handles the creation of event through long click on the map
        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                Intent intent = new Intent(getApplicationContext(), CreateEventActivity.class);
                intent.putExtra("latitude", latLng.latitude);
                intent.putExtra("longitude", latLng.longitude);
                startActivity(intent);
            }
        });

        // Handler for when the marker is clicked to show info windows and route to event venue.
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
//                LatLng userLocation = new LatLng(userLocationLat, userLocationLong);
//                LatLng markerLatLng = marker.getPosition();
//                if(polylin!=null) {
//                    polylin.remove();
//                }
//                if(userLocation.equals(markerLatLng)) {
//
//                } else {
//                    RouteDrawer routeDrawer = new RouteDrawer();
//                    String mostProbableActivity = getProbableActivity();
//                    Document doc = routeDrawer.getDocument(userLocation, markerLatLng, mostProbableActivity);
//                    ArrayList<LatLng> directionPoint = routeDrawer.getDirection(doc);
//                    PolylineOptions routeLine = new PolylineOptions().width(3).color(
//                            Color.RED);
//
//                    for (int i = 0; i < directionPoint.size(); i++) {
//                        routeLine.add(directionPoint.get(i));
//                    }
//                    polylin = mMap.addPolyline(routeLine);
//
//                    marker.showInfoWindow();
//
//                }
                marker.showInfoWindow();
                return false;
            }
        });

        // Handler for when window is long clicked.
        // Shows event detail screen if the user is not the event creator.
        // Shows event editing screen if the user is the creator.
        mMap.setOnInfoWindowLongClickListener(new GoogleMap.OnInfoWindowLongClickListener() {
            @Override
            public void onInfoWindowLongClick(Marker marker) {
                String snippet = marker.getSnippet();
                List<String> tmpList = Arrays.asList(snippet.split("."));
                final int id = Integer.parseInt(tmpList.get(0));
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
                        Event event = googleJson.fromJson(res, Event.class);
                        sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                        String username = sharedPref.getString("username", "");
                        // If the creator long clicks on the Info Window
                        if(event.getUsername().equals(username)) {
                            Intent intent = new Intent(getApplicationContext(), EditEventActivity.class);
                            intent.putExtra("eventId", id);
                            startActivity(intent);
                            finish();
                        }
                        // If a non-creator long clicks on the Info Window
                        if(!event.getUsername().equals(username)) {
                            Intent intent = new Intent(getApplicationContext(), EventDetails.class);
                            intent.putExtra("eventId", id);
                            startActivity(intent);
                            finish();
                        }
                    }
                });
            }
        });

    }

    // https://stackoverflow.com/questions/17787235/creating-a-method-using-haversine-formula-android-v2
    public static double getDistance(double lat1, double long1,
                                        double lat2, double long2){
        int radius = 6371; // km (Earth radius)
        double LatitudeDifference = toRadians(lat2-lat1);
        double longitudeDifference = toRadians(long2 -long1);
        lat1 = toRadians(lat1);
        lat2 = toRadians(lat2);

        double a = Math.sin(LatitudeDifference/2) * Math.sin(LatitudeDifference/2) +
                Math.sin(longitudeDifference/2) * Math.sin(longitudeDifference/2) * Math.cos(lat1) * Math.cos(lat2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        return radius * c;
    }

    // https://stackoverflow.com/questions/17787235/creating-a-method-using-haversine-formula-android-v2
    public static double toRadians(double degrees) {
        return degrees * (Math.PI/180);
    }

    // Using Awareness API to see what the user is doing to show him/her the specified
    // route, e.g. if the user is on foot then the user would be shown the walking route
    // on map.
    public String getProbableActivity() {
        Awareness.SnapshotApi.getDetectedActivity(client)
                .setResultCallback(new ResultCallback<DetectedActivityResult>() {
                    @Override
                    public void onResult(@NonNull DetectedActivityResult detectedActivityResult) {
                        if (detectedActivityResult.getStatus().isSuccess()) {
                            ActivityRecognitionResult activityRecognitionResult = detectedActivityResult.getActivityRecognitionResult();
                            // do stuff with ActivityRecognitionResult object!
                            DetectedActivity probAct = activityRecognitionResult.getMostProbableActivity();
                            mode = "walking";
                            if(probAct.getType()==DetectedActivity.IN_VEHICLE) {
                                mode = "driving";
                            } else if(probAct.getType()==DetectedActivity.ON_BICYCLE){
                                mode = "bicycling";
                            } else if(probAct.getType()==DetectedActivity.ON_FOOT
                                    ||probAct.getType()==DetectedActivity.RUNNING
                                    ||probAct.getType()==DetectedActivity.STILL) {
                                mode = "walking";
                            }
                        }
                    }
                });
        return mode;
    }

    // Using awareness API to get weather conditions at the current location of the user.
    public String getWeatherConditions() {
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
        return temp+" ℃ "+stringBuilder.toString();
    }

    //https://github.com/hitherejoe/Aware Some of the code taken from this source is boiler plate code so there might be a complete match.
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

    // Method to show attendees for the event. To be shown on info window.
    public long getAttendees(long eventId) {
        final String[] res = {""};
        Request request = new Request.Builder()
                .url("https://events-api-81942.herokuapp.com/eventsavvy-api/main/getEventAttendees/"+eventId)
                .build();

        httpclient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                res[0] = response.body().string();
            }
        });
        if(res[0].equals("")) {
            return 0;
        } else {
            return Long.parseLong(res[0]);
        }
    }

}
