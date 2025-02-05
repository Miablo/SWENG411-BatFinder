package com.example.batfinder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.net.Uri;

import com.example.batfinder.ui.login.LoginActivity;

public class MainActivity extends AppCompatActivity {

    static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 23;
    static final int MY_PERMISSIONS_REQUEST_READ_CAMERA = 34;
    static final int  MY_PERMISSIONS_REQUEST_READ_External = 33;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkPermission(); // calls check permission
        Button buttonguest = findViewById(R.id.Guest);
        Button buttonLogin = findViewById(R.id.Login);
        Button buttonFeedback = findViewById(R.id.feedback);

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
        }); //calls openDisclaimer method with a onClick

        buttonFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFeedback();
            }
        }); //calls openFeedback method with a onClick

    }

    private void checkPermission() { // ask for any permissions need add here for future permissions
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) //Ask for rough location permission
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, //Ask for fine location permission
                    MY_PERMISSIONS_REQUEST_READ_CONTACTS);
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
        != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA},
                    MY_PERMISSIONS_REQUEST_READ_CAMERA);
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    MY_PERMISSIONS_REQUEST_READ_External);
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

    private void openFeedback() {
        Intent intent = new Intent(this, Feedback.class);
        startActivity(intent);
    }

}