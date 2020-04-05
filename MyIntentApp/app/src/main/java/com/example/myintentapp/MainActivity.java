package com.example.myintentapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity  {

    Button btn_move_activity;
    Button btn_move_data_activity;
    Button btn_dial_phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_move_activity = findViewById(R.id.btn_move_activity);
        btn_move_data_activity = findViewById(R.id.btn_move_data_activity);
        btn_dial_phone = findViewById(R.id.btn_dial_phone);


        btn_move_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotosecond = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(gotosecond);
            }
        });

        btn_move_data_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoactivitywithdata = new Intent(MainActivity.this, MoveWithDataAct.class);

                gotoactivitywithdata.putExtra(MoveWithDataAct.EXTRA_NAME, "Aditiya Ihzar Eka Prayogo");
                gotoactivitywithdata.putExtra(MoveWithDataAct.EXTRA_AGE, 18);

                startActivity(gotoactivitywithdata);
                finish();
            }
        });

        btn_dial_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String phoneNumber = "081282432271";
                Intent gotodialnumber = new Intent(Intent.ACTION_DIAL, Uri.parse("tel: "+phoneNumber));
                startActivity(gotodialnumber);
                finish();
            }
        });


    }
}
