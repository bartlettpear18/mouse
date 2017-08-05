package com.bartlettpear18gmail.mouse;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity);
    }

    public void startMouse(View view) {
        Intent intent = new Intent(this, Mouse.class);
        startActivity(intent);
    }

    public void startPresentation (View view) {
        Intent intent = new Intent(this, com.bartlettpear18gmail.mouse.Presentation.class);
        startActivity(intent);
    }

    public void startNetwork(View view) {
        Intent intent = new Intent(this, com.bartlettpear18gmail.mouse.Network.class);
        startActivity(intent);
    }

}
