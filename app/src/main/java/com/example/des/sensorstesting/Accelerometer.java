package com.example.des.sensorstesting;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by home on 19/11/2017.
 */

public class Accelerometer extends Service implements SensorEventListener {
    private SensorManager sensorManager;
    double ax,ay,az;   // these are the acceleration in x,y and z axis

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        sensorManager=(SensorManager) getSystemService(SENSOR_SERVICE);
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);



    }

    @Override
    public void onAccuracyChanged(Sensor arg0, int arg1) {
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType()== Sensor.TYPE_ACCELEROMETER){
            Intent i = new Intent("location_update");
            ax=event.values[0];
            String axSt = Double.toString(ax);
            i.putExtra("ax", axSt);
            ay=event.values[1];
            String aySt = Double.toString(ay);
            i.putExtra("ay", aySt);
            az=event.values[2];
            String azSt = Double.toString(az);
            i.putExtra("az", azSt);
            sendBroadcast(i);
        }

    }


    @Override
    public void onDestroy() {
        super.onDestroy();

    }
}
