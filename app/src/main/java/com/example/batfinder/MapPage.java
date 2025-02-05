package com.example.batfinder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
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
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

public class MapPage extends AppCompatActivity{

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

        // Initialize fused location
        client = LocationServices.getFusedLocationProviderClient(this);

            getCurrentLocation();

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
    public void setBats(GoogleMap googleMap){
        String[] bat = new String[10];
        String[] snip = new String[10];
        double[] lat = new double[10];
        double[] lng = new double[10];
        bat[0] = "Alcathoe";
        lat[0] = 51.513;
        lng[0] = -0.1;
        snip[0] = "Date 12/01/2020";
        bat[1] = "Barbastelle";
        lat[1] = 51;
        lng[1] = -0.139;
        snip[1] = "Date 11/291/2020";
        bat[2] = "Bechstein";
        lat[2] = 51.987;
        lng[2] = -0.1000054;
        snip[2] = "Date 12/07/2020";
        bat[3] = "Brandt";
        lat[3] = 51.378;
        lng[3] = -0.081;
        snip[3] = "Date 12/04/2020";
        bat[4] = "Noctule";
        lat[4] = 51.0982;
        lng[4] = -0.102;
        snip[4] = "Date 11/26/2020";
        bat[5] = "Brown Long Eared";
        lat[5] = 52.089;
        lng[5] = -0.567;
        snip[5] = "Date 12/01/2020";
        bat[6] = "Common Pipistrelle";
        lat[6] = 50.954;
        lng[6] = -0.0986;
        snip[6] = "Date 12/10/2020";
        bat[7] = "Daubentons";
        lat[7] = 51.098;
        lng[7] = -0.09873;
        snip[7] = "Date 12/04/2020";
        bat[8] = "Greater Horseshoe";
        lat[8] = 51.254;
        lng[8] = -0.10983;
        snip[8] = "Date 12/09/2020";
        bat[9] = "Serotine";
        lat[9] = 51.0254;
        lng[9] = -0.10009;
        snip[9] = "Date 12/08/2020";

        for(int i = 0; i < bat.length; i++) {
            LatLng test = new LatLng(lat[i], lng[i]);
            MarkerOptions marker1 = new MarkerOptions().position(test).title(bat[i]).snippet(snip[i])
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_bat_logo_pin ));
            googleMap.addMarker(marker1);
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

                            // Add marker current location marker on map
                            googleMap.addMarker(options);

                            setBats(googleMap);

                            // added four random bat location markers
                            LatLng bat1 = new LatLng(51.509380, -0.080340);
                            MarkerOptions marker1 = new MarkerOptions().position(bat1).title("Alcathoe");
                            marker1.snippet("Date 11/15/2020")
                                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_bat_logo_pin ));
                            googleMap.addMarker(marker1);

                            LatLng bat2 = new LatLng(51.614725, -0.205020);
                            MarkerOptions marker2 = new MarkerOptions().position(bat2).title("Greater horseshoe");
                            marker2.snippet("Date 11/22/2020")
                                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_bat_logo_pin ));
                            googleMap.addMarker(marker2);

                            LatLng bat3 = new LatLng(51.570206, -0.079765);
                            MarkerOptions marker3 = new MarkerOptions().position(bat3).title("Serotine");
                            marker3.snippet("Date 12/01/2020")
                                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_bat_logo_pin ));
                            googleMap.addMarker(marker3);

                            LatLng bat4 = new LatLng(51.481243,  -0.210620);
                            MarkerOptions marker4 = new MarkerOptions().position(bat4).title("Noctule");
                            marker4.snippet("Date 11/01/2020")
                                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_bat_logo_pin ));
                            googleMap.addMarker(marker4);

                        }
                    });
                }
            }
        });
    }

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
        }else if (item.getItemId()== R.id.feedbackmenu) {
            openfeedback();
            return true;
        }else if (item.getItemId() == R.id.homemenu) {
            openhome();
            return true;
        }else
            return super.onOptionsItemSelected(item);
    }

    private void openhome() {
        Intent intent = new Intent(this, TaskPage.class);
        startActivity(intent);
    }

    private void openfeedback() {
        Intent intent = new Intent(this, Feedback.class);
        startActivity(intent);
    }

    public void openinfo(){
        Intent intent = new Intent(this, Cardview.class); // opens bat info page
        startActivity(intent);
    }

    public void openreport(){
        Intent intent = new Intent(this, SubmitReport.class); // opens submit report page
        startActivity(intent);
    }

    public void openmap(){
        Intent intent = new Intent(this, MapPage.class); // opens map page
        startActivity(intent);
    }
}