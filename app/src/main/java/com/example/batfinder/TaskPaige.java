package com.example.batfinder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.batfinder.ui.login.LoginActivity;

public class TaskPaige extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_paige);

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
    public void openinfo(){
        Intent intent = new Intent(this, InfoPaigeMain.class);
        startActivity(intent);
    }

    public void openreport(){
        Intent intent = new Intent(this, InfoPaigeMain.class); //need to fix right paige.
        startActivity(intent);
    }

    public void openmap(){
        Intent intent = new Intent(this, MapPaige.class); //need to fix right paige.
        startActivity(intent);
    }
}