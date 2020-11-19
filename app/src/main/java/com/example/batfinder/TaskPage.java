package com.example.batfinder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class TaskPage extends AppCompatActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_page);



        Button info = findViewById(R.id.info);
        info.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                openinfo();
            }
        });

        Button report = findViewById(R.id.report);
        report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openreport();
            }
        });

        Button map = findViewById(R.id.maps);
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openmap();
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
        }else
        return super.onOptionsItemSelected(item);
    }

    public void openinfo(){
        Intent intent = new Intent(this, InfoPageMain.class);
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


}