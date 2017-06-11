package com.bartlettpear18gmail.mouse;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Start extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
    }

    public void start(View view) {
        Intent intent = new Intent(this, Devices.class);
        startActivity(intent);
        System.out.println("Starting app");
    }

}
