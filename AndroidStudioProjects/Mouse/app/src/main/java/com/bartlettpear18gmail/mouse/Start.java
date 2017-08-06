package com.bartlettpear18gmail.mouse;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Start extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = new Intent(this, Mouse.class);
        startActivity(intent);
        System.out.println("Starting app");
        finish();
    }

}
