package com.example.firstapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.content.Intent;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.File;
import java.io.FileInputStream;


public class SecondActivity extends AppCompatActivity {

    int Points = 0;
    int pointperclick = 1;
    ImageButton HomeButton;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        HomeButton = (ImageButton) findViewById(R.id.imageButton5);
        TextView Counter = (TextView) findViewById(R.id.textView4);
        Points = PointsFile("points.txt");
        Counter.setText(Points + " pontis");

        HomeButton.setOnClickListener(v -> {
            Intent intent = new Intent(SecondActivity.this, MainActivity.class);
            startActivity(intent);
        }
        );


    }
    public int PointsFile(String name){
        File Path = getApplicationContext().getFilesDir();
        File readfrom = new File(Path, name);
        byte[] readF = new byte[(int) readfrom.length()];
        try{
            FileInputStream stream = new FileInputStream(readfrom);

            stream.read(readF);
            int points = Integer.parseInt(new String(readF));
            stream.close();
            return points;
        } catch (Exception e){
            e.printStackTrace();
            return 0;
        }

    }
}