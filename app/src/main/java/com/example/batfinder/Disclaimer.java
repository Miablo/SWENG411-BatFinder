package com.example.batfinder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Disclaimer extends AppCompatActivity {

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disclamier);

        textView = findViewById(R.id.textView);
        ReadTextFile();


        Button button = findViewById(R.id.acept);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openTask();
            }
        });
    }

    public void ReadTextFile(){
        String string = "";
        StringBuilder stringBuilder = new StringBuilder();
        InputStream is = getResources().openRawResource(R.raw.dis);
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

    public void openTask(){
        Intent intent = new Intent(this, TaskPaige.class);
        startActivity(intent);
    }
}