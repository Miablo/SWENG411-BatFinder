package com.example.batfinder;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Cardview extends AppCompatActivity {

    TextView textView;
    InputStream is;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardview);

        // Text Views
        textView = findViewById(R.id.alcathoeText);
        is = getResources().openRawResource(R.raw.alcathoe_bat_statistics);
        ReadTextFile();
        textView = findViewById(R.id.barbastelleText);
        is = getResources().openRawResource(R.raw.barbastelle_statistic);
        ReadTextFile();
        textView = findViewById(R.id.bechsteinText);
        is = getResources().openRawResource(R.raw.bechstein_statistic);
        ReadTextFile();
        textView = findViewById(R.id.brandtText);
        is = getResources().openRawResource(R.raw.brandt_statstic);
        ReadTextFile();
        textView = findViewById(R.id.brownlongearedText);
        is = getResources().openRawResource(R.raw.brown_long_eared_statistic);
        ReadTextFile();
        textView = findViewById(R.id.commonpipistrelleText);
        is = getResources().openRawResource(R.raw.common_pipistrelle_statistic);
        ReadTextFile();
        textView = findViewById(R.id.daubentonsText);
        is = getResources().openRawResource(R.raw.daubentons_statistics);
        ReadTextFile();
        textView = findViewById(R.id.greaterhorseshoeText);
        is = getResources().openRawResource(R.raw.greater_horshoe_statistics);
        ReadTextFile();
        textView = findViewById(R.id.greylongearededText);
        is = getResources().openRawResource(R.raw.grey_long_eared_statistics);
        ReadTextFile();
        textView = findViewById(R.id.leislersText);
        is = getResources().openRawResource(R.raw.leisler_statistics);
        ReadTextFile();
        textView = findViewById(R.id.lesserhorseshoeText);
        is = getResources().openRawResource(R.raw.lesser_horseshoe_statistics);
        ReadTextFile();
        textView = findViewById(R.id.nathusiuspipistrellText);
        is = getResources().openRawResource(R.raw.nathusius_pipistrelle_statistics);
        ReadTextFile();
        textView = findViewById(R.id.natterersText);
        is = getResources().openRawResource(R.raw.natterer_statistics);
        ReadTextFile();
        textView = findViewById(R.id.noctuleText);
        is = getResources().openRawResource(R.raw.noctule_statistics);
        ReadTextFile();
        textView = findViewById(R.id.serotineText);
        is = getResources().openRawResource(R.raw.serotine_bat_statistics);
        ReadTextFile();
        textView = findViewById(R.id.sopranopipistrelleText);
        is = getResources().openRawResource(R.raw.soprano_pipistrelle_bat_statistics);
        ReadTextFile();
        textView = findViewById(R.id.whiskeredText);
        is = getResources().openRawResource(R.raw.whiskered_bat_statistics);
        ReadTextFile();

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

    public void ReadTextFile(){
        String string = "";
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        while (true) {
            try {
                if ((string = reader.readLine()) == null) break;
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            stringBuilder.append(string).append("\n");
            textView.setText(stringBuilder);
        }
        try {
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}