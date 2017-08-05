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


public class Mouse extends AppCompatActivity {

    //Debug tag
    private static String tag = "Debug";

    //Accelerometer setup
    private SensorManager mSensorManager;
    private Sensor mAccel;
    private static int x = 0;
    private static int y = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mouse);


        Client client = new Client();
        Log.d(tag, "Client made");
        client.execute();
    }

//        //Accelerometer Set up
//        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
//        mAccel = mSensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
//        mSensorManager.registerListener(this, mAccel, SensorManager.SENSOR_DELAY_NORMAL);


//
//    @Override
//    public void onAccuracyChanged(Sensor sensor, int accuracy) {
//        //System.out.println("Accuracy changed by: " + accuracy);
//    }
//
//    @Override
//    public void onPause() {
//        super.onPause();
//        mSensorManager.unregisterListener(this);
//    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        mSensorManager.registerListener(this, mAccel, SensorManager.SENSOR_DELAY_NORMAL);
//    }
//
//    @Override
//    public void onSensorChanged(SensorEvent sensorEvent) {
//        Sensor acc = sensorEvent.sensor;
//
//        if (acc.getType() == Sensor.TYPE_LINEAR_ACCELERATION) {
//            float tempX = sensorEvent.values[0];
//            float tempY = sensorEvent.values[1];
//            float tempZ = sensorEvent.values[2];
//
//            double x;
//            double y;
//            double z = (double) Math.round(tempZ * 100) / 100;
//            if (Math.abs(z) > 1) {
//                x = 0;
//                y = 0;
//            } else {
//                x = Math.round(tempX * 100) / 100;
//                y = Math.round(tempY * 100) / 100;
//
//
//            }
//
//            /**
//            TextView accX = (TextView) findViewById(R.id.sensorX);
//            String printAccX = "Accelerometer X: " + x;
//            accX.setText(printAccX);
//
//            TextView accY = (TextView) findViewById(R.id.sensorY);
//            String printAccY = "Accelerometer Y: " + y;
//            accY.setText(printAccY);
//
//            TextView disX = (TextView) findViewById(R.id.disX);
//            String printDisX = "Displacement X: " + displacement(x);
//            disX.setText(printDisX);
//
//            TextView disY = (TextView) findViewById(R.id.disY);
//            String printDisY = "Displacement Y: " + displacement(y);
//            disY.setText(printDisY);
//             */
//
//        }
//    }
//
//    public static int getX() { return x; }
//    public static int getY() { return y; }
//
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

}
