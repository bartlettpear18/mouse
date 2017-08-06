package com.bartlettpear18gmail.mouse;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.bartlettpear18gmail.mouse.Client.*;

import java.io.IOException;

import static com.bartlettpear18gmail.mouse.Client.*;


public class Mouse extends AppCompatActivity implements SensorEventListener{

    //Debug tag
    private static String tag = "Debug";

    //Constructor
    public Mouse() {}

    //Packet variables
    private int movementX = 0;
    private int movementY = 0;

    //Sensor setup
    private SensorManager sensorManager;
    private final double Z_LIMIT = 0.25;
    private float xAccel;
    private float yAccel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mouse);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL)


        Client client = new Client();
        Log.d(tag, "Client made");
        client.execute();
    }


    //Accelerometer values
    public int getMovementX() { return movementX; }
    public int getMovementY() { return movementY; }

    // Clicks
    public void leftClick(View view) {
        if(left == LEFT.OFF) { left = LEFT.ON; }
        else { left = LEFT.OFF; }
        Log.d(tag, "Sending left");
    }
    public void rightClick(View view) {
        if(right == RIGHT.OFF) { right = RIGHT.ON; }
        else { right = RIGHT.OFF; }
        Log.d(tag, "Sending right");
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {}
    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            if(event.values[2] < Z_LIMIT) {
                xAccel =  event.values[0];
                yAccel =  event.values[1];
                Log.d(tag, "X Movement: " + xAccel + "\nY Movement: " + yAccel);
            } else {
                xAccel = 0;
                yAccel = 0;
                Log.d(tag, "X and Y are 0, as Z is moving");
            }
        }
    }

}
