package com.example.firstapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.File;


public class MainActivity extends AppCompatActivity {

    int Points = 0;
    int pointperclick = 1;
    Button Mybutton;
    Button UpgradeButton;
    ImageButton HomeButton;
    ImageButton ProfileButton;
    TextView Counter;
    TextView Errormessage;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Mybutton = (Button) findViewById(R.id.button);
        UpgradeButton = (Button) findViewById(R.id.buybutton);
        HomeButton = (ImageButton) findViewById(R.id.imageButton2);
        ProfileButton = (ImageButton) findViewById(R.id.imageButton3);
        Counter = (TextView) findViewById(R.id.textView2);
        Errormessage = (TextView) findViewById(R.id.Error);

        Points = PointsFile("points.txt");
        Counter.setText(Points + " pontis");

        Mybutton.setOnClickListener(v -> {
            Points += pointperclick;
            Counter.setText(Points + " pontis");
            WriteFile("points.txt", Points);
        });
        UpgradeButton.setOnClickListener(v -> {
            if (Points >= 25) {
                Points -= 25;
                pointperclick += 1;
                Counter.setText(Points + " pontis");
                UpgradeButton.setVisibility(View.INVISIBLE);
            }
            else {
                Errormessage.setVisibility(View.VISIBLE);
                Errormessage.setText("Error: " + "Not enough pontis");

                new Handler(Looper.getMainLooper()).postDelayed(() -> Errormessage.setVisibility(View.INVISIBLE), 1000);
            }
        });
        ProfileButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
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
    public void WriteFile(String name, int points){
        File Path = getApplicationContext().getFilesDir();
        try {
            FileOutputStream writer = new FileOutputStream(new File(Path, name));
            writer.write(String.valueOf(points).getBytes());
            writer.close();
            //Toast.makeText(getApplicationContext(), "File saved", Toast.LENGTH_SHORT).show();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

}