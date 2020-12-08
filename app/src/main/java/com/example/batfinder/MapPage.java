package com.example.batfinder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
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
                            MarkerOptions options = new MarkerOptions().position(latlng).title("current location")
                                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED ));

                            // Zoom map
                            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latlng, 10));

                            // Add marker on map
                            googleMap.addMarker(options);
                            setBats(googleMap);


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