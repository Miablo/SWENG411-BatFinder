    package com.example.batfinder;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static android.os.Environment.getExternalStoragePublicDirectory;

    public class SubmitReport extends AppCompatActivity {

        String pathToFile;
        ImageView photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_report);

        Button uploadbtn = (Button) findViewById(R.id.UploadBtn);
        photo = findViewById(R.id.pic);
        uploadbtn.setOnClickListener(v -> {
            if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    takePic();

                }
            }
        });
        // Button Submit Report Functionality
        Button submitButton = (Button) findViewById(R.id.reportsubmit);
        // perform click event on button
        submitButton.setOnClickListener(v -> {
            // Snackbar pop up at bottom to display confirmation message
            Snackbar.make(v, "Report Submitted Successfully. Thank You!", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            finish();
        });

    }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
                if(requestCode == 1){
                    Bitmap bitmap = (Bitmap)data.getExtras().get("data");
                    photo.setImageBitmap(bitmap);
                    photo.setVisibility(View.VISIBLE);
                    TextView pictext = findViewById(R.id.addphototext);
                    pictext.setVisibility(View.GONE);

            }
        }

        public void takePic(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent,  1);

        }


        public boolean onCreateOptionsMenu(Menu menu){
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.menu_layout, menu);
            return true;
        }
        // Menu options and functionality
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
