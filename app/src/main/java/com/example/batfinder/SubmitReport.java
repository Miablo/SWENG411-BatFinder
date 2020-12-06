    package com.example.batfinder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

    public class SubmitReport extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_report);

        // Button Submit Report Functionality
        Button submitButton = (Button) findViewById(R.id.reportsubmit);
        // perform click event on button
        submitButton.setOnClickListener(v -> {
            // Snackbar pop up at bottom to display confirmation message
            Snackbar.make(v, "Report Submitted Successfully. Thank You!", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        });
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
