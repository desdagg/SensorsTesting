package com.example.des.sensorstesting;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by home on 19/11/2017.
 */

public class SensorDisplay extends Activity {

    private TextView longitudeTxt, latitudeTxt, aYTxt, aXTxt, aZTxt;
    private Button btn1;
    private BroadcastReceiver broadcastReceiver;


    @Override
    protected void onResume() {
        super.onResume();
        if(broadcastReceiver == null){
            broadcastReceiver = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {

                    //Bundle b = intent.getExtras();

                    //String longitudeStr = null;

                   // if(b!=null){
                    //    longitudeStr = (String) b.get("longitude");
                   // }

                    //String longitudeStr = (String) intent.getExtras().get("longitude");
                    //String latitudeStr = (String) intent.getExtras().get("longitude");
                    //longitudeTxt.setText(longitudeStr);
                    //latitudeTxt.setText(latitudeStr);
                    //longitudeTxt.append("\n" +intent.getExtras().get("longitude"));
                    if(intent.getExtras().get("latitude") != null)
                        latitudeTxt.setText("\n" +intent.getExtras().get("latitude"));
                    if(intent.getExtras().get("longitude") != null)
                        longitudeTxt.setText("\n" + intent.getExtras().get("longitude"));
                    aXTxt.setText("\n" +intent.getExtras().get("ax"));
                    aYTxt.setText("\n" + intent.getExtras().get("ay"));
                    aZTxt.setText("\n" + intent.getExtras().get("az"));


                }
            };
        }
        registerReceiver(broadcastReceiver,new IntentFilter("location_update"));
    }

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sensor_display);
        longitudeTxt = findViewById(R.id.longitudeView);
        latitudeTxt = findViewById(R.id.latitudeView);
        aXTxt = findViewById(R.id.textViewX);
        aYTxt = findViewById(R.id.textViewY);
        aZTxt = findViewById(R.id.textViewZ);
        btn1 = findViewById(R.id.button);


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), GPS_Service.class);
                Intent j = new Intent(getApplicationContext(), Accelerometer.class);
                startService(i);
                startService(j);

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(broadcastReceiver != null){
            unregisterReceiver(broadcastReceiver);
        }
    }

}
