package com.eckstein.paige.knittingcompanion;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.FrameLayout;

import com.eckstein.paige.knittingcompanion.BaseClasses.BaseActivity_Map;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class StoreSearchActivity extends BaseActivity_Map implements LocationListener {

    FrameLayout main;
    SupportMapFragment map;
    private static GoogleMap googleMap;

    LocationManager locMan;
    Marker userMarker;
    static Marker[] placeMarkers;
    private final int MAX_PLACES = 20;
    private static boolean updateFinished = true;
    private static MarkerOptions[] places;

    @Override
    public void onCreate(Bundle onSavedInstance) {
        super.onCreate(onSavedInstance);
        //setContentView(R.layout.activity_map);

        main = findViewById(R.id.mainFrame);
        map = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapContainer);

        map.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap gMap) {
                googleMap = gMap;
//check in case map/ Google Play services not available
                if (googleMap != null) {
                    //ok - proceed
                    googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                    //create marker array
                    placeMarkers = new Marker[MAX_PLACES];
                    //update location
                    locMan = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                    if (ContextCompat.checkSelfPermission(StoreSearchActivity.this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                            || ContextCompat.checkSelfPermission(StoreSearchActivity.this, android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                        locMan.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 30000, 100, StoreSearchActivity.this);
                    }
                }
            }
        });

        //((ViewGroup)map.getParent()).removeView(map);
        //main.addView(map);
    }

    @Override
    public void onLocationChanged(Location location) {
        Log.v("MyMapActivity", "location changed");
        Log.v("Test", "location change block");
        updatePlaces();
    }

    @Override
    public void onProviderDisabled(String provider) {
        Log.v("MyMapActivity", "provider disabled");
    }

    @Override
    public void onProviderEnabled(String provider) {
        Log.v("MyMapActivity", "provider enabled");
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Log.v("MyMapActivity", "status changed");
    }

    /*
     * update the place markers
     */
    private void updatePlaces() {
        //get location manager
        //get last location
        Location lastLoc = null;

        if (ContextCompat.checkSelfPermission(StoreSearchActivity.this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(StoreSearchActivity.this, android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            lastLoc = locMan.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        }
        double lat = lastLoc.getLatitude();
        double lng = lastLoc.getLongitude();
        //create LatLng
        LatLng lastLatLng = new LatLng(lat, lng);

        //remove any existing marker
        if (userMarker != null) userMarker.remove();
        //create and set marker properties
        userMarker = googleMap.addMarker(new MarkerOptions()
                .position(lastLatLng)
                .title("You are here")
                .snippet("Your last recorded location"));
        //move to location
        googleMap.animateCamera(CameraUpdateFactory.newLatLng(lastLatLng), 3000, null);

        //build places query string
        String placesSearchStr = "https://maps.googleapis.com/maps/api/place/textsearch/" +
                "json?location=" + lat + "," + lng +
                "&radius=1000&sensor=true" +
                "&query=yarn" +
                "&key=" + getResources().getString(R.string.API_KEY);//ADD KEY

        //execute query
        new GetPlaces().execute(placesSearchStr);
    }

    private static class GetPlaces extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... placesURL) {
            //fetch places
            updateFinished = false;
            StringBuilder placesBuilder = new StringBuilder();
            for (String placeSearchURL : placesURL) {
                try {

                    URL requestUrl = new URL(placeSearchURL);
                    HttpURLConnection connection = (HttpURLConnection) requestUrl.openConnection();
                    connection.setRequestMethod("GET");
                    connection.connect();
                    int responseCode = connection.getResponseCode();

                    if (responseCode == HttpURLConnection.HTTP_OK) {

                        BufferedReader reader = null;

                        InputStream inputStream = connection.getInputStream();
                        if (inputStream == null) {
                            return "";
                        }
                        reader = new BufferedReader(new InputStreamReader(inputStream));

                        String line;
                        while ((line = reader.readLine()) != null) {

                            placesBuilder.append(line + "\n");
                        }

                        if (placesBuilder.length() == 0) {
                            return "";
                        }

                        Log.d("test", placesBuilder.toString());
                    } else {
                        Log.i("test", "Unsuccessful HTTP Response Code: " + responseCode);
                    }
                } catch (MalformedURLException e) {
                    Log.e("test", "Error processing Places API URL", e);
                } catch (IOException e) {
                    Log.e("test", "Error connecting to Places API", e);
                }
            }
            return placesBuilder.toString();
        }

        //process data retrieved from doInBackground
        protected void onPostExecute(String result) {
            //parse place data returned from Google Places
            //remove existing markers
            if (placeMarkers != null) {
                for (int pm = 0; pm < placeMarkers.length; pm++) {
                    if (placeMarkers[pm] != null)
                        placeMarkers[pm].remove();
                }
            }
            try {
                //parse JSON

                //create JSONObject, pass stinrg returned from doInBackground
                JSONObject resultObject = new JSONObject(result);
                //get "results" array
                JSONArray placesArray = resultObject.getJSONArray("results");
                //marker options for each place returned
                places = new MarkerOptions[placesArray.length()];
                //loop through places

                Log.d("test", "The placesArray length is " + placesArray.length() + "...............");

                for (int p = 0; p < placesArray.length(); p++) {
                    //parse each place
                    //if any values are missing we won't show the marker
                    boolean missingValue = false;
                    LatLng placeLL = null;
                    String placeName = "";
                    String vicinity = "";
                    try {
                        //attempt to retrieve place data values
                        missingValue = false;
                        //get place at this index
                        JSONObject placeObject = placesArray.getJSONObject(p);
                        //get location section
                        JSONObject loc = placeObject.getJSONObject("geometry")
                                .getJSONObject("location");
                        //read lat lng
                        placeLL = new LatLng(Double.valueOf(loc.getString("lat")),
                                Double.valueOf(loc.getString("lng")));
                        //get types
                        JSONArray types = placeObject.getJSONArray("types");
                        //vicinity
                        vicinity = placeObject.getString("vicinity");
                        //name
                        placeName = placeObject.getString("name");
                    } catch (JSONException jse) {
                        Log.v("PLACES", "missing value");
                        missingValue = true;
                        jse.printStackTrace();
                    }
                    //if values missing we don't display
                    if (missingValue) places[p] = null;
                    else
                        places[p] = new MarkerOptions()
                                .position(placeLL)
                                .title(placeName)
                                .snippet(vicinity);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (places != null && placeMarkers != null) {
                Log.d("test", "The placeMarkers length is " + placeMarkers.length + "...............");

                for (int p = 0; p < places.length && p < placeMarkers.length; p++) {
                    //will be null if a value was missing

                    if (places[p] != null) {

                        placeMarkers[p] = googleMap.addMarker(places[p]);
                    }
                }
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (googleMap != null) {
            if (ContextCompat.checkSelfPermission(StoreSearchActivity.this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                    || ContextCompat.checkSelfPermission(StoreSearchActivity.this, android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                locMan.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 30000, 100, this);
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (googleMap != null) {
            locMan.removeUpdates(this);
        }
    }

}
