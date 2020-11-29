package com.example.batfinder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class SubmitReport extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_report);
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
            }else
                return super.onOptionsItemSelected(item);
        }

        public void openinfo(){
            Intent intent = new Intent(this, Cardview.class);
            startActivity(intent);
        }

        public void openreport(){
            Intent intent = new Intent(this, SubmitReport.class); //need to fix right paige.
            startActivity(intent);
        }

        public void openmap(){
            Intent intent = new Intent(this, MapPage.class); //need to fix right paige.
            startActivity(intent);
        }
    }
