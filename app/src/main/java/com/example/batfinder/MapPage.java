package com.example.batfinder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.List;

public class MapPage extends AppCompatActivity{

    private GoogleMap mMap;
    LocationManager locationManager;
    LocationListener locationListener;
    double longitude;
    double  latitude;

    FusedLocationProviderClient client;
    SupportMapFragment mapFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_page);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapView);
        //assert mapFragment != null;
        //mapFragment.getMapAsync((OnMapReadyCallback) this);

        // Initialize fused location
        client = LocationServices.getFusedLocationProviderClient(this);


        // check permission
       // if(ActivityCompat.checkSelfPermission(MapPage.this,
         //       Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
       // {
            // When permission granted
            getCurrentLocation();
       // }
      //  else{
            // When permission denied
            // Request permission
       //     ActivityCompat.requestPermissions(MapPage.this,
        //            new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
       // }

        locationManager = (LocationManager) this.getSystemService(LOCATION_SERVICE);
        boolean isGPS_enabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        if(isGPS_enabled){
            locationListener = new LocationListener() {
                @Override
                public void onLocationChanged(@NonNull Location location) {
                    longitude = location.getLongitude();
                    latitude = location.getLatitude();


                }
            };
        }
    }

    private void getCurrentLocation(){
        // Initialize task location
        @SuppressLint("MissingPermission") Task<Location> task = client.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                // When success
                if(location != null){
                    // Sync map
                    mapFragment.getMapAsync(new OnMapReadyCallback() {
                        @Override
                        public void onMapReady(GoogleMap googleMap) {
                            // Initialize lat lng
                            LatLng latlng = new LatLng(location.getLatitude(),
                                    location.getLongitude());

                            // Create marker options
                            MarkerOptions options = new MarkerOptions().position(latlng).title("current location");

                            // Zoom map
                            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latlng, 10));

                            // Add marker on map
                            googleMap.addMarker(options);

                            // adds marker to the bat locations
                            //LatLng markerLocation = getLocationFromAddress(new Geocoder(getApplicationContext()), "St Katharine's & Wapping, London EC3N 4AB, United Kingdom");
                            LatLng test = new LatLng(51.509380, -0.080340);
                            MarkerOptions marker1 = new MarkerOptions().position(test).title("TEST");
                            googleMap.addMarker(marker1);

                        }
                    });
                }
            }
        });
    }

    /*@Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == 44){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                // When permission granted
                getCurrentLocation();
            }
        }
    }*/

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_layout, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        if (item.getItemId() == R.id.mapmenu) {
            openmap();
            return true;
        } else if (item.getItemId() == R.id.infomenu) {
            openinfo();
            return true;
        } else if (item.getItemId() == R.id.reportmenu) {
            openreport();
            return true;
        }else
            return super.onOptionsItemSelected(item);
    }

    public void openinfo(){
        Intent intent = new Intent(this, Cardview.class);
        startActivity(intent);
    }

    public void openreport(){
        Intent intent = new Intent(this, submitReport.class); //need to fix right paige.
        startActivity(intent);
    }

    public void openmap(){
        Intent intent = new Intent(this, MapPage.class); //need to fix right paige.
        startActivity(intent);
    }

   /* @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
    }

    public LatLng getLocationFromAddress(Geocoder context, String strAddress) {

        Geocoder coder = context;
        List<Address> address;
        LatLng p1 = null;

        try {
            // May throw an IOException
            address = coder.getFromLocationName(strAddress, 5);
            if (address == null) {
                return null;
            }

            Address location = address.get(0);
            p1 = new LatLng(location.getLatitude(), location.getLongitude() );

        } catch (IOException ex) {

            ex.printStackTrace();
        }

        return p1;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }*/
}