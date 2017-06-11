package com.bartlettpear18gmail.mouse;

import android.content.Context;
import android.content.IntentFilter;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.wifi.p2p.WifiP2pManager;
import android.net.wifi.p2p.WifiP2pManager.ActionListener;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import static com.bartlettpear18gmail.mouse.Position.displacement;

public class Mouse extends AppCompatActivity implements SensorEventListener {

    //Accelerometer setup
    private SensorManager mSensorManager;
    private Sensor mAccel;

    //Server setup
    public static Socket client;
    private static final int SERVER_PORT = 10000;
    private static final String SERVER_IP = "10.0.0.139";
    public static String debug = "debug";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Accelerometer Set up
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccel = mSensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
        mSensorManager.registerListener(this, mAccel, SensorManager.SENSOR_DELAY_NORMAL);
        /**
        try {
            client = new Socket(SERVER_IP, SERVER_PORT);
            Log.i(debug, "server connected");
        } catch (IOException e) {
            e.printStackTrace();
        }
         */

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        //System.out.println("Accuracy changed by: " + accuracy);
    }

    @Override
    public void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mAccel, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        Sensor acc = sensorEvent.sensor;

        if (acc.getType() == Sensor.TYPE_LINEAR_ACCELERATION) {
            float tempX = sensorEvent.values[0];
            float tempY = sensorEvent.values[1];
            float tempZ = sensorEvent.values[2];

            double x;
            double y;
            double z = (double) Math.round(tempZ * 100) / 100;
            if (Math.abs(z) > 1) {
                x = 0;
                y = 0;
            } else {
                x = (double) Math.round(tempX * 100) / 100;
                y = (double) Math.round(tempY * 100) / 100;


            }


            TextView accX = (TextView) findViewById(R.id.sensorX);
            String printAccX = "Accelerometer X: " + x;
            accX.setText(printAccX);

            TextView accY = (TextView) findViewById(R.id.sensorY);
            String printAccY = "Accelerometer Y: " + y;
            accY.setText(printAccY);

            TextView disX = (TextView) findViewById(R.id.disX);
            String printDisX = "Displacement X: " + displacement(x);
            disX.setText(printDisX);

            TextView disY = (TextView) findViewById(R.id.disY);
            String printDisY = "Displacement Y: " + displacement(y);
            disY.setText(printDisY);

        }
    }


    // Clicks
    public void leftClick(View view) {
        System.out.println("Left Click");
    }

    public void rightClick(View view) {
        System.out.println("Right Click");
    }

}
