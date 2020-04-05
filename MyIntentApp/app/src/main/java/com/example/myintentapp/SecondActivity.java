package com.example.myintentapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SecondActivity extends AppCompatActivity {

    Button btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        btn_back = findViewById(R.id.btn_back);
        btn_back .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent gotohome = new Intent(SecondActivity.this, MainActivity.class);
                finish();

            }
        });


    }
}
