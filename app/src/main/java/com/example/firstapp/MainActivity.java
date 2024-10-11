package com.example.firstapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;



public class MainActivity extends AppCompatActivity {

    int Points = 0;
    int pointperclick = 1;
    Button Mybutton;
    Button UpgradeButton;
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
        Mybutton =  (Button) findViewById(R.id.button);
        UpgradeButton = (Button) findViewById(R.id.buybutton);
        Counter = (TextView)findViewById(R.id.textView2);
        Errormessage = (TextView) findViewById(R.id.Error);
        //Counter.setText("me");

        Mybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Points += pointperclick;
                Counter.setText(String.valueOf(Points) + " pontis");
            }
        });
        UpgradeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Points >= 25) {
                    Points -= 25;
                    pointperclick += 1;
                    Counter.setText(String.valueOf(Points) + " pontis");
                    UpgradeButton.setVisibility(View.INVISIBLE);
                }
                else {
                    Errormessage.setVisibility(View.VISIBLE);
                    Errormessage.setText("Error: " + "Not enough pontis");

                    new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Errormessage.setVisibility(View.INVISIBLE);
                        }
                    }, 1000);
                }
            }
        });
    }
}