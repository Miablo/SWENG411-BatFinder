package com.example.batfinder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.batfinder.ui.login.LoginActivity;

public class MainActivity extends AppCompatActivity {

    static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 23;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkPermission(); // calls check permission
        Button buttonguest = findViewById(R.id.Guest);
        Button buttonLogin = findViewById(R.id.Login);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openLogin();
            }
        }); // calls openLogin method with a onClick
        buttonguest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDisclaimer();
            }
        }); //calls openDiclamier method with a onClick
    }

    private void checkPermission() { // ask for any permissions need add here for future permissions
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) //Ask for rough location permission
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, //Ask for fine location permission
                    MY_PERMISSIONS_REQUEST_READ_CONTACTS);
        }
    }

    public void openDisclaimer(){
        Intent intent = new Intent(this, Disclaimer.class); // adds intent to Disclaimer
        startActivity(intent); //opens disclaimer page
    }

    public void openLogin(){
        Intent intent = new Intent(this, LoginActivity.class); // adds intent to Login
        startActivity(intent); //open login page
    }
}