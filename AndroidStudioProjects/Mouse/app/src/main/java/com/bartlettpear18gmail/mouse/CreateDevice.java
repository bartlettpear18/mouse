package com.bartlettpear18gmail.mouse;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class CreateDevice extends AppCompatActivity {

    HostDevice device = new HostDevice();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_device);

    }


    public void deviceName(View view) {
        EditText text = (EditText) findViewById(R.id.name);
        device.setName(text.getText().toString());
    }

    public void deviceAddress(View view) {
        EditText text = (EditText) findViewById(R.id.ipAddress);
        device.setIpAddress(Integer.parseInt(text.getText().toString()));
    }

    public void serverPort(View view) {
        EditText text = (EditText) findViewById(R.id.port);
        device.setPort(Integer.parseInt(text.getText().toString()));
    }

    public void screenX (View view) {
        EditText text = (EditText) findViewById(R.id.screenX);
        device.setDimensionX(Integer.parseInt(text.getText().toString()));
    }

    public void screenY (View view) {
        EditText text = (EditText) findViewById(R.id.screenY);
        device.setDimensionY(Integer.parseInt(text.getText().toString()));
    }
}
