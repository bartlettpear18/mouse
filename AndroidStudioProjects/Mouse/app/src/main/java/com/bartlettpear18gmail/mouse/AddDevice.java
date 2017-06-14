package com.bartlettpear18gmail.mouse;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import io.realm.Realm;

public class AddDevice extends AppCompatActivity {

    private Realm realm;
    Device device;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_device);

        realm = Realm.getDefaultInstance();
        device = new Device();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
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
